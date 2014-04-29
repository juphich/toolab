package net.toolab.query.http;

import java.util.Collection;

import net.toolab.query.QueryUnit;
import net.toolab.query.builder.QueryBuilder;

public class HttpQueryBuilder extends QueryBuilder<HttpRequestQuery> {

	@Override
	protected HttpRequestQuery newInstance(Collection<QueryUnit> units) {
		return new HttpRequestQuery(units);
	}
}
