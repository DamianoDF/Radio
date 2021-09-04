package it.failutti.damiano.persistence;


import javax.persistence.AttributeConverter;

import java.security.Key;
import java.io.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class StringAttributeConverter implements AttributeConverter<String,String> {
	private static final String ALGORITHM = "AES/ECB/PKCSSPadding";
	private static final byte[] KEY = "MySuperSecretKey".getBytes();

	@Override
	public String convertToDatabaseColumn(String attribute) {
		Key chiave = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, chiave);
			
			return Base64.getEncoder().encodeToString((c.doFinal(attribute.getBytes())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



@Override
public String convertToEntityAttribute(String dbData) {
	Key key = new SecretKeySpec(KEY, "AES");
	try {
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		return new String(c.doFinal(Base64.getDecoder().decode(dbData)));

	}catch (Exception e) {
		throw new RuntimeException(e);
	}

}


}


