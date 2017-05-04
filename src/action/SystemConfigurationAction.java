package action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.PlatformService;
import service.RoleService;
import util.StringUtil;
import bean.TransferResultInfo;
import bean.TransferRoleInfo;
import bean.TransferSystemConfigurationInfo;

import com.google.gson.Gson;

import cache.Configurations;
import cache.ResultCodeStorage;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.PermissionDeniedException;

public class SystemConfigurationAction extends BaseAction implements
		ServletResponseAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1427594506560854784L;
	private static final int get = 0;
	private static final int set = 1;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String chatsystempath;
	private String creditvaluechangeforcreator;
	private String creditvaluechangeforsolver;
	private String defaultusercreditvalue;
	private String defaultuserroleuid;
	
	private String w;

	public String getChatsystempath() {
		return chatsystempath;
	}

	public void setChatsystempath(String chatsystempath) {
		this.chatsystempath = chatsystempath;
	}

	public String getCreditvaluechangeforcreator() {
		return creditvaluechangeforcreator;
	}

	public void setCreditvaluechangeforcreator(String creditvaluechangeforcreator) {
		this.creditvaluechangeforcreator = creditvaluechangeforcreator;
	}

	public String getCreditvaluechangeforsolver() {
		return creditvaluechangeforsolver;
	}

	public void setCreditvaluechangeforsolver(String creditvaluechangeforsolver) {
		this.creditvaluechangeforsolver = creditvaluechangeforsolver;
	}

	public String getDefaultusercreditvalue() {
		return defaultusercreditvalue;
	}

	public void setDefaultusercreditvalue(String defaultusercreditvalue) {
		this.defaultusercreditvalue = defaultusercreditvalue;
	}

	public String getDefaultuserroleuid() {
		return defaultuserroleuid;
	}

	public void setDefaultuserroleuid(String defaultuserroleuid) {
		this.defaultuserroleuid = defaultuserroleuid;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
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
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void get() {
		operations(get);
	}
	
	public void set() {
		operations(set);
	}
	
	private void operations(int op) {
		try {
			checkAdminLogin();
			checkPermission();
			switch (op) {
			case set: {
				switch(w) {
				case Configurations.action_sysconf_which_chat_system_set: {
					if (chatsystempath == null || "".equals(chatsystempath))
						throw new IllegalParameterException("chatsystempath 参数无效");
					
					Configurations.setChatSystemPath(chatsystempath);
					PlatformService.writeConfigToFile(System.getProperty(Configurations.config_platform_property_key), Configurations.config_platform_property_file_name);
					
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_success);
					rs.setMsgCode(ResultCodeStorage.code_success);
					rs.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_success));
					sendMsgtoWeb(rs);
				}
				break;
				case Configurations.action_sysconf_which_role_set: {
					if (defaultuserroleuid == null || "".equals(defaultuserroleuid))
						throw new IllegalParameterException("defaultuserroleuid 参数无效");
					roleService.init();
					roleService.setParameters(RoleService.set_uid, defaultuserroleuid);
					TransferResultInfo<?> rs_role = roleService.find();
					if (!ResultCodeStorage.type_success.equals(rs_role.getMsgType())) {
						sendMsgtoWeb(rs_role);
						return;
					}
					@SuppressWarnings("unchecked")
					List<TransferRoleInfo> list_role = (List<TransferRoleInfo>) rs_role.getMsgContent();
					if (list_role.size() <= 0)
						throw new IllegalParameterException("defaultuserroleuid 参数无效");
					
					Configurations.setDefaultUserRoleUid(defaultuserroleuid);
					PlatformService.writeConfigToFile(System.getProperty(Configurations.config_platform_property_key), Configurations.config_platform_property_file_name);
					
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_success);
					rs.setMsgCode(ResultCodeStorage.code_success);
					rs.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_success));
					sendMsgtoWeb(rs);
				}
				break;
				case Configurations.action_sysconf_which_credit_value_set: {
					if (creditvaluechangeforcreator == null || "".equals(creditvaluechangeforcreator))
						throw new IllegalParameterException("creditvaluechangeforcreator 参数无效");
					if (creditvaluechangeforsolver == null || "".equals(creditvaluechangeforsolver))
						throw new IllegalParameterException("creditvaluechangeforsolver 参数无效");
					if (defaultusercreditvalue == null || "".equals(defaultusercreditvalue))
						throw new IllegalParameterException("defaultusercreditvalue 参数无效");
					try {
						int t = Integer.parseInt(creditvaluechangeforcreator);
						if (t < 0)
							throw new IllegalParameterException("creditvaluechangeforcreator 参数的值无效");
					} catch (NumberFormatException e) {
						// TODO: handle exception
						throw new IllegalParameterException("creditvaluechangeforsolver 参数无效");
					}
					try {
						int t = Integer.parseInt(creditvaluechangeforsolver);
						if (t < 0)
							throw new IllegalParameterException("creditvaluechangeforsolver 参数的值无效");
					} catch (NumberFormatException e) {
						// TODO: handle exception
						throw new IllegalParameterException("creditvaluechangeforsolver 参数无效");
					}
					try {
						int t = Integer.parseInt(defaultusercreditvalue);
						if (t < 0)
							throw new IllegalParameterException("defaultusercreditvalue 参数的值无效");
					} catch (NumberFormatException e) {
						// TODO: handle exception
						throw new IllegalParameterException("defaultusercreditvalue 参数无效");
					}
					Configurations.setCreditValueChangeForCreator(Integer.parseInt(creditvaluechangeforcreator));
					Configurations.setCreditValueChangeForSolver(Integer.parseInt(creditvaluechangeforsolver));
					Configurations.setDefaultUserCreditValue(Integer.parseInt(defaultusercreditvalue));
					PlatformService.writeConfigToFile(System.getProperty(Configurations.config_platform_property_key), Configurations.config_platform_property_file_name);
					
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_success);
					rs.setMsgCode(ResultCodeStorage.code_success);
					rs.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_success));
					sendMsgtoWeb(rs);
				}
				break;
				default:
					throw new IllegalParameterException("w 参数无效");
				}
			}
			break;
			case get:
			default: {
				TransferResultInfo<TransferSystemConfigurationInfo> rs = new TransferResultInfo<TransferSystemConfigurationInfo>();
				TransferSystemConfigurationInfo sysConf = new TransferSystemConfigurationInfo();
				sysConf.setChatsystempath(Configurations.getChatSystemPath());
				sysConf.setDefaultusercreditvalue(Configurations.getDefaultUserCreditValue());
				sysConf.setDefaultuserroleuid(Configurations.getDefaultUserRoleUid());
				sysConf.setCreditvaluechangeforcreator(Configurations.getCreditValueChangeForCreator());
				sysConf.setCreditvaluechangeforsolver(Configurations.getCreditValueChangeForSolver());
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(sysConf);
				sendMsgtoWeb(rs);
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
		} catch (NoLoginException e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_no_login);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_no_login, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (PermissionDeniedException e) {
			// TODO: handle exception
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
				|| Configurations.interceptor_string_nologin.equals(session.getAttribute(Configurations.session_admin_login_key)))
			throw new NoLoginException("管理员没有登录");
	}
	
	private void checkPermission() throws PermissionDeniedException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_authorization_key) == null
				|| Configurations.interceptor_string_authorization_fail.equals(session.getAttribute(Configurations.session_authorization_key)))
			throw new PermissionDeniedException("没有该操作的授权");
	}

}
