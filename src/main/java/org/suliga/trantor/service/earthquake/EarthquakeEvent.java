package org.suliga.trantor.service.earthquake;

import java.util.Map;

public class EarthquakeEvent {
	private String type;
	private Map<String,String> metadata;
	private Features[] features;
	private Double[] bbox;

	public EarthquakeEvent() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Features[] getFeatures() {
		return features;
	}

	public void setFeatures(Features[] features) {
		this.features = features;
	}

	public Double[] getBbox() {
		return bbox;
	}

	public void setBbox(Double[] bbox) {
		this.bbox = bbox;
	}
	
	@Override
	public String toString() {
		return "type=" + type + ", metadata=" + metadata;
	}

	public static class Features implements Comparable<Features> {
		private String type;
		private Map<String,String> properties;
		private Map<String,Object> geometry;
		private String id;
		
		public Features() {}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Map<String, String> getProperties() {
			return properties;
		}

		public void setProperties(Map<String, String> properties) {
			this.properties = properties;
		}

		public Map<String, Object> getGeometry() {
			return geometry;
		}

		public void setGeometry(Map<String, Object> geometry) {
			this.geometry = geometry;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public int compareTo(Features other) {
			if (other == null)
				return 0;
			String s1 = properties.get("mag");
			String s2 = other.getProperties().get("mag");
			if (s1 == null || s2 == null)
				return 0;
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s2);
			int i1 = (int) (d1 * 100);
			int i2 = (int) (d2 * 100);
			return i2 - i1; // needed for descending order
		}
	}
}

	/*
	 {
    "type": "FeatureCollection",
    "metadata": {
        "generated": 1474739885000,
        "url": "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson",
        "title": "USGS All Earthquakes, Past Day",
        "status": 200,
        "api": "1.5.2",
        "count": 155
    },
    "features": [{
            "type": "Feature",
            "properties": {
                "mag": 1.58,
                "place": "9km WNW of Calipatria, CA",
                "time": 1474738046480,
                "updated": 1474738266877,
                "tz": -420,
                "url": "http://earthquake.usgs.gov/earthquakes/eventpage/ci37698952",
                "detail": "http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/ci37698952.geojson",
                "felt": null,
                "cdi": null,
                "mmi": null,
                "alert": null,
                "status": "automatic",
                "tsunami": 0,
                "sig": 38,
                "net": "ci",
                "code": "37698952",
                "ids": ",ci37698952,",
                "sources": ",ci,",
                "types": ",general-link,geoserve,nearby-cities,origin,phase-data,scitech-link,",
                "nst": 23,
                "dmin": 0.02933,
                "rms": 0.18,
                "gap": 89,
                "magType": "ml",
                "type": "earthquake",
                "title": "M 1.6 - 9km WNW of Calipatria, CA"
            },
            "geometry": {
                "type": "Point",
                "coordinates": [-115.6048333, 33.1495, 5.57]
            },
            "id": "ci37698952"
        }
        33.1495,-115.6048333,3Z
        @33.1,-112.1,3z
	 */

/*
 {
    "type": "Feature",
    "properties": {
        "mag": 5.2,
        "place": "67km NNW of Jayapura, Indonesia",
        "time": 1474710117950,
        "updated": 1474711259040,
        "tz": 540,
        "url": "http://earthquake.usgs.gov/earthquakes/eventpage/us10006sah",
        "detail": "http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us10006sah.geojson",
        "felt": null,
        "cdi": null,
        "mmi": null,
        "alert": null,
        "status": "reviewed",
        "tsunami": 1,
        "sig": 416,
        "net": "us",
        "code": "10006sah",
        "ids": ",us10006sah,",
        "sources": ",us,",
        "types": ",cap,geoserve,origin,phase-data,",
        "nst": null,
        "dmin": 6.817,
        "rms": 0.91,
        "gap": 65,
        "magType": "mb",
        "type": "earthquake",
        "title": "M 5.2 - 67km NNW of Jayapura, Indonesia"
    },
    "geometry": {
        "type": "Point",
        "coordinates": [140.5458, -1.9383, 10]
    },
    "id": "us10006sah"
},
 */
