<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.db.ConnectDB" %>    
    
<%
	ConnectDB connectDB = ConnectDB.getInstance();
	String name;
	name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	String age = request.getParameter("age");
	String job = request.getParameter("job");
	String favorite = request.getParameter("favorite");
	String area = request.getParameter("area");
	
	String returns = connectDB.connectionDB(name, id, pw, email, phone, age, job, favorite, area);
	
	System.out.println(returns);

	out.println(returns);
%>