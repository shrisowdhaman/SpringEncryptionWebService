package com.shri.ws.shared;

import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

/**
 * @author shrisowdhaman
 * Nov 15, 2017
 */
@Service
public class AESEncrypted extends XmlAdapter<String, String>{
	
    //Secret Key values set from properties files
    private static SecretKeySpec secretKey; 
    
	private static Cipher cipher;	
	
	static Logger log = Logger.getLogger(AESEncrypted.class.getName());
	
	public AESEncrypted(){}
	
	//Value set from dispatcher Servlet constructor INJECTION
	public AESEncrypted(String awsKey){
		try {
			
			secretKey = new SecretKeySpec(awsKey.getBytes(), "AES");
			cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		} catch (Exception e) {
			log.info("Secrate Key Loading issue !! " );
		}
	}

	/**
	 * Encrypts the value to be placed back in XML
	 */
	@Override
	public String marshal(String plaintext)   {
	  try{
         cipher.init(Cipher.ENCRYPT_MODE, secretKey);
         byte[] cipherText = cipher.doFinal(plaintext.getBytes("UTF8"));
         String encryptedString = new String(Base64.encodeBase64(cipherText),"UTF-8");
         return encryptedString;
	  }catch(Exception err){
		  log.info(err.getMessage());
   	   	return "";
      }
	}

	/**
	 * Decrypts the string value
	 */
	@Override
	public String unmarshal(String encryptedText)  {
       try{
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] cipherText = Base64.decodeBase64(encryptedText.getBytes("UTF8"));
		String decryptedString = new String(cipher.doFinal(cipherText), "UTF-8");
		return decryptedString;
       }catch(Exception err){
 		  log.info(err.getMessage());   
    	   return "";
       }

	}

}
