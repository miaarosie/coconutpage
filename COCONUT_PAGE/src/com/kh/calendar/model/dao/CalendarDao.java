package com.kh.calendar.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.calendar.model.vo.Calendar;
import com.kh.post.model.dao.ShareDao;

public class CalendarDao {
	
	private Properties prop = new Properties();

	public CalendarDao() {
		String fileName = ShareDao.class.getResource("/sql/calendar/calendar-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public ArrayList<Calendar> selectCalendarList(Connection conn, int empNo) {
		
		ArrayList<Calendar> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCalendarList");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, empNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Calendar c = new Calendar();
				c.setSchdNo(rset.getInt("SCHD_NO"));
				c.setSchdTitle(rset.getString("SCHD_TITLE"));
				c.setSchdCategory(rset.getInt("SCHD_CATEGORY"));
				c.setStartDate(rset.getString("START_DATE"));
				c.setEndDate(rset.getString("END_DATE"));

				list.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public int insertCalendar(Connection conn, Calendar c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCalendar");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getSchdTitle());
			pstmt.setString(2, c.getStartDate());
			pstmt.setString(3, c.getEndDate());
			pstmt.setInt(4, c.getEmpNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}
		
		
		return result;
		
	}
	
	public int updateCalendar(Connection conn, Calendar c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateCalendar");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getStartDate());
			pstmt.setString(2, c.getEndDate());
			pstmt.setInt(3, c.getEmpNo());
			pstmt.setString(4, c.getSchdTitle());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int deleteCalendar(Connection conn, Calendar c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCalendar");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c.getSchdNo());
			pstmt.setInt(2, c.getEmpNo());

			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		System.out.println(result);
		return result;
		
		
	}
	 

}
