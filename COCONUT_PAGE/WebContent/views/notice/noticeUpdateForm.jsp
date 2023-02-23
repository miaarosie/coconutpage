<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.employee.model.vo.Employee, com.kh.notice.model.vo.Notice"%>
<%
	Notice n = (Notice)request.getAttribute("n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>
/* header 가운데 메뉴바 */

/* ---------------------------------------- */
/* 메인 */

#write_form {
	border-radius: 7px;
	padding: 40px 100px;
	width: 1200px;
	margin: 50px auto;
	background-color: rgb(244, 244, 244);
}

#write_form>div {
	width: 100%;
}

.write_title {
	border-bottom: 1px solid #d2d2d2;
	margin-bottom:15px;
}

.write_title>input {
	width: 100%;
	height: 60px;
	outline: none;
	border: none;
	font-size: 30px;
	padding-left: 10px;
	line-height: 60px;
	background-color: rgb(244, 244, 244);
}

.content_area>textarea {
	width: 100%;
	height: 500px;
	border-radius: 5px;
	margin-top: 15px;
	font-size: 20px;
	border: solid 2px rgb(201, 201, 201);
	outline: none;
	padding: 10px;
	resize: none;
}

#count {
 	text-align: right;
}
#count {
  font-size:0.9rem;
}

.upload_btn {
	overflow: hidden;
	margin: 20px 0 10px;
}

.upload_btn>button {
	background-color: rgb(110, 76, 68);
	color: white;
	width: 100px;
	height: 60px;
	line-height: 55px;
	text-align: center;
	border: solid 1px white;
	font-size: 20pt;
	font-weight:500;
	border-radius: 10px;
	cursor: pointer;
	float: right;
	margin-left: 15px;
}
.upload_btn>button:hover {
	background-color: #553939;
	color: white;
	font-size: 22pt;
	font-weight:600;
}

/* -------------------------------------- */
/* 수정 모달 */
#notice-update-Modal .modal-content {
	width: 600px;
	height: 400px;
	margin: auto;
}

#notice-update-Modal  .modal-body {
	height: 250px;
	width: 600px;
	font-size: 40px;
	font-weight: 400%;
	text-align: center;
	padding-top: 140px;
}

#notice-update-Modal .modal-footer {
	border: none;
	text-align: center;
	margin-bottom: 25px;
}


#notice-update-Modal  button {
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
#notice-update-Modal  button:hover {
	background-color: #553939;
	color: white;
	font-size: 26px;
	font-weight:600;
}



/* -------------------------------------- */
/* 파일박스*/
#filebox * {
	box-sizing: border-box;
}

#filebox {
	overflow: hidden;
	margin-top: 10px;
	position: relative;
}

#filebox>div {
	float: left;
}

#filebox>div:nth-child(1) {
	width: 5%;
}

#filebox>div:nth-child(2) {
	width: 95%;
}

#filebox label {
	margin: 0;
}

#filebox img {
	width: 100%;
	cursor: pointer;
}

#filebox input[type="text"] {
	width: 100%;
	height: 56px;
	font-size: 20px;
	font-weight: 500;
	padding-left: 15px;
	border: none;
	cursor: pointer;
	outline: none;
	background-color: rgb(244, 244, 244);
}

#filebox i {
	position: absolute;
	right: 20px;
	top : 16px;
    font-size: 25px;
    color: black;
    cursor: pointer;
}

