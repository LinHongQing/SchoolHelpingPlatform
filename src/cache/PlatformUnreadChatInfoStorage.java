package cache;

import java.util.HashMap;
import java.util.Map;

public class PlatformUnreadChatInfoStorage {
	private static final Map<String, Map<String, Integer>> unread = new HashMap<String, Map<String, Integer>>();
	
	public static synchronized void setUnreadChatInfoCount(String fromUserUid, String toUserUid, int count) {
		if (unread.get(fromUserUid) == null) {
			Map<String, Integer> newHashMap = new HashMap<String, Integer>();
			unread.put(fromUserUid, newHashMap);
		}
		Map<String, Integer> toUsers = unread.get(fromUserUid);
		toUsers.put(toUserUid, count);
	}
	
	public static synchronized Map<String, Integer> getUnreadChatInfo(String fromUserUid) {
		return unread.get(fromUserUid);
	}
	
	public static synchronized void removeUnreadChatInfoCount(String fromUserUid, String toUserUid) {
		if (unread.get(fromUserUid) == null)
			return;
		unread.get(fromUserUid).remove(toUserUid);
	}
}
