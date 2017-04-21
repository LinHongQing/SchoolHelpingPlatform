package bean;

import java.util.List;

public class TransferSolveInfo {
	private String uid;
	private TransferProblemInfo problem;
	private TransferUserInfo createuser;
	private String strassistantuids;
	private List<TransferUserInfo> assistant;
	private String description;
	private String createip;
	private int createtime;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public TransferProblemInfo getProblem() {
		return problem;
	}
	public void setProblem(TransferProblemInfo problem) {
		this.problem = problem;
	}
	public TransferUserInfo getCreateuser() {
		return createuser;
	}
	public void setCreateuser(TransferUserInfo createuser) {
		this.createuser = createuser;
	}
	public String getStrassistantuids() {
		return strassistantuids;
	}
	public void setStrassistantuids(String strassistantuids) {
		this.strassistantuids = strassistantuids;
	}
	public List<TransferUserInfo> getAssistant() {
		return assistant;
	}
	public void setAssistant(List<TransferUserInfo> assistant) {
		this.assistant = assistant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return "TransferSolveInfo [uid=" + uid + ", problem=" + problem
				+ ", createuser=" + createuser + ", strassistantuids="
				+ strassistantuids + ", assistant=" + assistant + ", description="
				+ description + ", createip=" + createip + ", createtime="
				+ createtime + "]";
	}
}
