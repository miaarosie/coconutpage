package com.kh.post.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.common.model.vo.PageInfo;
import com.kh.post.model.vo.Like;
import com.kh.post.model.vo.Post2;
import com.kh.post.model.vo.Post2File;
import com.kh.post.model.vo.Reply;

public class Post2Dao {

	private Properties prop = new Properties();
	
	public Post2Dao() {
		String fileName = Post2Dao.class.getResource("/sql/post/post2-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return listCount;
	}
	
	public int selectListCount(Connection conn, String searchword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql= prop.getProperty("selectpost_titleCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return listCount;
	}
	
	public int selectListCountName(Connection conn, String searchword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql= prop.getProperty("selectpost_titleCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return listCount;
	}
	
	public ArrayList<Post2> selectList(Connection conn, PageInfo pi) {
		ArrayList<Post2> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
			int endRow = startRow + pi.getBoardLimit()-1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Post2(rset.getInt("POST_NO")
								 , rset.getString("POST_TITLE")
								 , rset.getString("EMP_NAME")
								 , rset.getDate("REG_DATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return list;
	}
	
	public ArrayList<Post2> selectList(Connection conn, PageInfo pi, String keyword, String searchword) {
		ArrayList<Post2> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		if(keyword!=null && keyword.equals("title")) {
			sql= prop.getProperty("selectpost_title");
		} else if( keyword != null && keyword.equals("writer")){
			sql = prop.getProperty("selectemp_name");
		}
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
			int endRow = startRow + pi.getBoardLimit()-1;
			
			if((keyword != null && keyword.equals("title")) || (keyword != null && keyword.equals("writer"))) {
				pstmt.setString(1, searchword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Post2(rset.getInt("POST_NO")
								 , rset.getString("POST_TITLE")
								 , rset.getString("EMP_NAME")
								 , rset.getDate("REG_DATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return list;
	}
	
	
	public Post2 selectPost2(Connection conn, int postNo) {
		Post2 p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPost2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Post2(rset.getInt("POST_NO")
						    , rset.getString("POST_TITLE")
						    , rset.getString("DEPT_NAME")
						    , rset.getString("EMP_NAME")
						    , rset.getDate("REG_DATE")
						    , rset.getInt("VIEW_COUNT")
						    , rset.getString("POST_CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return p;
	}
	
	public Post2File selectPost2File(Connection conn, int postNo) {
		
		Post2File pf = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPost2File");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, postNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pf = new Post2File();
				pf.setFileNo(rset.getInt("FILE_NO"));
				pf.setOriginName(rset.getString("ORIGIN_NAME"));
				pf.setChangeName(rset.getString("CHANGE_NAME"));
				pf.setFilePath(rset.getString("FILE_PATH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return pf;
	}
	
	// 게시글조회수
	public int increaseCount(Connection conn, int postNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}
	
	
	public int insertPost2(Connection conn , Post2 p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPost2");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getPostTitle());
			pstmt.setString(2, p.getPostContent());
			pstmt.setInt(3, Integer.parseInt(p.getEmpNo()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}

	public int insertPost2File(Connection conn, Post2File pf) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPost2File");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pf.getOriginName());
			pstmt.setString(2, pf.getChangeName());
			pstmt.setString(3, pf.getFilePath());
			
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}

	public int updatePost2(Connection conn, Post2 p ) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePost2");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getPostTitle());
			pstmt.setString(2, p.getPostContent());
			pstmt.setInt(3, p.getPostNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}
	
	public int updatePost2File(Connection conn, Post2File pf) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePost2File");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pf.getOriginName());
			pstmt.setString(2, pf.getChangeName());
			pstmt.setInt(3, pf.getFileNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
 		} return result;
		
	}
	
	public int insertNewPost2File(Connection conn, Post2File pf) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewPost2File");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pf.getOriginName());
			pstmt.setString(2, pf.getChangeName());
			pstmt.setString(3, pf.getFilePath());
			pstmt.setInt(4, pf.getPostNo());
			
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}
	
	public int deletePost2File(Connection conn, int fileNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deletePost2File");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, fileNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}
	
	public int deletePost2(Connection conn, int postNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deletePost2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
		
	}
	
	// 게시글 좋아요 카운트
	public int countLike(Connection conn, Like l) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("countLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, l.getPostNo());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return result;
	}
	
	public Like selectLike(Connection conn, int postNo, int empNo) {
		Like l = new Like();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, empNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				l.setEmpNo(rset.getInt("EMP_NO"));
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return l;
	}
	
	// 좋아요 클릭
	public int insertLike(Connection conn, Like l) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, l.getPostNo());
			pstmt.setInt(2, l.getEmpNo());
			
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}
	
	// 좋아요 취소
	public int deleteLike(Connection conn, Like l) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, l.getPostNo());
			pstmt.setInt(2, l.getEmpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}
	
	// 댓글
	public int insertReply(Connection conn, Reply r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getPostNo());
			pstmt.setString(3, r.getEmpNo());
			
			result = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}
	
	public ArrayList<Reply> selectReplyList(Connection conn, int postNo) {
		ArrayList<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				r.setReplyNo(rset.getInt("REPLY_NO"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				r.setRegDate(rset.getString("REG_DATE"));
				r.setParentReply(rset.getInt("PARENT_REPLY"));
				r.setEmpNo(rset.getString("EMP_NAME"));
				r.setDeptName(rset.getString("DEPT_NAME"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return list;
	}
	
	public int insertNestedReply(Connection conn, Reply r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNestedReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getParentReply());
			pstmt.setInt(3, r.getPostNo());
			pstmt.setString(4, r.getEmpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}

	// 댓글삭제 
	public int deleteReply(Connection conn, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, replyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} return result;
	}
	

}
