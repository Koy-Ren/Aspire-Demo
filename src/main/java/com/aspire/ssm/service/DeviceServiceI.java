package com.aspire.ssm.service;

import com.aspire.ssm.bean.Device;

public interface DeviceServiceI {
	
	public Device getDeviceById(String deviceId);
	
	public void insertDevice(Device device);

}
