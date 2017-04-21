package pojo;

/**
 * Adminloginlog entity. @author MyEclipse Persistence Tools
 */

public class Adminloginlog implements java.io.Serializable {

	// Fields

	private String uid;
	private Admin admin;
	private Integer id;
	private String ip;
	private Integer logintime;
	private Short isvalid;

	// Constructors

	/** default constructor */
	public Adminloginlog() {
	}

	/** full constructor */
	public Adminloginlog(Admin admin, Integer id, String ip, Integer logintime,
			Short isvalid) {
		this.admin = admin;
		this.id = id;
		this.ip = ip;
		this.logintime = logintime;
		this.isvalid = isvalid;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
	}

	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

}