package com.kh.employee.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.dao.MemberDao;
import com.kh.employee.model.vo.Member;

public class MemberService {

//일반사원용 전체 조회 화면 보이기
	public ArrayList<Member> search() {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> list = new ArrayList<>();

		list = new MemberDao().search(conn);

		JDBCTemplate.close(conn);

		return list;

	}
	// 일반사원용 디테일 페이지 화면 보이기

	public Member selectMember(String empNo) {

		Connection conn = JDBCTemplate.getConnection();

		Member emp = new MemberDao().selectMember(empNo, conn);

		JDBCTemplate.close(conn);

		return emp;

	}

	// 사원 상세 검색
	public ArrayList<Member> showList(String option, PageInfo pi, String find) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> optionList = new MemberDao().showList(option, pi, find, conn);

		JDBCTemplate.close(conn);

		return optionList;

	}

	/// 리스트 카운트 구하기 전체 사원의 리스트 (일반용 -퇴사하지 않은 사람만 보기)
	public int selectListCount() {

		Connection conn = JDBCTemplate.getConnection();

		int listCount = new MemberDao().selectListCount(conn);

		JDBCTemplate.close(conn);

		return listCount;
	}

	public ArrayList<Member> selectempList(PageInfo pi) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> empList = new MemberDao().selectempList(conn, pi);

		JDBCTemplate.close(conn);

		return empList;
	}

	// 매니저용 전체 화면 보이기
	public ArrayList<Member> showAllempList() {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> empListall = new MemberDao().showAllempList(conn);

		JDBCTemplate.close(conn);

		return empListall;

	}

	// listCount 구하기
	public int listCountbyOption(String find, String option) {

		Connection conn = JDBCTemplate.getConnection();

		int pageCount = new MemberDao().listCountbyOption(find, option, conn);

		JDBCTemplate.close(conn);

		return pageCount;

	}

	// 사원 추가
	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		// BOARD 테이블 INSERT 요청
		int result = new MemberDao().insertMember(conn, m); // 성공 : 1, 실패 : 0
		if (result > 0) {

			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	// 프로필 사진 추가(일반사용자용)
	public int insertThumbnailmMember(Member m) {

		Connection conn = JDBCTemplate.getConnection();
		// BOARD 테이블 INSERT 요청
		int result = new MemberDao().insertThumbnailmMember(conn, m);

		if (result > 0) {

			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	// 상세 사원검색(관리자용)
	public ArrayList<Member> searchListAdmin(String option, String find) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> optionListAd = new MemberDao().searchListAdmin(option, find, conn);

		JDBCTemplate.close(conn);

		return optionListAd;

	}

	// 사원 정보수정
	public int updateMemberInfo(Member m) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new MemberDao().updateMemberInfo(conn, m); // 성공 : 1, 실패 : 0

		if (result > 0) {

			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;

	}

	// 사번체크
	public int noCheck(int checkEmpNo) {
		Connection conn = JDBCTemplate.getConnection();

		int count = new MemberDao().noCheck(conn, checkEmpNo);

		JDBCTemplate.close(conn);

		return count;

	}

	// 사원 퇴사처리하기
	public int resignMember(Member m) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new MemberDao().resignMember(conn, m); // 성공 : 1, 실패 : 0

		if (result > 0) {

			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;

	}
	// 사번으로 검색

	public int selectListCountbyEmpNo(String option) {

		Connection conn = JDBCTemplate.getConnection();

		int listCount = new MemberDao().selectListCountbyEmpNo(conn, option);

		JDBCTemplate.close(conn);

		return listCount;

	}

	// 이름으로 검색할때 카운트
	public int selectListCountbyEmpName(String option) {

		Connection conn = JDBCTemplate.getConnection();

		int listCount = new MemberDao().selectListCountbyEmpName(conn, option);

		JDBCTemplate.close(conn);

		return listCount;

	}

	// 부서로 검색할때 카운트
	public int selectListCountbyDeptName(String option) {

		Connection conn = JDBCTemplate.getConnection();

		int listCount = new MemberDao().selectListCountbyDeptName(conn, option);

		JDBCTemplate.close(conn);

		return listCount;

	}

	// 직급으로 검색할때 카운트
	public int selectListCountbyJobName(String option) {

		Connection conn = JDBCTemplate.getConnection();

		int listCount = new MemberDao().selectListCountbyJobName(conn, option);

		JDBCTemplate.close(conn);

		return listCount;

	}

}
