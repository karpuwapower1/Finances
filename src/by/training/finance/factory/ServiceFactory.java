package by.training.finance.factory;

import by.training.finance.service.CounterService;
import by.training.finance.service.InitializatorService;
import by.training.finance.service.UserService;
import by.training.finance.service.impl.CounterServiceImpl;
import by.training.finance.service.impl.InitializatorServiceImpl;
import by.training.finance.service.impl.UserServiceImpl;

public class ServiceFactory {	
	
	private final CounterService counterService = new CounterServiceImpl();
	private final InitializatorService initService = new InitializatorServiceImpl();
	private final UserService userService = new UserServiceImpl();
	
	private ServiceFactory() {}
	
	private static class InstanceHolder {
		private static ServiceFactory instance = new ServiceFactory();
	}
	
	public static ServiceFactory getInstance() {
		return InstanceHolder.instance;
	}
	
	public CounterService getCounterService() {
		return counterService;
	}
	
	public InitializatorService getInitializatorService() {
		return initService;
	}
	
	public UserService getUserService() {
		return userService;
	}
}
