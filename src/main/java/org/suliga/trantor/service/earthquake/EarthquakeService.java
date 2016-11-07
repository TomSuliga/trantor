package org.suliga.trantor.service.earthquake;

import java.util.List;

public abstract interface EarthquakeService {
	public abstract String getDailyFeed();
	public abstract String getWeeklyFeed();
	public abstract String getMonthlyFeed();
	public abstract List<String> getTopSixEvents(EarthquakePeriod eqPeriod);
	public abstract List<String> getTopSixEvents(String period);
}


