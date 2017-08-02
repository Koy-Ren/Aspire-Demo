package com.aspire.ssm.bean;

public class DeviceFormal {
	
	private String deviceId;
	private String deviceName;
	private String firmVersion;
	private String recordDateTime;
	private int deviceStatus;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getFirmVersion() {
		return firmVersion;
	}
	public void setFirmVersion(String firmVersion) {
		this.firmVersion = firmVersion;
	}
	public String getRecordDateTime() {
		return recordDateTime;
	}
	public void setRecordDateTime(String recordDateTime) {
		this.recordDateTime = recordDateTime;
	}
	public int getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

}
