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
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%
		parseXML parse = parseXML.getInstance();
		String sort = request.getParameter("sort");
		String userId = request.getParameter("userId");
		String categoryNum = request.getParameter("categoryNum");
		String query = "";
	%>
</head>
<body>

	
		<div class="container"> 

				<div class = "row">

					<table class="table table-bordered" style="text-align:center; border-collapse:separate; border:1px solid #000000">

						<tbody>
						
						<%
						if(sort.trim().equals("userclick"))
							out.println(parse.collaborateView_userClick_ALL(userId));
						
						else if(sort.trim().equals("userrating"))
							out.println(parse.collaborateView_userRating_ALL(userId));
						
						else if(sort.trim().equals("groupclick"))
							out.println(parse.collaborateView_groupClick_ALL(userId));
						
						else if(sort.trim().equals("grouprating"))
							out.println(parse.collaborateView_groupRating_ALL(userId));

						else if(sort.trim().equals("category"))
							out.println(parse.category_ALL(categoryNum, userId));
						
						else if(sort.trim().equals("search")){
							query = new String(request.getParameter("query").getBytes("ISO-8859-1"), "UTF-8");
							out.println(parse.resultSearch_ALL(query, userId));
						}
						
						else if(sort.trim().equals("newpolicy")){
							out.println(parse.viewHomeNewPolicy_ALL(userId));
						}
						else if(sort.trim().equals("hotclick")){
							out.println(parse.viewHomeHotPolicy_Click_ALL(userId));
						}
						else if(sort.trim().equals("hotrating")){
							out.println(parse.viewHomeHotPolicy_Rating_ALL(userId));
						}
						%> 

						</tbody>

					</table>


				</div>

			</div>
			<input type="button" value="뒤로가기" onclick="history.back(-1);">
</body>
</html>