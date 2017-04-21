package exception;

public class IllegalRequestPathException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719061700161563083L;
	public IllegalRequestPathException(String msg) {
		super("请求的路径非法 " + msg);
	}
}
