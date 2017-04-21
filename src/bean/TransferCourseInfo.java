package bean;

public class TransferCourseInfo {
	private String uid;
	private String name;
	private TransferAcademyInfo academy;
	private TransferAdminInfo createuser;
	private String createip;
	private int createtime;
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
	public TransferAcademyInfo getAcademy() {
		return academy;
	}
	public void setAcademy(TransferAcademyInfo academy) {
		this.academy = academy;
	}
	public TransferAdminInfo getCreateuser() {
		return createuser;
	}
	public void setCreateuser(TransferAdminInfo createuser) {
		this.createuser = createuser;
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
		return "TransferCourseInfo [uid=" + uid + ", name=" + name
				+ ", academy=" + academy + ", createuser=" + createuser
				+ ", createip=" + createip + ", createtime=" + createtime + "]";
	}
}
