package by.training.finance.dao.constants;

import java.io.File;

public class DAOConstants {

	public static final File INIT_FILE_NAME = new File("resources\\init.txt");
	public static final File CATEGORIES_FILE_NAME = new File("resources\\categories.txt");
	public static final File ACCOUNTS_FILE_NAME = new File("resources\\accounts.txt");
	public static final File COUNTERBOOK_FILE_NAME = new File("resources\\counterBook.txt");

	// user login:account from: date: amount: categoryName:account from
	public static final String COUNTER_BOOK_PATTERN = "%15s:%12s:%10s:%7.2f:%10s%n";
	public static final int COUNTER_BOOK_LOGIN_INDEX = 0;
	public static final int COUNTER_BOOK_ACCOUNT_INDEX = 1;
	public static final int COUNTER_BOOK_DATE_INDEX = 2;
	public static final int COUNTER_BOOK_AMOUNT_INDEX = 3;
	public static final int COUNTER_BOOK_PAYMENT_CATEGORY_INDEX = 4;

	public static final String COUNTER_BOOK_DEFAULT_CATEGORY_MARKER = String.format("%3d%n", 0);
	public static final String COUNTER_BOOK_LOGIN_PATTERN = "%15s";

	// user login:account from: account to: date: amount:
	public static final String ACCOUNT_TRANSFER_PATTERN = "%15s:%12s:%12s:%10s:%7.2f%n";
	public static final int COUNTER_BOOK_BETWEEN_ACCOUNT_USER_LOGIN_INDEX = 0;
	public static final int COUNTER_BOOK_BETWEEN_ACCOUNT_ACCOUNT_FROM_INDEX = 1;
	public static final int COUNTER_BOOK_BETWEEN_ACCOUNT_ACCOUNT_TO_INDEX = 2;
	public static final int COUNTER_BOOK_BETWEEN_ACCOUNT_DATE_INDEX = 3;
	public static final int COUNTER_BOOK_BETWEEN_ACCOUNT_AMOUNT_INDEX = 4;
	public static final int COUNTER_BOOK_REFRESH_SIGN = 100;
	public static final String COUNTER_BOOK_DELETED_LINE_MARKER = String.format("%15s", "d");
	public static final String DELIMETER = ":";

	// login:password
	public static final String INIT_USER_STRING_PATTERN = "%15s:%15s%n";
	public static final int INIT_FILE_LOGIN_INDEX = 0;
	public static final int INIT_FILE_PASSWORD_INDEX = 1;

	// :amount:account name
	public static final String ACCOUNT_PATTERN = ":%7.2f:%10s";
	// user login: every account written with account pattern
	public static final String ACCOUNT_FILE_PATTERN = "%15s%s%n";
	// :category name:category type
	public static final String PAYMENT_CATEGORY_PATTERN = ":%10s:%10s";
	// user id:sategories:categories
	public static final String PAYMENT_CATEGORY_FILE_PATTERN = "%15s%s%n";
	public static final int USER_DATA_LOGIN_INDEX = 0;

}
