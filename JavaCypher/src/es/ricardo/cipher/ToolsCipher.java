//package es.ricardo.cipher;
///*
// * This software is provided 'as-is', without any express or implied
// * warranty.  In no event will Google be held liable for any damages
// * arising from the use of this software.
// *
// * Permission is granted to anyone to use this software for any purpose,
// * including commercial applications, and to alter it and redistribute it
// * freely, as long as the origin is not misrepresented.
// * 
// * @author: Ricardo Champa
// * 
// * Warning use only to encrypt local files, DON'T use it on SSL comunications
// */
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.security.SecureRandom;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.RandomUtils;
//
//
//
//public class ToolsCipher {
//	
//	private final static String DIRECTORY = "./";
//	private final static String SETTINGS = "settings.son";
//	private final static String ALGORITHM = "AES";
//
//	private final static String IV_FILE = "init.son";
//	
//	public static void saveData(String data){
//		try{
//			byte[] bytes = data.toString().getBytes("utf-8");
//			saveDataFile(bytes,DIRECTORY,SETTINGS,true);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public static String getData(){
//		return getDataFile(DIRECTORY, SETTINGS, true);
//	}
//	
//	private static void saveDataFile(byte[] data, String directory, String filename, boolean cipher){
//		
//		File mediaStorageDir = new File(directory);
//		
//		if (mediaStorageDir!=null && !mediaStorageDir.exists()){
//	        if (!mediaStorageDir.mkdirs()){
//	            return;
//	        }
//		} 
//		   
//	    File settingsFile = new File(directory+filename);
//    	try {
//    		if(settingsFile!=null && !settingsFile.exists() &&!settingsFile.createNewFile())
//				return;
//			
//		} 
//    	catch (IOException e1) {
//			e1.printStackTrace();
//			return;
//		}
//	    
//    	FileOutputStream out = null;
//        try {
//        	out = new FileOutputStream(settingsFile.getAbsolutePath());
//        	if(cipher){
//        		//byte[] b = data.toString().getBytes("utf-8");
//        		out.write(encrypt(data));
//        	}
//        	else{
//        		//out.write(data.toString().getBytes("utf-8"));
//        		out.write(data);
//        	}
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//        finally{
//        	try {
//        		if(out!=null)
//        			out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//        }
//	} 
//	
//	
//	//STRING
//	private static String getDataFile(String directory, String filename, boolean cipher){
//		
//		try {
//			FileInputStream fis = new FileInputStream(directory+filename);
//			byte[] data = IOUtils.toByteArray(fis);
//		    if(fis!=null)
//	            fis.close();
//		    
//		    if(cipher){
//		    	byte[] decryptedData = decrypt(data);
//			    String str = new String(decryptedData, Charset.forName("UTF8"));
//			    return str;
//		    }
//		    else{
//		    	String str = new String(data, Charset.forName("UTF8"));
//		    	return str;
//		    }
//		    
//		     
//		 }catch (Exception e) {
//			 e.printStackTrace();
//			 return null;
//		 }
//		
//	}
//	
//	
//	
//	//AES
////	private static IvParameterSpec getIV(){
////		
////		byte[] init = getDataFromForeverFileCipherBytes(DIRECTORY, IV_FILE, false);
////		if(init==null){
////			init = RandomUtils.nextBytes(16);
////			saveDataFile(init, DIRECTORY, IV_FILE, false);
////			System.out.println("new IV");
////		}
////		
////	    IvParameterSpec ivspec = new IvParameterSpec(init);
////	    return ivspec;
////	}
////	
////	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
////	    SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
////	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
////	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, getIV());
////	    byte[] encrypted = cipher.doFinal(clear);
////	    //Not needed... yet
////	    //AlgorithmParameters params = cipher.getParameters();
////	    //byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
////	    return encrypted;
////	}
////
////	private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
////	    SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
////	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
////	    cipher.init(Cipher.DECRYPT_MODE, skeySpec, getIV());
////	    byte[] decrypted = cipher.doFinal(encrypted);
////	    return decrypted;
////	}
////	
////	private static byte[] getKey() throws Exception{
////		//a pelo pues porque por ahora no hay mejor solucion
////		byte[] keyStart = "my secret :)".getBytes("utf-8");
////		KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
////		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
////		sr.setSeed(keyStart);
////		kgen.init(128, sr); // 192 and 256 bits may not be available
////		SecretKey skey = kgen.generateKey();
////		byte[] key = skey.getEncoded();
////		return key;
////		
////	}
////	////////////////////////////////////////////////////////////
////	private static byte[] encrypt(byte[] data) throws Exception{
////		return encrypt(getKey(),data);
////	}
////	
////	private static byte[] decrypt(byte[] encryptedData) throws Exception{
////		return decrypt(getKey(),encryptedData);
////	}
//
//}
