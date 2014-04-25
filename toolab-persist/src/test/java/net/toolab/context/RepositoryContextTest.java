package net.toolab.context;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class RepositoryContextTest {

	@Test
	public void testRepositoryContext() {
		RepositoryContext context = new RepositoryContext();
		
		MockRepository repository = new MockRepository();
		context.add(Domain.class, repository);
		
		MockRepository result = context.get(Domain.class);
		
		assertThat(result, is(repository));
	}
	
	@Test
	public void testRegister() {
		MockRepository repository = new MockRepository();
		Repository.register(Domain.class, repository);
		
		MockRepository result = Repository.find(Domain.class);
		
		assertThat(result, is(repository));
	}
}

class Domain {
}

class MockRepository {
}