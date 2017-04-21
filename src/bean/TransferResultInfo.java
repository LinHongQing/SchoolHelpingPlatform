package bean;

public class TransferResultInfo<T> {
	private String msgType;
	private String msgCode;
	private T msgContent;
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public T getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(T msgContent) {
		this.msgContent = msgContent;
	}
	@Override
	public String toString() {
		return "ResultInfo [msgType=" + msgType + ", msgCode=" + msgCode
				+ ", msgContent=" + msgContent + "]";
	}
}
