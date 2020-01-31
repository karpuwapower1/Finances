package by.training.finance.dao.exception;

public class NoSuchUserException extends DAOException {
	
	private static final long serialVersionUID = 1L;

	public NoSuchUserException() {
		super();
	}
	
	public NoSuchUserException(String message) {
		super(message);
	}
	
	public NoSuchUserException(Throwable cause) {
		super(cause);
	}
	
	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

}
