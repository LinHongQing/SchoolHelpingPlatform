package bean;

public class TransferResourceInfo {
	private String uid;
	private String name;
	private int type;
	private String value;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "TransferResourceListInfo [uid=" + uid + ", name=" + name
				+ ", type=" + type + ", value=" + value + "]";
	}
}
