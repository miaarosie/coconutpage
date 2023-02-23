package com.kh.card.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.card.model.dao.KanbanDao;
import com.kh.card.model.vo.Kanban;
import static com.kh.common.JDBCTemplate.*;

public class KanbanService {
	/*리스트 조회*/
	public ArrayList<Kanban> selectAllCards(int empNo){
		Connection conn = getConnection();
		
		ArrayList<Kanban> list = new KanbanDao().selectAllCards(conn, empNo);
		
		close(conn);
		
		return list;
	}
	
	/*카드 추가*/
	public int insertCard(Kanban c) {
		Connection conn = getConnection();
		
		int result = new KanbanDao().insertCard(conn, c);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/*카드 추가 후 반영*/
	public Kanban insertNewCard(int empNo) {
		Connection conn = getConnection();
		Kanban card = new Kanban();
		
		card = new KanbanDao().insertNewCard(conn, empNo);
		
		close(conn);
		
		return card;
	}
	
	
	/*카드 수정*/
	public int updateCard(Kanban c) {
		Connection conn = getConnection();
		
		int result = new KanbanDao().updateCard(conn, c);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	
	/*카드 이동 시 위치값 변경*/
	public int updateCardPosition(String[] cardNoArr, String[] orderArr, String[] statementArr) {
		Connection conn = getConnection();
		int result = new KanbanDao().updateCardPosition(conn, cardNoArr, orderArr, statementArr);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
		
	}
	
	
	/*카드 삭제*/
	public int deleteCard(int cardNo) {
		Connection conn = getConnection();
		
		int result = new KanbanDao().deleteCard(conn, cardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	
}
