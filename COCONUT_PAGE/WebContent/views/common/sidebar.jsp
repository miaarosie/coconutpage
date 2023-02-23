<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.employee.model.vo.Employee"%>
<%
    String contextPath = request.getContextPath();
	Employee ee = (Employee)session.getAttribute("loginEmp");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>

	.sidebar {
		background-color: rgb(110, 76, 68)!important;
	}
	
	.sidebar a {
		text-decoration : none;
	}
    .side {
        width : 100px;
        height : 100px;
        box-sizing: border-box;
        text-align: center;
        padding-top : 10px;
        color : rgb(253, 255, 240)!important;
        
    }

    /* 사이드 바 버튼 */
    .side img {
        width: 50px;
        height : 50px;

    }

    .side div {
        color : rgb(253, 255, 240)!important;

    }

   .container {
         width : 1920px!important;
         height : 980px;
         margin : 0px!important;
         padding : 0px!important;
         max-width: none!important;
    }
    
	.container>div {
        height : 980px;
        float : left;
    }
    
    #statistics {
    	width : 70px;
    	height : 70px;
    }

</style>
</head>
<body>

    <!-- 고정 사이드바 -->
    <div class="sidebar">
        <div style="width : 100%; height: 20px;"></div> <!-- 간격용 -->
        <!-- /COCONUT_PAGE/ -->
        <div class="btn1 side"><a href="<%= contextPath %>"><img src="resources/images/home.png"><div>홈</div></a> </div>
        <!-- /COCONUT_PAGE/send.docs -->
        <div class="btn1 side"><a href="<%= contextPath%>/approvalList.ap"><img src="resources/images/approval.png"><div>전자결재</div></a></div>
        <!-- /COCONUT_PAGE/자유게시판url매핑값 -->
        <div class="btn2 side"><a href="<%=contextPath%>/wagleList.po?currentPage=1"><img src="resources/images/add_document.png"><div>자유게시판</div></a></div>
        <!-- /COCONUT_PAGE/공지사항url매핑값 -->
        <div class="btn3 side"><a href="<%=contextPath%>/noList.board?boardNo=1&currentPage=1"><img src="resources/images/info.png"><div>공지사항</div></a></div>
        <!-- /COCONUT_PAGE/캘린더url매핑값 -->
        <div class="btn4 side"><a href="<%=contextPath%>/calendar.ca"><img src="resources/images/calendar.png" width="60px" height="60px"><div>캘린더</div></a></div>
        <!-- /COCONUT_PAGE/코코넛저장소url매핑값 -->
        <div class="btn5 side"><a href="<%=contextPath%>/noList.board?boardNo=2&currentPage=1"><img src="resources/images/coconut_storage.png"><div>코코넛저장소</div></a></div>
        <!-- /COCONUT_PAGE/조직도url매핑값 -->
        <div class="btn6 side"><a href="<%=contextPath%>/List.do?currentPage=1"><img src="resources/images/tree.png"><div>조직도</div></a></div>
        <!-- /COCONUT_PAGE/칸반보드url매핑값 -->
        <div class="btn7 side"><a href="<%= contextPath %>/list.kan"><img src="resources/images/kanvan.png"><div>칸반보드</div></a></div>
        
        <% if(ee.getManagerYN().equals("Y")) { %>
        
       		 <div class="btn8 side"><a href="<%=contextPath%>/emp.ch"><img src="resources/images/statistics.png" id="statistics"><div style="margin-top:-10px;">경영관리</div></a></div>
        
        <% } %>
    </div>    
    
</body>
</html>