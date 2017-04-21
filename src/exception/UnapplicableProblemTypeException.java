package exception;

public class UnapplicableProblemTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477939864824631830L;
	public UnapplicableProblemTypeException(String msg) {
		super("不适用的问题类型 " + msg);
	}
}
