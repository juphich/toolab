package net.toolab.query.sql;

import java.util.Collection;
import java.util.PriorityQueue;

import net.toolab.query.AbstractQuery;
import net.toolab.query.QueryUnit;

class SqlQuery extends AbstractQuery {

	SqlQuery(Collection<QueryUnit> queries) {
		super(queries);
	}
	
	@Override
	public String queryString() {
		PriorityQueue<QueryUnit> units = new PriorityQueue<>(queries());
		
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
}
