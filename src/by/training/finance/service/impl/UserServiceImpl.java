package by.training.finance.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import by.training.finance.constants.PaymentType;
import by.training.finance.dao.CounterDAO;
import by.training.finance.dao.UserDAO;
import by.training.finance.entity.Account;
import by.training.finance.entity.Category;
import by.training.finance.entity.Payment;
import by.training.finance.entity.User;
import by.training.finance.exception.dao.DAOException;
import by.training.finance.exception.service.IllegalAccountException;
import by.training.finance.exception.service.InvalidAccountException;
import by.training.finance.exception.service.InvalidCategoryException;
import by.training.finance.exception.service.InvalidLoginException;
import by.training.finance.exception.service.InvalidPaymentException;
import by.training.finance.exception.service.ServiceException;
import by.training.finance.factory.DAOFactory;
import by.training.finance.service.UserService;
import by.training.finance.validator.Validator;

public class UserServiceImpl implements UserService {

	private static UserService service;
	DAOFactory factory = DAOFactory.getInstance();

	private UserServiceImpl() {
	}

	public static UserService getUserService() {
		if (service == null) {
			service = new UserServiceImpl();
		}
		return service;
	}

	@Override
	public User createNewUser(String login) throws ServiceException {
		User user = new User(login);
		saveUser(user);
		return user;
	}

	@Override
	public User findUser(String login) throws ServiceException {
		try {
			UserDAO userDao = factory.getUserDAO();
			Set<Category> categories = userDao.findCategories(login);
			Set<Account> accounts = userDao.findAccounts(login);
			User user = new User(login, accounts, categories);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void saveUser(User user) throws ServiceException {
		try {
			UserDAO userDao = factory.getUserDAO();
			userDao.writeAccounts(user.getLogin(), user.getAccounts());			
			userDao.writeCategories(user.getLogin(), user.getCategories());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addAccount(String login, Account account) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isAccountValid(account)) {
			throw new InvalidAccountException();
		}
		try {
			UserDAO userDao = factory.getUserDAO();
			Set<Account> accounts = userDao.findAccounts(login);
			for (Account acc : accounts) {
				if (acc.getCartName().equals(account.getCartName())) {
					throw new IllegalAccountException();
				}
			}
			accounts.add(account);
			userDao.writeAccounts(login, accounts);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removeAccount(String login, String name) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isAccountNameValid(name)) {
			throw new InvalidAccountException();
		}
		try {
			UserDAO userDao = factory.getUserDAO();
			Set<Account> accounts = userDao.findAccounts(login);
			boolean isDeleted = false;
			for (Account account : accounts) {
				if (account.getCartName().equals(name)) {
					accounts.remove(account);
					isDeleted = true;
					break;
				}
			}
			userDao.writeAccounts(login, accounts);
			return isDeleted;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addPaymentCategory(String login, Category category) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isCategoryValid(category)) {
			throw new InvalidCategoryException();
		}
		try {
			UserDAO userDao = factory.getUserDAO();
			Set<Category> categories = userDao.findCategories(login);
			if (categories.contains(category)) {
				return false;
			}
			categories.add(category);
			userDao.writeCategories(login, categories);
			return true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removePaymentCategory(String login, Category category) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isCategoryValid(category)) {
			throw new InvalidCategoryException();
		}
		try {
			UserDAO userDao = factory.getUserDAO();
			Set<Category> categories = userDao.findCategories(login);	
			boolean isRemoved = categories.remove(category);
			if (isRemoved) {
				userDao.writeCategories(login, categories);
			}
			return isRemoved;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public BigDecimal getFullBalance(String login) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		try {
			UserDAO userDao = factory.getUserDAO();
			Set<Account> accounts = userDao.findAccounts(login);
			if (accounts.isEmpty()) {
				return BigDecimal.ZERO;
			}
			BigDecimal balance = BigDecimal.ZERO;
			for (Account account : accounts) {
				balance = balance.add(account.getBalance());
			}
			return balance;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean makePayment(String login, Payment payment) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isPaumentValid(payment)) {
			throw new InvalidPaymentException();
		}
		try {
			UserDAO userDao = factory.getUserDAO();
			Set<Account> accounts = userDao.findAccounts(login);
			boolean isAccountPresent = false;
			for (Account account : accounts) {
				if (account.getCartName().equals(payment.getAccountName())) {
					if (payment.getCategory().getType() == PaymentType.SPENDING) {
						account.reduceBalance(payment.getAmount());
					} else {
						account.riseBalance(payment.getAmount());
					}
					CounterDAO book = factory.getCounterBookDAO();					
					book.writePayment(login, payment);
					isAccountPresent = true;
					break;
				}
			}
			if (isAccountPresent) {
				userDao.writeAccounts(login, accounts);
			}
			return isAccountPresent;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(String login) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		try {
			UserDAO userDao = factory.getUserDAO();
			userDao.deleteAccounts(login);
			userDao.deleteCategories(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
