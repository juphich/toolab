package net.toolab.query;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.toolab.query.exception.QueryCompositionException;
import net.toolab.utils.ReflectionUtils;

public class QuerySet implements Query {

	private List<QueryEntry> queries;
	
	private boolean allowDuplicate = false;
	
	private QueryGenerator queryGenerator;
	
	public QuerySet() { }
	
	QuerySet(boolean duplicate) {
		this.allowDuplicate = duplicate;
	}
	
	public void isAllowDuplicate(boolean allow) {
		this.allowDuplicate = allow;
	}
	
	public void setQueryGenerator(QueryGenerator queryGenerator) {
		this.queryGenerator = queryGenerator;
	}

	public void addQuery(Predicate predicate, Query query) {
		if (queries == null) {
			queries = new ArrayList<QueryEntry>();
		}
		
		if (!allowDuplicate && queries.contains(query)) {
			return;
		}
		
		if (query instanceof QuerySet) {
			((QuerySet) query).setQueryGenerator(queryGenerator);
		}
		
		queries.add(new QueryEntry(predicate, query));
	}
	
	@Override
	public String query() {
		StringBuilder query = new StringBuilder();
		
		Collections.sort(queries);
		
		for (QueryEntry entry : queries) {
			query.append(queryGenerator.makeQuery(entry.predicate, entry.query))
				 .append(" ");
		}
		
		return query.toString().trim();
	}

	@Override
	public Map<Serializable, Object> parameters() {
		Map<Serializable, Object> params = new HashMap<Serializable, Object>();
		
		for (QueryEntry entry : queries) {
			if (entry.query instanceof QueryUnit) {
				QueryUnit q = (QueryUnit) entry.query;
				params.put(q.key(), q.value());
			} else {
				params.putAll(entry.query.parameters());
			}
		}
		
		return params;
	}

	@Override
	public <P> P parameters(Class<P> type) {
		try {
			Constructor<P> constructor = type.getDeclaredConstructor();
			
			constructor.setAccessible(true);
			P target = constructor.newInstance();
			constructor.setAccessible(false);
			
			Map<Serializable, Object> params = parameters();
			for (Entry<Serializable, Object> entry : params.entrySet()) {
				String field = String.valueOf(entry.getKey());
				ReflectionUtils.bindPropertyValue(target, field, entry.getValue());
			}
			
			return target;
		} catch (NoSuchMethodException e) {
			throw new QueryCompositionException("It's necessary default constructor to create keymap object..", e);
		} catch (SecurityException e) {
			throw new QueryCompositionException("Could not access to constructor of target class.", e);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new QueryCompositionException("Could not bind to keymap object field.", e);
		}
	}
	
	private class QueryEntry implements Comparable<QueryEntry>{
		Predicate predicate;
		Query query;
		
		QueryEntry(Predicate predicate, Query query) {
			this.predicate = predicate;
			this.query = query;
		}

		@Override
		public int compareTo(QueryEntry o) {
			return this.predicate.ordinal() - o.predicate.ordinal();
		}
	}
}
