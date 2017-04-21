package cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.StringUtil;

public final class PrivilegeCodeRequestPathStorage {
	
	public static final int _privilege_code_maximun_character_length = 64;
	
	public static final int user = 0;
	public static final int admin = 1;
	
	public static final int is_authorized = 1;
	public static final int not_authorized = 0;
	
	public static final int invalid_request_path_code = Configurations.invalid_int;
	
	public static final int _user_permit_problem_new = 0;
	public static final int _user_permit_problem_operation = 1;
	public static final int _user_permit_problem_delete = 2;
	public static final int _user_permit_problem_get = 3;
	public static final int _user_permit_user_update = 4;
	public static final int _user_permit_user_qualification = 5;
	public static final int _user_permit_user_complaint = 6;
	
	public static final int _admin_permit_academy_operation = 0;
	public static final int _admin_permit_course_operation = 1;
	public static final int _admin_permit_grade_operation = 2;
	public static final int _admin_permit_term_operation = 3;
	public static final int _admin_permit_location_operation = 4;
	public static final int _admin_permit_user_operation = 4;
	public static final int _admin_permit_role_operation = 5;
	public static final int _admin_permit_admin_operation = 6;
	public static final int _admin_permit_privilege_operation = 7;
	public static final int _admin_permit_qualificationtype_operation = 8;
	public static final int _admin_permit_problemtype_operation = 9;
	public static final int _admin_permit_complaint_get = 10;
	public static final int _admin_permit_complaint_operation = 11;
	public static final int _admin_permit_qualificationrequest_get = 12;
	public static final int _admin_permit_qualificationrequest_verify = 13;
	
	private static final Map<Integer, String> users;
	private static final Map<Integer, String> admins;
	
	public static final Map<Integer, String> users_description;
	public static final Map<Integer, String> admins_description;
	
