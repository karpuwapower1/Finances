package by.training.finance.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import by.training.finance.constants.Constants;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private Set<Account> accounts = new HashSet<>();
	private Set<Category> categories = new HashSet<>();

	public User() {
		login = new String();
	}

	public User(String login) {
		this.login = login;
		accounts.add(Constants.DEFAULT_ACCOUNT);
		categories.add(Constants.DEFAULT_CATEGORY);
	}

	public User(String login, Set<Account> accounts, Set<Category> categories) {
		this.login = login;
		this.accounts = accounts;
		this.categories = categories;
	}

	public String getLogin() {
		return login;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "login=" + login + ", accounts=" + accounts.toString()
		+ ", categories=" + categories.toString();
	}

	@Override
	public int hashCode() {
		return login.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (login.equals(other.login))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		return true;
	}
}
