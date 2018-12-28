package com.backup.services;

import com.backup.domain.Rule;
import com.github.pagehelper.PageInfo;

/**
 * Created by duanxun on 2018-12-24.
 */
public interface RuleService {

    /**
     * 立即执行任务
     * @param id
     * @return
     */
    boolean runJob(String id);

    /**
     * 保存
     *
     * @param rule
     * @return
     */
    int save(Rule rule);

    /**
     * id查询
     *
     * @param id
     * @return
     */
    Rule get(String id);


    /**
     * 改变状态
     * @param id
     * @return
     */
    boolean changeStatus(String id);


    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param rule
     * @return
     */
    PageInfo<Rule> list(Integer pageNumber, Integer pageSize, Rule rule);

    /**
     * 更新
     *
     * @param rule
     * @return
     */
    int update(Rule rule);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int del(String id);

    /**
     * 初始化job
     */
    void initSchedule();

    /**
     * 更新最后一次执行状态
     *
     * @param
     * @return
     */
    int updateLastExecuteStatus(String taskName,String taskArea,String lastExecuteStatus);

    /**
     * 是否已存在
     *
     * @param
     * @return
     */
    Rule exist(Rule rule);
}
