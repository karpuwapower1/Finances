package by.training.finance.controller.command.impl.user;

import java.util.HashMap;

import by.training.finance.beans.Category;
import by.training.finance.constants.PaymentType;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.service.UserService;
import by.training.finance.service.exception.InvalidCategoryException;
import by.training.finance.service.exception.InvalidLoginException;
import by.training.finance.service.exception.ServiceException;
import by.training.finance.service.factory.ServiceFactory;

public class RemoveCategory implements Command {
	
	/*
	 * request looks like 
	 * login=login&categoryname=name&categoryType=type
	 */

	@Override
	public String execute(String request) {
		String login = new String();
		String name = new String();
		PaymentType type = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			name = parameters.get(ControllerConstants.CATEGORY_NAME_PARAMETER);			
			type = PaymentType.valueOf(parameters.get(ControllerConstants.TYPE_PARAMETER).toUpperCase());
			Category category = new Category(name, type);
			if (service.removePaymentCategory(login, category)) {
				return ControllerMessages.SUCCESSFUL_OPERATION;
			}
			return ControllerMessages.INVALID_CATEGORY_MESSAGE;
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (InvalidCategoryException | IllegalArgumentException e) {
			return ControllerMessages.INVALID_CATEGORY_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}

}
