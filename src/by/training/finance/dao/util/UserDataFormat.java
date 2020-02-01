package by.training.finance.dao.util;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import by.training.finance.constants.PaymentType;
import by.training.finance.dao.constants.DAOConstants;
import by.training.finance.entity.Account;
import by.training.finance.entity.Category;

public class UserDataFormat {

	/*
	 * makes String represents user's accounts line in file looks like : user
	 * login:balance:account name: balance:accountName
	 */
	public static String formAccounts(String login, Set<Account> accounts) {
		StringBuilder userAccounts = new StringBuilder();
		for (Account account : accounts) {
			userAccounts.append(String.format(Locale.US, DAOConstants.ACCOUNT_PATTERN,
					account.getBalance().doubleValue(), account.getCartName()));
		}
		return String.format(DAOConstants.ACCOUNT_FILE_PATTERN, login, userAccounts.toString());
	}

	/*
	 * Parses user's accounts
	 */
	public static Set<Account> parseAccount(String str) {
		String[] parameters = str.split(DAOConstants.DELIMETER);
		Set<Account> accounts = new HashSet<>((parameters.length - 1) / 2);
		for (int i = 1; i < parameters.length - 1; i = i + 2) {
			BigDecimal balance = new BigDecimal(parameters[i].trim());
			String name = parameters[i + 1].trim();
			accounts.add(new Account(balance, name));
		}
		return accounts;
	}

	/*
	 * data in file looks like : user login:
	 * categoryname:categorytype:categoryname:categorytype
	 */

	public static String formPaymentCategories(String login, Set<Category> categories) {
		return String.format(DAOConstants.PAYMENT_CATEGORY_FILE_PATTERN, login, form(categories));
	}

	public static Set<Category> parsePaymentCategories(String str) {
		String s = str.substring(str.indexOf(DAOConstants.DELIMETER) + 1);
		return parse(s);
	}

	private static Set<Category> parse(String str) {
		String[] parameters = str.split(DAOConstants.DELIMETER);
		Set<Category> categories = new HashSet<>(parameters.length);
		for (int i = 0; i < parameters.length; i = i + 2) {
			String name = parameters[i].trim();
			PaymentType type = PaymentType.valueOf(parameters[i + 1].trim());
			categories.add(new Category(name, type));
		}
		return categories;
	}

	/*
	 * makes line of categories looks like :
	 * :categoryname:categorytype:categoryname:categorytype
	 */
	private static String form(Set<Category> categories) {
		StringBuilder stringCategory = new StringBuilder();
		for (Category category : categories) {
			stringCategory.append(String.format(DAOConstants.PAYMENT_CATEGORY_PATTERN, category.getName(),
					category.getType().toString()));
		}
		return stringCategory.toString();
	}
}
