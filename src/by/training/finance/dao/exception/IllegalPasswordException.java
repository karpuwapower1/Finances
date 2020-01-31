package by.training.finance.dao.exception;

public class IllegalPasswordException extends DAOException {

	private static final long serialVersionUID = 1L;

	public IllegalPasswordException() {
		super();
	}

	public IllegalPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalPasswordException(String message) {
		super(message);
	}

	public IllegalPasswordException(Throwable cause) {
		super(cause);
	}

}
