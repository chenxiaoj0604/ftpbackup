package com.backup.controller;

import com.backup.domain.Rule;
import com.backup.enums.Rwzt;
import com.backup.services.RuleService;
import com.backup.utils.R;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by duanxun on 2018-12-24.
 */
@Controller
@Slf4j
@RequestMapping("rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    private String prefix = "rule/";

    @ModelAttribute
    public Rule get(@RequestParam(required = false) String id){
        if (StringUtils.isNotBlank(id)){
            return ruleService.get(id);
        } else {
            return new Rule();
        }
    }

    @GetMapping("")
    public String ftpBackupJob(){
        return prefix + "list";
    }

    @GetMapping("/edit")
    public String edit(Rule rule, Model model){
        model.addAttribute("rule",rule);
        model.addAttribute("taskStatus", Rwzt.getValueMap());
        return prefix + "edit";
    }

    @PostMapping("/save")
    @ResponseBody
    public R save(Rule rule){
        try {
            Class.forName(rule.getExecuteClassName()).newInstance();
        } catch (Exception e) {
            return R.error("执行类名无效");
        }

        if (StringUtils.isBlank(rule.getId())) {
            if(ruleService.exist(rule) != null){
                return R.error("分组下已有该任务");
            }
            return ruleService.save(rule) > 0 ? R.ok("保存成功") : R.error("保存失败");
        } else {
            if (Objects.equals(rule.getTaskStatus(), Rwzt.KQ.getIndex())){
                return R.error("更新前先停止任务");
            }

            return ruleService.update(rule) > 0 ? R.ok("更新成功") : R.error("更新失败");
        }

    }


    @PostMapping("/del")
    @ResponseBody
    public R del(String id){
        return ruleService.del(id) > 0 ? R.ok("删除成功") : R.error("删除失败");
    }


    @PostMapping("/list")
    @ResponseBody
    public PageInfo<Rule> list(Integer pageNumber, Integer pageSize, Rule rule){
        return ruleService.list(pageNumber,pageSize,rule);
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public R changeStatus(String id,String taskStatus){
        String tip = "";
        if (Objects.equals(taskStatus,Rwzt.KQ.getIndex())){
            tip = "停用";
        } else {
            tip = "启用";
        }

        boolean result = ruleService.changeStatus(id);
        return result ? R.ok("任务" + tip + "成功") : R.error("任务" + tip + "失败");

    }

    @PostMapping("/runJob")
    @ResponseBody
    public R runJob(String id,String taskStatus){
        if (Objects.equals(taskStatus,Rwzt.TY.getIndex())){
            return R.error("请先开启任务");
        }
        boolean result = ruleService.runJob(id);
        return  result ? R.ok("执行成功") : R.error("执行失败");
    }
}
