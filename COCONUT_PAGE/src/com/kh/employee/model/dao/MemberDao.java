package com.kh.employee.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.vo.Member;

public class MemberDao {
    private Properties prop = new Properties();

//일반 사원용 전체 데이터 화면 보이기
    public MemberDao() {
        String fileName = MemberDao.class.getResource("/sql/employee/member-mapper.xml").getPath();

        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//검색된 조건별로 pageCount의 조건 구하기
    public int listCountbyOption(String find, String option, Connection conn) {
        int pageCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = "SELECT COUNT(*) COUNT FROM EMPLOYEE";

        try {

            if (find.equals("EMP_NO")) {
                sql = "SELECT COUNT(*) COUNT FROM EMPLOYEE WHERE EMP_NO =?";

            } else if (find.equals("EMP_NAME")) {
                sql = "SELECT COUNT(*) COUNT FROM EMPLOYEE WHERE" + "EMP_NAME LIKE '%' || ? || '%'";

            } else if (find.equals("JOB_NAME")) {
                sql = "SELECT COUNT(*) COUNT" +
                        " FROM EMPLOYEE E" +
                        " LEFT OUTER JOIN JOB J" +
                        " ON (E.JOB_CODE=J.JOB_CODE)" +
                        " WHERE J.JOB_NAME=?";

            } else if (find.equals("DEPT_NAME")) {
                sql = "SELECT COUNT(*) COUNT" +
                        " FROM EMPLOYEE E" +
                        " LEFT OUTER JOIN DEPARTMENT D" +
                        " ON (E.DEPT_CODE=D.DEPT_CODE)" +
                        " WHERE D.DEPT_NAME=?";

            } else if (find.equals("NICK_NAME")) {
                sql = "SELECT COUNT(*) COUNT FROM EMPLOYEE WHERE NICK_NAME = ?";

            }

            pstmt = conn.prepareStatement(sql);

            if (find.equals("EMP_NO")) {

                pstmt.setInt(1, Integer.parseInt(option));
            } else {

                pstmt.setString(1, option);
            }

            rset = pstmt.executeQuery();
            if (rset.next()) {
                pageCount = rset.getInt("COUNT");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }

        return pageCount;

    }

    public ArrayList<Member> search(Connection conn) {

        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("search");

        try {

            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while (rset.next()) {

                Member m = new Member();

                m.setEmpNo(rset.getInt("사번"));
                m.setEmpName(rset.getString("이름"));
                m.setNickName(rset.getString("닉네임"));
                m.setDeptCode(rset.getString("부서이름"));
                m.setJobCode(rset.getString("직급명"));
                m.setEmail(rset.getString("이메일"));
                m.setTel(rset.getString("내선번호"));
                m.setPhone(rset.getString("휴대폰"));
                m.setLeaveDate(rset.getDate("퇴사일"));

                list.add(m);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }

        return list;

    }

    // 일반 사원용 전체 데이터 화면 조회
    public Member selectMember(String empNo, Connection conn) {

        Member emp = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("detail");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, empNo);
            rset = pstmt.executeQuery();
            if (rset.next()) {

                emp = new Member();
                emp.setEmpName(rset.getString("이름"));
                emp.setEmpNo(rset.getInt("사번"));
                emp.setEmail(rset.getString("이메일"));
                emp.setEmpName(rset.getString("이름"));
                emp.setNickName(rset.getString("닉네임"));
                emp.setDeptCode(rset.getString("소속부서"));
                emp.setPhone(rset.getString("핸드폰"));
                emp.setJobCode(rset.getString("직급"));
                emp.setTel(rset.getString("내선번호"));
                emp.setProfile(rset.getString("프로필"));
                emp.setEnrollDate(rset.getDate("입사일"));
                emp.setLeaveDate(rset.getDate("퇴사일"));
                emp.setEmpPwd(rset.getString("비밀번호"));
                emp.setManagerYn(rset.getString("관리자여부"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }

        return emp;
    }

    // 사원의 키워드로 검색하기
    public ArrayList<Member> showList(String option,PageInfo pi, String find, Connection conn) {

    	  ArrayList<Member> optionList = new ArrayList<>();
          PreparedStatement pstmt = null;
          ResultSet rset = null;
          
          String sql=prop.getProperty("selectempList");

          if (option != null && find.equals("EMP_NO")) {
              sql = prop.getProperty("selectByempNo");

          } else if (option != null && find.equals("EMP_NAME")) {
              sql = prop.getProperty("selectByempName");

          } else if (option != null && find.equals("DEPT_NAME")) {
                sql = prop.getProperty("selectBydeptName");

          } else if (option != null && find.equals("JOB_NAME")) {
              sql = prop.getProperty("selectByJobName");
          }
          try {
              pstmt = conn.prepareStatement(sql);

              int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit()  + 1;
              int endRow = startRow + pi.getBoardLimit() - 1;

              if ((option != null && find.equals("EMP_NO")) || (option != null && find.equals("EMP_NAME"))
                      || (option != null && find.equals("JOB_NAME")) || (option != null && find.equals("DEPT_NAME"))) {
                  pstmt.setString(1, option);
                  pstmt.setInt(2, startRow);
                  pstmt.setInt(3, endRow);
               

              }else {
                  pstmt.setInt(1, startRow);
                  pstmt.setInt(2, endRow);
              }
              rset = pstmt.executeQuery();

              while (rset.next()) {

                  Member m = new Member();

                  m.setEmpNo(rset.getInt("사번"));
                  m.setEmpName(rset.getString("이름"));
                  m.setNickName(rset.getString("닉네임"));
                  m.setDeptCode(rset.getString("부서이름"));
                  m.setJobCode(rset.getString("직급명"));
                  m.setEmail(rset.getString("이메일"));
                  m.setTel(rset.getString("내선번호"));
                  m.setPhone(rset.getString("휴대폰"));

                  optionList.add(m);
              }
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } finally {
              JDBCTemplate.close(rset);
              JDBCTemplate.close(pstmt);
          }

          return optionList;

      }

    	
    // 총 사원의 리스트 갯수 구하기//
    public int selectListCount(Connection conn) {
        int listCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectListCount");

        try {
            pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                listCount = rset.getInt("COUNT");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }

        return listCount;
    }

    
    public ArrayList<Member> selectempList(Connection conn,PageInfo pi) {

        ArrayList<Member> empList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectempList");

        try {
            pstmt = conn.prepareStatement(sql);
        
        int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit()  + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
        
        pstmt.setInt(1, startRow);
        pstmt.setInt(2, endRow);
        rset = pstmt.executeQuery();

        while (rset.next()) {

            Member m = new Member();

            m.setEmpNo(rset.getInt("사번"));
            m.setEmpName(rset.getString("이름"));
            m.setNickName(rset.getString("닉네임"));
            m.setDeptCode(rset.getString("부서이름"));
            m.setJobCode(rset.getString("직급명"));
            m.setEmail(rset.getString("이메일"));
            m.setTel(rset.getString("내선번호"));
            m.setPhone(rset.getString("휴대폰"));

            empList.add(m);
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        JDBCTemplate.close(rset);
        JDBCTemplate.close(pstmt);
    }

    return empList;
}
    // 모든 회원정보 전체 조회하기(관리자용)
    public ArrayList<Member> showAllempList(Connection conn) {

        ArrayList<Member> empListall = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("showAllempList");

        try {

            pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery();

            while (rset.next()) {

                Member m = new Member();

                m.setEmpNo(rset.getInt("사번"));
                m.setEmpName(rset.getString("이름"));
                m.setDeptCode(rset.getString("부서이름"));
                m.setJobCode(rset.getString("직급명"));
                m.setEmail(rset.getString("이메일"));
                m.setEmpPwd(rset.getString("비밀번호"));
                m.setTel(rset.getString("내선번호"));
                m.setPhone(rset.getString("휴대폰"));
                m.setEmpAddress(rset.getString("주소"));
                m.setManagerYn(rset.getString("관리자여부"));
                m.setEnrollDate(rset.getDate("입사일"));
                m.setLeaveDate(rset.getDate("퇴사일"));

                empListall.add(m);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }
        return empListall;

    }

   
    // 사원추가(관리자)
    public int insertMember(Connection conn, Member m) {

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertMember");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, m.getEmpNo());
            pstmt.setString(2, m.getEmpName());
            pstmt.setString(3, m.getGender());
            pstmt.setString(4, m.getEmpNum());
            pstmt.setInt(5, Integer.parseInt(m.getDeptCode()));
            pstmt.setString(6, m.getJobCode());
            pstmt.setString(7, m.getEmail());
            pstmt.setString(8, m.getEmpPwd());
            pstmt.setString(9, m.getTel());
            pstmt.setString(10, m.getPhone());
            pstmt.setString(11, m.getEmpAddress());
            pstmt.setString(12, m.getManagerYn());
            pstmt.setDate(13, m.getEnrollDate());

            result = pstmt.executeUpdate();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        } finally {
            JDBCTemplate.close(pstmt);
        }

        return result;

    }

    // 프로필 사진 추가(일반조회-상세조회화면)
    public int insertThumbnailmMember(Connection conn, Member m) {
        // INSERT 문 => int (처리된 행의 갯수)

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertThumbnailmMember");

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, m.getProfile());
            pstmt.setInt(2, m.getEmpNo());

            result = pstmt.executeUpdate();
            System.out.println(result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            JDBCTemplate.close(pstmt);
        }

        return result;
    }

    // 사원 검색하기(관리자용)
    public ArrayList<Member> searchListAdmin(String option, String find, Connection conn) {

        ArrayList<Member> optionListAd = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("searchListAdmin");

        // 전체///
        try {
            if (find != null && !option.equals("")) {
                sql += " WEHRE " + find + " like '%'|| ? ||'%'";
            }
            /*
             * if (status.equals("")) {
             * sql += " AND LEAVE_DATE IS NOT NULL"; //퇴사자
             * }else if(status.equals("null")){
             * sql+=" AND LEAVE_DATE IS NULL"; //재직자
             * 
             * }else{
             * sql+="";
             * }
             */

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, option);

            rset = pstmt.executeQuery();

            while (rset.next()) {

                Member m = new Member();

                m.setEmpNo(rset.getInt("사번"));
                m.setEmpName(rset.getString("이름"));
                m.setDeptCode(rset.getString("부서이름"));
                m.setJobCode(rset.getString("직급명"));
                m.setEmail(rset.getString("이메일"));
                m.setEmpPwd(rset.getString("비밀번호"));
                m.setTel(rset.getString("내선번호"));
                m.setPhone(rset.getString("휴대폰"));
                m.setEmpAddress(rset.getString("주소"));
                m.setManagerYn(rset.getString("관리자여부"));
                m.setEnrollDate(rset.getDate("입사일"));
                m.setLeaveDate(rset.getDate("퇴사일"));

                optionListAd.add(m);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }

        return optionListAd;
    }

    // 회원 정보 수정 메소드
    public int updateMemberInfo(Connection conn, Member m) {

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateMemberInfo");

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, Integer.parseInt(m.getDeptCode()));
            pstmt.setString(2, m.getJobCode());
            pstmt.setString(3, m.getEmpPwd());
            pstmt.setString(4, m.getTel());
            pstmt.setString(5, m.getManagerYn());
//            pstmt.setDate(6, m.getLeaveDate());
            pstmt.setInt(6, m.getEmpNo());
            
            result = pstmt.executeUpdate();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        } finally {
            JDBCTemplate.close(pstmt);
        }

        return result;

    }
    //사원 사번 중복체크 
    public int noCheck( Connection conn, int checkEmpNo) {
      
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("noCheck");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt (1,checkEmpNo);
            rset= pstmt.executeQuery();
            
            if(rset.next()) {
                count = rset.getInt("COUNT(*)");
                
            }
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
            
        }
        return count;
    }
    
    // 회원 퇴사처리 
    public int resignMember(Connection conn, Member m) {

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("resignMember");

        try {
            pstmt = conn.prepareStatement(sql);

           
            pstmt.setDate(1, m.getLeaveDate());
            pstmt.setInt(2, m.getEmpNo());
          
            result = pstmt.executeUpdate();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        } finally {
            JDBCTemplate.close(pstmt);
        }

        return result;

    }
    // 사번으로 검색할때 카운트
    public int selectListCountbyEmpNo(Connection conn, String option) {

        int listCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectListCountbyEmpNo");

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, option);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                listCount = rset.getInt("COUNT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);

        }
        return listCount;
    }

    public int selectListCountbyEmpName(Connection conn, String option) {

        int listCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectListCountbyEmpName");

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, option);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                listCount = rset.getInt("COUNT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);

        }
        return listCount;
    }

    public int selectListCountbyDeptName(Connection conn, String option) {

        int listCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectListCountbyDeptName");

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, option);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                listCount = rset.getInt("COUNT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);

        }
        return listCount;
    }

    public int selectListCountbyJobName(Connection conn, String option) {

        int listCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectListCountbyJobName");

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, option);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                listCount = rset.getInt("COUNT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);

        }
        return listCount;

    }

	public ArrayList<Member> showInOfficeList(String option, PageInfo pi, String find) {
		
		
		
		
		
		return null;
	}
}



