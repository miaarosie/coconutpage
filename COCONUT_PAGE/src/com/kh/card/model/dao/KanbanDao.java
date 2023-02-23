package com.kh.card.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.card.model.vo.Kanban;

public class KanbanDao {

	private Properties prop = new Properties();
	
	public KanbanDao() {
		
		String fileName = KanbanDao.class.getResource("/sql/card/card-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*카드리스트 전체 조회(완료)*/
	public ArrayList<Kanban> selectAllCards(Connection conn, int empNo) {
		ArrayList<Kanban> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllCards");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Kanban c = new Kanban();
						
				c.setCardNo(rset.getInt("CARD_NO"));
				c.setCardStatement(rset.getInt("CARD_STMT"));
				c.setCardTitle(rset.getString("CARD_TITLE"));
				c.setCardContent(rset.getString("CARD_CONTENT"));
				c.setDewDate(rset.getString("CARD_DEW").substring(0,10));
				c.setCardPriority(rset.getInt("CARD_PRIORITY"));
				c.setCardOrder(rset.getInt("CARD_ORDER"));
				
				list.add(c);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	/*카드추가(완료)*/
	public int insertCard(Connection conn, Kanban c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getCardTitle());
			pstmt.setString(2, c.getCardContent());
			Date d = Date.valueOf(c.getDewDate());
			pstmt.setDate(3, d);
			pstmt.setInt(4, c.getCardPriority());
			pstmt.setInt(5, Integer.parseInt(c.getEmpNo()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/*카드 추가 후 반영*/
	public Kanban insertNewCard(Connection conn, int empNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Kanban card = new Kanban();
		String sql = prop.getProperty("insertNewCard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				card = new Kanban();
				
				card.setCardNo(rset.getInt("CARD_NO"));
				card.setCardStatement(rset.getInt("CARD_STMT"));
				card.setCardTitle(rset.getString("CARD_TITLE"));
				card.setCardContent(rset.getString("CARD_CONTENT"));
				card.setDewDate(rset.getString("CARD_DEW"));
				card.setCardPriority(rset.getInt("CARD_PRIORITY"));
				card.setCardOrder(rset.getInt("CARD_ORDER"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return card;
	}
	
	
	/*카드수정*/
	public int updateCard(Connection conn, Kanban c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateCard");
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getCardContent());
			Date d = Date.valueOf(c.getDewDate());
			pstmt.setDate(2, d);
			pstmt.setInt(3, c.getCardPriority());
			pstmt.setInt(4, c.getCardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/*카드 이동 시 위치값 변경(완료)*/
	public int updateCardPosition(Connection conn, String[] cardNoArr, String[] orderArr, String[] statementArr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateCardPosition");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i< orderArr.length; i++) {
				
				pstmt.setInt(1, Integer.parseInt(statementArr[i]));
				pstmt.setInt(2, Integer.parseInt(orderArr[i]));
				pstmt.setInt(3, Integer.parseInt(cardNoArr[i]));
			
				result *= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	/*카드삭제*/
	public int deleteCard(Connection conn, int cardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  cardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
}
