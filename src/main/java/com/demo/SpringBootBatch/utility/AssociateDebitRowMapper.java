package com.demo.SpringBootBatch.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.SpringBootBatch.entities.AssociateDebit;

public class AssociateDebitRowMapper implements RowMapper<AssociateDebit> {

	@Override
	public AssociateDebit mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
