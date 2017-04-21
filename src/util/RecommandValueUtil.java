package util;

public final class RecommandValueUtil {
	
	private static final float distanceWeights = 0.4f;
	private static final float timeWeights = 0.6f;
	
	public static final int getValue(double lat1, double lng1, double lat2, double lng2, int preferStart, int preferEnd) {
		double distance = DistanceUtil.getDistance(lat1, lng1, lat2, lng2);
		int nowTime = TimeUtil.getNowTimeValue(TimeUtil.get_hour) * 60 + TimeUtil.getNowTimeValue(TimeUtil.get_minute);
		long timeDifference = Math.abs((preferEnd + preferStart) / 2 - nowTime);
		System.out.println("distance: " + distance);
		System.out.println("nowTime: " + nowTime);
		System.out.println("timeDiff: " + timeDifference);
		System.out.println("recommand: " + (int) (distance * distanceWeights + timeDifference * timeWeights));
		return (int) (distance * distanceWeights + timeDifference * timeWeights);
	}
}
