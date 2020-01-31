package by.training.finance.dao.exception;

public class UserAlreadyPresentException extends DAOException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyPresentException() {
		super();
	}

	public UserAlreadyPresentException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyPresentException(String message) {
		super(message);
	}

	public UserAlreadyPresentException(Throwable cause) {
		super(cause);
	}

}
