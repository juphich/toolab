package net.toolab.roundtable.manager;

import java.io.Serializable;
import java.util.List;

import net.toolab.roundtable.DefaultGaSession;
import net.toolab.roundtable.GaSession;
import net.toolab.roundtable.exception.InvalideSessionException;
import net.toolab.roundtable.repository.InMemorySessionRepository;
import net.toolab.roundtable.repository.SessionRepository;


/**
 * @author chang jung pil
 *
 */
public class GaSessionManager implements SessionManager {

	private SessionRepository sessionRepository;

	private long sessionTimeoutSecond;
	
	public GaSessionManager() {
		this(1800);
	}
	
	public GaSessionManager(long sessionTimeoutSecond) {
		this(new InMemorySessionRepository(), sessionTimeoutSecond);
	}
	
	public GaSessionManager(SessionRepository sessionRepository) {
		this(sessionRepository, 1800);
	}
	
	public GaSessionManager(SessionRepository sessionRepository, long sessionTimeoutSecond) {
		this.sessionRepository = sessionRepository;
		this.sessionTimeoutSecond = sessionTimeoutSecond;
	}

	public void setSessionRepository(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@Override
	public GaSession getSession(Serializable sessionId) {
		GaSession session = sessionRepository.getSession(sessionId);
			
		if (session != null) {
			return session;
		} else {
			throw new InvalideSessionException("session dose not exist.");
		}
	}
	
	@Override
	public List<GaSession> getSessions() {
		return sessionRepository.getSessions();
	}
	
	@Override
	public GaSession createSession() {
		GaSession session = new DefaultGaSession(sessionRepository);
		sessionRepository.setSession(session);
		return session;
	}

	@Override
	public void expireSession(Serializable sessionId) {
		sessionRepository.expireSession(sessionId);
	}

	@Override
	public void expireAll() {
		sessionRepository.initialize();
	}
	
	@Override
	public void clearExpired() {
		sessionRepository.clear(sessionTimeoutSecond);
	}

	@Override
	public long getSessionTimeoutSecond() {
		return sessionTimeoutSecond;
	}
}
