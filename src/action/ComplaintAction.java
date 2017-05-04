package action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.ComplaintrequestService;
import service.ProblemService;
import util.DbUidGeneratorUtil;
import util.StringUtil;
import util.TimeUtil;
import bean.TransferComplaintrequestInfo;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferProblemInfo;
import bean.TransferResultInfo;

import com.google.gson.Gson;

import cache.Configurations;
import cache.PlatformStatistics;
import cache.ResultCodeStorage;
import exception.IllegalOperationException;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.PermissionDeniedException;

public class ComplaintAction extends BaseAction implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 643647643183889134L;
	
	private static final int op_request = 0;
	private static final int op_process = 1;
	private static final int op_finish = 2;
	private static final int op_get = 3;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;
	private String useruid;
	private String createtime;
	private String createip;
	private String problemuid;
	private String description;
	private String resourceuid;
	private String status;
	private String replydescription;
	private String replyresourceuid;
	private String replycreateuseruid;
	private String replycreatetime;
	private String replycreateip;
	
	private String ui;
	private String type;

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

	public String getProblemuid() {
		return problemuid;
	}

	public void setProblemuid(String problemuid) {
		this.problemuid = problemuid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceuid() {
		return resourceuid;
	}

	public void setResourceuid(String resourceuid) {
		this.resourceuid = resourceuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReplydescription() {
		return replydescription;
	}

	public void setReplydescription(String replydescription) {
		this.replydescription = replydescription;
	}

	public String getReplyresourceuid() {
		return replyresourceuid;
	}

	public void setReplyresourceuid(String replyresourceuid) {
		this.replyresourceuid = replyresourceuid;
	}

	public String getReplycreateuseruid() {
		return replycreateuseruid;
	}

	public void setReplycreateuseruid(String replycreateuseruid) {
		this.replycreateuseruid = replycreateuseruid;
	}

	public String getReplycreatetime() {
		return replycreatetime;
	}

	public void setReplycreatetime(String replycreatetime) {
		this.replycreatetime = replycreatetime;
	}

	public String getReplycreateip() {
		return replycreateip;
	}

	public void setReplycreateip(String replycreateip) {
		this.replycreateip = replycreateip;
	}

	public String getUi() {
		return ui;
	}

	public void setUi(String ui) {
		this.ui = ui;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public void request() {
		operations(op_request);
	}
	
	public void get() {
		operations(op_get);
	}
	
	public void process() {
		operations(op_process);
	}
	
	public void finish() {
		operations(op_finish);
	}
	
	private void operations(int op) {
		try {
			complaintrequestService.initParameters();
			if (uid != null && !"".equals(uid))
				complaintrequestService.setParameters(ComplaintrequestService.set_uid, uid);
			if (useruid != null && !"".equals(useruid))
				complaintrequestService.setParameters(ComplaintrequestService.set_userUid, useruid);
			if (createtime != null && !"".equals(createtime))
				complaintrequestService.setParameters(ComplaintrequestService.set_createTime, createtime);
			if (createip != null && !"".equals(createip))
				complaintrequestService.setParameters(ComplaintrequestService.set_createIp, createip);
			if (problemuid != null && !"".equals(problemuid))
				complaintrequestService.setParameters(ComplaintrequestService.set_problemUid, problemuid);
			if (description != null && !"".equals(description))
				complaintrequestService.setParameters(ComplaintrequestService.set_description, description);
			if (resourceuid != null && !"".equals(resourceuid))
				complaintrequestService.setParameters(ComplaintrequestService.set_resourceUid, resourceuid);
			if (status != null && !"".equals(status))
				complaintrequestService.setParameters(ComplaintrequestService.set_status, status);
			if (replydescription != null && !"".equals(replydescription))
				complaintrequestService.setParameters(ComplaintrequestService.set_replyDescription, replydescription);
			if (replyresourceuid != null && !"".equals(replyresourceuid))
				complaintrequestService.setParameters(ComplaintrequestService.set_replyResourceUid, replyresourceuid);
			if (replycreateuseruid != null && !"".equals(replycreateuseruid))
				complaintrequestService.setParameters(ComplaintrequestService.set_replyCreateUserUid, replycreateuseruid);
			if (replycreatetime != null && !"".equals(replycreatetime))
				complaintrequestService.setParameters(ComplaintrequestService.set_replyCreateTime, replycreatetime);
			if (replycreateip != null && !"".equals(replycreateip))
				complaintrequestService.setParameters(ComplaintrequestService.set_createIp, replycreateip);
			if (replyresourceuid != null && !"".equals(replyresourceuid))
				complaintrequestService.setParameters(ComplaintrequestService.set_uid, replyresourceuid);
			if (ui == null || "".equals(ui))
				ui = Configurations.action_general_UI_USER;
			
			switch(op) {
			case op_request: {
				checkUserLogin();
				checkPermission();
				if (description == null || "".equals(description))
					throw new IllegalParameterException("description 参数不能为空");
				if (problemuid == null || "".equals(problemuid))
					throw new IllegalParameterException("problemuid 参数不能为空");
				problemService.initParameters();
				problemService.setParameters(ProblemService.set_uid, problemuid);
				TransferResultInfo<?> rs_problem = problemService.find(ProblemService.find_summary);
				if (!ResultCodeStorage.type_success.equals(rs_problem.getMsgType())) {
					sendMsgtoWeb(rs_problem);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs_problem.getMsgContent();
				if (list_problem.size() <= 0) {
					throw new IllegalParameterException("problemuid 参数无效");
				}
				problemService.setParameters(ProblemService.set_status, String.valueOf(Configurations.db_problem_status_lock));
				rs_problem = problemService.update();
				if (!ResultCodeStorage.type_success.equals(rs_problem.getMsgType())) {
					sendMsgtoWeb(rs_problem);
					return;
				}
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				complaintrequestService.setParameters(ComplaintrequestService.set_uid,
						DbUidGeneratorUtil.generateComplaintrequestUid(PlatformStatistics.getTodayComplaintRequestCount()));
				complaintrequestService.setParameters(ComplaintrequestService.set_userUid, user.getUser().getUid());
				complaintrequestService.setParameters(ComplaintrequestService.set_createIp, request.getRemoteAddr());
				complaintrequestService.setParameters(ComplaintrequestService.set_createTime, TimeUtil.getNowTimeStamp());
				complaintrequestService.setParameters(ComplaintrequestService.set_status, String.valueOf(Configurations.db_complaintrequest_status_waiting));
				TransferResultInfo<?> rs = complaintrequestService.insert();
				sendMsgtoWeb(rs);
				PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayComplaintRequest);
			}
			break;
			case op_process: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferOnlineUserBasicInfo admin = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				complaintrequestService.initParameters();
				complaintrequestService.setParameters(ComplaintrequestService.set_uid, uid);
				TransferResultInfo<?> rs = complaintrequestService.find(ComplaintrequestService.find_summary);
				if (rs.getMsgType().equals(ResultCodeStorage.type_success)) {
					@SuppressWarnings("unchecked")
					List<TransferComplaintrequestInfo> list_complaint = (List<TransferComplaintrequestInfo>) rs.getMsgContent();
					int complaintStatus = Configurations.int_invalid;
					for (TransferComplaintrequestInfo transferComplaintrequestInfo : list_complaint) {
						complaintStatus = transferComplaintrequestInfo.getStatus();
						break;
					}
					if (complaintStatus == Configurations.db_complaintrequest_status_waiting) {
						complaintrequestService.initParameters();
						complaintrequestService.setParameters(ComplaintrequestService.set_uid, uid);
						complaintrequestService.setParameters(ComplaintrequestService.set_status, String.valueOf(Configurations.db_complaintrequest_status_processing));
						complaintrequestService.setParameters(ComplaintrequestService.set_replyCreateUserUid, admin.getAdmin().getUid());
						rs = complaintrequestService.update();
						sendMsgtoWeb(rs);
					} else {
						throw new IllegalOperationException("投诉当前状态不能执行此操作");
					}
				} else {
					sendMsgtoWeb(rs);
				}
			}
			break;
			case op_finish: {
				checkAdminLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				if (replydescription == null || "".equals(replydescription))
					throw new IllegalParameterException("replydescription 参数不能为空");
				TransferOnlineUserBasicInfo admin = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				complaintrequestService.initParameters();
				complaintrequestService.setParameters(ComplaintrequestService.set_uid, uid);
				TransferResultInfo<?> rs = complaintrequestService.find(ComplaintrequestService.find_summary);
				if (rs.getMsgType().equals(ResultCodeStorage.type_success)) {
					@SuppressWarnings("unchecked")
					List<TransferComplaintrequestInfo> list_complaint = (List<TransferComplaintrequestInfo>) rs.getMsgContent();
					int complaintStatus = Configurations.int_invalid;
					String replyCreateUserUid = "";
					for (TransferComplaintrequestInfo transferComplaintrequestInfo : list_complaint) {
						complaintStatus = transferComplaintrequestInfo.getStatus();
						replyCreateUserUid = transferComplaintrequestInfo.getReplycreateuser().getUid();
						break;
					}
					if (complaintStatus == Configurations.db_complaintrequest_status_waiting
							|| complaintStatus == Configurations.db_complaintrequest_status_complete) {
						throw new IllegalOperationException("不能在别人处理的投诉单上执行此操作");
					} else {
						if (admin.getAdmin().getUid().equals(replyCreateUserUid)) {
							complaintrequestService.initParameters();
							complaintrequestService.setParameters(ComplaintrequestService.set_uid, uid);
							complaintrequestService.setParameters(ComplaintrequestService.set_status, String.valueOf(Configurations.db_complaintrequest_status_complete));
							complaintrequestService.setParameters(ComplaintrequestService.set_replyCreateTime, TimeUtil.getNowTimeStamp());
							complaintrequestService.setParameters(ComplaintrequestService.set_replyCreateIp, request.getRemoteAddr());
							complaintrequestService.setParameters(ComplaintrequestService.set_replyDescription, replydescription);
							rs = complaintrequestService.update();
							sendMsgtoWeb(rs);
							PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayComplaintReply);
						} else {
							throw new IllegalOperationException("投诉当前状态不能执行此操作");
						}
					}
				} else {
					sendMsgtoWeb(rs);
				}
			}
			break;
			case op_get: {
				if (type == null || "".equals(type))
					type = Configurations.action_complaint_type_get_summary;
				switch (ui) {
				case Configurations.action_general_UI_ADMIN: {
					switch(type) {
					case Configurations.action_complaint_type_get_detail: {
						if (uid == null || "".equals(uid))
							throw new IllegalParameterException("uid 参数不能为空");
						TransferResultInfo<?> rs = complaintrequestService.find(ComplaintrequestService.find_detail);
						sendMsgtoWeb(rs);
					}
					break;
					case Configurations.action_complaint_type_get_summary:
					default: {
						TransferResultInfo<?> rs = complaintrequestService.find(ComplaintrequestService.find_summary);
						sendMsgtoWeb(rs);
					}
					break;
					}
				}
				break;
				case Configurations.action_general_UI_USER:
				default: {
					TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
					switch(type) {
					case Configurations.action_complaint_type_get_detail: {
						if (uid == null || "".equals(uid))
							throw new IllegalParameterException("uid 参数不能为空");
						complaintrequestService.setParameters(ComplaintrequestService.set_userUid, user.getUser().getUid());
						TransferResultInfo<?> rs = complaintrequestService.find(ComplaintrequestService.find_detail);
						sendMsgtoWeb(rs);
					}
					break;
					case Configurations.action_complaint_type_get_summary:
					default: {
						complaintrequestService.setParameters(ComplaintrequestService.set_userUid, user.getUser().getUid());
						TransferResultInfo<?> rs = complaintrequestService.find(ComplaintrequestService.find_summary);
						sendMsgtoWeb(rs);
					}
					break;
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
			throw new NoLoginException("管理员没有登录");
	}
	
	private void checkPermission() throws PermissionDeniedException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_authorization_key) == null
				|| Configurations.interceptor_string_authorization_fail.equals(session.getAttribute(Configurations.session_authorization_key)))
			throw new PermissionDeniedException("没有该操作的授权");
	}

}
