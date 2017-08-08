package com.aspire.ssm.dao;

import java.util.Date;
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

	int getDeviceCount();

	List<DeviceFormal> getDeviceFormal(int startPos, int pageSize);

	int getDeviceCountByName(String deviceName);

	List<DeviceFormal> getDeviceFormalByName(String deviceName, int startPos, int pageSize);

	int getDeviceCountByVersion(String firmVersion);

	List<DeviceFormal> getDeviceFormalByVersion(String firmVersion, int startPos, int pageSize);

	int getDeviceCountByStatus(String deviceStatus);

	List<DeviceFormal> getDeviceFormalByStatus(String deviceStatus, int startPos, int pageSize);

	int getDeviceCountByTime(Date timeBegin, Date timeEnd);

	List<DeviceFormal> getDeviceFormalByTime(Date timeBegin, Date timeEnd, int startPos, int pageSize);

}