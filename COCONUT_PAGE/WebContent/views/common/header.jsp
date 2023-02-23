<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.kh.employee.model.vo.Employee" %>
<%
	Employee e = (Employee)session.getAttribute("loginEmp");


	String alertMsg = (String)session.getAttribute("alertMsg");
	
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
  <title>COCONUT PAGE</title>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script> <!-- 온라인 방식 -->

  <!-- Latest compiled and minified CSS -->
  <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
  />

  <!-- Popper JS -->
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

  <!-- Latest compiled JavaScript -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

  <!-- font awesome icon css -->
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
  />
  
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap" rel="stylesheet">
  
  <style>
    body {
      background-color: rgb(253, 255, 240);
      font-family: 'IBM Plex Sans KR', sans-serif;
    }
    .wrap {
        
      margin : auto;
      width : 1920px;
      /* height : 1080px; */
      /* border: 1px solid black; */
        
    }
  	.main {
       float : left;
       width : 1820px;

        }
    
    .header {
      height: 100px;
      width : 100%; 
      border-bottom : 1px solid black;     
      box-sizing: border-box;      
    }


    #logo {
      width : 100px;
      height : 100px;

    }

  #coconut_page {
    color: rgb(110, 76, 68);
    font-weight: bolder;
    font-size: 40px;
    width : 300px;
    height : 100%;
    padding-top : 20px;
    vertical-align: middle;
    
    
  }
    
  .header>div {
    float : left;
  }
  
          #header_menu {
            font-weight: 600;
            font-size: 30px;
            width: 300px;
            height: 100%;
            padding-top: 25px;
            text-align: center;
            
            text-decoration: none;
        }

        #header_menu a {
            color:   rgb(110, 76, 68);
            text-decoration: none;
            text-align: center;
            margin:auto;
            font-weight : bolder;
            margin-right : 5%;
            display: inline-block;

        }

  /* --------------------------------------------------------------------- */
  /* 버튼색깔 */

  .btn-primary {
    background-color: rgb(110, 76, 68);
    border : none;
  }
    

  /* --------------------------------------------------------------------- */
  /* header 가운데 메뉴바 */

  #header_menu {
    margin:auto;
    
  }
  #header_menu div {
    float:left;
    color: rgb(110, 76, 68);
    font-weight: 400;
    font-size: 30px;
    text-align: center;
    margin:auto;
    padding-top : 20px;
    text-align: center;
    padding-right : 100px;
    display : contents;
    
  }
  



  /* --------------------------------------------------------------------- */
  /* header 우측 */
    
  #profile, #logout {
    height : 100px;
    /* border : 1px solid black; */
    box-sizing: border-box;
  }

  #logout {
    width : 170px;
    text-align: center;
    padding-top :30px

  }


    #profile {
    width: 100px;
    position: relative;
  }

  #profile > img {
    position: absolute;
    margin: auto;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgb(110, 76, 68);
    width: 75%;
    height:75%;
    cursor: pointer;
  }

  /* prfofile Modal */

  #profile-Modal {
    background-color: rgba(253, 255, 240, 0.7);
    top :0;
    bottom :0;
    left : 0;
    right :0;
  }

  #profile-Modal .modal-dialog,
  #profile-modify-Modal .modal-dialog {
    min-width: 600px;
    max-width: 800px;
  }

  #profile-password-Modal .modal-dialog {
    min-width: 500px;
    max-width: 500px;
    margin-top: 100px;
  }

  #profile-Modal .modal-content,
  #profile-modify-Modal .modal-content {
    background-color: #6e4c44;
    position: relative;
    border-radius: 20px;
  }
  #profile-Modal .modal-content > i,
  #profile-modify-Modal .modal-content > i {
    position: absolute;
    margin: auto;
    right: 15px;
    top: 10px;
    color: #fdfff0;
    font-size: 35px;
    cursor: pointer;
  }
  #profile-password-Modal .modal-content {
    background-color: #fdfff0;
    position: relative;
    border-radius: 20px;
  }

  #profile-password-Modal .modal-content > i {
    position: absolute;
    margin: auto;
    right: 15px;
    top: 10px;
    color: #6e4c44;
    font-size: 25px;
    cursor: pointer;
  }

  /* 모달 header */

  #profile-Modal .modal-header,
  #profile-modify-Modal .modal-header {
    padding: 16px 60px;
    height: 250px;
    border: 0;
  }
  #profile-password-Modal .modal-header {
    padding: 15px 40px;
    border: 0;
  }

  #profile-Modal .modal-header > div,
  #profile-modify-Modal .modal-header > div {
    position: relative;
    height: 100%;
    border-bottom: 3px solid #fdfff0;
  }
  #profile-Modal .modal-header > div:nth-child(1) {
    padding: 90px 0;
    font-weight: 900;
    font-size: 50px;
    color: rgb(253, 255, 240);
  }
  #profile-Modal .modal-header img {
    position: absolute;
    margin: auto;
    top: 0;
    bottom: 0;
    right: 30px;
    background-color: rgb(253, 255, 240);
    width: 130px;
    height: 130px;
  }

  #profile-modify-Modal .modal-header > div {
    width: 100%;
    padding: 90px 0;
    font-weight: 900;
    font-size: 50px;
    color: rgb(253, 255, 240);
  }
  #profile-password-Modal .modal-header > div {
    width: 100%;
    color: #6e4c44;
    display: block;
  }
  .modal-password-header div:nth-child(1) {
    font-weight: 800;
    font-size: 25px;
    margin-top: 20px;
    margin-bottom: 15px;
  }
  .modal-password-header div:nth-child(2) {
    font-weight: 600;
    font-size: 12px;
    margin-bottom: 15px;
  }

  /* 모달 body */
  #profile-Modal .modal-body,
  #profile-modify-Modal .modal-body {
    padding: 0px 80px;
  }
  #profile-password-Modal .modal-body {
    padding: 0px 40px;
  }

  #profile-Modal table,
  #profile-modify-Modal table {
    color: rgb(253, 255, 240);
  }

  #profile-Modal th,
  #profile-Modal td,
  #profile-modify-Modal th,
  #profile-modify-Modal td {
    vertical-align: middle;
    padding: 0;
  }

  #profile-Modal th {
    width: 50%;
    height: 72px;
    font-size: 25px;
    font-weight: 500;
  }
  #profile-modify-Modal th {
    width: 40%;
    height: 72px;
    font-size: 25px;
    font-weight: 500;
  }
  #profile-Modal td,
  #profile-modify-Modal td {
    font-size: 25px;
    font-weight: 700;
  }
  #profile-modify-Modal input {
    width: 100%;
    border: 0;
    border-radius: 5px;
    outline-color: #fdfff0;
    padding: 7px 15px;
  }

  /* 모달 footer */

  .modal-footer {
    border: 0;
    margin: auto;
  }
  #profile-Modal .modal-footer > button,
  #profile-modify-Modal .modal-footer > button {
    background-color: #fdfff0;
    padding: 10px 20px;
    margin: 10px;
    font-size: 20px;
    font-weight: 900;
    color: rgb(110, 76, 68);
  }
  #profile-password-Modal .modal-footer > button {
    background-color: #6e4c44;
    padding: 7px 15px;
    margin: 10px;
    font-size: 20px;
    font-weight: 900;
    color: #fdfff0;
  }
  #profile-password-Modal input {
    width: 100%;
    border: 1px solid #f4f4f4;
    border-radius: 5px;
    margin-bottom: 15px;
    padding: 10px 15px;
    font-size: 15px;
    font-weight: 700;
    outline-color: #6e4c44;
  }
  #profile-password-Modal input:nth-child(3) {
    margin-bottom: 0px;
  }


  .modal-backdrop {
    width : 125vw;
    height : 125vh;
  }
  
  .btn-primary:hover {
	background-color: #553939;
	color: white;
	font-size: 12pt;
	font-weight:600;
}
  
  </style>
