package by.training.finance.exception.service;

public class InvalidCategoryException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidCategoryException() {
		super();
	}

	public InvalidCategoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCategoryException(String message) {
		super(message);
	}

	public InvalidCategoryException(Throwable cause) {
		super(cause);
	}

}
