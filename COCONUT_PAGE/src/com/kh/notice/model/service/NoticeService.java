package com.kh.notice.model.service;

//import com.kh.common.JDBCTemplate;
//JDBCTemplate 클래스 모든 메소들 그냥 가져다 쓰겠다.
import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeList(int boardNo, PageInfo pi) {

		Connection conn = getConnection();

		ArrayList<Notice> nList = new NoticeDao().selectNoticeList(conn,boardNo,pi);
	
		close(conn);

		return nList;
	}
	
	public ArrayList<Notice> searchNoticeList(int boardNo, PageInfo pi, String keyword) {

		Connection conn = getConnection();

		ArrayList<Notice> nList = new NoticeDao().searchNoticeList(conn,boardNo, pi, keyword);
	
		close(conn);

		return nList;
	}
	
	
	public int totalNotice(int boardNo) {

		Connection conn = getConnection();

		int result = new NoticeDao().totalNotice(conn,boardNo);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public int totalSearchNotice(int boardNo, String keyword) {

		Connection conn = getConnection();

		int result = new NoticeDao().totalSearchNotice(conn,boardNo, keyword);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public int insertNotice(Notice n) {

		Connection conn = getConnection();

		int result = new NoticeDao().insertNotice(conn, n);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public int increaseViewCount(int noticeNo) {

		Connection conn = getConnection();

		int result = new NoticeDao().increaseViewCount(conn, noticeNo);
	
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public Notice selectNoticeByNo(int noticeNo) {

		Connection conn = getConnection();

		Notice n = new NoticeDao().selectNoticeByNo(conn, noticeNo);
	
		close(conn);
		return n;
	}
	
	public int updateNotice(Notice n) {

		Connection conn = getConnection();

		int result = new NoticeDao().updateNotice(conn, n);
	
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);

		return result;
	}
	
	public int deleteNotice(int noticeNo) {

		Connection conn = getConnection();

		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);

		return result;
	}
	
}
