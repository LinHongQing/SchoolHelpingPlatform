package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.CourseService;
import util.DbUidGeneratorUtil;
import util.StringUtil;
import util.TimeUtil;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferResultInfo;

import com.google.gson.Gson;

import cache.Configurations;
import cache.ResultCodeStorage;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.PermissionDeniedException;

public class CourseAction extends BaseAction implements ServletRequestAware,
		ServletResponseAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8994138836681120832L;
	
	private static final int save = 0;
	private static final int update = 1;
	private static final int delete = 2;
	private static final int get = 3;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;
	private String name;
	private String academyuid;
	private String createuseruid;
	private String createip;
	private String createtime;

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

	public String getAcademyuid() {
		return academyuid;
	}

	public void setAcademyuid(String academyuid) {
		this.academyuid = academyuid;
	}

	public String getCreateuseruid() {
		return createuseruid;
	}

	public void setCreateuseruid(String createuseruid) {
		this.createuseruid = createuseruid;
	}

	public String getCreateip() {
		return createip;
	}

	public void setCreateip(String createip) {
		this.createip = createip;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
			courseService.initParameters();
			if (uid != null && !"".equals(uid))
				courseService.setParameters(CourseService.set_uid, uid);
			if (name != null && !"".equals(name))
				courseService.setParameters(CourseService.set_name, name);
			if (academyuid != null && !"".equals(academyuid))
				courseService.setParameters(CourseService.set_academyUid, academyuid);
			if (createuseruid != null && !"".equals(createuseruid))
				courseService.setParameters(CourseService.set_createUserUid, createuseruid);
			if (createip != null && !"".equals(createip))
				courseService.setParameters(CourseService.set_createIp, createip);
			if (createtime != null && !"".equals(createtime))
				courseService.setParameters(CourseService.set_createTime, createtime);
			
			switch (op) {
			case save: {
				checkAdminLogin();
				checkPermission();
				if (academyuid == null || "".equals(academyuid))
					throw new IllegalParameterException("academyuid 参数不能为空");
				if (name == null || "".equals(name))
					throw new IllegalParameterException("name 参数不能为空");
				TransferOnlineUserBasicInfo admin = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				courseService.setParameters(CourseService.set_uid, DbUidGeneratorUtil.generateCourseUid());
				courseService.setParameters(CourseService.set_createIp, request.getRemoteAddr());
				courseService.setParameters(CourseService.set_createUserUid, admin.getAdmin().getUid());
				courseService.setParameters(CourseService.set_createTime, TimeUtil.getNowTimeStamp());
				TransferResultInfo<?> rs = courseService.insert();
				sendMsgtoWeb(rs);
			}
			break;
			case update: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = courseService.update();
				sendMsgtoWeb(rs);
			}
			break;
			case delete: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = courseService.delete();
				sendMsgtoWeb(rs);
			}
			break;
			case get: {
				checkAdminLogin();
				checkPermission();
				TransferResultInfo<?> rs = courseService.find();
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
	
	private void checkAdminLogin() throws NoLoginException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_admin_login_key) == null
				|| Configurations.string_nologin.equals(session.getAttribute(Configurations.session_admin_login_key))) {
			throw new NoLoginException("管理员没有登录");
		}
	}
	
	private void checkPermission() throws PermissionDeniedException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_user_authorization_key) == null
				|| Configurations.string_authorization_fail.equals(session.getAttribute(Configurations.session_user_authorization_key))) {
			throw new PermissionDeniedException("没有该操作的授权");
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
	
}
