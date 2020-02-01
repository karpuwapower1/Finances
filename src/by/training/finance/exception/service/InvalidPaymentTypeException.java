package by.training.finance.exception.service;

public class InvalidPaymentTypeException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidPaymentTypeException() {
		super();
	}

	public InvalidPaymentTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPaymentTypeException(String message) {
		super(message);
	}

	public InvalidPaymentTypeException(Throwable cause) {
		super(cause);
	}
}
