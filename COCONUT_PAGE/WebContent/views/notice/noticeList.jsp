<%@ page import="java.text.SimpleDateFormat, com.kh.notice.model.vo.Notice,
	java.util.ArrayList, com.kh.employee.model.vo.Employee, com.kh.common.model.vo.PageInfo"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%
	int boardNo = (int) request.getAttribute("boardNo");
	String keyword = (String) request.getAttribute("keyword");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	int currentPage = pi.getCurrentPage();
	ArrayList<Notice> noticeList = (ArrayList<Notice>) request.getAttribute("noticeList");
	Employee loginEmployee = (Employee)session.getAttribute("loginEmp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>COCONUT PAGE</title>

<style>
/* 메인 리스트 */
.list {
	width: 80%;
	margin: auto;
}

#list-btn {
	width: 100%;
	padding: 25px 0 15px;
	overflow: hidden;
}

#list-btn>button {
	float: right;
	height: 50px;
	width: 100px;
	background-color: rgb(110, 76, 68);
	color: white;
	border-radius: 5px;
	border: none;
	font-size: 22px;
}
/* 글쓰기 버튼 효과 */
#list-btn>button:hover {
	background-color: transparent;
	color: #594545;
	font-weight: 800;
	font-size: 24px;
	border: 5px solid #594545;
}

#tb {
	width: 100%;
	text-align: center;
	vertical-align: middle;
	background-color: white;
	margin-bottom: 40px;
	table-layout: fixed;
}

#tb>thead {
	background-color: lightgray;
}

#tb>tbody>tr:hover {
	background-color: rgb(240, 240, 240);
	cursor: pointer;
	font-weight: 600;
}

.tbmargin {
	margin-top: 90px;
}

#tb th, #tb td {
	vertical-align: middle;
	padding: 0;
}

#tb th {
	height: 65px;
	font-size: 22px;
	font-weight: 600;
}

#tb td {
	height: 60px;
	font-size: 20px;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.pagination {
	justify-content: center;
}

.pagination a {
	color: rgb(110, 76, 68);
	cursor: pointer;
}

.pagination a:hover {
  color: #3C2317;
  background-color: #fafafa; 
  border-color: #ccc;
}

#active-page {
	background-color: rgb(110, 76, 68);
	color: white;
}


/* 검색조건, 입력, 검색 버튼 */
#search {
	height: 40px;
	margin-top: 30px;
	text-align: center;
}

#search-filter, #search-text, #search-btn {
	height: 100%;
}

#search-filter {
	width: 200px;
}

#search-filter option[value=""] {
	display: none;
}

#search-text {
	width: 500px;
	display: inline;
}

#search-btn {
	width: 80px;
	border: 1px solid rgb(201, 201, 201);
	background-color: rgb(110, 76, 68);
	color: white;
	border-radius: 5px;
	font-size: 18px;
}
#search-btn:hover {
	width: 80px;
	border: 2px solid rgb(201, 201, 201);
	background-color: rgb(110, 76, 68);
	color: white;
	border-radius: 5px;
	font-weight:600;
	font-size: 18px;
	
	
}


