package com.kh.approval.model.dao;

import static com.kh.common.JDBCTemplate.close;

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

public class TempApprovalDao {

	private Properties prop = new Properties();

	public TempApprovalDao() {

		String fileName = ApprovalDao.class.getResource("/sql/approval/tempapproval-mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 임시저장 문서함 전체 조회용
	public int selectListCount(Connection conn, int empNo) {

		// SELECT 문 : ResultSet : COUNT 함수 한 행

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

	// 임시저장 문서함 검색용
	public int selectSearchListCount(Connection conn, int empNo, String searchText) {

		// SELECT 문 : ResultSet : COUNT 함수 한 행

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectSearchListCount");

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

	public ArrayList<Approval> selectApprovalList(Connection conn, PageInfo pi, int empNo) {

		// SELECT 문 : ResultSet : ArrayList

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

	public ArrayList<Approval> selectSearchApprovalList(Connection conn, PageInfo pi, int empNo, String searchText) {

		// SELECT 문 : ResultSet : ArrayList

		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectSearchApprovalList");

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

	public Approval selectTempApproval(Connection conn, String aprId, int empNo) {

		// SELECT 문 : ResultSet : Approval 하나 조회

		Approval a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectTempApproval");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, aprId);

			rset = pstmt.executeQuery();

			if (rset.next()) {

				a = new Approval(rset.getString("APR_ID"), rset.getString("APR_TITLE"), rset.getString("APR_CONTENT"),
						rset.getString("WRITE_DATE"), rset.getDate("UPDATE_DATE"),
						String.valueOf(rset.getInt("APR_STATUS")), rset.getString("ORIGIN_NAME"),
						rset.getString("CHANGE_NAME"), rset.getString("EMP_NAME"), rset.getString("DOC_NO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return a;
	}

	public ArrayList<DocSpent> selectTempDocSpent(Connection conn, String aprId) {

		// SELECT 문 : ResultSet : ArrayList

		ArrayList<DocSpent> list = new ArrayList<DocSpent>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectTempDocSpent");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, aprId);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				DocSpent d = new DocSpent();
				d.setDocNo(rset.getInt("DOC_NO"));
				d.setSpentDate(rset.getDate("SPENT_DATE"));
				d.setCorpName(rset.getString("CORP"));
				d.setCorpNo(rset.getString("CORP_NO"));
				d.setHistory(rset.getString("HISTORY"));
				d.setSpentMoney(rset.getInt("SPENT_MONEY"));

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

	public int updateAppDoc(Connection conn, Approval a) {

		// UPDATE 문 : int (처리된 행의 갯수)

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateAppDoc");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getAprTitle());
			pstmt.setString(2, a.getAprContent());
			pstmt.setString(3, a.getOriginName());
			pstmt.setString(4, a.getChangeName());
			pstmt.setString(5, a.getAprComment());
			pstmt.setString(6, a.getAprId());
			pstmt.setInt(7, Integer.parseInt(a.getEmpNo()));

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteDocSpent(Connection conn, DocSpent d) {

		// DELETE 문 : int (처리된 행의 갯수)

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteDocSpent");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, d.getAprId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertDocSpent(Connection conn, DocSpent d) {

		// INSERT 문 : 처리된 행의 개수

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
			pstmt.setString(6, d.getAprId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 임시저장한 문서 다시 임시저장
	public int updateTempAppDoc(Connection conn, Approval a) {

		// UPDATE 문 : int (처리된 행의 갯수)

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateTempAppDoc");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getAprTitle());
			pstmt.setString(2, a.getAprContent());
			pstmt.setString(3, a.getOriginName());
			pstmt.setString(4, a.getChangeName());
			pstmt.setString(5, a.getAprId());
			pstmt.setInt(6, Integer.parseInt(a.getEmpNo()));

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 임시저장한 문서 조회하기
	public Approval selectTempReport(Connection conn, String aprId) {

		Approval a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectTempReport");

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

	// 임시저장한 문서 임시저장하기
	public int updateReportForm(Connection conn, Approval a) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateReportForm");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getAprTitle());
			pstmt.setString(2, a.getAprContent());
			pstmt.setString(3, a.getOriginName());
			pstmt.setString(4, a.getChangeName());
			pstmt.setString(5, a.getAprComment());
			pstmt.setString(6, a.getAprId());
			pstmt.setInt(7, Integer.parseInt(a.getEmpNo()));
			

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 임시저장한 문서 상신(보고서)

	public int insertAprList(Connection conn, ArrayList<Approver> apList, Approval ap) {

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

	// 임시저장한 문서 다시 임시저장
	public int updateTempReport(Connection conn, Approval a) {

		// UPDATE 문 : int (처리된 행의 갯수)

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateTempReport");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getAprTitle());
			pstmt.setString(2, a.getAprContent());
			pstmt.setString(3, a.getOriginName());
			pstmt.setString(4, a.getChangeName());
			pstmt.setString(5, a.getAprId());
			pstmt.setInt(6, Integer.parseInt(a.getEmpNo()));

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

}
