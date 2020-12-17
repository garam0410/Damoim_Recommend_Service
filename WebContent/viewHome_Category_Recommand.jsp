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
 
	 <script>
	function hideAndVisible(){
		if(layer1.style.visibility="hidden"){
			layer1.style.visibility="visible";
		}
		else if(layer1.style.visibility="visible"){
			layer1.style.visibility="hidden";
		}

	}
	</script>

</head>
<body>
      <%
            parseXML parse = parseXML.getInstance();
            request.setCharacterEncoding("UTF-8");
            String userId = request.getParameter("userId");
            int userId_Num = parse.userId_Num_Get(userId); 
            String categoryNum = parse.viewHomeConnect(userId_Num);
            System.out.println(categoryNum);
			String moreView = request.getParameter("more");%>

		
		<table width = "100%" style="text-align:center; border:none">
			
		<tr>			
			<div style=" background-color:skyblue; float:left; padding:10px; width: '100%'; height: auto;
			cursor: pointer;" onclick="location.href='javascript:hideAndVisible()';" Viewer>
				사용자데이터 기반 맞춤 추천 
			</div>
		</tr>
	
		</table>	
		
		<table id = 'layer1' width = "100%" style="text-align:center; border:none; hidden;">
		
			<tr>

				<div style="background-color: skyblue; float:left; padding:10px; margin:5px; colspan:2; height: auto; cursor: pointer;"
				onclick="location.href='http://61.245.226.108:8181/damoimServer/viewCollaborate_userClick.jsp?userId=<%=userId %>'" Viewer>
					userClick
				</div>
				
				<div style=" background-color: skyblue; float:left; padding:10px; margin:5px; colspan:2; height: auto; cursor: pointer;"
				onclick="location.href='http://61.245.226.108:8181/damoimServer/viewCollaborate_userRating.jsp?userId=<%=userId %>'" Viewer>
					userRating
				</div>

			</tr>
	
		</table>	

						<table width = "100%" style="text-align:center; border:none">
		
			<tr>
				
				<div style=" background-color: skyblue; float:left; padding:10px; height: auto;
				cursor: pointer;"onclick="location.href='javascript:hideAndVisible()';" Viewer>
					
					그룹 기반 맞춤 추천
				</div>

			</tr>
	
		</table>	
						<table width = "100%" style="text-align:center; border:none">
		
			<tr>
				
				<div id = 'layer3' style=" background-color: skyblue; float:left; padding:10px; margin:5px; colspan:2; height: auto;"
				onclick="location.href='http://61.245.226.108:8181/damoimServer/viewCollaborate_groupClick.jsp?userId=<%=userId %>'" Viewer>
					
					groupClick
				</div>
				<div id = 'layer4' style=" background-color: skyblue; float:left; padding:10px; margin:5px; colspan:2; height: auto;"
				onclick="location.href='http://61.245.226.108:8181/damoimServer/viewCollaborate_groupRating.jsp?userId=<%=userId %>'" Viewer>
					
					groupRating
				</div>

			</tr>
	
		</table>	
			
            <%
           // out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");        
            
            //RandomForest
            out.println(userId + "님을 위한 추천 카테고리");
            
            
            
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

							parse.category("00" + categoryNum,userId) + 

						"</tbody>" +

					"</table>" +


				"</div>" +

			"</div>");
      %>
		<a href="http://61.245.226.108:8181/damoimServer/viewAllList.jsp?userId=<%=userId%>&categoryNum=<%="00"+categoryNum%>&sort=category">전체보기</a>
</body>
</html>