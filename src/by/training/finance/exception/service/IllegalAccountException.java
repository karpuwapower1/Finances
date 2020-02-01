package by.training.finance.exception.service;

public class IllegalAccountException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public IllegalAccountException() {
		super();
	}

	public IllegalAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalAccountException(String message) {
		super(message);
	}

	public IllegalAccountException(Throwable cause) {
		super(cause);
	}

}
