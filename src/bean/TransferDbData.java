package bean;

import java.util.HashMap;
import java.util.Map;

public class TransferDbData {
	private Map<Integer, String> info;

	public TransferDbData() {
		super();
		this.info = new HashMap<Integer, String>();
	}
	
	public void insertValues(int key, String value) {
		info.put(key, value);
	}
	
	public Map<Integer, String> getValues() {
		return info;
	}
}
