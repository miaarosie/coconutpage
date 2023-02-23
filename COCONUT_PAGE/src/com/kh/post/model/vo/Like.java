package com.kh.post.model.vo;

public class Like {
	
	int postNo;
	int empNo;
	
	
	public Like() {
		super();
	}
	public Like(int postNo, int empNo) {
		super();
		this.postNo = postNo;
		this.empNo = empNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	
	@Override
	public String toString() {
		return "Like [postNo=" + postNo + ", empNo=" + empNo + "]";
	}

}
