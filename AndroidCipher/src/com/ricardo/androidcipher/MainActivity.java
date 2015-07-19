package com.ricardo.androidcipher;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tv_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_text = (TextView)findViewById(R.id.tv_text);
		
		//According to http://android-developers.blogspot.com.es/2013/08/some-securerandom-thoughts.html
		try{
        	PRNGFixes.apply();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
		
		String[] test_bench = {
				"{id:1, name:Lucía, age:25, country:España}",
				"{id:2, name:Ricardo, age:27, country:Perú}",
				"{id:3, name:Daria, age:22, country:Rusia}",
				"{id:4, name:Antonio, age:25, country:España}",
				"{id:5, name:Irene, age:25, country:España}",
				"{id:6, name:Raúl, age:27, country:España}",
				"{id:7, name:Nuño, age:25, country:España}"
		};
		
		tv_text.append("ENCRYPTING");
		tv_text.append("\n");
		MyCipher myCipher = new MyCipher("yo me llamo Ralph");
		List<MyCipherData> cipherList = new ArrayList<MyCipherData>();
		for (String myData : test_bench) {
			MyCipherData myCipherData = myCipher.encryptUTF8(myData);
			cipherList.add(myCipherData);
			
			tv_text.append("data: "+bytesToHex(myCipherData.getData()));
			tv_text.append("\n");
			tv_text.append("iv: "+bytesToHex(myCipherData.getIV()));
			tv_text.append("\n");
		}
		
		tv_text.append("DECRYPTING");
		tv_text.append("\n");
		for (MyCipherData myCipherData : cipherList) {
			byte[] encrypted_data = myCipherData.getData();
			IvParameterSpec iv = new IvParameterSpec(myCipherData.getIV());
			
			tv_text.append(myCipher.decryptUTF8(encrypted_data, iv));
			tv_text.append("\n");
		}
	}
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

}
