package net.toolab.query;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import net.toolab.query.sql.SqlQueryGenerator;
import net.toolab.query.sql.SqlQuerySetBuilder;

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
public class QueryBuilderTest {

	@Test
	public void testBuildQuery() {
		Query query = new SqlQuerySetBuilder(new SqlQueryGenerator())
			.where("userNum").is(4)
			.and("posts").gt(100)
			.and("flag").not("test")
			.build();
		
		assertNotNull(query);
		assertThat(query.query(), is("where userNum = 4 and posts > 100 and flag <> test"));
	}
	
	@Test
	public void testQueryKeyMapTest() {
		Query query = new SqlQuerySetBuilder(new SqlQueryGenerator())
			.where("userNum").is(4)
			.and("name").is("elsa")
			.and("posts").gt(100)
			.and("allow").is(true)
			.and("flag").not("test")
			.build();
		
		Param param = query.parameters(Param.class);
		
		assertThat(param.userNum, is(4));
		assertThat(param.name, is("elsa"));
		assertThat(param.allow, is(true));
	}
	
	@Test
	public void testBuildOrQuery() {
		Query query = new SqlQuerySetBuilder(new SqlQueryGenerator())
			.where("1").is(1)
			.and(
				new SqlQuerySetBuilder(null).with("name").is("elsa").or("class").is("princess")
			 )
			.and("age").lt(20)
			.build();
		
		assertNotNull(query);
		assertThat(query.query(), is("where 1 = 1 and (name = elsa or class = princess) and age < 20"));
	}
}

class Param {
	String name;
	int userNum;
	boolean allow;
}
