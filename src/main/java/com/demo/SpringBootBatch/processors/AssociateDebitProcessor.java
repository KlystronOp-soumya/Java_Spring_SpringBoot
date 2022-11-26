package com.demo.SpringBootBatch.processors;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.demo.SpringBootBatch.entities.AssociateDebit;
import com.demo.SpringBootBatch.entities.Associates;
import com.demo.SpringBootBatch.utility.Constants;

public class AssociateDebitProcessor implements ItemProcessor<Associates, AssociateDebit> {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(AssociateDebitProcessor.class);

	@Override
	public AssociateDebit process(Associates item) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Inside processor");
		AssociateDebit associateDebit = new AssociateDebit();
		associateDebit.setAssociateName(item.getAssociateName());
		associateDebit.setLeaves(item.getLeaves());
		associateDebit.setSerialNum(item.getSerialNum());
		associateDebit.setDebitAmt(new BigDecimal(0));
		this.debitForLeaveBalanceCalcualtor(associateDebit);

		LOGGER.info("Associate processing completed");
		return associateDebit;
	}

	private void debitForLeaveBalanceCalcualtor(AssociateDebit associateDebit) {
		LOGGER.info("Calcualtion leave balance");
		// check if the number of leaves is greater than 5
		// then deduct *100
		if (Integer.parseInt(associateDebit.getLeaves()) > Integer.parseInt(Constants.ALLOWED_LEAVES.getValue())) {
			associateDebit.setDebitAmt(new BigDecimal(Integer.parseInt(associateDebit.getLeaves()) * 100).negate());
			LOGGER.debug("Leaves exceeded: " + associateDebit.getSerialNum());
		}
	}

}
