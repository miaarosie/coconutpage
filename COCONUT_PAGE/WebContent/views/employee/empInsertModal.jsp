<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>
        /* approval line Modal */
        #emp_Insert_Modal {
            background-color: rgba(253, 255, 240, 0.7);
        }

        #emp_Insert_Modal .modal-dialog {
            min-width: 1000px;
            max-width: 1000px;
            	margin: 200px auto; 
        }

        #emp_Insert_Modal .modal-content {
            background-color: #6e4c44;
            border-radius: 5px;
        }

        /* 모달 body */
        h2 {
            color: white;
            margin: 40px 0;
        }

        #emp_Insert_Modal .modal-body {
            padding: 0px 40px;
        }

        #emp_Insert_tb {
            color: rgb(253, 255, 240);
            width: 100%;
        }

        #emp_Insert_tb th,
        #emp_Insert_tb td {
            vertical-align: middle;
            padding: 0;
            text-align: justify;
            height: 55px;
        }

        #emp_Insert_tb th {
            font-size: 20px;
            font-weight: 600;
        }

        #emp_Insert_tb tr>th:nth-of-type(1) {
            width: 16%;
        }

        #emp_Insert_tb tr>th:nth-of-type(2) {
            width: 24%;
            padding-left: 30px;
        }

        #emp_Insert_tb td {
            width: 30%;
            font-size: 16px;
            font-weight: 600;
        }

        #emp_Insert_tb td input {
            width: 100%;
            height: 75%;
            padding: 0;
            border: 0;
            border-radius: 5px;
            outline: none;
            padding: 7px 15px;
            box-sizing: border-box;
        }

        #idchk_td>input {
            width: 60% !important;
        }


        #emp_Insert_tb td select {
            -o-appearance: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
        }

        #emp_Insert_tb .sel_dv {
            width: 100%;
            height: 75%;
            background-color: white;
            border-radius: 4px;
            border: none;
        }

        #emp_Insert_tb td select {
            width: 100%;
            height: 100%;
            padding: 5px 30px 5px 10px;
            border: none;
            outline: 0 none;
            background: url('resources/images/selectarrow.png') calc(100% - 5px) center no-repeat;
            background-size: 20px;
        }

        #idchk_td>button {
            float: right;
            width: 35%;
            height: 75%;
            background-color: #f4f4f4;
            color: black;
            border-radius: 5px;
            border: none;
            font-size: 17px;
            font-weight: 500;
            cursor: pointer;
        }

        #jumin_td>input {
            width: 45% !important;
        }

        .hyphen {
            display: inline-block;
            text-align: center;
            width: 10%;
        }

        /* 모달 footer */
        #emp_Insert_Modal .eInsert-footer {
            border: 0;
            overflow: hidden;
            margin: 10px 0 40px;
        }

        #emp_Insert_Modal .eInsert-footer button {
            float: right;
            background-color: #f4f4f4;
            color: black;
            border: none;
            width: 110px;
            height: 45px;
            border-radius: 5px;
            font-size: 17px;
            font-weight: 600;
            margin-left: 20px;
            cursor: pointer;
        }
        #emp_Insert_Modal .eInsert-footer button:hover {
            background-color: #c2c2c2;
            font-size: 19px;
            font-weight: 700;
        }

    </style>
