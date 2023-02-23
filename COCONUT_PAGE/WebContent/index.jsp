<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.employee.model.vo.Employee" %>
<%
String alertMsg1 = (String)session.getAttribute("alertMsg1");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>
	body {
	  background-color: rgb(253, 255, 240);
	}
	.wrap {
	  margin: auto;
	  width: 1920px;
	  height: 1080px;
	  /* border: 1px solid black; */
	}
	.header {
	  height: 100px;
	  width: 100%;
	  border-bottom: 1px solid black;
	  box-sizing: border-box;
	}
	.container {
	  width: 1920px !important;
	  height: 980px;
	  margin: 0px !important;
	  padding: 0px !important;
	  max-width: none !important;
	}

	.container > div {
	  height: 980px;
	  float: left;
	}
	.sidebar {
	  width: 100px;
	  background-color: rgb(110, 76, 68);
	}
	.main {
	  float: left;
	  width: 1820px;
	}

	#logo {
	  width: 100px;
	  height: 100px;
	}

	#coconut_page {
	  color: rgb(110, 76, 68);
	  font-weight: bolder;
	  font-size: 40px;
	  width: 300px;
	  height: 100%;
	  padding-top: 20px;
	  vertical-align: middle;
	}

	.header > div {
	  float: left;
	}

	/* --------------------------------------------------------------------- */
	/* header 가운데 메뉴바 */

	#header_menu,
	#header_menu * {
	  color: rgb(110, 76, 68);
	  font-weight: 400;
	  font-size: 30px;
	  width: 300px;
	  height: 100%;
	  padding-top: 20px;
	  text-align: center;
	  padding-right: 100px;
	}

	/* --------------------------------------------------------------------- */
	/* header 우측 */

	#profile,
	#logout {
	  height: 100px;
	  box-sizing: border-box;
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
	  cursor: pointer;
	}

	/* prfofile Modal */

	#profile-Modal {
	  background-color: rgba(253, 255, 240, 0.7);
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
	  cursor: pointer;
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
	/* ----------------------------------- */
	#logout {
	  width: 170px;
	  text-align: center;
	  padding-top: 30px;
	}

	/* --------------------------------------------------------------------- */
	/* 사이드 바 버튼 div */

	.side {
	  width: 100%;
	  height: 100px;
	  /* border : 1px solid black; */
	  box-sizing: border-box;
	  text-align: center;
	  padding-top: 10px;
	  color: rgb(253, 255, 240);
	}

	/* 사이드 바 버튼 */
	.side > img {
	  width: 60px;
	  height: 60px;
	}

	/* 윤곽선용 */
	body * {
	  box-sizing: border-box;
	  /* border : 1px solid black; */
	}
  </style>
</head>
<body>

<script>

var msg = "<%= alertMsg1 %>";

if (msg != "null") {
  alert(msg);

  <% session.removeAttribute("alertMsg1"); %>
}

</script>

<% if (session.getAttribute("loginEmp") == null) { %>
	<!-- 로그인이 되지 않은 상태라면 -->
	<%@ include file="/views/employee/employeeLogin.jsp" %>
<% } else { %>
	<!-- 로그인이 된 상태라면 -->
	<%@ include file="/views/common/mainMenu.jsp" %>
<% } %>

</body>
</html>