package com.kh.approval.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.DocSpent;
import com.kh.common.model.vo.PageInfo;

import static com.kh.common.JDBCTemplate.*;

public class AdminApprovalDao {

	private Properties prop = new Properties();
	
	public AdminApprovalDao() {
		
		String fileName = AdminApprovalDao.class.getResource("/sql/approval/adminapproval-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 전체 문서 개수
	public int selectAllListCount(Connection conn) {
		
		// SELECT 문 (COUNT 그룹 함수) int로 조회
		 
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAllListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
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
	
	// 결재진행중 문서 개수
	public int selectIngListCount(Connection conn) {
		
		// SELECT 문 (COUNT 그룹 함수) int로 조회
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectIngListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
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
	
	// 결재 완료 문서 개수
	public int selectDoneListCount(Connection conn) {
		
		// SELECT 문 (COUNT 그룹 함수) int로 조회
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDoneListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
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
	
	// 검색 + 전체 문서 개수
	public int selectSearchListCount(Connection conn, String searchText) {
		
		// SELECT 문 (COUNT 그룹 함수) int로 조회
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			
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
	
	// 검색 + 결재진행중 문서 개수
	public int selectSearchIngListCount(Connection conn, String searchText) {
		
		// SELECT 문 (COUNT 그룹 함수) int로 조회
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchIngListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			
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
	
	// 검색 + 결재 완료 문서 개수
	public int selectSearchDoneListCount(Connection conn, String searchText) {
		
		// SELECT 문 (COUNT 그룹 함수) int로 조회
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchDoneListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			
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
	
	// 전체 문서 리스트
	public ArrayList<Approval> selectAllApprovalList(Connection conn, PageInfo pi) {
		
		// SELECT 문 : ResultSet : ArrayList
		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAllApprovalList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
								
				int aprYN = rset.getInt("APR_YN");
				int aprOrder = rset.getInt("APR_ORDER");
				
				if (aprYN == 4 && aprOrder == 4) {
					a.setAprStatus("결재완료");
				} else {
					a.setAprStatus("결재진행중");
				}
				
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
	
	// 결재진행중 문서 리스트
	public ArrayList<Approval> selectIngApprovalList(Connection conn, PageInfo pi) {
		
		// SELECT 문 : ResultSet : ArrayList
		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectIngApprovalList");

		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
								
				int aprYN = rset.getInt("APR_YN");
				int aprOrder = rset.getInt("APR_ORDER");
				
				if (aprYN == 4 && aprOrder == 4) {
					a.setAprStatus("결재완료");
				} else {
					a.setAprStatus("결재진행중");
				}
				
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
	
	// 결재 완료 문서 리스트
	public ArrayList<Approval> selectDoneApprovalList(Connection conn, PageInfo pi) {
		
		// SELECT 문 : ResultSet : ArrayList
		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDoneApprovalList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
								
				int aprYN = rset.getInt("APR_YN");
				int aprOrder = rset.getInt("APR_ORDER");
				
				if (aprYN == 4 && aprOrder == 4) {
					a.setAprStatus("결재완료");
				} else {
					a.setAprStatus("결재진행중");
				}
				
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
	
	// 검색 + 전체 문서 리스트
	public ArrayList<Approval> selectSearchAllApprovalList(Connection conn, PageInfo pi, String searchText) {
		
		// SELECT 문 : ResultSet : ArrayList
		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchAllApprovalList");

		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
								
				int aprYN = rset.getInt("APR_YN");
				int aprOrder = rset.getInt("APR_ORDER");
				
				if (aprYN == 4 && aprOrder == 4) {
					a.setAprStatus("결재완료");
				} else {
					a.setAprStatus("결재진행중");
				}
				
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
	
	// 검색 + 결재진행중 문서 리스트
	public ArrayList<Approval> selectSearchIngApprovalList(Connection conn, PageInfo pi, String searchText) {
		
		// SELECT 문 : ResultSet : ArrayList
		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectSearchIngApprovalList");

		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
								
				int aprYN = rset.getInt("APR_YN");
				int aprOrder = rset.getInt("APR_ORDER");
				
				if (aprYN == 4 && aprOrder == 4) {
					a.setAprStatus("결재완료");
				} else {
					a.setAprStatus("결재진행중");
				}
				
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
	
	// 검색 + 결재 완료 문서 리스트
	public ArrayList<Approval> selectSearchDoneApprovalList(Connection conn, PageInfo pi, String searchText) {
		
		// SELECT 문 : ResultSet : ArrayList
		ArrayList<Approval> list = new ArrayList<Approval>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchDoneApprovalList");

		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Approval a = new Approval();
				a.setAprId(rset.getString("APR_ID"));
				a.setAprTitle(rset.getString("APR_TITLE"));
				a.setUpdateDate(rset.getDate("UPDATE_DATE"));
				a.setEmpNo(rset.getString("EMP_NAME"));
								
				int aprYN = rset.getInt("APR_YN");
				int aprOrder = rset.getInt("APR_ORDER");
				
				if (aprYN == 4 && aprOrder == 4) {
					a.setAprStatus("결재완료");
				} else {
					a.setAprStatus("결재진행중");
				}
				
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
	
	// 문서 상세조회
	public Approval selectApproval(Connection conn, String aprId) {
		
		// SELECT 문 : ResultSet : 한 행 조회
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
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return a;
	}
	
	// 지결내역 상세조회
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
				
				DocSpent d = new DocSpent(rset.getInt("DOC_NO")
										, rset.getDate("SPENT_DATE")
										, rset.getString("CORP")
										, rset.getString("CORP_NO")
										, rset.getString("HISTORY")
										, rset.getInt("SPENT_MONEY")
										, rset.getString("APR_ID"));
				
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
	
}
