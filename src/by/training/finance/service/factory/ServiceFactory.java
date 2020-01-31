package by.training.finance.service.factory;

import by.training.finance.service.CounterService;
import by.training.finance.service.InitializatorService;
import by.training.finance.service.UserService;
import by.training.finance.service.impl.CounterServiceImpl;
import by.training.finance.service.impl.InitializatorServiceImpl;
import by.training.finance.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	
	private ServiceFactory() {}
	
	private static class InstanceHolder {
		private static ServiceFactory instance = new ServiceFactory();
	}
	
	public static ServiceFactory getInstance() {
		return InstanceHolder.instance;
	}
	
	public CounterService getCounterService() {
		return CounterServiceImpl.getCounterService();
	}
	
	public InitializatorService getInitializatorService() {
		return InitializatorServiceImpl.getInitializatorService();
	}
	
	public UserService getUserService() {
		return UserServiceImpl.getUserService();
	}
}
