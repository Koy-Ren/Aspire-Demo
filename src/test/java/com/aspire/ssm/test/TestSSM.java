package com.aspire.ssm.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aspire.ssm.bean.Device;
import com.aspire.ssm.service.DeviceServiceI;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-mybatis.xml")
public class TestSSM {

	@Autowired
	private DeviceServiceI deviceServiceImpl;
	
	@Test
	public void testAdd() throws Exception{
		
		Device device = new Device();
		device.setDeviceId("YYYYYSUDISIDUSY");
		device.setDeviceName("test1");
		device.setFirmVersion("version 1.2.3");
		deviceServiceImpl.insertDevice(device);
	}
}
