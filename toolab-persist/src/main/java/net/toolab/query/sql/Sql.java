package net.toolab.query.sql;

import java.io.Serializable;

import net.toolab.query.Query;
import net.toolab.query.QueryBuilder;

public class Sql {

	public static Query query(QueryBuilder builder) {
		return builder.build();
	}
	
	public static SqlQueryUnitBuilder where(Serializable key) {
		SqlQuerySetBuilder builder = new SqlQuerySetBuilder(new SqlQueryGenerator());
		
		return builder.where(key);
	}

	public static SqlQueryUnitBuilder with(String key) {
		SqlQuerySetBuilder builder = new SqlQuerySetBuilder(null);
		return builder.with(key);
	}
}
