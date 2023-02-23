package com.kh.post.model.service;
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.model.vo.PageInfo;
import com.kh.post.model.dao.Post1Dao;
import com.kh.post.model.vo.Like;
import com.kh.post.model.vo.Post1;
import com.kh.post.model.vo.Post1File;
import com.kh.post.model.vo.Reply;


public class Post1Service {
	
	
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new Post1Dao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	public int selectListCount(String searchword) {
		
		Connection conn = getConnection();
		
		int listCount = new Post1Dao().selectListCount(conn, searchword);
		
		close(conn);
		return listCount;
	}
	
	
	public int selectListCountName(String searchword) {
		
		Connection conn = getConnection();
		
		int listCount = new Post1Dao().selectListCountName(conn, searchword);
		
		close(conn);
	
		return listCount;
	}
	
	public ArrayList<Post1> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Post1> list = new Post1Dao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Post1> selectList(PageInfo pi, String keyword, String searchword) {
		
		Connection conn = getConnection();
		
		ArrayList<Post1> list = new Post1Dao().selectList(conn, pi, keyword, searchword);
		
		close(conn);
		
		return list;
	}
	
	
	// 와글와글 게시판 글 조회 수
	public int increaseCount(int postNo) {
		
		Connection conn = getConnection();
		
		int result = new Post1Dao().increaseCount(conn, postNo);
		
		if(result > 0) {
			commit(conn);
		
		} else {
			rollback(conn);
		
		} 
		
		close(conn); 
		return result;
	}
		
	public int insertPost1(Post1 p, Post1File pf) {
		
		Connection conn = getConnection();
		
		int result1 = new Post1Dao().insertPost1(conn, p);
		int result2 = 1;
		
		if(pf != null) {
			result2 = new Post1Dao().insertPost1File(conn, pf);
		}
		if(result1 > 0 && result2 > 0) {
			commit(conn);
			
		} else {
			rollback(conn);
			
		} close(conn);
		
		return result1 * result2;
	} 
	
	public Post1 selectPost1(int postNo) {
		
		Connection conn = getConnection();
		
		Post1 p = new Post1Dao().selectPost1(conn, postNo);
		
		close(conn);
		
		return p;
	}
	
	public Post1File selectPost1File(int postNo) {
		
		Connection conn = getConnection();
		
		Post1File pf = new Post1Dao().selectPost1File(conn, postNo);
		
		close(conn);
		
		return pf;
	} 
	
	public int updatePost1(Post1 p, Post1File pf) {
		
		Connection conn = getConnection();
		
		int result1 = new Post1Dao().updatePost1(conn, p);
		
		int result2 = 1;
		
		if(pf != null) {
			
			if(pf.getFileNo() != 0) { 
				
				result2 = new Post1Dao().updatePost1File(conn, pf);
			
			} else { 

				result2 = new Post1Dao().insertNewPost1File(conn, pf);
			}
		}
		
		if(result1 > 0 && result2 > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
			
		} close(conn);
		
		return result1 * result2;
	}
	
	
	public int deletePost1(int postNo, int fileNo) {
		
		Connection conn = getConnection();
		
		int result1 = 1;
		
		if(fileNo != 0) {
			result1 = new Post1Dao().deletePost1File(conn, fileNo);
		}
		
		int result2 = new Post1Dao().deletePost1(conn, postNo);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		
		} else {
			rollback(conn);
		
		} close(conn);
		return result1 * result2;
	}
	
	// 와글와글 좋아요 갯수
	public int countLike(Like l) {
		
		Connection conn = getConnection();
		
		int result = new Post1Dao().countLike(conn, l);
		
		if(result > 0) {
		
			commit(conn);
			
		} else {
			rollback(conn);
		
		}
		close(conn);
		
		return result;
	}
	
	public Like selectLike(int postNo, int empNo) {
		
		Connection conn = getConnection();
		
		Like l = new Post1Dao().selectLike(conn, postNo, empNo);
		
		close(conn);
		
		return l;
	}
	
	// 좋아요 생성
	public int insertLike(Like l) {
		
		Connection conn = getConnection();
		
		int result = new Post1Dao().insertLike(conn, l);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		} 
		close(conn);
		
		return result;
	}
	
	// 좋아요 취소
	public int deleteLike(Like l) {
		
		Connection conn = getConnection();
		
		int result = new Post1Dao().deleteLike(conn, l);
		
		if(result > 0) {
			commit(conn);
		
		} else {
			rollback(conn);
		} 
		
		close(conn);
		
		return result;
	}

	// 댓글
	public int insertReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new Post1Dao().insertReply(conn,r);
		
		if(result > 0) {
			commit(conn);
		
		} else {
			rollback(conn);
		
		} close(conn);
		
		return result;
	}
	
	public ArrayList<Reply> selectReplyList(int postNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new Post1Dao().selectReplyList(conn, postNo);
		
		close(conn);
		
		return list;
	}
	
	public int insertNestedReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new Post1Dao().insertNestedReply(conn, r);
		
		if(result>0) {
			commit(conn);
		
		} else {
			rollback(conn);
		
		} close(conn);
		
		return result;
	}
	
	// 댓글 삭제
	public int deleteReply(int replyNo) {
		
		Connection conn = getConnection();
		
		int result = new Post1Dao().deleteReply(conn, replyNo);
		
		if(result > 0) {
			commit(conn);
		
		} else {
			rollback(conn);
		} 
		close(conn);
		
		return result;
	} 

}
