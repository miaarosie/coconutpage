package com.kh.notice.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.vo.Notice;

public class NoticeDao {

	private Properties prop = new Properties();

	public NoticeDao() {

		String fileName = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Notice> selectNoticeList(Connection conn, int boardNo, PageInfo pi) {

		ArrayList<Notice> nList = new ArrayList<Notice>();

		// 페이지 번호에 따른 시작 행 번호
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectNoticeList"));
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, startRow + pi.getBoardLimit() - 1);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				nList.add(new Notice(rset.getInt("NOTICE_NO"), rset.getString("NOTICE_TITLE"),
						rset.getString("NDATE"), rset.getString("EMP_NAME"), rset.getInt("VIEW_COUNT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return nList;
	}

	public ArrayList<Notice> searchNoticeList(Connection conn, int boardNo, PageInfo pi, String keyword) {

		ArrayList<Notice> nList = new ArrayList<Notice>();

		// 페이지 번호에 따른 시작 행 번호
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("searchNoticeList"));
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setInt(3, boardNo);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, startRow + pi.getBoardLimit() - 1);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				nList.add(new Notice(rset.getInt("NOTICE_NO"), rset.getString("NOTICE_TITLE"),
						rset.getString("NDATE"), rset.getString("EMP_NAME"), rset.getInt("VIEW_COUNT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return nList;
	}

	public int totalNotice(Connection conn, int boardNo) {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("totalNotice"));
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int totalSearchNotice(Connection conn, int boardNo, String keyword) {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("totalSearchNotice"));
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setInt(3, boardNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int insertNotice(Connection conn, Notice n) {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertNotice"));
			pstmt.setInt(1, n.getNoticeType());
			pstmt.setString(2, n.getNoticeTitle());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setInt(4, n.getEmpNo());
			pstmt.setString(5, n.getOriginFile());
			pstmt.setString(6, n.getChangeFile());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int increaseViewCount(Connection conn, int noticeNo) {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("increaseViewCount"));
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Notice selectNoticeByNo(Connection conn, int noticeNo) {

		Notice n = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectNoticeByNo"));
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				n = new Notice(rset.getInt("NOTICE_NO"),
				rset.getInt("NOTICE_TYPE"),
				rset.getString("NOTICE_TITLE"),
				rset.getString("NOTICE_CONTENT"),
				rset.getString("NDATE"),
				rset.getInt("VIEW_COUNT"),	
				rset.getInt("EMP_NO"),
				rset.getString("ORIGIN_FILE"),
				rset.getString("CHANGE_FILE"),
				rset.getString("EMP_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return n;
	}
	
	public int updateNotice(Connection conn, Notice n) {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateNotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getOriginFile());
			pstmt.setString(4, n.getChangeFile());
			pstmt.setInt(5, n.getNoticeNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

	public int deleteNotice(Connection conn, int noticeNo) {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("deleteNotice"));
			pstmt.setInt(1, noticeNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

}
