package com.cg.dao;

import com.cg.beans.Account;
import com.cg.exception.AccountException;


public interface AccountDao {
	public String createAccount(Account account) throws AccountException;
	public double showBalance(String mobileNo) throws AccountException;
	Account deposit(String mobileNo,double balance) throws AccountException;
	Account withdraw(String mobileNo,double balance) throws AccountException;
    Account printTransactionDetails(String mobileNo) throws AccountException;
   public boolean fundTransfer(String sourceMobileNo,String destMobileNo,double transferAmount) throws AccountException;
}
