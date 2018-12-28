package com.backup.domain;

import lombok.Data;

/**
 * Created by duanxun on 2018-12-24.
 */
@Data
public class Rule {

    //规则id
    private String id;

    //任务名称
    private String taskName;

    //任务分组
    private String taskArea;

    //任务明细
    private String taskDetail;

    //执行类名
    private String executeClassName;

    //任务状态
    private String taskStatus;

    //上次执行状态
    private String lastExecuteStatus;

    //规则类型
    private String type;

    //源ftp地址
    private String sourceFTPIP;

    //目标ftp地址
    private String targetFTPIP;

    //源ftp目录
    private String sourceFTPDir = "";

    //目标ftp目录
    private String targetFTPDir = "";

    //源ftp用户名
    private String sourceFTPUsername;

    //目标ftp用户名
    private String targetFTPUsername;

    //源ftp密码
    private String sourceFTPPassword;

    //目标ftp密码
    private String targetFTPPassword;

    //源文件名
    private String sourceFileName;

    //后缀
    private String suffix;

    //源文件名后拼接的日期格式
    private String sourceDateFormat;

    //重命名文件名
    private String targetFileName;

    //重命名文件名后拼接的日期格式
    private String targetDateFormat;

    //保留天数
    private Long saveDays;

    //备份时间cron
    private String backupTimeCron;

    //源ftp上文件天数
    private Long getDays;

    //正则表达式
    private String regex;

    //文件数量
    private Long fileNum;

}
