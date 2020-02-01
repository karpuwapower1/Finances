package by.training.finance.service;

import by.training.finance.exception.service.ServiceException;

public interface InitializatorService {
	
	void register(String login, String password) throws ServiceException;
	void signIn(String login, String password) throws ServiceException;
	void signOut(String login) throws ServiceException;
	void delete(String login) throws ServiceException;

}
