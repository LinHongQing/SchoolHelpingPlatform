package bean;

public class TransferLoginlogInfo {
	private String uid;
	private TransferUserInfo user;
	private TransferAdminInfo admin;
	private int logintime;
	private String ip;
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
	public TransferAdminInfo getAdmin() {
		return admin;
	}
	public void setAdmin(TransferAdminInfo admin) {
		this.admin = admin;
	}
	public int getLogintime() {
		return logintime;
	}
	public void setLogintime(int logintime) {
		this.logintime = logintime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "TransferLoginlogInfo [uid=" + uid + ", user=" + user
				+ ", admin=" + admin + ", logintime=" + logintime + ", ip="
				+ ip + "]";
	}
}
