<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.parse.parseXML" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
    <h2>result 페이지</h2>
 
 <%
 	parseXML parse = parseXML.getInstance();
 	String rating = request.getParameter("rating");
 	String bizId = request.getParameter("bizId");
 	String userId = request.getParameter("userId");
 	System.out.println(rating);
 	System.out.println(userId);
 	System.out.println(bizId);
 	
 	parse.Ratingconnect(userId, rating, bizId);
 %>
 
<script>
  parent.ifun("성공");  
</script>
</body>
</html>