package bean;

public class TransferPlatformStatisticsInfo {
	private int online;
	private int login;
	private int todaylogin;
	private int todayproblem;
	private int todaysolve;
	public int getOnline() {
		return online;
	}
	public void setOnline(int todayonline) {
		this.online = todayonline;
	}
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public int getTodaylogin() {
		return todaylogin;
	}
	public void setTodaylogin(int todaylogin) {
		this.todaylogin = todaylogin;
	}
	public int getTodayproblem() {
		return todayproblem;
	}
	public void setTodayproblem(int todayproblem) {
		this.todayproblem = todayproblem;
	}
	public int getTodaysolve() {
		return todaysolve;
	}
	public void setTodaysolve(int todaysolve) {
		this.todaysolve = todaysolve;
	}
	@Override
	public String toString() {
		return "TransferPlatformStatisticsInfo [online=" + online + ", login="
				+ login + ", todayLogin=" + todaylogin + ", todayproblem="
				+ todayproblem + ", todaysolve=" + todaysolve + "]";
	}
}
