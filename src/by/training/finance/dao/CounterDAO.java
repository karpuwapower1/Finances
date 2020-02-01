package by.training.finance.dao;

import java.util.List;

import by.training.finance.entity.Payment;
import by.training.finance.exception.dao.DAOException;

public interface CounterDAO {

	void writePayment(String login, Payment payment) throws DAOException;

	List<Payment> getAllPayments(String login) throws DAOException;

	void delete(String login) throws DAOException;
}
