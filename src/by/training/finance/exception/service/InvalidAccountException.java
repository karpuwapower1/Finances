package by.training.finance.exception.service;

public class InvalidAccountException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidAccountException() {
		super();
	}

	public InvalidAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAccountException(String message) {
		super(message);
	}

	public InvalidAccountException(Throwable cause) {
		super(cause);
	}

}
