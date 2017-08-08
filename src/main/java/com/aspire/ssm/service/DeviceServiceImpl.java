package com.aspire.ssm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspire.ssm.bean.Device;
import com.aspire.ssm.bean.DeviceFormal;
import com.aspire.ssm.dao.DeviceDao;

@Service
public class DeviceServiceImpl implements DeviceServiceI {
	
	@Autowired
	private DeviceDao deviceDao;
	
	public Device getDeviceById(String deviceId){
		return deviceDao.selectById(deviceId);
	}

	public void insertDevice(Device device) {
		// TODO Auto-generated method stub
		deviceDao.insertDevice(device);
	}

	public List<Device> getDeviceAll() {
		// TODO Auto-generated method stub
		return deviceDao.selectAll();
	}

	public void deleteDevice(String deviceId) {
		// TODO Auto-generated method stub
		deviceDao.deleteDevice(deviceId);
	}

	public List<DeviceFormal> getDeviceAllFormal() {
		// TODO Auto-generated method stub
		return deviceDao.selectAllFormal();
	}

	public void insertDeviceFormal(DeviceFormal deviceFormal) {
		// TODO Auto-generated method stub
		deviceDao.insertDeviceFormal(deviceFormal);
	}

	public DeviceFormal getDeviceFormalById(String deviceId) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceFormalById(deviceId);
	}

	public List<DeviceFormal> getDeviceFormalByVersion(String firmVersion) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceFormalByVersion(firmVersion);
	}

	public List<DeviceFormal> getDeviceFormalByName(String deviceName) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceFormalByName(deviceName);
	}

	public int getDeviceCount() {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceCount();
	}

	public List<DeviceFormal> getDeviceFormal(int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceFormal(startPos, pageSize);
	}

	public int getDeviceCountByName(String deviceName) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceCountByName(deviceName);
	}

	public List<DeviceFormal> getDeviceFormalByName(String deviceName, int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceFormalByName(deviceName, startPos, pageSize);
	}

	public int getDeviceCountByVersion(String firmVersion) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceCountByVersion(firmVersion);
	}

	public List<DeviceFormal> getDeviceFormalByVersion(String firmVersion, int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceFormalByVersion(firmVersion, startPos, pageSize);
	}

	public int getDeviceCountByStatus(String deviceStatus) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceCountByStatus(deviceStatus);
	}

	public List<DeviceFormal> getDeviceFormalByStatus(String deviceStatus, int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceFormalByStatus(deviceStatus, startPos, pageSize);
	}

	public int getDeviceCountByTime(Date timeBegin, Date timeEnd) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceCountByTime(timeBegin, timeEnd);
	}

	public List<DeviceFormal> getDeviceFormalByTime(Date timeBegin, Date timeEnd, int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceFormalByTime(timeBegin, timeEnd, startPos, pageSize);
	}

} 
