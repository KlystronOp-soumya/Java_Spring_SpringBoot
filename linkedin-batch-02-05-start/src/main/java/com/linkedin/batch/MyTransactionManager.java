package com.linkedin.batch;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class MyTransactionManager implements PlatformTransactionManager {

	@Override
	public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit(TransactionStatus status) throws TransactionException {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollback(TransactionStatus status) throws TransactionException {
		// TODO Auto-generated method stub

	}

}
