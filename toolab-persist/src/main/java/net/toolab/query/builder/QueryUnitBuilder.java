package net.toolab.query.builder;

import java.io.Serializable;

import net.toolab.query.Operand;
import net.toolab.query.Predicate;
import net.toolab.query.QueryUnit;

public class QueryUnitBuilder {

	private QueryBuilder<?> builder;
	
	private Predicate pre;
	private Serializable key;
	private Object value;
	private Operand op;

	public QueryUnitBuilder(Predicate pre, Serializable key, QueryBuilder<?> builder) {
		this.pre = pre;
		this.key = key;
		this.builder = builder;
	}
	
	public QueryBuilder<?> is(Object value) {
		this.op = Operand.is;
		return setter(value);
	}
	
	public QueryBuilder<?> not(Object value) {
		this.op = Operand.not;
		return setter(value);
	}
	
	public QueryBuilder<?> gt(Object value) {
		this.op = Operand.gt;
		return setter(value);
	}
	
	public QueryBuilder<?> ge(Object value) {
		this.op = Operand.ge;
		return setter(value);
	}
	
	public QueryBuilder<?> lt(Object value) {
		this.op = Operand.lt;
		return setter(value);
	}
	
	public QueryBuilder<?> le(Object value) {
		this.op = Operand.le;
		return setter(value);
	}
	
	private QueryBuilder<?> setter(Object value) {
		this.value = value;
		return builder;
	}
	
	public QueryUnit build() {
		return new QueryUnit(pre, key, op, value);
	}
}
