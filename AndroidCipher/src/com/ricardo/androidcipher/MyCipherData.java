package com.ricardo.androidcipher;

public class MyCipherData {

	private byte[] data;
	private byte[] iv;
	
	public MyCipherData(byte[] data, byte[] iv) {
		this.data = data;
		this.iv = iv;
	}
	public byte[] getData() {
		return data;
	}
	public byte[] getIV() {
		return iv;
	}
	
}
