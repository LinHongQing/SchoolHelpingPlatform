package exception;

public class FileOperateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5203724422723105860L;
	public FileOperateException(String msg) {
		super("文件操作失败 " + msg);
	}
}
