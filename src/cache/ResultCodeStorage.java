package cache;

import java.util.HashMap;
import java.util.Map;

public final class ResultCodeStorage {
	public static final String type_success = "success";
	public static final String type_error = "error";
	public static final String code_success = "00000";
	
	public static final String code_err_missing_parameter = "00001";
	public static final String code_err_invalid_parameter = "00002";
	public static final String code_err_no_login = "00003";
	public static final String code_err_no_qualification = "00004";
	public static final String code_err_invalid_access = "00005";
	public static final String code_err_invalid_password = "00006";
	public static final String code_err_invalid_uid = "00007";
	public static final String code_err_invalid_email = "00008";
	public static final String code_err_invalid_num = "00009";
	
	public static final String code_err_login_failed = "00010";
	
	public static final String code_err_dao_hibernate_save = "00011";
	public static final String code_err_dao_hibernate_update = "00012";
	public static final String code_err_dao_hibernate_query = "00013";
	public static final String code_err_session_hibernate_empty = "00014";
	
	public static final String code_err_illegal_operation = "00015";
	public static final String code_err_unapplicable_problem_type = "00016";
	
	public static final String code_err_generic_server_internal_exception = "00500";
	private static Map<String, String> resultCodeStorage = new HashMap<String, String>();
	static {
		resultCodeStorage.put(code_success, "正常");
		resultCodeStorage.put(code_err_missing_parameter, "参数缺失");
		resultCodeStorage.put(code_err_no_login, "无效的登录状态");
		resultCodeStorage.put(code_err_no_qualification, "无效的认证状态");
		resultCodeStorage.put(code_err_invalid_parameter, "无效的参数");
		resultCodeStorage.put(code_err_invalid_access, "无效的权限");
		resultCodeStorage.put(code_err_invalid_password, "无效的密码");
		resultCodeStorage.put(code_err_invalid_uid, "无效的通用 id");
		resultCodeStorage.put(code_err_invalid_email, "无效的邮箱");
		resultCodeStorage.put(code_err_invalid_num, "无效的学号");
		resultCodeStorage.put(code_err_login_failed, "登录失败 ");
		resultCodeStorage.put(code_err_dao_hibernate_save, "Hibernate insert 操作错误");
		resultCodeStorage.put(code_err_dao_hibernate_update, "Hibernate update 操作错误");
		resultCodeStorage.put(code_err_dao_hibernate_query, "Hibernate query 操作错误");
		resultCodeStorage.put(code_err_session_hibernate_empty, "hibernate session 未初始化");
		resultCodeStorage.put(code_err_illegal_operation, "非法操作");
		resultCodeStorage.put(code_err_unapplicable_problem_type, "不适用的问题类型");
		resultCodeStorage.put(code_err_generic_server_internal_exception, "通用的服务器内部错误");
	}
	
	public static String getResultCodeDescription(String code) {
		return resultCodeStorage.get(code);
	}
}
