package bean;

public class TransferTermInfo {
	private String uid;
	private String name;
	private int starttime;
	private int endtime;
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
	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	public int getEndtime() {
		return endtime;
	}
	public void setEndtime(int endtime) {
		this.endtime = endtime;
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
		return "TransferTermInfo [uid=" + uid + ", name=" + name
				+ ", starttime=" + starttime + ", endtime=" + endtime
				+ ", createuser=" + createuser + ", createtime=" + createtime
				+ ", createip=" + createip + "]";
	}
}
