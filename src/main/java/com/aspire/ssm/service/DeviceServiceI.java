package com.aspire.ssm.service;

import java.util.Date;
import java.util.List;

import com.aspire.ssm.bean.Device;
import com.aspire.ssm.bean.DeviceFormal;

public interface DeviceServiceI {
	
	public Device getDeviceById(String deviceId);
	
	public void insertDevice(Device device);
	
	public List<Device> getDeviceAll();
	
	public void deleteDevice(String deviceId);
	
	public List<DeviceFormal> getDeviceAllFormal();
	
	public void insertDeviceFormal(DeviceFormal deviceFormal);
	
	public DeviceFormal getDeviceFormalById(String deviceId);

	public List<DeviceFormal> getDeviceFormalByVersion(String firmVersion);

	public List<DeviceFormal> getDeviceFormalByName(String deviceName);

	public int getDeviceCount();

	public List<DeviceFormal> getDeviceFormal(int startPos, int pageSize);

	public int getDeviceCountByName(String deviceName);

	public List<DeviceFormal> getDeviceFormalByName(String deviceName, int startPos, int pageSize);

	public int getDeviceCountByVersion(String firmVersion);

	public List<DeviceFormal> getDeviceFormalByVersion(String firmVersion, int startPos, int pageSize);

	public int getDeviceCountByStatus(String deviceStatus);

	public List<DeviceFormal> getDeviceFormalByStatus(String deviceStatus, int startPos, int pageSize);

	public int getDeviceCountByTime(Date timeBegin, Date timeEnd);

	public List<DeviceFormal> getDeviceFormalByTime(Date timeBegin, Date timeEnd, int startPos, int pageSize);

}
