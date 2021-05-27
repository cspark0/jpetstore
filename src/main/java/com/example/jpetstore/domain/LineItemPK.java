package com.example.jpetstore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LineItemPK implements Serializable {
	private int orderId;
	private int lineNumber;

	public LineItemPK() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass())
			return false;
		LineItemPK other = (LineItemPK) obj;
		if (lineNumber != other.lineNumber) return false;
		if (orderId != other.orderId) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lineNumber;
		result = prime * result + orderId;
		return result;
	}
}
