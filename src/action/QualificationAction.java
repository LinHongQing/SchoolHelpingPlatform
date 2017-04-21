package action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;

import service.QualificationrequestService;
import service.UserService;
import util.DbUidGeneratorUtil;
import util.StringUtil;
import util.TimeUtil;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferQualificationrequestInfo;
import bean.TransferResultInfo;
import cache.Configurations;
import cache.PlatformStatistics;
import cache.ResultCodeStorage;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.PermissionDeniedException;

public class QualificationAction extends BaseAction implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624152569922716830L;
	
	private static final int apply = 0;
	private static final int verify = 1;
	private static final int get = 2;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;
	private String useruid;
	private String typeuid;
	private String resourceuid;
	private String description;
	private String requesttime;
	private String requestip;
	private String checkingstatus;
	private String checkingtype;
	private String checkingip;
	private String checkinguseruid;
	private String type;
	private String ui;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getTypeuid() {
		return typeuid;
	}

	public void setTypeuid(String typeuid) {
		this.typeuid = typeuid;
	}

	public String getResourceuid() {
		return resourceuid;
	}

	public void setResourceuid(String resourceuid) {
		this.resourceuid = resourceuid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

	public String getRequestip() {
		return requestip;
	}

	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}

	public String getCheckingstatus() {
		return checkingstatus;
	}

	public void setCheckingstatus(String checkingstatus) {
		this.checkingstatus = checkingstatus;
	}

	public String getCheckingtype() {
		return checkingtype;
	}

	public void setCheckingtype(String checkingtype) {
		this.checkingtype = checkingtype;
	}

	public String getCheckingip() {
		return checkingip;
	}

	public void setCheckingip(String checkingip) {
		this.checkingip = checkingip;
	}

	public String getCheckinguseruid() {
		return checkinguseruid;
	}

	public void setCheckinguseruid(String checkinguseruid) {
		this.checkinguseruid = checkinguseruid;
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
	
	public void apply() {
		operations(apply);
	}
	
	public void get() {
		operations(get);
	}
	
	public void verify() {
		operations(verify);
	}
	
	public void operations(int op) {
		try {
			qualificationrequestService.initParameters();
			if (uid != null && !"".equals(uid))
				qualificationrequestService.setParameters(QualificationrequestService.set_uid, uid);
			if (useruid != null && !"".equals(useruid))
				qualificationrequestService.setParameters(QualificationrequestService.set_userUid, useruid);
			if (typeuid != null && !"".equals(typeuid))
				qualificationrequestService.setParameters(QualificationrequestService.set_typeUid, typeuid);
			if (resourceuid != null && !"".equals(resourceuid))
				qualificationrequestService.setParameters(QualificationrequestService.set_resourceUid, resourceuid);
			if (description != null && !"".equals(description))
				qualificationrequestService.setParameters(QualificationrequestService.set_description, description);
			if (requesttime != null && !"".equals(requesttime))
				qualificationrequestService.setParameters(QualificationrequestService.set_requestTime, requesttime);
			if (requestip != null && !"".equals(requestip))
				qualificationrequestService.setParameters(QualificationrequestService.set_requestIp, requestip);
			if (checkingstatus != null && !"".equals(checkingstatus))
				qualificationrequestService.setParameters(QualificationrequestService.set_checkingStatus, checkingstatus);
			if (checkingtype != null && !"".equals(checkingtype))
				qualificationrequestService.setParameters(QualificationrequestService.set_checkingType, checkingtype);
			if (checkingip != null && !"".equals(checkingip))
				qualificationrequestService.setParameters(QualificationrequestService.set_checkingIp, checkingip);
			if (checkinguseruid != null && !"".equals(checkinguseruid))
				qualificationrequestService.setParameters(QualificationrequestService.set_checkingUserUid, checkinguseruid);
			if (ui == null || "".equals(uid))
				ui = Configurations.action_general_UI_USER;
			
			switch (op) {
			case apply: {
				checkUserLogin();
				checkPermission();
				if (typeuid == null || "".equals(typeuid))
					throw new IllegalParameterException("typeuid 参数不能为空");
				if (description == null || "".equals(description))
					throw new IllegalParameterException("description 参数不能为空");
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				qualificationrequestService.setParameters(QualificationrequestService.set_uid,
						DbUidGeneratorUtil.generateQualificationrequest(PlatformStatistics.getTodayQualificationRequestCount()));
				qualificationrequestService.setParameters(QualificationrequestService.set_userUid, user.getUser().getUid());
				qualificationrequestService.setParameters(QualificationrequestService.set_requestIp, request.getRemoteAddr());
				qualificationrequestService.setParameters(QualificationrequestService.set_requestTime, TimeUtil.getNowTimeStamp());
				qualificationrequestService.setParameters(QualificationrequestService.set_checkingStatus,
						String.valueOf(Configurations.db_qualificationrequest_checkingstatus_waiting));
				TransferResultInfo<?> rs = qualificationrequestService.insert();
				if (rs.getMsgType().equals(ResultCodeStorage.type_success))
					PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.getTodayQualificationRequestCount());
				sendMsgtoWeb(rs);
			}
			break;
			case verify: {
				checkAdminLogin();
				checkPermission();
				TransferOnlineUserBasicInfo admin = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				if (checkingstatus == null || "".equals(checkingstatus))
					throw new IllegalParameterException("checkingstatus 参数不能为空");
				int int_checkingStatus;
				try {
					switch(int_checkingStatus = Integer.parseInt(checkingstatus)) {
					case Configurations.db_qualificationrequest_checkingstatus_fail:
					case Configurations.db_qualificationrequest_checkingstatus_pass:
						qualificationrequestService.setParameters(QualificationrequestService.set_checkingStatus, checkingstatus);
						break;
					default:
						throw new IllegalParameterException("checkingstatus 参数无效");
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					throw new IllegalParameterException("checkingstatus 参数无效");
				}
				qualificationrequestService.setParameters(QualificationrequestService.set_checkingUserUid, admin.getAdmin().getUid());
				qualificationrequestService.setParameters(QualificationrequestService.set_checkingIp, request.getRemoteAddr());
				qualificationrequestService.setParameters(QualificationrequestService.set_checkingTime, TimeUtil.getNowTimeStamp());
				TransferResultInfo<?> rs_qualificationRequest = qualificationrequestService.update();
				if (ResultCodeStorage.type_error.equals(rs_qualificationRequest.getMsgType())) {
					sendMsgtoWeb(rs_qualificationRequest);
					return;
				}
				if (int_checkingStatus == Configurations.db_qualificationrequest_checkingstatus_fail) {
					sendMsgtoWeb(rs_qualificationRequest);
					return;
				}
				qualificationrequestService.initParameters();
				qualificationrequestService.setParameters(QualificationrequestService.set_uid, uid);
				rs_qualificationRequest = qualificationrequestService.find(QualificationrequestService.find_summary);
				if (!rs_qualificationRequest.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs_qualificationRequest);
					return;
				}
				String qualTypeUid = "";
				String qualRequestUserUid = "";
				@SuppressWarnings("unchecked")
				List<TransferQualificationrequestInfo> list_qualificationRequest = (List<TransferQualificationrequestInfo>) rs_qualificationRequest.getMsgContent();
				for (TransferQualificationrequestInfo transferQualificationrequestInfo : list_qualificationRequest) {
					qualTypeUid = transferQualificationrequestInfo.getType().getUid();
					qualRequestUserUid = transferQualificationrequestInfo.getUser().getUid();
					break;
				}
				if ("".equals(qualTypeUid) || "".equals(qualRequestUserUid)) {
					throw new IllegalParameterException("uid 参数无效");
				}

				userService.initParameters();
				userService.setParameters(UserService.set_uid, qualRequestUserUid);
				userService.setParameters(UserService.set_qualTypeUid, qualTypeUid);
				TransferResultInfo<?> rs_user = userService.update();
				sendMsgtoWeb(rs_user);
				}
			break;
			case get: {
				switch(ui) {
				case Configurations.action_general_UI_ADMIN: {
					checkAdminLogin();
					checkPermission();
					if (type == null || "".equals(type))
						type = Configurations.action_qualification_type_get_summary;
					switch(type) {
					case Configurations.action_qualification_type_get_summary: {
						TransferResultInfo<?> rs = qualificationrequestService.find(QualificationrequestService.find_summary);
						sendMsgtoWeb(rs);
					}
					break;
					case Configurations.action_qualification_type_get_detail: {
						if (uid == null || "".equals(uid))
							throw new IllegalParameterException("uid 参数不能为空");
						TransferResultInfo<?> rs = qualificationrequestService.find(QualificationrequestService.find_detail);
						sendMsgtoWeb(rs);
					}
					break;
					default:
						throw new IllegalParameterException("type 参数无效");
					}
				}
				break;
				case Configurations.action_general_UI_USER:
				default: {
					checkUserLogin();
					checkPermission();
					TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
					qualificationrequestService.setParameters(QualificationrequestService.set_userUid, user.getUser().getUid());
					switch(type) {
					case Configurations.action_qualification_type_get_summary: {
						TransferResultInfo<?> rs = qualificationrequestService.find(QualificationrequestService.find_summary);
						sendMsgtoWeb(rs);
					}
					break;
					case Configurations.action_qualification_type_get_detail: {
						if (uid == null || "".equals(uid))
							throw new IllegalParameterException("uid 不能为空");
						TransferResultInfo<?> rs = qualificationrequestService.find(QualificationrequestService.find_detail);
						sendMsgtoWeb(rs);
					}
					break;
					default:
						throw new IllegalParameterException("type 参数无效");
					}
					
				}
				break;
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
				|| Configurations.string_nologin.equals(session.getAttribute(Configurations.session_user_login_key)))
			throw new NoLoginException("普通用户没有登录");
	}
	
	private void checkAdminLogin() throws NoLoginException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_admin_login_key) == null
				|| Configurations.string_nologin.equals(session.getAttribute(Configurations.session_admin_login_key)))
			throw new NoLoginException("管理员用户没有登录");
	}
	
	private void checkPermission() throws PermissionDeniedException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_user_authorization_key) == null
				|| Configurations.string_authorization_fail.equals(session.getAttribute(Configurations.session_user_authorization_key)))
			throw new PermissionDeniedException("没有该操作的授权");
	}
	
}
