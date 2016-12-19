package com.rjxx.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {
	/**
	 * json 数据转换对象
	 * 
	 * @param Element
	 *            要转换的Element数据
	 * @param pojo
	 *            要转换的目标对象类型
	 * @return 转换的目标对象
	 * @throws Exception
	 *             转换失败
	 */
	@SuppressWarnings("rawtypes")
	public static Object fromXmlToBean(Element rootElt, Class pojo) throws Exception {
		// 首先得到pojo所定义的字段
		Field[] fields = pojo.getDeclaredFields();
		// 根据传入的Class动态生成pojo对象
		Object obj = pojo.newInstance();
		for (Field field : fields) {
			// 设置字段可访问（必须，否则报错）
			field.setAccessible(true);
			// 得到字段的属性名
			String name = field.getName();
			// 这一段的作用是如果字段在Element中不存在会抛出异常，如果出异常，则跳过。
			try {
				rootElt.elementTextTrim(name);
			} catch (Exception ex) {
				continue;
			}
			if (rootElt.elementTextTrim(name) != null && !"".equals(rootElt.elementTextTrim(name))) {
				// 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
				if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
					field.set(obj, Long.parseLong(rootElt.elementTextTrim(name)));
				} else if (field.getType().equals(String.class)) {
					field.set(obj, rootElt.elementTextTrim(name));
				} else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
					field.set(obj, Double.parseDouble(rootElt.elementTextTrim(name)));
				} else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
					field.set(obj, Integer.parseInt(rootElt.elementTextTrim(name)));
				} else if (field.getType().equals(java.util.Date.class)) {
					field.set(obj, Date.parse(rootElt.elementTextTrim(name)));
				} else {
					continue;
				}
			}
		}
		return obj;
	}

	/**
	 * 将xml转换为 Map
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */

	public static Map<String, Object> xml2Map(String xml) throws Exception {

		return xmlDoc2Map(DocumentHelper.parseText(xml));
	}

	/**
	 * 将 xml 文件转成Map
	 * 
	 * @paramxmlDoc
	 * @return
	 */

	public static Map<String, Object> xmlDoc2Map(Document xmlDoc) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (xmlDoc == null) {

			return map;
		}
		Element root = xmlDoc.getRootElement();

		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			List list = e.elements();

			if (list.size() > 0) {

				map.put(e.getName(), Dom2Map(e, map));
			} else {

				map.put(e.getName(), e.getText());
			}
		}

		return map;
	}

	private static Map Dom2Map(Element e, Map map) {
		List list = e.elements();

		if (list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if (iter.elements().size() > 0) {
					Map m = Dom2Map(iter, map);

					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());

						if (!obj.getClass().getName().equals("java.util.ArrayList")) {

							mapList = new ArrayList();

							mapList.add(obj);

							mapList.add(m);
						}

						if (obj.getClass().getName().equals("java.util.ArrayList")) {

							mapList = (List) obj;

							mapList.add(m);
						}

						map.put(iter.getName(), mapList);
					} else {

						map.putAll(m);
					}
				} else {

					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());

						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList<>();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else {
						map.put(iter.getName(), iter.getText());
					}
				}
			}
		} else {
			map.put(e.getName(), e.getText());
		}
		return map;
	}

	public static void main(String[] args) {
		String text = " <xml> <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName> "
				+ "<FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>"
				+ "<CreateTime>1395658984</CreateTime><MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event><MsgID>200163840</MsgID>"
				+ "<Status><![CDATA[failed: system failed]]></Status></xml>";
		try {
			Map res = xml2Map(text);
			System.out.println(res.get("ToUserName"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}