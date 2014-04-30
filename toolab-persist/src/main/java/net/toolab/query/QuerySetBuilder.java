package net.toolab.query;

import java.util.ArrayList;
import java.util.List;

public abstract class QuerySetBuilder implements QueryBuilder {

	private List<Entry> queries = new ArrayList<Entry>();
	
	private QueryGenerator queryGenerator;
	
	public QuerySetBuilder(QueryGenerator queryGenerator) {
		this.queryGenerator = queryGenerator;
	}

	protected void addQueryBuilder(Predicate pre, QueryBuilder builder) {
		queries.add(new Entry(pre, builder));
	}
	
	public Query build() {
		QuerySet query = new QuerySet(false);
		query.setQueryGenerator(queryGenerator);
		
		for (Entry entry : queries) {
			query.addQuery(entry.predicate, entry.builder.build());
		}
		
		return query;
	}
	
	private class Entry {
		private Predicate predicate;
		private QueryBuilder builder;
		
		private Entry(Predicate pre, QueryBuilder builder) {
			this.predicate = pre;
			this.builder = builder;
		}
	}
}
