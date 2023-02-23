package com.kh.post.model.vo;

public class Reply {
	
	private int replyNo;// REPLY_NO NUMBER CONSTRAINT REPLY_PK PRIMARY KEY,
    private String replyContent;// REPLY_CONTENT VARCHAR2(1500) CONSTRAINT REPLY_CONTENT_NN NOT NULL,
    private String regDate;// REG_DATE DATE DEFAULT SYSDATE CONSTRAINT REPLY_REG_DATE_NN NOT NULL,
    private int parentReply;// PARENT_REPLY NUMBER,
    private String deleteYN;// DELETE_YN CHAR(1) DEFAULT 'N' CHECK (DELETE_YN IN ('Y', 'N')),
    private int postNo; // POST_NO NUMBER CONSTRAINT REPLY_POST_NO_NN NOT NULL, -- 외래키
    private String empNo; // EMP_NO NUMBER CONSTRAINT REPLY_EMP_NO_NN NOT NULL -- 외래키
    private String deptName;
    

	public Reply() {
		super();
	}
	
	
	public Reply(int replyNo, String replyContent, String regDate, int parentReply, String deleteYN, int postNo,
			String empNo) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.regDate = regDate;
		this.parentReply = parentReply;
		this.deleteYN = deleteYN;
		this.postNo = postNo;
		this.empNo = empNo;
	}
	
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getParentReply() {
		return parentReply;
	}
	public void setParentReply(int parentReply) {
		this.parentReply = parentReply;
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


}
