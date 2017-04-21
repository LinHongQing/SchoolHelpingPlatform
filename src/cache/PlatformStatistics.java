package cache;

public class PlatformStatistics {
	
	public static final int online = 0;
	public static final int todayProblem = 1;
	public static final int todaySolved = 2;
	public static final int todayCreditValueChange = 3;
	public static final int todayLogin = 4;
	public static final int todayComplaintRequest = 5;
	public static final int todayComplaintReply = 6;
	public static final int todayQualificationRequest = 7;

	private static int onlineCount = 0;
	private static int todayProblemCount = 0;
	private static int todaySolvedCount = 0;
	private static int todayCreditValueChangeCount = 0;
	private static int todayLoginCount = 0;
	private static int todayComplaintRequestCount = 0;
	private static int todayComplaintReplyCount = 0;
	private static int todayQualificationRequestCount = 0;
	
	public synchronized static int getOnlineCount() {
		return onlineCount;
	}

	public synchronized static int getTodayProblemCount() {
		return todayProblemCount;
	}

	public synchronized static int getTodaySolvedCount() {
		return todaySolvedCount;
	}

	public synchronized static int getTodayCreditValueChangeCount() {
		return todayCreditValueChangeCount;
	}

	public synchronized static int getTodayLoginCount() {
		return todayLoginCount;
	}

	public synchronized static int getTodayComplaintRequestCount() {
		return todayComplaintRequestCount;
	}

	public synchronized static int getTodayComplaintReplyCount() {
		return todayComplaintReplyCount;
	}

	public synchronized static int getTodayQualificationRequestCount() {
		return todayQualificationRequestCount;
	}
	
	public synchronized static void resetTodayStatisticsValue() {
		todayProblemCount = 0;
		todaySolvedCount = 0;
		todayCreditValueChangeCount = 0;
		todayLoginCount = 0;
		todayComplaintRequestCount = 0;
		todayComplaintReplyCount = 0;
		todayQualificationRequestCount = 0;
	}
	
	public synchronized static void addOneValuetoStatistic(int statistic) {
		switch(statistic) {
		case online:
			onlineCount++;
			break;
		case todayProblem:
			todayProblemCount++;
			break;
		case todaySolved:
			todaySolvedCount++;
			break;
		case todayCreditValueChange:
			todayCreditValueChangeCount++;
			break;
		case todayLogin:
			todayLoginCount++;
			break;
		case todayComplaintRequest:
			todayComplaintRequestCount++;
			break;
		case todayComplaintReply:
			todayComplaintReplyCount++;
		}
	}
	
	public synchronized static void subOneValuetoStatistic(int statistic) {
		switch(statistic) {
		case online:
			onlineCount--;
			break;
		}
	}
	
}
