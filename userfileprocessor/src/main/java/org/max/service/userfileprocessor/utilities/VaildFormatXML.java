package org.max.service.userfileprocessor.utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.max.service.userfileprocessor.error.InvaidFormatException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VaildFormatXML implements IFormatter{
	
	String pathXml ;
	public VaildFormatXML(String path) {
		
		// TODO Auto-generated constructor stub
		
		this.pathXml = path;
	}

	@Override
	public ArrayList<LinkedHashMap<String,String>> getVaildFormat() throws InvaidFormatException {
		
		File rd =null;
		DocumentBuilderFactory dbFactory =null;
		DocumentBuilder dBuilder =null;
		
		ArrayList<LinkedHashMap<String,String>> listFormat = new ArrayList<LinkedHashMap<String,String>>();
		if(pathXml!=null && !pathXml.trim().isEmpty()){
			
			try{
				 rd= new File(pathXml);
				 dbFactory = DocumentBuilderFactory.newInstance();
			    dBuilder = dbFactory.newDocumentBuilder();
			    Document document = dBuilder.parse(rd);
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
