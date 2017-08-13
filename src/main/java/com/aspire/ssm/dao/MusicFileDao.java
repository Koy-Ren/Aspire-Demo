package com.aspire.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aspire.ssm.bean.MusicFile;

public interface MusicFileDao {

	void insertMusicFile(MusicFile musicFile);

	int getCountByName(String name);

	List<MusicFile> getMusicFileByName(String name, int startPos, int pageSize);

	int getCount();

	List<MusicFile> getAllMusicFile(int startPos, int pageSize);

	int getCountByCondition(
			@Param("name") String name,
			@Param("durationStatus") String durationStatus,
			@Param("duration") int duration,
			@Param("fileStatus") String fileStatus,
			@Param("fileSize") String fileSize,
			@Param("timeBegin") Date timeBegin,
			@Param("timeEnd") Date timeEnd);

	List<MusicFile> getMusicFileByCondition(
			@Param("name") String name,
			@Param("durationStatus") String durationStatus,
			@Param("duration") int duration,
			@Param("fileStatus") String fileStatus,
			@Param("fileSize") String fileSize,
			@Param("timeBegin") Date timeBegin,
			@Param("timeEnd") Date timeEnd,
			@Param("startPos") int startPos,
			@Param("pageSize") int pageSize);

	void deleteFileByRecordDateTime(@Param("recordDateTime") Date recordDateTime);

}
