package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private String uid;
	private Privilege privilege;
	private Integer id;
	private String email;
	private String name;
	private String pwd;
	private String createip;
	private Integer createtime;
	private Short isvalid;
	private Set locations = new HashSet(0);
	private Set qualificationrequests = new HashSet(0);
	private Set qualificationtypes = new HashSet(0);
	private Set terms = new HashSet(0);
	private Set academies = new HashSet(0);
	private Set complaintrequests = new HashSet(0);
	private Set roles = new HashSet(0);
	private Set courses = new HashSet(0);
	private Set problemtypes = new HashSet(0);
	private Set privileges = new HashSet(0);
	private Set adminloginlogs = new HashSet(0);
	private Set grades = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(Integer id, String email, String name, String pwd,
			String createip, Integer createtime, Short isvalid) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Admin(Privilege privilege, Integer id, String email, String name,
			String pwd, String createip, Integer createtime, Short isvalid,
			Set locations, Set qualificationrequests, Set qualificationtypes,
			Set terms, Set academies, Set complaintrequests, Set roles,
			Set courses, Set problemtypes, Set privileges, Set adminloginlogs,
			Set grades) {
		this.privilege = privilege;
		this.id = id;
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
		this.locations = locations;
		this.qualificationrequests = qualificationrequests;
		this.qualificationtypes = qualificationtypes;
		this.terms = terms;
		this.academies = academies;
		this.complaintrequests = complaintrequests;
		this.roles = roles;
		this.courses = courses;
		this.problemtypes = problemtypes;
		this.privileges = privileges;
		this.adminloginlogs = adminloginlogs;
		this.grades = grades;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCreateip() {
		return this.createip;
	}

	public void setCreateip(String createip) {
		this.createip = createip;
	}

	public Integer getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

	public Set getLocations() {
		return this.locations;
	}

	public void setLocations(Set locations) {
		this.locations = locations;
	}

	public Set getQualificationrequests() {
		return this.qualificationrequests;
	}

	public void setQualificationrequests(Set qualificationrequests) {
		this.qualificationrequests = qualificationrequests;
	}

	public Set getQualificationtypes() {
		return this.qualificationtypes;
	}

	public void setQualificationtypes(Set qualificationtypes) {
		this.qualificationtypes = qualificationtypes;
	}

	public Set getTerms() {
		return this.terms;
	}

	public void setTerms(Set terms) {
		this.terms = terms;
	}

	public Set getAcademies() {
		return this.academies;
	}

	public void setAcademies(Set academies) {
		this.academies = academies;
	}

	public Set getComplaintrequests() {
		return this.complaintrequests;
	}

	public void setComplaintrequests(Set complaintrequests) {
		this.complaintrequests = complaintrequests;
	}

	public Set getRoles() {
		return this.roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public Set getCourses() {
		return this.courses;
	}

	public void setCourses(Set courses) {
		this.courses = courses;
	}

	public Set getProblemtypes() {
		return this.problemtypes;
	}

	public void setProblemtypes(Set problemtypes) {
		this.problemtypes = problemtypes;
	}

	public Set getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(Set privileges) {
		this.privileges = privileges;
	}

	public Set getAdminloginlogs() {
		return this.adminloginlogs;
	}

	public void setAdminloginlogs(Set adminloginlogs) {
		this.adminloginlogs = adminloginlogs;
	}

	public Set getGrades() {
		return this.grades;
	}

	public void setGrades(Set grades) {
		this.grades = grades;
	}

}