package com.demo.springbatch.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

public class QrtzTriggerListener implements TriggerListener {

	public String getName() {
		// TODO Auto-generated method stub
		return "daily_Demo_Trigger_Listener";
	}

	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		
	}

	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	public void triggerMisfired(Trigger trigger) {
		// TODO Auto-generated method stub
		
	}

	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		// TODO Auto-generated method stub
		
	}


}
