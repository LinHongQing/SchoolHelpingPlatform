package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String uid;
	private Course course;
	private Role role;
	private Location location;
	private Grade grade;
	private Academy academy;
	private Qualificationtype qualificationtype;
	private Integer id;
	private String name;
	private String num;
	private String nickname;
	private String pwd;
	private String resourceid;
	private Integer creditvalue;
	private String createip;
	private Integer createtime;
	private Short isvalid;
	private Set creditvaluelogs = new HashSet(0);
	private Set qualificationrequests = new HashSet(0);
	private Set userloginlogs = new HashSet(0);
	private Set problems = new HashSet(0);
	private Set complaintrequests = new HashSet(0);
	private Set solves = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Course course, Role role, Location location, Grade grade,
			Academy academy, Integer id, String name, String num, String pwd,
			Integer creditvalue, String createip, Integer createtime,
			Short isvalid) {
		this.course = course;
		this.role = role;
		this.location = location;
		this.grade = grade;
		this.academy = academy;
		this.id = id;
		this.name = name;
		this.num = num;
		this.pwd = pwd;
		this.creditvalue = creditvalue;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public User(Course course, Role role, Location location, Grade grade,
			Academy academy, Qualificationtype qualificationtype, Integer id,
			String name, String num, String nickname, String pwd,
			String resourceid, Integer creditvalue, String createip,
			Integer createtime, Short isvalid, Set creditvaluelogs,
			Set qualificationrequests, Set userloginlogs, Set problems,
			Set complaintrequests, Set solves) {
		this.course = course;
		this.role = role;
		this.location = location;
		this.grade = grade;
		this.academy = academy;
		this.qualificationtype = qualificationtype;
		this.id = id;
		this.name = name;
		this.num = num;
		this.nickname = nickname;
		this.pwd = pwd;
		this.resourceid = resourceid;
		this.creditvalue = creditvalue;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
		this.creditvaluelogs = creditvaluelogs;
		this.qualificationrequests = qualificationrequests;
		this.userloginlogs = userloginlogs;
		this.problems = problems;
		this.complaintrequests = complaintrequests;
		this.solves = solves;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Academy getAcademy() {
		return this.academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public Qualificationtype getQualificationtype() {
		return this.qualificationtype;
	}

	public void setQualificationtype(Qualificationtype qualificationtype) {
		this.qualificationtype = qualificationtype;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getResourceid() {
		return this.resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public Integer getCreditvalue() {
		return this.creditvalue;
	}

	public void setCreditvalue(Integer creditvalue) {
		this.creditvalue = creditvalue;
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

	public Set getCreditvaluelogs() {
		return this.creditvaluelogs;
	}

	public void setCreditvaluelogs(Set creditvaluelogs) {
		this.creditvaluelogs = creditvaluelogs;
	}

	public Set getQualificationrequests() {
		return this.qualificationrequests;
	}

	public void setQualificationrequests(Set qualificationrequests) {
		this.qualificationrequests = qualificationrequests;
	}

	public Set getUserloginlogs() {
		return this.userloginlogs;
	}

	public void setUserloginlogs(Set userloginlogs) {
		this.userloginlogs = userloginlogs;
	}

	public Set getProblems() {
		return this.problems;
	}

	public void setProblems(Set problems) {
		this.problems = problems;
	}

	public Set getComplaintrequests() {
		return this.complaintrequests;
	}

	public void setComplaintrequests(Set complaintrequests) {
		this.complaintrequests = complaintrequests;
	}

	public Set getSolves() {
		return this.solves;
	}

	public void setSolves(Set solves) {
		this.solves = solves;
	}

}