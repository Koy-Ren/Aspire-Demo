package com.aspire.ssm.service;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface IMusicFileService {

	void addMusicFile(MultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response) throws ParseException;

}
