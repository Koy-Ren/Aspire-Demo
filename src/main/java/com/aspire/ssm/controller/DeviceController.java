package com.aspire.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspire.ssm.bean.Device;
import com.aspire.ssm.bean.DeviceFormal;
import com.aspire.ssm.service.DeviceServiceI;

@Controller
public class DeviceController {
	
	private Device device = new Device();
	private DeviceFormal deviceFormal = new DeviceFormal();
	@Autowired
	private DeviceServiceI deviceServiceImpl;
	
	@RequestMapping(value="/addDevice.do", method=RequestMethod.POST)
	public String addDevice(@RequestParam(value="deviceId") String deviceId, 
			@RequestParam(value="deviceName") String deviceName,
			@RequestParam(value="firmVersion") String firmVersion) {
//	public String addDevice(HttpServletRequest req){
		
//		String reqs = req.getParameter("deviceId");
		
//		System.out.println(deviceId);

		device.setDeviceId(deviceId);
		device.setDeviceName(deviceName);
		device.setFirmVersion(firmVersion);
		
		deviceServiceImpl.insertDevice(device);
		
		return "addDone";
	}
	
	@RequestMapping(value="/selectAllDevice.do", method=RequestMethod.POST)
	public String selectAllDevice(Model model){
		List<Device> deviceAll = deviceServiceImpl.getDeviceAll();
		model.addAttribute("deviceAll", deviceAll);
		return "allDevice";
	}
	
	@RequestMapping(value="/active.do", method=RequestMethod.GET)
	public String active(
			@RequestParam(value="deviceId") String deviceId, 
			Model model){
		deviceFormal.setDeviceId(deviceId);
		deviceFormal.setDeviceName(deviceServiceImpl.getDeviceById(deviceId).getDeviceName());
		deviceFormal.setFirmVersion(deviceServiceImpl.getDeviceById(deviceId).getFirmVersion());
		deviceFormal.setDeviceStatus(1);
		deviceServiceImpl.deleteDevice(deviceId);
		deviceServiceImpl.insertDeviceFormal(deviceFormal);
		List<DeviceFormal> deviceAllFormal = deviceServiceImpl.getDeviceAllFormal();
		model.addAttribute("deviceAllFormal", deviceAllFormal);
		return "deviceFormal";
	}
}
