package com.aspire.ssm.service;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface IMusicFileService {

	Model addMusicFile(MultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response, Model model)
			throws ParseException, FileUploadException, IOException;

	Model searchMusicFile(HttpServletRequest request, Model model) throws ParseException;

	Model searchAllMusicFile(HttpServletRequest request, Model model);

	Model deleteFile(HttpServletRequest request, Model model) throws ParseException;

}
