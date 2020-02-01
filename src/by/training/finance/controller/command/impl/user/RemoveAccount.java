package by.training.finance.controller.command.impl.user;

import java.util.HashMap;

import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.exception.service.InvalidAccountException;
import by.training.finance.exception.service.InvalidLoginException;
import by.training.finance.exception.service.ServiceException;
import by.training.finance.factory.ServiceFactory;
import by.training.finance.service.UserService;

public class RemoveAccount implements Command {

	/*
	 * request looks like login=login&accountName=name
	 */

	@Override
	public String execute(String request) {
		String login = new String();
		String accountName = new String();
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			accountName = parameters.get(ControllerConstants.ACCOUNT_NAME_PARAMETER);
			if (service.removeAccount(login, accountName)) {
				return ControllerMessages.SUCCESSFUL_OPERATION;
			}
			return ControllerMessages.INVALID_ACCOUNT_MESSAGE;
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (InvalidAccountException e) {
			return ControllerMessages.INVALID_ACCOUNT_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}
}
