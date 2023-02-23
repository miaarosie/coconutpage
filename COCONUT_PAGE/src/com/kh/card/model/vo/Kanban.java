package com.kh.card.model.vo;

public class Kanban {
	private int cardNo;
	private int cardStatement;
	private String cardTitle;
	private String cardContent;
	private String createDate;
	private	String dewDate;
	private int cardPriority;
	private int cardOrder;
	private String empNo;
	
	public Kanban() {
		super();
	};
	
	public Kanban(int cardNo, int cardStatement, String cardTitle, String cardContent, String createDate, String dewDate,
			int cardPriority, int cardOrder, String empNo) {
		super();
		this.cardNo = cardNo;
		this.cardStatement = cardStatement;
		this.cardTitle = cardTitle;
		this.cardContent = cardContent;
		this.createDate = createDate;
		this.dewDate = dewDate;
		this.cardPriority = cardPriority;
		this.cardOrder = cardOrder;
		this.empNo = empNo;
	}
	

	/*INSERT용 생성자*/
	public Kanban(String cardTitle, String cardContent, String dewDate, int cardPriority,
			String empNo) {
		super();
		this.cardTitle = cardTitle;
		this.cardContent = cardContent;
		this.dewDate = dewDate;
		this.cardPriority = cardPriority;
		this.empNo = empNo;
	}

	/*UPDATE용 생성자*/
	public Kanban(int cardNo, String cardContent, String dewDate, int cardPriority) {
		super();
		this.cardNo = cardNo;
		this.cardContent = cardContent;
		this.dewDate = dewDate;
		this.cardPriority = cardPriority;
	}
	

	public int getCardNo() {
		return cardNo;
	}


	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public int getCardStatement() {
		return cardStatement;
	}

	public void setCardStatement(int cardStatement) {
		this.cardStatement = cardStatement;
	}

	public String getCardTitle() {
		return cardTitle;
	}

	public void setCardTitle(String cardTitle) {
		this.cardTitle = cardTitle;
	}

	public String getCardContent() {
		return cardContent;
	}

	public void setCardContent(String cardContent) {
		this.cardContent = cardContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDewDate() {
		return dewDate;
	}

	public void setDewDate(String dewDate) {
		this.dewDate = dewDate;
	}

	public int getCardPriority() {
		return cardPriority;
	}

	public void setCardPriority(int cardPriority) {
		this.cardPriority = cardPriority;
	}

	public int getCardOrder() {
		return cardOrder;
	}

	public void setCardOrder(int cardOrder) {
		this.cardOrder = cardOrder;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setmpNo(String empNo) {
		this.empNo = empNo;
	}

	@Override
	public String toString() {
		return "Kanban [cardNo=" + cardNo + ", cardStatement=" + cardStatement + ", cardTitle=" + cardTitle + ", cardContent="
				+ cardContent + ", createDate=" + createDate + ", dewDate=" + dewDate + ", cardPriority=" + cardPriority
				+ ", cardOrder=" + cardOrder + ", empNo=" + empNo + "]";
	}
}
