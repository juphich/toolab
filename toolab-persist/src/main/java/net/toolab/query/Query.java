package net.toolab.query;

import java.io.Serializable;
import java.util.Map;

public interface Query {

	String query();

	Map<Serializable, Object> parameters();

	<P> P parameters(Class<P> type);
}
