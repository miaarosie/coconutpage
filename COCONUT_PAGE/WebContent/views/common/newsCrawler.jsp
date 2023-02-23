<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException, org.jsoup.Connection, org.jsoup.Jsoup, org.jsoup.nodes.Document, org.jsoup.select.Elements" %>
<%
String WebtoonUrl = "https://dailywebtoon.com/news";
Connection conn = Jsoup.connect(WebtoonUrl);


Document doc = conn.get();
Elements titleElements = doc.getElementsByAttributeValue("class", "td-subject");
Elements aElements = titleElements.select("a");
			
	for(int i = 0; i <= 3; i++) {
		String title = aElements.get(i).ownText();
		String no = aElements.get(i).attr("href"); // 해당 뉴스번호 뽑아보기
		out.println("<tr><td style='text-overflow: ellipsis; white-space: nowrap; overflow: hidden;'><a href='https://dailywebtoon.com" + no + "' target='_blank'>" + title + "</a></td></tr>");

	}


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
  a {
    text-decoration: none;
    color: black;
  }

  </style>
</head>
<body>
	<script>
	
		
   	</script>
      
</body>
</html>