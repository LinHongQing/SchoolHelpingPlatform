package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem entity. @author MyEclipse Persistence Tools
 */

public class Problem implements java.io.Serializable {

	// Fields

	private String uid;
	private Solve solve;
	private Location location;
	private User user;
	private Problemtype problemtype;
	private Integer id;
	private String preferday;
	private Integer preferstart;
	private Integer preferend;
	private String title;
	private String description;
	private String resourceid;
	private Integer createtime;
	private String createip;
	private Integer status;
	private Short isvalid;
	private Set solves = new HashSet(0);
	private Set complaintrequests = new HashSet(0);

	// Constructors

	/** default constructor */
	public Problem() {
	}

	/** minimal constructor */
	public Problem(Location location, User user, Problemtype problemtype,
			Integer id, String title, String description, Integer createtime,
			String createip, Integer status, Short isvalid) {
		this.location = location;
		this.user = user;
		this.problemtype = problemtype;
		this.id = id;
		this.title = title;
		this.description = description;
		this.createtime = createtime;
		this.createip = createip;
		this.status = status;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Problem(Solve solve, Location location, User user,
			Problemtype problemtype, Integer id, String preferday,
			Integer preferstart, Integer preferend, String title,
			String description, String resourceid, Integer createtime,
			String createip, Integer status, Short isvalid, Set solves,
			Set complaintrequests) {
		this.solve = solve;
		this.location = location;
		this.user = user;
		this.problemtype = problemtype;
		this.id = id;
		this.preferday = preferday;
		this.preferstart = preferstart;
		this.preferend = preferend;
		this.title = title;
		this.description = description;
		this.resourceid = resourceid;
		this.createtime = createtime;
		this.createip = createip;
		this.status = status;
		this.isvalid = isvalid;
		this.solves = solves;
		this.complaintrequests = complaintrequests;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Solve getSolve() {
		return this.solve;
	}

	public void setSolve(Solve solve) {
		this.solve = solve;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Problemtype getProblemtype() {
		return this.problemtype;
	}

	public void setProblemtype(Problemtype problemtype) {
		this.problemtype = problemtype;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPreferday() {
		return this.preferday;
	}

	public void setPreferday(String preferday) {
		this.preferday = preferday;
	}

	public Integer getPreferstart() {
		return this.preferstart;
	}

	public void setPreferstart(Integer preferstart) {
		this.preferstart = preferstart;
	}

	public Integer getPreferend() {
		return this.preferend;
	}

	public void setPreferend(Integer preferend) {
		this.preferend = preferend;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceid() {
		return this.resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

	public Set getSolves() {
		return this.solves;
	}

	public void setSolves(Set solves) {
		this.solves = solves;
	}

	public Set getComplaintrequests() {
		return this.complaintrequests;
	}

	public void setComplaintrequests(Set complaintrequests) {
		this.complaintrequests = complaintrequests;
	}

}