package com.parse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.Date;
public class parseXML {
	
	//���� url ����
	String serverURL = "61.245.226.108";
	
	Date date = new Date();
	
    private static parseXML instance = new parseXML();

    public static parseXML getInstance() {
        return instance;
    }
    public parseXML() {
    	
    }
	
    //�˻� ���_�Ϻ�
    public String resultSearch(String query, String userId) {
		
    	System.out.println(query);
    	
		BufferedReader br = null;
		//DocumentBuilderFactory ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		
		int i, j, k=0;
		
		String searchResult = "";
		
		String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
				"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
		for(int cnt = 1; cnt<2; cnt++) {
			try {
		    	query = URLEncoder.encode(query, "UTF-8");
					//OpenApiȣ��
			        String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex="+ cnt +"&display=20&query=" + query;
			        URL url = new URL(urlstr);
			        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			        
			        //���� �б�
			        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			        String result = "";
			        String line;
			        while ((line = br.readLine()) != null) {
			            result = result + line.trim();// result = URL�� XML�� ���� ��
			        }
			        // xml �Ľ��ϱ�
			        InputSource is = new InputSource(new StringReader(result));
			        builder = factory.newDocumentBuilder();
			        doc = builder.parse(is);
			        
			        XPathFactory xpathFactory = XPathFactory.newInstance();
			        XPath xpath = xpathFactory.newXPath();
			        // XPathExpression expr = xpath.compile("/response/body/items/item");
			        XPathExpression expr = xpath.compile("//emp");
			        
			        //xml ��� ����
			        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			        
			        for (i = 0; i < nodeList.getLength(); i++) {
			
			            NodeList child = nodeList.item(i).getChildNodes();

			            searchResult += "<tr>";
			            Node node = child.item(1);
			            System.out.print(key[0] + " : ");
		                System.out.println(node.getTextContent());
		                String bizId = node.getTextContent();
		                searchResult = searchResult + "<td>" + node.getTextContent() + "</td>";
		                
		                node = child.item(4);
			            System.out.print(key[3] + " : ");
		                System.out.println(node.getTextContent());
		                String path = String.valueOf("<A href = \"http://" + serverURL + ":8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + userId +"\">");
		                searchResult = searchResult + "<td>" +path + node.getTextContent() + "</A></td>";
		                
		                node = child.item(6);
			            System.out.print(key[5] + " : ");
		                System.out.println(node.getTextContent());
		                searchResult = searchResult + "<td>" + node.getTextContent() + "</td>";
		                
		                searchResult += "</tr>";
				 }
			    
			    
			} catch (Exception e) {
			    System.out.println(e.getMessage());
			}
		}
				//return search;
		return searchResult;
    }

    //�˻� ���_��ü
    public String resultSearch_ALL(String query, String userId) {
		
    	System.out.println(query);
    	
		BufferedReader br = null;
		//DocumentBuilderFactory ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		
		int i, j, k=0;
		
		String searchResult = "";
		
		String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
				"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
		for(int cnt = 1; cnt<15; cnt++) {
			try {
		    	query = URLEncoder.encode(query, "UTF-8");
					//OpenApiȣ��
			        String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex="+ cnt +"&display=100&query=" + query;
			        URL url = new URL(urlstr);
			        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			        
			        //���� �б�
			        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			        String result = "";
			        String line;
			        while ((line = br.readLine()) != null) {
			            result = result + line.trim();// result = URL�� XML�� ���� ��
			        }
			        // xml �Ľ��ϱ�
			        InputSource is = new InputSource(new StringReader(result));
			        builder = factory.newDocumentBuilder();
			        doc = builder.parse(is);
			        
			        XPathFactory xpathFactory = XPathFactory.newInstance();
			        XPath xpath = xpathFactory.newXPath();
			        // XPathExpression expr = xpath.compile("/response/body/items/item");
			        XPathExpression expr = xpath.compile("//emp");
			        
			        //xml ��� ����
			        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			        
			        for (i = 0; i < nodeList.getLength(); i++) {
			
			            NodeList child = nodeList.item(i).getChildNodes();

			            searchResult += "<tr>";
			            Node node = child.item(1);
			            System.out.print(key[0] + " : ");
		                System.out.println(node.getTextContent());
		                String bizId = node.getTextContent();
		                searchResult = searchResult + "<td>" + node.getTextContent() + "</td>";
		                
		                node = child.item(4);
			            System.out.print(key[3] + " : ");
		                System.out.println(node.getTextContent());
		                String path = String.valueOf("<A href = \"http://" + serverURL + ":8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + userId +"\">");
		                searchResult = searchResult + "<td>" +path + node.getTextContent() + "</A></td>";
		                
		                node = child.item(6);
			            System.out.print(key[5] + " : ");
		                System.out.println(node.getTextContent());
		                searchResult = searchResult + "<td>" + node.getTextContent() + "</td>";
		                
		                searchResult += "</tr>";
				 }
			    
			    
			} catch (Exception e) {
			    System.out.println(e.getMessage());
			}
		}
				//return search;
		return searchResult;
    }
    
    //��å ����
    public String policy(String bizId, String userId){

    	 BufferedReader br = null;
         //DocumentBuilderFactory ����
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(true);
         DocumentBuilder builder;
         Document doc = null;
         
         String policyResult = "";
         String category = null;
         
         int i, j, k=0;
         String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
         		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
         
         try {

             //OpenApiȣ��
             String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + bizId;
             URL url = new URL(urlstr);
             HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
             
             //���� �б�
             br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
             String result = "";
             String line;
             while ((line = br.readLine()) != null) {
                 result = result + line.trim();// result = URL�� XML�� ���� ��
             }
             
             // xml �Ľ��ϱ�
             InputSource is = new InputSource(new StringReader(result));
             builder = factory.newDocumentBuilder();
             doc = builder.parse(is);
             XPathFactory xpathFactory = XPathFactory.newInstance();
             XPath xpath = xpathFactory.newXPath();
             // XPathExpression expr = xpath.compile("/response/body/items/item");
             XPathExpression expr = xpath.compile("//emp");
             
             //xml ��� ����
             NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
             
             String rating = getRating(bizId, userId);
             
             String Num_10="", Num_1="", Num_2="", Num_3="", Num_4="", Num_5="",
            		 Num_6="", Num_7="", Num_8="", Num_9="";
             
             System.out.println(rating);
             
             if(rating.trim().equals("1")) {
            	 Num_1 = "selected";
             }
             else if(rating.trim().equals("2")) {
            	 Num_2 = "selected";
             }
             else if(rating.trim().equals("3")) {
            	 Num_3 = "selected";
             }
             else if(rating.trim().equals("4")) {
            	 Num_4 = "selected";
             }
             else if(rating.trim().equals("5")) {
            	 Num_5 = "selected";
             }
             else if(rating.trim().equals("6")) {
            	 Num_6 = "selected";
             }
             else if(rating.trim().equals("7")) {
            	 Num_7 = "selected";
             }
             else if(rating.trim().equals("8")) {
            	 Num_8 = "selected";
             }
             else if(rating.trim().equals("9")) {
            	 Num_9 = "selected";
             }
             else if(rating.trim().equals("10")) {
            	 Num_10 = "selected";
             }
             
             String like = getLike(bizId, userId);
             String likeResult = "";
             if(like.trim().equals("1")) {
            	 like = "���ƿ� ���";
             }
             else if(like.trim().equals("0")) {
            	 like = "���ƿ�";
             }
             
             System.out.println(like);
             
             policyResult += 
						  "<form action = \'sendRating.jsp\' target = 'param'>" + 
						  "<select name = \"rating\">" +
						    "<option value = \"1\" " + Num_1 +">1</option>" +
						    "<option value = \"2\" " + Num_2 +">2</option>" +
						    "<option value = \"3\" " + Num_3 +">3</option>" +
						    "<option value = \"4\" " + Num_4 +">4</option>" +
						    "<option value = \"5\" " + Num_5 +">5</option>" +
						    "<option value = \"6\" " + Num_6 +">6</option>" +
						    "<option value = \"7\" " + Num_7 +">7</option>" +
						    "<option value = \"8\" " + Num_8 +">8</option>" +
						    "<option value = \"9\" " + Num_9 +">9</option>" +
						    "<option value = \"10\" " + Num_10 +">10</option>" +
									  "</select>" +
						     "<input type='hidden' name='bizId' value= \'" + bizId + "\'/>" +
						     "<input type='hidden' name='userId' value= \'" + userId + "\'/>" +
						  "<input type='submit' value='����'>" +
						"</form>"+
						  
						//���ƿ� ��ư
						  "<form action = \'sendLike.jsp\' target='param'>" + 
						     "<input type='hidden' name='bizId' value= \'" + bizId + "\'/>" +
						     "<input type='hidden' name='userId' value= \'" + userId + "\'/>" +
						  "<input id = \"like\" onclick=\"changeValue()\" type='submit' value=\"" + like + "\">" +
						  ///"<input id = 'like2' type='button' value=\"" + like + "\">" +
						  "</form>";
             
             for (i = 0; i < nodeList.getLength(); i++) {
                 NodeList child = nodeList.item(i).getChildNodes();
                 
 	            for (j = 1; j < child.getLength(); j++) {
 	            	
 	            	policyResult += "<tr>";
 	            	
 	                Node node = child.item(j);
 	                System.out.print(key[k] + " : ");
 	                System.out.println(node.getTextContent());
 	                policyResult = policyResult + "<td width=\"100\" style=\"background-color: #eeeeee; text-align: center;\">"+ key[k] + "</td>"+ "<td>" + node.getTextContent() + "</td>";
 	                k++;
 	                if(j==6) {
 	                	System.out.println(node.getTextContent());
 	                	if(node.getTextContent().equals("�������")) {
 	                		category = "004001";
 	                	}
 	                	
 	                	else if(node.getTextContent().equals("â������")) {
 	                		category = "004002";
 	                	}
 	                	
 	                	else if(node.getTextContent().equals("�ְš�����") ){
 	                		category = "004003";
 	                	}
 	                	
 	                	else if(node.getTextContent().equals("��Ȱ������")) {
 	                		category = "004004";
 	                	}
 	                	
 	                	else if(node.getTextContent().equals("��å����")) {
 	                		category = "004005";
 	                	}
 	                	
 	                	else if(node.getTextContent().equals("�ڷγ�19")) {
 	                		category = "004006";
 	                	}
 	                }
 	            }
 	            k=0;
	            	
	            policyResult += "</tr>";
 	            System.out.println("");
 	            
             }
             System.out.println(category);
             Clickconnect(userId, category, bizId);
             
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         System.out.println(policyResult);
         return policyResult;
    }
    
    //���� ��������
    public String getRating(String bizId, String Id) {
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
     
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        
        int getId = userId_Num_Get(Id);
        
        String rating = null;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT RATING FROM STREAMDATA_RATING WHERE USERID = ? AND BIZID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(getId));
            pstmt.setString(2, bizId);
            rs = pstmt.executeQuery();

            if(rs.next()) {
            	rating = rs.getString("RATING");
            	System.out.println(rating);
            }
            else
            	rating = "1";
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
		return rating;
	}
	
