<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.chart.model.vo.Chart" %>
<%@ page import="java.util.HashMap, java.util.Iterator"%>    
<%
	
	ArrayList<Chart> approList = (ArrayList<Chart>)request.getAttribute("approList");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/emn178/chartjs-plugin-labels/src/chartjs-plugin-labels.js"></script>
<style>
        body {
            background-color: rgb(253, 255, 240);
        }
        .wrap {
            
            margin : auto;
            width : 1920px;
            height : 1080px;
            /* border: 1px solid black; */
            
        }
        .header {
            height: 100px;
            width : 100%; 
            border-bottom : 1px solid black;     
            box-sizing: border-box;      
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
        .sidebar {
            width: 100px;
            background-color: rgb(110, 76, 68);
        }
        .main {
            float : left;
            width : 1820px;

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

        /* --------------------------------------------------------------------- */
        /* header 가운데 메뉴바 */

        #header_menu, #header_menu {
            color: rgb(110, 76, 68);
            font-weight: 400;
            font-size: 30px;
            width : 300px;
            height : 100%;
            padding-top : 20px;
            text-align: center;
        }



        /* --------------------------------------------------------------------- */
        /* header 우측 */
       
        #profile, #logout {
            height : 100px;
            /* border : 1px solid black; */
            box-sizing: border-box;
        }

        #profile {
            width : 100px;
        }

        #logout {
            width : 170px;
            text-align: center;
            padding-top :30px

        }

        /* --------------------------------------------------------------------- */
        /* 사이드 바 버튼 div */

        .side {
            width : 100%;
            height : 100px;
            /* border : 1px solid black; */
            box-sizing: border-box;
            text-align: center;
            padding-top : 10px;
            color : rgb(253, 255, 240);
        }

        /* 사이드 바 버튼 */
        .side>img {
            width: 60px;
            height : 60px;
            
        }

        

        /* 윤곽선용 */
        body { 
            box-sizing : border-box;
            /* border : 1px solid black; */
        }
        /* --------------------------------------------------------------------- */

        #header_menu {
            padding-left: 10%;
        }

        #header_menu ul {
            list-style-type: none;
            display: inline;
        }
        
        .navbar li {
            padding-top: 5px;
            width: 200px;
            float: left;
        }
        #header_menu {

            font-weight: 400;
            font-size: 30px;
            width: 300px;
            height: 100%;
            padding-top: 25px;
            text-align: center;
            padding-right: 100px;
        }

        #header_menu a {
            color:rgb(110, 76, 68);
        }

        /* --------------------------------------------------------------------- */
        /* main 영역 */
        .main {
            padding: 70px 100px;
        }

        /* --------------------------------------------------------------------- */
        /*검색 버튼 */
        #search { 
            height: 40px; 
			width: 500px;
            margin-top : 30px;
            text-align: center;
			align: center;
        }

        #search-filter, #search-text, #search-btn { height: 100%; }

        #search-filter { width: 200px; }

        #search-filter option[value=""] { display: none; }

        #search-text {
            width: 500px;
            display: inline;
        }

        #search-btn { 
            width: 80px; 
            border : 1px solid rgb(201, 201, 201);
            background-color: rgb(110, 76, 68);
            color : white;
            border-radius: 5px;
            
        }
        
        /* --------------------------------------------------------------------- */
        /* 메인 */
        .list {
            width : 80%;
            margin : auto;

        }
        #list-btn {
            width: 100%;
            padding: 10px;
        }
        #list-btn>input {
            float: right;
            height: 50px;
            width: 100px;
            background-color: rgb(110, 76, 68);
            color: white;
            border-radius: 5px;
            border: none;
            margin-bottom: 20px;
            font-size: 20px;
        }
        
        #tb {
            width: 100%;
            height: 600px;
            text-align: center;
            vertical-align: middle;
            background-color: white;
            margin-bottom: 30px;
        }
        
        #tb a { color: black; }
        
        #tb>thead { background-color: lightgray; }
        
        .pagination {
            width : 300px;
            margin : auto;
        }
        .pagination a {
            color:rgb(110, 76, 68);
        }
        #active-page {
            background-color: rgb(110, 76, 68);
            color: white;
        }


        /* 글쓰기 버튼 */
        #write_btn {
			position: relative;
			left: 95%;
            background-color: rgb(110, 76, 68);
            height: 40px;
            width: 80px;
            border-radius: 6px;
            border: rgb(110, 76, 68);
            color: white;
            margin-bottom: 8px;
			
        }

        #write_btn:hover {
            background-color: rgb(205, 162, 152);
        }
		a { text-decoration: none;
			color: white;}

	 	#pieChart {
	 		margin-top:30px;
	 		align:center;
	 		
	 		zoom:125%;

	 		}
	 		
	 	.chart_outer {
	 		margin:auto

	 	}	
	 	
	 	.chart_inner {
	 		margin:auto;
	 	}
	 	
	 	.chart_title {
	 		text-align: left;
	 		font-weight: bolder;
	 		padding-left: 110px; 
	 		font-size: 45px;
	 	}
	 	
	 	

