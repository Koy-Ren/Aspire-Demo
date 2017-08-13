package com.aspire.ssm.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String fileUpload(@RequestParam("file") MultipartFile fileUpload, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException, FileUploadException, IOException {

		model = musicFileService.addMusicFile(fileUpload, request, response, model);
		
		System.out.println("test,jump");
		
		//return "redirect:/searchAllMusicFile.do";

		return "alert";
		
		

	}
	
	//搜索全部文件
	@RequestMapping(value = "/searchAllMusicFile.do", method = RequestMethod.GET)
	public String searchAllMusicFile(HttpServletRequest request, Model model){
		model = musicFileService.searchAllMusicFile(request, model);
		model.addAttribute("name", "");
		model.addAttribute("durationStatus", "");
		model.addAttribute("duration", "");
		model.addAttribute("fileStatus", "");
		model.addAttribute("fileSize", "");
		model.addAttribute("timeBegin", "2000-01-01 00:00:00");
		model.addAttribute("timeEnd", "2020-01-01 00:00:00");
		
		//return "alert";
		return "upload";
	}
	
	//条件搜索文件
	@RequestMapping(value = "/searchMusicFile.do", method = RequestMethod.GET)
	public String searchMusicFile(HttpServletRequest request, Model model) throws ParseException {

		model = musicFileService.searchMusicFile(request, model);
		model.addAttribute("name", request.getParameter("name"));
		model.addAttribute("durationStatus", request.getParameter("durationStatus"));
		model.addAttribute("duration", request.getParameter("duration"));
		model.addAttribute("fileStatus", request.getParameter("fileStatus"));
		model.addAttribute("fileSize", request.getParameter("fileSize"));
		model.addAttribute("timeBegin", request.getParameter("timeBegin"));
		model.addAttribute("timeEnd", request.getParameter("timeEnd"));

		return "upload";
	}
	
	//删除文件
	@RequestMapping(value = "/deleteFile.do", method = RequestMethod.GET)
	public String deleteFile(HttpServletRequest request, Model model) throws ParseException{
		model = musicFileService.deleteFile(request, model);
		model.addAttribute("name", "");
		model.addAttribute("durationStatus", "");
		model.addAttribute("duration", "");
		model.addAttribute("fileStatus", "");
		model.addAttribute("fileSize", "");
		model.addAttribute("timeBegin", "2000-01-01 00:00:00");
		model.addAttribute("timeEnd", "2020-01-01 00:00:00");
		
		return "upload";
	}

}
