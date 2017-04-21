package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Solve entity. @author MyEclipse Persistence Tools
 */

public class Solve implements java.io.Serializable {

	// Fields

	private String uid;
	private User user;
	private Problem problem;
	private Integer id;
	private String assistantid;
	private String description;
	private Integer createtime;
	private String createip;
	private Short isvalid;
	private Set problems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Solve() {
	}

	/** minimal constructor */
	public Solve(String uid, User user, Problem problem, Integer id,
			Integer createtime, String createip, Short isvalid) {
		this.uid = uid;
		this.user = user;
		this.problem = problem;
		this.id = id;
		this.createtime = createtime;
		this.createip = createip;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Solve(String uid, User user, Problem problem, Integer id,
			String assistantid, String description, Integer createtime,
			String createip, Short isvalid, Set problems) {
		this.uid = uid;
		this.user = user;
		this.problem = problem;
		this.id = id;
		this.assistantid = assistantid;
		this.description = description;
		this.createtime = createtime;
		this.createip = createip;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Problem getProblem() {
		return this.problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssistantid() {
		return this.assistantid;
	}

	public void setAssistantid(String assistantid) {
		this.assistantid = assistantid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set getProblems() {
		return this.problems;
	}

	public void setProblems(Set problems) {
		this.problems = problems;
	}

}