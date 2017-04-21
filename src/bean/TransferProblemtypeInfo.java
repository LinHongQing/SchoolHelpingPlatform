package bean;

public class TransferProblemtypeInfo {
	private String uid;
	private String name;
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
		return "TransferProblemtypeInfo [uid=" + uid + ", name=" + name
				+ ", createuser=" + createuser + ", createtime=" + createtime
				+ ", createip=" + createip + "]";
	}
}
