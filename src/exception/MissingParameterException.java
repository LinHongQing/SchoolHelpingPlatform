package exception;

public class MissingParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7976467526336479652L;

	public MissingParameterException(String msg) {
		super("参数缺失 " + msg);
	}
}
