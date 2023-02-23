<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.employee.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>
<%

//PageInfo pi = (PageInfo)(request.getAttribute("pi"));
//페이징바 
ArrayList<Member> empList= (ArrayList<Member>)request.getAttribute("empList");

/* int listCount;
int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();
int empLimit = pi.getEmpLimit(); */

String find = ((String)request.getAttribute("find") == null) ? "" : (String)request.getAttribute("find");
String option = ((String)request.getAttribute("option") == null) ? "" : (String)request.getAttribute("option");
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">

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
/* 검색조건, 입력, 검색 버튼 */
#search {
	height: 50px;
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
	margin-left: 5px;
	margin-top: 35px;
}

#search-btn {
	width: 50px;
	height: 40px;
	background-color: rgb(110, 76, 68);
	border: none;
	border-radius: 5px;
	margin-top: 35px;
}

#search-btn img {
	width: 90%;
	height: 90%;
}

/* 메인 리스트 */
#list-header {
	height: 70px;
}

#select-option {
	float: right;
	height: 70px;
	width: 200px;
	margin-bottom: 10px;
}

#main-list-scroll {
	height: 650px;
	overflow-y: scroll;
	
}

#main-list-scroll::-webkit-scrollbar {
	display:none;
	
	
}


#scroll-sticky {
	position: sticky;
	top: 0;
	overflow: scroll;
	
	
}



#tb {
	width: 90%;
	height: auto;
	text-align: center;
	vertical-align: middle;
	background-color: white;
	margin-bottom: 30px;
	margin-left: 120px;
}

#tb a {
	color: black;
}

#tb td {
	height: auto;
}

#tb>tbody>tr:hover {
	background-color: #f4f4f4;
	cursor: pointer;
}

#tb>thead {
	background-color: rgb(110, 76, 68);
	color: white;
}

#enroll-btn {
	float: right;
	height: 50px;
	width: 125px;
	background-color: rgb(110, 76, 68);
	color: white;
	border-radius: 5px;
	border: none;
}

.outer {
	background-color: rgb(110, 76, 68);
	color: rgb(253, 255, 240);
	width: 1000px;
	margin: auto;
	margin-top: 50px;
	border-radius: 20px;
}

#enroll-form table {
	margin: auto;
}

#enroll-form2 table {
	margin: auto;
}

#enroll-form input {
	margin: 5px;
}

#enroll-form2 input {
	margin: 5px;
}

.check_btn {
	background-color: rgb(253, 255, 240);
	border-radius: 2px;
	color: rgb(110, 76, 68);
	border: transparent
}

