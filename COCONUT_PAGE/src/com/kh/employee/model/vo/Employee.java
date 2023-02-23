package com.kh.employee.model.vo;

import java.sql.Date;

public class Employee {
	
	private int empNo;
	private String empName;
	private String address;
	private String phone;
	private String profile;
	private String email;
	private String empPwd;
	private String managerYN;
	private String empNum;
	private String tel;
	private Date enrollDate;
	private Date leaveDate;
	private String nickName;
	private String gender;
	private String deptCode;
	private String jobCode;
	
	public Employee() {
		super();
	}

	public Employee(int empNo, String empName, String deptCode, String jobCode) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
	}

	public Employee(int empNo, String empName, String profile, String deptCode, String jobCode) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.profile = profile;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
	}

	public Employee(int empNo, String empName, String phone, String profile, String email, String tel, String nickName,
			String gender, String deptCode, String jobCode) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.phone = phone;
		this.profile = profile;
		this.email = email;
		this.tel = tel;
		this.nickName = nickName;
		this.gender = gender;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
	}

	public Employee(int empNo, String empName, String address, String phone, String profile, String email,
			String empPwd, String managerYN, String empNum, String tel, Date enrollDate, Date leaveDate,
			String nickName, String gender, String deptCode, String jobCode) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.address = address;
		this.phone = phone;
		this.profile = profile;
		this.email = email;
		this.empPwd = empPwd;
		this.managerYN = managerYN;
		this.empNum = empNum;
		this.tel = tel;
		this.enrollDate = enrollDate;
		this.leaveDate = leaveDate;
		this.nickName = nickName;
		this.gender = gender;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getManagerYN() {
		return managerYN;
	}

	public void setManagerYN(String managerYN) {
		this.managerYN = managerYN;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", address=" + address + ", phone=" + phone
				+ ", profile=" + profile + ", email=" + email + ", empPwd=" + empPwd + ", managerYN=" + managerYN
				+ ", empNum=" + empNum + ", tel=" + tel + ", enrollDate=" + enrollDate + ", leaveDate=" + leaveDate
				+ ", nickName=" + nickName + ", gender=" + gender + ", deptCode=" + deptCode + ", jobCode=" + jobCode
				+ "]";
	}
	
}
