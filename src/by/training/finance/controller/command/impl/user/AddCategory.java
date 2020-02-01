package by.training.finance.controller.command.impl.user;

import java.util.HashMap;

import by.training.finance.constants.PaymentType;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.entity.Category;
import by.training.finance.exception.service.InvalidCategoryException;
import by.training.finance.exception.service.InvalidLoginException;
import by.training.finance.exception.service.ServiceException;
import by.training.finance.factory.ServiceFactory;
import by.training.finance.service.UserService;

public class AddCategory implements Command {

	/*
	 * request looks like login=login&categoryName=name&PaymentType=type
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
			if (service.addPaymentCategory(login, category)) {
				return ControllerMessages.SUCCESSFUL_OPERATION;
			}
			return ControllerMessages.CATEGORY_PRESENT_MESSAGE;
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (InvalidCategoryException | IllegalArgumentException e) {
			return ControllerMessages.INVALID_CATEGORY_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}

}
