package com.tm.utils.common;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParser {
	
	private XPath xPathInstance;
	private Document doc;

	public XMLParser(String filePath) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true); // never forget this!
	    DocumentBuilder builder = domFactory.newDocumentBuilder();
	    doc = builder.parse(filePath);

	    XPathFactory factory = XPathFactory.newInstance();
	    xPathInstance = factory.newXPath();
	}

	public String getStringValue(String xPath) throws XPathExpressionException {
		XPathExpression expr = xPathInstance.compile(xPath);
	    Object result = expr.evaluate(doc, XPathConstants.STRING);
	    return (String) result;
	}

}
