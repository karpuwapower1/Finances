package by.training.finance.controller.command.impl.user;

import java.util.HashMap;

import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.entity.User;
import by.training.finance.exception.service.InvalidLoginException;
import by.training.finance.exception.service.ServiceException;
import by.training.finance.factory.ServiceFactory;
import by.training.finance.service.UserService;

public class ShowUser implements Command {

	//request looks like
	//login=login
	
	@Override
	public String execute(String request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		String login = new String();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			User user = userService.findUser(login);
			return CommandUtils.getUserString(user);
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}
}
