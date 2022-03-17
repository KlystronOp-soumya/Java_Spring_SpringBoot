package com.demo.batch.BatchDemo.writers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;


import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;
import com.demo.batch.Util.DateTimeUtil;
import com.demo.batch.Util.LoggerUtil;

public class CustomAgtBnsWriter implements ItemWriter<AgentBnsQualifEntity> {

	/* logger */
	private static final Logger LOGGER = Logger.getLogger(CustomAgtBnsWriter.class) ;

	private transient DataSource dataSource ;

	private Connection connection=null;

	private ResultSet rs=null;

	private Statement stmt=null;
	
	private PreparedStatement pstmt = null;

	protected String query ;

	public CustomAgtBnsWriter(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.dataSource = dataSource ;
	}
	@Override
	public void write(List<? extends AgentBnsQualifEntity> items) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			if(!items.isEmpty())
				this.initWriter(items);
			LoggerUtil.info(LOGGER, "Writing of the agents completed");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void initWriter(List<? extends AgentBnsQualifEntity> items) {
		final String queryString = "INSERT INTO AGENT_BONUS_QUALIF(AGT_ID,NAME,DESIGNATION,LOB,bonus,bonus_pct,bonus_payout,calDay,calMonth,calYear,qualif_status)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?) " ;
		try {
			this.connection = this.dataSource.getConnection() ;
			pstmt = this.connection.prepareStatement(queryString);
			for(AgentBnsQualifEntity eachObj : items)
			{
				LoggerUtil.debug(LOGGER, "Writing of an agent under going....");
				pstmt.clearParameters();
				pstmt.setString(1, eachObj.getAgtId());
				pstmt.setString(2, eachObj.getName());
				pstmt.setString(3, eachObj.getDesignation());
				pstmt.setString(4, eachObj.getLOB());
				pstmt.setBigDecimal(5, eachObj.getBonusAmt());
				pstmt.setBigDecimal(6, eachObj.getBonusPerct());
				pstmt.setBigDecimal(7, eachObj.getBonusPayout());
				pstmt.setInt(8, eachObj.getCalDay());
				pstmt.setInt(9, eachObj.getCalMonth());
				pstmt.setInt(10, eachObj.getCalYear());
				pstmt.setInt(11, eachObj.getQualifStatus());
				
				pstmt.executeLargeUpdate();
				
				LoggerUtil.debug(LOGGER, "Agent was written...");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private void closeWriter() {
		if(pstmt!=null)
		{
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(connection!=null)
		{
			try {
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		LoggerUtil.debug(LOGGER,"Connection was closed successfully");
	}

}
