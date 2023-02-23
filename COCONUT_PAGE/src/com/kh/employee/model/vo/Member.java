package com.kh.employee.model.vo;

import java.sql.Date;

public class Member {

    // EMP_NO
    // EMP_NAME
    // ADDRESS
    // PHONE
    // PROFILE
    // EMAIL
    // EMP_PWD
    // MANAGER_YN
    // EMP_NUM
    // TEL
    // ENROLL_DATE
    // LEAVE_DATE
    // NICK_NAME
    // GENDER
    // DEPT_CODE
    // JOB_CODE

    private int empNo; // 사원번호
    private String empName;// 이름
    private String empAddress;// 주소
    private String phone;// 휴대폰번호
    private String profile;// 프로필사진경로
    private String email;// 이메일
    private String empPwd;// 비밀번호
    private String managerYn;// 관리자여부
    private String empNum;// 주민등록번호
    private String tel;// 전화번호
    private Date enrollDate;// 입사일
    private Date leaveDate;// 퇴사일
    private String nickName;// 닉네임
    private String gender;// 성별
    private String deptCode;// 부서코드
    private String jobCode;

    // 기본생성자
    public Member() {

    }

    // 생성자
    public Member(int empNo, String empName, String empAddress, String phone, String profile, String email,
            String empPwd, String managerYn, String empNum, String tel, Date enrollDate, Date leaveDate,
            String nickName, String gender, String deptCode, String jobCode, String deptName, String jobName) {
        super();
        this.empNo = empNo;
        this.empName = empName;
        this.empAddress = empAddress;
        this.phone = phone;
        this.profile = profile;
        this.email = email;
        this.empPwd = empPwd;
        this.managerYn = managerYn;
        this.empNum = empNum;
        this.tel = tel;
        this.enrollDate = enrollDate;
        this.leaveDate = leaveDate;
        this.nickName = nickName;
        this.gender = gender;
        this.deptCode = deptCode;
        this.jobCode = jobCode;
    }

    // 사원이 조회할때 생성자
    public Member(int empNo, String empName, String nickName, String email,
            String tel, String phone) {
        this.empNo = empNo;
        this.empName = empName;
        this.nickName = nickName;
        this.email = email;
        this.tel = tel;
        this.phone = phone;

    }

    // 사원추가 생성자
    public Member(int empNo, String empName, String gender, String empNum, String deptCode, String jobCode,
	            String email, String empPwd, String tel, String phone, String empAddress, String managerYN, String managerYn, Date enrollDate) {
        this.empNo = empNo;
        this.empName = empName;
        this.gender = gender;
        this.empNum = empNum;
        this.deptCode = deptCode;
        this.jobCode = jobCode;
        this.email = email;
        this.empPwd = empPwd;
        this.tel = tel;
        this.phone = phone;
        this.empAddress = empAddress;
        this.managerYn = managerYn;
        this.enrollDate = enrollDate;
       
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

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
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

    public String getManagerYn() {
        return managerYn;
    }

    public void setManagerYn(String managerYn) {
        this.managerYn = managerYn;
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
        return "Member [empNo=" + empNo + ", empName=" + empName + ", empAddress=" + empAddress + ", phone="
                + phone + ", profile=" + profile + ", email=" + email + ", empPwd=" + empPwd + ", managerYn="
                + managerYn + ", empNum=" + empNum + ", tel=" + tel + ", enrollDate=" + enrollDate + ", leaveDate="
                + leaveDate + ", nickName=" + nickName + ", gender=" + gender + ", deptCode=" + deptCode + ", jobCode="
                + jobCode + "]";
    }

//직급코드
}
