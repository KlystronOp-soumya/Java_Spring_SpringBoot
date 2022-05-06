package com.demo.batch.BatchDemo.readers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;
import com.demo.batch.BatchDemo.mappers.AgentBnsQualifRowMapper;

public class CustomeAgtBnsDetailsReader implements ItemReader<AgentBnsQualifEntity> {
	/* logger */
	private static final Logger LOGGER = Logger.getLogger(CustomeAgtBnsDetailsReader.class);

	@Autowired
	private transient DataSource dataSource;

	@Autowired
	private transient AgentBnsQualifRowMapper agtBnsQualifRowMapper;

	private ResultSet rs;

	private PreparedStatement pstmt;

	@Override
	public AgentBnsQualifEntity read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		LOGGER.info("Agent Bonus Qualif read");
		AgentBnsQualifEntity agtBnsQualifEntity = null;
		try {
			if (null == rs)
				initReader();
			else {
				agtBnsQualifEntity = this.agtBnsQualifRowMapper.mapRow(this.rs, this.rs.getRow());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return agtBnsQualifEntity;
	}

	protected String getQuery() {
		final String query = "SELECT AGT_ID,NAME,DESIGNATION,LOB,BONUS,BONUS_PCT,BONUS_PAYOUT,CALDAY,CALMONTH,CALYEAR,QUALIF_STATUS FROM AGENT_BONUS_QUALIF";
		return query;
	}

	protected void initReader() {
		LOGGER.info("Initializing the Reader");
		Connection conn = null;
		try {
			conn = this.dataSource.getConnection();
			this.pstmt = conn.prepareStatement(getQuery());

			this.rs = pstmt.executeQuery();

			if (rs == null)
				throw new NullPointerException("Empty Result set");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
