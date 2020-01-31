package by.training.finance.service.exception;

public class IllegalDateException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public IllegalDateException() {
		super();
	}

	public IllegalDateException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalDateException(String message) {
		super(message);
	}

	public IllegalDateException(Throwable cause) {
		super(cause);
	}

	
}
