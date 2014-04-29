package net.toolab.query;

import static net.toolab.query.builder.QueryFactory.*;

import org.junit.Test;

public class BuilderFactoryTest {

	@Test
	public void testBuilderFactory() {
		Query query = query(ofSql()
					.where("").is("ss")
				);
		throw new RuntimeException();
	}
}
