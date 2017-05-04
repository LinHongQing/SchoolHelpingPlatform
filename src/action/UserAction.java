package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.UserloginlogService;
import service.UserService;
import util.DbUidGeneratorUtil;
import util.StringUtil;
import util.TimeUtil;
import cache.Configurations;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferResultInfo;
import cache.PlatformOnlineUserStorage;
import cache.PlatformStatistics;
import cache.ResultCodeStorage;

import com.google.gson.Gson;

import exception.IllegalOperationException;
import exception.NoLoginException;
import exception.PermissionDeniedException;
import exception.IllegalParameterException;

public class UserAction extends BaseAction implements ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2884193171416011552L;
	
	private static final int save = 0;
	private static final int update = 1;
	private static final int delete = 2;
	private static final int get = 3;
	private static final int login = 4;
	private static final int logout = 5;
	private static final int changepwd = 6;
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private String uid;
	private String name;
	private String num;
	private String nickname;
	private String pwd;
	private String pwd_old;
	private String academyuid;
	private String courseuid;
	private String gradeuid;
	private String locationuid;
	private String roleuid;
	private String createip;
	private String lastloginip;
	private String creditvalue;
	private String createtime;
	private String lastlogintime;
	private String logincount;
	private String type;
	private String ui;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getAcademyuid() {
		return academyuid;
	}

	public void setAcademyuid(String academyuid) {
		this.academyuid = academyuid;
	}

	public String getCourseuid() {
		return courseuid;
	}

	public void setCourseuid(String courseuid) {
		this.courseuid = courseuid;
	}

	public String getGradeuid() {
		return gradeuid;
	}

	public void setGradeuid(String gradeuid) {
		this.gradeuid = gradeuid;
	}

	public String getLocationuid() {
		return locationuid;
	}

	public void setLocationuid(String locationuid) {
		this.locationuid = locationuid;
	}

	public String getRoleuid() {
		return roleuid;
	}

	public void setRoleuid(String roleuid) {
		this.roleuid = roleuid;
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

	public String getCreditvalue() {
		return creditvalue;
	}

	public void setCreditvalue(String creditvalue) {
		this.creditvalue = creditvalue;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUi() {
		return ui;
	}

	public void setUi(String ui) {
		this.ui = ui;
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
			userService.initParameters();
			if (uid != null && !"".equals(uid))
				userService.setParameters(UserService.set_uid, uid);
			if (name != null && !"".equals(name))
				userService.setParameters(UserService.set_name, name);
			if (num != null && !"".equals(num))
				userService.setParameters(UserService.set_num, num);
			if (pwd != null && !"".equals(pwd))
				userService.setParameters(UserService.set_pwd, pwd);
			if (nickname != null && !"".equals(nickname))
				userService.setParameters(UserService.set_nickname, nickname);
			if (academyuid != null && !"".equals(academyuid))
				userService.setParameters(UserService.set_academyUid, academyuid);
			if (courseuid != null && !"".equals(courseuid))
				userService.setParameters(UserService.set_courseUid, courseuid);
			if (gradeuid != null && !"".equals(gradeuid))
				userService.setParameters(UserService.set_gradeUid, gradeuid);
			if (locationuid != null && !"".equals(locationuid))
				userService.setParameters(UserService.set_locationUid, locationuid);
			if (roleuid != null && !"".equals(roleuid))
				userService.setParameters(UserService.set_roleUid, roleuid);
			if (createip != null && !"".equals(createip))
				userService.setParameters(UserService.set_createIp, createip);
			if (creditvalue != null && !"".equals(creditvalue))
				userService.setParameters(UserService.set_creditValue, creditvalue);
			if (createtime != null && !"".equals(createtime))
				userService.setParameters(UserService.set_createTime, createtime);
			if (ui == null || "".equals(ui))
				ui = Configurations.action_general_UI_USER;
			
			switch (op) {
			case save: {
				switch (ui) {
				case Configurations.action_general_UI_USER:
					break;
				case Configurations.action_general_UI_ADMIN:
					checkAdminLogin();
					checkPermission();
					break;
				default:
					break;
				}
				if (name == null || "".equals(name))
					throw new IllegalParameterException("name 参数不能为空");
				if (num == null || "".equals(num))
					throw new IllegalParameterException("num 参数不能为空");
				if (pwd == null || "".equals(pwd))
					throw new IllegalParameterException("pwd 参数不能为空");
				if (academyuid == null || "".equals(academyuid))
					throw new IllegalParameterException("academyuid 参数不能为空");
				if (courseuid == null || "".equals(courseuid))
					throw new IllegalParameterException("courseuid 参数不能为空");
				if (gradeuid == null || "".equals(gradeuid))
					throw new IllegalParameterException("gradeuid 参数不能为空");
				if (locationuid == null || "".equals(locationuid))
					throw new IllegalParameterException("locationuid 参数不能为空");
				userService.setParameters(UserService.set_uid, DbUidGeneratorUtil.generateUserUid());
				userService.setParameters(UserService.set_pwd, pwd);
				userService.setParameters(UserService.set_createIp, request.getRemoteAddr());
				userService.setParameters(UserService.set_createTime, TimeUtil.getNowTimeStamp());
				userService.setParameters(UserService.set_roleUid, Configurations.getDefaultUserRoleUid());
				userService.setParameters(UserService.set_creditValue, String.valueOf(Configurations.getDefaultUserCreditValue()));
				TransferResultInfo<?> rs = userService.insert();
				sendMsgtoWeb(rs);
			}
			break;
			case update: {
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				switch(ui) {
				case Configurations.action_general_UI_ADMIN:
					checkAdminLogin();
					checkPermission();
					break;
				case Configurations.action_general_UI_USER:
				default:
				checkUserLogin();
				checkPermission();
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				if (!uid.equals(user.getUser().getUid())) {
					throw new IllegalOperationException("不能修改其他人的信息");
				}
				}
				TransferResultInfo<?> rs = userService.update();
				sendMsgtoWeb(rs);
			}
			break;
			case delete: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = userService.delete();
				sendMsgtoWeb(rs);
			}
			break;
			case get: {
				if (type == null || "".equals(type))
					type = Configurations.action_user_type_get_summary;
				switch(ui) {
				case Configurations.action_general_UI_ADMIN:
					checkAdminLogin();
					checkPermission();
					break;
				case Configurations.action_general_UI_USER:
				default:
				checkUserLogin();
				}
				switch (type) {
				case Configurations.action_user_type_get_summary: {
					
					switch(ui) {
					case Configurations.action_general_UI_USER:
						if (uid == null || "".equals(uid)) {
							TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
							userService.setParameters(UserService.set_uid, user.getUser().getUid());
						}
					}
					TransferResultInfo<?> rs = userService.find(UserService.findMode_summary);
					sendMsgtoWeb(rs);
				}
				break;
				case Configurations.action_user_type_get_detail: {
					switch(ui) {
					case Configurations.action_general_UI_USER:
						if (uid == null || "".equals(uid)) {
							TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
							userService.setParameters(UserService.set_uid, user.getUser().getUid());
						}
					}
					TransferResultInfo<?> rs = userService.find(UserService.findMode_detail);
					sendMsgtoWeb(rs);
				}
				break;
				default:
					throw new IllegalParameterException("type 参数无效");
				}
			}
			break;
			case changepwd: {
				switch(ui) {
				case Configurations.action_general_UI_ADMIN: {
					checkAdminLogin();
					checkPermission();
					if (uid == null || "".equals(uid))
						throw new IllegalParameterException("uid 参数不能为空");
					if (pwd == null || "".equals(pwd))
						throw new IllegalParameterException("pwd 参数不能为空");
					userService.initParameters();
					userService.setParameters(UserService.set_uid, uid);
					userService.setParameters(UserService.set_pwd, pwd);
					TransferResultInfo<?> rs = userService.changepwd(UserService.changePwd_admin);
					sendMsgtoWeb(rs);
				}
				break;
				case Configurations.action_general_UI_USER:
				default: {
					checkUserLogin();
					checkPermission();
					TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
					if (pwd_old == null || "".equals(pwd_old))
						throw new IllegalParameterException("pwd_old 参数不能为空");
					if (pwd == null || "".equals(pwd))
						throw new IllegalParameterException("pwd 参数不能为空");
					userService.initParameters();
					userService.setParameters(UserService.set_uid, user.getUser().getUid());
					userService.setParameters(UserService.set_pwd_old, pwd_old);
					userService.setParameters(UserService.set_pwd, pwd);
					TransferResultInfo<?> rs = userService.changepwd(UserService.changePwd_user);
					sendMsgtoWeb(rs);
				}
				}
			}
			break;
			case login: {
				if (num == null || "".equals(num))
					throw new IllegalParameterException("num 参数不能为空");
				if (pwd == null || "".equals(pwd))
					throw new IllegalParameterException("pwd 参数不能为空");
				TransferResultInfo<?> rs_user = userService.login();
				if (!rs_user.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs_user);
					return;
				}
				TransferOnlineUserBasicInfo onlineUser = (TransferOnlineUserBasicInfo) rs_user.getMsgContent();
				
				String loginTime = TimeUtil.getNowTimeStamp();
				String loginIp = request.getRemoteAddr();
				
				onlineUser.setUserloginip(loginIp);
				onlineUser.setUserlogintime(Integer.valueOf(loginTime));
				
				
				userloginlogService.initParameters();
				userloginlogService.setParameters(UserloginlogService.set_userUid, onlineUser.getUser().getUid());
				TransferResultInfo<?> rs_userLoginLog = userloginlogService.login();
				System.out.println("登录 userloginlog 表 login 处理结果: " + rs_userLoginLog.getMsgType());
				if (!rs_userLoginLog.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs_userLoginLog);
					return;
				}
				
				TransferOnlineUserBasicInfo onlineUser2 = (TransferOnlineUserBasicInfo) rs_userLoginLog.getMsgContent();
				onlineUser.setUserlastloginip(onlineUser2.getUserlastloginip());
				onlineUser.setUserlastlogintime(onlineUser2.getUserlastlogintime());
				onlineUser.setUserlogincount(onlineUser2.getUserlogincount());
				
				userloginlogService.initParameters();
				userloginlogService.setParameters(UserloginlogService.set_uid, DbUidGeneratorUtil.generateAdminLoginlogUid(PlatformStatistics.getTodayLoginCount()));
				userloginlogService.setParameters(UserloginlogService.set_userUid, onlineUser.getUser().getUid());
				userloginlogService.setParameters(UserloginlogService.set_ip, request.getRemoteAddr());
				userloginlogService.setParameters(UserloginlogService.set_loginTime, TimeUtil.getNowTimeStamp());
				rs_userLoginLog = userloginlogService.insert();
				System.out.println("登录 userloginlog 表 insert 处理结果: " + rs_userLoginLog.getMsgType());
				if (!rs_userLoginLog.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs_userLoginLog);
					return;
				}
				
				request.getSession().setAttribute(Configurations.session_online_user_key, onlineUser);
				PlatformOnlineUserStorage.addOnlineUser(onlineUser.getUser().getUid(), request.getSession());
				PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayLogin);
				
				TransferResultInfo<String> msg = new TransferResultInfo<String>();
				msg.setMsgType(ResultCodeStorage.type_success);
				msg.setMsgCode(ResultCodeStorage.code_success);
				msg.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_success));
				sendMsgtoWeb(msg);
				PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayLogin);
			}
			break;
			case logout: {
				checkUserLogin();
				TransferOnlineUserBasicInfo onlineUser = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				
				// request.getSession().removeAttribute(Configurations.session_online_user_key);
				PlatformOnlineUserStorage.removeOnlineUser(onlineUser.getUser().getUid());
				onlineUser.setUser(null);
				
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
	
	private void checkUserLogin() throws NoLoginException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_user_login_key) == null
				|| Configurations.interceptor_string_nologin.equals(session.getAttribute(Configurations.session_user_login_key)))
			throw new NoLoginException("普通用户没有登录");
	}
	
	private void checkAdminLogin() throws NoLoginException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_admin_login_key) == null
				|| Configurations.interceptor_string_nologin.equals(session.getAttribute(Configurations.session_admin_login_key)))
			throw new NoLoginException("管理员用户没有登录");
	}
	
	private void checkPermission() throws PermissionDeniedException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_authorization_key) == null
				|| Configurations.interceptor_string_authorization_fail.equals(session.getAttribute(Configurations.session_authorization_key)))
			throw new PermissionDeniedException("没有该操作的授权");
	}

}
