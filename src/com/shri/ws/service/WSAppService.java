package com.shri.ws.service;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.ws.WebServiceContext;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.shri.ws.shared.XmlToObject;
import com.shri.ws.impl.EnqObjectImpl;
import com.shri.ws.shared.AESEncrypted;
import com.shri.ws.vo.Inbound;
import com.shri.ws.vo.Outbound;

/**
 * @author shrisowdhaman
 * Nov 15, 2017
 */
@WebService(serviceName = "WSAppService", portName = "WSAppServicePort", 
targetNamespace = "http://example.org/AESWS")
public class WSAppService extends SpringBeanAutowiringSupport {

	
	@Resource
	private WebServiceContext wsctx;
	 
	static Logger log = Logger.getLogger(WSAppService.class.getName());
	
	private EnqObjectImpl enqObjectImpl;
	
	
	@WebMethod(exclude = true)
	public void setEnqObjectImpl(EnqObjectImpl enqObjectImpl) {
		this.enqObjectImpl = enqObjectImpl;
	}
	
	
	//WebService 
	@WebMethod(operationName = "WS")
	@WebResult(name = "OUTBOUND")
	@XmlJavaTypeAdapter(AESEncrypted.class)
	public String startProcess(
			@WebParam(name = "INBOUND") @XmlJavaTypeAdapter(AESEncrypted.class) String request ){
		
		log.info(" Request : " + request);
		String response; // response
		Outbound outbound = new Outbound(); 
		
		try{
			log.info(request);
			//Validate the request
			if(request.trim().length()>0 && !request.trim().equalsIgnoreCase("?")){
				
				//After unmarsel the header request tag, validate the request by XSD schema 
				//Step1
				XmlToObject xmlToObject = new XmlToObject();
				
				Inbound inbound = (Inbound)XmlToObject.convert(Inbound.class, request, outbound);
				
				
				if (outbound.getStatus() == "failure") {
					response = outbound.toString(); // Outbound XML tag
					return response;
				
				}else{
					//Do your DAO process here 
					log.info(inbound.getName());
					log.info(inbound.getAddress());
					
					outbound.setStatus("success");
				}
				
			}else{
				outbound.setStatus("failure");
			}
			
		}catch(Exception err){
			log.info(err.toString());
			outbound.setStatus("failure");
			
		}
		response = outbound.toString();
		return "<ss>sss</ss>";
	}
	
	
}
