package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade implements java.io.Serializable {

	// Fields

	private String uid;
	private Admin admin;
	private Integer id;
	private String name;
	private Integer admissiontime;
	private Integer graduationtime;
	private Integer createtime;
	private String createip;
	private Short isvalid;
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Grade() {
	}

	/** minimal constructor */
	public Grade(Admin admin, Integer id, String name, Integer admissiontime,
			Integer graduationtime, Integer createtime, String createip,
			Short isvalid) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.admissiontime = admissiontime;
		this.graduationtime = graduationtime;
		this.createtime = createtime;
		this.createip = createip;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Grade(Admin admin, Integer id, String name, Integer admissiontime,
			Integer graduationtime, Integer createtime, String createip,
			Short isvalid, Set users) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.admissiontime = admissiontime;
		this.graduationtime = graduationtime;
		this.createtime = createtime;
		this.createip = createip;
		this.isvalid = isvalid;
		this.users = users;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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

	public Integer getAdmissiontime() {
		return this.admissiontime;
	}

	public void setAdmissiontime(Integer admissiontime) {
		this.admissiontime = admissiontime;
	}

	public Integer getGraduationtime() {
		return this.graduationtime;
	}

	public void setGraduationtime(Integer graduationtime) {
		this.graduationtime = graduationtime;
	}

	public Integer getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public String getCreateip() {
		return this.createip;
	}

	public void setCreateip(String createip) {
		this.createip = createip;
	}

	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}