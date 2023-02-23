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
	
	// 검색 값 유지
	String searchText = (String)request.getAttribute("searchText");
	int docStmt = (int)request.getAttribute("docStmt");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    
        <title>COCONUT PAGE</title>
        <style>
            /* --------------------------------------------------------------------- */
            /* main 영역 */
            .main {
                padding: 70px 100px;
            }
    
            /* --------------------------------------------------------------------- */
            /* 검색조건, 입력, 검색 버튼 */
            #search { height: 50px; }
    
            #doc-stmt, #search-text, #search-btn { height: 100%; }
    
            #doc-stmt { width: 200px; }
    
            #doc-stmt option[value="none"] { display: none; }
    
            #search-text {
                width: 500px;
                display: inline;
            }
    
            #search-btn { width: 100px; }
            
            /* --------------------------------------------------------------------- */
            /* 메인 리스트 */
            #list-btn {
                width: 100%;
                padding: 10px;
                height: 50px;
            }
            
            #tb {
                width: 100%;
                text-align: center;
                vertical-align: middle;
                background-color: white;
                margin-bottom: 30px;
            }

            #tb>tbody>tr {
                height: 50px;
            }
            
            #tb a { color: black; }
            
            #tb>thead { background-color: lightgray; }
            
            .pagination {
                margin: 0% 45%;
            }
            .pagination a {
                color:rgb(110, 76, 68);
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
                <%@ include file="../common/sidebar.jsp" %>				

                <!-- 실제 content 영역 -->
                <div class="main">    
                    <form action="<%= contextPath %>/send.docs" method="get" id="search">
                    	<input type="hidden" name="currentPage" value="1">                    	
                        <select name="docStmt" id="doc-stmt" required>
                            <option value="0" <%= docStmt == 0 || docStmt == 5?"selected":"" %> disabled>상태</option>
                            <option value="1" <%= docStmt == 1?"selected":"" %>>접수</option>
                            <option value="2" <%= docStmt == 2?"selected":"" %>>열람</option>
                            <option value="3" <%= docStmt == 3?"selected":"" %>>반려</option>
                            <option value="4" <%= docStmt == 4?"selected":"" %>>완료</option>
                        </select>
                        
                        <input type="search" name="searchText" id="search-text" class="form-control" placeholder="검색어를 입력하세요" value="<%= searchText %>">
                        <input type="submit" id="search-btn" value="검색">
                    </form>
                    

                    <div class="list">                       
                        <div id="list-btn">
                        </div>    
                        <table id="tb" class="table">
                            <thead>
                                <th>No</th>
                                <th>문서구분</th>
                                <th>문서번호</th>
                                <th>제목</th>
                                <th>신청일자</th>
                                <th>사원명</th>
                                <th>상태
                                </th>
                            </thead>
        
                            <tbody>
                            
                            	<%! String aprStatus = ""; %>
                            	<% if(list.isEmpty()) { %>
                            		<tr>
                            			<td colspan="7">현재 상신문서함이 비어있습니다.</td>
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
		                                    <td>
			                                    <%
			                                    switch (list.get(i).getAprStatus()) {
			                            		case "1": aprStatus = "접수"; break;
			                            		case "2": aprStatus = "열람"; break;
			                            		case "3": aprStatus = "반려"; break;
			                            		case "4": aprStatus = "승인"; break;
			                            		case "5" : aprStatus = "결재선미지정";
			                            		}
			                                    %>
			                                    <%= aprStatus %>
		                                    </td>
		                               		</tr>
                            		<% } %>
 							   <% } %>
                            </tbody>
                        </table>
                        
    
                        <div id="page">
                            <ul class="pagination">
                            
                            	<% if (currentPage != 1) { %>
                                	<li class="page-item"><a class="page-link" href="<%= contextPath %>/send.docs?currentPage=<%= currentPage - 1 %>&docStmt=<%= docStmt %>&searchText=<%= searchText %>">&lt;</a></li>
                                <% } %>
                                
                                <% for (int p = startPage; p <= endPage; p++) { %>
                                    <% if (p != currentPage) { %>
                                		<li class="page-item"><a class="page-link" href="<%= contextPath %>/send.docs?currentPage=<%= p %>&docStmt=<%= docStmt %>&searchText=<%= searchText %>"><%= p %></a></li>
                                    <% } else { %>
                                        <li class="page-item"><a class="page-link" id="disabled"><%= p %></a></li>
                                    <% } %>
                                <% } %>
                                
                                <% if (currentPage != maxPage && maxPage != 0) { %>
                                	<li class="page-item"><a class="page-link" href="<%= contextPath %>/send.docs?currentPage=<%= currentPage + 1 %>&docStmt=<%= docStmt %>&searchText=<%= searchText %>">&gt;</a></li>
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
                	location.href = "<%= contextPath %>/rcvapr.dc?aprType=1&aprId=" + $(this).children().eq(2).text()+"&route=2";
                } else {
                    
                    // 지출결의서 상세조회
                    location.href = "<%= contextPath %>/send.spt?aprId=" + $(this).children().eq(2).text();
                }
            });
        });
    </script>
    
     	                <script>
    $(function() {
       
            $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap' >전자결재 홈</a></div>"+
                    "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/send.docs?currentPage=1'  style='color:#20c997' >상신문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/temp.docs?currentPage=1'  >임시저장함</a></div>"
                    <% if(e.getManagerYN().equals("Y")) {%>
                    +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1' >전체문서조회</a></div>"
            		<%}%>
            );
    });
   </script>
        
    </body>
</html>