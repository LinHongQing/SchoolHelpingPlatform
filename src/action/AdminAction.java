package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cache.Configurations;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferResultInfo;
import cache.PlatformOnlineUserStorage;
import cache.PlatformStatistics;
import cache.ResultCodeStorage;

import com.google.gson.Gson;

import exception.IllegalOperationException;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.PermissionDeniedException;
import service.AdminService;
import service.AdminloginlogService;
import util.DbUidGeneratorUtil;
import util.StringUtil;
import util.TimeUtil;

public class AdminAction extends BaseAction implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4325913109853529105L;
	
	private static final int save = 0;
	private static final int update = 1;
	private static final int delete = 2;
	private static final int get = 3;
	private static final int changepwd = 4;
	private static final int login = 5;
	private static final int logout = 6;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String uid;
	private String email;
	private String name;
	private String pwd;
	private String pwd_old;
	private String privilegeuid;
	private String createtime;
	private String createip;
	private String lastloginip;
	private String lastlogintime;
	private String logincount;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd_old() {
		return pwd_old;
	}

	public void setPwd_old(String pwd_old) {
		this.pwd_old = pwd_old;
	}

	public String getPrivilegeuid() {
		return privilegeuid;
	}

	public void setPrivilegeuid(String privilegeuid) {
		this.privilegeuid = privilegeuid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreateip() {
		return createip;
	}

	public void setCreateip(String createip) {
		this.createip = createip;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLogincount() {
		return logincount;
	}

	public void setLogincount(String logincount) {
		this.logincount = logincount;
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
	
	public void save() {
		operations(save);
	}
	
	public void update() {
		operations(update);
	}
	
	public void delete() {
		operations(delete);
	}
	
	public void get() {
		operations(get);
	}
	
	public void changepwd() {
		operations(changepwd);
	}
	
	public void login() {
		operations(login);
	}
	
	public void logout() {
		operations(logout);
	}
	
	private void operations(int op) {
		try {
			adminService.initParameters();
			if (uid != null && !"".equals(uid))
				adminService.setParameters(AdminService.set_uid, uid);
			if (email != null && !"".equals(email))
				adminService.setParameters(AdminService.set_email, email);
			if (name != null && !"".equals(name))
				adminService.setParameters(AdminService.set_name, name);
			if (pwd != null && !"".equals(pwd))
				adminService.setParameters(AdminService.set_pwd, pwd);
			if (pwd_old != null && !"".equals(pwd_old))
				adminService.setParameters(AdminService.set_pwd_old, pwd_old);
			if (privilegeuid != null && !"".equals(privilegeuid))
				adminService.setParameters(AdminService.set_privilegeUid, privilegeuid);
			if (createip != null && !"".equals(createip))
				adminService.setParameters(AdminService.set_createIp, createip);
			if (createtime != null && !"".equals(createtime))
				adminService.setParameters(AdminService.set_createTime, createtime);
			
			switch (op) {
			case save: {
				checkAdminLogin();
				checkPermission();
				if (name == null || "".equals(name))
					throw new IllegalParameterException("name 参数不能为空");
				if (privilegeuid == null || "".equals(privilegeuid))
					throw new IllegalParameterException("privilegeuid 参数不能为空");
				if (pwd == null || "".equals(pwd))
					throw new IllegalParameterException("pwd 参数不能为空");
				adminService.setParameters(AdminService.set_uid, DbUidGeneratorUtil.generateAdminUid());
				adminService.setParameters(AdminService.set_createIp, request.getRemoteAddr());
				adminService.setParameters(AdminService.set_createTime, TimeUtil.getNowTimeStamp());
				TransferResultInfo<?> rs = adminService.insert();
				sendMsgtoWeb(rs);
			}
			break;
			case update: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = adminService.update();
				sendMsgtoWeb(rs);
			}
			break;
			case delete: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				if (Configurations.db_admin_default_admin_uid.equals(uid))
					throw new IllegalOperationException("默认管理员无法删除");
				TransferResultInfo<?> rs = adminService.delete();
				sendMsgtoWeb(rs);
			}
			break;
			case get: {
				checkAdminLogin();
				checkPermission();
				TransferResultInfo<?> rs = adminService.find();
				sendMsgtoWeb(rs);
			}
			break;
			case changepwd: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				if (pwd_old == null || "".equals(pwd_old))
					throw new IllegalParameterException("pwd_old 参数不能为空");
				if (pwd == null || "".equals(pwd))
					throw new IllegalParameterException("pwd 参数不能为空");
				adminService.initParameters();
				adminService.setParameters(AdminService.set_uid, uid);
				adminService.setParameters(AdminService.set_pwd_old, pwd_old);
				adminService.setParameters(AdminService.set_pwd, pwd);
				TransferResultInfo<?> rs = adminService.changepwd();
				sendMsgtoWeb(rs);
			}
			break;
			case login: {
				if (email == null || "".equals(email))
					throw new IllegalParameterException("email 参数不能为空");
				if (pwd == null || "".equals(pwd))
					throw new IllegalParameterException("pwd 参数不能为空");
				TransferResultInfo<?> rs_admin = adminService.login();
				if (!rs_admin.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs_admin);
					return;
				}
				TransferOnlineUserBasicInfo onlineUser = (TransferOnlineUserBasicInfo) rs_admin.getMsgContent();
				String loginTime = TimeUtil.getNowTimeStamp();
				String loginIp = request.getRemoteAddr();
				
				onlineUser.setAdminloginip(loginIp);
				onlineUser.setAdminlogintime(Integer.valueOf(loginTime));
					
				adminloginlogService.initParameters();
				adminloginlogService.setParameters(AdminloginlogService.set_adminUid, onlineUser.getAdmin().getUid());
				TransferResultInfo<?> rs_adminLoginLog = adminloginlogService.login();
				System.out.println("登录 adminloginlog 表 login 处理结果: " + rs_adminLoginLog.getMsgType());
				if (!rs_adminLoginLog.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs_adminLoginLog);
					return;
				}
				
				TransferOnlineUserBasicInfo onlineUser2 = (TransferOnlineUserBasicInfo) rs_adminLoginLog.getMsgContent();
				onlineUser.setAdminlastloginip(onlineUser2.getAdminlastloginip());
				onlineUser.setAdminlastlogintime(onlineUser2.getAdminlastlogintime());
				onlineUser.setAdminlogincount(onlineUser2.getAdminlogincount());
				
				adminloginlogService.initParameters();
				adminloginlogService.setParameters(AdminloginlogService.set_uid, DbUidGeneratorUtil.generateAdminLoginlogUid(PlatformStatistics.getTodayLoginCount()));
				adminloginlogService.setParameters(AdminloginlogService.set_adminUid, onlineUser.getAdmin().getUid());
				adminloginlogService.setParameters(AdminloginlogService.set_ip, request.getRemoteAddr());
				adminloginlogService.setParameters(AdminloginlogService.set_loginTime, TimeUtil.getNowTimeStamp());
				rs_adminLoginLog = adminloginlogService.insert();
				System.out.println("登录 adminloginlog 表 insert 处理结果: " + rs_adminLoginLog.getMsgType());
				if (!rs_adminLoginLog.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs_adminLoginLog);
					return;
				}
				
				request.getSession().setAttribute(Configurations.session_online_user_key, onlineUser);
				PlatformOnlineUserStorage.addOnlineUser(onlineUser.getAdmin().getUid(), request.getSession());
				PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayLogin);
				
				TransferResultInfo<String> msg = new TransferResultInfo<String>();
				msg.setMsgType(ResultCodeStorage.type_success);
				msg.setMsgCode(ResultCodeStorage.code_success);
				msg.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_success));
				sendMsgtoWeb(msg);
			}
			break;
			case logout: {
				checkAdminLogin();
				TransferOnlineUserBasicInfo onlineUser = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				// request.getSession().removeAttribute(Configurations.session_online_user_key);
				PlatformOnlineUserStorage.removeOnlineUser(onlineUser.getAdmin().getUid());
				onlineUser.setAdmin(null);
				
				TransferResultInfo<String> msg = new TransferResultInfo<String>();
				msg.setMsgType(ResultCodeStorage.type_success);
				msg.setMsgCode(ResultCodeStorage.code_success);
				msg.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_success));
				sendMsgtoWeb(msg);
			}
			break;
			default: {
				throw new IllegalParameterException("op 参数无效");
			}
			}
		} catch (IllegalParameterException e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_invalid_parameter);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_parameter, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (IllegalOperationException e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_illegal_operation);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_illegal_operation, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (NoLoginException e) {
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_no_login);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_no_login, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_invalid_access);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_access, e.getMessage()));
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
	
	private void checkAdminLogin() throws NoLoginException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_admin_login_key) == null
				|| Configurations.interceptor_string_nologin.equals(session.getAttribute(Configurations.session_admin_login_key))) {
			throw new NoLoginException("管理员没有登录");
		}
	}
	
	private void checkPermission() throws PermissionDeniedException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_authorization_key) == null
				|| Configurations.interceptor_string_authorization_fail.equals(session.getAttribute(Configurations.session_authorization_key))) {
			throw new PermissionDeniedException("没有该操作的授权");
		}
	}
}
