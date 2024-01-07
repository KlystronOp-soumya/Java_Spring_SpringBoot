package com.demo.pwdmanager.dao;

import java.beans.Statement;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.persistence.EntityManager;

public class BaseDAO {

	public transient EntityManager entityManager;
	public PreparedStatement pstmt;
	public ResultSet rs;
	public Statement statement;
	public Connection dbConn;

	public BaseDAO() {

	}

	public void clearResources() {
		PrintWriter outWriter = new PrintWriter(new OutputStreamWriter(System.err));
		try {

			if (this.rs != null || !this.rs.isClosed()) {
				rs.close();
			}
			if (this.pstmt != null || !this.pstmt.isClosed()) {
				pstmt.close();
			}
			if (null != this.dbConn || !this.dbConn.isClosed()) {
				this.dbConn.close();
			}

		} catch (Exception e) {
			outWriter.write("Could not close resource!!" + e.getMessage());
			outWriter.write("Reason:" + e.getCause().toString());
		}

	}

}
