package by.training.finance.service.impl;

import by.training.finance.dao.InitializatorDAO;
import by.training.finance.dao.exception.DAOException;
import by.training.finance.dao.exception.IllegalPasswordException;
import by.training.finance.dao.exception.NoSuchUserException;
import by.training.finance.dao.exception.UserAlreadyPresentException;
import by.training.finance.dao.factory.DAOFactory;
import by.training.finance.service.InitializatorService;
import by.training.finance.service.exception.InvalidLoginException;
import by.training.finance.service.exception.InvalidPasswordException;
import by.training.finance.service.exception.ServiceException;
import by.training.finance.service.exception.UserAlreadyPresentServiceException;
import by.training.finance.service.exception.WrongLoginOrPasswordException;
import by.training.finance.service.validator.Validator;

public class InitializatorServiceImpl implements InitializatorService {

	private static InitializatorServiceImpl initService;
	private DAOFactory factory = DAOFactory.getInstance();

	private InitializatorServiceImpl() {
	}

	public static InitializatorService getInitializatorService() {
		if (initService == null) {
			initService = new InitializatorServiceImpl();
		}
		return initService;
	}

	@Override
	public void register(String login, String password) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isPasswordValid(password)) {
			throw new InvalidPasswordException();
		}
		try {
			InitializatorDAO initDao = factory.getInitializatorDAO();
			initDao.register(login, password);
		} catch (UserAlreadyPresentException e) {
			throw new UserAlreadyPresentServiceException();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void signIn(String login, String password) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		if (!Validator.isPasswordValid(password)) {
			throw new InvalidPasswordException();
		}
		try {
			InitializatorDAO initDao = factory.getInitializatorDAO();
			initDao.signIn(login, password);
		} catch (NoSuchUserException | IllegalPasswordException e) {
			throw new WrongLoginOrPasswordException();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void signOut(String login) throws ServiceException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(String login) throws ServiceException {
		if (!Validator.isLoginValid(login)) {
			throw new InvalidLoginException();
		}
		try {
			InitializatorDAO initDao = factory.getInitializatorDAO();
			initDao.delete(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
