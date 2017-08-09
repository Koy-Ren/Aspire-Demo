package com.aspire.ssm.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aspire.ssm.service.IMusicFileService;

@Controller
public class MusicFileController {

	/**
	 * 实现文件上传
	 * 
	 * @param fileUpload
	 * @param request
	 * @return null
	 */

	@Autowired
	IMusicFileService musicFileService;

	@RequestMapping("/fileUpload.do")
	public void fileUpload(@RequestParam("file") MultipartFile fileUpload, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {

		musicFileService.addMusicFile(fileUpload, request, response);

	}

}
