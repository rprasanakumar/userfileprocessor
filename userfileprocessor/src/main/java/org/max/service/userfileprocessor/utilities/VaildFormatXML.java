package org.max.service.userfileprocessor.utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.io.Resources;
import org.max.service.userfileprocessor.error.InvaidFormatException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** 
 * Service class implementing  ICommonService IFormatter
 *   
 * @author Prasanna Kumar
 * @version 0.0.1
 */



public class VaildFormatXML implements IFormatter{
	
	String pathXml ;
	public VaildFormatXML(String path) {
		
		// TODO Auto-generated constructor stub
		
		this.pathXml = path;
	}

	/**
	 * Sevice method for pulling the valid formats configured in (/config/validformats.xml)
	 * 
	 *@return list of type  LinkedHashMap
	 * 
	 */
	
	
	@Override
	public ArrayList<LinkedHashMap<String,String>> getVaildFormat() throws InvaidFormatException {
		
		DocumentBuilderFactory dbFactory =null;
		DocumentBuilder dBuilder =null;
		
		ArrayList<LinkedHashMap<String,String>> listFormat = new ArrayList<LinkedHashMap<String,String>>();
		if(pathXml!=null && !pathXml.trim().isEmpty()){
			
			try{
				//InputStream resourceAsStream = getClass().getResourceAsStream(pathXml);
				//InputStream input = getClass().getResourceAsStream(pathXml);
				InputStream reader = Resources.getResourceAsStream(pathXml);
				 dbFactory = DocumentBuilderFactory.newInstance();
			    dBuilder = dbFactory.newDocumentBuilder();
			    Document document = dBuilder.parse(reader);
			    document.getDocumentElement().normalize();
			    NodeList nodeList = document.getElementsByTagName("format");
			    for (int item = 0; item < nodeList.getLength(); item++) {
			    	LinkedHashMap<String,String>fields =new LinkedHashMap<String,String>();
			        Node nNode = nodeList.item(item);
			        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			            Element e = (Element) nNode;
			            NodeList fieldNames = e.getElementsByTagName("field");
			            for (int i = 0; i < fieldNames.getLength(); i++) {
			            	Node currItem = fieldNames.item(i);
			            	fields.put(currItem.getTextContent(), currItem.getAttributes().getNamedItem("type").getNodeValue()) ;
			            }
			        
			            
			        }
			        listFormat.add(fields);
			    }
			}
			catch(Exception ex){
				ex.getMessage();
			}
			
		}
	
		return listFormat;
	}

}
