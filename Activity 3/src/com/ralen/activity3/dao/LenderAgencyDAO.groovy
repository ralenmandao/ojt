package com.ralen.activity3.dao
import wslite.soap.*

import com.ralen.activity3.model.BankQoute
import com.ralen.activity3.model.Customer


public interface LenderAgencyDAO {
	public BankQoute getBankQoute(Customer c);
}
