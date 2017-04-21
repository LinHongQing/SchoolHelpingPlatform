package util;

import java.util.ArrayList;
import java.util.List;

import exception.IllegalEmptyListException;

public final class DbStringUtil {
	
	private static final String segmentationCharacter = ".";

	public static final List<String> String2List(String sourceString) {
		String[] rsString = sourceString.split(segmentationCharacter);
		List<String> rsList = new ArrayList<String>();
		for (int i = 0; i < rsString.length; i++) {
			rsList.add(rsString[i]);
		}
		return rsList;
	}

	public static final String List2String(List<String> sourceList) throws IllegalEmptyListException {
		if (sourceList.isEmpty() || sourceList == null) {
			throw new IllegalEmptyListException("传入 List 内容非法!");
		}
		int size = sourceList.size();
		String rsString = "";
		for (int i = 0; i < size; i++) {
			rsString += sourceList.get(i);
			if (i == size - 1)
				break;
			rsString += ",";
		}
		return rsString;
	}
}
