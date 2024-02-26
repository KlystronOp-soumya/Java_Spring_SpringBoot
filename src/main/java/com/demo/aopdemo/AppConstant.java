package com.demo.aopdemo;

public enum AppConstant {

	AGENT_CACHE_NAME("agentCache") , ALL_AGENT_CACHE_NAME("allAgentCache") ;
	private String value ;
	
	private AppConstant(final String value) {
	 this.value = value ;
	}

	public String value() {
		return value;
	}
	
	
}
