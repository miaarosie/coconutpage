<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kh.employee.model.vo.Employee"%>
<%
	int boardNo = (int) request.getAttribute("boardNo");
	Employee loginEmployee = (Employee) session.getAttribute("loginEmp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
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

.content_area {
	margin-top: 15px;
}

.content_area>textarea {
	width: 100%;
	height: 500px;
	border-radius: 5px; 
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
/* 확인 모달 */
#notice-enroll-Modal .modal-content {
	width: 600px;
	height: 400px;
	margin: auto;
}

#notice-enroll-Modal  .modal-body {
	height: 250px;
	width: 600px;
	font-size: 40px;
	font-weight: 400%;
	text-align: center;
	padding-top: 140px;
}

#notice-enroll-Modal .modal-footer {
	border: none;
	text-align: center;
	margin-bottom: 25px;
}

#notice-enroll-Modal  button {
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

#notice-enroll-Modal  button:hover {
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
	margin: 20px 0	;
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
				<form action="<%=contextPath%>/insert.noti" method="post" id="write_form" enctype="multipart/form-data">
					<input type="hidden" name="boardNo" value="<%=boardNo%>">
					<input type="hidden" name="empNo" value="<%=loginEmployee.getEmpNo()%>">
					<div class="write_title">
						<!--제목입력-->
						<input type="text" name="noticeTitle" autocomplete="off" maxlength="50" placeholder="제목을 입력하세요." style="font-weight: 400" required />
					</div>
					<!--파일업로드-->
					<div id="filebox">
						<div>
							<label for="noticeFile"> <img src="resources/images/add-folder.png" />
							</label>
						</div>
						<div>
							<input type="text" value="파일 선택" readonly class="upload-name" /> <input type="file" name="noticeFile" id="noticeFile" />
						</div>
					</div>

					<div class="content_area">
						<textarea name="noticeContent" placeholder="글 내용" maxlength="1000"></textarea>
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
						<button type="button" class="up-btn2" onclick="noModalOpen()">등록</button>
					</div>
					
					
					<!-- The Modal -->
			        <div class="modal" id="notice-enroll-Modal"
			         data-backdrop="static"
            	    data-keyboard="false">
			            <div class="modal-dialog modal-lg">
			                <div class="modal-content">
			                    <!-- Modal body -->
			                    <div class="modal-body">
			               		         등록이 완료되었습니다.
			                    </div>
			                    <!-- Modal footer -->
			                    <div class="modal-footer">
			                        <button type="button" onclick="not_submit()">확인</button>
			                    </div>
			                </div>
			            </div>
			        </div>
				</form>
			</div>
		</div>
	</div>
	 	
	
	<script>
		function noModalOpen() {
			if($(".write_title>input").val().trim() == "") {
				alert("제목은 필수 입력 사항입니다.");
			}else {
				$('#notice-enroll-Modal').modal('show');
			}
		}
		function not_submit() {
			$("#write_form").submit();
		}
		
		function nofileDel() {
			$("#noticeFile").siblings(".upload-name").val("파일 선택");
			$("#filebox>i").remove();   
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
					var filename = $(this)[0].files[0].name;
					$(this).siblings(".upload-name").val(filename);
					$("#filebox").append("<i class='fa fa-times' aria-hidden='true' onclick='nofileDel()'></i>");
                } else {
                	$(this).siblings(".upload-name").val("파일 선택");      
                	$("#filebox>i").remove();   
                }
				if(!$(this).val()) { 
				}else {			
				}
			});
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
