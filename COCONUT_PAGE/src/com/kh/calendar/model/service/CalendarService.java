package com.kh.calendar.model.service;

import static com.kh.common.JDBCTemplate.*;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.calendar.model.dao.CalendarDao;
import com.kh.calendar.model.vo.Calendar;

public class CalendarService {
	
	public ArrayList<Calendar> selectCalendarList(int empNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Calendar> list = new CalendarDao().selectCalendarList(conn, empNo);
		
		close(conn);
		
		return list;
		
	}
	
	public int insertCalendar(Calendar c) {
		
		Connection conn = getConnection();
		
		int result = new CalendarDao().insertCalendar(conn, c);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		} 
		close(conn);
		
		return result;
		
	}
	
	public int updateCalendar(Calendar c) {
		
		Connection conn = getConnection();
				
		int result = new CalendarDao().updateCalendar(conn, c);
		
		if(result > 0) {
			
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteCalendar(Calendar c) {
		
		Connection conn = getConnection();
		
		int result = new CalendarDao().deleteCalendar(conn, c);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

}
