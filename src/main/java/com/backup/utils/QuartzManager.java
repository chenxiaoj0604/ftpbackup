package com.backup.utils;

import com.backup.domain.Rule;
import org.quartz.*;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 
 * 
 * @title: QuartzManager.java
 * @description: 计划任务管理
 *
 */
@Service
public class QuartzManager {

	@Autowired
	private Scheduler scheduler;

	/**
	 * 添加任务
	 * 
	 * @param rule
	 * @throws SchedulerException
	 */
	
	public void addJob(Rule rule) {
		try {
			// 创建jobDetail实例，绑定Job实现类
			// 指明job的名称，所在组的名称，以及绑定job类
			Class<? extends org.quartz.Job> jobClass = (Class<? extends org.quartz.Job>) (Class.forName(rule.getExecuteClassName()).newInstance().getClass());
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("rule",rule);

			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(rule.getTaskName(), rule.getTaskArea()).setJobData(jobDataMap).build();

			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			triggerBuilder.startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND));
			triggerBuilder.withIdentity(rule.getTaskName(), rule.getTaskArea());
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(rule.getBackupTimeCron()).withMisfireHandlingInstructionDoNothing());
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();

			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(jobDetail, trigger);
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<Rule> getAllJob() throws SchedulerException {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<Rule> ruleList = new ArrayList<Rule>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				Rule rule = new Rule();
				rule.setTaskName(jobKey.getName());
				rule.setTaskArea(jobKey.getGroup());
				rule.setTaskDetail("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				rule.setTaskStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					rule.setBackupTimeCron(cronExpression);
				}
				ruleList.add(rule);
			}
		}
		return ruleList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<Rule> getRunningJob() throws SchedulerException {
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<Rule> ruleList = new ArrayList<Rule>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			Rule rule = new Rule();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			rule.setTaskName(jobKey.getName());
			rule.setTaskArea(jobKey.getGroup());
			rule.setTaskDetail("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			rule.setTaskStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				rule.setBackupTimeCron(cronTrigger.getCronExpression());
			}
			ruleList.add(rule);
		}
		return ruleList;
	}

	/**
	 * 暂停一个job
	 * 
	 * @param rule
	 * @throws SchedulerException
	 */
	public void pauseJob(Rule rule) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(rule.getTaskName(), rule.getTaskArea());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * @param rule
	 * @throws SchedulerException
	 */
	public void resumeJob(Rule rule) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(rule.getTaskName(), rule.getTaskArea());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * @param rule
	 * @throws SchedulerException
	 */
	public void deleteJob(Rule rule) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(rule.getTaskName(), rule.getTaskArea());
		scheduler.deleteJob(jobKey);

	}

	/**
	 * 立即执行job
	 * 
	 * @param rule
	 * @throws SchedulerException
	 */
	public void runAJobNow(Rule rule) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(rule.getTaskName(), rule.getTaskArea());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param rule
	 * @throws SchedulerException
	 */
	public void updateJobCron(Rule rule) throws SchedulerException {

		TriggerKey triggerKey = TriggerKey.triggerKey(rule.getTaskName(), rule.getTaskArea());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(rule.getBackupTimeCron());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}
}