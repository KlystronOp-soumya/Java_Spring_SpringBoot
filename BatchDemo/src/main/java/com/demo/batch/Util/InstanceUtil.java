package com.demo.batch.Util;

import com.demo.batch.BatchDemo.entity.Agent;

/*
 * This class returns the instance of the 
 * this is not any singleton class
 * */
public class InstanceUtil {

	public static Agent getAgentInstance() {
		
		return new Agent() ;
		
	}
	
}
