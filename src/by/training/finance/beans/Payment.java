package by.training.finance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import by.training.finance.constants.Constants;

public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal amount;
	private Calendar date;
	private Category category;
	private String accountName;

	public Payment() {
		amount = BigDecimal.ZERO;
		date = new GregorianCalendar();
		category = Constants.DEFAULT_CATEGORY;
		setAccountName(Constants.DEFAULT_ACCOUNT_NAME);
	}

	public Payment(BigDecimal amount, Calendar date, Category category, String accountName) {
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.accountName = accountName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return getClass().getName() + ", amount=" + amount.doubleValue() + ", date="
				+ Constants.DATE_FORMAT.format(date.getTime()) + ", category=" + category.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = prime + (amount == null ? 0 : amount.intValue());
		result = prime * result + ((date == null) ? 0 : date.get(Calendar.YEAR));
		result = prime * result + ((category == null) ? 0 : category.getName().length());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(this instanceof Payment))
			return false;
		Payment other = (Payment) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (amount.compareTo(other.amount) != 0)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}
}
