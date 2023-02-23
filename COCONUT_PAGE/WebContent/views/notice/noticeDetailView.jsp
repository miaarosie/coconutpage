<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.kh.employee.model.vo.Employee, com.kh.notice.model.vo.Notice, java.text.SimpleDateFormat"%>
<%
	Notice n = (Notice) request.getAttribute("n");
	Employee loginEmployee = (Employee) session.getAttribute("loginEmp");
	String keyword = (String) request.getAttribute("keyword");
	int currentPage =  (int) request.getAttribute("currentPage");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>
/* header 가운데 메뉴바 */

/* --------------------------- */
/* 공지사항 글 상세 조회 페이지 div */
.noview-area {
	border-radius: 7px;
	padding: 50px 100px;
	width: 1000px;
	/*	height: 700px; */
	margin: 90px auto;
	background-color: rgb(244, 244, 244);
}

.noview-area>* {
	width: 100%;
}

.noview-area>div:nth-child(1) {
	overflow: hidden;
	margin-bottom: 40px;
}

.noview-area>div>input {
	background-color: rgb(110, 76, 68);
	color: white;
	width: 80px;
	height: 47px;
	line-height: 43px;
	text-align: center;
	border: none;
	font-size: 18pt;
	font-weight:500;
	border-radius: 4px;
}
.noview-area>div>input:hover {
	background-color: #553939;
	color: white;
	font-size: 19pt;
	font-weight:600;
}


.noview-area>h2 {
	margin-bottom: 35px;
}

.noview-span-area {
	overflow: hidden;
	padding: 0 10px;
	margin-bottom: 10px;
}

.noview-span-area>* {
	box-sizing: border-box;
	display: inline-block;
	line-height: 40px;
	font-size: 18px;
}

.noview-span-area>span:nth-child(1) {
	margin-right: 70px;
}
.noview-span-area>span:nth-child(3) {
	float: right;
}

.noview-area>textarea {
	background-color: white;
	height: 430px;
	margin-bottom: 20px;
	padding: 10px;
	border-radius: 10px;
	border: 3px solid rgb(244, 244, 244);
	font-size: 22px;
	font-weight: 500;
	
	outline: none;
	resize: none;
}

.file_download {
	height: 70px;
	border: solid 1px rgb(175, 175, 175);
	border-radius: 10px;
	background-color: white;
	padding: 0 15px;
	overflow: hidden;
	position: relative;
}

.file_download>span {
		font-weight: 600;
		font-size: 20px;
		line-height: 70px;
}

.file_download>span:nth-child(1) {
	margin-right: 20px;
}

.file_download>a {
	position: absolute;
	top: 50%;
	right: 15px;
	transform: translate(0, -50%);
	width: 110px;
	height: 50px;
	line-height: 50px;
	text-align: center;
	background-color: rgb(110, 76, 68); 
	color: white;
	border: none;
	border-radius: 10px;
	font-size: 20px;
	font-weight: 600;
}

.file_download>a:hover {
	background-color: #1C6758;
	color: #FFF9B0;	
	text-decoration: none;
	font-size: 23px;
	font-weight: 700;
}



/* --------------------------------------- */
/* 삭제 모달 */
#notice-delete-Modal .modal-content {
	width: 600px;
	height: 400px;
	margin: auto;
}

#notice-delete-Modal .modal-body {
	height: 250px;
	width: 600px;
	font-size: 40px;
	font-weight: 400%;
	text-align: center;
	padding-top: 140px;
}

#notice-delete-Modal .modal-footer {
	border: none;
	text-align: center;
	margin-bottom: 25px;
}

#notice-delete-Modal button {
	background-color: rgb(110, 76, 68);
	color: white;
	width: 85px;
	height: 60px;
	line-height: 58px;
	text-align: center;
	font-size: 25px;
	font-weight: 500;
	margin: 0 10px;
	border-radius: 5px;
	border: none;
}


#notice-delete-Modal button:hover {
	background-color: #553939;
	color: white;
	font-size: 26px;
	font-weight:600;
}

/* -------------------------------------- */
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
				<div class="noview-area">
					<div>
						<input type="button" value="목록" style="float: left"
							onclick="location.href='<%= contextPath %>/noList.board?boardNo=<%=n.getNoticeType() %>&currentPage=<%=currentPage%>&keyword=<%=keyword%>';">

						<%
							if (loginEmployee != null && (loginEmployee.getEmpNo() == n.getEmpNo())) {
						%>
						<input type="button" value="삭제"
							style="float: right; margin-left: 10px;" data-toggle="modal"
							data-target="#notice-delete-Modal"> <input type="button"
							value="수정" style="float: right"
							onclick="location.href='<%=contextPath%>/updateForm.no?nno=<%=n.getNoticeNo()%>'">
						<%
							}
						%>
					</div>

					<h2><%=n.getNoticeTitle()%></h2>
					<div class="noview-span-area">
						<span><%=n.getEmpName()%></span>
						<span><%=n.getNoticeDate()%></span>
						<span>조회&nbsp;&nbsp;&nbsp;<%=n.getViewCount()%></span>
					</div>
					<%
						if (n.getNoticeContent() == null) {
					%>
					<textarea disabled></textarea>
					<%
						} else {
					%>
					<textarea disabled><%=n.getNoticeContent()%></textarea>
					<%
						}
					%>
					<div class="file_download">
						<span>첨부파일</span>

						<%
							if (n.getOriginFile() == null) {
						%>
						<span>파일 없음</span>
						<%
							} else {
						%>
						<span><%=n.getOriginFile()%></span>
						<a download="<%=n.getOriginFile()%>"
							href="<%=contextPath%>/resources/notice_upfiles/<%=n.getChangeFile()%>">다운로드</a>
						<%
							}
						%>
					</div>
				</div>
			</div>

			<!-- The Modal -->
			<div class="modal" id="notice-delete-Modal">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<!-- Modal body -->
						<div class="modal-body">공지사항을 삭제하시겠습니까?</div>
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button"
								onclick="location.href='<%=contextPath%>/del.no?nno=<%=n.getNoticeNo()%>'">확인</button>
							<button type="button" data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	    $(function() {       
	            var bno = <%=n.getNoticeType()%>; 
	              
	              if(bno == 1) {
	            	   $("#header_menu").html("<div><a href='<%=contextPath%>/noList.board?boardNo=1&currentPage=1' style='color:#20c997'>공지사항</a></div>");
	              } else {
	            	   $("#header_menu").html("<div><a href='<%=contextPath%>/noList.board?boardNo=2&currentPage=1' style='color:#20c997'>자료실</a></div>");
	              }	 
	            
	    });
	    
	    
	 </script>
	
</body>
</html>