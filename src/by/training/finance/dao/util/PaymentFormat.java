package by.training.finance.dao.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import by.training.finance.beans.Category;
import by.training.finance.beans.Payment;
import by.training.finance.constants.Constants;
import by.training.finance.constants.PaymentType;
import by.training.finance.dao.constants.DAOConstants;
import by.training.finance.dao.exception.DAOException;

public class PaymentFormat {

	public static Payment parse(String str) throws DAOException {
		String[] parameter = str.split(DAOConstants.DELIMETER);
		BigDecimal amount = new BigDecimal(parameter[DAOConstants.COUNTER_BOOK_AMOUNT_INDEX].trim());
		PaymentType type = amount.compareTo(BigDecimal.ZERO) < 0 ? PaymentType.SPENDING : PaymentType.RECEIPT;
		amount = amount.compareTo(BigDecimal.ZERO) < 0 ? amount.negate() : amount;
		Category category = new Category (parameter[DAOConstants.COUNTER_BOOK_PAYMENT_CATEGORY_INDEX].trim(), type);
		String accountName = parameter[DAOConstants.COUNTER_BOOK_ACCOUNT_INDEX];
		Calendar date = new GregorianCalendar();
		try {
			date.setTime(Constants.DATE_FORMAT.parse(parameter[DAOConstants.COUNTER_BOOK_DATE_INDEX].trim()));
		} catch (ParseException e) {
			// can not be because of date was written by the same pattern;
		}
		return new Payment(amount, date, category, accountName);
	}

	public static String format(String login, Payment payment) {
		String date = Constants.DATE_FORMAT.format(payment.getDate().getTime());
		BigDecimal amount = payment.getAmount();
		if (payment.getCategory().getType() == PaymentType.SPENDING) {
			amount = amount.negate();
		}
		return String.format(Locale.US, DAOConstants.COUNTER_BOOK_PATTERN, login, payment.getAccountName(), date,
				amount.doubleValue(), payment.getCategory().getName());
	}
}
