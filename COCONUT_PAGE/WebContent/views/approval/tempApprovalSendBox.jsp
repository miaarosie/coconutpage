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
	
	String searchText = (String)request.getAttribute("searchText");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
 <!-- 임시저장한 문서함 리스트-->
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
            height: 50px;
        }
        
        #list-btn button {
            width: 100px;
            padding: 10px;
            height: 50px;
            margin: 10px;
            float: right;
            background-color: rgb(110, 76, 68);
            color: white;
            border-radius: 5px;
            border: none;
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
                <form action="<%= contextPath %>/temp.docs" method="get" id="search">
                    <input type="hidden" name="currentPage" value="1">
                    <input type="search" name="searchText" id="search-text" class="form-control" placeholder="<%= searchText %>">
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
                            <th>최종수정일자</th>
                            <th>사원명</th>
                        </thead>
    
                        <tbody>
                        
                            <%! String aprStatus = ""; %>
                            <% if(list.isEmpty()) { %>
                                <tr>
                                    <td colspan="6">현재 임시저장 문서함이 비어있습니다.</td>
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
                                        </tr>
                                <% } %>
                            
                            <% } %>
                        </tbody>
                    </table>
                    

                    <div id="page">
                        <ul class="pagination">
                        
                            <% if (currentPage != 1) { %>
                                <li class="page-item"><a class="page-link" href="<%= contextPath %>/temp.docs?currentPage=<%= currentPage - 1 %>&searchText=<%= searchText %>">&lt;</a></li>
                            <% } %>
                            
                            <% for (int p = startPage; p <= endPage; p++) { %>
                                <% if (p != currentPage) { %>
                                    <li class="page-item"><a class="page-link" href="<%= contextPath %>/temp.docs?currentPage=<%= p %>&searchText=<%= searchText %>"><%= p %></a></li>
                                <% } else { %>
                                    <li class="page-item"><a class="page-link" id="disabled"><%= p %></a></li>
                                <% } %>
                            <% } %>
                            
                            <% if (currentPage != maxPage && maxPage != 0) { %>
                                <li class="page-item"><a class="page-link" href="<%= contextPath %>/temp.docs?currentPage=<%= currentPage + 1 %>&searchText=<%= searchText %>">&gt;</a></li>
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
                	location.href = "<%= contextPath %>/temp.rep?aprId=" + $(this).children().eq(2).text();
                } else if ($(this).children().eq(1).text() == '지출결의서') {
                    location.href = "<%= contextPath %>/temp.spt?aprId=" + $(this).children().eq(2).text();
                } else {
                	alert("현재 임시저장된 문서가 없습니다.");
                	location.href = "<%= contextPath %>/temp.docs?currentPage=1"
                }
            });
        });
    </script>
    
     	                <script>
    $(function() {
       
            $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap' >전자결재 홈</a></div>"+
                    "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/send.docs?currentPage=1'  >상신문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/temp.docs?currentPage=1' style='color:#20c997' >임시저장함</a></div>"
                    <% if(e.getManagerYN().equals("Y")) {%>
                    +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1'  >전체문서조회</a></div>"
            		<%}%>
            );
    });
   </script>

</body>
</html>