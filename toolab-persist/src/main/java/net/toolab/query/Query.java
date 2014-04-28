package net.toolab.query;

import java.io.Serializable;
import java.util.Map;

public interface Query {

	String queryString();

	Map<Serializable, Object> entry();

	<P> P entry(Class<P> type);
}
