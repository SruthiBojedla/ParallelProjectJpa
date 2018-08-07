package com.cg.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.beans.Account;
import com.cg.exception.AccountException;
import com.cg.service.AccountService;
import com.cg.service.AccountServiceImpl;

public class AccountTest { 

	private AccountService service= new AccountServiceImpl();
	
	@Test
	public void testCreateAccountForMobile() {
	Account ac = new Account();
	ac.setMobile_no("9999");
	ac.setCustomer_name("Ramesh");
	ac.setEmail_id("ramesh@gmail.com");
	ac.setBalance(200.0);
	try {
	service.createAccount(ac);
	} catch (AccountException e) {
	assertEquals("Mobile number should contain 10 digits", e.getMessage());
	}
	}
	 
	@Test
	public void testCreateAccountForName() {
	Account ac = new Account();
	ac.setMobile_no("9999999999");
	ac.setCustomer_name("ravi123");
	ac.setEmail_id("ravi@gmail.com");
	ac.setBalance(500.0);
	try {
	service.createAccount(ac);
	} catch (AccountException e) {
	assertEquals("Name should start with Capital letter and should contain only alphabets", e.getMessage());
	}
	}
	 
	@Test
	public void testCreateAccountForNameIsEmpty() {
	Account ac = new Account();
	ac.setMobile_no("9999999999");
	ac.setCustomer_name("");
	ac.setEmail_id("deepak@gmail.com");
	ac.setBalance(200.0);
	try {
	service.createAccount(ac);
	} catch (AccountException e) {
	assertEquals("Name cannot be empty", e.getMessage());
	}
	}	 
	@Test
	public void testCreateAccountForEmailId() {
	Account ac = new Account();
	ac.setMobile_no("9999999999");
	ac.setCustomer_name("Ramesh");
	ac.setEmail_id("ramesh@@23gmail.com");
	ac.setBalance(200.0);
	try {
	service.createAccount(ac);
	} catch (AccountException e) {
	assertEquals("Enter a valid EmailID", e.getMessage());
	}
	}
/*	@Test
	public void testCreateAccount() {
	Account ac = new Account();
	ac.setMobile_no("7777777777");
	ac.setCustomer_name("Mahesh");
	ac.setEmail_id("mahesh@gmail.com");
	ac.setBalance(2000.0);
	 
	try {
	String s=service.createAccount(ac);
	assertNotNull(s);
	} catch (AccountException e) { 
	}	 
	}*/
	 
	@Test
	public void testShowBalanceForMobileNo() {
	try {
	service.showBalance("95059");
	} catch (AccountException e) {
	assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}
	
	@Test
	public void testDepositForMobileNo() {
	Account ac=new Account();
	ac.setMobile_no("95059345");
	try {
	service.deposit(ac.getMobile_no(), 230);
	} catch (AccountException e) {
	assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}
	@Test
	public void testDepositForDepositAmt1() {
	Account ac=new Account();
	ac.setMobile_no("9999999999");
	try {
	service.deposit(ac.getMobile_no(), -230);
	} catch (AccountException e) {
	assertEquals("deposit amount must be greater than zero",e.getMessage());
	}
	}
	
	@Test
	public void testDepositForDepositAmt1IsZero() {
	Account ac=new Account();
	ac.setMobile_no("9999999999");
	try {
	service.deposit(ac.getMobile_no(), 0);
	} catch (AccountException e) {
	assertEquals("deposit amount must be greater than zero",e.getMessage());
	}
	}
	
	@Test
	public void testWithdrawForAmt() {
	Account ac=new Account();
	ac.setMobile_no("9999999999");
	try {
	service.withdraw(ac.getMobile_no(), -230);
	} catch (AccountException e) {
	assertEquals("Amount should be greater than zero",e.getMessage());
	}
	}
	
	@Test
	public void testWithdrawForAmtIsZero() {
	Account ac=new Account();
	ac.setMobile_no("9999999999");
	try {
	service.withdraw(ac.getMobile_no(),0);
	} catch (AccountException e) {
	assertEquals("Amount should be greater than zero",e.getMessage());
	}
	}
	 
	@Test
	public void testFundTransferForMobileNo() {
	Account ac=new Account();
	Account ac2=new Account();
	ac.setMobile_no("95059345");
	ac2.setMobile_no("1234");
	try {
	service.fundTransfer(ac.getMobile_no(),ac2.getMobile_no(), 230);
	} catch (AccountException e) {
	assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}

	@Test
	public void testFundTransferForAmt() {
	Account ac=new Account();
	Account ac2=new Account();
	ac.setMobile_no("9999999999");
	ac2.setMobile_no("7777777777");
	try {
	service.fundTransfer(ac.getMobile_no(), ac2.getMobile_no(),  -230);
	} catch (AccountException e) {
	// TODO Auto-generated catch block
	assertEquals("Amount should be greater than zero",e.getMessage());
	}
	}
	@Test
	public void testFundTransfer() {
	Account ac=new Account();
	Account ac2=new Account();
	ac.setMobile_no("7777777777");
	ac2.setMobile_no("9999999999");
	try {
	assertTrue(service.fundTransfer(ac.getMobile_no(), ac2.getMobile_no(),  230));
	} catch (AccountException e) {
	}
	}
	@Test
	public void testPrinttransactionDetails() {
	Account ac=new Account();
	ac.setMobile_no("8888888888");
	try {
	Account acc=service.printTransactionDetails(ac.getMobile_no());
	assertNotNull(acc);
	} catch (AccountException e) {
	//System.out.println(e.getMessage());
	}
	}
	
	@Test
	public void testPrinttransactionDetailsForMobile() {
		Account ac=new Account();
		ac.setMobile_no("950593");
		try {
		Account acc=service.printTransactionDetails(ac.getMobile_no());
		} catch (AccountException e) {
		assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	
	/*@Test
	public void testPrinttransactionDetailsForMobileDoesNotExist() {
		Account ac=new Account();
		ac.setMobile_no("1234567890");
		try {
		Account acc=service.printTransactionDetails(ac.getMobile_no());
		} catch (AccountException e) {
		assertEquals("the mobile number is not there in the data base",e.getMessage());
		}
	}*/
	
	@Test
	public void testFundTransferForFirstMobile() {
	Account ac=new Account();
	Account ac2=new Account();
	ac.setMobile_no("950592");
	ac2.setMobile_no("9848468242");
	try {
		service.fundTransfer(ac.getMobile_no(),ac2.getMobile_no(),230);
	} catch (AccountException e) {
		assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}
	
	@Test
	public void testFundTransferForSecondMobile() {
	Account ac=new Account();
	Account ac2=new Account();
	ac.setMobile_no("9999999999");
	ac2.setMobile_no("9848");
	try {
		service.fundTransfer(ac.getMobile_no(),ac2.getMobile_no(),230);
	} catch (AccountException e) {
		assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}
}
