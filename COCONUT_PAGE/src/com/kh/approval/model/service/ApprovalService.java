package com.kh.approval.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.approval.model.dao.ApprovalDao;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.Approver;
import com.kh.approval.model.vo.DocSpent;
import com.kh.approval.model.vo.Referrer;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.vo.Employee;

public class ApprovalService {

	// 결재라인 조직도 불러오는 부분
	public ArrayList<String> selectDeptList() {

		Connection conn = getConnection();

		ArrayList<String> deptList = new ApprovalDao().selectDeptList(conn);

		close(conn);

		return deptList;
	}

	// 결재라인 조직도 불러오는 부분
	public ArrayList<Employee> selectEmployeeList() {

		Connection conn = getConnection();

		ArrayList<Employee> eList = new ApprovalDao().selectEmployeeList(conn);

		close(conn);

		return eList;
	}

	public Employee selectEmployee(int empNo) {

		Connection conn = getConnection();

		Employee emp = new ApprovalDao().selectEmployee(conn, empNo);

		close(conn);

		return emp;
	}

	// 결재라인 부서 검색 부분
	public ArrayList<Employee> searchAprLineEListByDept(String keyword) {

		Connection conn = getConnection();

		ArrayList<Employee> eList = new ApprovalDao().searchAprLineEListByDept(conn, keyword);

		close(conn);

		return eList;
	}

	// 결재라인 이름 검색 부분
	public ArrayList<Employee> searchAprLineEListByName(String keyword) {

		Connection conn = getConnection();

		ArrayList<Employee> eList = new ApprovalDao().searchAprLineEListByName(conn, keyword);

		close(conn);

		return eList;
	}

