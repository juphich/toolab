package net.toolab.query;

import java.io.Serializable;

public abstract class QueryUnitBuilder implements QueryBuilder {

	private Serializable key;
	private Object value;
	private Operand op;

	public QueryUnitBuilder(Serializable key) {
		this.key = key;
	}
	
	protected void setter(Operand op, Object value) {
		this.op = op;
		this.value = value;
	}
	
	public QueryUnit build() {
		return new QueryUnit(key, op, value);
	}
}
