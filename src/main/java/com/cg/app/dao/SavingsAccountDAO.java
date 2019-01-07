package com.cg.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;
	double checkCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	SavingsAccount deleteAccount(int accountNumber) throws SQLException, ClassNotFoundException ;
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	//void commit() throws SQLException;
	SavingsAccount updateAccount(SavingsAccount savingAccount) throws SQLException,ClassNotFoundException, AccountNotFoundException;
	
	SavingsAccount getAccountByName(String accountHolderName) throws AccountNotFoundException, ClassNotFoundException, SQLException;
	List<SavingsAccount> getAllAccountsBetweenSalaryRange(double mininumRange, double maximumRange) throws SQLException, ClassNotFoundException;
	
	List<SavingsAccount> sortBy(int choice) throws SQLException,
			ClassNotFoundException, AccountNotFoundException;
	
}
