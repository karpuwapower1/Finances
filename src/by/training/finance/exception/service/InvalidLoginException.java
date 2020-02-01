package by.training.finance.exception.service;

public class InvalidLoginException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidLoginException() {
		super();
	}

	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidLoginException(String message) {
		super(message);
	}

	public InvalidLoginException(Throwable cause) {
		super(cause);
	}

}
