package com.aspire.ssm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.aspire.ssm.bean.MusicFile;
import com.aspire.ssm.dao.MusicFileDao;
import com.aspire.ssm.util.FileMD5;
import com.aspire.ssm.util.Page;

@Service
public class MusicFileServiceImpl implements IMusicFileService {

	@Autowired
	MusicFileDao musicFileDao;
	
	//添加音频文件
	public Model addMusicFile(MultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response, Model model)
			throws ParseException, FileUploadException, IOException {
		// TODO Auto-generated method stub

		String uid = request.getParameter("uid");
		String fileMd5 = request.getParameter("fileMd5");
		SimpleDateFormat timeS = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		SimpleDateFormat timeMS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String originalFilename = fileUpload.getOriginalFilename();
		String systemFileName = timeMS.format(new Date())
				+ originalFilename.substring(originalFilename.lastIndexOf("."));
		Date recordDateTime = timeS.parse(timeS.format(new Date()));
		
		MusicFile musicFile = new MusicFile();
		// String dirName =
		// request.getSession().getServletContext().getRealPath("/") +
		// "fileUpload";

		// double originalFilesize = request.getContentLength();// 获取源文件大小
		System.out.println(fileUpload.getName());
		System.out.println(fileUpload.getSize());
		System.out.println(originalFilename);
		System.out.println(uid);
		System.out.println(fileMd5);
		System.out.println(systemFileName);

		String tempFileDir = "E:\\tempmusicfile";
		String formalFileDir = "C:\\Users\\aspire\\workspace\\ssm\\src\\main\\webapp\\musicfile";
		String tempDir = tempFileDir + "/" + systemFileName;
		String formalDir = formalFileDir + "/" + systemFileName;
		File file = new File(tempFileDir);
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		if (!file.exists()) {
			file.mkdir();

		}
		try {
			inputStream = fileUpload.getInputStream();

			outputStream = new FileOutputStream(tempDir);
			byte[] buffer = new byte[1024 * 1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
				outputStream.flush();
			}
			outputStream.close();
			inputStream.close();
			System.out.println(FileMD5.getFileMD5(new File(tempDir)));

			// 获取音频时长
			try {
				MP3File f = (MP3File) AudioFileIO.read(new File(tempDir));
				MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
				System.out.println(audioHeader.getTrackLength());

				if (!inputStream.equals(null)) {
					try {
						
						musicFile.setName(originalFilename.substring(0, originalFilename.lastIndexOf(".")));
						musicFile.setSize(String.valueOf(fileUpload.getSize()));
						musicFile.setExtension(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
						musicFile.setSystemFileName(systemFileName);
						musicFile.setRecordDateTime(recordDateTime);
						musicFile.setMd5(FileMD5.getFileMD5(new File(tempDir)));
						musicFile.setDuration(audioHeader.getTrackLength());
						
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//校验MD5
		if(musicFile.getMd5().equals(fileMd5)){
			file = new File(formalFileDir);
			inputStream = null;
			outputStream = null;
			if (!file.exists()) {
				file.mkdir();

			}
			try {
				inputStream = new FileInputStream(tempDir);

				outputStream = new FileOutputStream(formalDir);
				byte[] buffer = new byte[1024 * 1024];
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
					outputStream.flush();
				}
				System.out.println(new File(tempDir).exists());
				outputStream.close();
				inputStream.close();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			File temp = new File(tempDir);
			System.out.println(temp.delete());
			System.out.println(new File(tempDir).exists());
			musicFileDao.insertMusicFile(musicFile);
			model.addAttribute("alert", "上传成功");
			//response.getWriter().write("上传成功");
			return model;
		}else{
			model.addAttribute("alert", "上传失败");
			return model;
		}

	}
	
	//搜索音频文件
	public Model searchMusicFile(HttpServletRequest request, Model model) throws ParseException {
		// TODO Auto-generated method stub

		String name = request.getParameter("name");
		String durationStatus = request.getParameter("durationStatus");
		String drn = request.getParameter("duration");
		String fileStatus = request.getParameter("fileStatus");
		String fileSize = request.getParameter("fileSize");
		String timeB = request.getParameter("timeBegin");
		String timeE = request.getParameter("timeEnd");
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		int duration;
		
		if(drn.equals("")){
			duration = 0;
		}else{
			duration = Integer.parseInt(drn);
		}
			
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeBegin = sdf.parse(timeB);
		Date timeEnd = sdf.parse(timeE);
		
		if (pageSize == null)
			pageSize = "4";
		System.out.println(pageSize);
		
		Page page = null;
		
		List<MusicFile> allMusicFile = new ArrayList<MusicFile>();
		
		int totalCount = (int) musicFileDao.getCountByCondition(name, durationStatus, duration, fileStatus, fileSize, timeBegin, timeEnd);
		
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
			allMusicFile = musicFileDao.getMusicFileByCondition(name, durationStatus, duration, fileStatus, fileSize, timeBegin, timeEnd, page.getStartPos(), page.getPageSize());
		}else{
			page = new Page(totalCount, 1);
			page.setPageSize(Integer.parseInt(pageSize));
			allMusicFile = musicFileDao.getMusicFileByCondition(name, durationStatus, duration, fileStatus, fileSize, timeBegin, timeEnd, page.getStartPos(), page.getPageSize());
		}
		
		model.addAttribute("allMusicFile", allMusicFile);
		model.addAttribute("page", page);
		return model;

	}

	public Model searchAllMusicFile(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow");

		Page page = null;

		List<MusicFile> allMusicFile = new ArrayList<MusicFile>();

		int totalCount = (int) musicFileDao.getCount();

		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			allMusicFile = musicFileDao.getAllMusicFile(page.getStartPos(), page.getPageSize());
		} else {
			page = new Page(totalCount, 1);
			allMusicFile = musicFileDao.getAllMusicFile(page.getStartPos(), page.getPageSize());
		}

		model.addAttribute("allMusicFile", allMusicFile);
		model.addAttribute("page", page);

		return model;
	}

	public Model deleteFile(HttpServletRequest request, Model model) throws ParseException {
		// TODO Auto-generated method stub
		String rDT = request.getParameter("recordDateTime");
		SimpleDateFormat timeS = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		Date recordDateTime = timeS.parse(rDT);
		musicFileDao.deleteFileByRecordDateTime(recordDateTime);
		
		model = searchAllMusicFile(request, model);
		
		return model;
	}

}
