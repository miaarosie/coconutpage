<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>
<%@page import="java.util.ArrayList, com.kh.approval.model.vo.Approval"%>
<%
	ArrayList<Approval> list = (ArrayList<Approval>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");

	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
	int docStmt = (int)request.getAttribute("docStmt");
	String searchText = (String)request.getAttribute("searchText");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->

    <title>COCONUT PAGE</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script> <!-- 온라인 방식 -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: rgb(253, 255, 240);
        }
        .wrap {
            
            margin : auto;
            width : 1920px;
            height : 1080px;
            /* border: 1px solid black; */
            
        }
        .header {
            height: 100px;
            width : 100%; 
            border-bottom : 1px solid black;     
            box-sizing: border-box;      
        }
        .container {
            width : 1920px!important;
            height : 980px;
            margin : 0px!important;
            padding : 0px!important;
            max-width: none!important;
        }

        .container>div {
            height : 980px;
            float : left;
        }
        .sidebar {
            width: 100px;
            background-color: rgb(110, 76, 68);
        }
        .main {
            float : left;
            width : 1820px;

        }

        #logo {
            width : 100px;
            height : 100px;

        }

        #coconut_page {
            color: rgb(110, 76, 68);
            font-weight: bolder;
            font-size: 40px;
            width : 300px;
            height : 100%;
            padding-top : 20px;
            vertical-align: middle;
            
            
        }
        
        .header>div {
            float : left;
        }

        /* --------------------------------------------------------------------- */
        /* header 가운데 메뉴바 */

        #header_menu, #header_menu {
            color: rgb(110, 76, 68);
            font-weight: 400;
            font-size: 30px;
            width : 300px;
            height : 100%;
            padding-top : 20px;
            text-align: center;
        }



        /* --------------------------------------------------------------------- */
        /* header 우측 */
        
        #profile, #logout {
            height : 100px;
            /* border : 1px solid black; */
            box-sizing: border-box;
        }

        #profile {
            width : 100px;
        }

        #logout {
            width : 170px;
            text-align: center;
            padding-top :30px

        }

        /* --------------------------------------------------------------------- */
        /* 사이드 바 버튼 div */

        .side {
            width : 100%;
            height : 100px;
            /* border : 1px solid black; */
            box-sizing: border-box;
            text-align: center;
            padding-top : 10px;
            color : rgb(253, 255, 240);
        }

        /* 사이드 바 버튼 */
        .side>img {
            width: 60px;
            height : 60px;
            
        }

        

        /* 윤곽선용 */
        body { 
            box-sizing : border-box;
            /* border : 1px solid black; */
        }
        /* --------------------------------------------------------------------- */

        #header_menu ul {
            list-style-type: none;
            display: inline;
        }
        
        .navbar li {
            padding-top: 10px;
            width: 200px;
            float: left;
            font-size: 25px;
        }

        #header_menu a {
            color:rgb(110, 76, 68);
        }

        /* --------------------------------------------------------------------- */
        /* main 영역 */
        .main {
            padding: 70px 100px;
        }

        /* --------------------------------------------------------------------- */
        /* 검색조건, 입력, 검색 버튼 */
        #search { height: 50px; }

        #search-filter, #search-text, #search-btn { height: 100%; }

        #search-filter { width: 200px; }

        #search-filter option[value=""] { display: none; }

        #search-text {
            width: 500px;
            display: inline;
        }

        #search-btn { width: 100px; }
        
        /* --------------------------------------------------------------------- */
        /* 메인 리스트 */
        #list-title {
            display: inline-block;
            width: 100%;
            padding: 10px;
            margin: 10px 0px;
            font-size: 30px;
        }

        #list-icon {
            display: inline-block;
            width: 30px;
            height: 30px;
            background-color: black; /* 아이콘 표시 */
        }
        
        #tb {
            width: 100%;
            text-align: center;
            vertical-align: middle;
            background-color: white;
            margin-bottom: 30px;
        }

        #tb td {
            height: 50px;
        }
        
        #tb a { color: black; }
        
        #tb>thead { 
            background-color: lightgray;
            height: 50px;
        }
        

        .pagination {
            margin: 0% 45%;
        }
        .pagination a {
            color:rgb(110, 76, 68);
        }
        #active-page {
            background-color: rgb(110, 76, 68);
            color: white;
        }

        #disabled {
            background-color: rgb(110, 76, 68);
            color: white;
        }
        #tb>tbody>tr:hover {
            background: rgb(244, 244, 244);
            cursor: pointer;
        }
</style>

