package bean;

public class TransferAdminInfo {
	private String uid;
	private String email;
	private String name;
	private TransferPrivilegeInfo privilege;
	private String createip;
	private int createtime;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TransferPrivilegeInfo getPrivilege() {
		return privilege;
	}
	public void setPrivilege(TransferPrivilegeInfo privilege) {
		this.privilege = privilege;
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
	@Override
	public String toString() {
		return "TransferAdminInfo [uid=" + uid + ", email=" + email + ", name="
				+ name + ", privilege=" + privilege + ", createip=" + createip
				+ ", createtime=" + createtime + "]";
	}
}
