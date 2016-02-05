package util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LayoutUtil {

	public static Map<String, String> ParseLayout(String filePath) {

		Map<String, String> UnitMap = new HashMap<String, String>();

		Document doc = null;
		try {
			doc = new SAXReader().read(new File(filePath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		if (root.attributeValue("id") != null && root.attributeValue("id").length() > 0) {
			UnitMap.put(root.attributeValue("id"), root.getName());
			// System.out.println("root：" + root.getName() + ",content：" +
			// root.attributeValue("id"));

		}

		getElement(root, UnitMap);

		return UnitMap;

	}

	private static void getElement(Element element, Map<String, String> map) {
		List<?> list = element.elements();
		// 递归方法
		for (Iterator<?> its = list.iterator(); its.hasNext();) {
			Element chileEle = (Element) its.next();
			if (chileEle.attributeValue("id") != null && chileEle.attributeValue("id").length() > 0) {
				map.put(chileEle.attributeValue("id"), chileEle.getName());
				// System.out.println("dom："+chileEle.getName()+",content："+chileEle.attributeValue("id"));
			}

			getElement(chileEle, map);
		}
	}

}