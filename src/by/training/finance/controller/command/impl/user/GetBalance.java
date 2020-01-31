package by.training.finance.controller.command.impl.user;

import java.math.BigDecimal;
import java.util.HashMap;

import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.service.UserService;
import by.training.finance.service.exception.InvalidLoginException;
import by.training.finance.service.exception.ServiceException;
import by.training.finance.service.factory.ServiceFactory;

public class GetBalance implements Command {

	/*
	 * request looks like login=login
	 */

	@Override
	public String execute(String request) {
		String login = new String();
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			BigDecimal amount = service.getFullBalance(login);
			return String.valueOf(amount.doubleValue());
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}
}
