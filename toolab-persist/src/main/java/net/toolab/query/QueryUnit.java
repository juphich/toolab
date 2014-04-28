package net.toolab.query;

import java.io.Serializable;

public class QueryUnit implements Comparable<QueryUnit> {

	private Serializable key;
	private Object value;
	
	private Predicate predicate;
	private Operand op;
	
	public QueryUnit(Predicate predicate, Serializable key, Operand op, Object value) {
		this.predicate = predicate;
		this.key = key;
		this.op = op;
		this.value = value;
	}
	
	public Predicate predicate() {
		return predicate;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
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
	public int compareTo(QueryUnit o) {
		return this.predicate.ordinal() - o.predicate.ordinal();
	}
}
