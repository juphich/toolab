package net.toolab.query;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import java.io.Serializable;
import java.util.Map;

import net.toolab.query.sql.SqlQueryGenerator;

import org.junit.Test;

public class QueryTest {

	@Test
	public void testSimpleQuery() {
		QuerySet query = new QuerySet(false);
		query.setQueryGenerator(new SqlQueryGenerator());
		
		query.addQuery(Predicate.where, new QueryUnit("name", Operand.is, "elsa"));
		query.addQuery(Predicate.and, new QueryUnit("gender", Operand.is, "female"));
		query.addQuery(Predicate.or, new QueryUnit("age", Operand.le, 30));
		
		
		assertNotNull(query.query());
		assertThat(query.query(), is("where name = elsa and gender = female or age <= 30"));
	}
	
	@Test
	public void testQuerySet() {
		QuerySet query = new QuerySet(false);
		query.setQueryGenerator(new SqlQueryGenerator());
		
		query.addQuery(Predicate.where, new QueryUnit("name", Operand.is, "elsa"));
		
		QuerySet subset = new QuerySet(false);
		subset.addQuery(Predicate.empty, new QueryUnit("gender", Operand.is, "female"));
		subset.addQuery(Predicate.or, new QueryUnit("age", Operand.ge, 10));
		
		query.addQuery(Predicate.and, subset);
		
		
		assertThat(query.query(), is("where name = elsa and (empty gender = female or age >= 10)"));
	}
	
	@Test
	public void testQueryParameter() {
		QuerySet query = new QuerySet(false);
		query.setQueryGenerator(new SqlQueryGenerator());
		
		query.addQuery(Predicate.where, new QueryUnit("name", Operand.is, "elsa"));
		
		QuerySet subset = new QuerySet(false);
		subset.addQuery(Predicate.empty, new QueryUnit("gender", Operand.is, "female"));
		subset.addQuery(Predicate.or, new QueryUnit("age", Operand.ge, 10));
		
		query.addQuery(Predicate.and, subset);
		
		Map<Serializable, Object> pMap = query.parameters();
		
		assertThat("elsa", is((String)pMap.get("name")));
		assertThat("female", is((String)pMap.get("gender")));
		assertThat(10, is((int)pMap.get("age")));
		
		ParamObj pObj = query.parameters(ParamObj.class);
		assertThat("elsa", is(pObj.name));
		assertThat("female", is(pObj.gender));
		assertThat(10, is(pObj.age));
	}
}

class ParamObj {
	String name;
	String gender;
	int age;
}
