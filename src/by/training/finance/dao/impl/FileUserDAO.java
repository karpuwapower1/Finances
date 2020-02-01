package by.training.finance.dao.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Set;

import by.training.finance.dao.UserDAO;
import by.training.finance.dao.constants.DAOConstants;
import by.training.finance.dao.util.UserDataFormat;
import by.training.finance.entity.Account;
import by.training.finance.entity.Category;
import by.training.finance.exception.dao.DAOException;
import by.training.finance.exception.dao.NoSuchUserException;

public class FileUserDAO implements UserDAO {

	public static FileUserDAO instance;
	File categoriesFileName, accountsFileName;
	private RandomAccessFile accountsFile, categoriesFile;

	private FileUserDAO(File accountsFile, File categoriesFile) throws DAOException {
		try {
			categoriesFileName = categoriesFile;
			accountsFileName = accountsFile;
			this.categoriesFile = new RandomAccessFile(categoriesFile, "rw");
			this.accountsFile = new RandomAccessFile(accountsFile, "rw");
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}

	public static FileUserDAO getFileUserDAO(File accountsFile, File categoriesFile) throws DAOException {
		if (instance == null) {
			instance = new FileUserDAO(accountsFile, categoriesFile);
		}
		return instance;
	}

	/*
	 * Search the user into a file
	 */
	@Override
	public Set<Category> findCategories(String login) throws DAOException {
		try {
			long categoriesPosition = findPositionInFile(login, categoriesFile);
			categoriesFile.seek(categoriesPosition);
			String categoriesString = categoriesFile.readUTF();
			return UserDataFormat.parsePaymentCategories(categoriesString);
		} catch (IOException e) {
			closeFile(e);
		}
		throw new NoSuchUserException();
	}

	/*
	 * Search the user into a file
	 */
	@Override
	public Set<Account> findAccounts(String login) throws DAOException {
		try {
			long accountPosition = findPositionInFile(login, accountsFile);
			accountsFile.seek(accountPosition);
			String accountString = accountsFile.readUTF();
			Set<Account> accounts = UserDataFormat.parseAccount(accountString);
			return accounts;
		} catch (IOException e) {
			closeFile(e);
		}
		throw new NoSuchUserException();
	}

	/*
	 * Writes user parameters into a file
	 */
	@Override
	public void writeCategories(String login, Set<Category> categories) throws DAOException {
		String paymentCategoriesString = UserDataFormat.formPaymentCategories(login, categories);
		try {
			findPositionInFile(login, categoriesFile);
			deleteCategories(login);
		} catch (NoSuchUserException e) {
			// do nothing because user hasn't been written in file before
		}
		try {
			categoriesFile.seek(categoriesFile.length());
			categoriesFile.writeUTF(paymentCategoriesString);
		} catch (IOException e) {
			closeFile(e);
		}
	}

	/*
	 * Writes user parameters into a file
	 */
	@Override
	public void writeAccounts(String login, Set<Account> accounts) throws DAOException {
		String accountsString = UserDataFormat.formAccounts(login, accounts);
		try {
			findPositionInFile(login, accountsFile);
			deleteAccounts(login);
		} catch (NoSuchUserException e) {
			// do nothing because user hasn't been written in file before
		}
		try {
			accountsFile.seek(accountsFile.length());
			accountsFile.writeUTF(accountsString);
		} catch (IOException e) {
			closeFile(e);
		}
	}

	/*
	 * Tries to delete a user with this id from the users' data file. Rewrites this
	 * file into new temp file without deleted user. Than file deletes and temp file
	 * becomes users' data file.
	 */
	@Override
	public void deleteCategories(String login) throws DAOException {
		deleteFromFile(login, categoriesFile, categoriesFileName);
		try {
			categoriesFile = new RandomAccessFile(categoriesFileName, "rwd");
		} catch (FileNotFoundException e) {
			// can not be thrown
		}
	}

	@Override
	public void deleteAccounts(String login) throws DAOException {
		deleteFromFile(login, accountsFile, accountsFileName);
		try {
			accountsFile = new RandomAccessFile(accountsFileName, "rwd");
		} catch (FileNotFoundException e) {
			// can not be thrown
		}
	}

	private void deleteFromFile(String login, RandomAccessFile file, File fileName) throws DAOException {
		File tempFile = new File("temp.txt");
		try {
			DataOutputStream writer = new DataOutputStream(new FileOutputStream(tempFile));
			file.seek(0);
			while (file.getFilePointer() < file.length()) {
				String str = file.readUTF();
				if (!(login.equals(str.split(DAOConstants.DELIMETER)[DAOConstants.USER_DATA_LOGIN_INDEX].trim()))) {
					writer.writeUTF(str);
				}
			}
			file.close();
			writer.close();
			fileName.delete();
			tempFile.renameTo(fileName);
		} catch (IOException e) {
			closeFile(e);
		}
	}

	/*
	 * Searches user's position into a file by its id
	 */
	private long findPositionInFile(String login, RandomAccessFile file) throws DAOException {
		try {
			file.seek(0);
			while (file.length() > file.getFilePointer()) {
				long userPosition = file.getFilePointer();
				String str = file.readUTF();
				if (login.equals(str.split(DAOConstants.DELIMETER)[DAOConstants.USER_DATA_LOGIN_INDEX].trim())) {
					return userPosition;
				}
			}
		} catch (IOException e) {
			closeFile(e);
		}
		throw new NoSuchUserException();
	}

	/*
	 * Tries to close the file when an exception occurs. Instance becomes null after
	 * executing this method and next getFileUserDAO() call again create instance;
	 */
	private void closeFile(Exception e) throws DAOException {
		if (instance != null) {
			try {
				categoriesFile.close();
				accountsFile.close();
			} catch (IOException e1) {
			} finally {
				instance = null;
				throw new DAOException(e);
			}
		}
	}
}
