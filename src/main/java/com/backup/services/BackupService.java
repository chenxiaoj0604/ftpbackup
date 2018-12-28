package com.backup.services;


import com.backup.domain.Rule;
import com.backup.utils.R;

/**
 * Created by duanxun on 2018-11-02.
 */
public interface BackupService {

    /**
     * 根据文件名备份数据库
     * @param rule
     * @return
     */
    R backupByFileName(Rule rule);

    /**
     * 根据时间备份数据库
     * @param rule
     * @return
     */
    R backupByTime(Rule rule);

    /**
     * 根据正则表达式备份数据库
     * @param rule
     * @return
     */
    R backupByRegex(Rule rule);


    /**
     * 结果处理
     * @param r
     */
    void resultDeal(R r, Rule rule);
}
