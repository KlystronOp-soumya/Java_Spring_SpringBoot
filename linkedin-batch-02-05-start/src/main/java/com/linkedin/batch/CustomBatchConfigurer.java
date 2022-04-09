package com.linkedin.batch;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.transaction.PlatformTransactionManager;

public class CustomBatchConfigurer extends DefaultBatchConfigurer {

	@Override
	public PlatformTransactionManager getTransactionManager() {

		return new MyTransactionManager();
	}
}
