package net.toolab.roundtable.key;

import java.io.Serializable;

public interface SessionKeyGenerator<T extends Serializable> {
	T generateKey();
}