package com.shri.ws.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author shrisowdhaman
 * Nov 15, 2017
 */
@XmlType(name = "REQ",propOrder={"name","address"})
@XmlRootElement(name ="REQ")
public class Inbound {

	private String name;
	
	private String address;

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class) 
	@XmlElement(name="NAME",required=true, nillable=false)
	public String getName() {
		return name;
	}
	 
	public void setName(String name) {
		this.name = name;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class) 
	@XmlElement(name="ADDRESS",required=true, nillable=false)
	public String getAddress() {
		return address;
	}
 
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
