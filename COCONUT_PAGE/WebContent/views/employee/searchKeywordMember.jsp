<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.employee.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>   
<%
PageInfo pi = (PageInfo)(request.getAttribute("pi"));                       
int listCount=pi.getListCount();
int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();
int boardLimit = pi.getBoardLimit();  
ArrayList<Member> optionList=(ArrayList<Member>)request.getAttribute("optionList");
String find = ((String)request.getAttribute("find") == null) ? "" : (String)request.getAttribute("find");
String option = ((String)request.getAttribute("option") == null) ? "" : (String)request.getAttribute("option");
%>
	

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<title>COCONUT PAGE</title>

<style>
#search {
   width: 90%;
   margin : 0 auto 50px;
}

#f, #search-text, #search-btn {
   height: 100%;
}


#f option[value=""] {
   display: none;   
}

#search-text {
   width: 500px;
   display: inline;
   margin-left: 5px;
   margin-top:35px;
}

#search-btn {
   width: 50px;
   height: 40px;
   background-color: rgb(110, 76, 68);
   border: none;
   border-radius: 5px;
   margin-top:35px;
}

#search-btn img {
   width: 90%;
   height: 90%;
}

/* 메인 리스트 */

#select-option {
   float: right;
   height: 70px;
   width: 200px;
   margin-bottom: 10px;
}


#tb th, #tb td {
	vertical-align: middle;
	padding: 0;
}

#tb th {
	height: 65px;
	font-size: 18px;
	font-weight: 500;
}

#tb td {
	height: 70px;
	font-size: 18px;
}

#tb {
   width: 90%;
   height: auto;
   text-align: center;
   vertical-align: middle;
   background-color: white;
   margin : 0 auto 30px;
}

#tb a {
   color: black;
}

#tb>tbody>tr:hover {
   background-color: #f4f4f4;
   cursor: pointer;
}

#tb>thead {
   background-color: rgb(110, 76, 68);
   color: white;
}
.main {
	position: relative;
}

#enroll-btn {
  position: absolute;
  right : 90px;
  bottom: 20px;
   height: 50px;
   width: 125px;
   background-color: rgb(110, 76, 68);
   color: white;
   border-radius: 5px;
   border: none;
  
}
#enroll-btn:hover {
	background-color: #553939;
	color: white;	
   	font-size: 18px;
	font-weight: 500;

}


</style>
</head>
<body>

	<div class="wrap">
    <%@ include file="../common/header.jsp" %>
    
    <div class="container">
        <%@ include file="../common/sidebar.jsp" %>        
			<div class="main">			
				<form action="<%=request.getContextPath()%>/emplist.do?currentPage=1" method="get"  id="search">				
							<select name="find" id="f">
									<option value="EMP_NO"  <%= find.equals("EMP_NO")? "selected" : ""%>>사번</option>
									<option value="EMP_NAME" <%= find.equals("EMP_NAME")? "selected" : ""%>>사원명</option>
									<option value="DEPT_NAME" <%= find.equals("DEPT_NAME")? "selected" : ""%>>부서</option>
									<option value="JOB_NAME" <%= find.equals("JOB_NAME")? "selected" : ""%>>직급명</option>
								
							</select>
							<!-- searchMem?find=empNo&option=a -->
							 <input type="text"
								id="search-text" class="form-control" name="option"
								value="<%=option%>">
								<input type="hidden" name="currentPage" value="1">
						
								<!-- 버튼 아이콘은 예시 -->
							<button type="submit" id="search-btn">
									<img src="resources/images/search-icon.png">
							</button>	
				</form>	
				
				<div class="list">
					<div id="main-list-scroll">
						<table id="tb" class="table">
							<thead>
								<tr>
									<th>순번</th>
									<th>사번</th>
									<th>사원명</th>
									<th>닉네임</th>
									<th>부서</th>
									<th>직급</th>
									<th>이메일</th>
									<th>내선전화</th>
									<th>휴대폰번호</th>
								</tr>
							</thead>
							<tbody>							
							<%
								if (optionList.isEmpty()) {
							%>
							<tr>
								<td colspan="9">존재하는 사원이 없습니다.</td>
							</tr>
						<% } else { 								
							  for(int i=0; i<optionList.size(); i++){ %>
								<tr>
									<td><%= (currentPage - 1) * 10 + i+1 %></td>
									<td><%=optionList.get(i).getEmpNo()%></td>
									<td><%=optionList.get(i).getEmpName()%></td>
									<%String nickName=(optionList.get(i).getNickName()==null) ?"미설정" :optionList.get(i).getNickName(); %>
									<td><%=nickName%></td>
									<td><%=optionList.get(i).getDeptCode()%></td>
									<td><%=optionList.get(i).getJobCode()%></td>
									<td><%=optionList.get(i).getEmail()%></td>
									<td><%=optionList.get(i).getTel()%></td>
									<td><%=optionList.get(i).getPhone()%></td>
								</tr>
							<%}								
							  } %>								
							</tbody>
						</table>
						
												
						<div align="center" class="pagination" style="margin-left:800px;">	
						<% if(currentPage != 1) { %>
							<button onclick="location.href='<%= contextPath %>/emplist.do?currentPage=<%= currentPage-1 %>&find=<%=find %>&option=<%=option %>';">&lt;</button>
						<% } %>
							
						<% for(int pa = startPage; pa<= endPage; pa++) { %>
							<% if(pa != currentPage) { %>
								<button onclick="location.href='<%= contextPath %>/emplist.do?currentPage=<%=pa%>&find=<%=find%>&option=<%=option %>';"><%=pa %></button>
							<% } else { %>
								<button disabled  style="background-color: rgb(110, 76, 68); color : white; border: 1-x rgb(110, 76, 68);"><%=pa%></button>
							<% } %>
						<% } %>
							
						<% if(currentPage != maxPage && maxPage > 0) { %>
							<button onclick="location.href='<%= contextPath %>/emplist.do?currentPage=<%= currentPage +1 %>&find=<%=find%>&option=<%=option%>';">&gt;</button>
						<% } %>
						</div>			
					</div>
				</div>		
				<%if(e.getManagerYN().equals("Y")){%>
					<button type="button" id="enroll-btn" data-toggle="modal"
					data-target="#emp_Insert_Modal" >사원등록</button>
				<%}%>	
			</div>		
	</div>		
	</div>
	<%@ include file="empInsertModal.jsp" %>   					
<script>				
   $(function() {      
      $("#tb>tbody>tr").on("click", function() {
         var empNo = $(this).children().eq(1).text();																																																																								
         location.href = "/COCONUT_PAGE/detailView.me?empNo=" + empNo+"&currentPage=<%=currentPage%>&find=<%=find%>&option=<%=option%>";         
      });           
   });
</script>
		</body>
	</html>