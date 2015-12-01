package com.ralen.activity3.algorithm.imp

import org.springframework.aop.TrueClassFilter;

import com.ralen.activity3.algorithm.LoanBrokerAlgorithm
import com.ralen.activity3.model.BankQoute;
import com.ralen.activity3.model.Customer;

class PrudentBrokerAlgorithm implements LoanBrokerAlgorithm{
	long minimum
	
	@Override
	public boolean isAccepted(Customer c, BankQoute b) {
		if(c.loanAmount >= 100000){
			return c.creditScore > 1000 ? true : false
		}else if(c.loanAmount >= 50000){
			return c.creditScore > 950 ? true : false
		}else if(c.loanAmount >= 45000){
			return c.creditScore > 850 ? true : false
		}else if(c.loanAmount >= 35000){
			return c.creditScore > 800 ? true : false
		}else if(c.loanAmount >= 25000){
			return c.creditScore > 750 ? true : false
		}else if(c.loanAmount >= 10000){
			return c.creditScore > 700 ? true : false
		}else if(c.loanAmount >= minimum){
			return c.creditScore > 650 ? true : false
		}
		return false;
	}
	
}
