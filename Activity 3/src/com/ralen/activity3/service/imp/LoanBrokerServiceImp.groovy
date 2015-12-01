package com.ralen.activity3.service.imp;

import java.lang.annotation.Retention;

import org.springframework.beans.factory.annotation.Autowired

import com.ralen.activity3.algorithm.LoanBrokerAlgorithm;
import com.ralen.activity3.dao.CreditAgencyDAO
import com.ralen.activity3.dao.LenderAgencyDAO
import com.ralen.activity3.model.Customer
import com.ralen.activity3.service.LoanBrokerService

public class LoanBrokerServiceImp implements LoanBrokerService{

	@Autowired
	CreditAgencyDAO creditDAO;
	@Autowired
	LenderAgencyDAO lenderDAO;
	@Autowired
	List<LoanBrokerAlgorithm> algos;

	@Override
	public def loan(Customer c) {
		def ret = [:]
		int score = creditDAO.getCreditScore(c)
		c.creditScore = score
		def bank = lenderDAO.getBankQoute(c)
		c.total = (c.term * (c.loanAmount * (bank.rate / 100))) + c.loanAmount
		ret << ['bank': bank]
		ret << ['customer': c]
		boolean isAccepted = true
		algos.each{ 
			boolean a = it.isAccepted(c,bank)
			isAccepted &= a
			println "${it.class} : ${a}"
		}
		println "\n"
		ret << ['accepted': isAccepted]
		return ret
	}
}
