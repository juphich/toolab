package net.toolab.query.sql;

import java.util.Collection;

import net.toolab.query.QueryUnit;
import net.toolab.query.builder.QueryBuilder;

public class SqlQueryBuilder extends QueryBuilder<SqlQuery> {

	@Override
	protected SqlQuery newInstance(Collection<QueryUnit> units) {
		return new SqlQuery(units);
	}

}
