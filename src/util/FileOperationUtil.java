package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileOperationUtil {
	public static String readFile(String filePath, String fileName) {
		File file = new File(filePath + fileName);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] tempbyte = new byte[1];
			StringBuffer strBuf = new StringBuffer();
			while ((in.read(tempbyte)) != -1) {
				strBuf.append(new String(tempbyte));
			}
			System.out.println(strBuf.toString());
			in.close();
			return strBuf.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean writeStringToFile(String content, String filePath, String fileName) {
		File file = new File(filePath + fileName);

		try (FileOutputStream fop = new FileOutputStream(file)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean writeToFile(File sourceFile, String filePath, String fileName) {
		File file = new File(filePath + fileName);
		
		try (FileOutputStream fop = new FileOutputStream(file)) {
			InputStream is = new FileInputStream(sourceFile);
			byte[] buf = new byte[1024];
			int length = 0;

			while (-1 != (length = is.read(buf))) {
				fop.write(buf, 0, length);
			}
			is.close();
			fop.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
