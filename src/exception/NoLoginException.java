package exception;

public class NoLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3972189284312383834L;
	public NoLoginException(String msg) {
		super("没有登录 " + msg);
	}
}
