package com.kh.approval.model.vo;

public class Referrer {

	private String viewDate; // VIEW_DATE DATE Yes
	private int empNo; // EMP_NO NUMBER No
	private String aprId; // APR_ID VARCHAR2(60 BYTE) No
	private String empName;
	private String deptName;
	private String jobName;
	private String profile;

	public Referrer() {
		super();
	}

	public Referrer(int empNo) {
		super();
		this.empNo = empNo;
	}

	public Referrer(String viewDate, int empNo, String aprId) {
		super();
		this.viewDate = viewDate;
		this.empNo = empNo;
		this.aprId = aprId;
	}

	public Referrer(String viewDate, int empNo, String aprId, String empName, String deptName, String jobName,
			String profile) {
		super();
		this.viewDate = viewDate;
		this.empNo = empNo;
		this.aprId = aprId;
		this.empName = empName;
		this.deptName = deptName;
		this.jobName = jobName;
		this.profile = profile;
	}

	public String getViewDate() {
		return viewDate;
	}

	public void setViewDate(String viewDate) {
		this.viewDate = viewDate;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
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
		return "Referrer [viewDate=" + viewDate + ", empNo=" + empNo + ", aprId=" + aprId + ", empName=" + empName
				+ ", deptName=" + deptName + ", jobName=" + jobName + ", profile=" + profile + "]";
	}

}