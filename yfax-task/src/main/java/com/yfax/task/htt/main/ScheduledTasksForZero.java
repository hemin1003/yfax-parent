package com.yfax.task.htt.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.service.HttAppUserService;

/**
 * 零点任务中心
 */
@Component
public class ScheduledTasksForZero {

	protected static Logger logger = LoggerFactory.getLogger(ScheduledTasksForZero.class);

	@Autowired
	private HttAppUserService httAppUserService;

	// 1. 重置签到等标志任务
	@Scheduled(cron = "0 0 0 * * *")
	public void httTaskOfReset() {
		logger.info("============（零点任务中心）-重置签到等标志任务，start===================");
		this.httAppUserService.batchResetDailyCheckInHtt();
		logger.info("============（零点任务中心）-重置签到等标志任务，end===================");
	}

}
