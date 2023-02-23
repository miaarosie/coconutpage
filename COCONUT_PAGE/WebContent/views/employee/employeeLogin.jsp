<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();

	Cookie[] cookies = request.getCookies();
	
	String saveId = "";
	if (cookies != null) {
		for (int i=0; i<cookies.length; i++) {
			if (cookies[i].getName().equals("saveId")) {
				saveId = cookies[i].getValue();
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
        <style>
            body {
                background-color: rgb(253, 255, 240);
            }
            body * {
                box-sizing: border-box;
            }
            #login-area {
                width: 850px;
                height: 500px;
                margin: 200px auto;
                overflow: hidden;
            }
            h2 {
                height: 50px;
                line-height: 40px;
                font-size: 30px;
                color: rgb(110, 76, 68);
                margin-left:  100px;
            }
            #login-area > div {
                float: left;
                width: 50%;
                height: 450px;
            }
            #main-left > img {
                width: 100%;
                height: 100%;
                border-top-left-radius: 10px;
                border-bottom-left-radius: 10px;
            }
            #main-right {
                border-top-right-radius: 10px;
                border-bottom-right-radius: 10px;
                background-color: white;
            }
            form {
                margin: 80px auto;
                width: 70%;
            }
            input[type='number'],
            input[type='password'] {
                text-align: center;
                width: 100%;
                height: 40px;
                margin-bottom: 10px;
                border: none;
                border-bottom: solid 3px rgb(236, 236, 236);
            }
            #check-div {
                height: 40px;
                text-align: center;
                padding-top: 8px;
            }
            .log-btn {
                width: 100%;
                height: 50px;
                line-height: 50px;
                background-color: rgb(110, 76, 68);
                color: white;
                border: transparent;
                border-radius: 10px;
                font-size: 18px;
                font-weight: bold;
                margin-top: 45px;
            }

            input[type="number"]::-webkit-outer-spin-button,
            input[type="number"]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
            }

        </style>
    </head>
    <body>
        <!-- ---로그인창 ------------->
        <div class="main">
            <div id="login-area">
           
                <div id="main-left">
                    <img src="resources/images/LoginImg.png"/>
                </div>
                <div id="main-right">
                         <h2>coconut page</h2>
                    <form name="loginform" action="<%= contextPath %>/Login.em" method="post">
                        <input type="number" name="userId" placeholder="사원번호" id="userId" />
                        <input type="password" name="userPwd" placeholder="비밀번호" />
                        <div id="check-div">
                            <input type="checkbox" name="saveId" id="keepLogin" value="y" />
                            <label for="keepLogin">사원번호 저장</label>
                        </div>
                        <input type="submit" name="login" value="로그인" class="log-btn" />
                    </form>
                </div>
            </div>
        </div>
        
        <script>
        	$(function() {
        		
        		var saveId = "<%= saveId %>";
        		
        		if (saveId != "") {
        			
        			$("#userId").val(saveId);
        			$("#keepLogin").attr("checked", true);
        		}
        	});
        </script>

    </body>
</html>