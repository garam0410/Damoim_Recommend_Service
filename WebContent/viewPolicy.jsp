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
<title>정책</title>

<%
	parseXML parse = parseXML.getInstance();
	request.setCharacterEncoding("UTF-8");
	String bizId = request.getParameter("bizId");
	String userId = request.getParameter("userId");
	//parse.resultSearch(search);
	//search = URLEncoder.encode(search,"UTF-8");
	//System.out.println(parse.resultSearch(search));
	out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
	%>
	
	<style>
		#if{
			width:0px; 
			height:0px;
			border:0px;
		}
	</style>
	
<iframe id="if" name="param"></iframe>
<script>
    function ifun(msg){
        alert(msg);
    }
   
</script>

<script type="text/javascript">
	function changeValue(){
		
		if(document.getElementById('like').value == "좋아요"){
			document.getElementById('like').value = "좋아요 취소";
		}
		
		else if(document.getElementById('like').value == "좋아요 취소"){			
			document.getElementById('like').value = "좋아요";
		}
	}
</script>

</head>
<body>

	<%

	out.println("<div class=\"container\">" + 

				"<div class = \"row\">" + 

					"<table class=\"table table-bordered\" style=\"text-align:center; border-collapse:separate; border:1px solid #000000\">" + 

						"<tbody>" +
						
						parse.policy(bizId, userId) + 

						"</tbody>" +

					"</table>" +


				"</div>" +

			"</div>");
%>
</br><input type="button" value="뒤로가기" onclick="history.back(-1);">
</body>
</html>