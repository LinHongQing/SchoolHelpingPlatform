package bean;

public class TransferCreditvaluelogInfo {
	private String uid;
	private TransferUserInfo user;
	private int changevalue;
	private int finalvalue;
	private String reason;
	private int createtime;
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
	public int getChangevalue() {
		return changevalue;
	}
	public void setChangevalue(int changevalue) {
		this.changevalue = changevalue;
	}
	public int getFinalvalue() {
		return finalvalue;
	}
	public void setFinalvalue(int finalvalue) {
		this.finalvalue = finalvalue;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "TransferCreditvaluelogInfo [uid=" + uid + ", user=" + user
				+ ", changevalue=" + changevalue + ", finalvalue=" + finalvalue
				+ ", reason=" + reason + ", createtime=" + createtime + "]";
	}
}
