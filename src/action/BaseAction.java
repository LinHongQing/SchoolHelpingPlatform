package action;

import service.AcademyService;
import service.AdminService;
import service.AdminloginlogService;
import service.ComplaintrequestService;
import service.CourseService;
import service.CreditvaluelogService;
import service.GradeService;
import service.LocationService;
import service.UserloginlogService;
import service.PrivilegeService;
import service.ProblemService;
import service.ProblemtypeService;
import service.QualificationrequestService;
import service.QualificationtypeService;
import service.ResourceService;
import service.RoleService;
import service.SolveService;
import service.TermService;
import service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095251973376097402L;
	protected AcademyService academyService;
	protected AdminService adminService;
	protected ComplaintrequestService complaintrequestService;
	protected CourseService courseService;
	protected CreditvaluelogService creditvaluelogService;
	protected GradeService gradeService;
	protected LocationService locationService;
	protected UserloginlogService userloginlogService;
	protected AdminloginlogService adminloginlogService;
	protected PrivilegeService privilegeService;
	protected ProblemService problemService;
	protected ProblemtypeService problemtypeService;
	protected QualificationrequestService qualificationrequestService;
	protected QualificationtypeService qualificationtypeService;
	protected ResourceService resourceService;
	protected RoleService roleService;
	protected SolveService solveService;
	protected TermService termService;
	protected UserService userService;
	public AcademyService getAcademyService() {
		return academyService;
	}
	public void setAcademyService(AcademyService academyService) {
		this.academyService = academyService;
	}
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public ComplaintrequestService getComplaintrequestService() {
		return complaintrequestService;
	}
	public void setComplaintrequestService(
			ComplaintrequestService complaintrequestService) {
		this.complaintrequestService = complaintrequestService;
	}
	public CourseService getCourseService() {
		return courseService;
	}
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	public CreditvaluelogService getCreditvaluelogService() {
		return creditvaluelogService;
	}
	public void setCreditvaluelogService(CreditvaluelogService creditvaluelogService) {
		this.creditvaluelogService = creditvaluelogService;
	}
	public GradeService getGradeService() {
		return gradeService;
	}
	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
	public LocationService getLocationService() {
		return locationService;
	}
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	public UserloginlogService getUserloginlogService() {
		return userloginlogService;
	}
	public void setUserloginlogService(UserloginlogService userloginlogService) {
		this.userloginlogService = userloginlogService;
	}
	public AdminloginlogService getAdminloginlogService() {
		return adminloginlogService;
	}
	public void setAdminloginlogService(AdminloginlogService adminloginlogService) {
		this.adminloginlogService = adminloginlogService;
	}
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	public ProblemService getProblemService() {
		return problemService;
	}
	public void setProblemService(ProblemService problemService) {
		this.problemService = problemService;
	}
	public ProblemtypeService getProblemtypeService() {
		return problemtypeService;
	}
	public void setProblemtypeService(ProblemtypeService problemtypeService) {
		this.problemtypeService = problemtypeService;
	}
	public QualificationrequestService getQualificationrequestService() {
		return qualificationrequestService;
	}
	public void setQualificationrequestService(
			QualificationrequestService qualificationrequestService) {
		this.qualificationrequestService = qualificationrequestService;
	}
	public QualificationtypeService getQualificationtypeService() {
		return qualificationtypeService;
	}
	public void setQualificationtypeService(
			QualificationtypeService qualificationtypeService) {
		this.qualificationtypeService = qualificationtypeService;
	}
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public SolveService getSolveService() {
		return solveService;
	}
	public void setSolveService(SolveService solveService) {
		this.solveService = solveService;
	}
	public TermService getTermService() {
		return termService;
	}
	public void setTermService(TermService termService) {
		this.termService = termService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
