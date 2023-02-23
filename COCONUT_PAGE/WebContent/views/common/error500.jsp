<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>
    .error {
        margin : auto;
        
        zoom: 50%;
    }
    
    #error {
    	margin-top : 200px;
    	text-align : center;
    }
    
    #homeBtn {
    	background-color: rgb(110, 76, 68);
	    padding: 10px 20px;
	    margin: 10px;
	    font-size: 20px;
	    font-weight: 500;
	    color: #fdfff0;
	    border: none;
	    border-radius : 5px;
    }
    
    #homeBtn:hover {
    	font-weight: 900;
    	opacity: 0.8;
    	cursor : pointer;
    }
    
</style>
</head>
<body>

	<div id="error">
    	<img src="resources/images/error500.jpg" class="error" alt=""><br>
        <button id="homeBtn" onclick="location.href='<%= request.getContextPath() %>'">메인으로 가기</button>
    </div>
</body>
</html>