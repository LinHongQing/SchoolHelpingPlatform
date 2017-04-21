package bean;

public class TransferProblemInfo {
	private String uid;
	private TransferUserInfo createuser;
	private TransferProblemtypeInfo problemtype;
	private TransferLocationInfo location;
	private String title;
	private String description;
	private String preferday;
	private int preferstart;
	private int preferend;
	private int recommandvalue;
	private String resourceuid;
	private String createip;
	private int createtime;
	private int status;
	private TransferSolveInfo solved;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public TransferUserInfo getCreateuser() {
		return createuser;
	}
	public void setCreateuser(TransferUserInfo createuser) {
		this.createuser = createuser;
	}
	public TransferProblemtypeInfo getProblemtype() {
		return problemtype;
	}
	public void setProblemtype(TransferProblemtypeInfo problemtype) {
		this.problemtype = problemtype;
	}
	public TransferLocationInfo getLocation() {
		return location;
	}
	public void setLocation(TransferLocationInfo location) {
		this.location = location;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreferday() {
		return preferday;
	}
	public void setPreferday(String preferday) {
		this.preferday = preferday;
	}
	public int getPreferstart() {
		return preferstart;
	}
	public void setPreferstart(int preferstart) {
		this.preferstart = preferstart;
	}
	public int getPreferend() {
		return preferend;
	}
	public void setPreferend(int preferend) {
		this.preferend = preferend;
	}
	public int getRecommandvalue() {
		return recommandvalue;
	}
	public void setRecommandvalue(int recommandvalue) {
		this.recommandvalue = recommandvalue;
	}
	public String getResourceuid() {
		return resourceuid;
	}
	public void setResourceuid(String resourceuid) {
		this.resourceuid = resourceuid;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public TransferSolveInfo getSolved() {
		return solved;
	}
	public void setSolved(TransferSolveInfo solved) {
		this.solved = solved;
	}
	@Override
	public String toString() {
		return "TransferProblemInfo [uid=" + uid + ", createuser=" + createuser
				+ ", problemtype=" + problemtype + ", location=" + location
				+ ", title=" + title + ", description=" + description
				+ ", preferday=" + preferday + ", preferstart=" + preferstart
				+ ", preferend=" + preferend + ", recommandvalue="
				+ recommandvalue + ", resourceuid=" + resourceuid
				+ ", createip=" + createip + ", createtime=" + createtime
				+ ", status=" + status + ", solved=" + solved + "]";
	}
}
