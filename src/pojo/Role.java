package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private String uid;
	private Admin admin;
	private Integer id;
	private String name;
	private Integer rolevalue;
	private String rolecode;
	private Integer createtime;
	private String createip;
	private Short isvalid;
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(Admin admin, Integer id, String name, Integer rolevalue,
			String rolecode, Integer createtime, String createip, Short isvalid) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.rolevalue = rolevalue;
		this.rolecode = rolecode;
		this.createtime = createtime;
		this.createip = createip;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Role(Admin admin, Integer id, String name, Integer rolevalue,
			String rolecode, Integer createtime, String createip,
			Short isvalid, Set users) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.rolevalue = rolevalue;
		this.rolecode = rolecode;
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

	public Integer getRolevalue() {
		return this.rolevalue;
	}

	public void setRolevalue(Integer rolevalue) {
		this.rolevalue = rolevalue;
	}

	public String getRolecode() {
		return this.rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
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