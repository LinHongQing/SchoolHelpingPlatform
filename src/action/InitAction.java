package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.CourseService;
import util.StringUtil;
import cache.PrivilegeCodeRequestPathStorage;
import cache.ResultCodeStorage;

import com.google.gson.Gson;

import cache.Configurations;
import bean.TransferPrivilegeCodeInfo;
import bean.TransferResultInfo;
import exception.IllegalParameterException;

public class InitAction extends BaseAction implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5989386651884634559L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String t;	// 表
	private String uid;	// 通用 id
	
	
	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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
	
	public void init() {
		try {
			if (t == null || "".equals(t))
				throw new IllegalParameterException("t 参数不能为空");
			switch(t) {
			case Configurations.action_init_academy: {
				academyService.initParameters();
				TransferResultInfo<?> rs = academyService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_course: {
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				courseService.initParameters();
				courseService.setParameters(CourseService.set_academyUid, uid);
				TransferResultInfo<?> rs = courseService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_grade: {
				gradeService.initParameters();
				TransferResultInfo<?> rs = gradeService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_term: {
				termService.initParameters();
				TransferResultInfo<?> rs = termService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_location: {
				locationService.initParameters();
				TransferResultInfo<?> rs = locationService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_role: {
				roleService.initParameters();
				TransferResultInfo<?> rs = roleService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_privilege: {
				privilegeService.initParameters();
				TransferResultInfo<?> rs = privilegeService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_problemType: {
				problemtypeService.initParameters();
				TransferResultInfo<?> rs = problemtypeService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_qualificationType: {
				qualificationtypeService.initParameters();
				TransferResultInfo<?> rs = qualificationtypeService.init();
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_role_code: {
				List<TransferPrivilegeCodeInfo> rs_content = new ArrayList<TransferPrivilegeCodeInfo>();
				for (Integer value : PrivilegeCodeRequestPathStorage.users_description.keySet()) {
					TransferPrivilegeCodeInfo t = new TransferPrivilegeCodeInfo();
					t.setValue(value);
					t.setDescription(PrivilegeCodeRequestPathStorage.users_description.get(value));
					rs_content.add(t);
				}
				TransferResultInfo<List<TransferPrivilegeCodeInfo>> rs = new TransferResultInfo<List<TransferPrivilegeCodeInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(rs_content);
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_privilege_code: {
				List<TransferPrivilegeCodeInfo> rs_content = new ArrayList<TransferPrivilegeCodeInfo>();
				for (Integer value : PrivilegeCodeRequestPathStorage.admins_description.keySet()) {
					TransferPrivilegeCodeInfo t = new TransferPrivilegeCodeInfo();
					t.setValue(value);
					t.setDescription(PrivilegeCodeRequestPathStorage.admins_description.get(value));
					rs_content.add(t);
				}
				TransferResultInfo<List<TransferPrivilegeCodeInfo>> rs = new TransferResultInfo<List<TransferPrivilegeCodeInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(rs_content);
				sendMsgtoWeb(rs);
				return;
			}
			case Configurations.action_init_chat_system_path: {
				TransferResultInfo<String> rs = new TransferResultInfo<String>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(Configurations.string_chat_system_path);
				sendMsgtoWeb(rs);
				return;
			}
			default:
				throw new IllegalParameterException("t 参数无效");
			}
		} catch (IllegalParameterException e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_invalid_parameter);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_parameter, e.getMessage()));
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

}
