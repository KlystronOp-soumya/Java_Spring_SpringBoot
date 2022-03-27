package com.demo.poiDemo.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.demo.poiDemo.entity.EmployeeEntity;
import com.demo.poiDemo.intf.PoiOperationsIntf;

public class PoiOperationsImpl implements PoiOperationsIntf {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(PoiOperationsImpl.class);

	private transient Workbook workBook;

	protected int rowCounter;
	protected int colCounter;

	protected List<String> headers = new ArrayList<String>(Arrays.asList("Name", "Designation", "Salary"));;

	private List<EmployeeEntity> data;

	public PoiOperationsImpl() {
		// TODO Auto-generated constructor stub
		this.workBook = new HSSFWorkbook();
		this.rowCounter = 0;
		this.colCounter = 0;
	}

	@Override
	public void createWorkBook(String fileName, String sheetName) {
		// TODO Auto-generated method stub
		LOGGER.info("Creating WorkBook...");
		// opens a stream
		try (OutputStream fileOut = new FileOutputStream("myworkbook.xls")) {

			LOGGER.info("WorkBook Created....");

			Sheet sheet1 = this.workBook.createSheet(sheetName);// creates a steam

			CellStyle style = this.workBook.createCellStyle(); // Creating Style
			Font font = this.workBook.createFont();
			// font.setFontHeightInPoints((short)11);
			font.setFontName("Courier New");// set the font
			// font.setItalic(true);
			// font.setStrikeout(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);// set the font weight
			// Applying font to the style
			style.setFont(font); // applying to the style
			Row row = sheet1.createRow(rowCounter);

			// write the headers
			for (String h : this.headers) {
				LOGGER.info("Header created:: " + h);
				Cell cell = row.createCell(colCounter);// creates a cell
				cell.setCellValue(h);// populates
				cell.setCellStyle(style);
				colCounter++;
			}
			rowCounter++;
			colCounter = 0;// reset

			for (EmployeeEntity eachEntity : this.data) {
				Row r = sheet1.createRow(rowCounter);
				Cell c0 = r.createCell(colCounter);
				c0.setCellValue(eachEntity.getEmpName());
				colCounter++;
				Cell c1 = r.createCell(colCounter);
				c1.setCellValue(eachEntity.getEmpDesig());
				colCounter++;
				Cell c3 = r.createCell(colCounter);
				c3.setCellValue(eachEntity.getEmpSal().toString());
				// reset colCunter
				colCounter = 0;
				rowCounter++;
			}
			LOGGER.info("Excel Sheet Generated..");
			this.workBook.write(fileOut);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setData(List<EmployeeEntity> employeeEntitiesList) {
		this.data = employeeEntitiesList;// initializes with the data
	}
}
