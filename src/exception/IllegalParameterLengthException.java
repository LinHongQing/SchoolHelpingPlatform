package exception;

public class IllegalParameterLengthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4703442553584476879L;

	public IllegalParameterLengthException(String msg) {
		super("参数的长度非法 " + msg);
	}
}
