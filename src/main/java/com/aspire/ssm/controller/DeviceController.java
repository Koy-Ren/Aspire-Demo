package com.aspire.ssm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.aspire.ssm.util.Page;

/**
 * save audio file.
 * @author Koy Ren
 */
@Controller
public class DeviceController {

    private Device device = new Device();
    private DeviceFormal deviceFormal = new DeviceFormal();
    @Autowired
    private DeviceServiceI deviceServiceImpl;

    /**
     * 添加临时设备.
     * @param deviceId
     * @param deviceName
     * @param firmVersion
     * @param recordDateTime
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/addDevice.do", method = RequestMethod.POST)
    public String addDevice(@RequestParam(value = "deviceId") String deviceId,
                            @RequestParam(value = "deviceName") String deviceName,
                            @RequestParam(value = "firmVersion") String firmVersion,
                            @RequestParam(value = "recordDateTime") String recordDateTime) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(recordDateTime);
        device.setDeviceId(deviceId);
        device.setDeviceName(deviceName);
        device.setFirmVersion(firmVersion);
        device.setRecordDateTime(d);

        deviceServiceImpl.insertDevice(device);

        return "welcome";
    }

    /**
     * 选择所有临时设备.
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectAllDevice.do", method = RequestMethod.GET)
    public String selectAllDevice(Model model) {
        List<Device> deviceAll = deviceServiceImpl.getDeviceAll();
        model.addAttribute("deviceAll", deviceAll);
        return "WEB-INF/allDevice";
    }

    // 激活临时设备
    @SuppressWarnings("unused")
    @RequestMapping(value = "/active.do", method = RequestMethod.GET)
    public String active(@RequestParam(value = "deviceId") String deviceId, Model model) {
        deviceFormal.setDeviceId(deviceId);
        deviceFormal.setDeviceName(deviceServiceImpl.getDeviceById(deviceId).getDeviceName());
        deviceFormal.setFirmVersion(deviceServiceImpl.getDeviceById(deviceId).getFirmVersion());
        deviceFormal.setRecordDateTime(deviceServiceImpl.getDeviceById(deviceId).getRecordDateTime());
        deviceFormal.setDeviceStatus(1);
        deviceServiceImpl.deleteDevice(deviceId);
        deviceServiceImpl.insertDeviceFormal(deviceFormal);
        // List<DeviceFormal> deviceAllFormal =
        // deviceServiceImpl.getDeviceAllFormal();
        // model.addAttribute("deviceAllFormal", deviceAllFormal);
        // return "WEB-INF/deviceFormal";
        String pageNow = "1";

        Page page = null;

        List<DeviceFormal> deviceAllFormal = new ArrayList<DeviceFormal>();

        int totalCount = (int) deviceServiceImpl.getDeviceCount();

        if (pageNow != null) {
            page = new Page(totalCount, Integer.parseInt(pageNow));
            deviceAllFormal = deviceServiceImpl.getDeviceFormal(page.getStartPos(), page.getPageSize());
        } else {
            page = new Page(totalCount, 1);
            deviceAllFormal = deviceServiceImpl.getDeviceFormal(page.getStartPos(), page.getPageSize());
        }

        model.addAttribute("deviceAllFormal", deviceAllFormal);
        model.addAttribute("page", page);

        return "WEB-INF/deviceFormal";
    }

    // 查询所有正式设备
    @RequestMapping(value = "/selectAllDeviceFormal.do", method = RequestMethod.GET)
    public String selectAllFormalDevice(Model model) {
        List<DeviceFormal> deviceAllFormal = deviceServiceImpl.getDeviceAllFormal();
        model.addAttribute("deviceAllFormal", deviceAllFormal);
        // return "WEB-INF/deviceFormal";
        return "WEB-INF/deviceFormal";
    }

    // 查询正式设备
    @RequestMapping(value = "/searchDevice.do", method = RequestMethod.GET)
    public String searchDevice(@RequestParam(value = "deviceId") String deviceId,
                               @RequestParam(value = "deviceName") String deviceName,
                               @RequestParam(value = "firmVersion") String firmVersion,
                               @RequestParam(value = "deviceStatus") String deviceStatus, @RequestParam(value = "timeBegin") String timeB,
                               @RequestParam(value = "timeEnd") String timeE, @RequestParam(value = "pageNow") String pageNow, Model model)
            throws ParseException {
        if (!deviceId.equals("")) {
            deviceFormal = deviceServiceImpl.getDeviceFormalById(deviceId);
            List<DeviceFormal> deviceAllFormal = new LinkedList<DeviceFormal>();
            deviceAllFormal.add(deviceFormal);
            model.addAttribute("deviceAllFormal", deviceAllFormal);
            return "WEB-INF/deviceFormal";
        } else if (!deviceName.equals("")) {
            Page page = null;

            List<DeviceFormal> deviceAllFormal = new ArrayList<DeviceFormal>();

            int totalCount = (int) deviceServiceImpl.getDeviceCountByName(deviceName);

            if (pageNow != null) {
                page = new Page(totalCount, Integer.parseInt(pageNow));
                deviceAllFormal = deviceServiceImpl.getDeviceFormalByName(deviceName, page.getStartPos(),
                        page.getPageSize());
            } else {
                page = new Page(totalCount, 1);
                deviceAllFormal = deviceServiceImpl.getDeviceFormalByName(deviceName, page.getStartPos(),
                        page.getPageSize());
            }

            model.addAttribute("deviceAllFormal", deviceAllFormal);
            model.addAttribute("page", page);
            model.addAttribute("deviceId", deviceId);
            model.addAttribute("deviceName", deviceName);
            model.addAttribute("firmVersion", firmVersion);
            model.addAttribute("deviceStatus", deviceStatus);
            model.addAttribute("timeBegin", timeB);
            model.addAttribute("timeEnd", timeE);

            return "WEB-INF/deviceFormal";
        } else if (!firmVersion.equals("")) {
            Page page = null;

            List<DeviceFormal> deviceAllFormal = new ArrayList<DeviceFormal>();

            int totalCount = (int) deviceServiceImpl.getDeviceCountByVersion(firmVersion);

            if (pageNow != null) {
                page = new Page(totalCount, Integer.parseInt(pageNow));
                deviceAllFormal = deviceServiceImpl.getDeviceFormalByVersion(firmVersion, page.getStartPos(),
                        page.getPageSize());
            } else {
                page = new Page(totalCount, 1);
                deviceAllFormal = deviceServiceImpl.getDeviceFormalByVersion(firmVersion, page.getStartPos(),
                        page.getPageSize());
            }

            model.addAttribute("deviceAllFormal", deviceAllFormal);
            model.addAttribute("page", page);
            model.addAttribute("deviceId", deviceId);
            model.addAttribute("deviceName", deviceName);
            model.addAttribute("firmVersion", firmVersion);
            model.addAttribute("deviceStatus", deviceStatus);
            model.addAttribute("timeBegin", timeB);
            model.addAttribute("timeEnd", timeE);

            return "WEB-INF/deviceFormal";
        } else if (!deviceStatus.equals("")) {
            Page page = null;

            List<DeviceFormal> deviceAllFormal = new ArrayList<DeviceFormal>();

            int totalCount = (int) deviceServiceImpl.getDeviceCountByStatus(deviceStatus);

            if (pageNow != null) {
                page = new Page(totalCount, Integer.parseInt(pageNow));
                deviceAllFormal = deviceServiceImpl.getDeviceFormalByStatus(deviceStatus, page.getStartPos(),
                        page.getPageSize());
            } else {
                page = new Page(totalCount, 1);
                deviceAllFormal = deviceServiceImpl.getDeviceFormalByStatus(deviceStatus, page.getStartPos(),
                        page.getPageSize());
            }

            model.addAttribute("deviceAllFormal", deviceAllFormal);
            model.addAttribute("page", page);
            model.addAttribute("deviceId", deviceId);
            model.addAttribute("deviceName", deviceName);
            model.addAttribute("firmVersion", firmVersion);
            model.addAttribute("deviceStatus", deviceStatus);
            model.addAttribute("timeBegin", timeB);
            model.addAttribute("timeEnd", timeE);

            return "WEB-INF/deviceFormal";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeBegin = sdf.parse(timeB);
            Date timeEnd = sdf.parse(timeE);

            Page page = null;

            List<DeviceFormal> deviceAllFormal = new ArrayList<DeviceFormal>();

            int totalCount = (int) deviceServiceImpl.getDeviceCountByTime(timeBegin, timeEnd);

            if (pageNow != null) {
                page = new Page(totalCount, Integer.parseInt(pageNow));
                deviceAllFormal = deviceServiceImpl.getDeviceFormalByTime(timeBegin, timeEnd, page.getStartPos(),
                        page.getPageSize());
            } else {
                page = new Page(totalCount, 1);
                deviceAllFormal = deviceServiceImpl.getDeviceFormalByTime(timeBegin, timeEnd, page.getStartPos(),
                        page.getPageSize());
            }

            model.addAttribute("deviceAllFormal", deviceAllFormal);
            model.addAttribute("page", page);
            model.addAttribute("deviceId", deviceId);
            model.addAttribute("deviceName", deviceName);
            model.addAttribute("firmVersion", firmVersion);
            model.addAttribute("deviceStatus", deviceStatus);
            model.addAttribute("timeBegin", timeB);
            model.addAttribute("timeEnd", timeE);

            return "WEB-INF/deviceFormal";
        }
    }

    // 分页查询显示
    @RequestMapping(value = "/devicePage.do", method = RequestMethod.GET)
    public String devicePage(@RequestParam(value = "pageNow") String pageNow, Model model) {

        Page page = null;

        List<DeviceFormal> deviceAllFormal = new ArrayList<DeviceFormal>();

        int totalCount = (int) deviceServiceImpl.getDeviceCount();

        if (pageNow != null) {
            page = new Page(totalCount, Integer.parseInt(pageNow));
            deviceAllFormal = deviceServiceImpl.getDeviceFormal(page.getStartPos(), page.getPageSize());
        } else {
            page = new Page(totalCount, 1);
            deviceAllFormal = deviceServiceImpl.getDeviceFormal(page.getStartPos(), page.getPageSize());
        }

        model.addAttribute("deviceAllFormal", deviceAllFormal);
        model.addAttribute("page", page);
        model.addAttribute("deviceId", "");
        model.addAttribute("deviceName", "");
        model.addAttribute("firmVersion", "");
        model.addAttribute("deviceStatus", "");
        model.addAttribute("timeBegin", "2000-01-01 00:00:00");
        model.addAttribute("timeEnd", "2020-01-01 00:00:00");

        return "WEB-INF/deviceFormal";
    }

}
