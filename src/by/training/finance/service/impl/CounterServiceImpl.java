package by.training.finance.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import by.training.finance.beans.Payment;
import by.training.finance.constants.PaymentType;
import by.training.finance.dao.CounterDAO;
import by.training.finance.dao.exception.DAOException;
import by.training.finance.dao.factory.DAOFactory;
import by.training.finance.service.CounterService;
import by.training.finance.service.exception.IllegalDateException;
import by.training.finance.service.exception.InvalidLoginException;
import by.training.finance.service.exception.InvalidPaymentTypeException;
import by.training.finance.service.exception.ServiceException;
import by.training.finance.service.validator.Validator;

public class CounterServiceImpl implements CounterService {

	private static CounterServiceImpl counterService;
	DAOFactory factory = DAOFactory.getInstance();

	public static CounterService getCounterService() {
		if (counterService == null) {
			counterService = new CounterServiceImpl();
		}
		return counterService;
	}

	@Override
	public List<Payment> getAllPaymentsByType(String login, PaymentType type) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isPaymentTypeValid(type)) {
			throw new InvalidPaymentTypeException();
		}
		try {
			CounterDAO counterBookDao = factory.getCounterBookDAO();
			List<Payment> payments = counterBookDao.getAllPayments(login);
			Iterator<Payment> paymentsIterator = payments.iterator();
			while (paymentsIterator.hasNext()) {
				if (paymentsIterator.next().getCategory().getType() != type) {
					paymentsIterator.remove();
				}
			}
			return payments.size() > 0 ? payments : Collections.emptyList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public BigDecimal getFullPaymentSum(String login, PaymentType type) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isPaymentTypeValid(type)) {
			throw new InvalidPaymentTypeException();
		}
		List<Payment> payments = getAllPaymentsByType(login, type);
		if (payments.isEmpty()) {
			return BigDecimal.ZERO;
		}
		BigDecimal sum = BigDecimal.ZERO;
		for (Payment payment : payments) {
			sum = sum.add(payment.getAmount());
		}
		return sum;
	}

	@Override
	public List<Payment> getPaymentsBetweenDates(String login, PaymentType type, Calendar from, Calendar to)
			throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isPaymentTypeValid(type)) {
			throw new InvalidPaymentTypeException();
		}
		if (from.compareTo(to) > 0) {
			throw new IllegalDateException();
		}
		try {
			CounterDAO counterBookDao = factory.getCounterBookDAO();
			List<Payment> payments = counterBookDao.getAllPayments(login);
			Iterator<Payment> paymentsIterator = payments.iterator();
			while (paymentsIterator.hasNext()) {
				Payment payment = paymentsIterator.next();
				if (payment.getCategory().getType() != type || payment.getDate().compareTo(from) < 0
						|| payment.getDate().compareTo(to) > 0) {
					paymentsIterator.remove();
				}
			}
			return payments.size() > 0 ? payments : Collections.emptyList();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(String login) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		try {
			CounterDAO counterBookDao = factory.getCounterBookDAO();
			counterBookDao.delete(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
