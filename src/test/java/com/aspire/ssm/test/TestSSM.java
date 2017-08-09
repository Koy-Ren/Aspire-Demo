package com.aspire.ssm.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aspire.ssm.bean.Device;
import com.aspire.ssm.service.DeviceServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-mybatis.xml")
public class TestSSM {

	@Autowired
	private DeviceServiceI deviceServiceImpl;
	
	@Test
	public void testAdd() throws Exception{
		
		Device device = new Device();
		device.setDeviceId(String.valueOf((int)((Math.random()*9+1)*100000)));
		device.setDeviceName("test1");
		device.setFirmVersion("version 1.2.3");
		deviceServiceImpl.insertDevice(device);
	}
}
