package com.aspire.ssm.dao;

import java.util.List;

import com.aspire.ssm.bean.Device;
import com.aspire.ssm.bean.DeviceFormal;

public interface DeviceDao {
	
	Device selectById(String deviceId);
	
	void insertDevice(Device device);
	
	List<Device> selectAll();
	
	void deleteDevice(String deviceId);
	
	List<DeviceFormal> selectAllFormal();
	
	void insertDeviceFormal(DeviceFormal deviceFormal);
	
	DeviceFormal getDeviceFormalById(String deviceId);

	List<DeviceFormal> getDeviceFormalByVersion(String firmVersion);

	List<DeviceFormal> getDeviceFormalByName(String deviceName);

}