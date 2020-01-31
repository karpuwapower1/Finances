package by.training.finance.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import by.training.finance.beans.Payment;
import by.training.finance.dao.CounterDAO;
import by.training.finance.dao.constants.DAOConstants;
import by.training.finance.dao.exception.DAOException;
import by.training.finance.dao.util.PaymentFormat;

public class FileCounterDAO implements CounterDAO {

	private final RandomAccessFile file;
	private static FileCounterDAO instance;
	private static int deletingLineQuantity;

	private FileCounterDAO(File file) throws DAOException {
		try {
			this.file = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			throw new DAOException(e);
		}
	}

	public static CounterDAO getCounterBookDAO(File file) throws DAOException {
		if (instance == null) {
			instance = new FileCounterDAO(file);
		}
		return instance;
	}

	@Override
	public void writePayment(String login, Payment payment) throws DAOException {
		String str = PaymentFormat.format(login, payment);
		try {
			file.seek(file.length());
			file.writeUTF(str);
		} catch (IOException e) {
			closeFile(e);
		}
	}

	@Override
	public List<Payment> getAllPayments(String login) throws DAOException {
		List<Payment> payments = new ArrayList<>();
		try {
			file.seek(0);
			while (file.getFilePointer() < file.length()) {
				String str = file.readUTF();
				if (str.split(DAOConstants.DELIMETER)[DAOConstants.COUNTER_BOOK_LOGIN_INDEX].trim().equals(login)) {
					payments.add(PaymentFormat.parse(str));
				}
			}
		} catch (IOException e) {
			closeFile(e);
		}
		return payments;
	}

	/*
	 * Marks lines with specified login and if marks count is greater than
	 * dilitingLineQuantity calls refresh method
	 */
	@Override
	public void delete(String login) throws DAOException {
		try {
			file.seek(0);
			while (file.getFilePointer() < file.length()) {
				long position = file.getFilePointer();
				String str = file.readUTF();
				if (str.split(DAOConstants.DELIMETER)[DAOConstants.COUNTER_BOOK_LOGIN_INDEX].trim().equals(login)) {
					str = DAOConstants.COUNTER_BOOK_DELETED_LINE_MARKER
							+ str.substring(DAOConstants.COUNTER_BOOK_DELETED_LINE_MARKER.length());
					file.seek(position);
					file.writeUTF(str);
					deletingLineQuantity += 1;
				}
			}
			if (deletingLineQuantity > DAOConstants.COUNTER_BOOK_REFRESH_SIGN) {
				refresh();
			}
		} catch (IOException e) {
			closeFile(e);
		}
	}

	/*
	 * removes all lines with specified mark
	 */
	private void refresh() throws DAOException {
		long readPosition = 0;
		long writedPosition = 0;
		try {
			file.seek(0);
			while (readPosition < file.length()) {
				String str = file.readUTF();
				readPosition = file.getFilePointer();
				if (!str.split(DAOConstants.DELIMETER)[DAOConstants.COUNTER_BOOK_LOGIN_INDEX]
						.equals(DAOConstants.COUNTER_BOOK_DELETED_LINE_MARKER)) {
					file.seek(writedPosition);
					file.writeUTF(str);
					writedPosition = file.getFilePointer();
					file.seek(readPosition);
				}
			}
			file.setLength(writedPosition);
			deletingLineQuantity = 0;
		} catch (IOException e) {
			closeFile(e);
		}
	}

	private void closeFile(Exception e) throws DAOException {
		if (file != null) {
			try {
				file.close();
			} catch (IOException e1) {
			} finally {
				instance = null;
				throw new DAOException(e);
			}
		}
	}
}
