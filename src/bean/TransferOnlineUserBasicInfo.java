package bean;

public class TransferOnlineUserBasicInfo {
	private TransferUserInfo user;
	private TransferAdminInfo admin;
	private String userloginip;
	private int userlogintime;
	private String userlastloginip;
	private int userlastlogintime;
	private String adminloginip;
	private int adminlogintime;
	private String adminlastloginip;
	private int adminlastlogintime;
	private int userlogincount;
	private int adminlogincount;
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
	public String getUserloginip() {
		return userloginip;
	}
	public void setUserloginip(String userloginip) {
		this.userloginip = userloginip;
	}
	public int getUserlogintime() {
		return userlogintime;
	}
	public void setUserlogintime(int userlogintime) {
		this.userlogintime = userlogintime;
	}
	public String getUserlastloginip() {
		return userlastloginip;
	}
	public void setUserlastloginip(String userlastloginip) {
		this.userlastloginip = userlastloginip;
	}
	public int getUserlastlogintime() {
		return userlastlogintime;
	}
	public void setUserlastlogintime(int userlastlogintime) {
		this.userlastlogintime = userlastlogintime;
	}
	public String getAdminloginip() {
		return adminloginip;
	}
	public void setAdminloginip(String adminloginip) {
		this.adminloginip = adminloginip;
	}
	public int getAdminlogintime() {
		return adminlogintime;
	}
	public void setAdminlogintime(int adminlogintime) {
		this.adminlogintime = adminlogintime;
	}
	public String getAdminlastloginip() {
		return adminlastloginip;
	}
	public void setAdminlastloginip(String adminlastloginip) {
		this.adminlastloginip = adminlastloginip;
	}
	public int getAdminlastlogintime() {
		return adminlastlogintime;
	}
	public void setAdminlastlogintime(int adminlastlogintime) {
		this.adminlastlogintime = adminlastlogintime;
	}
	public int getUserlogincount() {
		return userlogincount;
	}
	public void setUserlogincount(int userlogincount) {
		this.userlogincount = userlogincount;
	}
	public int getAdminlogincount() {
		return adminlogincount;
	}
	public void setAdminlogincount(int adminlogincount) {
		this.adminlogincount = adminlogincount;
	}
	@Override
	public String toString() {
		return "TransferOnlineUserBasicInfo [user=" + user + ", admin=" + admin
				+ ", userloginip=" + userloginip + ", userlogintime="
				+ userlogintime + ", userlastloginip=" + userlastloginip
				+ ", userlastlogintime=" + userlastlogintime
				+ ", adminloginip=" + adminloginip + ", adminlogintime="
				+ adminlogintime + ", adminlastloginip=" + adminlastloginip
				+ ", adminlastlogintime=" + adminlastlogintime
				+ ", userlogincount=" + userlogincount + ", adminlogincount="
				+ adminlogincount + "]";
	}
}
