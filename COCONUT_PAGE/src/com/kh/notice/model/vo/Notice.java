package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {

	private int noticeNo; // NOTICE_NO NUMBER No
	private int noticeType; // NOTICE_TYPE NUMBER No
	private String noticeTitle; // NOTICE_TITLE VARCHAR2(150 BYTE) No
	private String noticeContent; // NOTICE_CONTENT VARCHAR2(3000 BYTE) Yes
	private String noticeDate; // NOTICE_DATE DATE No SYSDATE
	private int viewCount; // VIEW_COUNT NUMBER No '0'
	private String deleteYn; // DELETE_YN CHAR(1 BYTE) No 'N'
	private int empNo; // EMP_NO NUMBER No
	private String originFile; // ORIGIN_FILE VARCHAR2(300 BYTE) Yes
	private String changeFile; // CHANGE_FILE VARCHAR2(300 BYTE) Yes
	private String empName; // EMP_Name NUMBER No

	public Notice() {
		super();
	}

	public Notice(int noticeNo, String noticeTitle, String noticeDate, String empName, int viewCount) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.empName = empName;
		this.viewCount = viewCount;
	}

	public Notice(int noticeNo, int noticeType, String noticeTitle, String noticeContent, String noticeDate,
			int viewCount, int empNo, String originFile, String changeFile, String empName) {
		super();
		this.noticeNo = noticeNo;
		this.noticeType = noticeType;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
		this.viewCount = viewCount;
		this.empNo = empNo;
		this.originFile = originFile;
		this.changeFile = changeFile;
		this.empName = empName;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public int getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getOriginFile() {
		return originFile;
	}

	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}

	public String getChangeFile() {
		return changeFile;
	}

	public void setChangeFile(String changeFile) {
		this.changeFile = changeFile;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeType=" + noticeType + ", noticeTitle=" + noticeTitle
				+ ", noticeContent=" + noticeContent + ", noticeDate=" + noticeDate + ", viewCount=" + viewCount
				+ ", deleteYn=" + deleteYn + ", empNo=" + empNo + ", originFile=" + originFile + ", changeFile="
				+ changeFile + "]";
	}

}
