package com.aspire.ssm.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspire.ssm.bean.Device;
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

} 
