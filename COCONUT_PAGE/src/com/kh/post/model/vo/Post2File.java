package com.kh.post.model.vo;

import java.sql.Date;

public class Post2File {

	// 필드부
	
	private int fileNo;
	private String originName;
	private String changeName; 
	private String filePath; 
	private Date uploadDate;
	private int fileLevel; 
	private String deleteYN; 
	private int postNo; 
	
	
	// 생성자부
	
	public Post2File() {}

	public Post2File(int fileNo, String originName, String changeName, String filePath, Date uploadDate, int fileLevel,
			String deleteYN, int postNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.deleteYN = deleteYN;
		this.postNo = postNo;
	}

	
	// 메소드부
	
	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	@Override
	public String toString() {
		return "Post2File [fileNo=" + fileNo + ", originName=" + originName + ", changeName=" + changeName
				+ ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel + ", deleteYN="
				+ deleteYN + ", postNo=" + postNo + "]";
	}
	
	
}
