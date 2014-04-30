package net.toolab.query.sql;

import java.io.Serializable;

import net.toolab.query.Operand;
import net.toolab.query.QueryUnitBuilder;

public class SqlQueryUnitBuilder extends QueryUnitBuilder {

	private SqlQuerySetBuilder builder;
	
	public SqlQueryUnitBuilder(Serializable key, SqlQuerySetBuilder builder) {
		super(key);
		this.builder = builder;
	}

	public SqlQuerySetBuilder is(Object value) {
		setter(Operand.is, value);
		return builder;
	}
	
	public SqlQuerySetBuilder not(Object value) {
		setter(Operand.not, value);
		return builder;
	}
	
	public SqlQuerySetBuilder gt(Object value) {
		setter(Operand.gt, value);
		return builder;
	}
	
	public SqlQuerySetBuilder ge(Object value) {
		setter(Operand.ge, value);
		return builder;
	}
	
	public SqlQuerySetBuilder lt(Object value) {
		setter(Operand.lt, value);
		return builder;
	}
	
	public SqlQuerySetBuilder le(Object value) {
		setter(Operand.le, value);
		return builder;
	}
}
