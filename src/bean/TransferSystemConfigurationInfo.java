package bean;

import cache.Configurations;

public class TransferSystemConfigurationInfo {
	private String chatsystempath;
	private int creditvaluechangeforcreator;
	private int creditvaluechangeforsolver;
	private int defaultusercreditvalue;
	private String defaultuserroleuid;
	
	public TransferSystemConfigurationInfo() {
		super();
		creditvaluechangeforcreator = Configurations.int_invalid;
		creditvaluechangeforsolver = Configurations.int_invalid;
		defaultusercreditvalue = Configurations.int_invalid;
	}

	public String getChatsystempath() {
		return chatsystempath;
	}

	public void setChatsystempath(String chatsystempath) {
		this.chatsystempath = chatsystempath;
	}

	public int getCreditvaluechangeforcreator() {
		return creditvaluechangeforcreator;
	}

	public void setCreditvaluechangeforcreator(int creditvaluechangeforcreator) {
		this.creditvaluechangeforcreator = creditvaluechangeforcreator;
	}

	public int getCreditvaluechangeforsolver() {
		return creditvaluechangeforsolver;
	}

	public void setCreditvaluechangeforsolver(int creditvaluechangeforsolver) {
		this.creditvaluechangeforsolver = creditvaluechangeforsolver;
	}

	public int getDefaultusercreditvalue() {
		return defaultusercreditvalue;
	}

	public void setDefaultusercreditvalue(int defaultusercreditvalue) {
		this.defaultusercreditvalue = defaultusercreditvalue;
	}

	public String getDefaultuserroleuid() {
		return defaultuserroleuid;
	}

	public void setDefaultuserroleuid(String defaultuserroleuid) {
		this.defaultuserroleuid = defaultuserroleuid;
	}

	@Override
	public String toString() {
		return "TransferSystemConfigurationInfo [chatsystempath="
				+ chatsystempath + ", creditvaluechangeforcreator="
				+ creditvaluechangeforcreator + ", creditvaluechangeforsolver="
				+ creditvaluechangeforsolver + ", defaultusercreditvalue="
				+ defaultusercreditvalue + ", defaultuserroleuid="
				+ defaultuserroleuid + "]";
	}
	
}
