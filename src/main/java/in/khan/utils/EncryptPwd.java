package in.khan.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Component;
@Component
public class EncryptPwd {

	
	
	public String encryptGivenPwd(String pwd) throws NoSuchAlgorithmException
	{
		String salt="thisissaltforhashing";
		
		String hash=pwd+salt;
		
		MessageDigest instance = MessageDigest.getInstance("SHA-256");
		
		instance.reset();
		instance.update(hash.getBytes());
		byte[] digest = instance.digest();
		String encoded = Base64.getEncoder().encodeToString(digest);
		return encoded;
			
		
	}
}
