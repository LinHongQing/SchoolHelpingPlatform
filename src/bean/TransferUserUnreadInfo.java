package bean;

public class TransferUserUnreadInfo {
	private String userUid;
	private String userName;
	private int count;
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "TransferUserUnreadInfo [userUid=" + userUid + ", userName="
				+ userName + ", count=" + count + "]";
	}
}
