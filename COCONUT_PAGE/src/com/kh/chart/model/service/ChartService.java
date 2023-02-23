package com.kh.chart.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;

import com.kh.approval.model.dao.ApprovalDao;
import com.kh.approval.model.vo.Approval;
import com.kh.chart.model.dao.ChartDao;
import com.kh.chart.model.vo.Chart;

public class ChartService {

	public JSONArray employeeStatus() {
		
		Connection conn = getConnection();
		
		JSONArray ec = new ChartDao().employeeStatus(conn);
		
		close(conn);
		
		return ec;
	}
	
	public HashMap<Integer, Integer> leaveByMonth() {
		
		Connection conn = getConnection();
		
		HashMap<Integer, Integer> list = new ChartDao().leaveByMonth(conn);
		
		close(conn);
		
		return list;
	}
	
	public HashMap<Integer, Integer> enrollByMonth() {
		
		Connection conn = getConnection();
		
		HashMap<Integer, Integer> list = new ChartDao().enrollByMonth(conn);
		
		close(conn);
		
		return list;
	}
	
	
	// 문서 전체 갯수
	public ArrayList<Chart> selectApprovalList() {
		
		Connection conn = getConnection();
		
		ArrayList<Chart> approList = new ChartDao().selectApprovalList(conn);
		
		close(conn);
		
		return approList;
	}
}
	

	