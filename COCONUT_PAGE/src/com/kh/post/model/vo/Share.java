package com.kh.post.model.vo;

import java.sql.Date;

public class Share {

	private int postNo; // POST_NO NUMBER CONSTRAINT POST_PK PRIMARY KEY,
	private int postTypeNo; // POST_TYPE NUMBER CONSTRAINT POST_TYPE_NN NOT NULL, 
	private String postTitle; // POST_TITLE VARCHAR2(150) CONSTRAINT POST_TITLE_NN NOT NULL,
	private String postContent; // POST_CONTENT VARCHAR2(3000),
	private Date regDate;// REG_DATE DATE DEFAULT SYSDATE CONSTRAINT POST_REG_DATE_NN NOT NULL,
	private int viewCount; // VIEW_COUNT NUMBER DEFAULT 0 CONSTRAINT POST_VIEW_COUNT_NN NOT NULL,
	private String deleteYN; // DELETE_YN CHAR(1) DEFAULT 'N' CHECK(DELETE_YN IN('Y', 'N')),
	private String emp_no;// EMP_NO NUMBER CONSTRAINT POST_EMP_NO_NN NOT NULL
	private String titleImg;
	private int commentCount;
	private String deptName;
	
	
	








	public Share() {
		super();
	}




	public Share(int postNo, int postTypeNo, String postTitle, String postContent, Date regDate, int viewCount,
			String deleteYN, String emp_no) {
		super();
		this.postNo = postNo;
		this.postTypeNo = postTypeNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
		this.viewCount = viewCount;
		this.deleteYN = deleteYN;
		this.emp_no = emp_no;
	}
	
	
	
	
	
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getPostTypeNo() {
		return postTypeNo;
	}
	public void setPostTypeNo(int postTypeNo) {
		this.postTypeNo = postTypeNo;
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
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	
	
	public String getTitleImg() {
		return titleImg;
	}


	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	

	public int getCommentCount() {
		return commentCount;
	}




	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	

	public String getDeptName() {
		return deptName;
	}




	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	
	
	@Override
	public String toString() {
		return "Post [postNo=" + postNo + ", postTypeNo=" + postTypeNo + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", regDate=" + regDate + ", viewCount=" + viewCount + ", deleteYN=" + deleteYN
				+ ", emp_no=" + emp_no + "]";
	}
	
	
	
	
	

	
}
