package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;

import service.LocationService;
import util.DbUidGeneratorUtil;
import util.StringUtil;
import util.TimeUtil;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferResultInfo;
import cache.Configurations;
import cache.ResultCodeStorage;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.PermissionDeniedException;

public class LocationAction extends BaseAction implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -381740214326425462L;
	
	private static final int save = 0;
	private static final int update = 1;
	private static final int delete = 2;
	private static final int get = 3;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;
	private String name;
	private String longitude;
	private String latitude;
	private String createuseruid;
	private String createtime;
	private String createip;

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

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCreateuseruid() {
		return createuseruid;
	}

	public void setCreateuseruid(String createuseruid) {
		this.createuseruid = createuseruid;
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
	
	private void operations(int op) {
		try {
			locationService.initParameters();
			if (uid != null && !"".equals(uid))
				locationService.setParameters(LocationService.set_uid, uid);
			if (name != null && !"".equals(name))
				locationService.setParameters(LocationService.set_name, name);
			if (longitude != null && !"".equals(longitude))
				locationService.setParameters(LocationService.set_longitude, longitude);
			if (latitude != null && !"".equals(latitude))
				locationService.setParameters(LocationService.set_latitude, latitude);
			if (createuseruid != null && !"".equals(createuseruid))
				locationService.setParameters(LocationService.set_createUserUid, createuseruid);
			if (createtime != null && !"".equals(createtime))
				locationService.setParameters(LocationService.set_createTime, createtime);
			if (createip != null && !"".equals(createip))
				locationService.setParameters(LocationService.set_createIp, createip);
			
			switch (op) {
			case save: {
				checkAdminLogin();
				checkPermission();
				if (name == null || "".equals(name))
					throw new IllegalParameterException("name 参数不能为空");
				try {
					Float.parseFloat(latitude);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					throw new IllegalParameterException("latitude 参数无效");
				}
				try {
					Float.parseFloat(longitude);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					throw new IllegalParameterException("longitude 参数无效");
				}
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				locationService.setParameters(LocationService.set_uid, DbUidGeneratorUtil.generateLocationUid());
				locationService.setParameters(LocationService.set_createUserUid, user.getAdmin().getUid());
				locationService.setParameters(LocationService.set_createTime, TimeUtil.getNowTimeStamp());
				locationService.setParameters(LocationService.set_createIp, request.getRemoteAddr());
				TransferResultInfo<?> rs = locationService.insert();
				sendMsgtoWeb(rs);
			}
			break;
			case update: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = locationService.update();
				sendMsgtoWeb(rs);
			}
			break;
			case delete: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = locationService.delete();
				sendMsgtoWeb(rs);
			}
			break;
			case get: {
				checkAdminLogin();
				checkPermission();
				TransferResultInfo<?> rs = locationService.find();
				sendMsgtoWeb(rs);
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
