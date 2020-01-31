package by.training.finance.dao.util;

import by.training.finance.dao.constants.DAOConstants;

public class InitializatorFormat {

	public static String format(String login, String password) {
		return String.format(DAOConstants.INIT_USER_STRING_PATTERN, login, password);
	}
}