	static{
		users = new HashMap<Integer, String>();
		admins = new HashMap<Integer, String>();
		users_description = new HashMap<Integer, String>();
		admins_description = new HashMap<Integer, String>();
		
		users.put(_user_permit_problem_new, "problem-save");
		users.put(_user_permit_problem_operation, StringUtil.stringsCombine("problem-accept", "problem-cancel", "problem-finish", "problem-get", "problem-reject"));
		users.put(_user_permit_user_update, StringUtil.stringsCombine("user-update", "user-changepwd"));
		users.put(_user_permit_user_qualification, StringUtil.stringsCombine("qualification-apply", "qualification-get"));
		users.put(_user_permit_user_complaint, StringUtil.stringsCombine("complaint-request", "complaint-get"));
		
		admins.put(_admin_permit_academy_operation, StringUtil.stringsCombine("academy-save", "academy-update", "academy-get", "academy-delete"));
		admins.put(_admin_permit_course_operation, StringUtil.stringsCombine("course-save", "course-update", "course-get", "course-delete"));
		admins.put(_admin_permit_grade_operation, StringUtil.stringsCombine("grade-save", "grade-update", "grade-get", "grade-delete"));
		admins.put(_admin_permit_term_operation, StringUtil.stringsCombine("term-save", "term-update", "term-get", "term-delete"));
		admins.put(_admin_permit_location_operation, StringUtil.stringsCombine("location-save", "location-update", "location-get", "location-delete"));
		admins.put(_admin_permit_user_operation, StringUtil.stringsCombine("user-save", "user-update", "user-get", "user-delete", "user-changepwd"));
		admins.put(_admin_permit_role_operation, StringUtil.stringsCombine("role-save", "role-update", "role-get", "role-delete"));
		admins.put(_admin_permit_admin_operation, StringUtil.stringsCombine("admin-save", "admin-update", "admin-get", "admin-delete", "admin-changepwd"));
		admins.put(_admin_permit_privilege_operation, StringUtil.stringsCombine("privilege-save", "privilege-update", "privilege-get", "privilege-delete"));
		admins.put(_admin_permit_qualificationtype_operation, StringUtil.stringsCombine("qualtype-save", "qualtype-update", "qualtype-get", "qualtype-delete"));
		admins.put(_admin_permit_problemtype_operation, StringUtil.stringsCombine("problemtype-save", "problemtype-update", "problemtype-get", "problemtype-delete"));
		admins.put(_admin_permit_complaint_get, StringUtil.stringsCombine("complaint-get"));
		admins.put(_admin_permit_complaint_operation, StringUtil.stringsCombine("complaint-process", "complaint-finish"));
		admins.put(_admin_permit_qualificationrequest_get, StringUtil.stringsCombine("qualreq-get"));
		admins.put(_admin_permit_qualificationrequest_verify, StringUtil.stringsCombine("qualreq-verify"));
		
		users_description.put(_user_permit_problem_new, "允许用户发布问题");
		users_description.put(_user_permit_problem_operation, "允许用户进行问题操作");
		users_description.put(_user_permit_user_update, "允许用户更新个人信息");
		users_description.put(_user_permit_user_qualification, "允许用户提交认证");
		users_description.put(_user_permit_user_complaint, "允许用户进行投诉");
		
		admins_description.put(_admin_permit_academy_operation, "允许操作学院信息");
		admins_description.put(_admin_permit_course_operation, "允许操作专业信息");
		admins_description.put(_admin_permit_grade_operation, "允许操作年级信息");
		admins_description.put(_admin_permit_term_operation, "允许操作学期信息");
		admins_description.put(_admin_permit_location_operation, "允许操作位置信息");
		admins_description.put(_admin_permit_user_operation, "允许操作用户信息");
		admins_description.put(_admin_permit_role_operation, "允许操作用户权限信息");
		admins_description.put(_admin_permit_admin_operation, "允许操作管理员信息");
		admins_description.put(_admin_permit_privilege_operation, "允许操作管理员权限信息");
		admins_description.put(_admin_permit_qualificationtype_operation, "允许操作认证信息");
		admins_description.put(_admin_permit_problemtype_operation, "允许操作问题类型信息");
		admins_description.put(_admin_permit_complaint_get, "允许查看投诉信息");
		admins_description.put(_admin_permit_complaint_operation, "允许处理投诉信息");
		admins_description.put(_admin_permit_qualificationrequest_get, "允许查看认证请求信息");
		admins_description.put(_admin_permit_qualificationrequest_verify, "允许处理认证请求信息");
		
	}
	
	public static String formatPrivilegeListToString(List<String> source) {
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < _privilege_code_maximun_character_length; i++)
			temp.add("0");
		if (source == null || source.size() == 0)
			return StringUtil.stringsCombine(temp);
		for (String string : source) {
			try {
				temp.set(Integer.parseInt(string), "1");
			} catch (NumberFormatException e) {
				System.out.println("无效数字");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("无效范围");
			}
		}
		return StringUtil.stringsCombine(temp);
	}
	
	public static int resolvePrivilegeCode(String privilegeCode, int privilegeName) {
		try {
			String[] split_result = privilegeCode.split(Configurations.split_string);
			return Integer.parseInt(split_result[privilegeName]) == 1 ? is_authorized : not_authorized;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return -1;
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			return -2;
		}
	}
	
	public static int getRequestPathPrivilegeCode(int privilegeRole, String path) {
		switch (privilegeRole) {
		case admin:
			for (int key : admins.keySet()) {
				String value = admins.get(key);
				String[] requests = value.split(Configurations.split_string);
				for (String string : requests) {
					if (path.contains(string))
						return key;
				}
			}
			break;
		case user:
			for (int key : users.keySet()) {
				String value = users.get(key);
				String[] requests = value.split(Configurations.split_string);
				for (String string : requests) {
					if (path.contains(string))
						return key;
				}
			}
			break;
		}
		return invalid_request_path_code;
	}
}
