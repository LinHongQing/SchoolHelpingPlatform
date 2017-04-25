package action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import util.StringUtil;

import com.google.gson.Gson;

import bean.TransferApiUserInfo;
import bean.TransferResultInfo;
import bean.TransferUserInfo;
import cache.Configurations;
import cache.PlatformOnlineUserStorage;
import cache.PlatformUnreadChatInfoStorage;
import cache.ResultCodeStorage;
import dao.UserService;
import exception.IllegalParameterException;

public class SystemApisAction extends BaseAction implements
		ServletResponseAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3157704977868503249L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String which;
	private String uid;
	private String from;
	private String to;
	private String count;

	public String getWhich() {
		return which;
	}

	public void setWhich(String which) {
		this.which = which;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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
	
	public void doRun() {
		try {
			if (which == null || "".equals(which))
				throw new IllegalParameterException("which 参数不能为空");
			switch(which) {
			case Configurations.action_apis_which_get_users_info: {
				if (from == null || "".equals(from))
					throw new IllegalParameterException("from 参数不能为空");
				if (to == null || "".equals(to))
					throw new IllegalParameterException("to 参数不能为空");
				userService.initParameters();
				userService.setParameters(UserService.set_uid, from);
				TransferResultInfo<?> rs_from = userService.find(UserService.findMode_summary);
				if (ResultCodeStorage.type_error.equals(rs_from.getMsgType())) {
					sendMsgtoWeb(rs_from);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferUserInfo> list_from = (List<TransferUserInfo>) rs_from.getMsgContent();
				TransferApiUserInfo from = new TransferApiUserInfo();
				for (TransferUserInfo transferUserInfo : list_from) {
					from.setUsrUid(transferUserInfo.getUid());
					from.setUsrNickname(transferUserInfo.getNickname() == null ? "" : transferUserInfo.getNickname());
					from.setUsrImgResourcePath("");
				}
				userService.initParameters();
				userService.setParameters(UserService.set_uid, to);
				TransferResultInfo<?> rs_to = userService.find(UserService.findMode_summary);
				if (ResultCodeStorage.type_error.equals(rs_to.getMsgType())) {
					sendMsgtoWeb(rs_to);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferUserInfo> list_to = (List<TransferUserInfo>) rs_to.getMsgContent();
				TransferApiUserInfo to = new TransferApiUserInfo();
				for (TransferUserInfo transferUserInfo : list_to) {
					to.setUsrUid(transferUserInfo.getUid());
					to.setUsrNickname(transferUserInfo.getNickname() == null ? "" : transferUserInfo.getNickname());
					to.setUsrImgResourcePath("");
				}
				Map<String, TransferApiUserInfo> rs_final_content = new HashMap<String, TransferApiUserInfo>();
				rs_final_content.put("from", from);
				rs_final_content.put("to", to);
				TransferResultInfo<Map<String, TransferApiUserInfo>> rs_final = new TransferResultInfo<Map<String,TransferApiUserInfo>>();
				rs_final.setMsgType(ResultCodeStorage.type_success);
				rs_final.setMsgCode(ResultCodeStorage.code_success);
				rs_final.setMsgContent(rs_final_content);
				sendMsgtoWeb(rs_final);
			}
			break;
			case Configurations.action_apis_which_check_user_access: {
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				if (PlatformOnlineUserStorage.getOnlineUserHttpSession(uid) == null) {
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_error);
					rs.setMsgContent("无该用户登录信息");
					sendMsgtoWeb(rs);
				} else {
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_success);
					rs.setMsgContent("有该用户登录信息");
					sendMsgtoWeb(rs);
				}
			}
			break;
			case Configurations.action_apis_which_notify_user_unread_message: {
				if (from == null || "".equals(from))
					throw new IllegalParameterException("from 参数不能为空");
				if (to == null || "".equals(to))
					throw new IllegalParameterException("to 参数不能为空");
				if (count == null || "".equals(count))
					throw new IllegalParameterException("count 参数不能为空");
				try {
					Integer.parseInt(count);
				} catch (NumberFormatException e) {
					throw new IllegalParameterException("count 参数无效");
				}
				PlatformUnreadChatInfoStorage.setUnreadChatInfoCount(to, from, Integer.parseInt(count));
				TransferResultInfo<String> rs = new TransferResultInfo<String>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgContent("OK");
				sendMsgtoWeb(rs);
			}
			break;
			default:
				throw new IllegalParameterException("which 参数无效");
			}
		} catch (IllegalParameterException e) {
			// TODO: handle exception
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
