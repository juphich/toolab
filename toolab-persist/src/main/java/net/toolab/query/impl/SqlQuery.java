package net.toolab.query.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.PriorityQueue;

import net.toolab.query.QueryUnit;

public class SqlQuery extends AbstractQuery {

	private PriorityQueue<QueryUnit> units = new PriorityQueue<>();
	
	public SqlQuery(Collection<QueryUnit> queries) {
		super(queries);
	}
	
	@Override
	public String queryString() {
		StringBuilder query = new StringBuilder();
		
		while (!units.isEmpty()) {
			QueryUnit unit = units.poll();
			query.append(unit.predicate()).append(" ")
				.append(unit.key()).append(" ")
				.append(unit.operand().getSymbol()).append(" ")
				.append(unit.value()).append(" ");
		}
		
		return query.toString().trim();
	}

	@Override
	public Map<Serializable, Object> entry() {
		return null;
	}
}
