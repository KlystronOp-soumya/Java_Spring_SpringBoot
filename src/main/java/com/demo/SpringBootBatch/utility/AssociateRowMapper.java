package com.demo.SpringBootBatch.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.demo.SpringBootBatch.entities.Associates;

public class AssociateRowMapper implements RowMapper<Associates> {
	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(AssociateRowMapper.class);

	@Override
	public Associates mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Associates associateObj = new Associates(rs.getString("SERIAL_NUM"), rs.getString("COMPANY"),
				rs.getString("ASSOCIATE_NAME"), rs.getString("DESCRIPTION"), rs.getString("LEAVES"));

		LOGGER.debug("Fetched: " + associateObj);
		return associateObj;
	}

}
