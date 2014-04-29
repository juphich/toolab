package net.toolab.query.exception;

public class QueryCompositionException extends RuntimeException {

	private static final long serialVersionUID = 3072260388656048258L;

	public QueryCompositionException(String message, Throwable t) {
		super(message, t);
	}

	public QueryCompositionException(String message) {
		super(message);
	}
}
