package com.shri.ws.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

/**
 * @author shrisowdhaman
 * Nov 15, 2017
 */
@XmlType(name = "RES",propOrder={"status"})
@XmlRootElement(name ="REQ")
public class Outbound {

	private String status ;

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class) 
	@XmlElement(name="STATUS",required=true, nillable=false) 
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		
	   String response ="<STATUS>"+status+"</STATUS>";
		
	   return response;
	}
	
}
