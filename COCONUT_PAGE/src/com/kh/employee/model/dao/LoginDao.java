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

public class LoginDao {
	
	private Properties prop = new Properties();
	
	public LoginDao() {
		
		String fileName = LoginDao.class.getResource("/sql/employee/employee-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 요청
	public Employee loginEmployee(Connection conn, Employee e) {
		
		// 1. 필요한 변수 세팅
		Employee loginEmp = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginEmployee");
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더값 메우기
			pstmt.setInt(1, e.getEmpNo());
			pstmt.setString(2, e.getEmpPwd());

			// 쿼리문 실행하고 결과값 받기
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				
				loginEmp = new Employee(rset.getInt("EMP_NO")
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
			// 자원반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return loginEmp;
	}

}
