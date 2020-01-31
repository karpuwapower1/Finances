package by.training.finance.service.exception;

public class InvalidPasswordException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super();
	}

	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(Throwable cause) {
		super(cause);
	}

}
