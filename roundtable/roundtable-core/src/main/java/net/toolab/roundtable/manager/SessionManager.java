package net.toolab.roundtable.manager;

import java.io.Serializable;
import java.util.List;

import net.toolab.roundtable.GaSession;


/**
 * @author chang jung pil
 *
 */
public interface SessionManager {

	GaSession getSession(Serializable sessionId);

	GaSession createSession();
	
	void expireSession(Serializable sessionId);

	void expireAll();
	
	void clearExpired();

	List<GaSession> getSessions();

	long getSessionTimeoutSecond();
}