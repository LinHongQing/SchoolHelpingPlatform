package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.AdminloginlogService;
import service.UserloginlogService;
import util.StringUtil;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferResultInfo;
import cache.Configurations;
import cache.ResultCodeStorage;

import com.google.gson.Gson;

import exception.IllegalParameterException;
import exception.NoLoginException;

public class LoginlogAction extends BaseAction implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8431699329801747325L;
	
	private static final int admin = 0;
	private static final int user = 1;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;
	private String adminuid;
	private String useruid;
	private String ip;
	private String logintime;
	private String ui;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getUi() {
		return ui;
	}

	public void setUi(String ui) {
		this.ui = ui;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	public void admin() {
		operations(admin);
	}
	
	public void user() {
		operations(user);
	}
	
	private void operations(int op) {
		try {
			switch(ui) {
			case Configurations.action_general_UI_ADMIN:
				checkAdminLogin();
				break;
			case Configurations.action_general_UI_USER:
			default:
				checkUserLogin();
			}
			switch (op) {
			case admin: {
				adminloginlogService.initParameters();
				if (uid != null && !"".equals(uid))
					adminloginlogService.setParameters(AdminloginlogService.set_uid, uid);
				if (adminuid != null && !"".equals(adminuid))
					adminloginlogService.setParameters(AdminloginlogService.set_adminUid, adminuid);
				if (logintime != null && !"".equals(logintime))
					adminloginlogService.setParameters(AdminloginlogService.set_loginTime, logintime);
				if (ip != null && !"".equals(ip))
					adminloginlogService.setParameters(AdminloginlogService.set_ip, ip);
				TransferResultInfo<?> rs = adminloginlogService.find();
				sendMsgtoWeb(rs);
			}
			break;
			case user: {
				switch(ui) {
				case Configurations.action_general_UI_ADMIN: {
					userloginlogService.initParameters();
					if (uid != null && !"".equals(uid))
						userloginlogService.setParameters(UserloginlogService.set_uid, uid);
					if (useruid != null && !"".equals(useruid))
						userloginlogService.setParameters(UserloginlogService.set_userUid, useruid);
					if (logintime != null && !"".equals(logintime))
						userloginlogService.setParameters(UserloginlogService.set_loginTime, logintime);
					if (ip != null && !"".equals(ip))
						userloginlogService.setParameters(UserloginlogService.set_ip, ip);
					TransferResultInfo<?> rs = userloginlogService.find();
					sendMsgtoWeb(rs);
				}
				break;
				case Configurations.action_general_UI_USER:
				default: {
					TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
					userloginlogService.initParameters();
					if (uid != null && !"".equals(uid))
						userloginlogService.setParameters(UserloginlogService.set_uid, uid);
					if (logintime != null && !"".equals(logintime))
						userloginlogService.setParameters(UserloginlogService.set_loginTime, logintime);
					if (ip != null && !"".equals(ip))
						userloginlogService.setParameters(UserloginlogService.set_ip, ip);
					userloginlogService.setParameters(UserloginlogService.set_userUid, user.getUser().getUid());
					TransferResultInfo<?> rs = userloginlogService.find();
					sendMsgtoWeb(rs);
				}
				}
			}
			break;
			default:
				throw new IllegalParameterException("op 参数无效");
			}
		} catch (IllegalParameterException e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_invalid_parameter);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_parameter, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (NoLoginException e) {
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_no_login);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_no_login, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_generic_server_internal_exception);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_generic_server_internal_exception, e.getMessage()));
			sendMsgtoWeb(rs);
		}
	}
	
	private void sendMsgtoWeb(TransferResultInfo<?> result) {
		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out;
		try {
			out = response.getWriter();
			Gson gson = new Gson();
			out.print(gson.toJson(result));
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_generic_server_internal_exception);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_generic_server_internal_exception, e.getMessage()));
			sendMsgtoWeb(rs);
		}
	}
	
	private void checkUserLogin() throws NoLoginException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_user_login_key) == null
				|| Configurations.string_nologin.equals(session.getAttribute(Configurations.session_user_login_key)))
			throw new NoLoginException("普通用户没有登录");
	}
	
	private void checkAdminLogin() throws NoLoginException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_admin_login_key) == null
				|| Configurations.string_nologin.equals(session.getAttribute(Configurations.session_admin_login_key)))
			throw new NoLoginException("管理员用户没有登录");
	}
}
