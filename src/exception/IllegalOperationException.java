package exception;

public class IllegalOperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6196267924449061730L;
	public IllegalOperationException(String msg) {
		super("非法操作 " + msg);
	}
}
