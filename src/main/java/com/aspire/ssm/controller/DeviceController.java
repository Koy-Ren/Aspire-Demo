package com.aspire.ssm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
	
	//添加临时设备
	@RequestMapping(value="/addDevice.do", method=RequestMethod.POST)
	public String addDevice(
			@RequestParam(value="deviceId") String deviceId, 
			@RequestParam(value="deviceName") String deviceName,
			@RequestParam(value="firmVersion") String firmVersion,
			@RequestParam(value="recordDateTime") String recordDateTime
			) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse(recordDateTime);
		device.setDeviceId(deviceId);
		device.setDeviceName(deviceName);
		device.setFirmVersion(firmVersion);
		device.setRecordDateTime(d);
		
		deviceServiceImpl.insertDevice(device);

		return "welcome";
	}
	
	//选择所有临时设备
	@RequestMapping(value="/selectAllDevice.do", method=RequestMethod.GET)
	public String selectAllDevice(Model model){
		List<Device> deviceAll = deviceServiceImpl.getDeviceAll();
		model.addAttribute("deviceAll", deviceAll);
		return "WEB-INF/allDevice";
	}
	
	//激活临时设备
	@RequestMapping(value="/active.do", method=RequestMethod.GET)
	public String active(
			@RequestParam(value="deviceId") String deviceId, 
			Model model){
		deviceFormal.setDeviceId(deviceId);
		deviceFormal.setDeviceName(deviceServiceImpl.getDeviceById(deviceId).getDeviceName());
		deviceFormal.setFirmVersion(deviceServiceImpl.getDeviceById(deviceId).getFirmVersion());
		deviceFormal.setRecordDateTime(deviceServiceImpl.getDeviceById(deviceId).getRecordDateTime());
		deviceFormal.setDeviceStatus(1);
		deviceServiceImpl.deleteDevice(deviceId);
		deviceServiceImpl.insertDeviceFormal(deviceFormal);
		List<DeviceFormal> deviceAllFormal = deviceServiceImpl.getDeviceAllFormal();
		model.addAttribute("deviceAllFormal", deviceAllFormal);
		return "WEB-INF/deviceFormal";
	}
	
	//查询所有正式设备
	@RequestMapping(value="/selectAllDeviceFormal.do", method=RequestMethod.GET)
	public String selectAllFormalDevice(Model model){
		List<DeviceFormal> deviceAllFormal = deviceServiceImpl.getDeviceAllFormal();
		model.addAttribute("deviceAllFormal", deviceAllFormal);
		return "WEB-INF/deviceFormal";
	}
	
	//查询正式设备
	@RequestMapping(value="/searchDevice.do", method=RequestMethod.POST)
	public String searchDevice(
			@RequestParam(value="deviceId") String deviceId,
			@RequestParam(value="deviceName") String deviceName,
			@RequestParam(value="firmVersion") String firmVersion,
			@RequestParam(value="deviceStatus") String deviceStatus,
			Model model){
		if(!deviceId.equals("")){
			deviceFormal = deviceServiceImpl.getDeviceFormalById(deviceId);
			List<DeviceFormal> deviceAllFormal = new LinkedList<DeviceFormal>();
			deviceAllFormal.add(deviceFormal);
			model.addAttribute("deviceAllFormal", deviceAllFormal);
			return "WEB-INF/deviceFormal";
		} else if(!deviceName.equals("")){
			List<DeviceFormal> deviceAllFormal = deviceServiceImpl.getDeviceFormalByName(deviceName);
			model.addAttribute("deviceAllFormal", deviceAllFormal);
			return "WEB-INF/deviceFormal";
		} else if(!firmVersion.equals("")) {
			List<DeviceFormal> deviceAllFormal = deviceServiceImpl.getDeviceFormalByVersion(firmVersion);
			model.addAttribute("deviceAllFormal", deviceAllFormal);
			return "WEB-INF/deviceFormal";
		} else if (!deviceStatus.equals("")){
			return "";
		} else {
			List<DeviceFormal> deviceAllFormal = deviceServiceImpl.getDeviceAllFormal();
			model.addAttribute("deviceAllFormal", deviceAllFormal);
			return "WEB-INF/deviceFormal";
		}
	}
	
}