.btn-primary {
	margin-left: 3000px;
	margin-top: 20px;
}
</style>
<body>



	<div class="wrap">
		<%@ include file="../common/header.jsp"%>

		<div class="container">
			<%@ include file="../common/sidebar.jsp"%>
		<%-- <script>
    	var msg = "<%=alertMsg%>";
            
            if (msg != "null") {
                alert(msg);
            
                <% session.removeAttribute("alertMsg"); %>
            }
            
            </script> --%>


			<div class="main">
				<form action="<%= request.getContextPath()%>/emplist.do"
					method="get" id="search">

					<!-- <input type="hidden" name="currentPage" value="1"> -->

					<table>
						<tr>
							<td><select style="margin-left: 120px;" name="find">
									<option value="EMP_NO">사번</option>
									<option value="EMP_NAME">사원명</option>
									<option value="DEPT_NAME">부서</option>
									<option value="JOB_NAME">직급명</option>
									<option value="NICK_NAME">닉네임</option>
							</select> <!-- searchMem?find=empNo&option=a --> <input type="text"
								id="search-text" class="form-control" placeholder="검색"
								name="option" value="<%= option %>">
							<td>
								<!-- 버튼 아이콘은 예시 -->
							<td><button type="submit" id="search-btn">
									<img src="resources/images/info.png">
								</button></td>
						</tr>
					</table>
				</form>

				<script>
                 		$(function() {
                 			
                 			$("#search option").each(function() {
                 				
                 				if($(this).text() == "<%= find %>") {
                 					
                 					$(this).attr("selected", true);
                 				}
                 			});
                 		}); 
                  </script>

				<div class="list">

					<div id="list-header"></div>

					<div id="main-list-scroll">

						<table id="tb" class="table" style="height: 30px;">
							<thead id="scroll-sticky">
								<th>순번</th>
								<th>사번</th>
								<th>사원명</th>
								<th>닉네임</th>
								<th>부서</th>
								<th>직급</th>
								<th>이메일</th>
								<th>내선전화</th>
								<th>휴대폰번호</th>
								<th>재직여부</th>
							</thead>
							<tbody>

								<% for(int i=0; i<empList.size(); i++){%>

								<tr>
									<td><%=i+1%></td>
									<td><%=empList.get(i).getEmpNo()%></td>
									<td><%=empList.get(i).getEmpName()%></td>
									<%String nickName= (empList.get(i).getNickName()==null) ?"미설정":empList.get(i).getNickName();%>
									<td><%=nickName%></td>
									<td><%=empList.get(i).getDeptCode()%></td>
									<td><%=empList.get(i).getJobCode()%></td>
									<td><%=empList.get(i).getEmail()%></td>
									<td><%=empList.get(i).getTel()%></td>
									<td><%=empList.get(i).getPhone()%></td>
									<%String inOfficeYN=(empList.get(i).getLeaveDate()==null) ?"Y":"N";%>
									<td><%=inOfficeYN%></td>
								</tr>
							</tbody>
							<%}%>

						</table>



					</div>

				</div>


				<div class="container">

					<%if(e.getManagerYN().equals("Y")){%>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#myModal" style="margin-left: 200px;">사원등록</button>
						<%}%>

					<!-- The Modal -->
					<div class="modal fade" id="myModal">
						<div class="modal-dialog modal-xl">
							<div class="modal-content">

								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title"></h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<!-- Modal body -->
								<div class="modal-body">
									<div class="outer">

										<br>
										<h2 align="center">사원등록</h2>

										<!-- 
			현재 나의 주소 : http://localhost:8888/jsp/enrollForm.me
			내가 요청을 보내고자 하는 주소 : http://localhost:8888/jsp/insert.me
			
			절대경로 : /jsp/insert.me
			상대경로 : insert.me
		-->

										<form id="enroll-form2" action="<%= contextPath %>/emp.up"
											method="post">

											<!-- 아이디, 비밀번호, (비밀번호확인), 전화번호, 이메일, 주소, 취미 -->
											<table>
												<!-- (tr>td*3)*8 + Enter -->
												<tr>
													<td>사번</td>
													<td><input type="number" name="empNo" id="empNo" maxlength="8"
														required></td>
													<td><button type="button" onclick="idCheck()"
															class="check_btn">중복확인</button></td>

												</tr>
												<tr>
													<td>사원명</td>
													<td><input type="text" name="empName" maxlength="15"
														required></td>
													<td></td>
												</tr>
												<tr>
													<td>성별</td>
													<td><select name="gender">
															<option value="F">F</option>
															<option value="M">M</option>
													</select></td>
													<td></td>
												</tr>
												<tr>
													<td>주민번호</td>
													<td><input type="text" name="empNum" maxlength="6"
														required placeholder="생년월일" size="6"><span class="hyphen">-</span><input type="text" name="empNum" maxlength="7" size="6"
														required ></td>
													<td></td>
												</tr>
												<tr>
													<td>부서</td>
													<td><select required class="form_td_detail"
														name="deptCode">
															<option selected disabled>부서 입력</option>
															<option value="2">웹콘텐츠 본부</option>
															<option value="16">콘텐츠 기획팀</option>
															<option value="17">콘텐츠 편집팀</option>
															<option value="15">고객서비스팀</option>
															<option value="1">경영본부</option>
															<option value="14">전략기획팀</option>
															<option value="11">인사팀</option>
															<option value="12">회계팀</option>
															<option value="13">홍보팀</option>
															<option value="3">마케팅 본부</option>
															<option value="18">퍼포먼스 마케팅팀</option>
															<option value="19">CRM 마케팅팀</option>
															<option value="20">콘텐츠 마케팅팀</option>
															<option value="4">영업본부</option>
															<option value="21">제휴 영업팀</option>
															<option value="22">페이지 영업팀</option>
															<option value="5">해외 본부</option>
															<option value="23">아시아 영업팀</option>
															<option value="24">미주영업팀</option>
															<option value="6">온라인 본부</option>
															<option value="26">온라인 개발팀</option>
															<option value="25">온라인 마케팅팀</option>
															<option value="27">온라인 기획팀</option>
													</select></td>
												</tr>
												
												
												
												
												<tr>
													<td>직급</td>
													<td><select id="jobCode" name="jobCode">
															<option selected disabled>직급 입력</option>
															<option value="J7">사원</option>
															<option value="J6">대리</option>
															<option value="J5">과장</option>
															<option value="J4">차장</option>
															<option value="J3">팀장</option>
															<option value="J2">본부장</option>
													</select></td>
												</tr>
												<tr>
													<td>이메일</td>
													<td><input type="text" name="email" placeholder=></td>
													<td></td>
												</tr>
												
									
												<tr>
													<td>비밀번호</td>
													<td><input type="text" name="empPwd"></td>
													<td></td>
												</tr>
												<tr>
													<td>내선번호</td>
													<td><input type="text" name="tel" placeholder="-포함입력"></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td>휴대폰</td>
													<td><input type="text" name="phone"
														onclick="phoneCheck()" placeholder="-포함입력"></td>
													<td></td>
												</tr>
												<tr>
													<td>주소</td>
													<td><input type="text" name="empAddress"></td>
												</tr>

												<tr>
													<td>관리자여부</td>
													<td><select id="jobName" name="managerYN">
															<option selected disabled>N</option>
															<option>Y</option>

													</select></td>
												</tr>
												<tr>
													<td>입사일</td>
													<td><input type="Date" name="enrollDate"></td>
												</tr>

											</table>
											<br> <br>
											<!-- Modal footer -->
											<div class="modal-footer">

												<button type="submit" class="enroll_btn">등록</button>
												<button type="submit" class="btn btn-secondary"
													data-dismiss="modal">취소</button>
											</div>

											<br> <br>
										</form>
									</div>


								</div>

							</div>
						</div>
					</div>

					<script>
					
		function idCheck(){
			//사번을 입력하는 input 요소 객체
			
			var $empNo=$("#enroll-form2 input[name=empNo]");
			
			//name 속성이 empNo인 요소
			$.ajax({
				
				url:"check.me",
				data :{checkEmpNo:$("#empNo").val()},
				success:function(result){
					console.log(result);
	    			if(result=="NNNNN"){//사용불가
	    				alert("이미 존재하는 사번입니다.");
	    			$empNo.focus();//재입력 유도
	    			
	    			}else{
	    				if(confirm("사용가능한 사번입니다.등록하시겠습니까?")){
	    					$empNo.attr("readonly",true);
	    					$("#enroll-form2-button[type=button]").removeAttr("disabled")
	    				}
	    			}
	    		},	
	    		error:function(){
	    			console.log("아이디 중복체크용 ajax 통신 실패!");
	    		}
	    	});
	    	}
		</script>
		
		

		   <script>
	
           $(function() {
              
              $("#tb>tbody>tr").on("click", function() {
     
                 var empNo = $(this).children().eq(1).text();
     																																																																																														
                 location.href = "/COCONUT_PAGE/detailView.me?empNo=" + empNo;
                 
              });           
           });
        </script>
</body>