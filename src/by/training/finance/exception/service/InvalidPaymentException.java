package by.training.finance.exception.service;

public class InvalidPaymentException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidPaymentException() {
		super();
	}

	public InvalidPaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPaymentException(String message) {
		super(message);
	}

	public InvalidPaymentException(Throwable cause) {
		super(cause);
	}
}
