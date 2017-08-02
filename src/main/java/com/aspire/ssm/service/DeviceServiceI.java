package com.aspire.ssm.service;

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

}
