package com.aspire.ssm.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aspire.ssm.bean.MusicFile;
import com.aspire.ssm.dao.MusicFileDao;
import com.aspire.ssm.util.FileMD5;

@Service
public class MusicFileServiceImpl implements IMusicFileService {

	@Autowired
	MusicFileDao musicFileDao;

	public void addMusicFile(MultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat timeS = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		SimpleDateFormat timeMS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String originalFilename = fileUpload.getOriginalFilename();
		String systemFileName = timeMS.format(new Date())
				+ originalFilename.substring(originalFilename.lastIndexOf("."));
		Date recordDateTime = timeS.parse(timeS.format(new Date()));
		// String dirName =
		// request.getSession().getServletContext().getRealPath("/") +
		// "fileUpload";

		// double originalFilesize = request.getContentLength();// 获取源文件大小
		System.out.println(fileUpload.getName());
		System.out.println(fileUpload.getSize());
		System.out.println(originalFilename);
		// System.out.println(dirName);
		System.out.println(systemFileName);

		String fileDir = "E:\\musicfile";
		File file = new File(fileDir);
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		if (!file.exists()) {
			file.mkdir();

		}
		try {
			inputStream = fileUpload.getInputStream();

			outputStream = new FileOutputStream(fileDir + "/" + systemFileName);
			byte[] buffer = new byte[1024 * 1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
				outputStream.flush();
			}
			outputStream.close();
			inputStream.close();
			System.out.println(FileMD5.getFileMD5(new File(fileDir + "/" + systemFileName)));

			if (!inputStream.equals(null)) {
				try {
					MusicFile musicFile = new MusicFile();
					musicFile.setName(originalFilename.substring(0, originalFilename.lastIndexOf(".")));
					musicFile.setSize(String.valueOf(fileUpload.getSize()));
					musicFile.setExtension(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
					musicFile.setSystemFileName(systemFileName);
					musicFile.setRecordDateTime(recordDateTime);
					musicFile.setMd5(FileMD5.getFileMD5(new File(fileDir + "/" + systemFileName)));
					musicFile.setDuration(200);

					musicFileDao.insertMusicFile(musicFile);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
