package bean;

public class TransferRoleInfo {
	private String uid;
	private String name;
	private int rolevalue;
	private String rolecode;
	private String createip;
	private int createtime;
	private TransferAdminInfo createuser;
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
	public int getRolevalue() {
		return rolevalue;
	}
	public void setRolevalue(int rolevalue) {
		this.rolevalue = rolevalue;
	}
	public String getRolecode() {
		return rolecode;
	}
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public String getCreateip() {
		return createip;
	}
	public void setCreateip(String createip) {
		this.createip = createip;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	public TransferAdminInfo getCreateuser() {
		return createuser;
	}
	public void setCreateuser(TransferAdminInfo createuser) {
		this.createuser = createuser;
	}
	@Override
	public String toString() {
		return "TransferRoleInfo [uid=" + uid + ", name=" + name
				+ ", rolevalue=" + rolevalue + ", rolecode=" + rolecode
				+ ", createip=" + createip + ", createtime=" + createtime
				+ ", createuser=" + createuser + "]";
	}
}
