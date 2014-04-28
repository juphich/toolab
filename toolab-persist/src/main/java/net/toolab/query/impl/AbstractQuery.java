package net.toolab.query.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import net.toolab.query.Query;
import net.toolab.query.QueryUnit;

public abstract class AbstractQuery implements Query {

	private boolean allowDuplicate = false;
	
	private Collection<QueryUnit> queries;
	
	private Map<Serializable, Object> keyMap = new HashMap<>();
	
	protected AbstractQuery(Collection<QueryUnit> queries) {
		this(queries, false);
	}
	
	protected AbstractQuery(Collection<QueryUnit> queries, boolean allow) {
		this.allowDuplicate = allow;
		
		if (allowDuplicate) {
			this.queries = new HashSet<>(queries);
		} else {
			this.queries = new ArrayList<>(queries);
		}
	}
	
	@Override
	public Map<Serializable, Object> entry() {
		return keyMap;
	}
	
	@Override
	public <P> P entry(Class<P> type) {
		return null;
	}

	private void extract() {
		
	}
}
