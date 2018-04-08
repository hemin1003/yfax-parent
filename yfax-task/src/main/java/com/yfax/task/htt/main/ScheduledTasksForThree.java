package com.yfax.task.htt.main;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.service.HttDeleteTableDataService;
import com.yfax.utils.DateUtil;

/**
 * 三点任务中心
 */
@Component
public class ScheduledTasksForThree {

	protected static Logger logger = LoggerFactory.getLogger(ScheduledTasksForThree.class);

	@Autowired
	private HttDeleteTableDataService httDeleteTableDataService;

	// 3. 迁移htt_read_his数据任务
	@Scheduled(cron = "0 45 3 * * *")
	public void httTaskOfTransferReadHis() {
		logger.info("============（三点任务中心）-迁移htt_read_his数据任务，start===================");
		Map<String, Object> map = new HashMap<>();
		map.put("currentTime", DateUtil.getCurrentDate(-1));
		boolean flag = this.httDeleteTableDataService.insertHttReadHis(map);
		logger.info("迁移htt_read_his数据任务（插入数据），执行结果flag=" + flag);
		if (flag) {
			flag = this.httDeleteTableDataService.deleteHttReadHis(map);
			logger.info("迁移htt_read_his数据任务（清除数据），执行结果flag=" + flag);
		}
		logger.info("============（三点任务中心）-迁移htt_read_his数据任务，end===================");
	}

}
