package by.training.finance.controller.command.impl.user;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import by.training.finance.beans.Category;
import by.training.finance.beans.Payment;
import by.training.finance.constants.Constants;
import by.training.finance.constants.PaymentType;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.controller.constants.ControllerMessages;
import by.training.finance.service.UserService;
import by.training.finance.service.exception.InvalidLoginException;
import by.training.finance.service.exception.InvalidPaymentException;
import by.training.finance.service.exception.ServiceException;
import by.training.finance.service.factory.ServiceFactory;

public class ReceiptMoney implements Command {

	public String execute(String request) {
		String login;
		String name;
		BigDecimal amount;
		Calendar date;
		String accountName;
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		try {
			HashMap<String, String> parameters = CommandUtils.getRequestParameters(request);
			login = parameters.get(ControllerConstants.LOGIN_PARAMETER);
			name = parameters.get(ControllerConstants.CATEGORY_NAME_PARAMETER);
			Category category = new Category(name, PaymentType.RECEIPT);
			accountName = parameters.get(ControllerConstants.ACCOUNT_NAME_PARAMETER);
			amount = new BigDecimal(parameters.get(ControllerConstants.ACCOUNT_AMOUNT_PARAMETER).replace(",", "."));
			String d = parameters.get(ControllerConstants.DATE_PARAMETER);
			date = new GregorianCalendar();
			date.setTime(Constants.DATE_FORMAT.parse(d));
			Payment payment = new Payment(amount, date, category, accountName);
			service.makePayment(login, payment);
			return ControllerMessages.SUCCESSFUL_OPERATION;
		} catch (NumberFormatException e) {
			return ControllerMessages.INVALID_AMOUNT_MESSAGE;
		} catch (ParseException e) {
			return ControllerMessages.INVALID_DATE_MESSAGE;
		} catch (InvalidPaymentException e) {
			return ControllerMessages.INVALID_PAYMENT_MESSAGE;
		} catch (InvalidLoginException e) {
			return ControllerMessages.INVALID_LOGIN_MESSAGE;
		} catch (ServiceException e) {
			return ControllerMessages.INTERNAL_ERROR_MESSAGE;
		}
	}
}
