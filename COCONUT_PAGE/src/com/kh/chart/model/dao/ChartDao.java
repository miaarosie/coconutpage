package com.kh.chart.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONArray;

import com.kh.chart.model.vo.Chart;

public class ChartDao {
	
	private Properties prop = new Properties();

		public ChartDao() {
		
		String fileName = ChartDao.class.getResource("/sql/chart/chart-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public JSONArray employeeStatus(Connection conn) {
		
		//EmployeeChart ec = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		JSONArray jsonArray = new JSONArray();
		
		String sql = prop.getProperty("employeeStatus");
		
		JSONArray colNameArray = new JSONArray();
		colNameArray.add("총사원수");
		colNameArray.add("퇴직");
		colNameArray.add("재직");
		jsonArray.add(colNameArray);
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rset.getInt(""));
				rowArray.add(rset.getInt(""));
				rowArray.add(rset.getInt(""));	
				
				jsonArray.add(rowArray);
			} 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		return jsonArray;
	} 
			

	public HashMap<Integer, Integer> leaveByMonth(Connection conn) {
		
		// SELECT 문 => ResultSet 객체 (여러행조회)
		
		HashMap<Integer, Integer> list = new HashMap<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("leaveByMonth");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.put(rset.getInt("월"), rset.getInt("명"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	
	public HashMap<Integer, Integer> enrollByMonth(Connection conn) {
		
		// SELECT 문 => ResultSet 객체 (여러행조회)
		
		HashMap<Integer, Integer> list = new HashMap<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("enrollByMonth");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.put(rset.getInt("월"), rset.getInt("명"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	// 문서 전체
	public ArrayList<Chart> selectApprovalList(Connection conn) {
			
			// SELECT 문 : ResultSet => ArrayList
			
			ArrayList<Chart> approList = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectApprovalList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
			
				while(rset.next()) {
					
					Chart c = new Chart();
					c.setAprYN(rset.getInt("상태"));
					c.setAprId(rset.getInt("갯수"));
					
					approList.add(c);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			
				close(rset);
				close(pstmt);
			}
	
			return approList;
		}
	
	
	
	
	
	
}
		


