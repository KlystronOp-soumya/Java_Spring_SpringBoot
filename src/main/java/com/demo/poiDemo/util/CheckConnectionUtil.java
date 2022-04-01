package com.demo.poiDemo.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckConnectionUtil {

	private static Logger LOGGER = LogManager.getLogger(CheckConnectionUtil.class);

	public static void checkDbConnection() {
		Connection conn = null;
		try {
			conn = DBConnectionUtil.getMySqlDataSource().getConnection();
			if (conn != null) {
				LOGGER.info("Connection Established Successfully");
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
	}

	public static void checkBasicDbConnection() {
		Connection conn = null;
		try {
			conn = DBConnectionUtil.getBasicDataSource().getConnection();
			if (conn != null) {
				LOGGER.info("Connection Established Successfully");
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
	}

	public static void checkHikariDs() {
		Connection conn = null;
		try {
			conn = DBConnectionUtil.getHikariDataSource().getConnection();
			if (conn != null) {
				LOGGER.info("Connection Established Successfully");
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

	}
}
