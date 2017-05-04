package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;

import bean.TransferOnlineUserBasicInfo;
import bean.TransferProblemtypeInfo;
import bean.TransferQualificationtypeInfo;
import bean.TransferResultInfo;
import cache.Configurations;
import cache.ResultCodeStorage;
import exception.IllegalParameterException;
import exception.NoLoginException;
import exception.PermissionDeniedException;
import service.ProblemtypeService;
import service.QualificationtypeService;
import util.DbUidGeneratorUtil;
import util.StringUtil;
import util.TimeUtil;

public class QualificationtypeAction extends BaseAction implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5340771308083468261L;
	
	private static final int save = 0;
	private static final int update = 1;
	private static final int delete = 2;
	private static final int get = 3;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;
	private String name;
	private List<String> problemtypeuid;
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

	public List<String> getProblemtypeuid() {
		return problemtypeuid;
	}

	public void setProblemtypeuid(List<String> problemtypeuid) {
		this.problemtypeuid = problemtypeuid;
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
			checkAdminLogin();
			checkPermission();
			
			qualificationtypeService.initParameters();
			if (uid != null && !"".equals(uid))
				qualificationtypeService.setParameters(QualificationtypeService.set_uid, uid);
			if (name != null && !"".equals(name))
				qualificationtypeService.setParameters(QualificationtypeService.set_name, name);
			if (problemtypeuid != null && !"".equals(problemtypeuid))
				qualificationtypeService.setParameters(QualificationtypeService.set_problemTypeUid, StringUtil.stringsCombine(problemtypeuid));
			if (createuseruid != null && !"".equals(createuseruid))
				qualificationtypeService.setParameters(QualificationtypeService.set_createUserUid, createuseruid);
			if (createip != null && !"".equals(createip))
				qualificationtypeService.setParameters(QualificationtypeService.set_createIp, createip);
			if (createtime != null && !"".equals(createtime))
				qualificationtypeService.setParameters(QualificationtypeService.set_createTime, createtime);

			switch (op) {
			case save: {
				if (name == null || "".equals(name))
					throw new IllegalParameterException("name 参数不能为空");
				if (problemtypeuid == null || "".equals(problemtypeuid))
					throw new IllegalParameterException("problemtypeuid 参数不能为空");
				TransferOnlineUserBasicInfo user = (TransferOnlineUserBasicInfo) request.getSession().getAttribute(Configurations.session_online_user_key);
				qualificationtypeService.setParameters(QualificationtypeService.set_uid, DbUidGeneratorUtil.generateQualificationtypeUid());
				qualificationtypeService.setParameters(QualificationtypeService.set_createIp, request.getRemoteAddr());
				qualificationtypeService.setParameters(QualificationtypeService.set_createTime, TimeUtil.getNowTimeStamp());
				qualificationtypeService.setParameters(QualificationtypeService.set_createUserUid, user.getAdmin().getUid());
				TransferResultInfo<?> rs = qualificationtypeService.insert();
				sendMsgtoWeb(rs);
			}
			break;
			case update: {
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = qualificationtypeService.update();
				sendMsgtoWeb(rs);
			}
			break;
			case delete: {
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = qualificationtypeService.delete();
				sendMsgtoWeb(rs);
			}
			break;
			case get: {
//				if (uid == null || "".equals(uid))
//					throw new IllegalParameterException("uid 参数不能为空");
				TransferResultInfo<?> rs = qualificationtypeService.find();		// 获取资质类型信息
				if (rs.getMsgType() == ResultCodeStorage.type_success) {		// 返回的信息有效
					// 获取资质类型信息的具体内容
					@SuppressWarnings("unchecked")
					List<TransferQualificationtypeInfo> list_qualType = (List<TransferQualificationtypeInfo>) rs.getMsgContent();
					
					for (TransferQualificationtypeInfo transferQualificationtypeInfo : list_qualType) {
						// 获取资质类型信息里面包含的问题类型字符串
						String strApplicableProblemTypeUids = transferQualificationtypeInfo.getStrapplicableproblemtypeUids();
						// 将对应的多个问题类型分割出来
						String[] applicableProblemTypeUids = strApplicableProblemTypeUids.split(Configurations.string_split);
						// 初始化最终的集合
						List<TransferProblemtypeInfo> finalApplicableProblemType = new ArrayList<TransferProblemtypeInfo>();
						// 开始获取所有问题类型的具体信息 (通用 id, 名称)
						for (String applicableProblemUid : applicableProblemTypeUids) {
							problemtypeService.initParameters();
							problemtypeService.setParameters(ProblemtypeService.set_uid, applicableProblemUid);
							TransferResultInfo<?> rs_problemType = problemtypeService.find();
							if (rs_problemType.getMsgType() == ResultCodeStorage.type_success) {
								// 获取到问题类型的所有信息
								@SuppressWarnings("unchecked")
								List<TransferProblemtypeInfo> list_problem = (List<TransferProblemtypeInfo>) rs_problemType.getMsgContent();
								for (TransferProblemtypeInfo transferProblemtypeInfo : list_problem) {
									TransferProblemtypeInfo fin = new TransferProblemtypeInfo();
									// 设置 uid 与 name
									fin.setUid(transferProblemtypeInfo.getUid());
									fin.setName(transferProblemtypeInfo.getName());
									// 添加到 List 中
									finalApplicableProblemType.add(fin);
									break;
								}
							} else {
								sendMsgtoWeb(rs_problemType);
							}
						}
						// 将最终值设置到 content 中去
						transferQualificationtypeInfo.setApplicableproblemtypes(finalApplicableProblemType);
						// 清除不必要的变量
						transferQualificationtypeInfo.setStrapplicableproblemtypeUids(null);
					}
					sendMsgtoWeb(rs);
				} else {
					sendMsgtoWeb(rs);
				}
			}
			break;
			default: {
				TransferResultInfo<String> rs = new TransferResultInfo<String>();
				rs.setMsgType(ResultCodeStorage.type_error);
				rs.setMsgCode(ResultCodeStorage.code_err_invalid_parameter);
				rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_parameter, "op 参数无效"));
				sendMsgtoWeb(rs);
			}
			break;
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