/* --------------------------------------------------------------------- */
</style>
</head>
<body>
	<div class="wrap">
		<!-- 헤더  -->
		<%@ include file="../common/header.jsp"%>
		<div class="container">
			<!-- 사이드바 -->
			<%@ include file="../common/sidebar.jsp"%>
			<div class="main">
				<div class="list">
					<%
						if (loginEmployee != null && loginEmployee.getManagerYN().equals("Y")) {
					%>
					<div id="list-btn">
						<button type="button" onclick="location.href='<%=contextPath%>/noList.wt?boardNo=<%=boardNo%>&currentPage=<%=currentPage%>';">글쓰기</button>
					</div>
					<%
						}
					%>
					<table id="tb" class="table">
						<thead>
							<tr>
								<th style="width: 10%">No</th>
								<th style="width: 50%">Title</th>
								<th style="width: 15%">Writer</th>
								<th style="width: 15%">Date</th>
								<th style="width: 10%">Views</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (noticeList.isEmpty()) {
							%>
							<tr>
								<td colspan="5">존재하는 공지사항이 없습니다.</td>
							</tr>
							<%
								} else {							
								for (Notice n : noticeList) {
							%>
							<tr>
								<td><%=n.getNoticeNo()%></td>
								<td><%=n.getNoticeTitle()%></td>
								<td><%=n.getEmpName() %></td>
								<td><%=n.getNoticeDate()%></td>
								<td><%=n.getViewCount()%></td>
							</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
					<ul class="pagination">
						<li class="page-item no-page-prev"><a class="page-link">&lt;</a></li>
						<%
							for (int i = pi.getStartPage(); i <= pi.getEndPage(); i++) {
						%>
						<li class="page-item no-page-btn"><a class="page-link"><%=i%></a></li>
						<%
							}
						%>
						<li class="page-item no-page-next"><a class="page-link">&gt;</a></li>
					</ul>
					<form action="<%=contextPath%>/noList.board" method="get" id="search">
						<input type="hidden" name="boardNo" value="<%=boardNo%>">
						<input type="hidden" name="currentPage" value="1">
						<input type="search" name="keyword" id="search-text" class="form-control" />
						<input type="submit" id="search-btn" value="검색" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		var managerYn = "<%=loginEmployee.getManagerYN()%>";
	  	if(managerYn == "N") {
	  		$("#tb").addClass("tbmargin");
	  	}
	  	$(function() {
          	$("#tb>tbody>tr").click(function() {
          		var nno = $(this).children().eq(0).text();
          		var keyword = "<%=keyword%>";
          		if(keyword == "null") {
          			location.href ="<%=contextPath%>/det.no?nno=" + nno+"&bno=<%=boardNo%>&cup=<%=currentPage%>";
          		}else {
          			location.href ="<%=contextPath%>/det.no?nno=" + nno+"&bno=<%=boardNo%>&cup=<%=currentPage%>&keyword=<%=keyword%>";
          		}
          		
			});
		});
        $(function() {
        	if(<%=currentPage%>==1) {
         		$(".pagination").children().first().addClass("disabled");
			}
			if(<%=currentPage%>==<%=pi.getMaxPage()%>) {
				$(".pagination").children().last().addClass("disabled");
			}
			$(".page-link").each(function() {
          		if ($(this).text() ==<%=currentPage%>) {
          			$(this).attr("id", "active-page");
          			$(this).parent().addClass("disabled");
          		} else {
          			$(this).removeAttr("id", "active-page");
          		}
          	});

			var keyword = "<%=keyword%>";
			var prevNo = <%=currentPage%>-1;
			var nextNo = <%=currentPage%>+1;
			
			if(keyword == "null") {
				$(".no-page-prev>a").click(function() {
					location.href ="<%=contextPath%>/noList.board?boardNo=<%=boardNo%>&currentPage="+prevNo;
				});
				$(".no-page-btn>a").click(function() {
					location.href ="<%=contextPath%>/noList.board?boardNo=<%=boardNo%>&currentPage="+$(this).text();
				});
				$(".no-page-next>a").click(function() {
					location.href ="<%=contextPath%>/noList.board?boardNo=<%=boardNo%>&currentPage="+nextNo;
				});
         	}else {
         		$("#search-text").val(keyword);
				$(".no-page-prev>a").click(function() {
					location.href ="<%=contextPath%>/noList.board?boardNo=<%=boardNo%>&currentPage="+prevNo+"&keyword="+keyword;
				});
				$(".no-page-btn>a").click(function() {
					location.href ="<%=contextPath%>/noList.board?boardNo=<%=boardNo%>&currentPage="+$(this).text()+"&keyword="+keyword;
				});
				$(".no-page-next>a").click(function() {
					location.href ="<%=contextPath%>/noList.board?boardNo=<%=boardNo%>&currentPage="+nextNo+"&keyword="+keyword;					
				});
			}
		});
	</script>
	<script>
	    $(function() {
	              var bno = <%=boardNo%>; 	              
	              if(bno == 1) {
	            	  $("#header_menu").html("<div><a href='<%=contextPath%>/noList.board?boardNo=1&currentPage=1' style='color:#20c997'>공지사항</a></div>");
	              } else {
	            	  $("#header_menu").html("<div><a href='<%=contextPath%>/noList.board?boardNo=2&currentPage=1' style='color:#20c997'>자료실</a></div>");
	              }	            
	    });
	 </script>
</body>
</html>
