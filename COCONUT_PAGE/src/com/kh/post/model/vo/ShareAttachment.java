package com.kh.post.model.vo;

import java.sql.Date;

public class ShareAttachment {
	
	
	  private int fileNo; // FILE_NO NUMBER CONSTRAINT POST_FILE_PK PRIMARY KEY,
	  private String originName; // ORIGIN_NAME VARCHAR2(300),
	  private String changeName; // CHANGE_NAME VARCHAR2(300),
	  private String filePath; // FILE_PATH VARCHAR2(500),
	  private Date uploadDate; // UPLOAD_DATE DATE DEFAULT SYSDATE,
	  private int fileLevel;// FILE_LEVEL NUMBER,
	  private String deleteYN; // DELETE_YN CHAR(1) DEFAULT 'N' CHECK(DELETE_YN IN('Y', 'N')),
	  private int postNo; // POST_NO NUMBER CONSTRAINT POST_FILE_POST_NO_NN NOT NULL
	  
	  
	  
	public ShareAttachment() {
		super();
	}
	
	
	public ShareAttachment(int fileNo, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, String deleteYN, int postNo) {
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
	
	

}
