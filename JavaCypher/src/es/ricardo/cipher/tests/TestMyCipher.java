package es.ricardo.cipher.tests;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import es.ricardo.cipher.MyCipher;
import es.ricardo.cipher.MyCipherData;

public class TestMyCipher {

	public static void main(String[] args) {
		
		String[] test_bench = {
				"{id:1, name:Lucía, age:25, country:España}",
				"{id:2, name:Ricardo, age:27, country:Perú}",
				"{id:3, name:Daria, age:22, country:Rusia}",
				"{id:4, name:Antonio, age:25, country:España}",
				"{id:5, name:Irene, age:25, country:España}",
				"{id:6, name:Raúl, age:27, country:España}",
				"{id:7, name:Nuño, age:25, country:España}"
		};
		
		System.out.println("ENCRYPTING");
		MyCipher myCipher = new MyCipher("Yo me llamo Ralph");
		List<MyCipherData> cipherList = new ArrayList<MyCipherData>();
		for (String myData : test_bench) {
			MyCipherData myCipherData = myCipher.encryptUTF8(myData);
			cipherList.add(myCipherData);
			System.out.println("data: "+bytesToHex(myCipherData.getData()));
			System.out.println("iv: "+bytesToHex(myCipherData.getIV()));
		}
		
		System.out.println("DECRYPTING");
		for (MyCipherData myCipherData : cipherList) {
			byte[] encrypted_data = myCipherData.getData();
			IvParameterSpec iv = new IvParameterSpec(myCipherData.getIV());
			
			System.out.println(myCipher.decryptUTF8(encrypted_data, iv));
		}

	}
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

}
