package com.backup.services.impl;

import com.backup.dao.RuleDao;
import com.backup.domain.Rule;
import com.backup.enums.Rwzt;
import com.backup.services.RuleService;
import com.backup.utils.QuartzManager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by duanxun on 2018-12-24.
 */
@Service
@Slf4j
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDao ruleDao;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public boolean runJob(String id) {
        Rule rule = get(id);
        if (rule != null){
            try {
                quartzManager.runAJobNow(rule);
                return true;
            }catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public int save(Rule rule) {
        rule.setTaskStatus(Rwzt.TY.getIndex());
        return ruleDao.save(rule);
    }

    @Override
    public Rule get(String id) {
        return ruleDao.get(id);
    }

    @Override
    public boolean changeStatus(String id) {
        try {
            Rule rule = get(id);
            if (Objects.equals(rule.getTaskStatus(),Rwzt.KQ.getIndex())){
                rule.setTaskStatus(Rwzt.TY.getIndex());
                quartzManager.deleteJob(rule);
            } else {
                rule.setTaskStatus(Rwzt.KQ.getIndex());
                quartzManager.addJob(rule);
            }
            update(rule);
            return true;
        }catch (Exception e){
            log.error("异常信息",e);
            return false;
        }
    }

    @Override
    public PageInfo<Rule> list(Integer pageNumber, Integer pageSize, Rule rule) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(ruleDao.list(rule));
    }

    @Override
    public int update(Rule rule) {
        return ruleDao.update(rule);
    }

    @Override
    public int del(String id) {
        return ruleDao.del(id);
    }

    @Override
    public void initSchedule() {
        // 这里获取任务信息数据
        List<Rule> jobList = ruleDao.list(new Rule());
        for (Rule rule : jobList) {
            if (Rwzt.KQ.getIndex().equals(rule.getTaskStatus())) {
                quartzManager.addJob(rule);
            }
        }
    }

    @Override
    public int updateLastExecuteStatus(String taskName, String taskArea, String lastExecuteStatus) {
        Rule rule = new Rule();
        rule.setTaskName(taskName);
        rule.setTaskArea(taskArea);
        rule.setLastExecuteStatus(lastExecuteStatus);
        return ruleDao.updateLastExecuteStatus(rule);
    }

    @Override
    public Rule exist(Rule rule) {
        return ruleDao.getByTaskNameAndArea(rule);
    }
}
