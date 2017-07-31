package com.aspire.ssm.dao;

import com.aspire.ssm.bean.Device;

public interface DeviceDao {
	
	Device selectById(String deviceId);
	
	void insertDevice(Device device);

}