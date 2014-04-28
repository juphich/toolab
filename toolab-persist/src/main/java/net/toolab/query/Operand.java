package net.toolab.query;

public enum Operand {
	is("=", "is"),
	not("<>", "not"),
	gt(">", "greater then"), 
	ge(">=", "greater then or equal"), 
	lt("<", "less then"), 
	le("<=", "less then or equal");
	
	private String symbol;
	private String description;
	
	private Operand(String symbol, String description) {
		this.symbol = symbol;
		this.description = description;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getDescription() {
		return description;
	}
}
