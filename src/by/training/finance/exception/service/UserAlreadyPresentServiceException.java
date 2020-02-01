package by.training.finance.exception.service;

public class UserAlreadyPresentServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyPresentServiceException() {
		super();
	}

	public UserAlreadyPresentServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyPresentServiceException(String message) {
		super(message);
	}

	public UserAlreadyPresentServiceException(Throwable cause) {
		super(cause);
	}

}
