package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.CreditvaluelogService;
import util.StringUtil;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferResultInfo;

import com.google.gson.Gson;

import cache.Configurations;
import cache.ResultCodeStorage;
import exception.NoLoginException;

public class CreditvaluelogAction extends BaseAction implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2583742834256816382L;
	
	private static final int get = 0;
	
	private String uid;
	private String useruid;
	private String changevalue;
	private String finalvalue;
	private String reason;
	private String createtime;
	private String ui;
	
	private HttpServletRequest request;
	private HttpServletResponse response;

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

	public String getChangevalue() {
		return changevalue;
	}

	public void setChangevalue(String changevalue) {
		this.changevalue = changevalue;
	}

	public String getFinalvalue() {
		return finalvalue;
	}

	public void setFinalvalue(String finalvalue) {
		this.finalvalue = finalvalue;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
	
	public void get() {
		operations(get);
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
			switch(ui) {
			case Configurations.action_general_UI_ADMIN: {
				creditvaluelogService.initParameters();
				if (uid != null && !"".equals(uid))
					creditvaluelogService.setParameters(CreditvaluelogService.set_userUid, uid);
				if (useruid != null && !"".equals(useruid))
					creditvaluelogService.setParameters(CreditvaluelogService.set_userUid, useruid);
				if (changevalue != null && !"".equals(changevalue))
					creditvaluelogService.setParameters(CreditvaluelogService.set_changeValue, changevalue);
				if (finalvalue != null && !"".equals(finalvalue))
					creditvaluelogService.setParameters(CreditvaluelogService.set_finalValue, finalvalue);
				if (reason != null && !"".equals(reason))
					creditvaluelogService.setParameters(CreditvaluelogService.set_reason, reason);
				if (createtime != null && !"".equals(createtime))
					creditvaluelogService.setParameters(CreditvaluelogService.set_createTime, createtime);
				TransferResultInfo<?> rs = creditvaluelogService.find();
				sendMsgtoWeb(rs);
			}
			break;
			case Configurations.action_general_UI_USER:
			default: {
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				creditvaluelogService.initParameters();
				creditvaluelogService.setParameters(CreditvaluelogService.set_userUid, user.getUser().getUid());
				TransferResultInfo<?> rs = creditvaluelogService.find();
				sendMsgtoWeb(rs);
			}
			}
		}  catch (NoLoginException e) {
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
