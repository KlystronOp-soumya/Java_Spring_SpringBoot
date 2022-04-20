package com.demo.batch.BatchDemo.tasklets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;
import com.demo.batch.BatchDemo.enums.Constants;
import com.demo.batch.BatchDemo.services.intf.AgentBonusIntf;
import com.demo.batch.BatchDemo.services.intf.ReportGenerator;

@Component
public class AgentReportGenerator implements Tasklet, ReportGenerator {

	// Get data from Serviece class
	private AgentBonusIntf agentBonusService;

	private HSSFWorkbook workBook;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		try {
			generateExcelReport();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return RepeatStatus.FINISHED;
	}

	@Override
	public void generateExcelReport() throws FileNotFoundException {
		// TODO Auto-generated method stub
		int rowCounter = 0, colCounter = 0;
		List<String> header = new ArrayList<String>(Arrays.asList("AGENT", "NAME", "DESIGNATION", "LOB", "BONUS",
				"BONUS_PCT", "BONUS_PAYOUT", "CALDAY", "CALMONTH", "CALYEAR"));
		try {
			// Create the
			workBook = new HSSFWorkbook();
			try (OutputStream fileOut = new FileOutputStream(Constants.EXCEL_REPORT_PATH.value, false)) {

				Sheet sheet1 = this.workBook.createSheet("Agent Bonus");// creates a steam

				CellStyle style = this.workBook.createCellStyle(); // Creating Style
				HSSFFont font = this.workBook.createFont();
				// font.setFontHeightInPoints((short)11);
				font.setFontName("Courier New");// set the font
				// font.setItalic(true);
				// font.setStrikeout(true);
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);// set the font weight
				// Applying font to the style
				style.setFont(font); // applying to the style
				Row row = sheet1.createRow(rowCounter);

				// write the headers
				for (String h : header) {
					Cell cell = row.createCell(colCounter);// creates a cell
					cell.setCellValue(h);// populates
					cell.setCellStyle(style);
					colCounter++;
				}
				rowCounter++;
				colCounter = 0;// reset

				List<AgentBnsQualifEntity> qualifAgtList = this.agentBonusService.getAgtQualifList();

				for (AgentBnsQualifEntity eachEntity : qualifAgtList) {
					Row r = sheet1.createRow(rowCounter);
					Cell c0 = r.createCell(colCounter);
					c0.setCellValue(eachEntity.getAgtId());
					colCounter++;
					Cell c1 = r.createCell(colCounter);
					c1.setCellValue(eachEntity.getName());
					colCounter++;
					Cell c3 = r.createCell(colCounter);
					c3.setCellValue(eachEntity.getDesignation());
					colCounter++;
					Cell c4 = r.createCell(colCounter);
					c4.setCellValue(eachEntity.getLOB());
					colCounter++;
					Cell c5 = r.createCell(colCounter);
					c5.setCellValue(eachEntity.getBonusAmt().toString());
					colCounter++;
					Cell c6 = r.createCell(colCounter);
					c6.setCellValue(eachEntity.getBonusPerct().toString());
					colCounter++;
					Cell c7 = r.createCell(colCounter);
					c7.setCellValue(eachEntity.getBonusPayout().toString());
					colCounter++;
					Cell c8 = r.createCell(colCounter);
					c8.setCellValue(eachEntity.getCalDay());
					colCounter++;
					Cell c9 = r.createCell(colCounter);
					c9.setCellValue(eachEntity.getCalMonth());
					colCounter++;
					Cell c10 = r.createCell(colCounter);
					c10.setCellValue(eachEntity.getCalYear());
					colCounter++;
					// reset colCunter
					colCounter = 0;
					rowCounter++;
				}
				this.workBook.write(fileOut);
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void generateJasperReport() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateCsvReport() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAgentBonusService(AgentBonusIntf agentBonusIntf) {
		// TODO Auto-generated method stub
		this.agentBonusService = agentBonusIntf;
	}

}
