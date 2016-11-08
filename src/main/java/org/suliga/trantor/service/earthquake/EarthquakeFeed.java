package org.suliga.trantor.service.earthquake;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.suliga.trantor.service.earthquake.EarthquakeEvent.Features;

@Service
public class EarthquakeFeed implements EarthquakeService {
	public String getDailyFeed() {
		return "getDailyFeed";
	}
	
	public String getWeeklyFeed() {
		return "getWeeklyFeed";
	}
	
	public String getMonthlyFeed() {
		return "getMonthlyFeed";
	}
	
	public List<String> getTopSixEvents(EarthquakePeriod eqPeriod) {
		List<String> list = new ArrayList<>();
	   	RestTemplate restTemplate = new RestTemplate();
    	String result = restTemplate.getForObject(getUrl(eqPeriod), String.class);
    	try {
    		//System.out.println("<> result=" + result);
    		ObjectMapper om = new ObjectMapper();
    		EarthquakeEvent event = om.readValue(result, EarthquakeEvent.class);
    		//System.out.println("event=" + event);
    		Features[] features = event.getFeatures();
    		
    		if (eqPeriod == EarthquakePeriod.MONTH_US)
    			features = Arrays.asList(features)
    				.stream()
    				.filter(f -> isState(f.getProperties().get("title")))
    				.toArray(Features[]::new);
    		
    		Arrays.sort(features);
    	  	for (int i=0;i<6;i++) {
        		if (features.length > i) {
        			StringBuilder sb = new StringBuilder();
        			sb.append("Magnitude: " + oneDecimalPlace(features[i].getProperties().get("mag")));
        			sb.append("<br />");
         			sb.append("Location: " + features[i].getProperties().get("place"));
        			sb.append("<br />");
         			sb.append("Date: " + eqDate(features[i].getProperties().get("time")));
        			sb.append("<br />");
        			list.add(sb.toString());
        		}
        	}
		} catch (IOException e) {
			System.out.println("e=" + e.getMessage());
			e.printStackTrace();
		}
    	System.out.println("result.length=" + result.length());
    	return list;
	}
	
	private String oneDecimalPlace(String string) {
		if (string.contains("."))
			return string;
		return string + ".0";
	}

	private boolean isState(String title) {
		String s =  title.substring(title.lastIndexOf(" ")+1, title.length());
		switch (s) {
		case "Alabama":
		case "Alaska":
		case "Arizona":
		case "Arkansas":
		case "California":
		case "Colorado":
		case "Connecticut":
		case "Delaware":
		case "Florida":
		case "Georgia":
		case "Hawaii":
		case "Idaho":
		case "Illinoi":
		case "Indiana":
		case "Iowa":
		case "Kansas":
		case "Kentucky":
		case "Louisiana":
		case "Maine":
		case "Maryland":
		case "Massachusetts":
		case "Michigan":
		case "Minnesota":
		case "Mississippi":
		case "Missouri":
		case "Montana":
		case "Nebraska":
		case "Nevada":
		case "Ohio":
		case "Oklahoma":
		case "Oregon":
		case "Pennsylvania":
		case "Tennessee":
		case "Texas":
		case "Utah":
		case "Vermont":
		case "Virginia":
		case "Washington":
		case "Wisconsin":
		case "Wyoming":
			return true;
		default:
			return title.endsWith("New Hampshire")
				|| title.endsWith("New Jersey")
				|| title.endsWith("New Mexico")
				|| title.endsWith("New York")
				|| title.endsWith("Rhode Island")
				|| title.endsWith("North Dakota")
				|| title.endsWith("North Carolina")
				|| title.endsWith("South Carolina")
				|| title.endsWith("South Dakota")
				|| title.endsWith("West Virginia");
		}
	}
	
	private String eqDate(String longDate) {
		Instant date = Instant.ofEpochMilli(Long.parseLong(longDate));
		ZonedDateTime z = ZonedDateTime.ofInstant(date, ZoneId.of("UTC+0"));
		return DateTimeFormatter.ofPattern("MMM dd hh:mm a").format(z);
	}

	@Override
	public List<String> getTopSixEvents(String period) {
		if (StringUtils.isEmpty(period))
			return getTopSixEvents(EarthquakePeriod.MONTH);
		
		switch (period) {
			case "1": return getTopSixEvents(EarthquakePeriod.DAY);
			case "2": return getTopSixEvents(EarthquakePeriod.WEEK);
			case "3": return getTopSixEvents(EarthquakePeriod.MONTH);
			case "4": return getTopSixEvents(EarthquakePeriod.MONTH_US);
		}
		return null;
	}
	
	private String getUrl(EarthquakePeriod eqPeriod) {
		switch (eqPeriod) {
		case DAY:	return "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";
		case WEEK:  return "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.geojson";
		case MONTH: return "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson";
		case MONTH_US: return "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_month.geojson";
		default:    return "ERROR";
		}
	}
}
