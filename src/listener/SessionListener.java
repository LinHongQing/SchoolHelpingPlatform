package listener;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cache.PlatformOnlineUserStorage;
import cache.Configurations;
import cache.PlatformStatistics;
import bean.TransferOnlineUserBasicInfo;

public class SessionListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent session) {
		// TODO Auto-generated method stub
		PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.online);
		System.out.println("Session: " + session.getSession().getId() + " 已创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent session) {
		// TODO Auto-generated method stub
		PlatformStatistics.subOneValuetoStatistic(PlatformStatistics.online);
		TransferOnlineUserBasicInfo onlineUser = (TransferOnlineUserBasicInfo) session.getSession().getAttribute(Configurations.session_online_user_key);
		if (onlineUser != null) {
			if (onlineUser.getUser() != null) {
				PlatformOnlineUserStorage.removeOnlineUser(onlineUser.getUser().getUid());
			} else if (onlineUser.getAdmin() != null) {
				PlatformOnlineUserStorage.removeOnlineUser(onlineUser.getAdmin().getUid());
			} else {
				System.out.println("登出处理: 用户已经自行登出");
			}
		} else {
			System.out.println("登出处理: 该 session 无登录操作");
		}
		System.out.println("Session: " + session.getSession().getId() + " 已销毁");
	}

}
