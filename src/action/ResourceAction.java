package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.ResourceService;
import util.StringUtil;
import bean.TransferResourceInfo;
import bean.TransferResultInfo;

import com.google.gson.Gson;

import cache.Configurations;
import cache.ResultCodeStorage;
import exception.FileOperateException;
import exception.IllegalParameterException;
import exception.NoLoginException;

public class ResourceAction extends BaseAction implements ServletResponseAware,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7673368050620394712L;
	private static final int get = 0;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String uid;

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
	
	private void operations(int op) {
		InputStream ins = null;
		BufferedInputStream bins = null;
		OutputStream outs = null;
		BufferedOutputStream bouts = null;
		try {
			checkAdminLogin();
			switch (op) {
			case get: {
				if (uid == null || "".equals(uid))
					throw new IllegalParameterException("uid 参数无效");
				resourceService.initParameters();
				if (uid != null && !"".equals(uid))
					resourceService.setParameters(ResourceService.set_uid, uid);
				TransferResultInfo<?> rs = resourceService.find();
				if (!ResultCodeStorage.type_success.equals(rs.getMsgType())) {
					sendMsgtoWeb(rs);
					return;
				}
				@SuppressWarnings("unchecked")
				List<TransferResourceInfo> list = (List<TransferResourceInfo>) rs.getMsgContent();
				String fileName = null;
				String storagePath = null;
				for (TransferResourceInfo transferResourceInfo : list) {
					fileName = transferResourceInfo.getName();
					storagePath = transferResourceInfo.getValue();
					break;
				}
				if (fileName == null || storagePath == null)
					throw new IllegalParameterException("uid 参数无效");

				File file = new File(storagePath);
				if (file.exists()) {
					ins = new FileInputStream(storagePath);
					bins = new BufferedInputStream(ins);
					outs = response.getOutputStream();
					bouts = new BufferedOutputStream(outs);
					response.setContentType("application/x-download");
					response.setHeader(
							"Content-disposition",
							"attachment;filename="
									+ URLEncoder.encode(fileName, "UTF-8"));
					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
						bouts.write(buffer, 0, bytesRead);
					}
					bouts.flush();
				} else {
					throw new FileOperateException("文件不存在");
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
		} catch (NoLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_no_login);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_no_login, e.getMessage()));
			sendMsgtoWeb(rs);
		} catch (FileOperateException | IOException e) {
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_file_operate_error);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_file_operate_error, e.getMessage()));
			sendMsgtoWeb(rs);
		} finally {
			try {
	            if (null != ins) {
	                ins.close();
	            }
	            if (null != bins) {
	                bins.close();
	            }
	            if (null != outs) {
	                outs.close();
	            }
	            if (null != bouts) {
	                bouts.close();
	            }
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				TransferResultInfo<String> rs = new TransferResultInfo<String>();
				rs.setMsgType(ResultCodeStorage.type_error);
				rs.setMsgCode(ResultCodeStorage.code_err_file_operate_error);
				rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_file_operate_error, e2.getMessage()));
				sendMsgtoWeb(rs);
			}
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
			throw new NoLoginException("管理员用户没有登录");
	}

}
