package com.example.TestVer.Airport.getSchedule;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class International {
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }
    private static String getTagValue2(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }
    public  List<List<String>> getInternational(String schDeptCityCode,String schArrvCityCode,String schDate) throws IOException, ParserConfigurationException, SAXException {
            String pageNo = "1";
            int result = 0;
            List<String> liststart = new ArrayList<>();
            List<List<String>> resultList = new ArrayList<>();
            String test1 = null;
            String test2 = null;
            StringBuilder urlBuilder = new StringBuilder("http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=N%2FvZ6UMgHmkSrjlrj3icHZcHDy2Vd%2B%2B5CVENT57s2ZvDAAlfIE6B0v8yjXnXrvRdiK4Xbs8i3HgMLNA564iZHw%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("schDate","UTF-8") + "=" + URLEncoder.encode(schDate, "UTF-8")); /*국내 / 국제*/
            urlBuilder.append("&" + URLEncoder.encode("schDeptCityCode","UTF-8") + "=" + URLEncoder.encode(schDeptCityCode, "UTF-8")); /*도착 / 출발*/
            urlBuilder.append("&" + URLEncoder.encode("schArrvCityCode","UTF-8") + "=" + URLEncoder.encode(schArrvCityCode, "UTF-8")); /*공항코드*/
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(urlBuilder.toString());
            doc.getDocumentElement().normalize();
            NodeList nList2 = doc.getElementsByTagName("body");//값 총개수
            Node node2 = nList2.item(0);
            Element eElement2 = (Element) node2;
            System.out.println("총개수 : " + getTagValue2("totalCount",eElement2));
            int total = Integer.parseInt(getTagValue2("totalCount",eElement2));
            if(total%10 == 0){
                result = total/10;
            }else if(total%10 >0){
                result = total/10 +1;
            }//총 값 개수 받아오는 코드
            for(int i = 1;i<=result;i++){
                StringBuilder urlBuilder2 = new StringBuilder("http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList"); /*URL*/
                urlBuilder2.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=N%2FvZ6UMgHmkSrjlrj3icHZcHDy2Vd%2B%2B5CVENT57s2ZvDAAlfIE6B0v8yjXnXrvRdiK4Xbs8i3HgMLNA564iZHw%3D%3D"); /*Service Key*/
                urlBuilder2.append("&" + URLEncoder.encode("schDate","UTF-8") + "=" + URLEncoder.encode(schDate, "UTF-8")); /*국내 / 국제*/
                urlBuilder2.append("&" + URLEncoder.encode("schDeptCityCode","UTF-8") + "=" + URLEncoder.encode(schDeptCityCode, "UTF-8")); /*도착 / 출발*/
                urlBuilder2.append("&" + URLEncoder.encode("schArrvCityCode","UTF-8") + "=" + URLEncoder.encode(schArrvCityCode, "UTF-8")); /*공항코드*/
                urlBuilder2.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(i), "UTF-8"));
                DocumentBuilderFactory dbFactoty2 = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder2 = dbFactoty2.newDocumentBuilder();
                Document doc2 = dBuilder2.parse(urlBuilder2.toString());
                doc2.getDocumentElement().normalize();
                NodeList nList = doc2.getElementsByTagName("item");
                for(int temp = 0; temp < nList.getLength(); temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){
                        Element eElement = (Element) nNode;
                        test1= getTagValue("airlineKorean", eElement);//항공사명
                        test2 = getTagValue("internationalTime", eElement);//출발예정시간
                    }	// for end

                    liststart.add(0,test1);
                    liststart.add(1,test2);
                    resultList.add(liststart);
                    liststart = new ArrayList<>();

                }	// if end
                int pageInt = Integer.parseInt(pageNo);
                pageInt++;
                pageNo = Integer.toString(pageInt);
            }
            return resultList;
        }
}
