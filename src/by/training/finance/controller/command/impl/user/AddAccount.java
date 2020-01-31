package by.training.finance.controller.command.impl.user;

import java.math.BigDecimal;
import java.util.HashMap;

import by.training.finance.beans.Account;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.service.UserService;
import by.training.finance.service.exception.IllegalAccountException;
import by.training.finance.service.exception.InvalidAccountException;
import by.training.finance.service.exception.InvalidLoginException;
import by.training.finance.service.exception.ServiceException;
import by.training.finance.service.factory.ServiceFactory;

public class AddAccount implements Command {
	
	/*
	 * request looks like login=login&accountName=name&balance=balance
	 */

	@Override
	public String execute(String request) {
		String login = new String();
		String accountName = new String();
		BigDecimal amount = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			accountName = parameters.get(ControllerConstants.ACCOUNT_NAME_PARAMETER);
			amount = new BigDecimal(parameters.get(ControllerConstants.ACCOUNT_AMOUNT_PARAMETER).replace(",", "."));
			Account account = new Account(amount, accountName);
			userService.addAccount(login, account);
			return ControllerMessages.SUCCESSFUL_OPERATION;
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (InvalidAccountException e) {
			return ControllerMessages.INVALID_ACCOUNT_MESSAGE;
		} catch (IllegalAccountException e) {
			return ControllerMessages.ACCOUNT_PRESENT_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}
}
