package net.toolab.query;

import java.io.Serializable;
import java.util.Map;

public class QueryUnit implements Query {

	private Serializable key;
	private Object value;
	
	private Operand op;
	
	QueryUnit(Serializable key, Operand op, Object value) {
		this.key = key;
		this.op = op;
		this.value = value;
	}
	
	public Serializable key() {
		return key;
	}

	public Object value() {
		return value;
	}

	public Operand operand() {
		return op;
	}

	@Override
	public String query() {
		throw new UnsupportedOperationException("QueryUnit class does not support this method");
	}

	@Override
	public Map<Serializable, Object> parameters() {
		throw new UnsupportedOperationException("QueryUnit class does not support this method");
	}

	@Override
	public <P> P parameters(Class<P> type) {
		throw new UnsupportedOperationException("QueryUnit class does not support this method");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryUnit other = (QueryUnit) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
}