    //���ƿ� ��������
    public String getLike(String bizId, String Id) {
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
     
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        
        int getId = userId_Num_Get(Id);
        
        String rating = null;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT POLICYLIKE FROM STREAMDATA_LIKE WHERE USERID = ? AND BIZID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(getId));
            pstmt.setString(2, bizId);
            rs = pstmt.executeQuery();

            if(rs.next()) {
            	rating = rs.getString("POLICYLIKE");
            	System.out.println(rating);
            }
            else
            	rating = "0";
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
		return rating;
	}
	
    //ī�װ� ��_�Ϻ�
    public String category(String categoryCode, String userId){

    	 BufferedReader br = null;
         //DocumentBuilderFactory ����
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(true);
         DocumentBuilder builder;
         Document doc = null;
         
         String categoryResult = "";
         
         int i, j, k=0;
         String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
         		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
         
         try {
        	 //�� Ȯ���ض�
        		 for(int cnt = 1; cnt<2; cnt++) {
             		//OpenApiȣ��
                      String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex="+ cnt +"&display=20&bizTycdSel=" + categoryCode;
                      URL url = new URL(urlstr);
                      HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                      
                      //���� �б�
                      br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                      String result = "";
                      String line;
                      while ((line = br.readLine()) != null) {
                          result = result + line.trim();// result = URL�� XML�� ���� ��
                      }
                      
                      // xml �Ľ��ϱ�
                      InputSource is = new InputSource(new StringReader(result));
                      builder = factory.newDocumentBuilder();
                      doc = builder.parse(is);
                      XPathFactory xpathFactory = XPathFactory.newInstance();
                      XPath xpath = xpathFactory.newXPath();
                      // XPathExpression expr = xpath.compile("/response/body/items/item");
                      XPathExpression expr = xpath.compile("//emp");
                      
                      //xml ��� ����
                      NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                      for (i = 0; i < nodeList.getLength(); i++) {
                     	 
                          NodeList child = nodeList.item(i).getChildNodes();
                          
     			            categoryResult += "<tr>";
     			            Node node = child.item(1);
     			            System.out.print(key[0] + " : ");
     		                System.out.println(node.getTextContent());
     		                String bizId = node.getTextContent();
     		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
     		                
     		                node = child.item(4);
     			            System.out.print(key[3] + " : ");
     		                System.out.println(node.getTextContent());
     		                String path = String.valueOf("<A href = \"http://" + serverURL + ":8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + userId +"\">");
     		                categoryResult = categoryResult + "<td>" +path + node.getTextContent() + "</A></td>";
     		                
     		                node = child.item(6);
     			            System.out.print(key[5] + " : ");
     		                System.out.println(node.getTextContent());
     		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
     		                
     		                categoryResult += "</tr>";
                      }
                 }
             
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    	
 		return categoryResult;
    }
    
    //ī�װ� ��_��ü
    public String category_ALL(String categoryCode, String userId){

    	 BufferedReader br = null;
         //DocumentBuilderFactory ����
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(true);
         DocumentBuilder builder;
         Document doc = null;
         
         String categoryResult = "";
         
         int i, j, k=0;
         String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
         		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
         
         try {
        	 //�� Ȯ���ض�
        		 for(int cnt = 1; cnt<15; cnt++) {
             		//OpenApiȣ��
                      String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex="+ cnt +"&display=100&bizTycdSel=" + categoryCode;
                      URL url = new URL(urlstr);
                      HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                      
                      //���� �б�
                      br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                      String result = "";
                      String line;
                      while ((line = br.readLine()) != null) {
                          result = result + line.trim();// result = URL�� XML�� ���� ��
                      }
                      
                      // xml �Ľ��ϱ�
                      InputSource is = new InputSource(new StringReader(result));
                      builder = factory.newDocumentBuilder();
                      doc = builder.parse(is);
                      XPathFactory xpathFactory = XPathFactory.newInstance();
                      XPath xpath = xpathFactory.newXPath();
                      // XPathExpression expr = xpath.compile("/response/body/items/item");
                      XPathExpression expr = xpath.compile("//emp");
                      
                      //xml ��� ����
                      NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                      for (i = 0; i < nodeList.getLength(); i++) {
                     	 
                          NodeList child = nodeList.item(i).getChildNodes();
                          
     			            categoryResult += "<tr>";
     			            Node node = child.item(1);
     			            System.out.print(key[0] + " : ");
     		                System.out.println(node.getTextContent());
     		                String bizId = node.getTextContent();
     		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
     		                
     		                node = child.item(4);
     			            System.out.print(key[3] + " : ");
     		                System.out.println(node.getTextContent());
     		                String path = String.valueOf("<A href = \"http://" + serverURL + ":8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + userId +"\">");
     		                categoryResult = categoryResult + "<td>" +path + node.getTextContent() + "</A></td>";
     		                
     		                node = child.item(6);
     			            System.out.print(key[5] + " : ");
     		                System.out.println(node.getTextContent());
     		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
     		                
     		                categoryResult += "</tr>";
                      }
                 }
             
         } catch (Exception e) {
             e.printStackTrace();
         }
    	
 		return categoryResult;
    }
    
    //Ȩ ȭ�� �ֽ� ��å ��_�Ϻ�
    public String viewHomeNewPolicy(String userId) {
    	BufferedReader br = null;
		//DocumentBuilderFactory ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		
		int i, j, k=0;
		
		String searchResult = "";
		
		String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
				"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
		try {
			for(int cnt = 1; cnt<2; cnt++) {
				//OpenApiȣ��
		        String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex="+ cnt +"&display=15";
		
		        URL url = new URL(urlstr);
		        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
		        
		        //���� �б�
		        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		        String result = "";
		        String line;
		        while ((line = br.readLine()) != null) {
		            result = result + line.trim();// result = URL�� XML�� ���� ��
		        }
		        // xml �Ľ��ϱ�
		        InputSource is = new InputSource(new StringReader(result));
		        builder = factory.newDocumentBuilder();
		        doc = builder.parse(is);
		        XPathFactory xpathFactory = XPathFactory.newInstance();
		        XPath xpath = xpathFactory.newXPath();
		        // XPathExpression expr = xpath.compile("/response/body/items/item");
		        XPathExpression expr = xpath.compile("//emp");
		        
		        //xml ��� ����
		        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		        
				        for (i = 0; i < nodeList.getLength(); i++) {
				
				            NodeList child = nodeList.item(i).getChildNodes();
			                
			                //searchResult += "<br>";
				            searchResult += "<tr>";
				            Node node = child.item(1);
				            System.out.print(key[0] + " : ");
			                System.out.println(node.getTextContent());
			                String bizId = node.getTextContent();
			                searchResult = searchResult + "<td>" + node.getTextContent() + "</td>";
			                
			                node = child.item(4);
				            System.out.print(key[3] + " : ");
			                System.out.println(node.getTextContent());
			                String path = String.valueOf("<A href = \"http://" + serverURL + ":8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + userId +"\">");
			                searchResult = searchResult + "<td>" +path + node.getTextContent() + "</A></td>";
			                
			                node = child.item(6);
				            System.out.print(key[5] + " : ");
			                System.out.println(node.getTextContent());
			                searchResult = searchResult + "<td>" + node.getTextContent() + "</td>";
			                
			                searchResult += "</tr>";
					 }
		        }
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}

		return searchResult;
    }
    
    //Ȩ ȭ�� �ֽ� ��å ��_��ü
    public String viewHomeNewPolicy_ALL(String userId) {
    	BufferedReader br = null;
		//DocumentBuilderFactory ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		
		int i, j, k=0;
		
		String searchResult = "";
		
		String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
				"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
		try {
			for(int cnt = 1; cnt<15; cnt++) {
				//OpenApiȣ��
		        String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex="+ cnt +"&display=100";
		
		        URL url = new URL(urlstr);
		        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
		        
		        //���� �б�
		        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		        String result = "";
		        String line;
		        while ((line = br.readLine()) != null) {
		            result = result + line.trim();// result = URL�� XML�� ���� ��
		        }
		        // xml �Ľ��ϱ�
		        InputSource is = new InputSource(new StringReader(result));
		        builder = factory.newDocumentBuilder();
		        doc = builder.parse(is);
		        XPathFactory xpathFactory = XPathFactory.newInstance();
		        XPath xpath = xpathFactory.newXPath();
		        // XPathExpression expr = xpath.compile("/response/body/items/item");
		        XPathExpression expr = xpath.compile("//emp");
		        
		        //xml ��� ����
		        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		        
				        for (i = 0; i < nodeList.getLength(); i++) {
				
				            NodeList child = nodeList.item(i).getChildNodes();
			                
			                //searchResult += "<br>";
				            searchResult += "<tr>";
				            Node node = child.item(1);
				            System.out.print(key[0] + " : ");
			                System.out.println(node.getTextContent());
			                String bizId = node.getTextContent();
			                searchResult = searchResult + "<td>" + node.getTextContent() + "</td>";
			                
			                node = child.item(4);
				            System.out.print(key[3] + " : ");
			                System.out.println(node.getTextContent());
			                String path = String.valueOf("<A href = \"http://" + serverURL + ":8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + userId +"\">");
			                searchResult = searchResult + "<td>" +path + node.getTextContent() + "</A></td>";
			                
			                node = child.item(6);
				            System.out.print(key[5] + " : ");
			                System.out.println(node.getTextContent());
			                searchResult = searchResult + "<td>" + node.getTextContent() + "</td>";
			                
			                searchResult += "</tr>";
					 }
		        }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		return searchResult;
    }
      
    //Ȩ ȭ�� ��õ ��åī�װ� ��
    public String viewHomeConnect(int Id) {
       // TODO Auto-generated method stub
         // oracle ����
         String jdbcUrl = "jdbc:oracle:thin:@" + "61.245.226.108" + ":1521:orcl";
         String userId = "scott";
         String userPw = "1234";
         
         Connection conn = null;
         PreparedStatement pstmt = null;
         PreparedStatement pstmt2 = null;
         ResultSet rs = null;
         ResultSet rs2 = null;

         String sql = "";
         String sql2 = "";
         String returns = "a";
         String returns2 = "b";
         
         System.out.println(Id);
         
         try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            sql = "SELECT PREDICTION FROM RANDOMFOREST_RESULT WHERE USERID = ?";
            //sql = "SELECT CATEGORY FROM RESULT_CLICK WHERE USERID = (SELECT USERID_NUM FROM STREAM_CLICK_NUM WHERE USERID = ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Id);
            
            System.out.println(sql);
            
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	returns2 = rs.getString("PREDICTION");
            }
            else{
            	System.out.println("������");	
            	sql2 = "SELECT FAVORITE FROM USERINFORMATION WHERE USERID = ?";
            	pstmt2 = conn.prepareStatement(sql2);
            	pstmt2.setInt(1, Id);
            	
            	rs2 = pstmt2.executeQuery();
            	if(rs2.next()) {
            		returns2 = rs2.getString("FAVORITE");
            	}
            }

         } catch (Exception e) {
             e.printStackTrace();
         } finally {
            if (pstmt2 != null)try {pstmt2.close();} catch(SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
         }
         System.out.println(returns2);
       return returns2;
    }
    
    //Ȩ ȭ�� �α� ��å ��_��ȸ��_�Ϻ�
    public String viewHomeHotPolicy_Click(String Id) {

    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";

        int userId_Num = 0;
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT BIZID FROM USER_CLICK_POPULAR_RESULT";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
            	BizId bizid = new BizId();
            	bizid.setBizId(rs.getInt("BIZID"));
            	list.add(bizid);
            	
            	System.out.println(rs.getInt("BIZID"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }

        int size = list.size();
    	
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        String categoryResult = "";
        
        int i, j;
        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
        		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
        
        try {
       		 for(int cnt = 0; cnt<=4; cnt++) {
            		//OpenApiȣ��
                     String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + list.get(cnt).getBizId();
                     URL url = new URL(urlstr);
                     HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                     
                     //���� �б�
                     br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                     String result = "";
                     String line;
                     while ((line = br.readLine()) != null) {
                         result = result + line.trim();// result = URL�� XML�� ���� ��
                     }
                     
                     // xml �Ľ��ϱ�
                     InputSource is = new InputSource(new StringReader(result));
                     builder = factory.newDocumentBuilder();
                     doc = builder.parse(is);
                     XPathFactory xpathFactory = XPathFactory.newInstance();
                     XPath xpath = xpathFactory.newXPath();
                     // XPathExpression expr = xpath.compile("/response/body/items/item");
                     XPathExpression expr = xpath.compile("//emp");
                     
                     //xml ��� ����
                     NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                     for (i = 0; i < nodeList.getLength(); i++) {
                    	 
                         NodeList child = nodeList.item(i).getChildNodes();
                         
    			            categoryResult += "<tr>";
    			            Node node = child.item(1);
    			            System.out.print(key[0] + " : ");
    		                System.out.println(node.getTextContent());
    		                String bizId = node.getTextContent();
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                node = child.item(4);
    			            System.out.print(key[3] + " : ");
    		                System.out.println(node.getTextContent());
    		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + Id +"\">");
    		                categoryResult = categoryResult + "<td>" +path + node.getTextContent() + "</A></td>";
    		                
    		                node = child.item(6);
    			            System.out.print(key[5] + " : ");
    		                System.out.println(node.getTextContent());
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                categoryResult += "</tr>";
                     }
                }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(categoryResult);
        return categoryResult;
    }
    
    //Ȩ ȭ�� �α� ��å ��_����_�Ϻ�
    public String viewHomeHotPolicy_Rating(String Id) {

    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";

        int userId_Num = 0;
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT BIZID FROM USER_RATING_POPULAR_RESULT";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
            	BizId bizid = new BizId();
            	bizid.setBizId(rs.getInt("BIZID"));
            	list.add(bizid);
            	
            	System.out.println(rs.getInt("BIZID"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }

        int size = list.size();
    	
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        String categoryResult = "";
        
        int i, j;
        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
        		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
        
        try {
       		 for(int cnt = 0; cnt<=4; cnt++) {
            		//OpenApiȣ��
                     String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + list.get(cnt).getBizId();
                     URL url = new URL(urlstr);
                     HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                     
                     //���� �б�
                     br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                     String result = "";
                     String line;
                     while ((line = br.readLine()) != null) {
                         result = result + line.trim();// result = URL�� XML�� ���� ��
                     }
                     
                     // xml �Ľ��ϱ�
                     InputSource is = new InputSource(new StringReader(result));
                     builder = factory.newDocumentBuilder();
                     doc = builder.parse(is);
                     XPathFactory xpathFactory = XPathFactory.newInstance();
                     XPath xpath = xpathFactory.newXPath();
                     // XPathExpression expr = xpath.compile("/response/body/items/item");
                     XPathExpression expr = xpath.compile("//emp");
                     
                     //xml ��� ����
                     NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                     for (i = 0; i < nodeList.getLength(); i++) {
                    	 
                         NodeList child = nodeList.item(i).getChildNodes();
                         
    			            categoryResult += "<tr>";
    			            Node node = child.item(1);
    			            System.out.print(key[0] + " : ");
    		                System.out.println(node.getTextContent());
    		                String bizId = node.getTextContent();
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                node = child.item(4);
    			            System.out.print(key[3] + " : ");
    		                System.out.println(node.getTextContent());
    		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + Id +"\">");
    		                categoryResult = categoryResult + "<td>" +path + node.getTextContent() + "</A></td>";
    		                
    		                node = child.item(6);
    			            System.out.print(key[5] + " : ");
    		                System.out.println(node.getTextContent());
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                categoryResult += "</tr>";
                     }
                }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(categoryResult);
        return categoryResult;
    }
    
    //Ȩ ȭ�� �α� ��å ��_��ȸ��_��ü
    public String viewHomeHotPolicy_Click_ALL(String Id) {

    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";

        int userId_Num = 0;
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT BIZID FROM USER_CLICK_POPULAR_RESULT";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
            	BizId bizid = new BizId();
            	bizid.setBizId(rs.getInt("BIZID"));
            	list.add(bizid);
            	
            	System.out.println(rs.getInt("BIZID"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }

        int size = list.size();
    	
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        String categoryResult = "";
        
        int i, j;
        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
        		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
        
        try {
       		 for(int cnt = 0; cnt<=size; cnt++) {
            		//OpenApiȣ��
                     String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + list.get(cnt).getBizId();
                     URL url = new URL(urlstr);
                     HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                     
                     //���� �б�
                     br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                     String result = "";
                     String line;
                     while ((line = br.readLine()) != null) {
                         result = result + line.trim();// result = URL�� XML�� ���� ��
                     }
                     
                     // xml �Ľ��ϱ�
                     InputSource is = new InputSource(new StringReader(result));
                     builder = factory.newDocumentBuilder();
                     doc = builder.parse(is);
                     XPathFactory xpathFactory = XPathFactory.newInstance();
                     XPath xpath = xpathFactory.newXPath();
                     // XPathExpression expr = xpath.compile("/response/body/items/item");
                     XPathExpression expr = xpath.compile("//emp");
                     
                     //xml ��� ����
                     NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                     for (i = 0; i < nodeList.getLength(); i++) {
                    	 
                         NodeList child = nodeList.item(i).getChildNodes();
                         
    			            categoryResult += "<tr>";
    			            Node node = child.item(1);
    			            System.out.print(key[0] + " : ");
    		                System.out.println(node.getTextContent());
    		                String bizId = node.getTextContent();
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                node = child.item(4);
    			            System.out.print(key[3] + " : ");
    		                System.out.println(node.getTextContent());
    		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + Id +"\">");
    		                categoryResult = categoryResult + "<td>" +path + node.getTextContent() + "</A></td>";
    		                
    		                node = child.item(6);
    			            System.out.print(key[5] + " : ");
    		                System.out.println(node.getTextContent());
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                categoryResult += "</tr>";
                     }
                }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(categoryResult);
        return categoryResult;
    }
    
    //Ȩ ȭ�� �α� ��å ��_����_��ü
    public String viewHomeHotPolicy_Rating_ALL(String Id) {

    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";

        int userId_Num = 0;
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT BIZID FROM USER_RATING_POPULAR_RESULT";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
            	BizId bizid = new BizId();
            	bizid.setBizId(rs.getInt("BIZID"));
            	list.add(bizid);
            	
            	System.out.println(rs.getInt("BIZID"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }

        int size = list.size();
    	
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        String categoryResult = "";
        
        int i, j;
        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
        		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
        
        try {
       		 for(int cnt = 0; cnt<=size; cnt++) {
            		//OpenApiȣ��
                     String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + list.get(cnt).getBizId();
                     URL url = new URL(urlstr);
                     HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                     
                     //���� �б�
                     br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                     String result = "";
                     String line;
                     while ((line = br.readLine()) != null) {
                         result = result + line.trim();// result = URL�� XML�� ���� ��
                     }
                     
                     // xml �Ľ��ϱ�
                     InputSource is = new InputSource(new StringReader(result));
                     builder = factory.newDocumentBuilder();
                     doc = builder.parse(is);
                     XPathFactory xpathFactory = XPathFactory.newInstance();
                     XPath xpath = xpathFactory.newXPath();
                     // XPathExpression expr = xpath.compile("/response/body/items/item");
                     XPathExpression expr = xpath.compile("//emp");
                     
                     //xml ��� ����
                     NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                     for (i = 0; i < nodeList.getLength(); i++) {
                    	 
                         NodeList child = nodeList.item(i).getChildNodes();
                         
    			            categoryResult += "<tr>";
    			            Node node = child.item(1);
    			            System.out.print(key[0] + " : ");
    		                System.out.println(node.getTextContent());
    		                String bizId = node.getTextContent();
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                node = child.item(4);
    			            System.out.print(key[3] + " : ");
    		                System.out.println(node.getTextContent());
    		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + Id +"\">");
    		                categoryResult = categoryResult + "<td>" +path + node.getTextContent() + "</A></td>";
    		                
    		                node = child.item(6);
    			            System.out.print(key[5] + " : ");
    		                System.out.println(node.getTextContent());
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                categoryResult += "</tr>";
                     }
                }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(categoryResult);
        return categoryResult;
    }
    
    //Ȩ ȭ�� ���α� ��å ��_����
    public String viewHomeHotPolicy_Rating_Unpopular(String Id) {

    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";

        int userId_Num = 0;
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT BIZID FROM USER_RATING_UNPOPULAR";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
            	BizId bizid = new BizId();
            	bizid.setBizId(rs.getInt("BIZID"));
            	list.add(bizid);
            	
            	System.out.println(rs.getInt("BIZID"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }

        int size = list.size();
    	
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        String categoryResult = "";
        
        int i, j;
        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
        		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
        
        try {
       		 for(int cnt = 0; cnt<=size; cnt++) {
            		//OpenApiȣ��
                     String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + list.get(cnt).getBizId();
                     URL url = new URL(urlstr);
                     HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                     
                     //���� �б�
                     br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                     String result = "";
                     String line;
                     while ((line = br.readLine()) != null) {
                         result = result + line.trim();// result = URL�� XML�� ���� ��
                     }
                     
                     // xml �Ľ��ϱ�
                     InputSource is = new InputSource(new StringReader(result));
                     builder = factory.newDocumentBuilder();
                     doc = builder.parse(is);
                     XPathFactory xpathFactory = XPathFactory.newInstance();
                     XPath xpath = xpathFactory.newXPath();
                     // XPathExpression expr = xpath.compile("/response/body/items/item");
                     XPathExpression expr = xpath.compile("//emp");
                     
                     //xml ��� ����
                     NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                     for (i = 0; i < nodeList.getLength(); i++) {
                    	 
                         NodeList child = nodeList.item(i).getChildNodes();
                         
    			            categoryResult += "<tr>";
    			            Node node = child.item(1);
    			            System.out.print(key[0] + " : ");
    		                System.out.println(node.getTextContent());
    		                String bizId = node.getTextContent();
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                node = child.item(4);
    			            System.out.print(key[3] + " : ");
    		                System.out.println(node.getTextContent());
    		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + Id +"\">");
    		                categoryResult = categoryResult + "<td>" +path + node.getTextContent() + "</A></td>";
    		                
    		                node = child.item(6);
    			            System.out.print(key[5] + " : ");
    		                System.out.println(node.getTextContent());
    		                categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
    		                
    		                categoryResult += "</tr>";
                     }
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(categoryResult);
        return categoryResult;
    }
 
     //��å Ŭ���� ī����
    public void Clickconnect(String Id, String category, String bizId) {
        // oracle ����
        String jdbcUrl = "jdbc:oracle:thin:@" + "61.245.226.108" + ":1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        int count;
        String rand;

        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;

        String sql = "";
        String sql2 = "";
        String returns = "a";
        String returns2 = "b";
        
        int getId = userId_Num_Get(Id);
        
        System.out.println(date + ", " + Id + ", " + category + ", " + bizId);
        
        try {
        	
        	Random random = new Random();
        	
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

        		sql2 = "INSERT INTO STREAMDATA_CLICK VALUES(?,?,?,?,?)";
                pstmt2 = conn.prepareStatement(sql2);
                
                rand = String.valueOf(random.nextInt(999999999));
                pstmt2.setString(1, String.valueOf(random.nextInt(999999999)));
                pstmt2.setString(2, String.valueOf(getId));
                pstmt2.setString(3, category);
                pstmt2.setString(4,  bizId);
                pstmt2.setString(5, "1");
                pstmt2.executeUpdate();
                System.out.println("success");

        	
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
    }

    // ���� ����
    public void Ratingconnect(String Id, String rating, String bizId) {
            
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        String category = null;
        
        int i, j;
        try {
            //OpenApiȣ��
            String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + bizId;
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            
            //���� �б�
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result = result + line.trim();// result = URL�� XML�� ���� ��
            }
            
            // xml �Ľ��ϱ�
            InputSource is = new InputSource(new StringReader(result));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            // XPathExpression expr = xpath.compile("/response/body/items/item");
            XPathExpression expr = xpath.compile("//emp");
            
            //xml ��� ����
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (i = 0; i < nodeList.getLength(); i++) {
                NodeList child = nodeList.item(i).getChildNodes();
	                Node node = child.item(6);

	                	if(node.getTextContent().equals("�������")) {
	                		category = "004001";
	                	}
	                	
	                	else if(node.getTextContent().equals("â������")) {
	                		category = "004002";
	                	}
	                	
	                	else if(node.getTextContent().equals("�ְš�����") ){
	                		category = "004003";
	                	}
	                	
	                	else if(node.getTextContent().equals("��Ȱ������")) {
	                		category = "004004";
	                	}
	                	
	                	else if(node.getTextContent().equals("��å����")) {
	                		category = "004005";
	                	}
	                	
	                	else if(node.getTextContent().equals("�ڷγ�19")) {
	                		category = "004006";
	                	}

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    	
    	// oracle ����
            String jdbcUrl = "jdbc:oracle:thin:@" + "61.245.226.108" + ":1521:orcl";
            String userId = "scott";
            String userPw = "1234";
            
            Connection conn = null;
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            ResultSet rs = null;

            String sql = "";
            String sql2 = "";
            String returns = "a";
            String returns2 = "b";
           
            System.out.println(Id +  " " + category + " " + bizId + " " +rating);
            
            int getId = userId_Num_Get(Id);
            
            try {
            	
            	Class.forName("oracle.jdbc.driver.OracleDriver");
            	conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            	
            	sql = "SELECT BIZID FROM STREAMDATA_RATING WHERE USERID = ? AND BIZID = ?";
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, String.valueOf(getId));
            	pstmt.setString(2, bizId);
            	pstmt.executeQuery();
            	rs = pstmt.executeQuery();
            	
            	if(rs.next()) {
            		sql2 = "UPDATE STREAMDATA_RATING SET RATING = ? WHERE USERID = ? AND BIZID = ?";
                    pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.setString(1, rating);
                    pstmt2.setString(2, String.valueOf(getId));
                    pstmt2.setString(3, bizId);
                    pstmt2.executeUpdate();
                    System.out.println("success");
            	}
            	
            	else {
                	Random random = new Random();
                    
                    sql2 = "INSERT INTO STREAMDATA_RATING VALUES(?,?,?,?,?)";
                    pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.setString(1, String.valueOf(random.nextInt(999999999)));
                    pstmt2.setString(2, String.valueOf(getId));
                    pstmt2.setString(3, category);
                    pstmt2.setString(4,  bizId);
                    pstmt2.setString(5, rating);
                    pstmt2.executeUpdate();
                    System.out.println("success");
            	}

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
                if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
            }
    }

    // ���ƿ� ��������
    public String viewLike(String Id, String bizId) {
        // oracle ����
    	int getId = userId_Num_Get(Id);
    	
        String jdbcUrl = "jdbc:oracle:thin:@" + "61.245.226.108" + ":1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;

        String sql = "";
        String sql2 = "";
        String returns = "a";
        String returns2 = "b";
        
        System.out.println(Id);
        
        try {
        	Random random = new Random();

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            //���ƿ� ��� �ִ��� ����
            sql = "SELECT POLICYLIKE FROM STREAMDATA_LIKE WHERE USERID = ? AND BIZID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(getId));
            pstmt.setString(2, bizId);
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
            	sql2 = "DELETE FROM STREAMDATA_LIKE WHERE USERID = ? AND BIZID = ?";
            	pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, String.valueOf(getId));
                pstmt2.setString(2, bizId);
            	pstmt2.executeUpdate();
            	System.out.println("DELETE success");
            }
            
            else {
            	sql2 = "INSERT INTO STREAMDATA_LIKE VALUES(?,?,?,?)";
            	pstmt2 = conn.prepareStatement(sql2);
            	pstmt2.setString(1, String.valueOf(random.nextInt(999999999)));
            	pstmt2.setString(2, String.valueOf(getId));
            	pstmt2.setString(3, bizId);
            	pstmt2.setString(4, "1");
            	pstmt2.executeUpdate();
            	System.out.println("ADD success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return null;
    }
  
    //���ƿ� ��� �̾ƿ���
    public String getLikeList(String Id) {

    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";

        int getId = userId_Num_Get(Id);
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT BIZID FROM STREAMDATA_LIKE WHERE USERID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(getId));
            rs = pstmt.executeQuery();

            while(rs.next()) {
            	BizId bizid = new BizId();
            	bizid.setBizId(rs.getInt("BIZID"));
            	list.add(bizid);
            	
            	System.out.println(rs.getInt("BIZID"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }

        int size = list.size();
    	
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        String categoryResult = "";
        
        int i, j;
        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
        		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
        
        try {
        	if(size != 0) {
        		
        		for(int cnt = 0; cnt<=size; cnt++) {
        			categoryResult += "<script type=\"text/javascript\">\r\n" + 
        					"	function changeValue"+cnt+"(){\r\n" + 
        					"		\r\n" + 
        					"		if(document.getElementById('like"+ cnt +"').value == \"���ƿ�\"){\r\n" + 
        					"			document.getElementById('like"+ cnt +"').value = \"���ƿ� ���\";\r\n" + 
        					"		}\r\n" + 
        					"		\r\n" + 
        					"		else if(document.getElementById('like"+ cnt +"').value == \"���ƿ� ���\"){			\r\n" + 
        					"			document.getElementById('like"+ cnt +"').value = \"���ƿ�\";\r\n" + 
        					"		}\r\n" + 
        					"	}\r\n" + 
        					"</script>";
        			
        			//OpenApiȣ��
        			String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + list.get(cnt).getBizId();
        			URL url = new URL(urlstr);
        			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
        			
        			//���� �б�
        			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
        			String result = "";
        			String line;
        			while ((line = br.readLine()) != null) {
        				result = result + line.trim();// result = URL�� XML�� ���� ��
        			}
        			
        			// xml �Ľ��ϱ�
        			InputSource is = new InputSource(new StringReader(result));
        			builder = factory.newDocumentBuilder();
        			doc = builder.parse(is);
        			XPathFactory xpathFactory = XPathFactory.newInstance();
        			XPath xpath = xpathFactory.newXPath();
        			// XPathExpression expr = xpath.compile("/response/body/items/item");
        			XPathExpression expr = xpath.compile("//emp");
        			
        			//xml ��� ����
        			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        			
        			for (i = 0; i < nodeList.getLength(); i++) {
        				
        				NodeList child = nodeList.item(i).getChildNodes();
        				
        				categoryResult += "<tr>";
        				Node node = child.item(1);
        				System.out.print(key[0] + " : ");
        				System.out.println(node.getTextContent());
        				String bizId = node.getTextContent();
        				categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
        				
        				String like = getLike(bizId, Id);
        				String likeResult = "";
        				if(like.trim().equals("1")) {
        					like = "���ƿ� ���";
        				}
        				else if(like.trim().equals("0")) {
        					like = "���ƿ�";
        				}
        				
        				node = child.item(4);
        				System.out.print(key[3] + " : ");
        				System.out.println(node.getTextContent());
        				String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + Id +"\">");
        				categoryResult = categoryResult + "<td>" +path + node.getTextContent() + "</A></td>";
        				
        				node = child.item(6);
        				System.out.print(key[5] + " : ");
        				System.out.println(node.getTextContent());
        				//categoryResult = categoryResult + "<td>" + node.getTextContent() + "</td>";
        				categoryResult = categoryResult + 
        						
        						//���ƿ� ��ư
        						"<td><form action = \'sendLike.jsp\' target='param'>" + 
        						"<input type='hidden' name='bizId' value= \'" + bizId + "\'/>" +
        						"<input type='hidden' name='userId' value= \'" + Id + "\'/>" +
        						"<input id = \"like"+ cnt + "\" onclick=\"changeValue"+cnt+"()\" type='submit' value=\"" + like + "\">" +
        						///"<input id = 'like2' type='button' value=\"" + like + "\">" +
        						"</form></td>";
        				
        				categoryResult += "</tr>";
        			}
        		}
        	}else {
        		categoryResult += "�����Ͱ� �������� �ʽ��ϴ�.";
        	}
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(categoryResult);
        return categoryResult;
    }
    
    //��å �̾Ƴ���(ī�װ���)
    public String choosedPolicy(ArrayList<BizId> list, String userId) {
    	
    	int size = list.size();
    	
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        String categoryResult = "";
        
        int i, j, k=0;
        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
        		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
        
        String[] Cate = {"�������", "â������", "�ְ�,����", "��Ȱ, ����", "��å����", "�ڷγ�19"};
        
        String policy_Employment = "x";
        String policy_Corona = "x";
        String policy_HousingSupport = "x";
        String policy_LivingWelfare = "x";
        String policy_Participation = "x";
        String policy_StartUp = "x";
        
        try {
        	if(size != 0) {
          		 for(int cnt = 0; cnt<size; cnt++) {
             		//OpenApiȣ��
                      String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + list.get(cnt).getBizId();
                      URL url = new URL(urlstr);
                      HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                      
                      //���� �б�
                      br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                      String result = "";
                      String line;
                      while ((line = br.readLine()) != null) {
                          result = result + line.trim();// result = URL�� XML�� ���� ��
                      }
                      
                      // xml �Ľ��ϱ�
                      InputSource is = new InputSource(new StringReader(result));
                      builder = factory.newDocumentBuilder();
                      doc = builder.parse(is);
                      XPathFactory xpathFactory = XPathFactory.newInstance();
                      XPath xpath = xpathFactory.newXPath();
                      // XPathExpression expr = xpath.compile("/response/body/items/item");
                      XPathExpression expr = xpath.compile("//emp");
                      
                      NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                      
                      	for (i = 0; i < nodeList.getLength(); i++) {
                      		NodeList child = nodeList.item(i).getChildNodes();
          	            	
          	            	categoryResult += "<tr>";
          	            	
          	            	//ī�װ�
          	                Node node = child.item(6);
          	                String category = node.getTextContent();
          	                
          	               
          	                
          	                if(category.trim().equals("â������")) {
          	                	if(policy_StartUp == "x") {
          	                		policy_StartUp = category;
          	                		System.out.println("�����? : " + category.trim().equals("â������"));
                  	                //��å��
              		                node = child.item(4);
              			            System.out.print(key[3] + " : ");
              		                System.out.println(node.getTextContent());
              		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + list.get(cnt).getBizId() + "&userId=" + userId +"\">");
              		               String policy = node.getTextContent();
              		               categoryResult = categoryResult + "<td width=\"100\" style=\"background-color: #eeeeee; text-align: center;\">"+ category + "</td>"+ "<td>"+ path + policy  + "</A></td>";
          	                	}
          	                }
          	                
          	                else if(category.trim().equals("�������")) {
          	                	if(policy_Employment == "x") {
          	                		policy_Employment = category;
                  	                //��å��
              		                node = child.item(4);
              			            System.out.print(key[3] + " : ");
              		                System.out.println(node.getTextContent());
              		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + list.get(cnt).getBizId() + "&userId=" + userId +"\">");
              		               String policy = node.getTextContent();
              		               categoryResult = categoryResult + "<td width=\"100\" style=\"background-color: #eeeeee; text-align: center;\">"+ category + "</td>"+ "<td>"+ path + policy  + "</A></td>";
          	                	}
          	                }
          	               else if(category.trim().equals("��Ȱ������")) {
          	            	  if(policy_LivingWelfare == "x") {
          	            		 policy_LivingWelfare = category;
               	                //��å��
           		                node = child.item(4);
           			            System.out.print(key[3] + " : ");
           		                System.out.println(node.getTextContent());
           		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + list.get(cnt).getBizId() + "&userId=" + userId +"\">");
           		              String policy = node.getTextContent();
         		               categoryResult = categoryResult + "<td width=\"100\" style=\"background-color: #eeeeee; text-align: center;\">"+ category + "</td>"+ "<td>"+ path + policy  + "</A></td>";
        	                	}
         	                }
          	              else if(category.trim().equals("�ְš�����")) {
          	            	 if(policy_HousingSupport == "x") {
          	            		policy_HousingSupport = category;
              	                //��å��
          		                node = child.item(4);
          			            System.out.print(key[3] + " : ");
          		                System.out.println(node.getTextContent());
          		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + list.get(cnt).getBizId() + "&userId=" + userId +"\">");
          		               String policy = node.getTextContent();
          		               categoryResult = categoryResult + "<td width=\"100\" style=\"background-color: #eeeeee; text-align: center;\">"+ category + "</td>"+ "<td>"+ path + policy  + "</A></td>";
       	                	}
        	                }
          	             else if(category.trim().equals("�ڷγ�19")) {
          	            	if(policy_Corona == "x") {
          	            		policy_Corona = category;
              	                //��å��
          		                node = child.item(4);
          			            System.out.print(key[3] + " : ");
          		                System.out.println(node.getTextContent());
          		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + list.get(cnt).getBizId() + "&userId=" + userId +"\">");
          		               String policy = node.getTextContent();
          		               categoryResult = categoryResult + "<td width=\"100\" style=\"background-color: #eeeeee; text-align: center;\">"+ category + "</td>"+ "<td>"+ path + policy  + "</A></td>";
      	                	}
       	                }
          	            else if(category.trim().equals("��å����")) {
          	            	if(policy_Participation == "x") {
          	            		policy_Participation = category;
              	                //��å��
          		                node = child.item(4);
          			            System.out.print(key[3] + " : ");
          		                System.out.println(node.getTextContent());
          		                String path = String.valueOf("<A href = \"http://61.245.226.108:8181/damoimServer/viewPolicy.jsp?bizId=" + list.get(cnt).getBizId() + "&userId=" + userId +"\">");
          		               String policy = node.getTextContent();
          		               categoryResult = categoryResult + "<td width=\"100\" style=\"background-color: #eeeeee; text-align: center;\">"+ category + "</td>"+ "<td>"+ path + policy  + "</A></td>";
      	                	}
      	                }

          	            }
         	            	
          	           categoryResult += "</tr>";
          	            System.out.println("");

                      }   	
        	}else {
        		categoryResult += "�����Ͱ� �����մϴ�.";
        	}
         
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return categoryResult;
    }
    
    //��å �̾Ƴ���(��ü)
    public String choosedPolicy_ALL(ArrayList<BizId> list, String userId) {

    	int size = list.size();
    	
    	BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        String categoryResult = "";
        
        int i, j, k=0;
        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
        		"���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};
        
        String[] Cate = {"�������", "â������", "�ְ�,����", "��Ȱ, ����", "��å����", "�ڷγ�19"};
        
        String policy_Employment = "x";
        String policy_Corona = "x";
        String policy_HousingSupport = "x";
        String policy_LivingWelfare = "x";
        String policy_Participation = "x";
        String policy_StartUp = "x";
        
        try {
       		 for(int cnt = 0; cnt<size; cnt++) {
            		//OpenApiȣ��
                     String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=1&srchPolicyId=" + list.get(cnt).getBizId();
                     URL url = new URL(urlstr);
                     HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                     
                     //���� �б�
                     br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                     String result = "";
                     String line;
                     while ((line = br.readLine()) != null) {
                         result = result + line.trim();// result = URL�� XML�� ���� ��
                     }
                     
                     // xml �Ľ��ϱ�
                     InputSource is = new InputSource(new StringReader(result));
                     builder = factory.newDocumentBuilder();
                     doc = builder.parse(is);
                     XPathFactory xpathFactory = XPathFactory.newInstance();
                     XPath xpath = xpathFactory.newXPath();
                     // XPathExpression expr = xpath.compile("/response/body/items/item");
                     XPathExpression expr = xpath.compile("//emp");
                     
                     NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                     
                     	for (i = 0; i < nodeList.getLength(); i++) {
                     		NodeList child = nodeList.item(i).getChildNodes();
         	            	
         	            	categoryResult += "<tr>";

         	            	
         	            	categoryResult += "<tr>";
         	            	
         	                Node node = child.item(6);
         	                String category = node.getTextContent();
         	                
         	                node = child.item(4);
         	                String policy = node.getTextContent();
         	                
         	                System.out.print(category + " : ");
         	                System.out.println(policy);
         	                String path = String.valueOf("<A href = \"http://" + serverURL + ":8181/damoimServer/viewPolicy.jsp?bizId=" + list.get(cnt).getBizId() + "&userId=" + userId +"\">");
         	                categoryResult = categoryResult + "<td width=\"100\" style=\"background-color: #eeeeee; text-align: center;\">"+ category + "</td>"+ "<td>"+ path + policy  + "</A></td>";
         	                k++;
         	            }
        	            	
         	           categoryResult += "</tr>";
         	            System.out.println("");
                     }            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return categoryResult;
    }
    
    //����� �ε��� ��ȯ
    public int userId_Num_Get(String Id) {
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";

        int userId_Num = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            String sql = "";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT USERID FROM USERINFORMATION WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                userId_Num = rs.getInt("USERID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }

    	return userId_Num;
    }

    //������õŬ�� ��
    public String collaborateView_userClick(String Id) {
    	
    	int getId = userId_Num_Get(Id);
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        	
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";

        	try {
        		String sql = "";
        		Class.forName("oracle.jdbc.driver.OracleDriver");
        		conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
        		
        		sql = "SELECT recommendations0, recommendations1, recommendations2, recommendations3,"
        				+ "recommendations4, recommendations5, recommendations6, recommendations7,"
        				+ "recommendations8, recommendations9 FROM USER_RECOMMEND_CLICK WHERE USERID = ?";
        		pstmt = conn.prepareStatement(sql);
        		pstmt.setString(1, String.valueOf(getId));
        		rs = pstmt.executeQuery();
        		
        		while(rs.next()) {
        			String num = null;
        			
        			BizId bizid_0 = new BizId();        			
        			BizId bizid_1 = new BizId();
        			BizId bizid_2 = new BizId();
        			BizId bizid_3 = new BizId();
        			BizId bizid_4 = new BizId();
        			BizId bizid_5 = new BizId();
        			BizId bizid_6 = new BizId();
        			BizId bizid_7 = new BizId();
        			BizId bizid_8 = new BizId();
        			BizId bizid_9 = new BizId();

        			num = rs.getString("recommendations0");
        	        num = num.replaceAll(match, "");
        	        String arr[] = num.split(" ");
        	        int policyNum = Integer.parseInt(arr[0]);
        	        System.out.println(policyNum);
        	        bizid_0.setBizId(policyNum);
        			list.add(bizid_0);
        			
        			num = rs.getString("recommendations1");
        	        num = num.replaceAll(match, "");
        	        String arr_1[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_1[0]);
        	        System.out.println(policyNum);
        	        bizid_1.setBizId(policyNum);
        			list.add(bizid_1);
        			
        			num = rs.getString("recommendations2");
        	        num = num.replaceAll(match, "");
        	        String arr_2[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_2[0]);
        	        System.out.println(policyNum);
        	        bizid_2.setBizId(policyNum);
        			list.add(bizid_2);
        			
        			num = rs.getString("recommendations3");
        	        num = num.replaceAll(match, "");
        	        String arr_3[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_3[0]);
        	        System.out.println(policyNum);
        	        bizid_3.setBizId(policyNum);
        			list.add(bizid_3);
        			
        			num = rs.getString("recommendations4");
        	        num = num.replaceAll(match, "");
        	        String arr_4[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_4[0]);
        	        System.out.println(policyNum);
        	        bizid_4.setBizId(policyNum);
        			list.add(bizid_4);
        			
        			num = rs.getString("recommendations5");
        	        num = num.replaceAll(match, "");
        	        String arr_5[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_5[0]);
        	        System.out.println(policyNum);
        	        bizid_5.setBizId(policyNum);
        			list.add(bizid_5);
        			
        			num = rs.getString("recommendations6");
        	        num = num.replaceAll(match, "");
        	        String arr_6[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_6[0]);
        	        System.out.println(policyNum);
        	        bizid_6.setBizId(policyNum);
        			list.add(bizid_6);
        			
        			num = rs.getString("recommendations7");
        	        num = num.replaceAll(match, "");
        	        String arr_7[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_7[0]);
        	        System.out.println(policyNum);
        	        bizid_7.setBizId(policyNum);
        			list.add(bizid_7);
        			
        			num = rs.getString("recommendations8");
        	        num = num.replaceAll(match, "");
        	        String arr_8[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_8[0]);
        	        System.out.println(policyNum);
        	        bizid_8.setBizId(policyNum);
        			list.add(bizid_8);
        			
        			num = rs.getString("recommendations9");
        	        num = num.replaceAll(match, "");
        	        String arr_9[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_9[0]);
        	        System.out.println(policyNum);
        	        bizid_9.setBizId(policyNum);
        			list.add(bizid_9);
        			
        			for(int i = 0; i<10; i++) {
        				System.out.println(list.get(i).getBizId());
        			}
        		}
        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	} finally {
        		if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
        		if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        	}

			return choosedPolicy(list, Id);
    }

    //������õ���� ��
    public String collaborateView_userRating(String Id) {

    	int getId = userId_Num_Get(Id);
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        	
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";

        	try {
        		String sql = "";
        		Class.forName("oracle.jdbc.driver.OracleDriver");
        		conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
        		
        		sql = "SELECT recommendations0, recommendations1, recommendations2, recommendations3,"
        				+ "recommendations4, recommendations5, recommendations6, recommendations7,"
        				+ "recommendations8, recommendations9 FROM USER_RECOMMEND_RATING WHERE USERID = ?";
        		pstmt = conn.prepareStatement(sql);
        		pstmt.setString(1, String.valueOf(getId));
        		rs = pstmt.executeQuery();
        		
        		while(rs.next()) {
        			String num = null;
        			
        			BizId bizid_0 = new BizId();        			
        			BizId bizid_1 = new BizId();
        			BizId bizid_2 = new BizId();
        			BizId bizid_3 = new BizId();
        			BizId bizid_4 = new BizId();
        			BizId bizid_5 = new BizId();
        			BizId bizid_6 = new BizId();
        			BizId bizid_7 = new BizId();
        			BizId bizid_8 = new BizId();
        			BizId bizid_9 = new BizId();

        			num = rs.getString("recommendations0");
        	        num = num.replaceAll(match, "");
        	        String arr[] = num.split(" ");
        	        int policyNum = Integer.parseInt(arr[0]);
        	        System.out.println(policyNum);
        	        bizid_0.setBizId(policyNum);
        			list.add(bizid_0);
        			
        			num = rs.getString("recommendations1");
        	        num = num.replaceAll(match, "");
        	        String arr_1[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_1[0]);
        	        System.out.println(policyNum);
        	        bizid_1.setBizId(policyNum);
        			list.add(bizid_1);
        			
        			num = rs.getString("recommendations2");
        	        num = num.replaceAll(match, "");
        	        String arr_2[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_2[0]);
        	        System.out.println(policyNum);
        	        bizid_2.setBizId(policyNum);
        			list.add(bizid_2);
        			
        			num = rs.getString("recommendations3");
        	        num = num.replaceAll(match, "");
        	        String arr_3[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_3[0]);
        	        System.out.println(policyNum);
        	        bizid_3.setBizId(policyNum);
        			list.add(bizid_3);
        			
        			num = rs.getString("recommendations4");
        	        num = num.replaceAll(match, "");
        	        String arr_4[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_4[0]);
        	        System.out.println(policyNum);
        	        bizid_4.setBizId(policyNum);
        			list.add(bizid_4);
        			
        			num = rs.getString("recommendations5");
        	        num = num.replaceAll(match, "");
        	        String arr_5[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_5[0]);
        	        System.out.println(policyNum);
        	        bizid_5.setBizId(policyNum);
        			list.add(bizid_5);
        			
        			num = rs.getString("recommendations6");
        	        num = num.replaceAll(match, "");
        	        String arr_6[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_6[0]);
        	        System.out.println(policyNum);
        	        bizid_6.setBizId(policyNum);
        			list.add(bizid_6);
        			
        			num = rs.getString("recommendations7");
        	        num = num.replaceAll(match, "");
        	        String arr_7[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_7[0]);
        	        System.out.println(policyNum);
        	        bizid_7.setBizId(policyNum);
        			list.add(bizid_7);
        			
        			num = rs.getString("recommendations8");
        	        num = num.replaceAll(match, "");
        	        String arr_8[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_8[0]);
        	        System.out.println(policyNum);
        	        bizid_8.setBizId(policyNum);
        			list.add(bizid_8);
        			
        			num = rs.getString("recommendations9");
        	        num = num.replaceAll(match, "");
        	        String arr_9[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_9[0]);
        	        System.out.println(policyNum);
        	        bizid_9.setBizId(policyNum);
        			list.add(bizid_9);
        			
        			for(int i = 0; i<10; i++) {
        				System.out.println(list.get(i).getBizId());
        			}
        		}
        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	} finally {
        		if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
        		if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        	}

			return choosedPolicy(list, Id);
    }
    
    //�׷���õŬ�� ��
    public String collaborateView_groupClick(String Id) {

    	int getId = userId_Num_Get(Id);
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        String sql = null;
        String sql2 = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs;
        ResultSet rs2;
        	
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        int groupId = 0;
        	try {
        		
        		Class.forName("oracle.jdbc.driver.OracleDriver");
	        	conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

	        	sql2 = "SELECT PREDICTION FROM USER_GROUP WHERE USERID = ?";
	        	pstmt2 = conn.prepareStatement(sql2);
	        	pstmt2.setString(1, String.valueOf(getId));
	        	rs2 = pstmt2.executeQuery();
	        	
	        	if(rs2.next()) {
	        		groupId = rs2.getInt("PREDICTION");
	        		System.out.println(groupId);
	        		sql = "SELECT recommendations0, recommendations1, recommendations2, recommendations3,"
	        				+ "recommendations4, recommendations5, recommendations6, recommendations7,"
	        				+ "recommendations8, recommendations9 FROM USER_RECOMMEND_GROUP_CLICK WHERE GROUPID = ?";
	        		pstmt = conn.prepareStatement(sql);
	        		pstmt.setString(1, String.valueOf(groupId));
	        		rs = pstmt.executeQuery();
	        		
	        		while(rs.next()) {
	        			String num = null;
	        			
	        			BizId bizid_0 = new BizId();        			
	        			BizId bizid_1 = new BizId();
	        			BizId bizid_2 = new BizId();
	        			BizId bizid_3 = new BizId();
	        			BizId bizid_4 = new BizId();
	        			BizId bizid_5 = new BizId();
	        			BizId bizid_6 = new BizId();
	        			BizId bizid_7 = new BizId();
	        			BizId bizid_8 = new BizId();
	        			BizId bizid_9 = new BizId();
	
	        			num = rs.getString("recommendations0");
	        	        num = num.replaceAll(match, "");
	        	        String arr[] = num.split(" ");
	        	        int policyNum = Integer.parseInt(arr[0]);
	        	        System.out.println(policyNum);
	        	        bizid_0.setBizId(policyNum);
	        			list.add(bizid_0);
	        			
	        			num = rs.getString("recommendations1");
	        	        num = num.replaceAll(match, "");
	        	        String arr_1[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_1[0]);
	        	        System.out.println(policyNum);
	        	        bizid_1.setBizId(policyNum);
	        			list.add(bizid_1);
	        			
	        			num = rs.getString("recommendations2");
	        	        num = num.replaceAll(match, "");
	        	        String arr_2[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_2[0]);
	        	        System.out.println(policyNum);
	        	        bizid_2.setBizId(policyNum);
	        			list.add(bizid_2);
	        			
	        			num = rs.getString("recommendations3");
	        	        num = num.replaceAll(match, "");
	        	        String arr_3[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_3[0]);
	        	        System.out.println(policyNum);
	        	        bizid_3.setBizId(policyNum);
	        			list.add(bizid_3);
	        			
	        			num = rs.getString("recommendations4");
	        	        num = num.replaceAll(match, "");
	        	        String arr_4[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_4[0]);
	        	        System.out.println(policyNum);
	        	        bizid_4.setBizId(policyNum);
	        			list.add(bizid_4);
	        			
	        			num = rs.getString("recommendations5");
	        	        num = num.replaceAll(match, "");
	        	        String arr_5[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_5[0]);
	        	        System.out.println(policyNum);
	        	        bizid_5.setBizId(policyNum);
	        			list.add(bizid_5);
	        			
	        			num = rs.getString("recommendations6");
	        	        num = num.replaceAll(match, "");
	        	        String arr_6[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_6[0]);
	        	        System.out.println(policyNum);
	        	        bizid_6.setBizId(policyNum);
	        			list.add(bizid_6);
	        			
	        			num = rs.getString("recommendations7");
	        	        num = num.replaceAll(match, "");
	        	        String arr_7[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_7[0]);
	        	        System.out.println(policyNum);
	        	        bizid_7.setBizId(policyNum);
	        			list.add(bizid_7);
	        			
	        			num = rs.getString("recommendations8");
	        	        num = num.replaceAll(match, "");
	        	        String arr_8[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_8[0]);
	        	        System.out.println(policyNum);
	        	        bizid_8.setBizId(policyNum);
	        			list.add(bizid_8);
	        			
	        			num = rs.getString("recommendations9");
	        	        num = num.replaceAll(match, "");
	        	        String arr_9[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_9[0]);
	        	        System.out.println(policyNum);
	        	        bizid_9.setBizId(policyNum);
	        			list.add(bizid_9);
	        			
	        			for(int i = 0; i<10; i++) {
	        				System.out.println(list.get(i).getBizId());
	        			}
	        		}
	        	}
	        	        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	} finally {
        		if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
        		if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        	}

			return choosedPolicy(list, Id);
    }
  
    //�׷���õ���� ��
    public String collaborateView_groupRating(String Id){

    	int getId = userId_Num_Get(Id);
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        String sql = null;
        String sql2 = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs;
        ResultSet rs2;
        	
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        int groupId = 0;
        	try {
        		
        		Class.forName("oracle.jdbc.driver.OracleDriver");
	        	conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

	        	sql2 = "SELECT PREDICTION FROM USER_GROUP WHERE USERID = ?";
	        	pstmt2 = conn.prepareStatement(sql2);
	        	pstmt2.setString(1, String.valueOf(getId));
	        	rs2 = pstmt2.executeQuery();
	        	
	        	if(rs2.next()) {
	        		groupId = rs2.getInt("PREDICTION");
	        		System.out.println(groupId);
	        		sql = "SELECT recommendations0, recommendations1, recommendations2, recommendations3,"
	        				+ "recommendations4, recommendations5, recommendations6, recommendations7,"
	        				+ "recommendations8, recommendations9 FROM USER_RECOMMEND_GROUP_RATING WHERE GROUPID = ?";
	        		pstmt = conn.prepareStatement(sql);
	        		pstmt.setString(1, String.valueOf(groupId));
	        		rs = pstmt.executeQuery();
	        		
	        		while(rs.next()) {
	        			String num = null;
	        			
	        			BizId bizid_0 = new BizId();        			
	        			BizId bizid_1 = new BizId();
	        			BizId bizid_2 = new BizId();
	        			BizId bizid_3 = new BizId();
	        			BizId bizid_4 = new BizId();
	        			BizId bizid_5 = new BizId();
	        			BizId bizid_6 = new BizId();
	        			BizId bizid_7 = new BizId();
	        			BizId bizid_8 = new BizId();
	        			BizId bizid_9 = new BizId();
	
	        			num = rs.getString("recommendations0");
	        	        num = num.replaceAll(match, "");
	        	        String arr[] = num.split(" ");
	        	        int policyNum = Integer.parseInt(arr[0]);
	        	        System.out.println(policyNum);
	        	        bizid_0.setBizId(policyNum);
	        			list.add(bizid_0);
	        			
	        			num = rs.getString("recommendations1");
	        	        num = num.replaceAll(match, "");
	        	        String arr_1[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_1[0]);
	        	        System.out.println(policyNum);
	        	        bizid_1.setBizId(policyNum);
	        			list.add(bizid_1);
	        			
	        			num = rs.getString("recommendations2");
	        	        num = num.replaceAll(match, "");
	        	        String arr_2[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_2[0]);
	        	        System.out.println(policyNum);
	        	        bizid_2.setBizId(policyNum);
	        			list.add(bizid_2);
	        			
	        			num = rs.getString("recommendations3");
	        	        num = num.replaceAll(match, "");
	        	        String arr_3[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_3[0]);
	        	        System.out.println(policyNum);
	        	        bizid_3.setBizId(policyNum);
	        			list.add(bizid_3);
	        			
	        			num = rs.getString("recommendations4");
	        	        num = num.replaceAll(match, "");
	        	        String arr_4[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_4[0]);
	        	        System.out.println(policyNum);
	        	        bizid_4.setBizId(policyNum);
	        			list.add(bizid_4);
	        			
	        			num = rs.getString("recommendations5");
	        	        num = num.replaceAll(match, "");
	        	        String arr_5[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_5[0]);
	        	        System.out.println(policyNum);
	        	        bizid_5.setBizId(policyNum);
	        			list.add(bizid_5);
	        			
	        			num = rs.getString("recommendations6");
	        	        num = num.replaceAll(match, "");
	        	        String arr_6[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_6[0]);
	        	        System.out.println(policyNum);
	        	        bizid_6.setBizId(policyNum);
	        			list.add(bizid_6);
	        			
	        			num = rs.getString("recommendations7");
	        	        num = num.replaceAll(match, "");
	        	        String arr_7[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_7[0]);
	        	        System.out.println(policyNum);
	        	        bizid_7.setBizId(policyNum);
	        			list.add(bizid_7);
	        			
	        			num = rs.getString("recommendations8");
	        	        num = num.replaceAll(match, "");
	        	        String arr_8[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_8[0]);
	        	        System.out.println(policyNum);
	        	        bizid_8.setBizId(policyNum);
	        			list.add(bizid_8);
	        			
	        			num = rs.getString("recommendations9");
	        	        num = num.replaceAll(match, "");
	        	        String arr_9[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_9[0]);
	        	        System.out.println(policyNum);
	        	        bizid_9.setBizId(policyNum);
	        			list.add(bizid_9);
	        			
	        			for(int i = 0; i<10; i++) {
	        				System.out.println(list.get(i).getBizId());
	        			}
	        		}
	        	}
	        	        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	} finally {
        		if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
        		if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        	}

			return choosedPolicy(list, Id);
    }

    //������õŬ�� ��_��ü
    public String collaborateView_userClick_ALL(String Id) {
    	
    	int getId = userId_Num_Get(Id);
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        	
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";

        	try {
        		String sql = "";
        		Class.forName("oracle.jdbc.driver.OracleDriver");
        		conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
        		
        		sql = "SELECT recommendations0, recommendations1, recommendations2, recommendations3,"
        				+ "recommendations4, recommendations5, recommendations6, recommendations7,"
        				+ "recommendations8, recommendations9 FROM USER_RECOMMEND_CLICK WHERE USERID = ?";
        		pstmt = conn.prepareStatement(sql);
        		pstmt.setString(1, String.valueOf(getId));
        		rs = pstmt.executeQuery();
        		
        		while(rs.next()) {
        			String num = null;
        			
        			BizId bizid_0 = new BizId();        			
        			BizId bizid_1 = new BizId();
        			BizId bizid_2 = new BizId();
        			BizId bizid_3 = new BizId();
        			BizId bizid_4 = new BizId();
        			BizId bizid_5 = new BizId();
        			BizId bizid_6 = new BizId();
        			BizId bizid_7 = new BizId();
        			BizId bizid_8 = new BizId();
        			BizId bizid_9 = new BizId();

        			num = rs.getString("recommendations0");
        	        num = num.replaceAll(match, "");
        	        String arr[] = num.split(" ");
        	        int policyNum = Integer.parseInt(arr[0]);
        	        System.out.println(policyNum);
        	        bizid_0.setBizId(policyNum);
        			list.add(bizid_0);
        			
        			num = rs.getString("recommendations1");
        	        num = num.replaceAll(match, "");
        	        String arr_1[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_1[0]);
        	        System.out.println(policyNum);
        	        bizid_1.setBizId(policyNum);
        			list.add(bizid_1);
        			
        			num = rs.getString("recommendations2");
        	        num = num.replaceAll(match, "");
        	        String arr_2[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_2[0]);
        	        System.out.println(policyNum);
        	        bizid_2.setBizId(policyNum);
        			list.add(bizid_2);
        			
        			num = rs.getString("recommendations3");
        	        num = num.replaceAll(match, "");
        	        String arr_3[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_3[0]);
        	        System.out.println(policyNum);
        	        bizid_3.setBizId(policyNum);
        			list.add(bizid_3);
        			
        			num = rs.getString("recommendations4");
        	        num = num.replaceAll(match, "");
        	        String arr_4[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_4[0]);
        	        System.out.println(policyNum);
        	        bizid_4.setBizId(policyNum);
        			list.add(bizid_4);
        			
        			num = rs.getString("recommendations5");
        	        num = num.replaceAll(match, "");
        	        String arr_5[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_5[0]);
        	        System.out.println(policyNum);
        	        bizid_5.setBizId(policyNum);
        			list.add(bizid_5);
        			
        			num = rs.getString("recommendations6");
        	        num = num.replaceAll(match, "");
        	        String arr_6[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_6[0]);
        	        System.out.println(policyNum);
        	        bizid_6.setBizId(policyNum);
        			list.add(bizid_6);
        			
        			num = rs.getString("recommendations7");
        	        num = num.replaceAll(match, "");
        	        String arr_7[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_7[0]);
        	        System.out.println(policyNum);
        	        bizid_7.setBizId(policyNum);
        			list.add(bizid_7);
        			
        			num = rs.getString("recommendations8");
        	        num = num.replaceAll(match, "");
        	        String arr_8[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_8[0]);
        	        System.out.println(policyNum);
        	        bizid_8.setBizId(policyNum);
        			list.add(bizid_8);
        			
        			num = rs.getString("recommendations9");
        	        num = num.replaceAll(match, "");
        	        String arr_9[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_9[0]);
        	        System.out.println(policyNum);
        	        bizid_9.setBizId(policyNum);
        			list.add(bizid_9);
        			
        			for(int i = 0; i<10; i++) {
        				System.out.println(list.get(i).getBizId());
        			}
        		}
        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	} finally {
        		if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
        		if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        	}

			return choosedPolicy_ALL(list, Id);
    }

    //������õ���� ��_��ü
    public String collaborateView_userRating_ALL(String Id) {

    	int getId = userId_Num_Get(Id);
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        int k = 1;
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        	
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";

        	try {
        		String sql = "";
        		Class.forName("oracle.jdbc.driver.OracleDriver");
        		conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
        		
        		sql = "SELECT recommendations0, recommendations1, recommendations2, recommendations3,"
        				+ "recommendations4, recommendations5, recommendations6, recommendations7,"
        				+ "recommendations8, recommendations9 FROM USER_RECOMMEND_RATING WHERE USERID = ?";
        		pstmt = conn.prepareStatement(sql);
        		pstmt.setString(1, String.valueOf(getId));
        		rs = pstmt.executeQuery();
        		
        		while(rs.next()) {
        			String num = null;
        			
        			BizId bizid_0 = new BizId();        			
        			BizId bizid_1 = new BizId();
        			BizId bizid_2 = new BizId();
        			BizId bizid_3 = new BizId();
        			BizId bizid_4 = new BizId();
        			BizId bizid_5 = new BizId();
        			BizId bizid_6 = new BizId();
        			BizId bizid_7 = new BizId();
        			BizId bizid_8 = new BizId();
        			BizId bizid_9 = new BizId();

        			num = rs.getString("recommendations0");
        	        num = num.replaceAll(match, "");
        	        String arr[] = num.split(" ");
        	        int policyNum = Integer.parseInt(arr[0]);
        	        System.out.println(policyNum);
        	        bizid_0.setBizId(policyNum);
        			list.add(bizid_0);
        			
        			num = rs.getString("recommendations1");
        	        num = num.replaceAll(match, "");
        	        String arr_1[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_1[0]);
        	        System.out.println(policyNum);
        	        bizid_1.setBizId(policyNum);
        			list.add(bizid_1);
        			
        			num = rs.getString("recommendations2");
        	        num = num.replaceAll(match, "");
        	        String arr_2[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_2[0]);
        	        System.out.println(policyNum);
        	        bizid_2.setBizId(policyNum);
        			list.add(bizid_2);
        			
        			num = rs.getString("recommendations3");
        	        num = num.replaceAll(match, "");
        	        String arr_3[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_3[0]);
        	        System.out.println(policyNum);
        	        bizid_3.setBizId(policyNum);
        			list.add(bizid_3);
        			
        			num = rs.getString("recommendations4");
        	        num = num.replaceAll(match, "");
        	        String arr_4[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_4[0]);
        	        System.out.println(policyNum);
        	        bizid_4.setBizId(policyNum);
        			list.add(bizid_4);
        			
        			num = rs.getString("recommendations5");
        	        num = num.replaceAll(match, "");
        	        String arr_5[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_5[0]);
        	        System.out.println(policyNum);
        	        bizid_5.setBizId(policyNum);
        			list.add(bizid_5);
        			
        			num = rs.getString("recommendations6");
        	        num = num.replaceAll(match, "");
        	        String arr_6[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_6[0]);
        	        System.out.println(policyNum);
        	        bizid_6.setBizId(policyNum);
        			list.add(bizid_6);
        			
        			num = rs.getString("recommendations7");
        	        num = num.replaceAll(match, "");
        	        String arr_7[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_7[0]);
        	        System.out.println(policyNum);
        	        bizid_7.setBizId(policyNum);
        			list.add(bizid_7);
        			
        			num = rs.getString("recommendations8");
        	        num = num.replaceAll(match, "");
        	        String arr_8[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_8[0]);
        	        System.out.println(policyNum);
        	        bizid_8.setBizId(policyNum);
        			list.add(bizid_8);
        			
        			num = rs.getString("recommendations9");
        	        num = num.replaceAll(match, "");
        	        String arr_9[] = num.split(" ");
        	        policyNum = Integer.parseInt(arr_9[0]);
        	        System.out.println(policyNum);
        	        bizid_9.setBizId(policyNum);
        			list.add(bizid_9);
        			
        			for(int i = 0; i<10; i++) {
        				System.out.println(list.get(i).getBizId());
        			}
        		}
        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	} finally {
        		if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
        		if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        	}

			return choosedPolicy_ALL(list, Id);
    }
    
    //�׷���õŬ�� ��_��ü
    public String collaborateView_groupClick_ALL(String Id) {

    	int getId = userId_Num_Get(Id);
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        String sql = null;
        String sql2 = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs;
        ResultSet rs2;
        	
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        int groupId = 0;
        	try {
        		
        		Class.forName("oracle.jdbc.driver.OracleDriver");
	        	conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

	        	sql2 = "SELECT PREDICTION FROM USER_GROUP WHERE USERID = ?";
	        	pstmt2 = conn.prepareStatement(sql2);
	        	pstmt2.setString(1, String.valueOf(getId));
	        	rs2 = pstmt2.executeQuery();
	        	
	        	if(rs2.next()) {
	        		groupId = rs2.getInt("PREDICTION");
	        		System.out.println(groupId);
	        		sql = "SELECT recommendations0, recommendations1, recommendations2, recommendations3,"
	        				+ "recommendations4, recommendations5, recommendations6, recommendations7,"
	        				+ "recommendations8, recommendations9 FROM USER_RECOMMEND_GROUP_CLICK WHERE GROUPID = ?";
	        		pstmt = conn.prepareStatement(sql);
	        		pstmt.setString(1, String.valueOf(groupId));
	        		rs = pstmt.executeQuery();
	        		
	        		while(rs.next()) {
	        			String num = null;
	        			
	        			BizId bizid_0 = new BizId();        			
	        			BizId bizid_1 = new BizId();
	        			BizId bizid_2 = new BizId();
	        			BizId bizid_3 = new BizId();
	        			BizId bizid_4 = new BizId();
	        			BizId bizid_5 = new BizId();
	        			BizId bizid_6 = new BizId();
	        			BizId bizid_7 = new BizId();
	        			BizId bizid_8 = new BizId();
	        			BizId bizid_9 = new BizId();
	
	        			num = rs.getString("recommendations0");
	        	        num = num.replaceAll(match, "");
	        	        String arr[] = num.split(" ");
	        	        int policyNum = Integer.parseInt(arr[0]);
	        	        System.out.println(policyNum);
	        	        bizid_0.setBizId(policyNum);
	        			list.add(bizid_0);
	        			
	        			num = rs.getString("recommendations1");
	        	        num = num.replaceAll(match, "");
	        	        String arr_1[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_1[0]);
	        	        System.out.println(policyNum);
	        	        bizid_1.setBizId(policyNum);
	        			list.add(bizid_1);
	        			
	        			num = rs.getString("recommendations2");
	        	        num = num.replaceAll(match, "");
	        	        String arr_2[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_2[0]);
	        	        System.out.println(policyNum);
	        	        bizid_2.setBizId(policyNum);
	        			list.add(bizid_2);
	        			
	        			num = rs.getString("recommendations3");
	        	        num = num.replaceAll(match, "");
	        	        String arr_3[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_3[0]);
	        	        System.out.println(policyNum);
	        	        bizid_3.setBizId(policyNum);
	        			list.add(bizid_3);
	        			
	        			num = rs.getString("recommendations4");
	        	        num = num.replaceAll(match, "");
	        	        String arr_4[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_4[0]);
	        	        System.out.println(policyNum);
	        	        bizid_4.setBizId(policyNum);
	        			list.add(bizid_4);
	        			
	        			num = rs.getString("recommendations5");
	        	        num = num.replaceAll(match, "");
	        	        String arr_5[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_5[0]);
	        	        System.out.println(policyNum);
	        	        bizid_5.setBizId(policyNum);
	        			list.add(bizid_5);
	        			
	        			num = rs.getString("recommendations6");
	        	        num = num.replaceAll(match, "");
	        	        String arr_6[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_6[0]);
	        	        System.out.println(policyNum);
	        	        bizid_6.setBizId(policyNum);
	        			list.add(bizid_6);
	        			
	        			num = rs.getString("recommendations7");
	        	        num = num.replaceAll(match, "");
	        	        String arr_7[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_7[0]);
	        	        System.out.println(policyNum);
	        	        bizid_7.setBizId(policyNum);
	        			list.add(bizid_7);
	        			
	        			num = rs.getString("recommendations8");
	        	        num = num.replaceAll(match, "");
	        	        String arr_8[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_8[0]);
	        	        System.out.println(policyNum);
	        	        bizid_8.setBizId(policyNum);
	        			list.add(bizid_8);
	        			
	        			num = rs.getString("recommendations9");
	        	        num = num.replaceAll(match, "");
	        	        String arr_9[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_9[0]);
	        	        System.out.println(policyNum);
	        	        bizid_9.setBizId(policyNum);
	        			list.add(bizid_9);
	        			
	        			for(int i = 0; i<10; i++) {
	        				System.out.println(list.get(i).getBizId());
	        			}
	        		}
	        	}
	        	        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	} finally {
        		if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
        		if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        	}

			return choosedPolicy_ALL(list, Id);
    }
  
    //�׷���õ���� ��_��ü
    public String collaborateView_groupRating_ALL(String Id){

    	int getId = userId_Num_Get(Id);
    	
    	String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
        String userId = "scott";
        String userPw = "1234";
        
        ArrayList<BizId> list = new ArrayList<BizId>();
        
        String sql = null;
        String sql2 = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs;
        ResultSet rs2;
        	
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        int groupId = 0;
        	try {
        		
        		Class.forName("oracle.jdbc.driver.OracleDriver");
	        	conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

	        	sql2 = "SELECT PREDICTION FROM USER_GROUP WHERE USERID = ?";
	        	pstmt2 = conn.prepareStatement(sql2);
	        	pstmt2.setString(1, String.valueOf(getId));
	        	rs2 = pstmt2.executeQuery();
	        	
	        	if(rs2.next()) {
	        		groupId = rs2.getInt("PREDICTION");
	        		System.out.println(groupId);
	        		sql = "SELECT recommendations0, recommendations1, recommendations2, recommendations3,"
	        				+ "recommendations4, recommendations5, recommendations6, recommendations7,"
	        				+ "recommendations8, recommendations9 FROM USER_RECOMMEND_GROUP_RATING WHERE GROUPID = ?";
	        		pstmt = conn.prepareStatement(sql);
	        		pstmt.setString(1, String.valueOf(groupId));
	        		rs = pstmt.executeQuery();
	        		
	        		while(rs.next()) {
	        			String num = null;
	        			
	        			BizId bizid_0 = new BizId();        			
	        			BizId bizid_1 = new BizId();
	        			BizId bizid_2 = new BizId();
	        			BizId bizid_3 = new BizId();
	        			BizId bizid_4 = new BizId();
	        			BizId bizid_5 = new BizId();
	        			BizId bizid_6 = new BizId();
	        			BizId bizid_7 = new BizId();
	        			BizId bizid_8 = new BizId();
	        			BizId bizid_9 = new BizId();
	
	        			num = rs.getString("recommendations0");
	        	        num = num.replaceAll(match, "");
	        	        String arr[] = num.split(" ");
	        	        int policyNum = Integer.parseInt(arr[0]);
	        	        System.out.println(policyNum);
	        	        bizid_0.setBizId(policyNum);
	        			list.add(bizid_0);
	        			
	        			num = rs.getString("recommendations1");
	        	        num = num.replaceAll(match, "");
	        	        String arr_1[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_1[0]);
	        	        System.out.println(policyNum);
	        	        bizid_1.setBizId(policyNum);
	        			list.add(bizid_1);
	        			
	        			num = rs.getString("recommendations2");
	        	        num = num.replaceAll(match, "");
	        	        String arr_2[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_2[0]);
	        	        System.out.println(policyNum);
	        	        bizid_2.setBizId(policyNum);
	        			list.add(bizid_2);
	        			
	        			num = rs.getString("recommendations3");
	        	        num = num.replaceAll(match, "");
	        	        String arr_3[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_3[0]);
	        	        System.out.println(policyNum);
	        	        bizid_3.setBizId(policyNum);
	        			list.add(bizid_3);
	        			
	        			num = rs.getString("recommendations4");
	        	        num = num.replaceAll(match, "");
	        	        String arr_4[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_4[0]);
	        	        System.out.println(policyNum);
	        	        bizid_4.setBizId(policyNum);
	        			list.add(bizid_4);
	        			
	        			num = rs.getString("recommendations5");
	        	        num = num.replaceAll(match, "");
	        	        String arr_5[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_5[0]);
	        	        System.out.println(policyNum);
	        	        bizid_5.setBizId(policyNum);
	        			list.add(bizid_5);
	        			
	        			num = rs.getString("recommendations6");
	        	        num = num.replaceAll(match, "");
	        	        String arr_6[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_6[0]);
	        	        System.out.println(policyNum);
	        	        bizid_6.setBizId(policyNum);
	        			list.add(bizid_6);
	        			
	        			num = rs.getString("recommendations7");
	        	        num = num.replaceAll(match, "");
	        	        String arr_7[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_7[0]);
	        	        System.out.println(policyNum);
	        	        bizid_7.setBizId(policyNum);
	        			list.add(bizid_7);
	        			
	        			num = rs.getString("recommendations8");
	        	        num = num.replaceAll(match, "");
	        	        String arr_8[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_8[0]);
	        	        System.out.println(policyNum);
	        	        bizid_8.setBizId(policyNum);
	        			list.add(bizid_8);
	        			
	        			num = rs.getString("recommendations9");
	        	        num = num.replaceAll(match, "");
	        	        String arr_9[] = num.split(" ");
	        	        policyNum = Integer.parseInt(arr_9[0]);
	        	        System.out.println(policyNum);
	        	        bizid_9.setBizId(policyNum);
	        			list.add(bizid_9);
	        			
	        			for(int i = 0; i<10; i++) {
	        				System.out.println(list.get(i).getBizId());
	        			}
	        		}
	        	}
	        	        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	} finally {
        		if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
        		if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        	}

			return choosedPolicy_ALL(list, Id);
    }

}




































