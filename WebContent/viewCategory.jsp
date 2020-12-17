<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
            <%@ page import = "java.io.BufferedReader" %>
<%@ page import  = "java.io.InputStreamReader" %>
<%@ page import =  "java.io.StringReader" %>
<%@ page import =  "java.net.HttpURLConnection" %>
<%@ page import = "java.net.*" %>
<%@ page import = "java.net.URL.*" %>
<%@ page import =  "java.util.HashMap" %>
<%@ page import  = "java.util.Map" %>
<%@ page import =  "javax.xml.parsers.DocumentBuilder" %>
<%@ page import  = "javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import  = "javax.xml.xpath.XPath" %>
<%@ page import  = "javax.xml.xpath.XPathConstants" %>
<%@ page import  = "javax.xml.xpath.XPathExpression" %>
<%@ page import  = "javax.xml.xpath.XPathFactory" %>
<%@ page import  = "org.w3c.dom.Document" %>
<%@ page import =  "org.w3c.dom.Element" %>
<%@ page import  = "org.w3c.dom.Node" %>
<%@ page import  = "org.w3c.dom.NodeList" %>
<%@ page import  = "org.xml.sax.InputSource"%>
        <%@ page import = "java.util.*" %>
        <%@ page import = "com.parse.parseXML" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>카테고리 결과</title>
</head>
<body>
<%
	parseXML parse = parseXML.getInstance();
	request.setCharacterEncoding("UTF-8");
	String categoryCode = request.getParameter("categoryCode");
	String userId = request.getParameter("userId");
    int userId_Num = parse.userId_Num_Get(userId); 
	//parse.resultSearch(search);
	//search = URLEncoder.encode(search,"UTF-8");
	//System.out.println(parse.resultSearch(search));
	out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
	out.println("<div class=\"container\">" + 

				"<div class = \"row\">" + 

					"<table class=\"table table-bordered\" style=\"text-align:center; border:1px solid #000000\">" + 

						"<thead>" +

							"<tr>" +

								"<th style=\"background-color: #eeeeee; text-align: center;\">정책ID</th>" +

								"<th style=\"background-color: #eeeeee; text-align: center;\">정책명</th>" +

								"<th style=\"background-color: #eeeeee; text-align: center;\">정책유형</th>" +

							"</tr>" +

						"</thead>" +

						"<tbody>" +

						parse.category(categoryCode, userId) + 

						"</tbody>" +

					"</table>" +


				"</div>" +

			"</div>");

%>
<a href="http://61.245.226.108:8181/damoimServer/viewAllList.jsp?userId=<%=userId%>&categoryNum=<%=categoryCode%>&sort=category">전체보기</a>
</body>
</html>