package pojo;

/**
 * Term entity. @author MyEclipse Persistence Tools
 */

public class Term implements java.io.Serializable {

	// Fields

	private String uid;
	private Admin admin;
	private Integer id;
	private String name;
	private Integer starttime;
	private Integer endtime;
	private Integer createtime;
	private String createip;
	private Short isvalid;

	// Constructors

	/** default constructor */
	public Term() {
	}

	/** full constructor */
	public Term(Admin admin, Integer id, String name, Integer starttime,
			Integer endtime, Integer createtime, String createip, Short isvalid) {
		this.admin = admin;
		this.id = id;
		this.name = name;
		this.starttime = starttime;
		this.endtime = endtime;
		this.createtime = createtime;
		this.createip = createip;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Integer starttime) {
		this.starttime = starttime;
	}

	public Integer getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Integer endtime) {
		this.endtime = endtime;
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

}