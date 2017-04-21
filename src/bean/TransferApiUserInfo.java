package bean;

public class TransferApiUserInfo {
	private String usrUid;
	private String usrNickname;
	private String usrImgResourcePath;
	public String getUsrUid() {
		return usrUid;
	}
	public void setUsrUid(String usrUid) {
		this.usrUid = usrUid;
	}
	public String getUsrNickname() {
		return usrNickname;
	}
	public void setUsrNickname(String usrNickname) {
		this.usrNickname = usrNickname;
	}
	public String getUsrImgResourcePath() {
		return usrImgResourcePath;
	}
	public void setUsrImgResourcePath(String usrImgResourcePath) {
		this.usrImgResourcePath = usrImgResourcePath;
	}
	@Override
	public String toString() {
		return "UserInfo [usrUid=" + usrUid + ", usrNickname=" + usrNickname
				+ ", usrImgResourceUid=" + usrImgResourcePath + "]";
	}
}
