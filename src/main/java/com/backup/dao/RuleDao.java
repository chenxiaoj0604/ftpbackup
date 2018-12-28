package com.backup.dao;

import com.backup.domain.Rule;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by duanxun on 2018-12-24.
 */
@Component
public interface RuleDao {

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
     * 获取集合
     *
     * @param rule
     * @return
     */
    List<Rule> list(Rule rule);

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
     * 更新最后一次执行状态
     *
     * @param
     * @return
     */
    int updateLastExecuteStatus(Rule rule);

    /**
     * 根据任务名称和任务分组查询
     *
     * @param
     * @return
     */
    Rule getByTaskNameAndArea(Rule rule);
}