#noticeFile {
	display: none;
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
				<form action="<%=contextPath%>/update.no" method="post" id="write_form" enctype="multipart/form-data">
					<input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">
					<div class="write_title">
						<!--제목입력-->
						<input type="text" name="noticeTitle" autocomplete="off" maxlength="50" placeholder="제목을 입력하세요." required style="font-weight: 400"
						value="<%=n.getNoticeTitle() %>" />
					</div>
					<!--파일업로드-->
					<div id="filebox">
						<div>
							<label for="noticeFile"> <img src="resources/images/add-folder.png" />
							</label>
						</div>
						<div>
					<% if(n.getOriginFile() != null) { %>
							<input type="text" value="<%=n.getOriginFile() %>" readonly class="upload-name" />
							<input type="hidden" name="changeFileName" value="<%= n.getChangeFile() %>">
							<i class='fa fa-times' aria-hidden='true' onclick='orinofileDel()'></i>
					<% } else {	%>
							<input type="text" value="파일" readonly class="upload-name" />
					<% } %>		
							 <input type="file" name="noticeFile" id="noticeFile" />
							 <input type="hidden" name="oriFileChk" id="oriFileChk" value="0">
						</div>
					</div>
					<div class="content_area">
						<textarea name="noticeContent" maxlength="1000" placeholder="글 내용"><%=n.getNoticeContent() %></textarea>
					</div>
					<div id="count">
						 <span>0</span> / 1000
					</div>
					<script>
						$(function() {
							$('.content_area>textarea').keyup(function () {		
					            $("#count>span").text($(this).val().length);  			
							});
						});
					</script>
					<div class="upload_btn">
						<button type="button" class="up-btn1" onclick="history.back();">취소</button>
						<button type="button" class="up-btn2" onclick="noModalOpen()" >수정</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	 <!-- The Modal -->
        <div class="modal" id="notice-update-Modal">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <!-- Modal body -->
                    <div class="modal-body">
                        수정이 완료되었습니다.
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" onclick="not_submit()">확인</button>
                    </div>
                </div>
            </div>
        </div>
	
	<script>
		var oriFileChk = 0;
		var oriFileName = "<%=n.getOriginFile()%>";
		
		function noModalOpen() {
			if($(".write_title>input").val().trim() == "") {
				alert("제목은 필수 입력 사항입니다.");
			}else {
				$('#notice-update-Modal').modal('show');
			}
		}
		function not_submit() {
			$("#write_form").submit();
		}
		
		function orinofileDel() {
			oriFileChk = 1;
			$("#oriFileChk").val(1);
			$("#noticeFile").siblings(".upload-name").val("파일 선택");
			$("#filebox i").remove();   
			
		}
		
		function nofileDel() {
			$("#filebox>i").remove();   
			if(oriFileChk == 1) { //기존파일 있었는데 이미 삭제버튼눌러서 지움
				$("#noticeFile").siblings(".upload-name").val("파일 선택");
			} else if(oriFileName != "null" && oriFileChk == 0) {  //기존파일 있었는데  지우지 않음
				$("#noticeFile").siblings(".upload-name").val(oriFileName);
        		$("#filebox").append("<i class='fa fa-times' aria-hidden='true' onclick='orinofileDel()'></i>");
				
			} else {
				$("#noticeFile").siblings(".upload-name").val("파일 선택");
			}
						
			var agent = navigator.userAgent.toLowerCase();
			//파일초기화 크로스 브라우징 
			if ((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) { 
				   $("#noticeFile").replaceWith($("#noticeFile").clone(true));
			} else{ //크롬 파폭 
				   $("#noticeFile").val("");
			}
		}
	
		$(function() {
			$(".upload-name").click(function() {
				$("#noticeFile").click();
			});

			$("#noticeFile").on("change", function() {
				if($(this)[0].files.length == 1) {
					if(oriFileName != "null" && oriFileChk == 0) { //기존파일 있었는데 삭제버튼 안누름
						$("#filebox>i").remove();   // 기존x버튼을 지움 기존파일을 삭제했다고 생각하는건 아니다.
					}
					var filename = $(this)[0].files[0].name;
					$(this).siblings(".upload-name").val(filename);
					$("#filebox").append("<i class='fa fa-times' aria-hidden='true' onclick='nofileDel()'></i>");
                } else { 
                	$("#filebox>i").remove(); 
                	// 기존 파일 있었는데 지운경우
                	if(oriFileChk == 1) { 
                		$(this).siblings(".upload-name").val("파일 선택");   
                	}else if(oriFileName != "null" && oriFileChk == 0) {   // 기존 파일 있었는데 삭제 안함
                		$(this).siblings(".upload-name").val(oriFileName);
                		$("#filebox").append("<i class='fa fa-times' aria-hidden='true' onclick='orinofileDel()'></i>");
                	} else { //기존 파일 없었다.
                		$(this).siblings(".upload-name").val("파일 선택");
                	}
                }
				if(!$(this).val()) { 
				}else {			
				}
			});
		});
		
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
