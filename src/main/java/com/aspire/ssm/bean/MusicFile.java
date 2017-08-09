package com.aspire.ssm.bean;

import java.util.Date;

public class MusicFile {
	
	private int id;
	private String name;
	private String systemFileName;
	private int duration;
	private String md5;
	private String extension;
	private String size;
	private Date recordDateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSystemFileName() {
		return systemFileName;
	}
	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Date getRecordDateTime() {
		return recordDateTime;
	}
	public void setRecordDateTime(Date recordDateTime) {
		this.recordDateTime = recordDateTime;
	}
	
}
