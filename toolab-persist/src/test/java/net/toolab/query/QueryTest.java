package net.toolab.query;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import net.toolab.query.builder.QueryBuilder;

import org.junit.Test;

/**
 * @author jupic
 *
 * query 의 종류는 여러가지가 존재한다.
 * 
 * sql query
 * http get request 요청 query
 * mongodb 등 nosql query
 * 
 * 각 query 들은 query string으로 변환가능 하며,
 * 
 * 해당 query에 대한 key map을 가지고 있다.
 * 
 * key map은 pojo로 작성이 가능하고 
 * 
 * default key map을 등록 가능하다.
 * 
 */
public class QueryTest {

	@Test
	public void testBuildQuery() {
		Query query = new QueryBuilder()
			.where("userNum").is(4)
			.and("posts").gt(100)
			.and("flag").not("test")
			.build();
		
		assertNotNull(query);
		assertThat(query.queryString(), is("where userNum = 4 and flag <> test and posts > 100"));
	}
	
	@Test
	public void testBuildOrQuery() {
		throw new RuntimeException();
	}
}
