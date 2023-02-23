package com.kh.employee.model.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.employee.model.dao.LoginDao;
import com.kh.employee.model.vo.Employee;

public class LoginService {

	public Employee loginEmployee(Employee e) {
		
		// Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 전달받은 매개변수와 conn 객체를 DAO 로 넘기기
		Employee loginEmp = new LoginDao().loginEmployee(conn, e);
		
		// 트랜잭션 처리 (SELECT 문이므로 생략)
		
		// Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 결과값 리턴
		return loginEmp;
	}
	
}
