package com.cg.service;

import java.time.LocalDateTime;

import com.cg.beans.Account;
import com.cg.dao.AccountDao;
import com.cg.dao.AccountDaoImpl;
import com.cg.exception.AccountException;

public class AccountServiceImpl implements AccountService {
AccountDao dao=new AccountDaoImpl();
	
	@Override
	public String createAccount(Account account) throws AccountException {
	if(!account.getMobile_no().matches("\\d{10}")){
		throw new AccountException("Mobile number should contain 10 digits");
	}
	if(account.getCustomer_name().isEmpty()||account.getCustomer_name()==null){
		throw new AccountException("Name cannot be empty");
	} else{
		if(!account.getCustomer_name().matches("[A-Z][A-Za-z]{3,}")){
			throw new AccountException("Name should start with Capital letter and should contain only alphabets");
		}
	}
	if(!account.getEmail_id().matches("[a-z0-9]+@[a-z]+\\.com")){
		throw new AccountException("Enter a valid EmailID");
	}
	if(account.getBalance()<=0){
		throw new AccountException("Balance should be greater than zero");
	}
		return dao.createAccount(account);
	}

	@Override
	public double showBalance(String mobileNo) throws AccountException {
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile number should contain 10 digits");
		}
		return dao.showBalance(mobileNo);
	}

	@Override
	public Account deposit(String mobileNo, double depositAmount)
			throws AccountException {
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile number should contain 10 digits");
		}
		Account account=dao.deposit(mobileNo, depositAmount);
		if(depositAmount<=0){
				throw new AccountException("deposit amount must be greater than zero");
		}
		//account.setBalance(account.getBalance()+depositAmount);
		//account.setDate(LocalDateTime.now());
		return account;
	}

	@Override
	public Account withdraw(String mobileNo, double withdrawAmount)
			throws AccountException {
		Account account=dao.withdraw(mobileNo, withdrawAmount);
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile number should contain 10 digits");
		}
		else if(withdrawAmount<=0){
			throw new AccountException("Amount should be greater than zero");	
		}
		else if(withdrawAmount>account.getBalance()){
			throw new AccountException("Balance should be greater than the withdraw amount");
		}	
		else{
			return account;
			}
		}
	
	

	@Override
	public Account printTransactionDetails(String mobileNo)
			throws AccountException {
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile number should contain 10 digits");
		}
		else
		{
		return dao.printTransactionDetails(mobileNo);
	}}

	@Override
	public boolean fundTransfer(String sourceMobileNo, String destMobileNo, double transferAmount) throws AccountException {
		if(!sourceMobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile number should contain 10 digits");
		}
			if(!destMobileNo.matches("\\d{10}")){
				throw new AccountException("Mobile number should contain 10 digits");
			}
		AccountService service =new AccountServiceImpl();
		service.withdraw(sourceMobileNo, transferAmount);
		service.deposit(destMobileNo, transferAmount);
		
		return true;
	}


}