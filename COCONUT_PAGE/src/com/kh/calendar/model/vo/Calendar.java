package com.kh.calendar.model.vo;

public class Calendar {
	
	private int schdNo; // SCHD_NO NUMBER CONSTRAINT CALENDAR_PK PRIMARY KEY,
	private String schdTitle; // SCHD_TITLE VARCHAR2(50) CONSTRAINT SCHD_TITLE_NN NOT NULL,
	private String schdContent;// SCHD_CONTENT	VARCHAR2(300),
	private int schdCategory;// SCHD_CATEGORY CHAR(1) CONSTRAINT SCHD_CATEGORY_NN NOT NULL,
	private String startDate;// START_DATE DATE CONSTRAINT START_DATE_NN NOT NULL,	
	private String endDate;// END_DATE	DATE CONSTRAINT END_DATE_NN NOT NULL,
	private String deleteYN;// DELETE_YN	CHAR(1) DEFAULT 'N' CONSTRAINT SCHD_DELETE_YN_NN NOT NULL,
	private int empNo;// EMP_NO NUMBER NOT NULL
	
	
	
	public Calendar() {
		super();
	}
	
	
	public Calendar(int schdNo, String schdTitle, String schdContent, int schdCategory, String startDate,
			String endDate, String deleteYN, int empNo) {
		super();
		this.schdNo = schdNo;
		this.schdTitle = schdTitle;
		this.schdContent = schdContent;
		this.schdCategory = schdCategory;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deleteYN = deleteYN;
		this.empNo = empNo;
	}
	
	
	
	public int getSchdNo() {
		return schdNo;
	}
	public void setSchdNo(int schdNo) {
		this.schdNo = schdNo;
	}
	public String getSchdTitle() {
		return schdTitle;
	}
	public void setSchdTitle(String schdTitle) {
		this.schdTitle = schdTitle;
	}
	public String getSchdContent() {
		return schdContent;
	}
	public void setSchdContent(String schdContent) {
		this.schdContent = schdContent;
	}
	public int getSchdCategory() {
		return schdCategory;
	}
	public void setSchdCategory(int schdCategory) {
		this.schdCategory = schdCategory;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	@Override
	public String toString() {
		return "Calendar [schdNo=" + schdNo + ", schdTitle=" + schdTitle + ", schdContent=" + schdContent
				+ ", schdCategory=" + schdCategory + ", startDate=" + startDate + ", endDate=" + endDate + ", deleteYN="
				+ deleteYN + ", empNo=" + empNo + "]";
	}

}
