package exception;

public class PermissionDeniedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 251372844134051760L;
	public PermissionDeniedException(String msg) {
		super("没有授权 " + msg);
	}
}
