package com.kh.approval.model.dao;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.Approver;
import com.kh.approval.model.vo.DocSpent;
import com.kh.approval.model.vo.Referrer;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.vo.Employee;

public class ApprovalDao {

	private Properties prop = new Properties();

	public ApprovalDao() {

		String fileName = ApprovalDao.class.getResource("/sql/approval/approval-mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 결재라인 조직도 불러오는 부분
	public ArrayList<String> selectDeptList(Connection conn) {

		ArrayList<String> deptList = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectDeptList"));
			rset = pstmt.executeQuery();

			while (rset.next()) {
				deptList.add(rset.getString("DEPT_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return deptList;
	}

	// 결재라인 조직도 불러오는 부분
	public ArrayList<Employee> selectEmployeeList(Connection conn) {

		ArrayList<Employee> eList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectEmployeeList"));
			rset = pstmt.executeQuery();

			while (rset.next()) {
				eList.add(new Employee(rset.getInt("EMP_NO"), rset.getString("EMP_NAME"), rset.getString("DEPT_NAME"),
						rset.getString("JOB_NAME")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return eList;
	}

	// 결재라인 조직도 불러오는 부분
	public Employee selectEmployee(Connection conn, int empNo) {

		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectEmployee"));
			pstmt.setInt(1, empNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				emp = new Employee(rset.getInt("EMP_NO"), rset.getString("EMP_NAME"), rset.getString("PROFILE"),
						rset.getString("DEPT_NAME"), rset.getString("JOB_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return emp;
	}

	// 결재라인 부서 검색 부분
	public ArrayList<Employee> searchAprLineEListByDept(Connection conn, String keyword) {

		ArrayList<Employee> eList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("searchAprLineEListByDept"));
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				eList.add(new Employee(rset.getInt("EMP_NO"), rset.getString("EMP_NAME"), rset.getString("DEPT_NAME"),
						rset.getString("JOB_NAME")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return eList;
	}

	// 결재라인 이름 검색 부분
	public ArrayList<Employee> searchAprLineEListByName(Connection conn, String keyword) {

		ArrayList<Employee> eList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("searchAprLineEListByName"));
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				eList.add(new Employee(rset.getInt("EMP_NO"), rset.getString("EMP_NAME"), rset.getString("DEPT_NAME"),
						rset.getString("JOB_NAME")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return eList;
	}

	// 결재자 리스트 추가
	public int insertAprList(Connection conn, ArrayList<Approver> apList) {

		// 결과값을 곱해야 하기 때문에 초기값 1
		int result = 1;
		PreparedStatement pstmt = null;

		try {
			for (Approver a : apList) {
				pstmt = conn.prepareStatement(prop.getProperty("insertAprList"));
				pstmt.setInt(1, a.getEmpNo());
				pstmt.setInt(2, a.getAprOrder());
				result *= pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 임시저장한 문서에서 결재자 리스트 insert
	public int insertAprList(Connection conn, ArrayList<Approver> apList, Approval ap) {

		// 결과값을 곱해야 하기 때문에 초기값 1
		int result = 1;
		PreparedStatement pstmt = null;

		try {

			for (Approver a : apList) {
				pstmt = conn.prepareStatement(prop.getProperty("insertAprListTemp"));
				pstmt.setInt(1, a.getEmpNo());
				pstmt.setInt(2, a.getAprOrder());
				pstmt.setString(3, ap.getAprId());

				result *= pstmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertRefList(Connection conn, ArrayList<Referrer> refList) {

		// 결과값을 곱해야 하기 때문에 초기값 1
		int result = 1;
		PreparedStatement pstmt = null;

		try {
			for (Referrer r : refList) {
				pstmt = conn.prepareStatement(prop.getProperty("insertRefList"));
				pstmt.setInt(1, r.getEmpNo());

				result *= pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 임시저장한 문서에서 참조자 리스트 insert
	public int insertRefList(Connection conn, ArrayList<Referrer> refList, Approval a) {

		// 결과값을 곱해야 하기 때문에 초기값 1
		int result = 1;
		PreparedStatement pstmt = null;

		try {
			for (Referrer r : refList) {
				pstmt = conn.prepareStatement(prop.getProperty("insertRefListTemp"));
				pstmt.setInt(1, r.getEmpNo());
				pstmt.setString(2, a.getAprId());

				result *= pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertAppDoc(Connection conn, Approval a) {

		// INSERT 문 : int 처리된 행의 개수

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAppDoc");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getAprTitle());
			pstmt.setString(2, a.getAprContent());
			pstmt.setString(3, a.getOriginName());
			pstmt.setString(4, a.getChangeName());
			pstmt.setString(5, a.getAprComment());
			pstmt.setInt(6, Integer.parseInt(a.getEmpNo()));

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertDocSpent(Connection conn, DocSpent d) {

		// INSERT 문 : int 처리된 행의 개수

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertDocSpent");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setDate(1, d.getSpentDate());
			pstmt.setString(2, d.getCorpName());
			pstmt.setString(3, d.getCorpNo());
			pstmt.setString(4, d.getHistory());
			pstmt.setInt(5, d.getSpentMoney());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 문서 상세 뷰
	public Approval selectApprovalById(Connection conn, String aprId) {

		Approval a = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectApprovalById"));
			pstmt.setString(1, aprId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				a = new Approval(rs.getString("APR_ID"), rs.getString("APR_TITLE"), rs.getString("APR_CONTENT"),
						rs.getString("ADATE"), rs.getDate("UPDATE_DATE"), rs.getString("APR_STATUS"),
						rs.getString("ORIGIN_NAME"), rs.getString("CHANGE_NAME"), rs.getString("EMP_NO"),
						rs.getString("APR_COMMENT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}
		return a;
	}

	// 결재자 리스트
	public ArrayList<Approver> selectAprListById(Connection conn, String aprId) {

		ArrayList<Approver> al = new ArrayList<Approver>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectAprListById"));
			pstmt.setString(1, aprId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				al.add(new Approver(rs.getInt("EMP_NO"), rs.getInt("APR_ORDER"), rs.getString("ODATE"),
						rs.getString("APRDATE"), rs.getString("ADATE"), rs.getString("APR_YN"),
						rs.getString("APR_COMMENT"), rs.getString("APR_ID"), rs.getString("EMP_NAME"),
						rs.getString("DEPT_NAME"), rs.getString("JOB_NAME"), rs.getString("PROFILE")));

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}
		return al;
	}

	// 참조자 리스트
	public ArrayList<Referrer> selectRefListById(Connection conn, String aprId) {

		ArrayList<Referrer> rl = new ArrayList<Referrer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectRefListById"));
			pstmt.setString(1, aprId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rl.add(new Referrer(rs.getString("ADATE"), rs.getInt("EMP_NO"), rs.getString("APR_ID"),
						rs.getString("EMP_NAME"), rs.getString("DEPT_NAME"), rs.getString("JOB_NAME"),
						rs.getString("PROFILE")));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}
		return rl;
	}

	// 지결 내역 리스트
	public ArrayList<DocSpent> selectDSListById(Connection conn, String aprId) {

		ArrayList<DocSpent> dsl = new ArrayList<DocSpent>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectDSListById"));
			pstmt.setString(1, aprId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dsl.add(new DocSpent(rs.getInt("DOC_NO"), rs.getDate("SPENT_DATE"), rs.getString("CORP"),
						rs.getString("CORP_NO"), rs.getString("HISTORY"), rs.getInt("SPENT_MONEY"),
						rs.getString("APR_ID")));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}
		return dsl;
	}

	// 결재자 열람일자 업데이트
	public int updateAprOpenDate(Connection conn, int empNo, String aprId) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateAprOpenDate"));
			pstmt.setInt(1, empNo);
			pstmt.setString(2, aprId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 참조자 열람일자 업데이트
	public int updateRefViewDate(Connection conn, int empNo, String aprId) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateRefViewDate"));
			pstmt.setInt(1, empNo);
			pstmt.setString(2, aprId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 결재 승인 여부 업데이트
	public int updateAprYN(Connection conn, Approver ap) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateAprYN"));
			pstmt.setString(1, ap.getAprYN());
			pstmt.setString(2, ap.getAprComment());
			pstmt.setInt(3, ap.getEmpNo());
			pstmt.setString(4, ap.getAprId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 열람일자 업데이트한 결재자 열람일자 가져오기
	public String[] selectAppOpenDate(Connection conn, int empNo, String aprId) {

		String[] d = new String[2];
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectAppOpenDate"));
			pstmt.setInt(1, empNo);
			pstmt.setString(2, aprId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				d[0] = rs.getString("ODATE");
				d[1] = rs.getString("APR_YN");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}
		return d;
	}

	// 열람일자 업데이트한 참조자 열람일자 가져오기
	public String selectRefViewDate(Connection conn, int empNo, String aprId) {

		String d = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectRefViewDate"));
			pstmt.setInt(1, empNo);
			pstmt.setString(2, aprId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				d = rs.getString("ODATE");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);
		}
		return d;
	}

	// 기안자 결재자 참조자 직원 리스트 불러오기
	public Employee selectProCommentEmployee(Connection conn, int empNo) {

		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectProCommentEmployee"));
			pstmt.setInt(1, empNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				emp = new Employee(rset.getInt("EMP_NO"), rset.getString("EMP_NAME"), rset.getString("PHONE"),
						rset.getString("PROFILE"), rset.getString("EMAIL"), rset.getString("TEL"),
						rset.getString("NICK_NAME"), rset.getString("GENDER"), rset.getString("DEPT_NAME"),
						rset.getString("JOB_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return emp;
	}

	// 받은 문서함 Dao
	// 받은문서함 리스트 개수 불러오기
	public int totalApprovalReceive(Connection conn, int empNo, int docStmt) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			if (docStmt == 0) {
				pstmt = conn.prepareStatement(prop.getProperty("totalApprovalReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
			} else if (docStmt == 1) { // 미결문서
				pstmt = conn.prepareStatement(prop.getProperty("totalApprovalImNotIngReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
			} else if (docStmt == 2) { // 결재진행중
				pstmt = conn.prepareStatement(prop.getProperty("totalApprovalIngReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setInt(3, empNo);
			} else if (docStmt == 3) { // 반려
				pstmt = conn.prepareStatement(prop.getProperty("totalApprovalRejectReceive"));
				pstmt.setInt(1, empNo);
			} else if (docStmt == 4) { // 완결
				pstmt = conn.prepareStatement(prop.getProperty("totalApprovalCompleteReceive"));
				pstmt.setInt(1, empNo);
			} else if (docStmt == 5) { // 참조
				pstmt = conn.prepareStatement(prop.getProperty("totalApprovalReferReceive"));
				pstmt.setInt(1, empNo);
			}

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	// 받은문서함 검색 리스트 개수 불러오기
	public int totalSearchApprovalReceive(Connection conn, int empNo, int docStmt, String searchText) {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			if (docStmt == 0) {
				pstmt = conn.prepareStatement(prop.getProperty("totalSearchApprovalReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setString(3, searchText);
				pstmt.setString(4, searchText);
			} else if (docStmt == 1) { // 미결문서
				pstmt = conn.prepareStatement(prop.getProperty("totalSearchApprovalImNotIngReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setString(3, searchText);
				pstmt.setString(4, searchText);
			} else if (docStmt == 2) { // 결재진행중
				pstmt = conn.prepareStatement(prop.getProperty("totalSearchApprovalIngReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setInt(3, empNo);
				pstmt.setString(4, searchText);
				pstmt.setString(5, searchText);
			} else if (docStmt == 3) { // 반려
				pstmt = conn.prepareStatement(prop.getProperty("totalSearchApprovalRejectReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);
			} else if (docStmt == 4) { // 완결
				pstmt = conn.prepareStatement(prop.getProperty("totalSearchApprovalCompleteReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);
			} else if (docStmt == 5) { // 참조
				pstmt = conn.prepareStatement(prop.getProperty("totalSearchApprovalReferReceive"));
				pstmt.setInt(1, empNo);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);
			}

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	// 받은 문서함 전체 리스트 불러오기
	public ArrayList<Approval> selectApprovalReceiveList(Connection conn, PageInfo pi, int empNo, int docStmt) {
		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			if (docStmt == 0) {
				pstmt = conn.prepareStatement(prop.getProperty("selectApprovalReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setInt(3, empNo);
				pstmt.setInt(4, empNo);
				pstmt.setInt(5, empNo);
				pstmt.setInt(6, empNo);
				pstmt.setInt(7, startRow);
				pstmt.setInt(8, endRow);
			} else if (docStmt == 1) { // 미결문서
				pstmt = conn.prepareStatement(prop.getProperty("selectApprovalImNotIngReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);

			} else if (docStmt == 2) { // 결재진행중
				pstmt = conn.prepareStatement(prop.getProperty("selectApprovalIngReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setInt(3, empNo);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);

			} else if (docStmt == 3) { // 반려
				pstmt = conn.prepareStatement(prop.getProperty("selectApprovalRejectReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else if (docStmt == 4) { // 완결
				pstmt = conn.prepareStatement(prop.getProperty("selectApprovalCompleteReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);

			} else if (docStmt == 5) { // 참조
				pstmt = conn.prepareStatement(prop.getProperty("selectApprovalReferReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setWriteDate(rset.getString("ADATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setDocType(rset.getString("DOC_NO"));
				a.setAprStatus(rset.getString("STATUS")); // 문서 상태를 상태 컬럼에 넣어서 보내줬습니다. 원래는 임시저장 구분용
				list.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 받은 문서함 검색 전체 리스트
	public ArrayList<Approval> selectSearchApprovalReceiveList(Connection conn, PageInfo pi, int empNo,
			String searchText, int docStmt) {
		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			if (docStmt == 0) {
				pstmt = conn.prepareStatement(prop.getProperty("selectSearchApprovalReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setInt(3, empNo);
				pstmt.setInt(4, empNo);
				pstmt.setInt(5, empNo);
				pstmt.setInt(6, empNo);
				pstmt.setString(7, searchText);
				pstmt.setString(8, searchText);
				pstmt.setInt(9, startRow);
				pstmt.setInt(10, endRow);

			} else if (docStmt == 1) { // 미결문서
				pstmt = conn.prepareStatement(prop.getProperty("selectSearchApprovalImNotIngReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setString(3, searchText);
				pstmt.setString(4, searchText);
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
			} else if (docStmt == 2) { // 결재진행중
				pstmt = conn.prepareStatement(prop.getProperty("selectSearchApprovalIngReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setInt(2, empNo);
				pstmt.setInt(3, empNo);
				pstmt.setString(4, searchText);
				pstmt.setString(5, searchText);
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, endRow);
			} else if (docStmt == 3) { // 반려
				pstmt = conn.prepareStatement(prop.getProperty("selectSearchApprovalRejectReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
			} else if (docStmt == 4) { // 완결
				pstmt = conn.prepareStatement(prop.getProperty("selectSearchApprovalCompleteReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
			} else if (docStmt == 5) { // 참조
				pstmt = conn.prepareStatement(prop.getProperty("selectSearchApprovalReferReceiveList"));
				pstmt.setInt(1, empNo);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
			}

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setWriteDate(rset.getString("ADATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setDocType(rset.getString("DOC_NO"));
				a.setAprStatus(rset.getString("STATUS")); // 문서 상태를 상태 컬럼에 넣어서 보내줬습니다. 원래는 임시저장 구분용

				list.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 전자결재홈 받은문서함 리스트
	public ArrayList<Approval> selectrList(Connection conn, int empNo) {
		ArrayList<Approval> rList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectrList");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, empNo);
			pstmt.setInt(2, empNo);
			pstmt.setInt(3, empNo);
			pstmt.setInt(4, empNo);
			pstmt.setInt(5, empNo);
			pstmt.setInt(6, empNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Approval a = new Approval();
				a.setDocType(rset.getString("DOC_NO"));
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setWriteDate(rset.getString("ADATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setAprStatus(rset.getString("STATUS")); // 문서 상태를 상태 컬럼에 넣어서 보내줬습니다. 원래는 임시저장 구분용

				rList.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return rList;
	}

	// 전자결재홈 상신문서함 리스트
	public ArrayList<Approval> selectsList(Connection conn, int empNo) {
		ArrayList<Approval> sList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectsList");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, empNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				Approval a = new Approval();
				a.setDocType(rset.getString("DOC_NO"));
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setAprStatus(String.valueOf(rset.getInt("APR_YN")));

				sList.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return sList;
	}

	// 임시저장
	public int insertTempAppDoc(Connection conn, Approval a) {

		// INSERT 문 : int 처리된 행의 개수

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertTempAppDoc");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getAprTitle());
			pstmt.setString(2, a.getAprContent());
			pstmt.setString(3, a.getOriginName());
			pstmt.setString(4, a.getChangeName());
			pstmt.setInt(5, Integer.parseInt(a.getEmpNo()));

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 임시저장
	public int insertTempDocSpent(Connection conn, DocSpent d) {

		// INSERT 문 : int 처리된 행의 개수

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertTempDocSpent");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setDate(1, d.getSpentDate());
			pstmt.setString(2, d.getCorpName());
			pstmt.setString(3, d.getCorpNo());
			pstmt.setString(4, d.getHistory());
			pstmt.setInt(5, d.getSpentMoney());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int selectListCount(Connection conn, int empNo) {

		// SELECT 문 : Count() 함수 사용 : 한 행으로 나올 예정

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, empNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt("COUNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public int selectListCount(Connection conn, int empNo, int docStmt) {

		// SELECT 문 : ResultSet : COUNT 함수 한 행으로 나올 예정

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListCountNotNull");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, empNo);
			pstmt.setInt(2, docStmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt("COUNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public int selectListSearchCount(Connection conn, int empNo, int docStmt, String searchText) {

		// SELECT 문 : rset 1줄

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListSearchCountNotNull");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, empNo);
			pstmt.setInt(2, docStmt);
			pstmt.setString(3, searchText);
			pstmt.setString(4, searchText);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt("COUNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public int selectListSearchCount(Connection conn, int empNo, String searchText) {

		// SELECT 문 : rset 1줄

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListSearchCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, empNo);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt("COUNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	// 문서함 전체 조회용 서비스 - 문서 상태 선택
	public ArrayList<Approval> selectApprovalList(Connection conn, PageInfo pi, int empNo, int docStmt) {

		// SELECT 문 : ResultSet : ArrayList

		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectApprovalListNotNull");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, empNo);
			pstmt.setInt(2, docStmt);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setAprStatus(String.valueOf(rset.getInt("APR_YN")));
				a.setDocType(rset.getString("DOC_NO"));

				list.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	// 문서함 전체 조회용 서비스
	public ArrayList<Approval> selectApprovalList(Connection conn, PageInfo pi, int empNo) {

		// SELECT 문 : ResultSet => ArrayList

		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectApprovalList");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, empNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setAprStatus(String.valueOf(rset.getInt("APR_YN")));
				a.setDocType(rset.getString("DOC_NO"));

				list.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	// 문서함 전체 조회용 서비스 - 검색, 문서 상태 선택 x
	public ArrayList<Approval> selectApprovalSearchList(Connection conn, PageInfo pi, int empNo, String searchText) {

		// SELECT 문 : ResultSet : ArrayList

		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectApprovalSearchList");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, empNo);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setAprStatus(String.valueOf(rset.getInt("APR_YN")));
				a.setDocType(rset.getString("DOC_NO"));

				list.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	// 문서함 전체 조회용 서비스 - 검색, 문서 상태 선택 x
	public ArrayList<Approval> selectApprovalSearchList(Connection conn, PageInfo pi, int empNo, String searchText,
			int docStmt) {

		// SELECT 문 : ResultSet : ArrayList

		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectApprovalSearchListNotNull");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setInt(1, empNo);
			pstmt.setInt(2, docStmt);
			pstmt.setString(3, searchText);
			pstmt.setString(4, searchText);
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setAprStatus(String.valueOf(rset.getInt("APR_YN")));
				a.setDocType(rset.getString("DOC_NO"));

				list.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	// 문서 상세조회용
	public Approval selectApproval(Connection conn, String aprId, int empNo) {

		// SELECT 문 : ResultSet : Approval

		Approval a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectApproval");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, aprId);

			rset = pstmt.executeQuery();

			if (rset.next()) {

				a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setAprContent(rset.getString("APR_CONTENT"));
				a.setWriteDate(rset.getString("ADATE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setOriginName(rset.getString("ORIGIN_NAME"));
				a.setChangeName(rset.getString("CHANGE_NAME"));
				a.setEmpNo(rset.getString("EMP_NAME"));
				a.setAprComment(rset.getString("APR_COMMENT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return a;
	}

	public ArrayList<DocSpent> selectDocSpentList(Connection conn, String aprId) {

		// SELECT 문 : ResultSet : ArrayList

		ArrayList<DocSpent> list = new ArrayList<DocSpent>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectDocSpentList");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, aprId);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				DocSpent d = new DocSpent(rset.getInt("DOC_NO"), rset.getDate("SPENT_DATE"), rset.getString("CORP"),
						rset.getString("CORP_NO"), rset.getString("HISTORY"), rset.getInt("SPENT_MONEY"),
						rset.getString("APR_ID"));

				list.add(d);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	// 보고서 상신
	public int sendReport(Connection conn, Approval ap) {

		int result = 0;
		PreparedStatement pstmt = null;

		// Insert문
		String sql = prop.getProperty("sendReport");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ap.getAprTitle());
			pstmt.setString(2, ap.getAprContent());
			pstmt.setInt(3, 1);
			pstmt.setString(4, ap.getOriginName());
			pstmt.setString(5, ap.getChangeName());
			pstmt.setInt(6, Integer.parseInt(ap.getEmpNo()));
			pstmt.setString(7, ap.getAprComment());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(pstmt);
		}
		return result;
	}

	// 보고서 임시저장
	public int sendReportTemp(Connection conn, Approval ap) {

		int result = 0;
		PreparedStatement pstmt = null;

		// Insert문
		String sql = prop.getProperty("sendReportTemp");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ap.getAprTitle());
			pstmt.setString(2, ap.getAprContent());
			pstmt.setInt(3, 0);
			pstmt.setString(4, ap.getOriginName());
			pstmt.setString(5, ap.getChangeName());
			pstmt.setInt(6, Integer.parseInt(ap.getEmpNo()));
			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(pstmt);
		}
		return result;
	}

	public Approval selectEachReport(Connection conn, String aprId) {
		Approval a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectEachReport");

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aprId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				a = new Approval();

				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setAprContent(rset.getString("APR_CONTENT"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setOriginName(rset.getString("ORIGIN_NAME"));
				a.setChangeName(rset.getString("CHANGE_NAME"));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return a;
	}

}
