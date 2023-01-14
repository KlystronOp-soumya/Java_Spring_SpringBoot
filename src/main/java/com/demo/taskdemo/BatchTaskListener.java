package com.demo.taskdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;

@Component("batchTaskListener")
public class BatchTaskListener implements TaskExecutionListener {

	private static final Logger LOGGER = LogManager.getLogger(BatchTaskListener.class);

	@Override
	public void onTaskStartup(TaskExecution taskExecution) {
		// TODO Auto-generated method stub
		LOGGER.info("Starting task: " + taskExecution.getTaskName());

	}

	@Override
	public void onTaskEnd(TaskExecution taskExecution) {
		// TODO Auto-generated method stub
		LOGGER.info("Task: " + taskExecution.getTaskName() + " completed at: " + taskExecution.getEndTime());
	}

	@Override
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
		// TODO Auto-generated method stub
		LOGGER.info("Failed: " + taskExecution.getErrorMessage() + " with: " + taskExecution.getExitMessage());
		LOGGER.info(throwable.getStackTrace());

	}

}
