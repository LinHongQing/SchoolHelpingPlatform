package bean;

public class TransferPrivilegeInfo {
	private String uid;
	private String name;
	private int privilegevalue;
	private String privilegecode;
	private TransferAdminInfo createuser;
	private int createtime;
	private String createip;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrivilegevalue() {
		return privilegevalue;
	}
	public void setPrivilegevalue(int privilegevalue) {
		this.privilegevalue = privilegevalue;
	}
	public String getPrivilegecode() {
		return privilegecode;
	}
	public void setPrivilegecode(String privilegecode) {
		this.privilegecode = privilegecode;
	}
	public TransferAdminInfo getCreateuser() {
		return createuser;
	}
	public void setCreateuser(TransferAdminInfo createuser) {
		this.createuser = createuser;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	public String getCreateip() {
		return createip;
	}
	public void setCreateip(String createip) {
		this.createip = createip;
	}
	@Override
	public String toString() {
		return "TransferPrivilegeInfo [uid=" + uid + ", name=" + name
				+ ", privilegevalue=" + privilegevalue + ", privilegecode="
				+ privilegecode + ", createuser=" + createuser
				+ ", createtime=" + createtime + ", createip=" + createip + "]";
	}
}
