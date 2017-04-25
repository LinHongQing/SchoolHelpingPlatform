package cache;


public final class Configurations {
	public static final int invalid_int = -1;
	public static final String split_string = ",";
	
	public static final short db_entry_invalid = 0;
	public static final short db_entry_valid = 1;
	public static final String db_entry_default_user_role_uid = "role_default";
	public static final String db_entry_default_admin_privilege_uid = "priv_default";
	public static final int db_entry_default_user_credit_value = 100;
	
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
	
	public static final String authorization_request_type_admin = "admin";
	public static final String authorization_request_type_user = "user";
	public static final String login_request_type_admin = "admin";
	public static final String login_request_type_user = "user";
	
	public static final String session_online_user_key = "user";
	public static final String session_user_login_key = "userlogin";
	public static final String session_user_authorization_key = "userauth";
	public static final String session_admin_login_key = "adminlogin";
	public static final String session_admin_authorization_key = "adminauth";

	public static final String string_authorization_success = "success";
	public static final String string_authorization_fail = "fail";
	public static final String string_login = "true";
	public static final String string_nologin = "false";
	public static final String string_true = "true";
	public static final String string_false = "false";
	public static final String string_solve_problem = "解决问题";
	public static final String string_problem_solve = "发布问题并解决";
	public static final String string_solve_assistant = "协助解决问题";
	
	public static final String string_chat_system_path = "http://localhost:8080/OnlineChatSystem/index.jsp";
	
	public static final String action_general_UI_ADMIN = "admin";
	public static final String action_general_UI_USER = "user";
	
	public static final int action_defaultCreditValueChange_problemComplete_creator = 10;
	public static final int action_defaultCreditValueChange_problemComplete_solver = 20;
	
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
	
	public static final String action_complaint_type_get_summary = "summary";
	public static final String action_complaint_type_get_detail = "detail";
	
	public static final String action_qualification_type_get_summary = "summary";
	public static final String action_qualification_type_get_detail = "detail";
	
	public static final String action_apis_which_get_users_info = "getChatUsersInfo";
	public static final String action_apis_which_check_user_access = "checkUserAccess";
	public static final String action_apis_which_notify_user_unread_message = "notifyUnreadMessage";
	
	public static final String action_platform_opt_get = "get";
	public static final String action_platform_opt_clear = "clear";
}
