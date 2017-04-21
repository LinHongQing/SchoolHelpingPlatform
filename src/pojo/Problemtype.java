package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Problemtype entity. @author MyEclipse Persistence Tools
 */

public class Problemtype implements java.io.Serializable {

	// Fields

	private String uid;
	private Admin admin;
	private Integer id;
	private String name;
	private String createip;
	private Integer createtime;
	private Short isvalid;
	private Set problems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Problemtype() {
	}

	/** minimal constructor */
	public Problemtype(Admin admin, Integer id, String name, String createip,
			Integer createtime, Short isvalid) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Problemtype(Admin admin, Integer id, String name, String createip,
			Integer createtime, Short isvalid, Set problems) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
		this.problems = problems;
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

	public Set getProblems() {
		return this.problems;
	}

	public void setProblems(Set problems) {
		this.problems = problems;
	}

}