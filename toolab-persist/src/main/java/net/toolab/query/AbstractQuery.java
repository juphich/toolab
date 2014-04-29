package net.toolab.query;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import net.toolab.query.exception.QueryCompositionException;
import net.toolab.utils.ReflectionUtils;

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
		
		extract();
	}
	
	protected Collection<QueryUnit> queries() {
		return queries;
	}
	
	@Override
	public Map<Serializable, Object> entry() {
		return keyMap;
	}
	
	@Override
	public <P> P entry(Class<P> type) {
		try {
			Constructor<P> constructor = type.getDeclaredConstructor();
			
			constructor.setAccessible(true);
			P target = constructor.newInstance();
			constructor.setAccessible(false);
			
			for (Entry<Serializable, Object> entry : keyMap.entrySet()) {
				String field = String.valueOf(entry.getKey());
				ReflectionUtils.bindPropertyValue(target, field, entry.getValue());
			}
			
			return target;
		} catch (NoSuchMethodException e) {
			throw new QueryCompositionException("It's necessary default constructor to create keymap object..", e);
		} catch (SecurityException e) {
			throw new QueryCompositionException("Could not access to constructor of target class.", e);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new QueryCompositionException("Could not bind to keymap object field.", e);
		}
	}

	private void extract() {
		for (QueryUnit unit : queries) {
			keyMap.put(unit.key(), unit.value());
		}
	}
}
