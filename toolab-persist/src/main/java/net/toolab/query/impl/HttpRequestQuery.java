package net.toolab.query.impl;

import java.util.Collection;

import net.toolab.query.QueryUnit;

public class HttpRequestQuery extends AbstractQuery {

	public HttpRequestQuery(Collection<QueryUnit> queries) {
		super(queries, true);
	}

	@Override
	public String queryString() {
		return null;
	}
}
