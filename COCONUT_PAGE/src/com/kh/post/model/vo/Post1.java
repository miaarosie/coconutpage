package com.kh.post.model.vo;

import java.sql.Date;

public class Post1 {
	//필드부
	
	private int postNo;// POST_NO	NUMBER
	private int postType; // POST_TYPE	NUMBER
	private String postTitle; // POST_TITLE	VARCHAR2(150 BYTE)
	private String postContent;// POST_CONTENT	VARCHAR2(3000 BYTE)
	private Date regDate; // REG_DATE	DATE
	private int viewCount; // VIEW_COUNT	NUMBER
	private String deleteYN; // DELETE_YN	CHAR(1 BYTE)
	private String empNo; // EMP_NO	NUMBER
	private String deptName;
	
	// 생성자부
	
	public Post1() {}


	public Post1(int postNo, int postType, String postTitle, String postContent, Date regDate, int viewCount,
			String deleteYN, String empNo) {
		super();
		this.postNo = postNo;
		this.postType = postType;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
		this.viewCount = viewCount;
		this.deleteYN = deleteYN;
		this.empNo = empNo;
	}
	
	
	// 리스트 조회용 생성자부
	public Post1(int postNo, String postTitle, String empNo, Date regDate) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle; 
		this.empNo = empNo;
		this.regDate = regDate;
	}
	
	// 게시글 상세조회용 생성자부
	public Post1(int postNo, String postTitle, String deptName, String empNo,  Date regDate, int viewCount, String postContent) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.deptName = deptName;
		this.empNo = empNo;
		this.regDate = regDate;
		this.viewCount = viewCount;
		this.postContent = postContent;
	}

	// 메소드부
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getPostType() {
		return postType;
	}

	public void setPostType(int postType) {
		this.postType = postType;
	}


	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	
	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	@Override
	public String toString() {
		return "Post1 [postNo=" + postNo + ", postType=" + postType + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", regDate=" + regDate + ", viewCount=" + viewCount + ", deleteYN=" + deleteYN
				+ ", empNo=" + empNo + ", deptName=" + deptName + "]";
	}


}
