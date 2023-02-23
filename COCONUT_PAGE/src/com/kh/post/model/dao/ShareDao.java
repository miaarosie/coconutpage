package com.kh.post.model.dao;


import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.PageInfo;
import com.kh.post.model.vo.Like;
import com.kh.post.model.vo.Reply;
import com.kh.post.model.vo.Share;
import com.kh.post.model.vo.ShareAttachment;

public class ShareDao {
	
	private Properties prop = new Properties();

	public ShareDao() {
		String fileName = ShareDao.class.getResource("/sql/post/share-mapper.xml").getPath();
		
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
			pstmt=conn.prepareStatement(sql);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
		
	}
	
	public int searchListCount(Connection conn, String searchField, String searchText) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		
		try {
		
			if (searchField.equals("postTitle")) {
				
				sql = prop.getProperty("searchListCountByTitle");
					
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, searchText);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					listCount = rset.getInt("COUNT");
				}
				
			} else {
				sql = prop.getProperty("searchListCountByEmpNo");
				
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, searchText);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					listCount = rset.getInt("COUNT");
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		
		
		return listCount;
	}
	
	
	public ArrayList<Share> selectShareList(Connection conn, PageInfo pi) {
		
		ArrayList<Share> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		String sql = prop.getProperty("selectShareList");
		
		try {
			
			/*
			 * boardLimit 가 5이라는 가정하에
			 * currentPage = 1 => 시작값 1, 끝값 10
			 * currentPage = 2 => 시작값 11, 끝값 20
			 * currentPage = 3 => 시작값 11, 끝값 15
			 * 
			 * 시작값 = (currentPage - 1) * boardLimit + 1
			 * 끝값 = 시작값 + boardLimit - 1
			 * 
			 */
			
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				
				Share s = new Share();
				s.setPostNo(rset.getInt("POST_NO"));
				s.setPostTitle(rset.getString("POST_TITLE"));
				s.setViewCount(rset.getInt("VIEW_COUNT"));
				s.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	
	public ArrayList<Share> selectShareList(Connection conn, PageInfo pi, String searchField, String searchText) {
		
		ArrayList<Share> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		String sql = null;
		
		if(searchField.equals("empNo")) {
			sql = prop.getProperty("selectShareListByEmpNo");
		}else {
			sql = prop.getProperty("selectShareListByTitle");
		}
		
		
		
		try {
			
						
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				
				Share s = new Share();
				s.setPostNo(rset.getInt("POST_NO"));
				s.setPostTitle(rset.getString("POST_TITLE"));
				s.setViewCount(rset.getInt("VIEW_COUNT"));
				s.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	
	
	public int insertSharePost(Connection conn, Share s) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertSharePost");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, s.getPostTitle());
			pstmt.setString(2, s.getPostContent());
			pstmt.setInt(3, Integer.parseInt(s.getEmp_no()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int insertShareAttachmentList(Connection conn, ArrayList<ShareAttachment> list) {
		
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertShareAttachmentList");
		
		try {
			
			for(ShareAttachment at : list) {
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				
				result *= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		
		return result;
	}
	
	public int increaseCount(Connection conn, int postNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, postNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		
		return result;
		
	}
	
	public Share selectPost(Connection conn, int postNo) {
		
		Share s = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPost");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				s = new Share();
				s.setPostNo(rset.getInt("POST_NO"));
				s.setPostTitle(rset.getString("POST_TITLE"));
				s.setPostContent(rset.getString("POST_CONTENT"));
				s.setRegDate(rset.getDate("REG_DATE"));
				s.setViewCount(rset.getInt("VIEW_COUNT"));
				s.setEmp_no(rset.getString("EMP_NAME"));
				s.setDeptName(rset.getString("DEPT_NAME"));
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return s;
		
	}
	
	public ArrayList<ShareAttachment> selectShareAttachmentList(Connection conn, int postNo) {
		
		ArrayList<ShareAttachment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				
		String sql = prop.getProperty("selectShareAttachmentList");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				
				ShareAttachment at = new ShareAttachment();
				
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				
				list.add(at);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
				
		return list;
		
	}
	
	
	public int updatePost(Connection conn, Share s) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePost");
		
		try {
			
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getPostTitle());
			pstmt.setString(2, s.getPostContent());
			pstmt.setInt(3, s.getPostNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int updateShareAttachment(Connection conn, ArrayList<ShareAttachment> updateList) {
		
		int result =1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateShareAttachment");
		
		try {
			
			for(ShareAttachment at : updateList) {
				
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setInt(3, at.getFileNo());
				
				result *= pstmt.executeUpdate(); 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		
		
		return result;
		
		
	}
	
	public int insertNewShareAttachment(Connection conn, ArrayList<ShareAttachment> insertList) {
		
		int result = 1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNewShareAttachment");
				
		try {
			
			for(ShareAttachment at : insertList) {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				pstmt.setInt(5, at.getPostNo());
				
				result *= pstmt.executeUpdate(); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteShare(Connection conn, int postNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteShare");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, postNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
		
		
	}
	
	
	
	
	
//	===============================================================================
	//종아요 댓글
	
	public int deleteReply(Connection conn, int replyNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReply");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result; 
	}
	
	public int insertReply(Connection conn, Reply r) {
		
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getPostNo());
			pstmt.setString(3, r.getEmpNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}
		
		return result;
		
		
	}
	
	public ArrayList<Reply> selectReplyList(Connection conn, int postNo) {
		
		ArrayList<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReplyList");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	
	
	public int insertNestedReply(Connection conn, Reply r) {
		
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNestedReply");
		
		try {
		
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getParentReply());
			pstmt.setInt(3, r.getPostNo());
			pstmt.setString(4, r.getEmpNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int insertLike(Connection conn, Like l) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLike");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, l.getPostNo());
			pstmt.setInt(2, l.getEmpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
		
	}
	
public int deleteLike(Connection conn, Like l) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteLike");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, l.getPostNo());
			pstmt.setInt(2, l.getEmpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
		
	}

	public int countLike(Connection conn, Like l) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("countLike");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, l.getPostNo());
			
			rset= pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("COUNT");
				
			}
			
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
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
			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				
				l.setEmpNo(rset.getInt("EMP_NO"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return l;
	}
	
	
}
