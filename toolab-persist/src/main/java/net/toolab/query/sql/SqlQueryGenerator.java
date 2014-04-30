package net.toolab.query.sql;

import net.toolab.query.Predicate;
import net.toolab.query.Query;
import net.toolab.query.QueryGenerator;
import net.toolab.query.QueryUnit;

public class SqlQueryGenerator implements QueryGenerator {

	@Override
	public String makeQuery(Predicate predicate, Query query) {
		String queryString = Predicate.empty == predicate ? "" : predicate.name();
		
		if (query instanceof QueryUnit) {
			QueryUnit unit = (QueryUnit) query;
			queryString += " " + unit.key() + " " + unit.operand().getSymbol() + " " +unit.value();
		} else {
			queryString += " (" + query.query() + ")";
		}
		
		return queryString.trim();
	}
}
