package by.training.finance.service;

import java.math.BigDecimal;

import by.training.finance.beans.Account;
import by.training.finance.beans.Category;
import by.training.finance.beans.Payment;
import by.training.finance.beans.User;
import by.training.finance.service.exception.ServiceException;

public interface UserService {

	User createNewUser(String login) throws ServiceException;
	
	void saveUser(User user) throws ServiceException;
	
	User findUser(String login) throws ServiceException;

	void addAccount(String login, Account account) throws ServiceException;

	boolean removeAccount(String login, String name) throws ServiceException;

	boolean addPaymentCategory(String login, Category category) throws ServiceException;

	boolean removePaymentCategory(String login, Category category) throws ServiceException;

	BigDecimal getFullBalance(String login) throws ServiceException;

	boolean makePayment(String login, Payment payment) throws ServiceException;

	void delete(String login) throws ServiceException;
}
