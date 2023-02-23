<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.kh.common.model.vo.PageInfo"%>
<%@page import="java.util.ArrayList, com.kh.approval.model.vo.Approval"%>
<%
	String searchText = (String)request.getAttribute("searchText");
	int docStmt = (int)request.getAttribute("docStmt");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	ArrayList<Approval> aList = (ArrayList<Approval>) request.getAttribute("aList");
	
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
        <title>COCONUT PAGE</title>
        <style>
            body {
                background-color: rgb(253, 255, 240);
            }

           
    
            #header_menu ul {
                list-style-type: none;
                display: inline;
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
    
            #doc-stmt, #search-text, #search-btn { height: 100%; }
    
            #doc-stmt { width: 200px; padding-left: 10px; }
    
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
                table-layout: fixed;
            }

			#tb th, #tb td {
				vertical-align: middle;
				padding: 0;
			}
          
            #tb th {
				height: 65px;
				font-size: 20px;
				font-weight: 600;
			}
			
			#tb td {
				height: 65px;
				font-size: 20px;
				text-overflow: ellipsis;
				white-space: nowrap;
				overflow: hidden;
			}
            
            
            #tb a { color: black; }
            
            #tb>thead { background-color: lightgray; }
            
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
                    <form action="<%= contextPath %>/rcv.docs" method="get" id="search">
                    	<input type="hidden" name="currentPage" value="1">                    	
                        <select name="docStmt" id="doc-stmt" required>
                            <option value="0" <%= docStmt == 0?"selected":"" %>>전체</option>
                            <option value="1" <%= docStmt == 1?"selected":"" %>>미결</option>
                            <option value="2" <%= docStmt == 2?"selected":"" %>>진행</option>
                            <option value="3" <%= docStmt == 3?"selected":"" %>>반려</option>
                            <option value="4" <%= docStmt == 4?"selected":"" %>>완결</option>
                            <option value="5" <%= docStmt == 5?"selected":"" %>>참조</option>
                        </select>                        
                        <input type="search" name="searchText" id="search-text" class="form-control" placeholder="검색어를 입력하세요" value="">
                        <input type="submit" id="search-btn" value="검색">
                    </form>               
                    <div class="list">                        
                        <div id="list-btn">
                        </div>    
                        <table id="tb" class="table">
                            <thead>
                                <th style="width: 6%">No</th>
                                <th style="width: 9%">문서구분</th>
                                <th style="width: 17%">문서번호</th>
                                <th style="width: 42%">제목</th>
                                <th style="width: 10%">작성일자</th>
                                <th style="width: 8%">작성자</th>
                                <th style="width: 8%">상태</th>
                            </thead>        
                            <tbody>                            
                            	<% if(aList.isEmpty()) { %>
                            		<tr>
                            			<td colspan="7">현재 받은문서함이 비어있습니다.</td>
                            		</tr>
                            	<% } else { %>
                            		<% for (int i=0; i<aList.size(); i++) { %>
		                            		<tr>
		                                    <td><%= (currentPage - 1) * 10 + i+1 %></td>
		                                    <td><%= aList.get(i).getDocType() %></td>
		                                    <td><%= aList.get(i).getAprId() %></td>
		                                    <td><%= aList.get(i).getAprTitle() %></td>
		                                    <td><%= aList.get(i).getWriteDate() %></td>
		                                    <td><%= aList.get(i).getEmpNo() %></td>
		                                     <td><%= aList.get(i).getAprStatus() %></td>
		                               		</tr>
                            		<% } %>
 							   <% } %>
                            </tbody>
                        </table>                     
    
                        <div id="page">
                            <ul class="pagination">
                            	<% if (currentPage != 1) { %>
                                	<li class="page-item no-page-prev"><a class="page-link" >&lt;</a></li>
                                <% } %>
                                <% for (int p = startPage; p <= endPage; p++) { %>
                                    <% if (p != currentPage) { %>
                                		<li class="page-item no-page-btn"><a class="page-link" ><%= p %></a></li>
                                    <% } else { %>
                                        <li class="page-item no-page-btn disabled"><a class="page-link" id="active-page"><%= p %></a></li>
                                    <% } %>
                                <% } %>
                                <% if (currentPage != maxPage && maxPage != 0) { %>
                                	<li class="page-item no-page-next"><a class="page-link" >&gt;</a></li>
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
             	var ano = $(this).children().eq(2).text();    

             	if (ano == "") {
             		return false;
             	}
                 if ($(this).children().eq(1).text() == '보고서') {                    
                 	location.href = "<%= contextPath %>/rcvapr.dc?aprType=1&aprId=" + ano+"&route=1";
                 } else {
                     // 지출결의서 상세조회
                 	location.href = "<%= contextPath %>/rcvapr.dc?aprType=2&aprId=" + ano+"&route=1";
                 }
             });

            
			var keyword = "<%=searchText%>";
			var prevNo = <%=currentPage%>-1;
			var nextNo = <%=currentPage%>+1;
			
			if(keyword == "null") {
				$(".no-page-prev>a").click(function() {
					location.href ="<%=contextPath%>/rcv.docs?docStmt=<%=docStmt%>&currentPage="+prevNo;
				});
				$(".no-page-btn>a").click(function() {
					location.href ="<%=contextPath%>/rcv.docs?docStmt=<%=docStmt%>&currentPage="+$(this).text();
				});
				$(".no-page-next>a").click(function() {
					location.href ="<%=contextPath%>/rcv.docs?docStmt=<%=docStmt%>&currentPage="+nextNo;
				});
         	}else {
         		$("#search-text").val(keyword);
				$(".no-page-prev>a").click(function() {
					location.href ="<%=contextPath%>/rcv.docs?docStmt=<%=docStmt%>&currentPage="+prevNo+"&searchText="+keyword;
				});
				$(".no-page-btn>a").click(function() {
					location.href ="<%=contextPath%>/rcv.docs?docStmt=<%=docStmt%>&currentPage="+$(this).text()+"&searchText="+keyword;
				});
				$(".no-page-next>a").click(function() {
					location.href ="<%=contextPath%>/rcv.docs?docStmt=<%=docStmt%>&currentPage="+nextNo+"&searchText="+keyword;					
				});
			}
			$("#doc-stmt").change(function(){
                location.href ="<%=contextPath%>/rcv.docs?docStmt="+$(this).val()+"&currentPage=1";
            });
        });
    </script>
    
   <script>
    $(function() {
       
            $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap' >전자결재 홈</a></div>"+
                    "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' style='color:#20c997'>받은문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/send.docs?currentPage=1' >상신문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/temp.docs?currentPage=1'  >임시저장함</a></div>"
                    <% if(e.getManagerYN().equals("Y")) {%>
                    +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1' >전체문서조회</a></div>"
            		<%}%>
            );
    });
   </script>
        
            
    </body>
</html>