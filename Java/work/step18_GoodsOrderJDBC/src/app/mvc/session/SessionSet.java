package app.mvc.session;

import java.util.HashSet;
import java.util.Set;

public class SessionSet { // 싱글톤

	// 여러 명의 유저들이 로그인/로그아웃 하기 위한 SessionSet
	private static SessionSet ss = new SessionSet();
	private Set<Session> set; // Set: 중복 X, 순서 X

	private SessionSet() {
		set = new HashSet<>();
	}

	public static SessionSet getInstance() { // SessionSet.getInstance() 호출해서 SessionSet 리턴받는다.
		return ss;
	}

	/*
	 * 사용자 찾기
	 */
	public Session get(String sessionId) {
		for (Session session : set) {
			if (session.getSessionId().equals(sessionId)) {
				return session;
			}
		}
		
		return null;
	}

	// 세션 객체들 반환
	public Set<Session> getSet() {
		return set;
	}

	/*
	 * ★★★로그인된 사용자 추가
	 */
	public void add(Session session) {
		set.add(session);
	}

	/*
	 * ★★★사용자 로그아웃 - 세션 제거
	 */
	public void remove(Session session) {
		set.remove(session);
	}
}