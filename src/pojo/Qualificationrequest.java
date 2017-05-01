package pojo;

/**
 * Qualificationrequest entity. @author MyEclipse Persistence Tools
 */

public class Qualificationrequest implements java.io.Serializable {

	// Fields

	private String uid;
	private User user;
	private Qualificationtype qualificationtype;
	private Admin admin;
	private Integer id;
	private String description;
	private String resourceid;
	private Integer requesttime;
	private String requestip;
	private Short checkingstatus;
	private Short checkingtype;
	private Integer checkingtime;
	private String checkingip;
	private Short isvalid;

	// Constructors

	/** default constructor */
	public Qualificationrequest() {
	}

	/** minimal constructor */
	public Qualificationrequest(User user, Qualificationtype qualificationtype,
			Integer id, String description, Integer requesttime,
			String requestip, Short checkingstatus,	Short isvalid) {
		this.user = user;
		this.qualificationtype = qualificationtype;
		this.id = id;
		this.description = description;
		this.requesttime = requesttime;
		this.requestip = requestip;
		this.checkingstatus = checkingstatus;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Qualificationrequest(User user, Qualificationtype qualificationtype,
			Admin admin, Integer id, String description, String resourceid,
			Integer requesttime, String requestip, Short checkingstatus,
			Short checkingtype, Integer checkingtime, String checkingip,
			Short isvalid) {
		this.user = user;
		this.qualificationtype = qualificationtype;
		this.admin = admin;
		this.id = id;
		this.description = description;
		this.resourceid = resourceid;
		this.requesttime = requesttime;
		this.requestip = requestip;
		this.checkingstatus = checkingstatus;
		this.checkingtype = checkingtype;
		this.checkingtime = checkingtime;
		this.checkingip = checkingip;
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

	public Qualificationtype getQualificationtype() {
		return this.qualificationtype;
	}

	public void setQualificationtype(Qualificationtype qualificationtype) {
		this.qualificationtype = qualificationtype;
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

	public Integer getRequesttime() {
		return this.requesttime;
	}

	public void setRequesttime(Integer requesttime) {
		this.requesttime = requesttime;
	}

	public String getRequestip() {
		return this.requestip;
	}

	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}

	public Short getCheckingstatus() {
		return this.checkingstatus;
	}

	public void setCheckingstatus(Short checkingstatus) {
		this.checkingstatus = checkingstatus;
	}

	public Short getCheckingtype() {
		return this.checkingtype;
	}

	public void setCheckingtype(Short checkingtype) {
		this.checkingtype = checkingtype;
	}

	public Integer getCheckingtime() {
		return this.checkingtime;
	}

	public void setCheckingtime(Integer checkingtime) {
		this.checkingtime = checkingtime;
	}

	public String getCheckingip() {
		return this.checkingip;
	}

	public void setCheckingip(String checkingip) {
		this.checkingip = checkingip;
	}

	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

}