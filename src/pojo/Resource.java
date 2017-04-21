package pojo;

/**
 * Resource entity. @author MyEclipse Persistence Tools
 */

public class Resource implements java.io.Serializable {

	// Fields

	private String uid;
	private Integer id;
	private Integer type;
	private String name;
	private String value;
	private Short isvalid;

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** minimal constructor */
	public Resource(Integer id, Integer type, String value, Short isvalid) {
		this.id = id;
		this.type = type;
		this.value = value;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Resource(Integer id, Integer type, String name, String value,
			Short isvalid) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.value = value;
		this.isvalid = isvalid;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

}