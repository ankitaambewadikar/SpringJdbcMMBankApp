package com.cg.app.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.rowMapper.SavingsAccountMapper;

@Repository
@Primary
public class SavingsAccountSpringJdbcDaoImpl implements SavingsAccountDAO {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		System.out.println("injdbc dao");

		jdbctemplate.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",
				new Object[] { account.getBankAccount().getAccountNumber(),
						account.getBankAccount().getAccountHolderName(), account.getBankAccount().getAccountBalance(),
						account.isSalary(), null, "SA" });

		return account;

	}

	@Override
	public double checkCurrentBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {

		return jdbctemplate.queryForObject("SELECT account_balance FROM account WHERE account_Id=?",
				new Object[] { accountNumber }, Double.class);

	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {

		return jdbctemplate.queryForObject("SELECT * FROM account where account_id=?", new Object[] { accountNumber },
				new SavingsAccountMapper());
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws SQLException, ClassNotFoundException {
		
		jdbctemplate.update("Delete FROM account WHERE account_Id=?",new Object[] {accountNumber});
		return null;
	
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return jdbctemplate.query("SELECT * FROM ACCOUNT", new SavingsAccountMapper());
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		
		

	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount savingAccount)
			throws SQLException, ClassNotFoundException, AccountNotFoundException {
		jdbctemplate.update("UPDATE ACCOUNT SET account_hn=?,salary=? WHERE account_Id = ?",new Object[] {
				
				savingAccount.getBankAccount().getAccountHolderName(),
				savingAccount.isSalary(),
				savingAccount.getBankAccount().getAccountNumber()
				});
		return getAccountById(savingAccount.getBankAccount().getAccountNumber());
		}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName)
			throws AccountNotFoundException, ClassNotFoundException, SQLException {
		return jdbctemplate.queryForObject("SELECT * from account WHERE account_hn=?", new Object[] {accountHolderName},new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> getAllAccountsBetweenSalaryRange(double mininumRange, double maximumRange)
			throws SQLException, ClassNotFoundException {
		return jdbctemplate.query("SELECT * FROM ACCOUNT WHERE account_balance BETWEEN ? AND ?",new Object[] { mininumRange,maximumRange}, new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> sortBy(int choice)
			throws SQLException, ClassNotFoundException, AccountNotFoundException {
		
		return null;
	}

}
