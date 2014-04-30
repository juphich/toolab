package net.toolab.query;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import static net.toolab.query.sql.Sql.*;

import net.toolab.query.sql.Sql;

import org.junit.Test;

public class BuilderFactoryTest {
	
	@Test
	public void testSqlQueryFactory() {
		Query query = Sql.query(
				where("pk").is(1234)
				.and(with("fk").le(30).or("fk").gt(40))
			);
		
		System.out.println(query.query());
		System.out.println(query.parameters());
		
		assertThat(query.query(), is("where pk = 1234 and (fk <= 30 or fk > 40)"));
	}
}
