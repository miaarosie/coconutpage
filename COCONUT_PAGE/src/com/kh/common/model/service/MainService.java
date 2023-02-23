package com.kh.common.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.approval.model.vo.Approval;
import com.kh.card.model.vo.Kanban;
import com.kh.common.model.dao.MainDao;
import com.kh.notice.model.vo.Notice;
import com.kh.post.model.vo.Post1;
import com.kh.post.model.vo.Share;

public class MainService {
	
	public ArrayList<Share> selectShareList(int postNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Share> shareList = new MainDao().selectShareList(conn, postNo);
		
		close(conn);
		
		return shareList;
		
	}
	
	 public ArrayList<Post1> selectWagleList(int postNo) {
		
		 Connection conn = getConnection();
		
		ArrayList<Post1> wagleList = new MainDao().selectWagleList(conn, postNo);
		
		close(conn);
		
		return wagleList;
		
	 }
	
	public ArrayList<Notice> selectNoticeList(int noticeType) {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> noticeList = new MainDao().selectNoticeList(conn, noticeType);
		
		close(conn);
		
		return noticeList;
		
	}
	
	public ArrayList<Approval> selectReceiveList(int empNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Approval> receiveList = new MainDao().selectReceiveList(conn, empNo);
		
		close(conn);
		
		return receiveList;
		
	}
	
	public ArrayList<Approval> selectSendList(int empNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Approval> sendList = new MainDao().selectSendList(conn, empNo);
		
		close(conn);
		
		return sendList;
	}
	
	public ArrayList<Kanban> selectToDoList(int empNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Kanban> toDoList = new MainDao().selectToDoList(conn, empNo);
		
		close(conn);
		
		return toDoList;
		
		
	}
	
	public ArrayList<Kanban> selectProgressList(int empNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Kanban> progressList = new MainDao().selectProgressList(conn, empNo);
		
		close(conn);
		
		return progressList;
	}

}
