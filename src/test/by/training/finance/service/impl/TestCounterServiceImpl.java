package test.by.training.finance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

import by.training.finance.constants.Constants;
import by.training.finance.constants.PaymentType;
import by.training.finance.entity.Account;
import by.training.finance.entity.Category;
import by.training.finance.entity.Payment;
import by.training.finance.exception.service.ServiceException;

public class TestCounterServiceImpl {
	
	{
		Category spendingCategory = new Category("food", PaymentType.SPENDING);
		Category receiptCategory = new Category("salary", PaymentType.RECEIPT);
		Account account = new Account("0", "cart");
		ArrayList<Payment> payments = new ArrayList<>();
		payments.add(new Payment(new BigDecimal(100), new GregorianCalendar(2020, 1, 1), 
				spendingCategory, account.getCartName()));
		payments.add(new Payment(new BigDecimal(100), new GregorianCalendar(2020, 1, 1), 
				receiptCategory, account.getCartName()));
		payments.add(new Payment(new BigDecimal(100), new GregorianCalendar(2020, 1, 1), 
				spendingCategory, account.getCartName()));
		payments.add(new Payment(new BigDecimal(100), new GregorianCalendar(2020, 1, 1), 
				receiptCategory, account.getCartName()));
	}
	
	@Test
	public void testGetAllPaymentsByType() throws ServiceException {
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
}
	
	
//	@Test
//	public List<Payment> getAllPaymentsByType(String login, PaymentType type) throws ServiceException {
//		if (!Validator.isLoginValid(login)) {
//			throw new InvalidLoginException();
//		}
//		if (!Validator.isPaymentTypeValid(type)) {
//			throw new InvalidPaymentTypeException();
//		}
//		try {
//			CounterDAO counterBookDao = factory.getCounterBookDAO();
//			List<Payment> payments = counterBookDao.getAllPayments(login);
//			Iterator<Payment> paymentsIterator = payments.iterator();
//			while (paymentsIterator.hasNext()) {
//				if (paymentsIterator.next().getCategory().getType() != type) {
//					paymentsIterator.remove();
//				}
//			}
//			return payments.size() > 0 ? payments : Collections.emptyList();
//		} catch (DAOException e) {
//			throw new ServiceException(e);
//		}
//	}
//
//
//	@Override
//	public BigDecimal getFullPaymentSum(String login, PaymentType type) throws ServiceException {
//		if (!Validator.isLoginValid(login)) {
//			throw new InvalidLoginException();
//		}
//		if (!Validator.isPaymentTypeValid(type)) {
//			throw new InvalidPaymentTypeException();
//		}
//		List<Payment> payments = getAllPaymentsByType(login, type);
//		if (payments.isEmpty()) {
//			return BigDecimal.ZERO;
//		}
//		BigDecimal sum = BigDecimal.ZERO;
//		for (Payment payment : payments) {
//			sum = sum.add(payment.getAmount());
//		}
//		return sum;
//	}
//
//	@Override
//	public List<Payment> getPaymentsBetweenDates(String login, PaymentType type, Calendar from, Calendar to)
//			throws ServiceException {
//		if (!Validator.isLoginValid(login)) {
//			throw new InvalidLoginException();
//		}
//		if (!Validator.isPaymentTypeValid(type)) {
//			throw new InvalidPaymentTypeException();
//		}
//		if (from.compareTo(to) > 0) {
//			throw new IllegalDateException();
//		}
//		try {
//			CounterDAO counterBookDao = factory.getCounterBookDAO();
//			List<Payment> payments = counterBookDao.getAllPayments(login);
//			Iterator<Payment> paymentsIterator = payments.iterator();
//			while (paymentsIterator.hasNext()) {
//				Payment payment = paymentsIterator.next();
//				if (payment.getCategory().getType() != type || payment.getDate().compareTo(from) < 0
//						|| payment.getDate().compareTo(to) > 0) {
//					paymentsIterator.remove();
//				}
//			}
//			return payments.size() > 0 ? payments : Collections.emptyList();
//		} catch (DAOException e) {
//			throw new ServiceException(e.getMessage(), e);
//		}
//	}
//
//	@Override
//	public void delete(String login) throws ServiceException {
//		if (!Validator.isLoginValid(login)) {
//			throw new InvalidLoginException();
//		}
//		try {
//			CounterDAO counterBookDao = factory.getCounterBookDAO();
//			counterBookDao.delete(login);
//		} catch (DAOException e) {
//			throw new ServiceException(e);
//		}
//	}
//}
