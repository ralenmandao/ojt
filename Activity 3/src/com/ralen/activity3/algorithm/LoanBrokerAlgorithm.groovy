package com.ralen.activity3.algorithm

import com.ralen.activity3.model.BankQoute
import com.ralen.activity3.model.Customer

interface LoanBrokerAlgorithm {
	public boolean isAccepted(Customer c, BankQoute b);
}
