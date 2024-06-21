package com.demo.unittesting.business;

import com.demo.unittesting.services.SomeDataService;

public class BusinessImpl {

	private SomeDataService dataService;

	public SomeDataService getDataService() {
		return dataService;
	}

	public void setDataService(SomeDataService dataService) {
		this.dataService = dataService;
	}

	public BusinessImpl() {
		super();
	}

	public BusinessImpl(SomeDataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int calculateSum(int[] data) {

		int sum = 0;
		for (int i : data) {
			sum += i;
		}

		return sum;

	}

	public int calculateSumDataService() {

		int[] data = this.dataService.getData();
		int sum = 0;
		for (int i : data) {
			sum += i;
		}

		return sum;

	}

}
