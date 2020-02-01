package by.training.finance.entity;

import java.io.Serializable;

import by.training.finance.constants.PaymentType;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private PaymentType type;

	public Category() {
	}

	public Category(String name, PaymentType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return getClass().getName() + "name=" + name + ", type=" + type;
	}

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = name == null ? 0 : name.hashCode();
		result = prime * result + (type == null ? 0 : type.ordinal());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
