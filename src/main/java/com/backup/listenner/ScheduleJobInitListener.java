package com.backup.listenner;

import com.backup.services.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

	@Autowired
	RuleService ruleService;

	@Override
	public void run(String... arg0) throws Exception {
		try {
			ruleService.initSchedule();
			log.info("计划任务初始化完成");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}