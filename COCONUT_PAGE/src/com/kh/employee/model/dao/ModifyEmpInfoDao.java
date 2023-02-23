package com.kh.employee.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.employee.model.vo.Employee;

public class ModifyEmpInfoDao {
	
	private Properties prop = new Properties();
	
	public ModifyEmpInfoDao() {

		String fileName = LoginDao.class.getResource("/sql/employee/employee-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int modifyInfo(Connection conn, Employee e) {
		
		// 필요한 변수 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("modifyInfo");
		
		try {
			// pstmt 생성 및 sql 문 전달
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더 값 메우기
			pstmt.setString(1, e.getNickName());
			pstmt.setString(2, e.getAddress());
			pstmt.setString(3, e.getPhone());
			pstmt.setInt(4, e.getEmpNo());
			
			// 실행하면서 결과값 받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	
	public int modifyPwd(Connection conn, Employee e, String newPwd) {
		
		// 필요한 변수 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("modifyPwd");
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더 값 채우기
			pstmt.setString(1, newPwd);
			pstmt.setInt(2, e.getEmpNo());
			pstmt.setString(3, e.getEmpPwd());
			
			//실행하면서 결과값 받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}		
		
		return result;
	}
	
	public Employee selectEmployee(Connection conn, int empNo) {
		
		// SELECT 문 => ResultSet 한 행
		
		// 변수 세팅
		Employee e = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectEmployee");
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);

			// 위치홀더 채우기
			pstmt.setInt(1, empNo);
			
			// 쿼리문 실행
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				e = new Employee(rset.getInt("EMP_NO")
								, rset.getString("EMP_NAME")
								, rset.getString("ADDRESS")
								, rset.getString("PHONE")
								, rset.getString("PROFILE")
								, rset.getString("EMAIL")
								, rset.getString("EMP_PWD")
								, rset.getString("MANAGER_YN")
								, rset.getString("EMP_NUM")
								, rset.getString("TEL")
								, rset.getDate("ENROLL_DATE")
								, rset.getDate("LEAVE_DATE")
								, rset.getString("NICK_NAME")
								, rset.getString("GENDER")
								, rset.getString("DEPT_NAME")
								, rset.getString("JOB_NAME"));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			// 자원 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 결과 리턴
		return e;
	}
}
