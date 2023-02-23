package com.kh.post.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.post.model.dao.ShareDao;
import com.kh.post.model.vo.Like;
import com.kh.post.model.vo.Reply;
import com.kh.post.model.vo.Share;
import com.kh.post.model.vo.ShareAttachment;


public class ShareService {
	
	public ArrayList<Share> selectShareList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Share> list = new ShareDao().selectShareList(conn, pi);
		
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<Share> selectShareList(PageInfo pi, String searchField, String searchText) {
		
		Connection conn = getConnection();
		
		ArrayList<Share> list = new ShareDao().selectShareList(conn, pi, searchField, searchText);
		
		close(conn);
		
		return list;
		
	}
	
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new ShareDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
		
		
	}
	
	public int searchListCount(String searchField, String searchText) {
		
		Connection conn = getConnection();
		
		int listCount = new ShareDao().searchListCount(conn, searchField, searchText);
		
		close(conn);
		
		return listCount;
		
	}
	
	public int insertSharePost(Share s, ArrayList<ShareAttachment> list) {
		
		Connection conn = getConnection();
		
		int result1 = new ShareDao().insertSharePost(conn, s);
		
		int result2 = new ShareDao().insertShareAttachmentList(conn, list);
		
		if(result1 >0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1*result2;
	}
	
	public int increaseCount(int postNo) {
		
		Connection conn = getConnection();
		
		int result = new ShareDao().increaseCount(conn, postNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}
	
	public Share selectPost(int postNo) {
		
		Connection conn = getConnection();
		
		Share s = new ShareDao().selectPost(conn, postNo);
		
		close(conn);
		
		return s;
		
	}
	
	public ArrayList<ShareAttachment> selectShareAttachmentList(int postNo) {
		
		Connection conn = getConnection();
		
		ArrayList<ShareAttachment> list = new ShareDao().selectShareAttachmentList(conn, postNo);
		
		close(conn);
		
		return list;
		
	}
	
	public int insertReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new ShareDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<Reply> selectReplyList(int postNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new ShareDao().selectReplyList(conn, postNo);
		
		close(conn);
		
		return list;
		
	}
	
	
	public int updateSharePost(Share s, ArrayList<ShareAttachment> updateList, ArrayList<ShareAttachment> insertList) {
		
		Connection conn = getConnection();
		
		int result1 = new ShareDao().updatePost(conn, s);
		
		int result2= new ShareDao().updateShareAttachment(conn, updateList);
		
		int result3 = new ShareDao().insertNewShareAttachment(conn, insertList);
		
		if(result1 > 0 && result2 > 0 && result3 > 0) {
			
			commit(conn);
		} else {
			
			rollback(conn);
		}
		close(conn);
		
		return result1*result2*result3;
	}
	
	public int deleteShare(int postNo) {
		
		Connection conn = getConnection();
		
		int result = new ShareDao().deleteShare(conn, postNo);
		
		if(result>0) {
			
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
//	==========================================================
//	댓글, 좋아요
	
	public int deleteReply(int replyNo) {
		Connection conn = getConnection();
		
		int result = new ShareDao().deleteReply(conn, replyNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}
	
	public int insertNestedReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new ShareDao().insertNestedReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	public int insertLike(Like l) {
		
		Connection conn = getConnection();
		
		int result = new ShareDao().insertLike(conn, l);
		
		if(result > 0) {
			
			commit(conn);
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int deleteLike(Like l) {
		
		Connection conn = getConnection();
		
		int result = new ShareDao().deleteLike(conn, l);
		
		if(result > 0) {
			
			commit(conn);
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int countLike(Like l) {
		
		Connection conn = getConnection();
		
		int result = new ShareDao().countLike(conn, l);
		
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
		
		Like l = new ShareDao().selectLike(conn, postNo, empNo);
		
		close(conn);
		
		return l;
	}

}
