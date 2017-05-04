package action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.RoleService;
import util.DbUidGeneratorUtil;
import util.StringUtil;
import util.TimeUtil;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferResultInfo;
import cache.Configurations;
import cache.PrivilegeCodeRequestPathStorage;
import cache.ResultCodeStorage;

import com.google.gson.Gson;

import exception.IllegalOperationException;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.PermissionDeniedException;

public class RoleAction extends BaseAction implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3985187610589169767L;
	
	private static final int save = 0;
	private static final int update = 1;
	private static final int delete = 2;
	private static final int get = 3;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;
	private String name;
	private List<String> rolecode;
	private String rolevalue;
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

	public List<String> getRolecode() {
		return rolecode;
	}

	public void setRolecode(List<String> rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolevalue() {
		return rolevalue;
	}

	public void setRolevalue(String rolevalue) {
		this.rolevalue = rolevalue;
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
			checkAdminLogin();
			checkPermission();
			
			roleService.initParameters();
			
			if (uid != null && !"".equals(uid))
				roleService.setParameters(RoleService.set_uid, uid);
			if (name != null && !"".equals(name))
				roleService.setParameters(RoleService.set_name, name);
			if (rolecode != null && !"".equals(rolecode))
				roleService.setParameters(RoleService.set_roleCode, PrivilegeCodeRequestPathStorage.formatPrivilegeListToString(rolecode));
			if (rolevalue != null && !"".equals(rolevalue))
				roleService.setParameters(RoleService.set_roleValue, rolevalue);
			if (createuseruid != null && !"".equals(createuseruid))
				roleService.setParameters(RoleService.set_createUserUid, createuseruid);
			if (createtime != null && !"".equals(createtime))
				roleService.setParameters(RoleService.set_createTime, createtime);
			if (createip != null && !"".equals(createip))
				roleService.setParameters(RoleService.set_createTime, createip);
			
			switch (op) {
			case save: {
				if (name == null || "".equals(name))
					throw new IllegalParameterException("name 参数不能为空");
				if (rolecode == null || rolecode.size() <= 0)
					throw new IllegalParameterException("rolecode 参数不能为空");
				if (rolevalue == null || "".equals(rolevalue))
					throw new IllegalParameterException("rolevalue 参数不能为空");
				try {
					int int_rolevalue = Integer.valueOf(rolevalue);
					if (int_rolevalue < 0 || int_rolevalue > 100)
						throw new IllegalParameterException("rolevalue 参数无效");
				} catch (NumberFormatException e) {
					throw new IllegalParameterException("rolevalue 参数无效");
				}
				TransferOnlineUserBasicInfo admin = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				roleService.setParameters(RoleService.set_uid, DbUidGeneratorUtil.generateRoleUid());
				roleService.setParameters(RoleService.set_createUserUid, admin.getAdmin().getUid());
				roleService.setParameters(RoleService.set_createIp, request.getRemoteAddr());
				roleService.setParameters(RoleService.set_createTime, TimeUtil.getNowTimeStamp());
				TransferResultInfo<?> rs = roleService.insert();
				sendMsgtoWeb(rs);
			}
			break;
			case update: {
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				if (rolevalue != null && !"".equals(rolevalue)) {
					try {
						Integer.valueOf(rolevalue);
					} catch (NumberFormatException e) {
						throw new IllegalParameterException("rolevalue 参数无效");
					}
				}
				TransferResultInfo<?> rs = roleService.update();
				sendMsgtoWeb(rs);
			}
			break;
			case delete: {
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				if (Configurations.getDefaultUserRoleUid().equals(uid))
					throw new IllegalOperationException("该权限无法删除");
				TransferResultInfo<?> rs = roleService.delete();
				sendMsgtoWeb(rs);
			}
			break;
			case get: {
				TransferResultInfo<?> rs = roleService.find();
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