</head>
<body>

  <script>
    $(function() {
      var scale= 80;
      document.body.style.zoom=scale + "%";
    });
    
    
    var msg = "<%= alertMsg %>";

    if (msg != "null") {
      alert(msg);

      <% session.removeAttribute("alertMsg"); %>
    }

    
  </script>

  <div class="header">
    <!-- 메인로고 -->
    
    	<div id="logo"><img src="resources/images/coconut.png" width="100px" height="100px" alt=""></div>
    	<div id="coconut_page">coconut page</div>
	
    <!-- 상단 메뉴바 -->
    <div style="height : 100px; width : 1250px;" id="header_menu">
      <!-- 자바스크립트로 붙일 예정 -->
    </div>


    <!-- 상단 우측바 -->
    <div id="profile">
      <img
        onclick=""
    	<%if(e.getProfile()!=(null)){ %>
            
    		src="resources/profile_upfiles/<%=e.getProfile()%>"
    		 <%} else {%>
    		src="resources/images/icons8-user-beige-96.png" width="75px" height="75px"
    	<% }%>	
        class="rounded-circle"
        alt="profile img"
        data-toggle="modal"
        data-target="#profile-Modal"
      />

      <!-- The profile Modal -->
      <div class="modal fade" id="profile-Modal">
        <div class="modal-dialog">
          <div class="modal-content">
            <i class="fa fa-times" data-dismiss="modal"></i>

            <!-- Modal Header -->
            <div class="modal-header">
              <div class="col"><%= e.getEmpName() %></div>
              <div class="col">
                <img
                  <%if(e.getProfile()!=(null)){ %>
            
		    		src="resources/profile_upfiles/<%=e.getProfile()%>"
		    	  <%} else {%>
		    		src="resources/images/icons8-user-brown-96.png" width="75px" height="75px"
			      <% }%>
                  class="rounded-circle"
                  alt="profile modal img"
                />
              </div>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
              <table class="table table-borderless">
                <tbody>
                  <tr>
                    <th>사번</th>
                    <td><%= e.getEmpNo() %></td>
                  </tr>
                  <tr>
                    <th>부서</th>
                    <td><%= e.getDeptCode() %></td>
                  </tr>
                  <tr>
                    <th>직급</th>
                    <td><%= e.getJobCode() %></td>
                  </tr>
                  <tr>
                    <th>닉네임</th>
                    <td><%= e.getNickName() == null? "미설정" : e.getNickName() %></td>
                  </tr>
                  <tr>
                    <th>이메일</th>
                    <td><%= e.getEmail() %>@coco.com</td>
                  </tr>
                  <tr>
                    <th>주소</th>
                    <td><%= e.getAddress()==null ? "미입력" : e.getAddress() %></td>
                  </tr>
                  <tr>
                    <th>휴대폰</th>
                    <td><%= e.getPhone()==null ? "미입력" : e.getPhone() %></td>
                  </tr>
                  <tr>
                    <th>내선번호</th>
                    <td><%= e.getTel() %></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
              <button
                type="button"
                class="btn"
                data-toggle="modal"
                data-target="#profile-password-Modal"
              >
                비밀번호변경
              </button>
              <button
                type="button"
                class="btn"
                data-toggle="modal"
                data-target="#profile-modify-Modal"
              >
                개인정보수정
              </button>
            </div>
          </div>
        </div>
      </div>
   <div
      class="modal fade"
      id="profile-modify-Modal"
      data-backdrop="static"
      data-keyboard="false"
      >
        <div class="modal-dialog">
          <form action="info.em" method="post">
            <div class="modal-content">
                
              <i class="fa fa-times" data-dismiss="modal"></i>
              <!-- Modal Header -->
              <div class="modal-header">
                <div>개인정보수정</div>
              </div>

              <!-- Modal body -->
              <div class="modal-body">
                <table class="table table-borderless">
                  <tbody>
                    <tr>
                      <th>사번</th>
                      <td><%= e.getEmpNo() %></td>
                    </tr>
                    <tr>
                      <th>부서</th>
                      <td><%= e.getDeptCode() %></td>
                    </tr>
                    <tr>
                      <th>직급</th>
                      <td><%= e.getJobCode() %></td>
                    </tr>
                    <tr>
                      <th>이메일</th>
                      <td><%= e.getEmail() %></td>
                    </tr>
                    <tr>
                      <th>내선번호</th>
                      <td><%= e.getTel() %></td>
                    </tr>
                    <tr>
                      <th>휴대폰</th>
                      <td>
                        <input
                          type="text"
                          name="phone"
                          value="<%= e.getPhone()==null ? "" : e.getPhone() %>"
                        />
                      </td>
                    </tr>
                    <tr>
                      <th>주소</th>
                      <td>
                        <input
                          type="text"
                          name="address"
                          value="<%= e.getAddress()==null ? "" : e.getAddress() %>"
                        />
                      </td>
                    </tr>
					<tr>
                      <th>닉네임</th>
                      <td>
                        <input type="text" name="nickname" value="<%= e.getNickName() == null? "" : e.getNickName() %>" />
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <!-- Modal footer -->
              <div class="modal-footer">
                <button type="submit" class="btn" style="margin: auto;">변경완료</button>
              </div>
              <br>
              
            </div>
          </form>
        </div>
      </div>
      <!-- 비밀번호변경 Modal -->
      <div
        class="modal fade"
        id="profile-password-Modal"
        data-backdrop="static"
        data-keyboard="false"
      >
        <div class="modal-dialog">
          <form action="pwd.em" method="post">
          <div class="modal-content">
            <i class="fa fa-times" data-dismiss="modal"></i>
            <!-- Modal Header -->
            <div class="modal-header">
              <div class="modal-password-header">
                <div>비밀번호 변경</div>
                <div>
                  비밀번호는 4 ~ 20자로 영문자, 특수문자 !@#$%^&*()를 포함하여 등록해야
                  합니다.
                </div>
              </div>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
              <input type="hidden" name="empNo" value="<%= e.getEmpNo() %>">
              <div>
                <input type="password" placeholder="현재 비밀번호" name="userPwd" />
              </div>
              <div><input type="password" id="newPwd" name="newPwd" placeholder="새로운 비밀번호" /></div>
              <div>
                <input type="password" id="checkPwd" placeholder="비밀번호 확인" name="checkPwd" />
              </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
              <button type="submit" class="btn" onclick="return validatePwd();">변경완료</button>
            </div>
          </div>
          </form>
        </div>
      </div>
    </div>
    <div id="logout"><button type="button" class="btn btn-secondary" onclick="logout();">LOGOUT</button></div>
      
  </div>
  
  <script>
    function logout() {

      location.href = "<%= request.getContextPath() %>/logout.em";
    }

    function validatePwd() {
    	
        let newPwd = $("#newPwd").val();
        let checkPwd = $("#checkPwd").val();
        
        if (newPwd.length < 4 || newPwd.length > 20) {
      	  alert("비밀번호는 4~20자로 작성되어야합니다.");
      	  return false;
        }

        let regex1 = /^[0-9a-zA-Z]{4,20}$/;
        let regex2 = /^[!@#$%^&*]$/;
        
        if (newPwd != checkPwd) {
          alert("비밀번호가 일치하지 않습니다.");
          return false;
        } else if (regex1.test(newPwd)) {
          alert("비밀번호는 영문자, 특수문자를 포함하여 4~20자로 작성되어야합니다.");
          return false;
        } else if (regex2.test(newPwd)) {
          alert("비밀번호에는 특수문자가 포함되어야합니다.");
          return false;
        } 
      }
  </script>

</body>