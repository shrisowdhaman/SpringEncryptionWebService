package com.shri.ws.shared;

import java.io.StringReader;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.shri.ws.shared.AESEncrypted;
import com.shri.ws.vo.Outbound;


/**
 * @author shrisowdhaman
 *
 * Nov 21, 2017
 */
public class XmlToObject {

	
	static Logger log = Logger.getLogger(XmlToObject.class.getName());
	
	public static Object convert (Class entityClass,String xml, Outbound outbound) throws SAXException{
		
		log.info("Entity class "+ entityClass.getName());
		//XML Validation 
		ValidationEventCollector vec = new ValidationEventCollector();
		
		Object  inbound = null;
		try {
			//It generate the URL entityClass.getResource() for the folder  
			URL xsdURL = entityClass.getResource("schema.xsd");   
			
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
	        Schema schema = sf.newSchema(xsdURL); 
	        
	        
	        JAXBContext jaxbContext = JAXBContext.newInstance(entityClass);
		
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); 
			unmarshaller.setSchema(schema);
		    unmarshaller.setEventHandler(vec);
		    
			StringReader reader = new StringReader(xml);
			inbound = (Object)unmarshaller.unmarshal(reader);
			 
		
		} catch (JAXBException err) {
			if (vec != null && vec.hasEvents()) {
	            for (ValidationEvent ve : vec.getEvents()) {
	            	
	                String msg = ve.getMessage();
	                ValidationEventLocator vel = ve.getLocator(); 
	                int numLigne = vel.getLineNumber();
	                int numColonne = vel.getColumnNumber();
	             
	               log.info("Error validation xml" + "Error : " + numLigne + "." + numColonne + ": " + msg +" : "+ ve.getSeverity());
	            }
	        }
			outbound.setStatus("failure");
			if (err.getLinkedException () == null){
				log.debug(err.toString());
			}else{
				log.debug(err.getLinkedException().getMessage());
			}
		}  
		
		return inbound;
	}
}
