package com.demo.poiDemo.intf;

import java.util.List;

import com.demo.poiDemo.entity.EmployeeEntity;

public interface PoiOperationsIntf {

	void createWorkBook(String fileName, String sheetName);

	void setData(List<EmployeeEntity> e);

}
