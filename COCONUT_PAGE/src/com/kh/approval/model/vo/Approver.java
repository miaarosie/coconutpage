package com.kh.approval.model.vo;

public class Approver {

	private int empNo; // EMP_NO NUMBER No 1 사번
	private int aprOrder; // APR_ORDER NUMBER No 2 결재순번 1번부터 시작
	private String openDate; // OPEN_DATE DATE Yes 3 열람일자
	private String aprDate; // APR_DATE DATE Yes 4 결재일자
	private String receiveDate; // RECEIVE_DATE DATE Yes SYSDATE 5 받은일자
	private String aprYN; // APR_YN CHAR(1 BYTE) Yes 6 승인여부
	private String aprComment; // APR_COMMENT VARCHAR2(300 BYTE) Yes 7 결재의견
	private String aprId; // APR_ID VARCHAR2(60 BYTE) No 8 결재번호
	private String empName;
	private String deptName;
	private String jobName;
	private String profile;

	public Approver() {
		super();
	}

	public Approver(int empNo, int aprOrder) {
		super();
		this.empNo = empNo;
		this.aprOrder = aprOrder;
	}

	public Approver(int empNo, int aprOrder, String openDate, String aprDate, String receiveDate, String aprYN,
			String aprComment, String aprId) {
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

	public Approver(int empNo, int aprOrder, String openDate, String aprDate, String receiveDate, String aprYN,
			String aprComment, String aprId, String empName, String deptName, String jobName, String profile) {
		super();
		this.empNo = empNo;
		this.aprOrder = aprOrder;
		this.openDate = openDate;
		this.aprDate = aprDate;
		this.receiveDate = receiveDate;
		this.aprYN = aprYN;
		this.aprComment = aprComment;
		this.aprId = aprId;
		this.empName = empName;
		this.deptName = deptName;
		this.jobName = jobName;
		this.profile = profile;
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

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getAprDate() {
		return aprDate;
	}

	public void setAprDate(String aprDate) {
		this.aprDate = aprDate;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getAprYN() {
		return aprYN;
	}

	public void setAprYN(String aprYN) {
		this.aprYN = aprYN;
	}

	public String getAprComment() {
		return aprComment;
	}

	public void setAprComment(String aprComment) {
		this.aprComment = aprComment;
	}

	public String getAprId() {
		return aprId;
	}

	public void setAprId(String aprId) {
		this.aprId = aprId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Approver [empNo=" + empNo + ", aprOrder=" + aprOrder + ", openDate=" + openDate + ", aprDate=" + aprDate
				+ ", receiveDate=" + receiveDate + ", aprYN=" + aprYN + ", aprComment=" + aprComment + ", aprId="
				+ aprId + ", empName=" + empName + ", deptName=" + deptName + ", jobName=" + jobName + ", profile="
				+ profile + "]";
	}

	

}
