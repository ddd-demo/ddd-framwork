package com.fengtaiguang.ddd.framwork.domain.service;

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

public class ServiceFactory implements IServiceFactory {

	private Map<String, IDomainService> services = new HashMap<String, IDomainService>();

	public ServiceFactory(InputStream inputStream) {
		try {
			this.load(inputStream);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void load(InputStream inputStream) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		SAXReader xmlReader = new SAXReader();
		Document document = null;
		try {
			
			document = xmlReader.read(inputStream);
		}
		catch (DocumentException e) {
			throw e;
		}
		Map<String, String> xmlMap = new HashMap<String, String>();
		xmlMap.put("abc", "http://www.39541240.com/services");
		xmlMap.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");

		XPath xpath = DocumentHelper
				.createXPath("abc:services/abc:service");
		xpath.setNamespaceURIs(xmlMap);

		@SuppressWarnings("unchecked")
		List<Node> nodes = xpath.selectNodes(document);
		
		for(Node n : nodes){
			Element e = (Element)n;
			String interfaceName = e.attributeValue("interface");
			String implementationName = e.attributeValue("implementation");
			Boolean enable_interceptor = Boolean.parseBoolean( e.attributeValue("enable_interceptor"));
			
			Class<?> clsInter = Class.forName(interfaceName);
			Class<?> clsImpl = Class.forName(implementationName);

			boolean isAssgin1 = clsInter.isAssignableFrom(clsImpl);
			boolean isAssgin2 = IDomainService.class.isAssignableFrom(clsImpl);
			
			if (!isAssgin1 || !isAssgin2) {
				throw new NotImplementedException(StringUtils.EMPTY);
			}
			
			IDomainService service = (IDomainService) clsImpl.newInstance();
			if(enable_interceptor){
				String ceptor = e.attributeValue("interceptor");
				InterfaceProxy proxy = (InterfaceProxy) Class.forName(ceptor).newInstance();
				
				service = (IDomainService) proxy.bind(service);
			}
			this.services.put(clsInter.getName(), service);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> cls) {
		String name = cls.getName();
		if(this.services.containsKey(name)){
			return (T) this.services.get(name);
		}
		return null;
	}
}
