package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege implements java.io.Serializable {

	// Fields

	private String uid;
	private Admin admin;
	private Integer id;
	private String name;
	private Integer privilegevalue;
	private String privilegecode;
	private String createip;
	private Integer createtime;
	private Short isvalid;
	private Set admins = new HashSet(0);

	// Constructors

	/** default constructor */
	public Privilege() {
	}

	/** minimal constructor */
	public Privilege(Admin admin, Integer id, String name,
			Integer privilegevalue, String privilegecode, String createip,
			Integer createtime, Short isvalid) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.privilegevalue = privilegevalue;
		this.privilegecode = privilegecode;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Privilege(Admin admin, Integer id, String name,
			Integer privilegevalue, String privilegecode, String createip,
			Integer createtime, Short isvalid, Set admins) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.privilegevalue = privilegevalue;
		this.privilegecode = privilegecode;
		this.createip = createip;
		this.createtime = createtime;
		this.isvalid = isvalid;
		this.admins = admins;
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

	public Integer getPrivilegevalue() {
		return this.privilegevalue;
	}

	public void setPrivilegevalue(Integer privilegevalue) {
		this.privilegevalue = privilegevalue;
	}

	public String getPrivilegecode() {
		return this.privilegecode;
	}

	public void setPrivilegecode(String privilegecode) {
		this.privilegecode = privilegecode;
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

	public Set getAdmins() {
		return this.admins;
	}

	public void setAdmins(Set admins) {
		this.admins = admins;
	}

}