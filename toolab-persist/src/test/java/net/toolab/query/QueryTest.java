package net.toolab.query;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import net.toolab.query.builder.QueryUnitBuilder;
import net.toolab.query.sql.SqlQueryBuilder;

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
		Query query = new SqlQueryBuilder()
			.where("userNum").is(4)
			.and("posts").gt(100)
			.and("flag").not("test")
			.build();
		
		assertNotNull(query);
		assertThat(query.queryString(), is("where userNum = 4 and flag <> test and posts > 100"));
	}
	
	@Test
	public void testQueryKeyMapTest() {
		Query query = new SqlQueryBuilder()
			.where("userNum").is(4)
			.and("name").is("elsa")
			.and("posts").gt(100)
			.and("allow").is(true)
			.and("flag").not("test")
			.build();
		
		Param param = query.entry(Param.class);
		
		assertThat(param.userNum, is(4));
		assertThat(param.name, is("elsa"));
		assertThat(param.allow, is(true));
	}
	
	@Test
	public void testBuildOrQuery() {
		Query query = new SqlQueryBuilder()
			.where("1").is(1)
			.and(
				new QueryUnitBuilder(Predicate.and, "", new SqlQueryBuilder()).is(3)
				//with("").is(2).and("vjli").ge(37)
			 )
			.build();
		throw new RuntimeException();
	}
}

class Param {
	String name;
	int userNum;
	boolean allow;
}
