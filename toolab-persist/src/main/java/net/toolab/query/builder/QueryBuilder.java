package net.toolab.query.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.toolab.query.Predicate;
import net.toolab.query.Query;
import net.toolab.query.QueryUnit;

public abstract class QueryBuilder<Q extends Query> {

	private List<QueryUnitBuilder> queries = new ArrayList<>();
	
	public QueryUnitBuilder where(Serializable key) {
		QueryUnitBuilder query = new QueryUnitBuilder(Predicate.where, key, this);
		queries.add(query);
		
		return query;
	}
	
	public QueryUnitBuilder and(Serializable key) {
		QueryUnitBuilder query = new QueryUnitBuilder(Predicate.and, key, this);
		queries.add(query);
		return query;
	}
	
	public QueryBuilder<Q> and(QueryBuilder<?> builder) {
		if (builder != this) {
			throw new RuntimeException("not equal");
		}
		
		return this;
	}
		
	public QueryBuilder<Q> or() {
		return this;
	}
	
	public Query build() {
		List<QueryUnit> units = new ArrayList<>();
		for (QueryUnitBuilder ub : queries) {
			units.add(ub.build());
		}
		
		return newInstance(units);
	}
	
	protected abstract Q newInstance(Collection<QueryUnit> units);
}
