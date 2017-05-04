package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import bean.TransferOnlineUserBasicInfo;
import cache.Configurations;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7220641832068258889L;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();						// 获取请求的 session
		if (session.getAttribute(Configurations.session_online_user_key) == null) {
			session.setAttribute(Configurations.session_user_login_key, Configurations.interceptor_string_nologin);
			session.setAttribute(Configurations.session_admin_login_key, Configurations.interceptor_string_nologin);
			System.out.println("无登录信息, 未登录");
		} else {
			TransferOnlineUserBasicInfo user = null;
			try {
				user = (TransferOnlineUserBasicInfo) session.getAttribute(Configurations.session_online_user_key);
			} catch (ClassCastException e) {
				session.setAttribute(Configurations.session_user_login_key, Configurations.interceptor_string_nologin);
				System.out.println("无登录信息, 未登录");
				return ai.invoke();
			}
			user = (TransferOnlineUserBasicInfo) session.getAttribute(Configurations.session_online_user_key);
			switch(role) {
			case Configurations.interceptor_string_login_request_type_user:
				if (user.getUser() == null) {
					session.setAttribute(Configurations.session_user_login_key, Configurations.interceptor_string_nologin);
					System.out.println("用户未登录");
				} else {
					session.setAttribute(Configurations.session_user_login_key, Configurations.interceptor_string_login);
					System.out.println("用户已登录");
				}
				return ai.invoke();
			case Configurations.interceptor_string_login_request_type_admin:
				if (user.getAdmin() == null) {
					session.setAttribute(Configurations.session_admin_login_key, Configurations.interceptor_string_nologin);
					System.out.println("管理员未登录");
				} else {
					session.setAttribute(Configurations.session_admin_login_key, Configurations.interceptor_string_login);
					System.out.println("管理员已登录");
				}
			}
		}
		return ai.invoke();
	}

}
