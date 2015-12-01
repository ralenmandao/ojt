package com.ralen.activity3.algorithm.imp

import com.ralen.activity3.algorithm.LoanBrokerAlgorithm
import com.ralen.activity3.model.BankQoute;
import com.ralen.activity3.model.Customer;;

class MoneyMakerAlgorithm implements LoanBrokerAlgorithm{
	double commision
	long minimum
	
	@Override
	public boolean isAccepted(Customer c, BankQoute b) {
		double additional = ((b.bankName.split(' ')[2] as Double) * 3) / 10
		switch(b.bankName){
			case ~/Bronze \w+ \w+/:
			setCommission(2 , additional)
			break;
			case ~/Silver \w+ \w+/:
			setCommission(3 , additional)
			break;
			case ~/Gold \w+ \w+/:
			setCommission(4 , additional)
			break;
		}
		println "your commision % : ${commision}"
		println "total : ${c.total}"
		double income = (commision / 100) * c.loanAmount
		println "your income : ${income}"
		if(income < minimum) return false
		return true
	}
	
	private double setCommission(double max, double add){
		commision = (Math.random() * max) + add
	}
}
