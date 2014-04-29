package net.toolab.query.builder;

import net.toolab.query.Query;
import net.toolab.query.http.HttpQueryBuilder;
import net.toolab.query.sql.SqlQueryBuilder;

public class QueryFactory {

	public static Query query(QueryBuilder<?> queryBuilder) {
		return queryBuilder.build();
	}
	
	public static QueryBuilder<?> ofSql() {
		return new SqlQueryBuilder();
	}
	
	public static QueryBuilder<?> http() {
		return new HttpQueryBuilder();
	}
}
