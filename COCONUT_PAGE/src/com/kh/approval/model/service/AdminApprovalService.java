package com.kh.approval.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.approval.model.dao.AdminApprovalDao;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.DocSpent;
import com.kh.common.model.vo.PageInfo;

import static com.kh.common.JDBCTemplate.*;

public class AdminApprovalService {
	
	// 문서 개수 (검색 x)
	public int selectListCount(int docStmt) {
		
		Connection conn = getConnection();
		
		int listCount = 0;
		
		if (docStmt == 5) {
			listCount = new AdminApprovalDao().selectAllListCount(conn);
		} else if (docStmt == 2) {
			listCount = new AdminApprovalDao().selectIngListCount(conn);
		} else {
			listCount = new AdminApprovalDao().selectDoneListCount(conn);
		}
		
		close(conn);
		
		return listCount;
	}
	
	// 문서 개수 + 검색
	public int selectSearchListCount(int docStmt, String searchText) {
		
		Connection conn = getConnection();
		
		int listCount = 0;
		
		if (docStmt == 5) {
			listCount = new AdminApprovalDao().selectSearchListCount(conn, searchText);
		} else if (docStmt == 2) {
			listCount = new AdminApprovalDao().selectSearchIngListCount(conn, searchText);
		} else {
			listCount = new AdminApprovalDao().selectSearchDoneListCount(conn, searchText);
		}
		
		close(conn);
		
		return listCount;
	}
	
	// 문서 리스트 (검색 x)
	public ArrayList<Approval> selectApprovalList(PageInfo pi, int docStmt) {
		
		Connection conn = getConnection();
		
		ArrayList<Approval> list = null;
		
		if (docStmt == 5) {
			list = new AdminApprovalDao().selectAllApprovalList(conn, pi);
		} else if (docStmt == 2) {
			list = new AdminApprovalDao().selectIngApprovalList(conn, pi);
		} else {
			list = new AdminApprovalDao().selectDoneApprovalList(conn, pi);
		}
		
		close(conn);
		
		return list;
	}
	
	// 문서 리스트 + 검색
	public ArrayList<Approval> selectSearchApprovalList(PageInfo pi, int docStmt, String searchText) {
		
		Connection conn = getConnection();
		
		ArrayList<Approval> list = null;
		
		if (docStmt == 5) {
			list = new AdminApprovalDao().selectSearchAllApprovalList(conn, pi, searchText);
		} else if (docStmt == 2) {
			list = new AdminApprovalDao().selectSearchIngApprovalList(conn, pi, searchText);
		} else {
			list = new AdminApprovalDao().selectSearchDoneApprovalList(conn, pi, searchText);
		}
		
		close(conn);
		
		return list;
	}

	// 문서 상세조회
	public Approval selectApproval(String aprId) {
		
		Connection conn = getConnection();
		
		Approval a = new AdminApprovalDao().selectApproval(conn, aprId);
		
		close(conn);
		
		return a;
	}
	
	// 지결내역 상세조회
	public ArrayList<DocSpent> selectDocSpentList(String aprId) {
		
		Connection conn = getConnection();
		
		ArrayList<DocSpent> list = new AdminApprovalDao().selectDocSpentList(conn, aprId);
		
		close(conn);
		
		return list;
	}
}
