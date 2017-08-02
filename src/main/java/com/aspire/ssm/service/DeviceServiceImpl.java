package com.aspire.ssm.service;

import java.util.List;

import javax.annotation.Resource;

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

} 
