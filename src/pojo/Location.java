package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Location entity. @author MyEclipse Persistence Tools
 */

public class Location implements java.io.Serializable {

	// Fields

	private String uid;
	private Admin admin;
	private Integer id;
	private String name;
	private Integer createtime;
	private String createip;
	private Float longitude;
	private Float latitude;
	private Short isvalid;
	private Set users = new HashSet(0);
	private Set problems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Location() {
	}

	/** minimal constructor */
	public Location(Admin admin, Integer id, String name, Integer createtime,
			String createip, Float longitude, Float latitude, Short isvalid) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.createtime = createtime;
		this.createip = createip;
		this.longitude = longitude;
		this.latitude = latitude;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Location(Admin admin, Integer id, String name, Integer createtime,
			String createip, Float longitude, Float latitude, Short isvalid,
			Set users, Set problems) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.createtime = createtime;
		this.createip = createip;
		this.longitude = longitude;
		this.latitude = latitude;
		this.isvalid = isvalid;
		this.users = users;
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

	public Float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
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

	public Set getProblems() {
		return this.problems;
	}

	public void setProblems(Set problems) {
		this.problems = problems;
	}

}