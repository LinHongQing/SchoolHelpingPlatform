package exception;

public class NoQualificationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1857295535557383670L;
	
	public NoQualificationException(String msg) {
		super("没有认证 " + msg);
	}

}
