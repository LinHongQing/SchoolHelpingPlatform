package bean;

public final class TransferUserInfo {
	private String uid;
	private String name;
	private String num;
	private String nickname;
	private TransferLocationInfo location;
	private TransferAcademyInfo academy;
	private TransferCourseInfo course;
	private TransferGradeInfo grade;
	private TransferRoleInfo role;
	private TransferQualificationtypeInfo qualtype;
	private int creditvalue;
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public TransferLocationInfo getLocation() {
		return location;
	}
	public void setLocation(TransferLocationInfo location) {
		this.location = location;
	}
	public TransferAcademyInfo getAcademy() {
		return academy;
	}
	public void setAcademy(TransferAcademyInfo academy) {
		this.academy = academy;
	}
	public TransferCourseInfo getCourse() {
		return course;
	}
	public void setCourse(TransferCourseInfo course) {
		this.course = course;
	}
	public TransferGradeInfo getGrade() {
		return grade;
	}
	public void setGrade(TransferGradeInfo grade) {
		this.grade = grade;
	}
	public TransferRoleInfo getRole() {
		return role;
	}
	public void setRole(TransferRoleInfo role) {
		this.role = role;
	}
	public TransferQualificationtypeInfo getQualtype() {
		return qualtype;
	}
	public void setQualtype(TransferQualificationtypeInfo qualtype) {
		this.qualtype = qualtype;
	}
	public int getCreditvalue() {
		return creditvalue;
	}
	public void setCreditvalue(int creditvalue) {
		this.creditvalue = creditvalue;
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
		return "TransferUserInfo [uid=" + uid + ", name=" + name + ", num="
				+ num + ", nickname=" + nickname + ", location=" + location
				+ ", academy=" + academy + ", course=" + course + ", grade="
				+ grade + ", role=" + role + ", qualtype=" + qualtype
				+ ", creditvalue=" + creditvalue + ", createip=" + createip
				+ ", createtime=" + createtime + "]";
	}
}
