package interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cache.Configurations;
import bean.TransferOnlineUserBasicInfo;
import cache.PrivilegeCodeRequestPathStorage;

public class AuthorizationInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5248826803104949875L;
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
		HttpSession session = request.getSession();		// 获取请求的 session
		
		String requestName = ai.getInvocationContext().getName();				// 获取请求的名称
		TransferOnlineUserBasicInfo user;
		// 获取请求 session 的用户信息并判断是否有登录信息
		switch (role) {
		case Configurations.inerceptor_string_authorization_request_type_admin: {	// 管理员
			if (session.getAttribute(Configurations.session_online_user_key) == null) {
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				System.out.println("无登录信息, 授权失败");
				return ai.invoke();
			}
			try {
				user = (TransferOnlineUserBasicInfo) session.getAttribute(Configurations.session_online_user_key);
			} catch (ClassCastException e) {
				// TODO: handle exception
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				System.out.println("无登录信息, 授权失败");
				return ai.invoke();
			}
		}
		break;
		case Configurations.interceptor_string_authorization_request_type_user:
		default: {	// 普通用户
			if (session.getAttribute(Configurations.session_online_user_key) == null) {
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				System.out.println("无登录信息, 授权失败");
				return ai.invoke();
			}
			try {
				user = (TransferOnlineUserBasicInfo) session.getAttribute(Configurations.session_online_user_key);
			} catch (ClassCastException e) {
				// TODO: handle exception
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				System.out.println("无登录信息, 授权失败");
				return ai.invoke();
			}
		}
		}
		int code = PrivilegeCodeRequestPathStorage.invalid_request_path_code;	// 初始化权限的代号
		switch (role) {		// 判断是普通用户授权还是管理员授权
		case Configurations.inerceptor_string_authorization_request_type_admin: {	// 管理员
			if (user.getAdmin() == null) {
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				System.out.println("无管理员登录信息, 授权失败");
				return ai.invoke();
			}
		}
		break;
		case Configurations.interceptor_string_authorization_request_type_user:
		default:{	// 普通用户
			if (user.getUser() == null) {
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				System.out.println("无普通用户登录信息, 授权失败");
				return ai.invoke();
			}
		}
		}
		switch (role) {		// 判断是普通用户授权还是管理员授权
		case Configurations.inerceptor_string_authorization_request_type_admin: {		// 管理员授权
			// 获取路径对应的管理员授权代号
			code = PrivilegeCodeRequestPathStorage.getRequestPathPrivilegeCode(PrivilegeCodeRequestPathStorage.admin, requestName);
			if (code == Configurations.int_invalid) {	// 未找到授权代号, 授权失败
				System.out.println("未找到请求对应的管理员授权代号");
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				return ai.invoke();
			}
		}
		break;
		case Configurations.interceptor_string_authorization_request_type_user:
		default: {		// 普通用户授权
			// 获取路径对应的普通用户授权代号
			code = PrivilegeCodeRequestPathStorage.getRequestPathPrivilegeCode(PrivilegeCodeRequestPathStorage.user, requestName);
			if (code == Configurations.int_invalid) {	// 未找到授权代号, 授权失败
				System.out.println("未找到请求对应的普通用户授权代号");
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				return ai.invoke();
			}
		}
		}
		switch (role) {			// 判断是普通用户授权还是管理员授权
		case Configurations.interceptor_string_authorization_request_type_user: {
			// 判断普通用户是否成功授权
			switch (PrivilegeCodeRequestPathStorage.resolvePrivilegeCode(user.getUser().getRole().getRolecode(), code)) {
			case PrivilegeCodeRequestPathStorage.is_authorized:
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_success);
				System.out.println("普通用户授权成功");
				break;
			case PrivilegeCodeRequestPathStorage.not_authorized:
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				System.out.println("普通用户授权失败");
				break;
			}
		}
		break;
		case Configurations.inerceptor_string_authorization_request_type_admin: {
			// 判断管理员是否成功授权
			switch (PrivilegeCodeRequestPathStorage.resolvePrivilegeCode(user.getAdmin().getPrivilege().getPrivilegecode(), code)) {
			case PrivilegeCodeRequestPathStorage.is_authorized:
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_success);
				System.out.println("管理员授权成功");
				break;
			case PrivilegeCodeRequestPathStorage.not_authorized:
				session.setAttribute(Configurations.session_authorization_key, Configurations.interceptor_string_authorization_fail);
				System.out.println("管理员授权失败");
				break;
			}
		}
		}
		return ai.invoke();
	}

}
