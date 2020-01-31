package by.training.finance.constants;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import by.training.finance.beans.Account;
import by.training.finance.beans.Category;

public class Constants {

	public final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final Category DEFAULT_CATEGORY = new Category("Another", PaymentType.SPENDING);
	public static final String DEFAULT_ACCOUNT_NAME = "New account";
	public static final Account DEFAULT_ACCOUNT = new Account(BigDecimal.ZERO, DEFAULT_ACCOUNT_NAME);
}
