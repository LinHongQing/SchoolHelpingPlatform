package exception;

public final class IllegalEmptyStringException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2006311967967986291L;

	public IllegalEmptyStringException(String msg) {
		super("String 的内容不能为空 " + msg);
	}
}
