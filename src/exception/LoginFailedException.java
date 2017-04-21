package exception;

public class LoginFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5665319979673453809L;

	public LoginFailedException(String msg) {
		super("登录失败 " + msg);
	}
}
