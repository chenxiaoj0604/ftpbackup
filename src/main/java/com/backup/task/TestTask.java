package com.backup.task;

import com.backup.domain.Rule;
import com.backup.services.BackupService;
import com.backup.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;

/**
 * Created by duanxun on 2018-12-26.
 */

@DisallowConcurrentExecution
@Component
@Slf4j
public class TestTask implements Job {

    @Autowired
    BackupService backupService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Rule rule = (Rule) jobDataMap.get("rule");
        //type 0:根据文件名 1:根据时间 2:根据正则表达式
        if(rule.getType().equals("0")){
            log.info("**根据文件名");
            R r = backupService.backupByFileName(rule);
            backupService.resultDeal(r,rule);
        }else if (rule.getType().equals("1")){
            log.info("**根据时间");
            R r = backupService.backupByTime(rule);
            backupService.resultDeal(r,rule);
        }else if(rule.getType().equals("2")){
            log.info("**根据正则表达式");
            R r = backupService.backupByRegex(rule);
            backupService.resultDeal(r,rule);
        }
    }
}
