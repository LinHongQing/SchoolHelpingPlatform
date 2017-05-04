package cache;


public final class Configurations {
	
	private static String chatSystemPath;
	private static int creditValueChangeForCreator;
	private static int creditValueChangeForSolver;
	private static int defaultUserCreditValue;
	private static String defaultUserRoleUid;

	public static String getChatSystemPath() {
		return chatSystemPath;
	}
	public static void setChatSystemPath(String chatSystemPath) {
		Configurations.chatSystemPath = chatSystemPath;
	}
	public static int getCreditValueChangeForCreator() {
		return creditValueChangeForCreator;
	}
	public static void setCreditValueChangeForCreator(
			int creditValueChangeForCreator) {
		Configurations.creditValueChangeForCreator = creditValueChangeForCreator;
	}
	public static int getCreditValueChangeForSolver() {
		return creditValueChangeForSolver;
	}
	public static void setCreditValueChangeForSolver(int creditValueChangeForSolver) {
		Configurations.creditValueChangeForSolver = creditValueChangeForSolver;
	}
	public static int getDefaultUserCreditValue() {
		return defaultUserCreditValue;
	}
	public static void setDefaultUserCreditValue(int defaultUserCreditValue) {
		Configurations.defaultUserCreditValue = defaultUserCreditValue;
	}
	public static String getDefaultUserRoleUid() {
		return defaultUserRoleUid;
	}
	public static void setDefaultUserRoleUid(String defaultUserRoleUid) {
		Configurations.defaultUserRoleUid = defaultUserRoleUid;
	}
	
	static {
		chatSystemPath = "http://localhost:8080/OnlineChatSystem/index.jsp";
		creditValueChangeForCreator = 10;
		creditValueChangeForSolver = 20;
		defaultUserCreditValue = 100;
		defaultUserRoleUid = "role_default";
	}
	
	public static final int int_invalid = -1;
	public static final String string_split = ",";
	
	public static final String config_platform_property_key = "shp.conf";
	public static final String config_platform_property_file_name = "conf.json";
	
	public static final String db_admin_privilege_uid = "priv_default";
	public static final String db_admin_default_admin_uid = "admin_default";
	
	public static final short db_entry_invalid = 0;
	public static final short db_entry_valid = 1;
	
	public static final int db_complaintrequest_status_waiting = 0;
	public static final int db_complaintrequest_status_processing = 1;
	public static final int db_complaintrequest_status_complete = 2;
	
	public static final int db_problem_status_waiting = 0;
	public static final int db_problem_status_processing = 1;
	public static final int db_problem_status_complete = 2;
	public static final int db_problem_status_lock = -1;
	
	public static final int db_qualificationrequest_checkingstatus_waiting = 0;
	public static final int db_qualificationrequest_checkingstatus_fail = -1;
	public static final int db_qualificationrequest_checkingstatus_pass = 1;
	public static final int db_qualificationrequest_checkingtype_auto = 0;
	public static final int db_qualificationrequest_checkingtype_manual = 1;
	
	public static final String interceptor_string_authorization_success = "success";
	public static final String interceptor_string_authorization_fail = "fail";
	public static final String interceptor_string_login = "true";
	public static final String interceptor_string_nologin = "false";
	public static final String inerceptor_string_authorization_request_type_admin = "admin";
	public static final String interceptor_string_authorization_request_type_user = "user";
	public static final String interceptor_string_login_request_type_admin = "admin";
	public static final String interceptor_string_login_request_type_user = "user";
	
	public static final String session_online_user_key = "user";
	public static final String session_user_login_key = "userlogin";
	public static final String session_authorization_key = "auth";
	public static final String session_admin_login_key = "adminlogin";

	public static final String action_general_UI_ADMIN = "admin";
	public static final String action_general_UI_USER = "user";
	
	public static final String action_init_academy = "academy";
	public static final String action_init_course = "course";
	public static final String action_init_grade = "grade";
	public static final String action_init_term = "term";
	public static final String action_init_location = "location";
	public static final String action_init_problemType = "problemtype";
	public static final String action_init_qualificationType = "qualificationtype";
	public static final String action_init_role = "role";
	public static final String action_init_privilege = "privilege";
	public static final String action_init_role_code = "rolecode";
	public static final String action_init_privilege_code = "privilegecode";
	public static final String action_init_chat_system_path = "chatsystempath";
	
	public static final String action_check_admin_email = "email";
	public static final String action_check_user_num_register = "num";
	public static final String action_check_user = "user";
	public static final String action_check_login = "login";
	public static final String action_check_qualification = "qualification";
	public static final String action_check_problem_owner = "problemowner";
	public static final String action_check_problem_creator = "problemcreator";
	
	public static final String action_user_type_get_summary = "summary";
	public static final String action_user_type_get_detail = "detail";
	
	public static final String action_problem_type_get_summary = "summary";
	public static final String action_problem_type_get_detail = "detail";
	public static final String action_problem_type_get_my = "my";
	public static final String action_problem_type_get_recommand = "recommand";
	public static final String action_problem_type_get_solve = "solve";
	public static final String action_string_solve_problem = "解决问题";
	public static final String action_string_problem_solved = "发布问题并解决";
	public static final String action_string_problem_solve_assistant = "协助解决问题";
	
	public static final String action_complaint_type_get_summary = "summary";
	public static final String action_complaint_type_get_detail = "detail";
	
	public static final String action_qualification_type_get_summary = "summary";
	public static final String action_qualification_type_get_detail = "detail";
	
	public static final String action_sysconf_which_role_set = "role";
	public static final String action_sysconf_which_credit_value_set = "creditvalue";
	public static final String action_sysconf_which_chat_system_set = "chatsystem";
	
	public static final String action_apis_which_get_users_info = "getChatUsersInfo";
	public static final String action_apis_which_check_user_access = "checkUserAccess";
	public static final String action_apis_which_notify_user_unread_message = "notifyUnreadMessage";
	
	public static final String action_platform_opt_get = "get";
	public static final String action_platform_opt_clear = "clear";
}
