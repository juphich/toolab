package net.toolab.query;


public interface QueryGenerator {

	String makeQuery(Predicate predicate, Query query);
}
