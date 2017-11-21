package com.shri.ws.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.shri.ws.vo.Inbound;
import com.shri.ws.vo.Outbound;

/**
 * @author shrisowdhaman
 * Nov 15, 2017
 */
@Service
public class EnqObjectImpl implements EnqObject{

	static Logger log = Logger.getLogger(EnqObjectImpl.class.getName());

	public Outbound startProcess(Inbound inbound, Outbound outbound, String xml) {

		if (inbound != null) {

			try {

				//Call DAO Area and save the values in database
				log.info("Inside Inbond");	
			} catch (Exception err) {
				outbound.setStatus("failure");
			}
		}

		else {
			outbound.setStatus("failure");
		}

		return outbound;
	}

}