</style>

</head>

<body>
<div class="wrap">
	<%@ include file="../common/header.jsp" %>
	
	<div class="container">
		<%@ include file="../common/sidebar.jsp" %>
		
		<div class="main">
		
		<h3 class="chart_title">문서 결재 현황</h3>

		
			<div class="chart_outer" style="width:1000px; height:800px;">
				<div class="chart_inner" style="width:600px; height:600px;">
<!-- 차트 그릴 캔버스 -->
<canvas id="pieChart" style="width:40%;height:40%; padding-top:20px;"></canvas>
	
	<script>
	
	var temp = [];
	
	<% for(int i = 0; i < approList.size(); i++) { %>
		temp.push(<%= approList.get(i).getAprId() %>);
	<% } %>
	
	
	var dataset = {
		    label: "결재 문서 현황",
		    backgroundColor: ['#92CC7F', '#6E4C44', '#ECF0D5', '#FF6384'], //라벨별 컬러설정
		    borderColor: '#22252B',
		    data: temp
		}

	var labels=[]; 
	
	<% if(!approList.isEmpty()) { %>	
		<% for(int i = 0; i < approList.size(); i++) { %>
			<%
				String status = "";
				
					switch(approList.get(i).getAprYN()) {
					case 1 : 
							status = "접수"; 
							break;
					case 2 : 
							status = "열람"; 
							break;
					case 3 : 
							status = "반려"; 
							break;
					case 4 : 
							status = "완료(승인)"; 
							break;
					}
					%>
				labels.push(['<%= status %>']);
		<% } %>
	<% } %>
		
	
		var datasets={ datasets:[dataset], labels:labels }
		var config = {
			    type: 'pie',
			    data: datasets, //데이터 셋 
			    options: {
			        responsive: true,
			        maintainAspectRatio: true, //true 하게 되면 캔버스 width,height에 따라 리사이징된다.
			        legend: {
			            position: 'right',
			            fontColor: 'black',
			            align: 'center',
			            display: true,
			            fullWidth: true,
			            labels: {
			                fontColor: 'rgb(0, 0, 0)'
			            }
			        },
			        plugins: {
			            labels: {//두번째 script태그를 설정하면 각 항목에다가 원하는 데이터 라벨링을 할 수 있다.
			                render: 'value',
			                fontColor: 'black',
			                fontSize: 15,
			                precision: 2
			            }

			        }
			    }
			}
		
		var canvas=document.getElementById('pieChart');
		var pieChart = new Chart(canvas,config);
	</script>
	
	<script>
    $(function() {
       $("#header_menu").html("<div><a href='<%=contextPath%>/emp.ch'>사원현황</a></div>"+
                               "<div><a href='<%=contextPath%>/appro.ch' style='color:#20c997'>결재현황</a></div>");
	});
 	</script>	

				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>