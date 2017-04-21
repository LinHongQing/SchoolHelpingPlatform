package action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import bean.TransferAdminInfo;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferProblemInfo;
import bean.TransferQualificationrequestInfo;
import bean.TransferQualificationtypeInfo;
import bean.TransferResultInfo;
import bean.TransferSolveInfo;
import bean.TransferUserInfo;

import com.google.gson.Gson;

import service.AdminService;
import service.ProblemService;
import service.QualificationrequestService;
import service.SolveService;
import util.StringUtil;
import cache.Configurations;
import cache.ResultCodeStorage;
import dao.UserService;
import exception.IllegalParameterException;
import exception.NoLoginException;

public class CheckAction extends BaseAction implements ServletResponseAware,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7380060739589317301L;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String t;
	private String uid;
	private String email;
	private String num;
	private String ui;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getUi() {
		return ui;
	}

	public void setUi(String ui) {
		this.ui = ui;
	}

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

	public void check() {
		try {
			if (t == null || "".equals(t))
				throw new IllegalParameterException("t 参数不能为空");
			switch (t) {
			case Configurations.action_check_admin_email: {
				if (email == null || "".equals(email))
					throw new IllegalParameterException("email 参数不能为空");
				adminService.initParameters();
				adminService.setParameters(AdminService.set_email, email);
				TransferResultInfo<?> rs = adminService.find();
				if (!rs.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferAdminInfo> list_admin = (List<TransferAdminInfo>) rs.getMsgContent();
				TransferResultInfo<String> fin = new TransferResultInfo<String>();
				if (list_admin.size() > 0) {
					if (uid != null && !"".equals(uid)) {
						for (TransferAdminInfo transferAdminInfo : list_admin) {
							if (uid.equals(transferAdminInfo.getUid())) {
								System.out.println("email 为用户自己的 email, 有效");
								fin.setMsgType(ResultCodeStorage.type_success);
								fin.setMsgCode(ResultCodeStorage.code_success);
								sendMsgtoWeb(fin);
							} else {
								System.out.println("email 为非用户自己的 email, 无效");
								fin.setMsgType(ResultCodeStorage.type_error);
								fin.setMsgCode(ResultCodeStorage.code_err_invalid_email);
								sendMsgtoWeb(fin);
							}
							break;
						}
					} else {
						System.out.println("email 已存在, 无效");
						fin.setMsgType(ResultCodeStorage.type_error);
						fin.setMsgCode(ResultCodeStorage.code_err_invalid_email);
						sendMsgtoWeb(fin);
					}
				} else {
					System.out.println("email 未存在, 有效");
					fin.setMsgType(ResultCodeStorage.type_success);
					fin.setMsgCode(ResultCodeStorage.code_success);
					sendMsgtoWeb(fin);
				}
			}
			break;
			case Configurations.action_check_user_num_register: {
				if (num == null || "".equals(num))
					throw new IllegalParameterException("num 参数不能为空");
				userService.initParameters();
				userService.setParameters(UserService.set_num, num);
				TransferResultInfo<?> rs = userService.find(UserService.findMode_summary);
				if (!rs.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferUserInfo> list_user = (List<TransferUserInfo>) rs.getMsgContent();
				TransferResultInfo<String> fin = new TransferResultInfo<String>();
				if (list_user.size() > 0) {
					System.out.println("num 已存在, 无效");
					fin.setMsgType(ResultCodeStorage.type_error);
					fin.setMsgCode(ResultCodeStorage.code_err_invalid_num);
					sendMsgtoWeb(fin);
				} else {
					System.out.println("num 未存在, 有效");
					fin.setMsgType(ResultCodeStorage.type_success);
					fin.setMsgCode(ResultCodeStorage.code_success);
					sendMsgtoWeb(fin);
				}
			}
			break;
			case Configurations.action_check_user: {
				if (num == null || "".equals(num))
					throw new IllegalParameterException("num 参数不能为空");
				userService.initParameters();
				userService.setParameters(UserService.set_num, num);
				TransferResultInfo<?> rs = userService.find(UserService.findMode_summary);
				if (!rs.getMsgType().equals(ResultCodeStorage.type_success)) {
					sendMsgtoWeb(rs);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferUserInfo> list_user = (List<TransferUserInfo>) rs.getMsgContent();
				
				if (list_user.size() > 0) {
					System.out.println("num 存在, 有效");
					TransferResultInfo<TransferUserInfo> fin = new TransferResultInfo<TransferUserInfo>();
					fin.setMsgType(ResultCodeStorage.type_success);
					fin.setMsgCode(ResultCodeStorage.code_success);
					for (TransferUserInfo transferUserInfo : list_user) {
						fin.setMsgContent(transferUserInfo);
					}
					sendMsgtoWeb(fin);
				} else {
					System.out.println("num 未存在, 无效");
					TransferResultInfo<String> fin = new TransferResultInfo<String>();
					fin.setMsgType(ResultCodeStorage.type_error);
					fin.setMsgCode(ResultCodeStorage.code_err_invalid_num);
					fin.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_err_invalid_num));
					sendMsgtoWeb(fin);
				}
			}
			break;
			case Configurations.action_check_problem_owner: {
				checkUserLogin();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				problemService.initParameters();
				problemService.setParameters(ProblemService.set_uid, uid);
				TransferResultInfo<?> rs_problem = problemService.find(ProblemService.find_summary);
				if (!ResultCodeStorage.type_success.equals(rs_problem.getMsgType())) {
					sendMsgtoWeb(rs_problem);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs_problem.getMsgContent();
				TransferSolveInfo solve = null;
				for (TransferProblemInfo transferProblemInfo : list_problem) {
					solve = transferProblemInfo.getSolved();
				}
				if (solve == null) {
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_error);
					sendMsgtoWeb(rs);
					return;
				}
				solveService.initParameters();
				solveService.setParameters(SolveService.set_uid, solve.getUid());
				TransferResultInfo<?> rs_solve = solveService.find();
				if (!ResultCodeStorage.type_success.equals(rs_solve.getMsgType())) {
					sendMsgtoWeb(rs_solve);
					return;
				}
				String solveUserUid = null;
				@SuppressWarnings("unchecked")
				List<TransferSolveInfo> list_solve = (List<TransferSolveInfo>) rs_solve.getMsgContent();
				for (TransferSolveInfo transferSolveInfo : list_solve) {
					solveUserUid = transferSolveInfo.getCreateuser().getUid();
				}
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				TransferResultInfo<String> rs_fin = new TransferResultInfo<String>();
				if (user.getUser().getUid().equals(solveUserUid)) {
					rs_fin.setMsgType(ResultCodeStorage.type_success);
					sendMsgtoWeb(rs_fin);
				} else {
					rs_fin.setMsgType(ResultCodeStorage.type_error);
					sendMsgtoWeb(rs_fin);
				}
			}
			break;
			case Configurations.action_check_problem_creator: {
				checkUserLogin();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				problemService.initParameters();
				problemService.setParameters(ProblemService.set_uid, uid);
				TransferResultInfo<?> rs_problem = problemService.find(ProblemService.find_summary);
				if (!ResultCodeStorage.type_success.equals(rs_problem.getMsgType())) {
					sendMsgtoWeb(rs_problem);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs_problem.getMsgContent();
				TransferUserInfo creator = null;
				for (TransferProblemInfo transferProblemInfo : list_problem) {
					creator = transferProblemInfo.getCreateuser();
				}
				if (creator == null) {
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_error);
					sendMsgtoWeb(rs);
					return;
				}
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				if (user.getUser().getUid().equals(creator.getUid())) {
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_success);
					sendMsgtoWeb(rs);
				} else {
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_error);
					sendMsgtoWeb(rs);
				}
			}
			break;
			case Configurations.action_check_login: {
				TransferResultInfo<String> fin = new TransferResultInfo<String>();
				switch (ui) {
				case Configurations.action_general_UI_ADMIN: {
					checkAdminLogin();
					fin.setMsgType(ResultCodeStorage.type_success);
					fin.setMsgCode(ResultCodeStorage.code_success);
					sendMsgtoWeb(fin);
				}
					break;
				case Configurations.action_general_UI_USER:
				default: {
					checkUserLogin();
					fin.setMsgType(ResultCodeStorage.type_success);
					fin.setMsgCode(ResultCodeStorage.code_success);
					sendMsgtoWeb(fin);
				}
				}
			}
			case Configurations.action_check_qualification: {
				checkUserLogin();
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				userService.initParameters();
				userService.setParameters(UserService.set_uid, user.getUser().getUid());
				TransferResultInfo<?> rs = userService.find(UserService.findMode_detail);
				if (ResultCodeStorage.type_error.equals(rs.getMsgType())) {
					sendMsgtoWeb(rs);
				}
				@SuppressWarnings("unchecked")
				List<TransferUserInfo> list_user = (List<TransferUserInfo>) rs.getMsgContent();
				TransferQualificationtypeInfo qual = null;
				for (TransferUserInfo transferUserInfo : list_user) {
					qual = transferUserInfo.getQualtype();
				}
				if (qual == null || qual.getUid() == null) {
					TransferResultInfo<String> fin = new TransferResultInfo<String>();
					fin.setMsgType(ResultCodeStorage.type_success);
					fin.setMsgCode(ResultCodeStorage.code_success);
					sendMsgtoWeb(fin);
				} else {
					qualificationrequestService.initParameters();
					qualificationrequestService.setParameters(QualificationrequestService.set_userUid, user.getUser().getUid());
					qualificationrequestService.setParameters(QualificationrequestService.set_typeUid, qual.getUid());
					TransferResultInfo<?> fin_list = qualificationrequestService.find(QualificationrequestService.find_summary);
					if (ResultCodeStorage.type_success.equals(fin_list.getMsgType())) {
						TransferQualificationrequestInfo fin_content = new TransferQualificationrequestInfo();
						@SuppressWarnings("unchecked")
						List<TransferQualificationrequestInfo> req_list = (List<TransferQualificationrequestInfo>) fin_list.getMsgContent();
						for (TransferQualificationrequestInfo transferQualificationrequestInfo : req_list) {
							fin_content.setType(transferQualificationrequestInfo.getType());
							fin_content.setCheckingtime(transferQualificationrequestInfo.getCheckingtime());
							break;
						}
						TransferResultInfo<TransferQualificationrequestInfo> fin = new TransferResultInfo<TransferQualificationrequestInfo>();
						fin.setMsgType(ResultCodeStorage.type_success);
						fin.setMsgCode(ResultCodeStorage.code_success);
						fin.setMsgContent(fin_content);
						sendMsgtoWeb(fin);
					} else {
						sendMsgtoWeb(fin_list);
					}
				}
			}
				break;
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
		} catch (NoLoginException e) {
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
		response.setHeader("Cache-Control", "no-cache");
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
				|| Configurations.string_nologin.equals(session
						.getAttribute(Configurations.session_user_login_key)))
			throw new NoLoginException("普通用户没有登录");
	}

	private void checkAdminLogin() throws NoLoginException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Configurations.session_admin_login_key) == null
				|| Configurations.string_nologin.equals(session
						.getAttribute(Configurations.session_admin_login_key)))
			throw new NoLoginException("管理员用户没有登录");
	}

}
