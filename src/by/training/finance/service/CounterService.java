package by.training.finance.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import by.training.finance.constants.PaymentType;
import by.training.finance.entity.Payment;
import by.training.finance.exception.service.ServiceException;

public interface CounterService {

	List<Payment> getAllPaymentsByType(String login, PaymentType type) throws ServiceException;

	BigDecimal getFullPaymentSum(String login, PaymentType type) throws ServiceException;

	List<Payment> getPaymentsBetweenDates(String login, PaymentType type, Calendar from, Calendar to)
			throws ServiceException;
	
	void delete(String login) throws ServiceException;
}
