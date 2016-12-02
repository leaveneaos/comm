package com.rjxx.test;
import java.io.StringReader;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
  
import org.dom4j.Document;  
import org.dom4j.DocumentException;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element;  

import com.rjxx.utils.XmlUtil;  
  
public class XmlUtilTest {  
  
    /** 
     * @param args 
     * @throws DocumentException 
     */  
    public static void main(String[] args) throws DocumentException {  
        // TODO Auto-generated method stub  
  
        
        String xmlss ="<?xml version=\"1.0\" encoding=\"gbk\"?>\n"
        		+ "<business id=\"FPKJ\" comment=\"发票开具\">\n"
        		+ "<REQUEST_COMMON_FPKJ class=\"REQUEST_COMMON_FPKJ\">\n"
        		+ "<COMMON_FPKJ_FPT class=\"COMMON_FPKJ_FPT\">\n"
        		+ "<FPQQLSH>521</FPQQLSH>\n"
        		+ "<KPLX>0</KPLX>\n"
        		+ "<BMB_BBH>12.0</BMB_BBH>\n"
        		+ "<ZSFS>0</ZSFS>\n"
        		+ "<XSF_NSRSBH>50012345671180278</XSF_NSRSBH>\n"
        		+ "<XSF_MC>兴业银行3</XSF_MC>\n"
        		+ "<XSF_DZDH>123、2222222</XSF_DZDH>\n"
        		+ "<XSF_YHZH>321</XSF_YHZH>\n"
        		+ "<GMF_NSRSBH></GMF_NSRSBH>\n"
        		+ "<GMF_MC>张三</GMF_MC>\n"
        		+ "<GMF_DZDH></GMF_DZDH>\n"
        		+ "<GMF_YHZH>643</GMF_YHZH>\n"
        		+ "<KPR>李四</KPR>\n"
        		+ "<SKR>李四</SKR>\n"
        		+ "<FHR>王五</FHR>\n"
        		+ "<YFP_DM></YFP_DM>\n"
        		+ "<YFP_HM></YFP_HM>\n"
        		+ "<JSHJ>12000.00</JSHJ>\n"
        		+ "<HJJE>10256.41</HJJE>\n"
        		+ "<HJSE>1743.59</HJSE>\n"
        		+ "<KCE></KCE>\n"
        		+ "<BZ>备注</BZ>\n"
        		+ "</COMMON_FPKJ_FPT>\n"
        + "<COMMON_FPKJ_XMXXS class=\"COMMON_FPKJ_XMXX\" size=\"1\">\n"
        		+ "<COMMON_FPKJ_XMXX>\n"
        		+ "<FPHXZ>0</FPHXZ>\n"
        		+ "<SPBM>1040201990000000000</SPBM>\n"
        		+ "<ZXBM></ZXBM>\n"
        		+ "<YHZCBS>0</YHZCBS>\n"
        		+ "<LSLBS></LSLBS>\n"
        		+ "<ZZSTSGL></ZZSTSGL>\n"
        		+ "<XMMC>金税软件11</XMMC>\n"
        		+ "<GGXH></GGXH>\n"
        		+ "<DW>套</DW>\n"
        		+ "<XMSL>1</XMSL>\n"
        		+ "<XMDJ>10256.410000</XMDJ>\n"
        		+ "<XMJE>10256.41</XMJE>\n"
        		+ "<SL>0.17</SL>\n"
        		+ "<SE>1743.59</SE>\n"
        		+ "</COMMON_FPKJ_XMXX>\n"
        		+ "</COMMON_FPKJ_XMXXS>\n"
        		+ "</REQUEST_COMMON_FPKJ>\n"
        		+ "</business>";
        COMMON_FPKJ_FPT  fpt = getCOMMON_FPKJ_FPT(xmlss);
       
        System.out.println(fpt.getFPQQLSH());
    List list=   getCommon_fpkj_xmxxList(xmlss);
    
    for(int i=0;i<list.size();i++){
    	COMMON_FPKJ_XMXX t = (COMMON_FPKJ_XMXX)list.get(i);
    	 System.out.println(t.getFPHXZ());
    	 System.out.println(t.getSPBM());
    }
    
    }  
  
    
    /** 
     * 把xml转化为fpt集合 
     *  
     * @param xml 
     * @return 
     */  
    public static COMMON_FPKJ_FPT getCOMMON_FPKJ_FPT(String xml) {  
  
        Document doc = null;  
         

        //List<Person> list = new ArrayList<Person>(); 
        COMMON_FPKJ_FPT fpt = new COMMON_FPKJ_FPT();
        try {  
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML  
  
            Element rootElt = doc.getRootElement(); // 获取根节点  
  
           
                Element elementGroupService = (Element) rootElt.element("REQUEST_COMMON_FPKJ").element("COMMON_FPKJ_FPT");  
                fpt = (COMMON_FPKJ_FPT) XmlUtil.fromXmlToBean(  
                        elementGroupService, COMMON_FPKJ_FPT.class);  
                //list.add(baseBean);  

        } catch (Exception e) {  
            // TODO: handle exception  
            System.out.println("数据解析错误");  
        }  
  
        return fpt;  
    }  
    
   
  
    /** 
     * 把xml转化为person集合 
     *  
     * @param xml 
     * @return 
     */  
    public static List<COMMON_FPKJ_XMXX> getCommon_fpkj_xmxxList(String xml) {  
  
        Document doc = null;  
        List<COMMON_FPKJ_XMXX> list = new ArrayList<COMMON_FPKJ_XMXX>();  
        try {  
  
            // 读取并解析XML文档  
  
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来  
  
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档  
  
            // Document document = reader.read(new File("User.hbm.xml"));  
  
            // 下面的是通过解析xml字符串的  
  
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML  
  
            Element rootElt = doc.getRootElement(); // 获取根节点  
    
           /* String returnCode = rootElt.elementTextTrim("desc");  
            if (!"0".equals(returnCode)) {  
                System.out.println("后台数据返回有问题");  
                return null;  
            }  */

            List<Element> it = rootElt.selectNodes("//REQUEST_COMMON_FPKJ//COMMON_FPKJ_XMXXS//COMMON_FPKJ_XMXX");
            
            for(int i=0;i<it.size();i++){
            	Element elementGroupService = (Element) it.get(i);  
                COMMON_FPKJ_XMXX baseBean = (COMMON_FPKJ_XMXX) XmlUtil.fromXmlToBean(  
                        elementGroupService, COMMON_FPKJ_XMXX.class);  
                list.add(baseBean);  
            }
            
  
        } catch (Exception e) {  
            // TODO: handle exception  
            System.out.println("数据解析错误");  
        }  
  
        return list;  
    }  
  
}  