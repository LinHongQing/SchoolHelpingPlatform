package pojo;

/**
 * Creditvaluelog entity. @author MyEclipse Persistence Tools
 */

public class Creditvaluelog implements java.io.Serializable {

	// Fields

	private String uid;
	private User user;
	private Integer id;
	private Integer changevalue;
	private Integer finalvalue;
	private Integer createtime;
	private String reason;

	// Constructors

	/** default constructor */
	public Creditvaluelog() {
	}

	/** full constructor */
	public Creditvaluelog(User user, Integer id, Integer changevalue,
			Integer finalvalue, Integer createtime, String reason) {
		this.user = user;
		this.id = id;
		this.changevalue = changevalue;
		this.finalvalue = finalvalue;
		this.createtime = createtime;
		this.reason = reason;
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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChangevalue() {
		return this.changevalue;
	}

	public void setChangevalue(Integer changevalue) {
		this.changevalue = changevalue;
	}

	public Integer getFinalvalue() {
		return this.finalvalue;
	}

	public void setFinalvalue(Integer finalvalue) {
		this.finalvalue = finalvalue;
	}

	public Integer getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}