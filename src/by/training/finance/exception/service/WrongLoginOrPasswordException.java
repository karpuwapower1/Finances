package by.training.finance.exception.service;

public class WrongLoginOrPasswordException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public WrongLoginOrPasswordException() {
		super();
	}

	public WrongLoginOrPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongLoginOrPasswordException(String message) {
		super(message);
	}

	public WrongLoginOrPasswordException(Throwable cause) {
		super(cause);
	}

}
