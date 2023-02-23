package com.kh.approval.model.vo;

import java.sql.Date;

public class Approval {

	// 필드부
	private String aprId;
	private String aprTitle;
	private String aprContent;
	private String writeDate;
	private Date updateDate;
	private String aprStatus;
	private String originName;
	private String changeName;
	private String empNo;
	private String docType;
	private String aprComment;
	
	// 생성자부
	public Approval() { }

	public Approval(String aprId, String aprTitle, String aprContent, String writeDate, Date updateDate,
			String aprStatus, String originName, String changeName, String empNo, String aprComment, String docType) {
		super();
		this.aprId = aprId;
		this.aprTitle = aprTitle;
		this.aprContent = aprContent;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.aprStatus = aprStatus;
		this.originName = originName;
		this.changeName = changeName;
		this.empNo = empNo;
		this.aprComment = aprComment;
		this.docType = docType;
	}
	

	public Approval(String aprId, String aprTitle, String aprContent, String writeDate, Date updateDate,
			String aprStatus, String originName, String changeName, String empNo, String aprComment) {
		super();
		this.aprId = aprId;
		this.aprTitle = aprTitle;
		this.aprContent = aprContent;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.aprStatus = aprStatus;
		this.originName = originName;
		this.changeName = changeName;
		this.empNo = empNo;
		this.aprComment = aprComment;
	}

	// 메소드부
	public String getAprId() {
		return aprId;
	}

	public void setAprId(String aprId) {
		this.aprId = aprId;
	}

	public String getAprTitle() {
		return aprTitle;
	}

	public void setAprTitle(String aprTitle) {
		this.aprTitle = aprTitle;
	}

	public String getAprContent() {
		return aprContent;
	}

	public void setAprContent(String aprContent) {
		this.aprContent = aprContent;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAprStatus() {
		return aprStatus;
	}

	public void setAprStatus(String aprStatus) {
		this.aprStatus = aprStatus;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	

	public String getAprComment() {
		return aprComment;
	}

	public void setAprComment(String aprComment) {
		this.aprComment = aprComment;
	}

	@Override
	public String toString() {
		return "Approval [aprId=" + aprId + ", aprTitle=" + aprTitle + ", aprContent=" + aprContent + ", writeDate="
				+ writeDate + ", updateDate=" + updateDate + ", aprStatus=" + aprStatus + ", originName=" + originName
				+ ", changeName=" + changeName + ", empNo=" + empNo + ", docType="+ docType +"]";
	}
	
}
