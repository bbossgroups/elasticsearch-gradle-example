package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.entity.ESBaseData;

public class PboosMap extends ESBaseData{
	private String cityName;
	private String standardAddrId;
	private String detailName;
	private String location;
	private String countyName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStandardAddrId() {
		return standardAddrId;
	}

	public void setStandardAddrId(String standardAddrId) {
		this.standardAddrId = standardAddrId;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
}
