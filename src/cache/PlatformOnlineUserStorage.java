package cache;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class PlatformOnlineUserStorage {
	private static Map<String, HttpSession> onlineUser = new HashMap<String, HttpSession>();
	
	public static synchronized void addOnlineUser(String userUid, HttpSession userSession) {
		onlineUser.put(userUid, userSession);
	}
	
	public static synchronized void removeOnlineUser(String userUid) {
		onlineUser.remove(userUid);
	}
	
	public static synchronized int getOnlineUserCount() {
		return onlineUser.size();
	}
	
	public static synchronized HttpSession getOnlineUserHttpSession(String userUid) {
		return onlineUser.get(userUid);
	}

}
