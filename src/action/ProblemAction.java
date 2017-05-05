package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import bean.TransferOnlineUserBasicInfo;
import bean.TransferProblemInfo;
import bean.TransferQualificationtypeInfo;
import bean.TransferResultInfo;
import bean.TransferSolveInfo;
import bean.TransferUserInfo;
import cache.Configurations;
import cache.PlatformStatistics;
import cache.ResultCodeStorage;

import com.google.gson.Gson;

import exception.IllegalOperationException;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.NoQualificationException;
import exception.PermissionDeniedException;
import service.CreditvaluelogService;
import service.ProblemService;
import service.QualificationtypeService;
import service.SolveService;
import service.UserService;
import util.DbUidGeneratorUtil;
import util.RecommandValueUtil;
import util.StringUtil;
import util.TimeUtil;

public class ProblemAction extends BaseAction implements ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5109006175908608284L;
	private static final int save = 0;
	private static final int accept = 1;
	private static final int reject = 2;
	private static final int cancel = 3;
	private static final int finish = 4;
	private static final int delete = 5;
	private static final int get = 6;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;
	private String createuseruid;
	private String locationuid;
	private String problemtypeuid;
	private String title;
	private List<String> preferday;
	private String preferstart;
	private String preferend;
	private String description;
	private String createip;
	private String createtime;
	private String status;
	private String solveduid;
	private String solvecreateuseruid;
	private List<String> solveassistantuid;
	private String solvedescription;
	private String solvecreatetime;
	private String solvecreateip;
	
	private String type;
	private String ui;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCreateuseruid() {
		return createuseruid;
	}

	public void setCreateuseruid(String createuseruid) {
		this.createuseruid = createuseruid;
	}

	public String getLocationuid() {
		return locationuid;
	}

	public void setLocationuid(String locationuid) {
		this.locationuid = locationuid;
	}

	public String getProblemtypeuid() {
		return problemtypeuid;
	}

	public void setProblemtypeuid(String problemtypeuid) {
		this.problemtypeuid = problemtypeuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getPreferday() {
		return preferday;
	}

	public void setPreferday(List<String> preferday) {
		this.preferday = preferday;
	}

	public String getPreferstart() {
		return preferstart;
	}

	public void setPreferstart(String preferstart) {
		this.preferstart = preferstart;
	}

	public String getPreferend() {
		return preferend;
	}

	public void setPreferend(String preferend) {
		this.preferend = preferend;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSolveduid() {
		return solveduid;
	}

	public void setSolveduid(String solveduid) {
		this.solveduid = solveduid;
	}

	public String getSolvecreateuseruid() {
		return solvecreateuseruid;
	}

	public void setSolvecreateuseruid(String solvecreateuseruid) {
		this.solvecreateuseruid = solvecreateuseruid;
	}

	public List<String> getSolveassistantuid() {
		return solveassistantuid;
	}

	public void setSolveassistantuid(List<String> solveassistantuid) {
		this.solveassistantuid = solveassistantuid;
	}

	public String getSolvedescription() {
		return solvedescription;
	}

	public void setSolvedescription(String solvedescription) {
		this.solvedescription = solvedescription;
	}

	public String getSolvecreatetime() {
		return solvecreatetime;
	}

	public void setSolvecreatetime(String solvecreatetime) {
		this.solvecreatetime = solvecreatetime;
	}

	public String getSolvecreateip() {
		return solvecreateip;
	}

	public void setSolvecreateip(String solvecreateip) {
		this.solvecreateip = solvecreateip;
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
	
	public void save() {
		operations(save);
	}
	
	public void accept() {
		operations(accept);
	}
	
	public void reject() {
		operations(reject);
	}
	
	public void cancel() {
		operations(cancel);
	}
	
	public void finish() {
		operations(finish);
	}
	
	public void delete() {
		operations(delete);
	}
	
	public void get() {
		operations(get);
	}
	
	private void operations(int op) {
		try {
			problemService.initParameters();
			solveService.initParameters();
			
			if (uid != null && !"".equals(uid)) {
				problemService.setParameters(ProblemService.set_uid, uid);
				solveService.setParameters(SolveService.set_problemUid, uid);
			}
			if (createuseruid != null && !"".equals(createuseruid))
				problemService.setParameters(ProblemService.set_createUserUid, createuseruid);
			if (locationuid != null && !"".equals(locationuid))
				problemService.setParameters(ProblemService.set_locationUid, locationuid);
			if (problemtypeuid != null && !"".equals(problemtypeuid))
				problemService.setParameters(ProblemService.set_problemTypeUid, problemtypeuid);
			if (title != null && !"".equals(title))
				problemService.setParameters(ProblemService.set_title, title);
			if (preferday != null && preferday.size() > 0)
				problemService.setParameters(ProblemService.set_preferDay, StringUtil.stringsCombine(preferday));
			if (preferstart != null && !"".equals(preferstart))
				problemService.setParameters(ProblemService.set_preferStart, preferstart);
			if (preferend != null && !"".equals(preferend))
				problemService.setParameters(ProblemService.set_preferEnd, preferend);
			if (description != null && !"".equals(description))
				problemService.setParameters(ProblemService.set_description, description);
			if (createip != null && !"".equals(createip))
				problemService.setParameters(ProblemService.set_createIp, createip);
			if (createtime != null && !"".equals(createtime))
				problemService.setParameters(ProblemService.set_createTime, createtime);
			if (status != null && !"".equals(status))
				problemService.setParameters(ProblemService.set_status, status);
			if (solveduid != null && !"".equals(solveduid)) {
				problemService.setParameters(ProblemService.set_solvedUid, solveduid);
				solveService.setParameters(SolveService.set_uid, solveduid);
			}
			if (solvecreateuseruid != null && !"".equals(solvecreateuseruid))
				solveService.setParameters(SolveService.set_createUserUid, solvecreateuseruid);
			if (solveassistantuid != null && solveassistantuid.size() > 0) {
				solveService.setParameters(SolveService.set_assistantUid, StringUtil.stringsCombine(solveassistantuid));
			}
			if (solvedescription != null && !"".equals(solvedescription))
				solveService.setParameters(SolveService.set_description, solvedescription);
			if (solvecreatetime != null && !"".equals(solvecreatetime))
				solveService.setParameters(SolveService.set_createTime, solvecreatetime);
			if (solvecreateip != null && !"".equals(solvecreateip))
				solveService.setParameters(SolveService.set_createIp, solvecreateip);
			if (ui == null || "".equals(ui))
				ui = Configurations.action_general_UI_USER;
			
			switch (op) {
			case save: {
				checkUserLogin();
				checkPermission();
				if (problemtypeuid == null || "".equals(problemtypeuid))
					throw new IllegalParameterException("problemtypeuid 参数不能为空");
				if (title == null || "".equals(title))
					throw new IllegalParameterException("title 参数不能为空");
				if (preferstart == null || "".equals(preferstart))
					throw new IllegalParameterException("preferstart 参数不能为空");
				if (preferend == null || "".equals(preferend))
					throw new IllegalParameterException("preferend 参数不能为空");
				if (preferday == null || "".equals(preferday))
					throw new IllegalParameterException("preferday 参数不能为空");
				if (description == null || "".equals(description))
					throw new IllegalParameterException("description 参数不能为空");
				
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				problemService.setParameters(ProblemService.set_uid,
						DbUidGeneratorUtil.generateProblemUid(PlatformStatistics.getTodayProblemCount()));
				problemService.setParameters(ProblemService.set_locationUid, user.getUser().getLocation().getUid());
				problemService.setParameters(ProblemService.set_status, String.valueOf(Configurations.db_problem_status_waiting));
				problemService.setParameters(ProblemService.set_createIp, request.getRemoteAddr());
				problemService.setParameters(ProblemService.set_createTime, TimeUtil.getNowTimeStamp());
				problemService.setParameters(ProblemService.set_createUserUid, user.getUser().getUid());
				TransferResultInfo<?> rs = problemService.insert();
				if (rs.getMsgType().equals(ResultCodeStorage.type_success)) {
					PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayProblem);
				}
				sendMsgtoWeb(rs);
			}
			break;
			case accept: {
				checkUserLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				// 从 session 中获取当前在线的用户信息
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				// 初始化
				qualificationtypeService.initParameters();
				// 判断用户是否取得认证, 若没有则抛异常
				if (user.getUser().getQualtype() == null)
					throw new NoQualificationException("该用户尚未取得认证, 无法接单");
				// 从 Qualificationtype 表中获取该用户认证类型所对应的问题类型集合
				qualificationtypeService.setParameters(QualificationtypeService.set_uid, user.getUser().getQualtype().getUid());
				TransferResultInfo<?> rs_qualType = qualificationtypeService.find();
				// 判断是否获取成功
				if (rs_qualType.getMsgType().equals(ResultCodeStorage.type_success)) {
					// 若获取成功, 则取出其内容
					@SuppressWarnings("unchecked")
					List<TransferQualificationtypeInfo> list_qualType = (List<TransferQualificationtypeInfo>) rs_qualType.getMsgContent();
					String str_applicableProblemTypeUids = "";
					// 获取对应问题类型集合字符串
					for (TransferQualificationtypeInfo transferQualificationtypeInfo : list_qualType) {
						str_applicableProblemTypeUids = transferQualificationtypeInfo.getStrapplicableproblemtypeUids();
						break;
					}
					// 初始化
					problemService.initParameters();
					// 设置搜索参数
					problemService.setParameters(ProblemService.set_uid, uid);
					// 搜索
					TransferResultInfo<?> rs_problem = problemService.find(ProblemService.find_summary);
					// 若成功搜索到
					if (rs_problem.getMsgType().equals(ResultCodeStorage.type_success)) {
						// 获取该问题的类型通用 id 字符串
						@SuppressWarnings("unchecked")
						List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs_problem.getMsgContent();
						String str_problemTypeUid = "";
						String str_problemUid = "";
						String str_problemCreatorUid = "";
						for (TransferProblemInfo transferProblemInfo : list_problem) {
							// 若该问题的状态已经为在处理或处理完毕或锁定状态, 则该操作非法
							if (transferProblemInfo.getStatus() == Configurations.db_problem_status_processing
									|| transferProblemInfo.getStatus() == Configurations.db_problem_status_complete
									|| transferProblemInfo.getStatus() == Configurations.db_problem_status_lock)
								throw new IllegalOperationException("当前情况无法执行此操作");
							// 获取到该问题的类型通用 id 字符串
							str_problemTypeUid = transferProblemInfo.getProblemtype().getUid();
							// 获取到该问题的通用 id 字符串
							str_problemUid = transferProblemInfo.getUid();
							// 获取到该问题的创建者通用 id 字符串
							str_problemCreatorUid = transferProblemInfo.getCreateuser().getUid();
						}
						if (str_problemCreatorUid.equals(user.getUser().getUid())) {
							throw new IllegalOperationException("不能接受自己发布的问题");
						}
						// 如果该问题类型符合该用户认证里所存在的问题类型, 接受操作成功
						if (str_applicableProblemTypeUids.contains(str_problemTypeUid)) {
							// 初始化
							solveService.initParameters();
							// 设置 Solve 表各个参数的值
							String solveUid = DbUidGeneratorUtil.generateSolveUid(PlatformStatistics.getTodaySolvedCount());
							solveService.setParameters(SolveService.set_uid, solveUid);
							solveService.setParameters(SolveService.set_createUserUid, user.getUser().getUid());
							solveService.setParameters(SolveService.set_createIp, request.getRemoteAddr());
							solveService.setParameters(SolveService.set_createTime, TimeUtil.getNowTimeStamp());
							solveService.setParameters(SolveService.set_problemUid, str_problemUid);
							// 执行插入操作
							TransferResultInfo<?> rs_solve = solveService.insert();
							// 插入结果成功
							if (rs_solve.getMsgType().equals(ResultCodeStorage.type_success)) {
								// 初始化
								problemService.initParameters();
								// 设置 Problem 表各个参数的值
								problemService.setParameters(ProblemService.set_uid, uid);
								problemService.setParameters(ProblemService.set_status, String.valueOf(Configurations.db_problem_status_processing));
								problemService.setParameters(ProblemService.set_solvedUid, solveUid);
								// 进行更新操作, 并将结果输出到前端
								sendMsgtoWeb(problemService.update());
							} else {
								sendMsgtoWeb(rs_solve);
							}
						} else {
							// 不符合抛非法操作异常
							throw new IllegalOperationException("该操作不适用于当前的问题类型");
						}
					} else {
						// 若失败将错误信息输出到前端
						sendMsgtoWeb(rs_problem);
					}
				} else {
					// 若失败将错误信息输出到前端
					sendMsgtoWeb(rs_qualType);
				}
			}
			break;
			case reject: {
				checkUserLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				// 从 session 中获取当前在线的用户信息
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				// 初始化
				problemService.initParameters();
				// 设置参数
				problemService.setParameters(ProblemService.set_uid, uid);
				// 搜索
				TransferResultInfo<?> rs_problem = problemService.find(ProblemService.find_summary);
				// 初始化问题创建者通用 id 字符串
				String problemCreateUserUid = "";
				// 初始化记录 Solve 表通用 id 变量
				String solvedUid = "";
				// 若成功搜索到
				if (rs_problem.getMsgType().equals(ResultCodeStorage.type_success)) {
					@SuppressWarnings("unchecked")
					List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs_problem.getMsgContent();
					// 获取 Problem 表中 Solve 表的通用 id 值
					for (TransferProblemInfo transferProblemInfo : list_problem) {
						// 若该问题的状态已经为在等待或处理完毕或锁定状态, 则该操作非法
						if (transferProblemInfo.getStatus() == Configurations.db_problem_status_waiting
								|| transferProblemInfo.getStatus() == Configurations.db_problem_status_complete
								|| transferProblemInfo.getStatus() == Configurations.db_problem_status_lock)
							throw new IllegalOperationException("当前情况无法执行此操作");
						problemCreateUserUid = transferProblemInfo.getCreateuser().getUid();
						solvedUid = transferProblemInfo.getSolved().getUid();
						break;
					}
					// 如果该问题发布者通用 id 与登录者通用 id 相同, 说明该问题记录是由登录者创建, 可以拒绝
					if (user.getUser().getUid().equals(problemCreateUserUid)) {
						// 初始化
						solveService.initParameters();
						// 设置参数
						solveService.setParameters(SolveService.set_uid, solvedUid);
						// 删除
						TransferResultInfo<?> rs_solve = solveService.delete();
						// 若成功搜索到
						if (rs_solve.getMsgType().equals(ResultCodeStorage.type_success)) {
							problemService.initParameters();
							problemService.setParameters(ProblemService.set_uid, uid);
							problemService.setParameters(ProblemService.set_status, String.valueOf(Configurations.db_problem_status_waiting));
							problemService.setParameters(ProblemService.set_solvedUid, null);
							sendMsgtoWeb(problemService.update());
						} else {
							// 若失败将错误信息输出到前端
							sendMsgtoWeb(rs_solve);
						}
					} else {
						throw new IllegalOperationException("不允许拒绝非自己创建的问题记录");
					}
				} else {
					// 若失败将错误信息输出到前端
					sendMsgtoWeb(rs_problem);
				}
			}
			break;
			case cancel: {
				checkUserLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				// 从 session 中获取当前在线的用户信息
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				// 初始化
				problemService.initParameters();
				// 设置参数
				problemService.setParameters(ProblemService.set_uid, uid);
				// 搜索
				TransferResultInfo<?> rs_problem = problemService.find(ProblemService.find_summary);
				// 初始化记录 Solve 表通用 id 变量
				String solvedUid = "";
				// 初始化记录该问题解决者通用 id 变量
				String solveCreateUserUid = "";
				// 若成功搜索到
				if (rs_problem.getMsgType().equals(ResultCodeStorage.type_success)) {
					@SuppressWarnings("unchecked")
					List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs_problem.getMsgContent();
					// 获取 Problem 表中 Solve 表的通用 id 值
					for (TransferProblemInfo transferProblemInfo : list_problem) {
						// 若该问题的状态已经为在等待或处理完毕或锁定状态, 则该操作非法
						if (transferProblemInfo.getStatus() == Configurations.db_problem_status_waiting
								|| transferProblemInfo.getStatus() == Configurations.db_problem_status_complete
								|| transferProblemInfo.getStatus() == Configurations.db_problem_status_lock)
							throw new IllegalOperationException("当前情况无法执行此操作");
						solvedUid = transferProblemInfo.getSolved().getUid();
						break;
					}
					// 初始化
					solveService.initParameters();
					// 设置参数
					solveService.setParameters(SolveService.set_uid, solvedUid);
					// 搜索
					TransferResultInfo<?> rs_solve = solveService.find();
					// 若成功搜索到
					if (rs_solve.getMsgType().equals(ResultCodeStorage.type_success)) {
						@SuppressWarnings("unchecked")
						// 获取该问题解决者通用 id
						List<TransferSolveInfo> list_solve = (List<TransferSolveInfo>) rs_solve.getMsgContent();
						for (TransferSolveInfo transferSolveInfo : list_solve) {
							solveCreateUserUid = transferSolveInfo.getCreateuser().getUid();
							break;
						}
						// 如果该解决者通用 id 与登录者通用 id 相同, 说明该解决记录是由登录者创建, 可以取消
						if (solveCreateUserUid.equals(user.getUser().getUid())) {
							// 进行取消操作
							rs_solve = solveService.delete();
							if (!ResultCodeStorage.type_success.equals(rs_solve.getMsgType())) {
								sendMsgtoWeb(rs_solve);
								return;
							}
							problemService.initParameters();
							problemService.setParameters(ProblemService.set_uid, uid);
							problemService.setParameters(ProblemService.set_status, String.valueOf(Configurations.db_problem_status_waiting));
							problemService.setParameters(ProblemService.set_solvedUid, null);
							sendMsgtoWeb(problemService.update());
						} else {
							// 若不相同, 则该操作为非法操作, 抛异常
							throw new IllegalOperationException("不允许取消非自己创建的解决记录");
						}
					} else {
						// 若失败将错误信息输出到前端
						sendMsgtoWeb(rs_solve);
					}
				} else {
					// 若失败将错误信息输出到前端
					sendMsgtoWeb(rs_problem);
				}
			}
			break;
			case finish: {
				checkUserLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				if (solvedescription == null || "".equals(solvedescription))
					throw new IllegalParameterException("solvedescription 参数不能为空");
				// 从 session 中获取当前在线的用户信息
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				// 初始化
				problemService.initParameters();
				// 设置参数
				problemService.setParameters(ProblemService.set_uid, uid);
				// 搜索
				TransferResultInfo<?> rs_problem = problemService.find(ProblemService.find_summary);
				// 初始化记录 Solve 表通用 id 变量
				String solvedUid = "";
				// 初始化记录 Solve 表创建用户通用 id 变量
				String problemCreateUserUid = "";
				// 初始化记录该问题解决者通用 id 变量
				String solveCreateUserUid = "";
				// 初始化记录该问题协助解决者通用 id 变量
				String solveAssistantUserUid = "";
				// 若成功搜索到
				if (rs_problem.getMsgType().equals(ResultCodeStorage.type_success)) {
					@SuppressWarnings("unchecked")
					List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs_problem.getMsgContent();
					// 获取 Problem 表中 Solve 表的通用 id 值
					for (TransferProblemInfo transferProblemInfo : list_problem) {
						// 若该问题的状态已经为在等待或处理完毕或锁定状态, 则该操作非法
						if (transferProblemInfo.getStatus() == Configurations.db_problem_status_waiting
								|| transferProblemInfo.getStatus() == Configurations.db_problem_status_complete
								|| transferProblemInfo.getStatus() == Configurations.db_problem_status_lock)
							throw new IllegalOperationException("当前情况无法执行此操作");
						solvedUid = transferProblemInfo.getSolved().getUid();
						problemCreateUserUid = transferProblemInfo.getCreateuser().getUid();
						break;
					}
					// 初始化
					solveService.initParameters();
					// 设置参数
					solveService.setParameters(SolveService.set_uid, solvedUid);
					// 搜索
					TransferResultInfo<?> rs_solve = solveService.find();
					// 若成功搜索到
					if (rs_solve.getMsgType().equals(ResultCodeStorage.type_success)) {
						@SuppressWarnings("unchecked")
						// 获取该问题解决者通用 id
						List<TransferSolveInfo> list_solve = (List<TransferSolveInfo>) rs_solve.getMsgContent();
						for (TransferSolveInfo transferSolveInfo : list_solve) {
							solveCreateUserUid = transferSolveInfo.getCreateuser().getUid();
							break;
						}
						// 如果该解决者通用 id 与登录者通用 id 相同, 说明该解决记录是由登录者创建, 可以进行完成操作
						if (solveCreateUserUid.equals(user.getUser().getUid())) {
							// 进行完成操作(Solve 表部分)
							solveService.initParameters();
							solveService.setParameters(SolveService.set_uid, solvedUid);
							solveService.setParameters(SolveService.set_assistantUid, StringUtil.stringsCombine(solveassistantuid));
							solveService.setParameters(SolveService.set_description, solvedescription);
							solveService.setParameters(SolveService.set_createTime, TimeUtil.getNowTimeStamp());
							rs_solve = solveService.update();
							if (rs_solve.getMsgType().equals(ResultCodeStorage.type_success)) {
								// 进行完成操作(Problem 表部分)
								problemService.initParameters();
								problemService.setParameters(ProblemService.set_uid, uid);
								problemService.setParameters(ProblemService.set_status, String.valueOf(Configurations.db_problem_status_complete));
								rs_problem = problemService.update();
								if (rs_problem.getMsgType().equals(ResultCodeStorage.type_success)) {
									// 以下操作执行信用值更新
									int currentCreditValue = Configurations.int_invalid;
									/*----------problemCreateUser----------*/
									userService.initParameters();
									userService.setParameters(UserService.set_uid, problemCreateUserUid);
									TransferResultInfo<?> rs_problemUser = userService.find(UserService.findMode_summary);
									if (!rs_problemUser.getMsgType().equals(ResultCodeStorage.type_success)) {
										sendMsgtoWeb(rs_problemUser);
										return;
									}
									@SuppressWarnings("unchecked")
									List<TransferUserInfo> list_problemUser = (List<TransferUserInfo>) rs_problemUser.getMsgContent();
									if (list_problemUser.size() == 0)
										System.out.println("problemCreatUserUid 无效");
									else {
										for (TransferUserInfo transferUserInfo : list_problemUser) {
											currentCreditValue = transferUserInfo.getCreditvalue();
											break;
										}
										creditvaluelogService.initParameters();
										creditvaluelogService.setParameters(CreditvaluelogService.set_uid,
												DbUidGeneratorUtil.generateCreditvaluelogUid(PlatformStatistics.getTodayCreditValueChangeCount()));
										creditvaluelogService.setParameters(CreditvaluelogService.set_userUid, problemCreateUserUid);
										creditvaluelogService.setParameters(CreditvaluelogService.set_createTime, TimeUtil.getNowTimeStamp());
										creditvaluelogService.setParameters(CreditvaluelogService.set_changeValue,
												String.valueOf(Configurations.getCreditValueChangeForCreator()));
										currentCreditValue += Configurations.getCreditValueChangeForCreator();
										creditvaluelogService.setParameters(CreditvaluelogService.set_finalValue,
												String.valueOf(currentCreditValue));
										creditvaluelogService.setParameters(CreditvaluelogService.set_reason, Configurations.action_string_problem_solved);
										TransferResultInfo<?> rs_creditvaluelog = creditvaluelogService.insert();
										if (!rs_creditvaluelog.getMsgType().equals(ResultCodeStorage.type_success)) {
											sendMsgtoWeb(rs_creditvaluelog);
											return;
										}
										userService.setParameters(UserService.set_creditValue, String.valueOf(currentCreditValue));
										rs_problemUser = userService.update();
										if (!rs_problemUser.getMsgType().equals(ResultCodeStorage.type_success)) {
											sendMsgtoWeb(rs_problemUser);
											return;
										}
										PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayCreditValueChange);
									}
									/*----------solveCreateUser----------*/
									userService.initParameters();
									userService.setParameters(UserService.set_uid, solveCreateUserUid);
									TransferResultInfo<?> rs_solveUser = userService.find(UserService.findMode_summary);
									if (!rs_solveUser.getMsgType().equals(ResultCodeStorage.type_success)) {
										sendMsgtoWeb(rs_solveUser);
										return;
									}
									@SuppressWarnings("unchecked")
									List<TransferUserInfo> list_solveUser = (List<TransferUserInfo>) rs_solveUser.getMsgContent();
									if (list_solveUser.size() == 0)
										System.out.println("solveCreateUserUid 无效");
									else {
										for (TransferUserInfo transferUserInfo : list_solveUser) {
											currentCreditValue = transferUserInfo.getCreditvalue();
											break;
										}
										creditvaluelogService.initParameters();
										creditvaluelogService.setParameters(CreditvaluelogService.set_uid,
												DbUidGeneratorUtil.generateCreditvaluelogUid(PlatformStatistics.getTodayCreditValueChangeCount()));
										creditvaluelogService.setParameters(CreditvaluelogService.set_userUid, solveCreateUserUid);
										creditvaluelogService.setParameters(CreditvaluelogService.set_createTime, TimeUtil.getNowTimeStamp());
										creditvaluelogService.setParameters(CreditvaluelogService.set_changeValue,
												String.valueOf(Configurations.getCreditValueChangeForSolver()));
										currentCreditValue += Configurations.getCreditValueChangeForSolver();
										creditvaluelogService.setParameters(CreditvaluelogService.set_finalValue,
												String.valueOf(currentCreditValue));
										creditvaluelogService.setParameters(CreditvaluelogService.set_reason, Configurations.action_string_solve_problem);
										TransferResultInfo<?> rs_creditvaluelog = creditvaluelogService.insert();
										if (!rs_creditvaluelog.getMsgType().equals(ResultCodeStorage.type_success)) {
											sendMsgtoWeb(rs_creditvaluelog);
											return;
										}
										userService.setParameters(UserService.set_creditValue, String.valueOf(currentCreditValue));
										rs_solveUser = userService.update();
										if (!rs_solveUser.getMsgType().equals(ResultCodeStorage.type_success)) {
											sendMsgtoWeb(rs_solveUser);
											return;
										}
										PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayCreditValueChange);
									}
									/*------------solveAssistantUsers---------*/
									if (solveassistantuid == null || "".equals(solveassistantuid)) {	// 如果不存在协助者
										PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.getTodaySolvedCount());
										TransferResultInfo<String> rs_final = new TransferResultInfo<String>();
										rs_final.setMsgType(ResultCodeStorage.type_success);
										rs_final.setMsgCode(ResultCodeStorage.code_success);
										rs_final.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_success, null));
										sendMsgtoWeb(rs_final);
									} else {
										for (String assistantUser : solveassistantuid) {
											userService.initParameters();
											userService.setParameters(UserService.set_uid, assistantUser);
											TransferResultInfo<?> rs_assistantUser = userService.find(UserService.findMode_summary);
											if (!rs_assistantUser.getMsgType().equals(ResultCodeStorage.type_success)) {
												sendMsgtoWeb(rs_assistantUser);
												return;
											}
											@SuppressWarnings("unchecked")
											List<TransferUserInfo> list_assistantUser = (List<TransferUserInfo>) rs_assistantUser.getMsgContent();
											if (list_assistantUser.size() == 0)
												System.out.println("solveAssistantUserUid 无效");
											else {
												for (TransferUserInfo transferUserInfo : list_assistantUser) {
													currentCreditValue = transferUserInfo.getCreditvalue();
													solveAssistantUserUid = transferUserInfo.getUid();
													break;
												}
												creditvaluelogService.initParameters();
												creditvaluelogService.setParameters(CreditvaluelogService.set_uid,
														DbUidGeneratorUtil.generateCreditvaluelogUid(PlatformStatistics.getTodayCreditValueChangeCount()));
												creditvaluelogService.setParameters(CreditvaluelogService.set_userUid, solveAssistantUserUid);
												creditvaluelogService.setParameters(CreditvaluelogService.set_createTime, TimeUtil.getNowTimeStamp());
												creditvaluelogService.setParameters(CreditvaluelogService.set_changeValue,
														String.valueOf(Configurations.getCreditValueChangeForSolver()));
												currentCreditValue += Configurations.getCreditValueChangeForSolver();
												creditvaluelogService.setParameters(CreditvaluelogService.set_finalValue,
														String.valueOf(currentCreditValue));
												creditvaluelogService.setParameters(CreditvaluelogService.set_reason, Configurations.action_string_problem_solve_assistant);
												TransferResultInfo<?> rs_creditvaluelog = creditvaluelogService.insert();
												if (!rs_creditvaluelog.getMsgType().equals(ResultCodeStorage.type_success)) {
													sendMsgtoWeb(rs_creditvaluelog);
													return;
												}
												userService.setParameters(UserService.set_creditValue, String.valueOf(currentCreditValue));
												rs_solveUser = userService.update();
												if (!rs_assistantUser.getMsgType().equals(ResultCodeStorage.type_success)) {
													sendMsgtoWeb(rs_assistantUser);
													return;
												}
												PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todayCreditValueChange);
											}
										}
									}
									PlatformStatistics.addOneValuetoStatistic(PlatformStatistics.todaySolved);
									TransferResultInfo<String> rs_final = new TransferResultInfo<String>();
									rs_final.setMsgType(ResultCodeStorage.type_success);
									rs_final.setMsgCode(ResultCodeStorage.code_success);
									rs_final.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_success, null));
									sendMsgtoWeb(rs_final);
								} else {
									sendMsgtoWeb(rs_problem);
								}
							} else {
								// 若失败将错误信息输出到前端
								sendMsgtoWeb(rs_solve);
							}
						} else {
							// 若不相同, 则该操作为非法操作, 抛异常
							throw new IllegalOperationException("不允许完成非自己创建的解决记录");
						}
					} else {
						// 若失败将错误信息输出到前端
						sendMsgtoWeb(rs_solve);
					}
				} else {
					// 若失败将错误信息输出到前端
					sendMsgtoWeb(rs_problem);
				}
			}
			break;
			case delete: {
				checkUserLogin();
				checkPermission();
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				// 从 session 中获取当前在线的用户信息
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				// 搜索
				TransferResultInfo<?> rs = problemService.find(ProblemService.find_summary);
				// 若成功搜索到
				if (rs.getMsgType().equals(ResultCodeStorage.type_success)) {
					// 初始化该问题记录的创建者通用 id 以及解决记录通用 id 变量
					String createUserUid = "";
					String solvedUid = "";
					@SuppressWarnings("unchecked")
					// 获取该问题记录的创建者通用 id 以及解决记录通用 id
					List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs.getMsgContent();
					for (TransferProblemInfo transferProblemInfo : list_problem) {
						createUserUid = transferProblemInfo.getCreateuser().getUid();
						solvedUid = transferProblemInfo.getSolved().getUid();
						break;
					}
					// 如果该解决者通用 id 与登录者通用 id 相同, 说明该解决记录是由登录者创建, 可以进行删除操作
					if (createUserUid.equals(user.getUser().getUid())) {
						// 进行删除操作(Solve 表部分)
						solveService.initParameters();
						solveService.setParameters(SolveService.set_uid, solvedUid);
						TransferResultInfo<?> rs_solve = solveService.delete();
						if (rs_solve.getMsgType().equals(ResultCodeStorage.type_success)) {
							// 进行删除操作(Problem 表部分)
							problemService.initParameters();
							problemService.setParameters(ProblemService.set_uid, uid);
							sendMsgtoWeb(problemService.delete());
						} else {
							sendMsgtoWeb(rs_solve);
						}
					} else {
						throw new IllegalOperationException("不允许删除非自己创建的问题记录");
					}
				} else {
					sendMsgtoWeb(rs);
				}
			}
			break;
			case get: {
				// 权限控制
				switch(ui) {
				case Configurations.action_general_UI_ADMIN:
					checkAdminLogin();
					break;
				case Configurations.action_general_UI_USER:
				default:
					checkUserLogin();
					checkPermission();
				}
				if (type == null || "".equals(type))
					type = Configurations.action_problem_type_get_summary;
				switch(type) {
				case Configurations.action_problem_type_get_summary: {
					TransferResultInfo<?> rs = problemService.find(ProblemService.find_summary);
					sendMsgtoWeb(rs);
				}
				break;
				case Configurations.action_problem_type_get_detail: {
					if (uid == null || "".equals(uid))
						throw new IllegalParameterException("uid 不能为空");
					TransferResultInfo<?> rs = problemService.find(ProblemService.find_detail);
					sendMsgtoWeb(rs);
				}
				break;
				case Configurations.action_problem_type_get_my: {
					if (Configurations.action_general_UI_ADMIN.equals(ui))
						throw new IllegalOperationException("管理员无法获取该数据");
					TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
					problemService.setParameters(ProblemService.set_createUserUid, user.getUser().getUid());
					TransferResultInfo<?> rs = problemService.find(ProblemService.find_summary);
					sendMsgtoWeb(rs);
				}
				break;
				case Configurations.action_problem_type_get_solve: {
					// 搜索
					TransferResultInfo<?> rs_solve = solveService.find();
					// 若成功搜索到
					if (rs_solve.getMsgType().equals(ResultCodeStorage.type_success)) {
						// 初始化
						String strAssistantUids = "";
						// 获取 Solve 返回的列表
						@SuppressWarnings("unchecked")
						List<TransferSolveInfo> list_solve = (List<TransferSolveInfo>) rs_solve.getMsgContent();
						// 初始化
						TransferSolveInfo solve = null;
						// 获取协助者的通用 id 集合字符串以及 Solve 返回的结果
						for (TransferSolveInfo transferSolveInfo : list_solve) {
							strAssistantUids = transferSolveInfo.getStrassistantuids();
							solve = transferSolveInfo;
							break;
						}
						// 若协助者不为空
						if (strAssistantUids != null && !"".equals(strAssistantUids)) {
							// 将字符串集合分割成字符串数组
							String[] assistantUids = strAssistantUids.split(Configurations.string_split);
							// 初始化要返回前端的协助者数组
							List<TransferUserInfo> assistant = new ArrayList<TransferUserInfo>();
							for (String assistantUid : assistantUids) {
								// 初始化
								TransferUserInfo t = new TransferUserInfo();
								// 初始化
								userService.initParameters();
								// 设置参数
								userService.setParameters(UserService.set_uid, assistantUid);
								// 搜索
								TransferResultInfo<?> rs_user = userService.find(UserService.findMode_summary);
								// 若成功搜索到
								if (rs_user.getMsgType().equals(ResultCodeStorage.type_success)) {
									// 获取 User 返回的列表
									@SuppressWarnings("unchecked")
									List<TransferUserInfo> list_user = (List<TransferUserInfo>) rs_user.getMsgContent();
									// 将协助者的具体信息添加到要返回前端的协助者数组中去
									for (TransferUserInfo transferUserInfo : list_user) {
										t.setUid(transferUserInfo.getUid());
										t.setName(transferUserInfo.getName());
										assistant.add(t);
										break;
									}
								} else {
									// 若失败将错误信息输出到前端
									sendMsgtoWeb(rs_user);
									return;
								}
							}
							// 清除协助者字符串的值
							solve.setStrassistantuids(null);
							// 将协助者具体信息更新到类中
							solve.setAssistant(assistant);
							// 发送到前端
							sendMsgtoWeb(rs_solve);
						} else {
							// 将结果输出到前端
							sendMsgtoWeb(rs_solve);
						}
					} else {
						// 若失败将错误信息输出到前端
						sendMsgtoWeb(rs_solve);
					}
				}
				break;
				case Configurations.action_problem_type_get_recommand: {
					// 从 session 中获取当前在线的用户信息
					TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
					if (user.getUser().getQualtype() == null)
						throw new NoQualificationException("该用户没有认证信息, 无法获取推荐问题");
					// 初始化
					qualificationtypeService.initParameters();
					// 从 Qualificationtype 表中获取该用户认证类型所对应的问题类型集合
					qualificationtypeService.setParameters(QualificationtypeService.set_uid, user.getUser().getQualtype().getUid());
					TransferResultInfo<?> rs_qualType = qualificationtypeService.find();
					// 判断是否获取成功
					if (rs_qualType.getMsgType().equals(ResultCodeStorage.type_success)) {
						// 若获取成功, 则取出其内容
						@SuppressWarnings("unchecked")
						List<TransferQualificationtypeInfo> list_qualType = (List<TransferQualificationtypeInfo>) rs_qualType.getMsgContent();
						String str_applicableProblemTypeUids = "";
						// 获取对应问题类型集合字符串
						for (TransferQualificationtypeInfo transferQualificationtypeInfo : list_qualType) {
							str_applicableProblemTypeUids = transferQualificationtypeInfo.getStrapplicableproblemtypeUids();
							break;
						}
						problemService.initParameters();
						if (str_applicableProblemTypeUids != null || !"".equals(str_applicableProblemTypeUids))
							// 字符串获取正常, 将其设置到 ProblemService 的参数集合中去
							problemService.setParameters(ProblemService.set_problemTypeUid, str_applicableProblemTypeUids);
						else
							// 若不正常则抛异常
							throw new IllegalParameterException("applicableProblemTypeUids 参数无效");
						// 获取当前日期的星期以及时间, 将用于计算推荐值以供排序
						String currentWeekDay = String.valueOf(TimeUtil.getNowTimeValue(TimeUtil.get_weekday) - 1);	// 星期日的值为 1 , 需要减少 1 以适配数据库数据
						int currentTime = TimeUtil.getNowTimeValue(TimeUtil.get_hour) * 60 + TimeUtil.getNowTimeValue(TimeUtil.get_minute);
						// 将其设置到 ProblemService 的参数集合中去
						problemService.setParameters(ProblemService.set_currentWeekDay, currentWeekDay);
						problemService.setParameters(ProblemService.set_currentTime, String.valueOf(currentTime));
						// 执行查询
						TransferResultInfo<?> rs_problem = problemService.find(ProblemService.find_applicable);
						// 判断是否成功
						if (rs_problem.getMsgType().equals(ResultCodeStorage.type_success)) {
							// 若获取成功, 则取出其内容
							@SuppressWarnings("unchecked")
							List<TransferProblemInfo> list_problem = (List<TransferProblemInfo>) rs_problem.getMsgContent();
							// 获取当前用户的地理位置信息, 将用于计算推荐值以供排序
							float myLongitude = user.getUser().getLocation().getLongitude();
							float myLatitude = user.getUser().getLocation().getLatitude();
							// 从列表中移除自己发布的问题, 并计算所有符合条件的所有条目的推荐值
							int i = 0;
							while (i < list_problem.size()) {
								TransferProblemInfo transferProblemInfo = list_problem.get(i);
								if (user.getUser().getUid().equals(transferProblemInfo.getCreateuser().getUid())) {
									list_problem.remove(transferProblemInfo);
									continue;
								}
								int preferStart = transferProblemInfo.getPreferstart();
								int preferEnd = transferProblemInfo.getPreferend();
								float longitude = transferProblemInfo.getLocation().getLongitude();
								float latitude = transferProblemInfo.getLocation().getLatitude();
								transferProblemInfo.setRecommandvalue(RecommandValueUtil.getValue(latitude, longitude, myLatitude, myLongitude, preferStart, preferEnd));
								i++;
							}
							// 按推荐值进行排序操作
							Collections.sort(list_problem, new ProblemSort());
							// 将排序好的最终结果输出到前端
							sendMsgtoWeb(rs_problem);
						} else {
							// 若失败将错误信息输出到前端
							sendMsgtoWeb(rs_problem);
						}
					} else {
						// 若失败将错误信息输出到前端
						sendMsgtoWeb(rs_qualType);
					}
				}
				break;
				default:
					throw new IllegalParameterException("type 参数无效");
				}
			}
			break;
			default: {
				throw new IllegalParameterException("op 参数无效");
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
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_no_login);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_no_login, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (NoQualificationException e) {
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_no_qualification);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_no_qualification, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_invalid_access);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_access, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (IllegalOperationException e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_illegal_operation);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_illegal_operation, e.getMessage()));
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
	
	/**
	 * 按类中的推荐值进行排序
	 * @author Paradise
	 *
	 */
	private class ProblemSort implements Comparator<TransferProblemInfo> {

		@Override
		public int compare(TransferProblemInfo o1, TransferProblemInfo o2) {
			// TODO Auto-generated method stub
			return o1.getRecommandvalue() - o2.getRecommandvalue();
		}
		
	}

}
