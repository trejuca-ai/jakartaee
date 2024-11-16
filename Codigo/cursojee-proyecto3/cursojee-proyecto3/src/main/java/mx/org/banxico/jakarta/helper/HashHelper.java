package mx.org.banxico.jakarta.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper {

	public static String hashPassword(String password) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			
			byte[] hashedBytes = md.digest();
			StringBuilder sb = new StringBuilder();
			
			for(byte b: hashedBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error al parsear la contraseña", e);
		}
	}
	
	public static boolean verifyPassword(String storedHash, String proviedHash) {
		return storedHash.equals(hashPassword(proviedHash));
	}
}
