package exception;

public final class IllegalEmptyListException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6356307925852822826L;

	public IllegalEmptyListException(String msg) {
		super("List 的内容不能为空 " + msg);
	}
}