</head>
<body>
    <div class="wrap"> 
        
        <%@ include file="../common/header.jsp" %>

        <div class="container">

            <!-- 고정 사이드바 -->
            <%@ include file="../common/sidebar.jsp" %>

            <script>
    
            var msg = "<%= alertMsg %>";
            
            if (msg != "null") {
                alert(msg);
            
                <% session.removeAttribute("alertMsg"); %>
            }
            
            </script>

            <!-- 실제 content 영역 -->
            <div class="main">

                <form action="" method="get" id="search">

                    <input type="hidden" name="currentPage" value="1">

                    <select name="docStmt" id="search-filter" required>
                        <option value="5" <%= docStmt == 0 || docStmt == 5?"selected":"" %> selected disabled>상태</option>
                        <option value="2" <%= docStmt == 2?"selected":"" %> >결재진행중</option> <!-- 열람~결재완료전 -->
                        <option value="4" <%= docStmt == 4?"selected":"" %>>결재완료</option> 
                    </select>
                    <input type="search" id="search-text" class="form-control" name="searchText" placeholder="<%= searchText %>">
                    <input type="submit" id="search-btn" value="검색">
                </form>

                <div class="list">
                    
                    <div id="list-title">
                        <span>전체문서 조회</span>
                        
                    </div>

                    <div id="list-tb">
                        
                        <table id="tb" class="table">
                            <thead>
                                <th>No</th>
                                <th>문서구분</th>
                                <th>문서번호</th>
                                <th>제목</th>
                                <th>신청일자</th>
                                <th>사원명</th>
                                <th>상태</th>
                            </thead>
        
                            <tbody>
                            	<% if(list.isEmpty()) { %>
                            		<tr>
                            			<td colspan="7">현재 전체문서함이 비어있습니다.</td>
                            		</tr>
                            	<% } else { %>
                            		<% for (int i=0; i<list.size(); i++) { %>
		                            		<tr>
			                                    <td><%= (currentPage - 1) * 10 + i+1 %></td>
			                                    <td><%= list.get(i).getDocType() %></td>
			                                    <td><%= list.get(i).getAprId() %></td>
			                                    <td><%= list.get(i).getAprTitle() %></td>
			                                    <td><%= list.get(i).getUpdateDate() %></td>
			                                    <td><%= list.get(i).getEmpNo() %></td>
			                                    <td><%= list.get(i).getAprStatus() %>
		                                    </td>
		                               		</tr>
                            		<% } %>
                                
 							   <% } %>
                            </tbody>
                        </table>
                        
                    </div>
                    
                    <div id="page">
                        <ul class="pagination">
                        
                        	<% if (currentPage != 1) { %>
                            	<li class="page-item"><a class="page-link" href="<%= contextPath %>/admin.docs?currentPage=<%= currentPage - 1 %>&docStmt=<%= docStmt %>&searchText=<%= searchText %>">&lt;</a></li>
                            <% } %>
                            
                            <% for (int p = startPage; p <= endPage; p++) { %>
                                <% if (p != currentPage) { %>
                            		<li class="page-item"><a class="page-link" href="<%= contextPath %>/admin.docs?currentPage=<%= p %>&docStmt=<%= docStmt %>&searchText=<%= searchText %>"><%= p %></a></li>
                                <% } else { %>
                                    <li class="page-item"><a class="page-link" id="disabled"><%= p %></a></li>
                                <% } %>
                            <% } %>
                            
                            <% if (currentPage != maxPage && maxPage != 0) { %>
                            	<li class="page-item"><a class="page-link" href="<%= contextPath %>/admin.docs?currentPage=<%= currentPage + 1 %>&docStmt=<%= docStmt %>&searchText=<%= searchText %>">&gt;</a></li>
                            <% } %>
                            
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <script>
    $(function() {
        $("#tb tbody tr").click(function() {
        	
            if ($(this).children().eq(1).text() == '보고서') {
            	location.href = "<%= contextPath %>/rcvapr.dc?aprType=1&aprId=" + $(this).children().eq(2).text()+"&route=3";;
            } else {
                // 지출결의서 상세조회
                location.href = "<%= contextPath %>/send.spt?aprId=" + $(this).children().eq(2).text();
            }
        });
    });
    </script>
    
    <script>
    $(function() {
       
            $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap'>전자결재 홈</a></div>"+
                    "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/send.docs?currentPage=1' >상신문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/temp.docs?currentPage=1' >임시저장함</a></div>"                    		
                    <% if(e.getManagerYN().equals("Y")) {%>
                    +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1'  style='color:#20c997'>전체문서조회</a></div>"
            		<%}%>
            );
 		  
    });
   </script>

    
       
    
    
</body>
</html>