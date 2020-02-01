package by.training.finance.controller.command.impl.counter;

import java.util.HashMap;
import java.util.List;

import by.training.finance.constants.PaymentType;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.entity.Payment;
import by.training.finance.exception.service.InvalidLoginException;
import by.training.finance.exception.service.InvalidPaymentTypeException;
import by.training.finance.exception.service.ServiceException;
import by.training.finance.factory.ServiceFactory;
import by.training.finance.service.CounterService;

public class AllPayments implements Command {
/*
 * request login=login&type=paymentType 
 */
	@Override
	public String execute(String request) {
		PaymentType type = null;
		String login = new String();
		ServiceFactory factory = ServiceFactory.getInstance();
		CounterService service = factory.getCounterService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			type = PaymentType.valueOf(parameters.get(ControllerConstants.TYPE_PARAMETER).toUpperCase());
			List<Payment> payments = service.getAllPaymentsByType(login, type);
			return CommandUtils.getPaymentString(payments);
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (InvalidPaymentTypeException | IllegalArgumentException e) {
			return ControllerMessages.INVALID_PAYMENT_TYPE_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}
}
