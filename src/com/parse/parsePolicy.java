package com.parse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class parsePolicy {

	private static parsePolicy instance = new parsePolicy();
	
    public static parsePolicy getInstance() {
        return instance;
    }
    public parsePolicy() {  }	
	
    public String xmlData(String cnt, String category) {

    	String result = "";
    	
        BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        String[] key = {"��å id", "��å �Ϸù�ȣ", "��� �� ����ü ����", "��å��", "��å�Ұ�", "��å����", "�����Ը�", "��������", "�������-����", "�������-�������","�������-�о�",
                "���� ���-����", "�������-Ưȭ�о�", "��û�����", "��û���", "��û����", "�ɻ��ǥ", "����Ʈ �ּ�"};

        try {
                //OpenApiȣ��
                String urlstr = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex="+ cnt +"&display=100&bizTycdSel=" + category;
                URL url = new URL(urlstr);

                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

                //���� �б�
                br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                
                String line;
                while ((line = br.readLine()) != null) {
                    result = result + line.trim();// result = URL�� XML�� ���� ��
                }
                
                // xml �Ľ��ϱ�
                InputSource is = new InputSource(new StringReader(result));
                builder = factory.newDocumentBuilder();
                doc = builder.parse(is);
                //doc = builder.parse(is);
                XPathFactory xpathFactory = XPathFactory.newInstance();
                XPath xpath = xpathFactory.newXPath();
                XPathExpression expr = xpath.compile("//emp");

                //xml ��� ����
                NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                for (int i = 0; i < nodeList.getLength(); i++) {

                    NodeList child = nodeList.item(i).getChildNodes();

                    Node node = child.item(1);

                    System.out.println(node.getTextContent());
                    String bizId = node.getTextContent();

                    node = child.item(4);

                    System.out.println(node.getTextContent());
                    String policyName = node.getTextContent();
                   //String path = String.valueOf("<A href = \"http://" + serverURL + ":8181/damoimServer/viewPolicy.jsp?bizId=" + bizId + "&userId=" + userId +"\">");

                    node = child.item(6);

                    System.out.println(node.getTextContent());
                    String categoryName = node.getTextContent();
                }

           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
