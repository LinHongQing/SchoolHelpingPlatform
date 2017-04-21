package bean;

public class TransferLocationInfo {
	private String uid;
	private String name;
	private float longitude;
	private float latitude;
	private TransferAdminInfo createuser;
	private int createtime;
	private String createip;
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
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public TransferAdminInfo getCreateuser() {
		return createuser;
	}
	public void setCreateuser(TransferAdminInfo createuser) {
		this.createuser = createuser;
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
	@Override
	public String toString() {
		return "TransferLocationInfo [uid=" + uid + ", name=" + name
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", createuser=" + createuser + ", createtime=" + createtime
				+ ", createip=" + createip + "]";
	}
}
