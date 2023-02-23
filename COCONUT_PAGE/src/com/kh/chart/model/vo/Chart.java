package com.kh.chart.model.vo;

import java.sql.Date;

public class Chart {
	
	private int empNo; //EMP_NO NUMBER CONSTRAINT APPROVAL_EMP_NO_NN NOT NULL,
    private int aprOrder; //APR_ORDER NUMBER CONSTRAINT APR_ORDER_NN NOT NULL,
    private Date openDate; //OPEN_DATE DATE,
    private Date aprDate; //APR_DATE DATE,
    private Date receiveDate; //RECEIVE_DATE DATE DEFAULT SYSDATE,
    private int aprYN; //APR_YN CHAR(1) CHECK (APR_YN IN ('1','2','3','4')),
    private String aprComment; //APR_COMMENT VARCHAR2(300), -- COMMENT 예약어라 테이블 생성 안되서 erd랑 컬럼명변경.
    private int aprId; //APR_ID VARCHAR2(60)	

	
    
    public Chart() {
		super();
	}


	public Chart(int empNo, int aprOrder, Date openDate, Date aprDate, Date receiveDate, int aprYN,
			String aprComment, int aprId, int count) {
		super();
		this.empNo = empNo;
		this.aprOrder = aprOrder;
		this.openDate = openDate;
		this.aprDate = aprDate;
		this.receiveDate = receiveDate;
		this.aprYN = aprYN;
		this.aprComment = aprComment;
		this.aprId = aprId;
	}
	
	

	// 문서 차트
	public Chart(int aprYN, int aprId) {
		super();
		this.aprYN = aprYN;
		this.aprId = aprId;
	}



	public int getEmpNo() {
		return empNo;
	}



	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}



	public int getAprOrder() {
		return aprOrder;
	}



	public void setAprOrder(int aprOrder) {
		this.aprOrder = aprOrder;
	}



	public Date getOpenDate() {
		return openDate;
	}



	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}



	public Date getAprDate() {
		return aprDate;
	}



	public void setAprDate(Date aprDate) {
		this.aprDate = aprDate;
	}



	public Date getReceiveDate() {
		return receiveDate;
	}



	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}



	public int getAprYN() {
		return aprYN;
	}



	public void setAprYN(int aprYN) {
		this.aprYN = aprYN;
	}



	public String getAprComment() {
		return aprComment;
	}



	public void setAprComment(String aprComment) {
		this.aprComment = aprComment;
	}



	public int getAprId() {
		return aprId;
	}



	public void setAprId(int aprId) {
		this.aprId = aprId;
	}



	@Override
	public String toString() {
		return "Chart [empNo=" + empNo + ", aprOrder=" + aprOrder + ", openDate=" + openDate + ", aprDate=" + aprDate
				+ ", receiveDate=" + receiveDate + ", aprYN=" + aprYN + ", aprComment=" + aprComment + ", aprId="
				+ aprId + "]";
	}
	
	
	
	
}
