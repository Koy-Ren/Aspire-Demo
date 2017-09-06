package com.aspire.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping(value = "/index.action")
    public void addDevice(HttpServletRequest req, HttpServletResponse rep) {

        //test

        String deviceId = req.getParameter("deviceId");
        String deviceName = req.getParameter("deviceName");
        String firmVersion = req.getParameter("firmVersion");
        System.out.println(deviceId + " " + deviceName + " " + firmVersion);
    }
}
