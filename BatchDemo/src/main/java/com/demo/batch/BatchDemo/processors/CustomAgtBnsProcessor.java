package com.demo.batch.BatchDemo.processors;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.demo.batch.BatchDemo.entity.Agent;
import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;
import com.demo.batch.BatchDemo.mappers.AgentDtlsRowMapper;
import com.demo.batch.Util.DateTimeUtil;
import com.demo.batch.Util.LoggerUtil;

public class CustomAgtBnsProcessor implements ItemProcessor<Agent, AgentBnsQualifEntity> {
	
	/* logger */
	private static final Logger LOGGER = Logger.getLogger(CustomAgtBnsProcessor.class) ;
	
	@Override
	public AgentBnsQualifEntity process(Agent item) throws Exception {
		// TODO Auto-generated method stub
		LoggerUtil.info(LOGGER, "Processing an Agent...");
		AgentBnsQualifEntity qualifEntity = new AgentBnsQualifEntity();
		
		int qualifSt = 0;
		BigDecimal bonusPayout= BigDecimal.ZERO;
		final BigDecimal bonusPct = new BigDecimal(0.05) ;
		//if the bonus amount is 1000 and above then only eligible for bonus
		if(item.getBonusAmt().compareTo(new BigDecimal(999)) == 1) 
		{
			qualifSt=1;
			bonusPayout = item.getBonusAmt().multiply(bonusPct);
		}
		qualifEntity.setAgtId(item.getAgtId());
		qualifEntity.setName(item.getName());
		qualifEntity.setDesignation(item.getDesignation());
		qualifEntity.setLOB(item.getLOB());
		qualifEntity.setBonusAmt(item.getBonusAmt());
		qualifEntity.setBonusPerct(bonusPct);
		qualifEntity.setBonusPayout(bonusPayout);
		qualifEntity.setCalDay(DateTimeUtil.getCalYear());
		qualifEntity.setCalMonth(DateTimeUtil.getCalMonth());
		qualifEntity.setCalYear(DateTimeUtil.getCalYear());
		
		qualifEntity.setQualifStatus(qualifSt);
		
		LoggerUtil.info(LOGGER, "Agent Bonus processed");
		return qualifEntity ;
	}

}
