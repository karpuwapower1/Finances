package by.training.finance.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import by.training.finance.dao.InitializatorDAO;
import by.training.finance.dao.constants.DAOConstants;
import by.training.finance.dao.util.InitializatorFormat;
import by.training.finance.exception.dao.DAOException;
import by.training.finance.exception.dao.IllegalPasswordException;
import by.training.finance.exception.dao.NoSuchUserException;
import by.training.finance.exception.dao.UserAlreadyPresentException;

public class FileInitializatorDAO implements InitializatorDAO {

	private static FileInitializatorDAO fileUserDAO;
	private final RandomAccessFile file;

	private FileInitializatorDAO(File file) throws DAOException {
		try {
			this.file = new RandomAccessFile(file, "rw");
		} catch (IOException e) {
			throw new DAOException("IO while initializing a file", e);
		}
	}

	public static InitializatorDAO getFileInitializatorDAO(File file) throws DAOException {
		if (fileUserDAO == null) {
			fileUserDAO = new FileInitializatorDAO(file);
		}
		return fileUserDAO;
	}

	/*
	 * Registers new user. If login already exists throws an exception and suggest
	 * to signIn. Creates new User and writes it's parameters into a file otherwise.
	 */
	@Override
	public void register(String login, String password) throws DAOException {
		try {
			findPosition(login);
			throw new UserAlreadyPresentException();
		} catch (NoSuchUserException e) {
			// do nothing because user with this login doesn't exist.
			// If login already presents in file, user must change login
			// or register.
		}
		try {
			String writedString = InitializatorFormat.format(login, password);
			file.seek(file.length());
			file.writeUTF(writedString);
		} catch (IOException e) {
			closeFile(e);
			throw new DAOException(e);
		}
	}

	/*
	 * Tries to find the user in the file. If no user found throws
	 * NoSuchUserException. After that checks password, and if
	 * password is equals to param password, return true. Throws 
	 * IllegalPasswordException otherwise
	 */
	@Override
	public void signIn(String login, String password) throws DAOException {
		long position = findPosition(login);
		try {
			file.seek(position);
			String str = file.readUTF();
			if (str.split(DAOConstants.DELIMETER)[DAOConstants.INIT_FILE_PASSWORD_INDEX].trim().equals(password)) {
				return;
			}
			throw new IllegalPasswordException();
		} catch (IOException e) {
			closeFile(e);
			throw new DAOException(e);
		}
	}

	@Override
	public void signOut(String login) throws DAOException {
		throw new UnsupportedOperationException();

	}

	/*
	 * Removes the user from the file;
	 */
	@Override
	public void delete(String login) throws DAOException {
		try {
			file.seek(0);
			long writedPosition = 0;
			long readedPosition = 0;
			while (file.length() > file.getFilePointer()) {
				String str = file.readUTF();
				if (!str.split(DAOConstants.DELIMETER)[DAOConstants.INIT_FILE_LOGIN_INDEX].trim().equals(login)) {
					readedPosition = file.getFilePointer();
					file.seek(writedPosition);
					file.writeUTF(str);
					writedPosition = file.getFilePointer();
					file.seek(readedPosition);
				}
			}
			file.setLength(writedPosition);
		} catch (IOException e) {
			closeFile(e);
		}
	}
	
	/*
	 * Searches the specified login position in the file
	 */

	private long findPosition(String login) throws DAOException {
		try {
			file.seek(0);
			while (file.getFilePointer() < file.length()) {
				long position = file.getFilePointer();
				String str = file.readUTF();
				if (str.split(DAOConstants.DELIMETER)[DAOConstants.INIT_FILE_LOGIN_INDEX].trim().equals(login)) {
					return position;
				}
			}
			throw new NoSuchUserException();
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}

	/*
	 * Closes the file when an exception occurs. Makes fileUserDAO equals null
	 * despite of closing result.
	 */
	private void closeFile(Exception e) throws DAOException {
		if (file != null) {
			try {
				file.close();
			} catch (IOException e1) {
			} finally {
				fileUserDAO = null;
				throw new DAOException(e);
			}
		}
	}
}
