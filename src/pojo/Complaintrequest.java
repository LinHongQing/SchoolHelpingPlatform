package pojo;

/**
 * Complaintrequest entity. @author MyEclipse Persistence Tools
 */

public class Complaintrequest implements java.io.Serializable {

	// Fields

	private String uid;
	private User user;
	private Admin admin;
	private Problem problem;
	private Integer id;
	private Integer createtime;
	private String createip;
	private String description;
	private String resourceid;
	private Integer status;
	private String replydescription;
	private String replyresourceid;
	private Integer replycreatetime;
	private String replycreateip;
	private Short isvalid;

	// Constructors

	/** default constructor */
	public Complaintrequest() {
	}

	/** minimal constructor */
	public Complaintrequest(User user, Problem problem, Integer id,
			Integer createtime, String createip, String description,
			Integer status, Short isvalid) {
		this.user = user;
		this.problem = problem;
		this.id = id;
		this.createtime = createtime;
		this.createip = createip;
		this.description = description;
		this.status = status;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Complaintrequest(User user, Admin admin, Problem problem,
			Integer id, Integer createtime, String createip,
			String description, String resourceid, Integer status,
			String replydescription, String replyresourceid,
			Integer replycreatetime, String replycreateip, Short isvalid) {
		this.user = user;
		this.admin = admin;
		this.problem = problem;
		this.id = id;
		this.createtime = createtime;
		this.createip = createip;
		this.description = description;
		this.resourceid = resourceid;
		this.status = status;
		this.replydescription = replydescription;
		this.replyresourceid = replyresourceid;
		this.replycreatetime = replycreatetime;
		this.replycreateip = replycreateip;
		this.isvalid = isvalid;
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

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReplydescription() {
		return this.replydescription;
	}

	public void setReplydescription(String replydescription) {
		this.replydescription = replydescription;
	}

	public String getReplyresourceid() {
		return this.replyresourceid;
	}

	public void setReplyresourceid(String replyresourceid) {
		this.replyresourceid = replyresourceid;
	}

	public Integer getReplycreatetime() {
		return this.replycreatetime;
	}

	public void setReplycreatetime(Integer replycreatetime) {
		this.replycreatetime = replycreatetime;
	}

	public String getReplycreateip() {
		return this.replycreateip;
	}

	public void setReplycreateip(String replycreateip) {
		this.replycreateip = replycreateip;
	}

	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

}