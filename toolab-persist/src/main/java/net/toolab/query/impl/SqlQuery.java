package net.toolab.query.impl;

import java.util.PriorityQueue;

import net.toolab.query.Query;
import net.toolab.query.QueryUnit;

public class SqlQuery implements Query {

	private PriorityQueue<QueryUnit> units = new PriorityQueue<>();
	
	public void addUnit(QueryUnit unit) {
		if (!units.contains(unit)) {
			units.add(unit);
		}
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
}
