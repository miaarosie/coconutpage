<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.common.model.vo.PageInfo, java.util.ArrayList, com.kh.post.model.vo.Post1" %>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Post1> list = (ArrayList<Post1>)request.getAttribute("list");
	String keyword = (String)request.getAttribute("keyword");
	String searchword = (String)request.getAttribute("searchword");
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>
	
	#searchform {
	margin-top : 20px;
	}
	
    #keyword { width: 100px; }
    
    #searchword {
        width: 300px;
        height : 37px;
        border : 1px solid lightgray;
        border-radius : 5px;
        margin-left : 5px;
    }

    #search-btn { 
        width: 70px; 
        height :  37px;
		border : none;
        background-color: rgb(110, 76, 68);
        color : white;
        border-radius: 5px;
		margin-left: 5px;
    }

	/* 게시판리스트*/

	.list {
        width : 80%;
        margin : auto;
		margin-top : 70px;
    }
    #list-btn {
        width: 100%;
        padding: 10px;
		line-height: 350%;
		text-align: center;
    }
	#list-btn>a {
        float: right;
        height: 50px;
        width: 100px;
        background-color: rgb(110, 76, 68);
        color: white;
        border-radius: 5px;
        border: none;
        margin-bottom: 30px;
        font-size: 25px;
		text-decoration: none;
    }

    #tb {
        width: 100%;
        text-align: center;
        vertical-align: middle;
        background-color: white;
        margin-bottom: 30px;
    }
        
    #tb a { color: black; }

	#tb thead tr{ height : 50px;}
        
    #tb>thead { background-color: lightgray; }
    
	#tb>tbody>tr>td :hover{
		text-decoration: underline;
		cursor: pointer;
	}
    .pagination {
        width : 300px;
        margin : auto;
		margin-top : 50px;
    }
    .pagination button {
        background-color: lightgray;
        color: black;
		margin : 5px;
		border : 1px solid lightgray;
    }

</style>
</head>
<body>
	<div class="wrap">
		<%@ include file="../common/header.jsp" %>
		
		<div class="container">
			<%@ include file="../common/sidebar.jsp" %>
			<div class="main">
				<div class="list">
					<% if(e != null) { %>
						<div id="list-btn">
							<a href="<%= contextPath %>/wagleEnrollForm.po" id="write_btn">글쓰기</a>
						</div>
					<% } %>	
					<table align="center" id="tb" class="table">
						<thead>
							<th width="10%;">No</th>
							<th width="50%;">Title</th>
							<th width="20%;">Writer</th>
							<th width="20%;">Date</th>
						</thead>
		
						<tbody>
							<% if(list.isEmpty())  { %>
								<tr>
								<td colspan="100%">조회된 게시글이 없습니다.</td>
								</tr>
							<% } else { %>
								<% for(Post1 p : list) { %>
									<tr>
									<input type="hidden" value="<%= p.getPostNo() %>">
										<td><%= p.getPostNo() %></td>
										<td><a><%= p.getPostTitle() %></a></td>
										<td><%= p.getEmpNo() %></td>
										<td><%= p.getRegDate() %></td>
									</tr>
								<% }  %>
							<% }  %>
						</tbody>
					</table>
					
					<script>
						$(function() {
							$(".table>tbody>tr").click(function() {
								location.href="<%= contextPath %>/wagleDetail.po?pno="+ $(this).children().eq(0).val();
							});
						});	
					</script>
					
	
					<div id="page">
						<div align="center" class="pagination">
							
						<% if(currentPage != 1) { %>
							<button onclick="location.href='<%= contextPath %>/wagleList.po?currentPage=<%= currentPage-1 %>&searchword=<%=searchword %>&keyword=<%=keyword %>';">&lt;</button>
						<% } %>
							
						<% for(int pa = startPage; pa<= endPage; pa++) { %>
							<% if(pa != currentPage) { %>
								<button onclick="location.href='<%= contextPath %>/wagleList.po?currentPage=<%= pa %>&searchword=<%=searchword %>&keyword=<%=keyword %>';"><%= pa %></button>
							<% } else { %>
								<button disabled  style="background-color: rgb(110, 76, 68); color : white; border: 1-x rgb(110, 76, 68);"><%= pa%></button>
							<% } %>
						<% } %>
							
						<% if(currentPage != maxPage && maxPage != 0) { %>
						<button onclick="location.href='<%= contextPath %>/wagleList.po?currentPage=<%= currentPage +1 %>&searchword=<%=searchword %>&keyword=<%=keyword %>';">&gt;</button>
						<% } %>
						</div>
					</div>	
					<div align="center">
						<form action="<%= contextPath %>/wagleList.po?currentPage=1" method="get" id="searchform">
							<table>
								<tr>
									<td>
										<select class="form-control" name="keyword" id="keyword">
											<option value="">선택</option>
											<option value="title">제목</option>
											<option value="writer">작성자</option>
										</select>
									</td>
									<td><input type="text" id="searchword" name="searchword"placeholder="검색어를 입력하세요."></td>
									<td><button type="submit" class="form-control" id="search-btn">검색</button></td>
									<input type="hidden" name="currentPage" value="1">
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<script>
    $(function() {
       $("#header_menu").html("<div><a href='<%=contextPath%>/wagleList.po?currentPage=1' style='color:#20c997'>코코넛 와글와글</a></div>"+
                               "<div><a href='<%=contextPath%>/thingsList.po?currentPage=1'>경조사</a></div>"+
                               "<div><a href='<%=contextPath%>/list.share?currentPage=1'>나눔해요</a></div>");
	});
 </script>
	
</body>
</html>