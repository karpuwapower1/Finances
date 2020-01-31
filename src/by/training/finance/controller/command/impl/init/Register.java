package by.training.finance.controller.command.impl.init;

import java.util.HashMap;

import by.training.finance.beans.User;
import by.training.finance.controller.Controller;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.service.InitializatorService;
import by.training.finance.service.UserService;
import by.training.finance.service.exception.InvalidLoginException;
import by.training.finance.service.exception.InvalidPasswordException;
import by.training.finance.service.exception.ServiceException;
import by.training.finance.service.exception.UserAlreadyPresentServiceException;
import by.training.finance.service.factory.ServiceFactory;

public class Register implements Command {
	
	/*
	 * request looks like login=login&password=password
	 */

	@Override
	public String execute(String request) {
		String login;
		String password;
		ServiceFactory factory = ServiceFactory.getInstance();
		InitializatorService service =factory.getInitializatorService();
		UserService userService = factory.getUserService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			password = parameters.get(ControllerConstants.PASSWORD_PARAMETER);
			service.register(login, password);
			User user = userService.createNewUser(login);
			Controller.setLogin(login);
			userService.saveUser(user);
			return CommandUtils.getUserString(user);
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (InvalidPasswordException e) {
			return ControllerMessages.INVALID_PASSWORD_MESSAGE;
		} catch (UserAlreadyPresentServiceException e) {
			return ControllerMessages.LOGIN_PRESENT_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}
}
