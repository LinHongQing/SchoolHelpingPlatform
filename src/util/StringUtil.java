package util;

import java.util.List;
import java.util.Random;

import cache.Configurations;
import cache.ResultCodeStorage;

public class StringUtil {
	
	private static final int default_length = 16;
	
	public static String stringsCombine(String... strings) {
		if (strings == null || strings.length == 0)
			return null;
		StringBuffer sb = new StringBuffer();
		int len = strings.length, i = 0;
		for (String string : strings) {
			sb.append(string);
			i++;
			if (i < len) {
				sb.append(Configurations.string_split);
			}
		}
		return sb.toString();
	}
	
	public static String stringsCombine(List<String> strings) {
		if (strings == null || strings.size() == 0)
			return null;
		StringBuffer sb = new StringBuffer();
		int len = strings.size(), i = 0;
		for (String string : strings) {
			sb.append(string);
			i++;
			if (i < len) {
				sb.append(Configurations.string_split);
			}
		}
		return sb.toString();
	}
	
	public static String getRandomString() {
		return getRandomString(default_length);
	}
	
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static String formatResultInfoMessage(String msgCode, String errorMsg) {
		StringBuffer sb = new StringBuffer();
		sb.append(ResultCodeStorage.getResultCodeDescription(msgCode));
		if (errorMsg == null || "".equals(errorMsg))
			return sb.toString();
		sb.append("\n错误消息: ");
		sb.append(errorMsg);
		return sb.toString();
	}
	
	/**
	 * 格式化 json
	 * @param jsonStr
	 * @return
	 */
	public static String formatJSON(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr))
			return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);
			switch (current) {
			case '{':
			case '[':
				sb.append(current);
				sb.append('\n');
				indent++;
				addIndentBlank(sb, indent);
				break;
			case '}':
			case ']':
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
				break;
			case ',':
				sb.append(current);
				if (last != '\\') {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}
		return sb.toString();
	}

	/**
	 * 添加space
	 * @param sb
	 * @param indent
	 */
	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append('\t');
		}
	}

}
