package com.demo.poiDemo.impl;

import static com.demo.poiDemo.util.LoggerConfigUtil.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.demo.poiDemo.entity.EmployeeEntity;
import com.demo.poiDemo.intf.EmployeeDAOIntf;
import com.demo.poiDemo.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAOIntf {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(EmployeeDAOImpl.class);

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		// TODO Auto-generated method stub
		info(LOGGER, "Inside getAllEmployees DAO method");
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		List<EmployeeEntity> employeeRecords = new ArrayList<>();
		String queryString = "SELECT NAME,DESIGNATION,LOB,BONUS FROM AGENT";
		try {
			conn = DBConnectionUtil.getHikariDataSource().getConnection();
			info(LOGGER, "Connection was established");
			pstmt = conn.prepareStatement(queryString);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmployeeEntity employeeEntity = new EmployeeEntity(rs.getString("NAME"), rs.getString("DESIGNATION"),
						rs.getString("LOB"), rs.getBigDecimal("BONUS"));
				employeeRecords.add(employeeEntity);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		info(LOGGER, "List returned");
		return employeeRecords;
	}

}
