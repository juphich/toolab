package net.toolab.query.sql;

import java.io.Serializable;

import net.toolab.query.Predicate;
import net.toolab.query.QueryGenerator;
import net.toolab.query.QuerySetBuilder;

public class SqlQuerySetBuilder extends QuerySetBuilder {

	public SqlQuerySetBuilder(QueryGenerator queryGenerator) {
		super(queryGenerator);
	}
	
	public SqlQueryUnitBuilder where(Serializable key) {
		SqlQueryUnitBuilder builder = new SqlQueryUnitBuilder(key, this);
		addQueryBuilder(Predicate.where, builder);
		return builder;
	}
	
	public SqlQueryUnitBuilder and(Serializable key) {
		SqlQueryUnitBuilder builder = new SqlQueryUnitBuilder(key, this);
		addQueryBuilder(Predicate.and, builder);
		return builder;
	}
	
	public SqlQueryUnitBuilder or(Serializable key) {
		SqlQueryUnitBuilder builder = new SqlQueryUnitBuilder(key, this);
		addQueryBuilder(Predicate.or, builder);
		return builder;
	}
	
	public SqlQueryUnitBuilder with(Serializable key) {
		SqlQueryUnitBuilder builder = new SqlQueryUnitBuilder(key, this);
		addQueryBuilder(Predicate.empty, builder);
		return builder;
	}
	
	public SqlQuerySetBuilder and(QuerySetBuilder builder) {
		addQueryBuilder(Predicate.and, builder);
		
		return this;
	}
}
