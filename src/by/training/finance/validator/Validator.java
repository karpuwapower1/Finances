package by.training.finance.validator;

import java.math.BigDecimal;

import by.training.finance.constants.PaymentType;
import by.training.finance.entity.Account;
import by.training.finance.entity.Category;
import by.training.finance.entity.Payment;

public class Validator {
	
	public static boolean isLoginValid(String login) {
		return (login != null && !login.isEmpty());
	}
	
	public static boolean isPasswordValid(String password) {
		return (password != null && !password.isEmpty());
	}
	
	public static boolean isAccountValid(Account account) {
		if (account == null || !isAccountNameValid(account.getCartName()) 
				|| account.getBalance() == null || account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
			return false;
		}
		return true;
	}
	
	public static boolean isAccountNameValid(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isCategoryValid(Category category) {
		if (category == null || category.getName() == null || category.getName().isEmpty() 
				|| category.getType() == null) {
			return false;
		}
		return true;
	}
	
	public static boolean isPaumentValid(Payment payment) {
		if (payment == null || !isAccountNameValid(payment.getAccountName()) ||
				!isCategoryValid(payment.getCategory())) {
			return false;
		}
		return true;
	}
	
	public static boolean isPaymentTypeValid(PaymentType type) {
		return type != null;
	}
}
