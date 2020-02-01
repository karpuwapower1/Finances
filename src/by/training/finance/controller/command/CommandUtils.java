package by.training.finance.controller.command;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.finance.constants.Constants;
import by.training.finance.constants.PaymentType;
import by.training.finance.entity.Account;
import by.training.finance.entity.Category;
import by.training.finance.entity.Payment;
import by.training.finance.entity.User;

public class CommandUtils {

	public static final String COMMAND_DELIMETER = "?";
	public static final String REQUEST_PARAMETER_DELIMETER = "&";
	public static final String REQUEST_PARAMETER_VALUE_DELIMETER = "=";
	public static final String REQUEST_PARAMETER_PATTERN = "([\\w]+)=([ \\w-]+)";
	public static final String NEW_LINE = "\n";
	public static final String WHITESPASE = " ";
	public static final String ACCOUNTS_TITLE = "Accounts:\n";
	public static final String SPENDING_CATEGORIES_TITLE = "Spending categories:\n";
	public static final String RECEIPT_CATEGORIES_TITLE = "Receipt categories:\n";
	public static final Pattern REQUEST_PATTERN = Pattern.compile(REQUEST_PARAMETER_PATTERN);

	public static String getCommandName(String request) {
		return request.substring(0, request.indexOf(COMMAND_DELIMETER));
	}
	
	public static String getRequestString(String request) {
		return request.substring(request.indexOf(COMMAND_DELIMETER));
	}

	public static HashMap<String, String> getRequestParameters(String request) {
		HashMap<String, String> parameters = new HashMap<>();
		Matcher matcher = REQUEST_PATTERN.matcher(request);
		while (matcher.find()) {
			parameters.put(matcher.group(1), matcher.group(2));
		}
		return parameters;
	}

	public static String getPaymentString(List<Payment> payments) {
		StringBuilder builder = new StringBuilder();
		for (Payment payment : payments) {
			builder.append(Constants.DATE_FORMAT.format(payment.getDate().getTime()));
			builder.append(WHITESPASE + payment.getAmount().doubleValue() + WHITESPASE);
			builder.append(payment.getCategory().getName() + NEW_LINE);
		}		
		return builder.toString();
	}

	public static String getUserString(User user) {
		StringBuilder builder = new StringBuilder();
		builder.append(user.getLogin() + NEW_LINE);
		builder.append(SPENDING_CATEGORIES_TITLE);
		for (Category category : user.getCategories()) {
			if (category.getType() == PaymentType.SPENDING) {
				builder.append(category.getName() + NEW_LINE);
			}
		}
		builder.append(RECEIPT_CATEGORIES_TITLE);
		for (Category category : user.getCategories()) {
			if (category.getType() == PaymentType.RECEIPT) {
				builder.append(category.getName() + NEW_LINE);
			}
		}
		builder.append(ACCOUNTS_TITLE);
		for (Account account : user.getAccounts()) {
			builder.append(account.getCartName() + WHITESPASE + account.getBalance().doubleValue() + NEW_LINE);
		}
		return builder.toString();
	}
}
