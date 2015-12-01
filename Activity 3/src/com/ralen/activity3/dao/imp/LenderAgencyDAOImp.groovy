package com.ralen.activity3.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;
import wslite.soap.*

import com.ralen.activity3.dao.LenderAgencyDAO
import com.ralen.activity3.model.BankQoute;
import com.ralen.activity3.model.Customer;;

public class LenderAgencyDAOImp implements LenderAgencyDAO{
	final def client
	
	@Autowired
	LenderAgencyDAOImp(String server){
		client = new SOAPClient(server)
	}
	
	@Override
	public BankQoute getBankQoute(Customer c) {
		final def response = client.send(SOAPAction:'urn:getBankQuote'){
			body{
				getBankQuote(xmlns:"http://impl.service.lenderagency.toro.com"){
					creditScoring(c.creditScore)
					loanAmount(c.loanAmount)
					term(c.term)
				}
			}
		}
		def res = response.getBankQuoteResponse.return
		return new BankQoute(bankName: res.bankName, rate: res.rate.toString() as double)
	}
}
