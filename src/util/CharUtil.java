package util;

public final class CharUtil {
	public static final int getCharCountValue(String source, char c) {
		int count = 0;
		for (int i = 0; i < source.length(); i++) {
			if (source.charAt(i) == c)
				count++;
		}
		return count;
	}
}
