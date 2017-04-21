package bean;

public class TransferGradeInfo {
	private String uid;
	private String name;
	private int admissiontime;
	private int graduationtime;
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
	public int getAdmissiontime() {
		return admissiontime;
	}
	public void setAdmissiontime(int admissiontime) {
		this.admissiontime = admissiontime;
	}
	public int getGraduationtime() {
		return graduationtime;
	}
	public void setGraduationtime(int graduationtime) {
		this.graduationtime = graduationtime;
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
		return "TransferGradeInfo [uid=" + uid + ", name=" + name
				+ ", admissiontime=" + admissiontime + ", graduationtime="
				+ graduationtime + ", createuser=" + createuser + ", createip="
				+ createip + ", createtime=" + createtime + "]";
	}
}
