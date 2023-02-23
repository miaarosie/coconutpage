package com.kh.approval.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.approval.model.dao.ApprovalDao;
import com.kh.approval.model.dao.TempApprovalDao;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.Approver;
import com.kh.approval.model.vo.DocSpent;
import com.kh.approval.model.vo.Referrer;
import com.kh.common.model.vo.PageInfo;

public class TempApprovalService {

	// 임시저장 문서함 전체 조회용
	public int selectListCount(int empNo) {

		Connection conn = getConnection();

		int listCount = new TempApprovalDao().selectListCount(conn, empNo);

		close(conn);

		return listCount;
	}

	// 임시저장문서함 검색용
	public int selectSearchListCount(int empNo, String searchText) {

		Connection conn = getConnection();

		int listCount = new TempApprovalDao().selectSearchListCount(conn, empNo, searchText);

		close(conn);

		return listCount;
	}

	// 리스트 전체 조회
	public ArrayList<Approval> selectApprovalList(PageInfo pi, int empNo) {

		Connection conn = getConnection();

		ArrayList<Approval> list = new TempApprovalDao().selectApprovalList(conn, pi, empNo);

		close(conn);

		return list;
	}

	// 검색리스트 전체 조회
	public ArrayList<Approval> selectSearchApprovalList(PageInfo pi, int empNo, String searchText) {

		Connection conn = getConnection();

		ArrayList<Approval> list = new TempApprovalDao().selectSearchApprovalList(conn, pi, empNo, searchText);

		close(conn);

		return list;
	}

	// 임시저장 문서 상세조회
	public Approval selectTempApproval(String aprId, int empNo) {

		Connection conn = getConnection();

		Approval a = new TempApprovalDao().selectTempApproval(conn, aprId, empNo);

		close(conn);

		return a;
	}

	// 임시저장 문서 지출내역 상세조회
	public ArrayList<DocSpent> selectTempDocSpent(String aprId) {

		Connection conn = getConnection();

		ArrayList<DocSpent> list = new TempApprovalDao().selectTempDocSpent(conn, aprId);

		close(conn);

		return list;
	}

	// ----- 지출결의서 UPDATE 서비스 -----
	public int updateAppDoc(Approval a, ArrayList<DocSpent> dclist, int currentList, ArrayList<Approver> apList,
			ArrayList<Referrer> refList) {

		Connection conn = getConnection();

		// UPDATE 문
		int result1 = new TempApprovalDao().updateAppDoc(conn, a);

		// DELETE 문
		int result2 = 1;

		for (int i = 0; i < currentList; i++) {
			result2 *= new TempApprovalDao().deleteDocSpent(conn, dclist.get(i));
		}

		// INSERT 문
		int result3 = 1;

		for (DocSpent d : dclist) {
			result3 *= new TempApprovalDao().insertDocSpent(conn, d);
		}

		// 결재 및 참조 리스트 추가 참조자는 있을수도 없을수도 있다.
		int result4 = new ApprovalDao().insertAprList(conn, apList, a);

		int result5 = 1;

		// 참조 리스트 추가는 size 가 > 0 클때만 실행
		if (refList.size() > 0) {
			result5 = new ApprovalDao().insertRefList(conn, refList, a);
		}

		// 트랜잭션 처리
		if (result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result1 * result2 * result3 * result4 * result5;
	}

	// 임시저장한 문서 다시 임시저장
	public int updateTempAppDoc(Approval a, ArrayList<DocSpent> dclist, int currentList) {

		Connection conn = getConnection();

		// UPDATE 문
		int result1 = new TempApprovalDao().updateTempAppDoc(conn, a);

		// DELETE 문
		int result2 = 1;

		for (int i = 0; i <= currentList; i++) {
			result2 *= new TempApprovalDao().deleteDocSpent(conn, dclist.get(i));
		}

		// INSERT 문
		int result3 = 1;

		for (DocSpent d : dclist) {
			result3 *= new TempApprovalDao().insertDocSpent(conn, d);
		}

		// 트랜잭션 처리
		if (result1 > 0 && result2 > 0 && result3 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result1 * result2 * result3;
	}

	// 임시저장한 문서 불러오기
	public Approval selectTempReport(String aprId) {

		Connection conn = getConnection();

		Approval a = new TempApprovalDao().selectTempReport(conn, aprId);

		close(conn);

		return a;

	}

	// 임시저장한 문서 다시 상신
	public int updateReportForm(Approval ap, ArrayList<Approver> apList, ArrayList<Referrer> refList) {

		Connection conn = getConnection();

		int result1 = new TempApprovalDao().updateReportForm(conn, ap);
		// 결재 및 참조 리스트 추가 , 참조자는 있을 수도 없을 수도 있다.

		int result2 = new TempApprovalDao().insertAprList(conn, apList, ap);

		int result3 = 1;

		if (refList.size() > 0) {

			result3 = new TempApprovalDao().insertRefList(conn, refList, ap);
		}

		if (result1 > 0 && result2 > 0 && result3 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result1 * result2 * result3;

	}

	// 임시저장한 문서 다시 임시저장
	public int updateTempReport(Approval a) {

		Connection conn = getConnection();

		// UPDATE 문
		int result1 = new TempApprovalDao().updateTempReport(conn, a);

		// 트랜잭션 처리
		if (result1 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result1;
	}

}
