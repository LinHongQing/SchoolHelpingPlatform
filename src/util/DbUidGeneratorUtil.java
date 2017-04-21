package util;

public final class DbUidGeneratorUtil {
	
	private static final String _linkCharacter						= "_";

	private static final String _uid_user_prefix 					= "usr";
	private static final String _uid_admin_prefix 					= "admin";
	private static final String _uid_role_prefix 					= "role";
	private static final String _uid_privilege_prefix 				= "priv";
	private static final String _uid_academy_prefix 				= "acad";
	private static final String _uid_course_prefix 					= "cour";
	private static final String _uid_grade_prefix 					= "grad";
	private static final String _uid_term_prefix 					= "term";
	private static final String _uid_user_loginlog_prefix 			= "usrlogin";
	private static final String _uid_admin_loginlog_prefix 			= "adminlogin";
	private static final String _uid_creditvaluelog_prefix 			= "cred";
	private static final String _uid_complaintrequest_prefix 		= "compreq";
	private static final String _uid_resource_prefix 				= "res";
	private static final String _uid_location_prefix 				= "loc";
	private static final String _uid_qualificationtype_prefix 		= "qualtyp";
	private static final String _uid_qualificationrequest_prefix 	= "qualreq";
	private static final String _uid_problemtype_prefix 			= "probtyp";
	private static final String _uid_problem_prefix 				= "prob";
	private static final String _uid_solve_prefix 					= "solve";

	public static String generateUserUid() {
		return _uid_user_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateAdminUid() {
		return _uid_admin_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateRoleUid() {
		return _uid_role_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generatePrivilegeUid() {
		return _uid_privilege_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateAcademyUid() {
		return _uid_academy_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateCourseUid() {
		return _uid_course_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateGradeUid() {
		return _uid_grade_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateTermUid() {
		return _uid_term_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateUserLoginlogUid(int dayCount) {
		return _uid_user_loginlog_prefix + _linkCharacter + TimeUtil.getNowTimeStamp() + _linkCharacter + String.valueOf(dayCount);
	}
	public static String generateAdminLoginlogUid(int dayCount) {
		return _uid_admin_loginlog_prefix + _linkCharacter + TimeUtil.getNowTimeStamp() + _linkCharacter + String.valueOf(dayCount);
	}
	public static String generateCreditvaluelogUid(int dayCount) {
		return _uid_creditvaluelog_prefix + _linkCharacter + TimeUtil.getNowTimeStamp() + _linkCharacter + String.valueOf(dayCount);
	}
	public static String generateComplaintrequestUid(int dayCount) {
		return _uid_complaintrequest_prefix + _linkCharacter + TimeUtil.getNowTimeStamp() + _linkCharacter + String.valueOf(dayCount);
	}
	public static String generateResourceUid(int typeCode) {
		return _uid_resource_prefix + _linkCharacter + TimeUtil.getNowTimeStamp() + _linkCharacter + String.valueOf(typeCode);
	}
	public static String generateLocationUid() {
		return _uid_location_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateQualificationtypeUid() {
		return _uid_qualificationtype_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateQualificationrequest(int dayCount) {
		return _uid_qualificationrequest_prefix + _linkCharacter + TimeUtil.getNowTimeStamp() + _linkCharacter + String.valueOf(dayCount);
	}
	public static String generateProblemtypeUid() {
		return _uid_problemtype_prefix + _linkCharacter + StringUtil.getRandomString();
	}
	public static String generateProblemUid(int dayCount) {
		return _uid_problem_prefix + _linkCharacter + TimeUtil.getNowTimeStamp() +  _linkCharacter + String.valueOf(dayCount);
	}
	public static String generateSolveUid(int dayCount) {
		return _uid_solve_prefix + _linkCharacter + TimeUtil.getNowTimeStamp() + _linkCharacter + String.valueOf(dayCount);
	}
}
