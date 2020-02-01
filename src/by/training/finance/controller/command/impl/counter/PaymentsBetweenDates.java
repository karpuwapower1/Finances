package by.training.finance.controller.command.impl.counter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import by.training.finance.constants.Constants;
import by.training.finance.constants.PaymentType;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.entity.Payment;
import by.training.finance.exception.service.IllegalDateException;
import by.training.finance.exception.service.InvalidLoginException;
import by.training.finance.exception.service.InvalidPaymentTypeException;
import by.training.finance.exception.service.ServiceException;
import by.training.finance.factory.ServiceFactory;
import by.training.finance.service.CounterService;

public class PaymentsBetweenDates implements Command {

	/*
	 * request looks like 
	 * login=login&type=type&datefrom=date&dateto=date
	 */
	
	@Override
	public String execute(String request) {
		String login = new String();
		PaymentType type;
		Calendar from = new GregorianCalendar();
		Calendar to = new GregorianCalendar();
		ServiceFactory factory = ServiceFactory.getInstance();
		CounterService service = factory.getCounterService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			type = PaymentType.valueOf(parameters.get(ControllerConstants.TYPE_PARAMETER).toUpperCase());
			from.setTime(Constants.DATE_FORMAT.parse(parameters.get(ControllerConstants.DATE_FROM_PARAMETER)));
			to.setTime(Constants.DATE_FORMAT.parse(parameters.get(ControllerConstants.DATE_TO_PARAMETER)));
			List<Payment> payments = service.getPaymentsBetweenDates(login, type, from, to);
			return CommandUtils.getPaymentString(payments);
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (InvalidPaymentTypeException | IllegalArgumentException e) {
			return ControllerMessages.INVALID_PAYMENT_TYPE_MESSAGE;
		}catch (IllegalDateException e) {
			return ControllerMessages.ILLEGAL_INTERVAL_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		} catch (ParseException e) {
			return ControllerMessages.INVALID_DATE_MESSAGE;
		} 
	}
}
