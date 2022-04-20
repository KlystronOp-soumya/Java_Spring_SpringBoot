package com.demo.batch.BatchDemo.services.intf;

import java.io.FileNotFoundException;

public interface ReportGenerator {

	void generateExcelReport() throws FileNotFoundException;

	void generateJasperReport();

	void generateCsvReport();

	void setAgentBonusService(AgentBonusIntf agentBonusService);
}
