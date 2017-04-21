package exception;

public class IllegalParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1545195265423112159L;
	public IllegalParameterException(String msg) {
		super("参数的内容非法 " + msg);
	}
}
