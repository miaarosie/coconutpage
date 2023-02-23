package com.kh.approval.model.vo;

import java.sql.Date;

public class DocSpent {

	// 필드부
	private int docNo;
	private Date spentDate;
	private String corpName;
	private String corpNo;
	private String history; // spentName
	private int spentMoney;
	private String aprId;
	
	// 생성자부
	public DocSpent() { }

	public DocSpent(int docNo, Date spentDate, String corpName, String corpNo, String history, int spentMoney,
			String aprId) {
		super();
		this.docNo = docNo;
		this.spentDate = spentDate;
		this.corpName = corpName;
		this.corpNo = corpNo;
		this.history = history;
		this.spentMoney = spentMoney;
		this.aprId = aprId;
	}
	
	// 지출내역 추가용 생성자
	public DocSpent(Date spentDate, String corpName, String corpNo, String history, int spentMoney) {
		super();
		this.spentDate = spentDate;
		this.corpName = corpName;
		this.corpNo = corpNo;
		this.history = history;
		this.spentMoney = spentMoney;
	}
	
	// 지출내역 수정용 생성자
	public DocSpent(Date spentDate, String corpName, String corpNo, String history, int spentMoney, String aprId) {
		super();
		this.spentDate = spentDate;
		this.corpName = corpName;
		this.corpNo = corpNo;
		this.history = history;
		this.spentMoney = spentMoney;
		this.aprId = aprId;
	}


	// 메소드부
	public int getDocNo() {
		return docNo;
	}

	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}

	public Date getSpentDate() {
		return spentDate;
	}

	public void setSpentDate(Date spentDate) {
		this.spentDate = spentDate;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpNo() {
		return corpNo;
	}

	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public int getSpentMoney() {
		return spentMoney;
	}

	public void setSpentMoney(int spentMoney) {
		this.spentMoney = spentMoney;
	}

	public String getAprId() {
		return aprId;
	}

	public void setAprId(String aprId) {
		this.aprId = aprId;
	}

	@Override
	public String toString() {
		return "DocSpent [docNo=" + docNo + ", spentDate=" + spentDate + ", corpName=" + corpName + ", corpNo=" + corpNo
				+ ", history=" + history + ", spentMoney=" + spentMoney + ", aprId=" + aprId + "]";
	}
	
}