	// 결재라인 추가
	public int insertAprLine(ArrayList<Approver> apList, ArrayList<Referrer> refList) {

		Connection conn = getConnection();
		int result2 = 1;

		// 결재자 리스트를 추가하는 구문과 refList를 추가하는 구문 그리고 기안작성을 호출하는 Dao
		// 3개 모두 결과 1이여야 반환한다.
		int result1 = new ApprovalDao().insertAprList(conn, apList);
		// 참조 리스트 추가는 size 가 > 0 클때만 실행
		if (refList.size() > 0) {
			result2 = new ApprovalDao().insertRefList(conn, refList);
		}
		int result3 = 1; // 기안 작성 Dao 성공했다 치자

		if (result1 > 0 && result2 > 0 && result3 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result1 * result2 * result3;
	}

	// 지출결의서 insert 요청 서비스
	public int insertAppDoc(Approval a, ArrayList<DocSpent> dclist, ArrayList<Approver> apList,
			ArrayList<Referrer> refList) {

		Connection conn = getConnection();

		// APR_DOC 에 insert
		int result1 = new ApprovalDao().insertAppDoc(conn, a);

		// DOC_SPENT 에 insert
		int result2 = 1;

		for (int i = 0; i < dclist.size(); i++) {
			int result = new ApprovalDao().insertDocSpent(conn, dclist.get(i));

			result2 *= result; // 0 나오면 실패
		}

		// 결재 및 참조 리스트 추가 참조자는 있을수도 없을수도 있다.
		int result3 = new ApprovalDao().insertAprList(conn, apList);
		int result4 = 1;

		// 참조 리스트 추가는 size 가 > 0 클때만 실행
		if (refList.size() > 0) {
			result4 = new ApprovalDao().insertRefList(conn, refList);
		}

		// 트랜잭션 처리
		if (result1 == 1 && result2 == 1 && result3 == 1 && result4 == 1) {
			commit(conn);
		} else {
			rollback(conn);
		}
		// conn 반납
		close(conn);

		return result1 * result2 * result3 * result4;
	}

	// 문서 상세 뷰
	public Approval selectApprovalById(String aprId) {

		Connection conn = getConnection();

		Approval a = new ApprovalDao().selectApprovalById(conn, aprId);

		close(conn);

		return a;
	}

	// 결재자 리스트 불러옴
	public ArrayList<Approver> selectAprListById(String aprId) {

		Connection conn = getConnection();

		ArrayList<Approver> al = new ApprovalDao().selectAprListById(conn, aprId);

		close(conn);

		return al;

	}

	// 참조자 리스트 불러옴
	public ArrayList<Referrer> selectRefListById(String aprId) {

		Connection conn = getConnection();

		ArrayList<Referrer> rl = new ApprovalDao().selectRefListById(conn, aprId);

		close(conn);

		return rl;

	}

	// 지결 내역 리스트 불러옴
	public ArrayList<DocSpent> selectDSListById(String aprId) {

		Connection conn = getConnection();

		ArrayList<DocSpent> dsl = new ApprovalDao().selectDSListById(conn, aprId);

		close(conn);

		return dsl;

	}

	// 결재자 열람일자 업데이트
	public void updateAprOpenDate(int empNo, String aprId) {

		Connection conn = getConnection();

		int result = new ApprovalDao().updateAprOpenDate(conn, empNo, aprId);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

	}

	// 참조자 열람일자 업데이트
	public void updateRefViewDate(int empNo, String aprId) {

		Connection conn = getConnection();

		int result = new ApprovalDao().updateRefViewDate(conn, empNo, aprId);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	}

	// 결재 승인여부 업데이트
	public int updateAprYN(Approver ap) {

		Connection conn = getConnection();

		int result = new ApprovalDao().updateAprYN(conn, ap);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	// 열람일자 업데이트한 결재자 열람일자 가져오기
	public String[] selectAppOpenDate(int empNo, String aprId) {

		Connection conn = getConnection();

		String[] d = new ApprovalDao().selectAppOpenDate(conn, empNo, aprId);

		return d;

	}

	// 열람일자 업데이트한 참조자 열람일자 가져오기
	public String selectRefViewDate(int empNo, String aprId) {

		Connection conn = getConnection();

		String d = new ApprovalDao().selectRefViewDate(conn, empNo, aprId);

		return d;

	}

	// 기안자 결재자 참조자 직원 리스트 불러오기
	public Employee selectProCommentEmployee(int empNo) {

		Connection conn = getConnection();

		Employee emp = new ApprovalDao().selectProCommentEmployee(conn, empNo);

		close(conn);

		return emp;
	}

	// 받은문서함 리스트 개수 불러오기
	public int totalApprovalReceive(int empNo, int docStmt) {

		Connection conn = getConnection();

		int result = new ApprovalDao().totalApprovalReceive(conn, empNo, docStmt);

		close(conn);

		return result;
	}

	// 받은문서함 검색 리스트 개수 불러오기
	public int totalSearchApprovalReceive(int empNo, int docStmt, String searchText) {

		Connection conn = getConnection();

		int result = new ApprovalDao().totalSearchApprovalReceive(conn, empNo, docStmt, searchText);

		close(conn);

		return result;
	}

	// 받은 문서함 전체 리스트-
	public ArrayList<Approval> selectApprovalReceiveList(PageInfo pi, int empNo, int docStmt) {

		Connection conn = getConnection();

		ArrayList<Approval> list = new ApprovalDao().selectApprovalReceiveList(conn, pi, empNo, docStmt);

		close(conn);

		return list;
	}

	// 받은 문서함 검색 전체 리스트-
	public ArrayList<Approval> selectSearchApprovalReceiveList(PageInfo pi, int empNo, String searchText, int docStmt) {

		Connection conn = getConnection();

		ArrayList<Approval> list = new ApprovalDao().selectSearchApprovalReceiveList(conn, pi, empNo, searchText,
				docStmt);

		close(conn);

		return list;
	}

	// 전자결재홈 받은문서함 리스트
	public ArrayList<Approval> selectrList(int empNo) {
		Connection conn = getConnection();
		ArrayList<Approval> rList = new ApprovalDao().selectrList(conn, empNo);
		close(conn);
		return rList;
	}

	// 전자결재홈 상신문서함 리스트
	public ArrayList<Approval> selelctsList(int empNo) {
		Connection conn = getConnection();
		ArrayList<Approval> sList = new ApprovalDao().selectsList(conn, empNo);
		close(conn);
		return sList;
	}

	// 임시저장
	public int insertTempAppDoc(Approval a, ArrayList<DocSpent> dclist, ArrayList<Approver> apList,
			ArrayList<Referrer> refList) {

		Connection conn = getConnection();

		// APR_DOC 에 insert
		int result1 = new ApprovalDao().insertTempAppDoc(conn, a);

		// DOC_SPENT 에 insert
		int result2 = 1;

		for (int i = 0; i < dclist.size(); i++) {
			int result = new ApprovalDao().insertTempDocSpent(conn, dclist.get(i));

			result2 *= result; // 0 나오면 실패
		}

		// 결재 및 참조 리스트 추가 참조자는 있을수도 없을수도 있다.
		int result3 = new ApprovalDao().insertAprList(conn, apList);
		int result4 = 1;

		// 참조 리스트 추가는 size 가 > 0 클때만 실행
		if (refList.size() > 0) {
			result4 = new ApprovalDao().insertRefList(conn, refList);
		}

		// 트랜잭션 처리
		if (result1 > 0 && result2 > 0 && result3 == 1 && result4 == 1) {
			commit(conn);
		} else {
			rollback(conn);
		}

		// conn 반납
		close(conn);

		return result1 * result2 * result3 * result4;
	}

	// 임시저장
	public int insertTempAppDoc(Approval a, ArrayList<DocSpent> dclist) {

		Connection conn = getConnection();

		// APR_DOC 에 insert
		int result1 = new ApprovalDao().insertTempAppDoc(conn, a);

		// DOC_SPENT 에 insert
		int result2 = 1;

		for (int i = 0; i < dclist.size(); i++) {
			int result = new ApprovalDao().insertTempDocSpent(conn, dclist.get(i));

			result2 *= result; // 0 나오면 실패
		}

		// 트랜잭션 처리
		if (result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		// conn 반납
		close(conn);

		return result1 * result2;
	}

	// 검색어가 없을 때 - 문서 상태 선택 x 일 때
	public int selectListCount(int empNo) {

		Connection conn = getConnection();

		int listCount = new ApprovalDao().selectListCount(conn, empNo);

		close(conn);

		return listCount;
	}

	// 검색어가 없을 때 - 문서 상태가 1~4일 때
	public int selectListCount(int empNo, int docStmt) {

		Connection conn = getConnection();

		int listCount = new ApprovalDao().selectListCount(conn, empNo, docStmt);

		close(conn);

		return listCount;
	}

	// 검색어가 있을 때 - 문서 상태가 1~ 4일 때
	public int selectListSearchCount(int empNo, int docStmt, String searchText) {

		Connection conn = getConnection();

		int listCount = new ApprovalDao().selectListSearchCount(conn, empNo, docStmt, searchText);

		close(conn);

		return listCount;
	}

	// 검색어가 있을 때 - 문서 상태 선택 x 일 때
	public int selectListSearchCount(int empNo, String searchText) {

		Connection conn = getConnection();

		int listCount = new ApprovalDao().selectListSearchCount(conn, empNo, searchText);

		close(conn);

		return listCount;
	}

	// 문서함 전체 조회용 서비스 - 문서상태 선택 x
	public ArrayList<Approval> selectApprovalList(PageInfo pi, int empNo) {

		Connection conn = getConnection();

		ArrayList<Approval> list = new ApprovalDao().selectApprovalList(conn, pi, empNo);

		close(conn);

		return list;
	}

	// 문서함 전체 조회용 서비스 - 문서 상태 선택
	public ArrayList<Approval> selectApprovalList(PageInfo pi, int empNo, int docStmt) {

		Connection conn = getConnection();

		ArrayList<Approval> list = new ApprovalDao().selectApprovalList(conn, pi, empNo, docStmt);

		close(conn);

		return list;
	}

	// 문서함 전체 조회용 서비스 - 검색, 문서 상태 선택 x
	public ArrayList<Approval> selectApprovalSearchList(PageInfo pi, int empNo, String searchText) {

		Connection conn = getConnection();

		ArrayList<Approval> list = new ApprovalDao().selectApprovalSearchList(conn, pi, empNo, searchText);

		close(conn);

		return list;
	}

	// 문서함 전체 조회용 서비스 - 검색, 문서 상태 선택 x
	public ArrayList<Approval> selectApprovalSearchList(PageInfo pi, int empNo, String searchText, int docStmt) {

		Connection conn = getConnection();

		ArrayList<Approval> list = new ApprovalDao().selectApprovalSearchList(conn, pi, empNo, searchText, docStmt);

		close(conn);

		return list;
	}

	// 문서 상세조회
	public Approval selectApproval(String aprId, int empNo) {

		Connection conn = getConnection();

		Approval a = new ApprovalDao().selectApproval(conn, aprId, empNo);

		close(conn);

		return a;
	}

	public ArrayList<DocSpent> selectDocSpentList(String aprId) {

		Connection conn = getConnection();

		ArrayList<DocSpent> list = new ApprovalDao().selectDocSpentList(conn, aprId);

		close(conn);

		return list;
	}

	// 결재상신 (보고서)
	public int sendReport(Approval ap, ArrayList<Approver> apList, ArrayList<Referrer> refList) {

		Connection conn = getConnection();

		int result = new ApprovalDao().sendReport(conn, ap);

		// 결재 및 참조 리스트 추가 참조자는 있을수도 없을수도 있다.
		int result3 = new ApprovalDao().insertAprList(conn, apList);
		int result4 = 1;

		// 참조 리스트 추가는 size 가 > 0 클때만 실행
		if (refList.size() > 0) {
			result4 = new ApprovalDao().insertRefList(conn, refList);
		}

		// 트랜잭션 처리
		if (result == 1 && result3 == 1 && result4 == 1) {
			commit(conn);
		} else {
			rollback(conn);
		}

		// conn 반납
		close(conn);

		return result * result3 * result4;
	}

	// 결재상신(보고서)_임시저장
	public int sendReportTemp(Approval ap) {

		Connection conn = getConnection();

		int result = new ApprovalDao().sendReportTemp(conn, ap);

		// 결재 및 참조 리스트 추가 참조자는 있을수도 없을수도 있다.

		// 참조 리스트 추가는 size 가 > 0 클때만 실행

		// 트랜잭션 처리
		if (result == 1) {
			commit(conn);
		} else {
			rollback(conn);
		}

		// conn 반납
		close(conn);

		return result;

	}

	// 전자결재 문서 조회
	public Approval selectEachReport(String aprId) {

		Connection conn = getConnection();

		Approval a = new ApprovalDao().selectEachReport(conn, aprId);

		close(conn);

		return a;
	}

}
