package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Qualificationtype entity. @author MyEclipse Persistence Tools
 */

public class Qualificationtype implements java.io.Serializable {

	// Fields

	private String uid;
	private Admin admin;
	private Integer id;
	private String name;
	private String problemtypeid;
	private String createip;
	private Integer createtime;
	private Short isvalid;
	private Set qualificationrequests = new HashSet(0);
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Qualificationtype() {
	}

	/** minimal constructor */
	public Qualificationtype(Admin admin, Integer id, String name,
			String problemtypeid, String createip, Integer createtime,
			Short isvalid) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.problemtypeid = problemtypeid;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Qualificationtype(Admin admin, Integer id, String name,
			String problemtypeid, String createip, Integer createtime,
			Short isvalid, Set qualificationrequests, Set users) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.problemtypeid = problemtypeid;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
		this.qualificationrequests = qualificationrequests;
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

	public String getProblemtypeid() {
		return this.problemtypeid;
	}

	public void setProblemtypeid(String problemtypeid) {
		this.problemtypeid = problemtypeid;
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

	public Set getQualificationrequests() {
		return this.qualificationrequests;
	}

	public void setQualificationrequests(Set qualificationrequests) {
		this.qualificationrequests = qualificationrequests;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}