package by.training.finance.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import by.training.finance.service.exception.IllegalAccountException;

/*
 * 	This class represents user's account.
 */

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_NAME = "New account";

	private BigDecimal balance;
	private String name;

	public Account() {
		balance = BigDecimal.ZERO;
		name = DEFAULT_NAME;
	}

	public Account(BigDecimal balance, String name) {
		this.balance = balance;
		this.name = name;
	}

	public Account(String balance, String name) {
		this.balance = new BigDecimal(balance);
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) throws IllegalAccountException {
		this.balance = balance;
	}
	
	public String getCartName() {
		return name;
	}
	
	public void setCartName(String name) {
		this.name = name;
	}
	
	public void reduceBalance(BigDecimal amount) {
		balance = balance.subtract(amount);
	}
	
	public void riseBalance(BigDecimal amount) {
		balance = balance.add(amount);
	}
	
	@Override
	public String toString() {
		return getClass().getName() 
				+ ", balance=" + balance.doubleValue()
				+ ", name=" + name;
	}

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = balance == null ? 0 : balance.hashCode();
		result = result * prime + (name == null ? 0 : name.length());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (balance.compareTo(other.balance) != 0)
			return false;
		if (name == null) {
			if (other.name != null) 
				return false;
			} else if (!name.contentEquals(other.name))
				return false;
		return true;
	}
}