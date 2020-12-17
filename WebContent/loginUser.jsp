<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "com.db.ConnectDB" %>    
    
<%
	ConnectDB connectDB = ConnectDB.getInstance();
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	String returns2 = connectDB.connectionDB(id, pw);
	
	System.out.println(returns2);
	out.println(returns2);
%>