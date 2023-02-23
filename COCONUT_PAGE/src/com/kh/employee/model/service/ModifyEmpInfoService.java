package com.kh.employee.model.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.employee.model.dao.ModifyEmpInfoDao;
import com.kh.employee.model.vo.Employee;

public class ModifyEmpInfoService {

	public Employee modifyInfo(Employee e) {
		
		// Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// Dao 단으로 conn, e 보내고 결과값 리턴
		int result = new ModifyEmpInfoDao().modifyInfo(conn, e);
		
		Employee updateEmp = null;
		
		// 트랜잭션 처리
		if (result > 0) {
			JDBCTemplate.commit(conn);
			
			updateEmp = new ModifyEmpInfoDao().selectEmployee(conn, e.getEmpNo());
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// conn 반납
		JDBCTemplate.close(conn);
		
		// 결과 리턴
		return updateEmp;
	}
	
	public Employee modifyPwd(Employee e, String newPwd) {
		
		// Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// Dao 단으로 매개변수 보내고 결과값 받기
		int result = new ModifyEmpInfoDao().modifyPwd(conn, e, newPwd);
		
		Employee updateEmp = null;
		
		// 트랜잭션 처리
		if (result > 0) {
			JDBCTemplate.commit(conn);
			
			updateEmp = new ModifyEmpInfoDao().selectEmployee(conn, e.getEmpNo());
			
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// conn 반납
		JDBCTemplate.close(conn);
		
		// 결과 리턴
		return updateEmp;
	}
	
}
