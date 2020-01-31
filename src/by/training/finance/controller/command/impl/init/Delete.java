package by.training.finance.controller.command.impl.init;

import java.util.HashMap;

import by.training.finance.controller.Controller;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.service.CounterService;
import by.training.finance.service.InitializatorService;
import by.training.finance.service.UserService;
import by.training.finance.service.exception.InvalidLoginException;
import by.training.finance.service.exception.ServiceException;
import by.training.finance.service.factory.ServiceFactory;

public class Delete implements Command {
	
	// request looks like login=login

	@Override
	public String execute(String request) {
		String login = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		InitializatorService service =factory.getInitializatorService();
		CounterService counterService = factory.getCounterService();
		UserService userService = factory.getUserService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			service.delete(login);
			counterService.delete(login);
			userService.delete(login);
			Controller.setLogin(new String());
			return ControllerMessages.SUCCESSFUL_OPERATION;
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}
}
