package com.fengtaiguang.ddd.framwork.domain.repository;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import com.fengtaiguang.ddd.framwork.utils.InterfaceProxy;

class RepositoryFactory implements IRepositoryFactory {

	private Map<String, IDao> repositories;

	public RepositoryFactory(InputStream stream) {
		this.repositories = new HashMap<String, IDao>();
		try {
			this.load(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
	private void load(InputStream stream) throws FileNotFoundException,
			DocumentException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		SAXReader xmlReader = new SAXReader();
		Document document = null;
		try {

			document = xmlReader.read(stream);
		} catch (DocumentException e) {
			throw e;
		}
		Map<String, String> xmlMap = new HashMap<String, String>();
		xmlMap.put("abc", "http://www.39541240.com/repository");
		xmlMap.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");

		XPath xpath = DocumentHelper
				.createXPath("abc:repositories/abc:repository");
		xpath.setNamespaceURIs(xmlMap);

		List<Node> nodes = xpath.selectNodes(document);

		for (Node n : nodes) {
			Element e = (Element) n;
			String inter = e.attributeValue("interface");
			String impl = e.attributeValue("implementation");
			boolean enable_interceptor = Boolean.parseBoolean(e
					.attributeValue("enable_interceptor"));

			Class<?> clsInter = Class.forName(inter);
			Class<?> clsImpl = Class.forName(impl);

			boolean isAssgin1 = clsInter.isAssignableFrom(clsImpl);
			boolean isAssgin2 = IDao.class.isAssignableFrom(clsImpl);

			if (!isAssgin1 || !isAssgin2) {
				throw new NotImplementedException(StringUtils.EMPTY);
			}

			IDao dao = (IDao) clsImpl.newInstance();
			if (enable_interceptor) {
				String ceptor = e.attributeValue("interceptor");
				InterfaceProxy proxy = (InterfaceProxy) Class.forName(ceptor)
						.newInstance();

				dao = (IDao) proxy.bind(dao);
			}
			this.repositories.put(clsInter.getName(), dao);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> cls) {
		String name = cls.getName();

		if (this.repositories.containsKey(name)) {
			return (T) this.repositories.get(name);
		}
		return null;
	}
}
