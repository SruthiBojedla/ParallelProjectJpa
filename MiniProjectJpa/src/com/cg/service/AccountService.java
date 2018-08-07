package com.cg.service;

import com.cg.beans.Account;
import com.cg.exception.AccountException;

public interface AccountService {

	public String createAccount(Account account) throws AccountException;
	public double showBalance(String mobileNo) throws AccountException;
	public Account deposit(String mobileNo,double depositAmount) throws AccountException;
	public Account withdraw(String mobileNo,double withdrawAmount) throws AccountException;
	public Account printTransactionDetails(String mobileNo) throws AccountException;
    public boolean fundTransfer(String sourceMobileNo,String destMobileNo,double transferAmount) throws AccountException;
}
