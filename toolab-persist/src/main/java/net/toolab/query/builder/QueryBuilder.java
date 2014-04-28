package net.toolab.query.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.toolab.query.Predicate;
import net.toolab.query.Query;
import net.toolab.query.impl.SqlQuery;

public class QueryBuilder {

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
		
	public QueryBuilder or() {
		return this;
	}
	
	public Query build() {
		SqlQuery query = new SqlQuery();
		
		for (QueryUnitBuilder ub : queries) {
			query.addUnit(ub.build());
		}
		
		return query;
	}
}