package com.ralen.activity3.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.ralen.activity3.dao.CreditAgencyDAO;
import com.ralen.activity3.model.Customer;
import wslite.soap.*

public class CreditAgencyDAOImp implements CreditAgencyDAO{

	final def client
	final def soapAction
	
	@Autowired
	CreditAgencyDAOImp(String server){
		client = new SOAPClient(server)
	}
			
	@Override
	public int getCreditScore(Customer customer) {
		def response = client.send(SOAPAction:'urn:getCreditScoring'){
			body{
				getCreditScoring(xmlns:"http://impl.service.creditagency.toro.com"){
					customerName(customer.name)
					ssn(customer.ssn)
					loan_amount(customer.loanAmount)
				}
			}
		}
		return Integer.parseInt(response.getCreditScoringResponse.return.creditScoring.toString())
	}
}
