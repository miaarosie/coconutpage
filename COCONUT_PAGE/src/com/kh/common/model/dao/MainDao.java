package com.kh.common.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.approval.model.vo.Approval;
import com.kh.card.model.vo.Kanban;
import com.kh.notice.model.vo.Notice;
import com.kh.post.model.dao.ShareDao;
import com.kh.post.model.vo.Post1;
import com.kh.post.model.vo.Share;

public class MainDao {
	
	private Properties prop = new Properties();

	public MainDao() {
		String fileName = ShareDao.class.getResource("/sql/mainmenu/mainmenu-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public ArrayList<Share> selectShareList(Connection conn, int postNo) {
		
		ArrayList<Share> shareList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectShareList");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, postNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Share s = new Share();
				s.setPostNo(rset.getInt("POST_NO"));
				s.setPostTitle(rset.getString("POST_TITLE"));
				
				shareList.add(s);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return shareList;
		
	}
	
	public ArrayList<Post1> selectWagleList(Connection conn, int postNo) {
		
		ArrayList<Post1> wagleList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectWagleList");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, postNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Post1 w = new Post1();
				w.setPostNo(rset.getInt("POST_NO"));
				w.setPostTitle(rset.getString("POST_TITLE"));
				
				wagleList.add(w);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return wagleList;
		
	}
	
	
	
	public ArrayList<Notice> selectNoticeList(Connection conn, int noticeType) {
		
		ArrayList<Notice> noticeList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeType);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				
				Notice n = new Notice();
				
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				
				noticeList.add(n);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return noticeList;
	}
	
	public ArrayList<Approval> selectReceiveList(Connection conn, int empNo) {
		
		ArrayList<Approval> receiveList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReceiveList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empNo);
			pstmt.setInt(2, empNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Approval app = new Approval();
				
				app.setAprId(rset.getString("APR_ID"));
				app.setAprTitle(rset.getString("APR_TITLE"));
				app.setWriteDate(rset.getString("ADATE"));
				app.setEmpNo(rset.getString("EMP_NAME"));
				app.setDocType(rset.getString("DOC_NO"));
				
				receiveList.add(app);
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			
		}
		
		return receiveList;
		
		
		
	}
	
	public ArrayList<Approval> selectSendList(Connection conn, int empNo) {
		
		ArrayList<Approval> sendList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSendList");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Approval app = new Approval();
				
				app.setAprId(rset.getString("APR_ID"));
				app.setAprTitle(rset.getString("APR_TITLE"));
				app.setUpdateDate(rset.getDate("UPDATE_DATE"));
				app.setEmpNo(rset.getString("EMP_NAME"));
				app.setDocType(rset.getString("DOC_NO"));
				
				sendList.add(app);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sendList;
	}
	
	public ArrayList<Kanban> selectToDoList(Connection conn, int empNo) {
		
		ArrayList<Kanban> toDoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectToDoList");
		
		try {

			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, empNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Kanban k = new Kanban();
				
				k.setCardTitle(rset.getString("CARD_TITLE"));
				k.setDewDate(rset.getString("CARD_DEW"));
				
				toDoList.add(k);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return toDoList;
	}
	
	public ArrayList<Kanban> selectProgressList(Connection conn, int empNo) {
		
		ArrayList<Kanban> progressList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProgressList");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, empNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Kanban k = new Kanban();
				
				k.setCardTitle(rset.getString("CARD_TITLE"));
				k.setDewDate(rset.getString("CARD_DEW"));
				
				progressList.add(k);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return progressList;
		
		
	}
	

}
