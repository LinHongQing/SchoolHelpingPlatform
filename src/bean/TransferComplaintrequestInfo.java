package bean;

import java.util.List;

public class TransferComplaintrequestInfo {
	private String uid;
	private TransferUserInfo user;
	private int createtime;
	private String createip;
	private TransferProblemInfo problem;
	private String description;
	private String strresourceuid;
	private List<TransferResourceInfo> resource;
	private short status;
	private String replydescription;
	private String replyresourceuid;
	private String replycreateip;
	private int replycreatetime;
	private TransferAdminInfo replycreateuser;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public TransferUserInfo getUser() {
		return user;
	}
	public void setUser(TransferUserInfo user) {
		this.user = user;
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
	public TransferProblemInfo getProblem() {
		return problem;
	}
	public void setProblem(TransferProblemInfo problem) {
		this.problem = problem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStrresourceuid() {
		return strresourceuid;
	}
	public void setStrresourceuid(String strresourceuid) {
		this.strresourceuid = strresourceuid;
	}
	public List<TransferResourceInfo> getResource() {
		return resource;
	}
	public void setResource(List<TransferResourceInfo> resource) {
		this.resource = resource;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public String getReplydescription() {
		return replydescription;
	}
	public void setReplydescription(String replydescription) {
		this.replydescription = replydescription;
	}
	public String getReplyresourceuid() {
		return replyresourceuid;
	}
	public void setReplyresourceuid(String replyresourceuid) {
		this.replyresourceuid = replyresourceuid;
	}
	public String getReplycreateip() {
		return replycreateip;
	}
	public void setReplycreateip(String replycreateip) {
		this.replycreateip = replycreateip;
	}
	public int getReplycreatetime() {
		return replycreatetime;
	}
	public void setReplycreatetime(int replycreatetime) {
		this.replycreatetime = replycreatetime;
	}
	public TransferAdminInfo getReplycreateuser() {
		return replycreateuser;
	}
	public void setReplycreateuser(TransferAdminInfo replycreateuser) {
		this.replycreateuser = replycreateuser;
	}
	@Override
	public String toString() {
		return "TransferComplaintrequestInfo [uid=" + uid + ", user=" + user
				+ ", createtime=" + createtime + ", createip=" + createip
				+ ", problem=" + problem + ", description=" + description
				+ ", strresourceuid=" + strresourceuid + ", resource="
				+ resource + ", status=" + status + ", replydescription="
				+ replydescription + ", replyresourceuid=" + replyresourceuid
				+ ", replycreateip=" + replycreateip + ", replycreatetime="
				+ replycreatetime + ", replycreateuser=" + replycreateuser
				+ "]";
	}
}
