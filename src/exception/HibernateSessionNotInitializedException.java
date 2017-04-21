package exception;

public class HibernateSessionNotInitializedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5064845316889062712L;

	public HibernateSessionNotInitializedException(String msg) {
		super("Hibernate Session 未初始化错误 " + msg);
	}
}