</head>
<body>
	<!-- The Modal -->
    <div class="modal fade" id="emp_Insert_Modal" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal body -->
                <div class="modal-body">
                    <h2 align="center">사원등록</h2>
                    <form id="enroll-form2" action="<%= contextPath %>/emp.up" method="post">
                        <!-- 아이디, 비밀번호, (비밀번호확인), 전화번호, 이메일, 주소, 취미 -->
                        <table class="table table-borderless" id="emp_Insert_tb">
                            <tr>
                                <th>사번</th>
                                <td id="idchk_td">
                                    <input type="number" name="empNo" id="empNo" maxlength="8" placeholder="사번 입력"  required>
                                    <button type="button" onclick="idCheck()" class="check_btn">중복확인</button>
                                </td>
                                <th>사원명</th>
                                <td><input type="text" name="empName" maxlength="15" required placeholder="사원명 입력"></td>
                            </tr>
                            <tr>
                                <th>성별</th>
                                <td>
                                    <div class="sel_dv">
                                        <select name="gender" required>
                                        	<option selected disabled>M / F</option>
                                            <option value="F">F</option>
                                            <option value="M">M</option>
                                        </select>
                                    </div>
                                </td>
                                <th>주민등록번호</th>
                                <td id="jumin_td">
                                    <input type="text" name="empNum1" maxlength="6" required placeholder="생년월일"
                                        size="6"><span class="hyphen">-</span><input type="text" name="empNum2"
                                        maxlength="7" size="6" required>
                                </td>
                            </tr>
                            <tr>
                                <th>부서</th>
                                <td>
                                    <div class="sel_dv">
                                        <select required class="form_td_detail" name="deptCode" onchange="changeDeptCode();">
                                            <option selected disabled>부서 선택</option>
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
                                        </select>
                                    </div>
                                </td>
                                <th>직급</th>
                                <td>
                                    <div class="sel_dv">
                                        <select id="jobCode" name="jobCode" required>
                                            <option selected disabled>직급 선택</option>
                                            <option value="J7">사원</option>
                                            <option value="J6">대리</option>
                                            <option value="J5">과장</option>
                                            <option value="J4">차장</option>
                                            <option value="J3">팀장</option>
                                            <option value="J2">본부장</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td>
                                    <input type="text" name="email" id="email" placeholder="이메일 입력" required>
                                </td>
                                <th>비밀번호</th>
                                <td>
                                    <input type="text" name="empPwd" required placeholder="비밀번호 입력">
                                </td>
                            </tr>
                            <tr>
                                <th>내선번호</th>
                                <td>
                                    <input type="text" name="tel" placeholder="- 포함입력" required>
                                </td>
                                <th>핸드폰</th>
                                <td>
                                    <input type="text" name="phone" placeholder="- 포함입력">
                                </td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td>
                                    <input type="text" name="empAddress" placeholder="주소 입력">
                                </td>
                                <th>입사일</th>
                                <td>
                                    <input type="Date" name="enrollDate" required>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td></td>
                                <th>관리자여부</th>
                                <td>
                                    <div class="sel_dv">
                                        <select id="jobName" name="managerYN">
                                            <option selected>N</option>
                                            <option>Y</option>

                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <div class="eInsert-footer">
		                    <button class="btn btn-secondary" data-dismiss="modal">취소</button>
		                    <button type="submit" onclick="return valichk()" >사원등록</button>    
		                </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
   <script>
    var idchk = 0;
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
    					$("#enroll-form2 button[type=button]").removeAttr("disabled");
    					$(".check_btn").attr("disabled",true);
    					idchk =1;
    				}
    			}
    		},	
    		error:function(){
    			console.log("아이디 중복체크용 ajax 통신 실패!");
    		}
    	});
   }
    $(function() {
  	  $("#enroll-form2 input").keydown(function() {
            if (event.keyCode === 13) {
                event.preventDefault();
                valichk();
            }
        });
  });
    function valichk() {
    	if(idchk == 0) {
    		alert("사번 중복확인을 진행해주세요.");
    		return false;
    	}
    	
    	if($("select[name=gender]").val() == null) {
			alert("성별을 선택해주세요.");
			return false;
		}
    	
    	if($("select[name=deptCode]").val() == null) {
			alert("부서를 선택해주세요.");
			return false;
		}
    	
    	if($("select[name=jobCode]").val() == null) {
			alert("직급을 선택해주세요.");
			return false;
		}
    	
    	if($("input[name=enrollDate]").val() == "") {
			alert("입사일을 선택해주세요.");
			return false;	
		}
    }
    </script>
    
    <script>
	function changeDeptCode() {
    	
    	var $deptCode = $("select[name=deptCode] option:selected");
    	var $email = $("input[name=email]");
    	
    	switch($deptCode.val()) {
    	case "2": $email.val("web"); break;
    	case "16": $email.val("conplan"); break;
    	case "17": $email.val("condedit"); break;
    	case "15": $email.val("service"); break;
    	case "1": $email.val("management"); break;
    	case "14": $email.val("strategy"); break;
    	case "11": $email.val("hr"); break;
    	case "12": $email.val("fi"); break;
    	case "13": $email.val("promo"); break;
    	case "3": $email.val("marketing"); break;
    	case "18": $email.val("perform"); break;
    	case "19": $email.val("crm"); break;
    	case "20": $email.val("conma"); break;
    	case "4": $email.val("sales"); break;
    	case "21": $email.val("partner"); break;
    	case "22": $email.val("page"); break;
    	case "5": $email.val("overseas"); break;
    	case "23": $email.val("asia"); break;
    	case "24": $email.val("ame"); break;
    	case "6": $email.val("online"); break;
    	case "26":$email.val("develop"); break;
    	case "25":$email.val("onma"); break;
    	case "27": $email.val("onplan"); break;
    	}
    }
    </script>

</body>
</html>