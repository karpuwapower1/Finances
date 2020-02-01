package by.training.finance.factory;

import java.io.File;

import by.training.finance.dao.CounterDAO;
import by.training.finance.dao.InitializatorDAO;
import by.training.finance.dao.UserDAO;
import by.training.finance.dao.constants.DAOConstants;
import by.training.finance.dao.impl.FileCounterDAO;
import by.training.finance.dao.impl.FileInitializatorDAO;
import by.training.finance.dao.impl.FileUserDAO;
import by.training.finance.exception.dao.DAOException;

public final class DAOFactory {
	
	private DAOFactory() {}
	
	private static class InstanceHolder {
		private static final DAOFactory instance = new DAOFactory();
	}

	public static DAOFactory getInstance()  {
		return InstanceHolder.instance;
	}

	public CounterDAO getCounterBookDAO() throws DAOException {
		File counterBook = DAOConstants.COUNTERBOOK_FILE_NAME;
		return FileCounterDAO.getCounterDAO(counterBook);
	}

	public InitializatorDAO getInitializatorDAO() throws DAOException {
		File init = DAOConstants.INIT_FILE_NAME;
		return FileInitializatorDAO.getFileInitializatorDAO(init);
	}
	
	public UserDAO getUserDAO() throws DAOException {
		File accounts = DAOConstants.ACCOUNTS_FILE_NAME;
		File categories = DAOConstants.CATEGORIES_FILE_NAME;
		return FileUserDAO.getFileUserDAO(accounts, categories);
	}
}
