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
<title>Insert title here</title>
</head>
<body>

 <%
 	parseXML parse = parseXML.getInstance();
 	String bizId = request.getParameter("bizId");
 	String userId = request.getParameter("userId");
 	System.out.println(userId);
 	System.out.println(bizId);
 	System.out.println("좋아요 정보 전달 완료");
 	parse.viewLike(userId, bizId);
 	//parse.Ratingconnect(userId, rating, bizId);
 %>
</body>
</html>