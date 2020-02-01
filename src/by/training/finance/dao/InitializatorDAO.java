package by.training.finance.dao;

import by.training.finance.exception.dao.DAOException;

public interface InitializatorDAO {

	void register(String login, String password) throws DAOException;

	void signIn(String login, String password) throws DAOException;

	void delete(String login) throws DAOException;

	void signOut(String login) throws DAOException;
}
