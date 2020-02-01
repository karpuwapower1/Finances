package by.training.finance.dao;

import java.util.Set;

import by.training.finance.entity.Account;
import by.training.finance.entity.Category;
import by.training.finance.exception.dao.DAOException;

public interface UserDAO {

	Set<Category> findCategories(String login) throws DAOException;

	Set<Account> findAccounts(String login) throws DAOException;

	void writeCategories(String login, Set<Category> categories) throws DAOException;

	void writeAccounts(String login, Set<Account> accounts) throws DAOException;

	void deleteCategories(String login) throws DAOException;

	void deleteAccounts(String login) throws DAOException;
}
