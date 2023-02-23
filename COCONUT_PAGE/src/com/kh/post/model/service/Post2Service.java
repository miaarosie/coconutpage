package com.kh.post.model.service;
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.model.vo.PageInfo;
import com.kh.post.model.dao.Post2Dao;
import com.kh.post.model.vo.Like;
import com.kh.post.model.vo.Post2;
import com.kh.post.model.vo.Post2File;
import com.kh.post.model.vo.Reply;

public class Post2Service {
	
	public int selectListCount() {
		Connection conn = getConnection();
		int listCount = new Post2Dao().selectListCount(conn);
		close(conn);
		return listCount;
	}
	
	public int selectListCount(String searchword) {
		Connection conn = getConnection();
		int listCount = new Post2Dao().selectListCount(conn, searchword);
		close(conn);
		return listCount;
	}
	
	public int selectListCountName(String searchword) {
		Connection conn = getConnection();
		int listCount = new Post2Dao().selectListCountName(conn, searchword);
		close(conn);
		return listCount;
	}
	
	public ArrayList<Post2> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Post2> list = new Post2Dao().selectList(conn, pi);
		close(conn);
		return list;
	}

	public ArrayList<Post2> selectList(PageInfo pi, String keyword, String searchword) {
		Connection conn = getConnection();
		
		ArrayList<Post2> list = new Post2Dao().selectList(conn, pi, keyword, searchword);
		close(conn);
		return list;
	}
	
	
	// 게시글조회수
	public int increaseCount(int postNo) {
		Connection conn = getConnection();
		int result = new Post2Dao().increaseCount(conn, postNo);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		close(conn); 
		return result;
	}
		
	public int insertPost2(Post2 p, Post2File pf) {
		Connection conn = getConnection();
		int result1 = new Post2Dao().insertPost2(conn, p);
		int result2 = 1;
		
		if(pf != null) {
			result2 = new Post2Dao().insertPost2File(conn, pf);
		}
		if(result1>0 && result2>0) {
			commit(conn);
		} else {
			rollback(conn);
		} close(conn);
		return result1*result2;
	} 
	
	public Post2 selectPost2(int postNo) {
		Connection conn = getConnection();
		
		Post2 p = new Post2Dao().selectPost2(conn, postNo);
		close(conn);
		
		return p;
	}
	
	public Post2File selectPost2File(int postNo) {
		Connection conn = getConnection();
		
		Post2File pf = new Post2Dao().selectPost2File(conn, postNo);
		close(conn);
		return pf;
	} 
	
	public int updatePost2(Post2 p, Post2File pf) {
		Connection conn = getConnection();
		int result1 = new Post2Dao().updatePost2(conn, p);
		int result2 = 1;
		
		if(pf != null) {
			if(pf.getFileNo() != 0) { // update
				result2 = new Post2Dao().updatePost2File(conn, pf);
			} else { // insert
				result2 = new Post2Dao().insertNewPost2File(conn, pf);
			}
		}
		if(result1>0 && result2>0) {
			commit(conn);
		} else {
			rollback(conn);
		} close(conn);
		return result1*result2;
	}
	
	public int deletePost2(int postNo, int fileNo) {
		Connection conn = getConnection();
		
		int result1 = 1;
		if(fileNo != 0) {
			result1 = new Post2Dao().deletePost2File(conn, fileNo);
		}
		
		int result2 = new Post2Dao().deletePost2(conn, postNo);
		if(result1>0 && result2>0) {
			commit(conn);
		} else {
			rollback(conn);
		} close(conn);
		return result1 * result2;
	}
	
	// 게시글 좋아요 카운트
	public int countLike(Like l) {
		Connection conn = getConnection();
		int result = new Post2Dao().countLike(conn, l);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public Like selectLike(int postNo, int empNo) {
		Connection conn = getConnection();
		Like l = new Post2Dao().selectLike(conn, postNo, empNo);
		close(conn);
		return l;
	}
	
	// 좋아요 클릭
	public int insertLike(Like l) {
		Connection conn = getConnection();
		int result = new Post2Dao().insertLike(conn, l);
		if(result>0) {
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
		int result = new Post2Dao().deleteLike(conn, l);
		if(result>0) {
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
		int result = new Post2Dao().insertReply(conn,r);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		} close(conn);
		return result;
	}
	
	public ArrayList<Reply> selectReplyList(int postNo) {
		Connection conn = getConnection();
		ArrayList<Reply> list = new Post2Dao().selectReplyList(conn, postNo);
		close(conn);
		return list;
	}
	
	public int insertNestedReply(Reply r) {
		Connection conn = getConnection();
		int result = new Post2Dao().insertNestedReply(conn, r);
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
		int result = new Post2Dao().deleteReply(conn, replyNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		close(conn);
		return result;
	} 

}
