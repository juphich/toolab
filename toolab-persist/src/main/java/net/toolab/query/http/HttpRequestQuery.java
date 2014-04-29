package net.toolab.query.http;

import java.util.Collection;

import net.toolab.query.AbstractQuery;
import net.toolab.query.QueryUnit;

class HttpRequestQuery extends AbstractQuery {

	HttpRequestQuery(Collection<QueryUnit> queries) {
		super(queries, true);
	}

	@Override
	public String queryString() {
		return null;
	}
}
