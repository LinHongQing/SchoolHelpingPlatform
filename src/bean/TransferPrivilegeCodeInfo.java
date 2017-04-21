package bean;

public class TransferPrivilegeCodeInfo {
	private int value;
	private String description;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "TransferPrivilegeCodeInfo [value=" + value + ", description="
				+ description + "]";
	}
}
