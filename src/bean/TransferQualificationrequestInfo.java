package bean;

import java.util.List;

public class TransferQualificationrequestInfo {
	private String uid;
	private TransferUserInfo user;
	private TransferQualificationtypeInfo type;
	private String strresourceuid;
	private List<TransferResourceInfo> resource;
	private String description;
	private int requesttime;
	private String requestip;
	private short checkingstatus;
	private short checkingtype;
	private int checkingtime;
	private String checkingip;
	private TransferAdminInfo checkinguser;
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
	public TransferQualificationtypeInfo getType() {
		return type;
	}
	public void setType(TransferQualificationtypeInfo type) {
		this.type = type;
	}
	public String getStrresourceuid() {
		return strresourceuid;
	}
	public void setStrresourceuid(String resourceuid) {
		this.strresourceuid = resourceuid;
	}
	public List<TransferResourceInfo> getResource() {
		return resource;
	}
	public void setResource(List<TransferResourceInfo> resource) {
		this.resource = resource;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(int requesttime) {
		this.requesttime = requesttime;
	}
	public String getRequestip() {
		return requestip;
	}
	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}
	public short getCheckingstatus() {
		return checkingstatus;
	}
	public void setCheckingstatus(short checkingstatus) {
		this.checkingstatus = checkingstatus;
	}
	public short getCheckingtype() {
		return checkingtype;
	}
	public void setCheckingtype(short checkingtype) {
		this.checkingtype = checkingtype;
	}
	public int getCheckingtime() {
		return checkingtime;
	}
	public void setCheckingtime(int checkingtime) {
		this.checkingtime = checkingtime;
	}
	public String getCheckingip() {
		return checkingip;
	}
	public void setCheckingip(String checkingip) {
		this.checkingip = checkingip;
	}
	public TransferAdminInfo getCheckinguser() {
		return checkinguser;
	}
	public void setCheckinguser(TransferAdminInfo checkinguser) {
		this.checkinguser = checkinguser;
	}
	@Override
	public String toString() {
		return "TransferQualificationrequestInfo [uid=" + uid + ", user="
				+ user + ", type=" + type + ", strresourceuid="
				+ strresourceuid + ", resource=" + resource + ", description="
				+ description + ", requesttime=" + requesttime + ", requestip="
				+ requestip + ", checkingstatus=" + checkingstatus
				+ ", checkingtype=" + checkingtype + ", checkingtime="
				+ checkingtime + ", checkingip=" + checkingip
				+ ", checkinguser=" + checkinguser + "]";
	}
}
