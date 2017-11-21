package com.shri.ws.impl;

import com.shri.ws.vo.Inbound;
import com.shri.ws.vo.Outbound;

/**
 * @author shrisowdhaman
 * Nov 15, 2017
 */
public interface EnqObject {

	public Outbound startProcess(Inbound inbound,Outbound outbound,String xml);
}
