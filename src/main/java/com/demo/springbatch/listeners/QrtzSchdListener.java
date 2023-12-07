package com.demo.springbatch.listeners;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class QrtzSchdListener implements SchedulerListener {

	public void jobScheduled(Trigger trigger) {
		System.out.println("");
		
	}

	public void jobUnscheduled(TriggerKey triggerKey) {
		// TODO Auto-generated method stub
		
	}

	public void triggerFinalized(Trigger trigger) {
		// TODO Auto-generated method stub
		
	}

	public void triggerPaused(TriggerKey triggerKey) {
		// TODO Auto-generated method stub
		
	}

	public void triggersPaused(String triggerGroup) {
		// TODO Auto-generated method stub
		
	}

	public void triggerResumed(TriggerKey triggerKey) {
		// TODO Auto-generated method stub
		
	}

	public void triggersResumed(String triggerGroup) {
		// TODO Auto-generated method stub
		
	}

	public void jobAdded(JobDetail jobDetail) {
		// TODO Auto-generated method stub
		System.out.println("Job added");
	}

	public void jobDeleted(JobKey jobKey) {
		// TODO Auto-generated method stub
		
	}

	public void jobPaused(JobKey jobKey) {
		// TODO Auto-generated method stub
		
	}

	public void jobsPaused(String jobGroup) {
		// TODO Auto-generated method stub
		
	}

	public void jobResumed(JobKey jobKey) {
		// TODO Auto-generated method stub
		
	}

	public void jobsResumed(String jobGroup) {
		// TODO Auto-generated method stub
		
	}

	public void schedulerError(String msg, SchedulerException cause) {
		// TODO Auto-generated method stub
		
	}

	public void schedulerInStandbyMode() {
		// TODO Auto-generated method stub
		
	}

	public void schedulerStarted() {
		// TODO Auto-generated method stub
		
	}

	public void schedulerStarting() {
		// TODO Auto-generated method stub
		
	}

	public void schedulerShutdown() {
		// TODO Auto-generated method stub
		
	}

	public void schedulerShuttingdown() {
		// TODO Auto-generated method stub
		
	}

	public void schedulingDataCleared() {
		// TODO Auto-generated method stub
		
	}

	
}
