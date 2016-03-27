package br.com.ufpb.mathtimer.view;

import java.io.*;                 

public class ByteBuffer implements  Serializable{

	static final long serialVersionUID = 7401588019652180668L;
	
	public static final int BUFFER_SIZE = 4096;
	
	protected byte[] byteRay = null;
	protected String enc = "US-ASCII";
	
	
	public ByteBuffer() {
	}
	
	public ByteBuffer(byte[] srcBuf) {
		append(srcBuf);
	}

	public ByteBuffer(ByteBuffer bb) {
		append(bb);
	}
	
	public ByteBuffer(InputStream is) throws IOException {
		byte[] readBuf = new byte[BUFFER_SIZE];
		while (true) {
			int read = is.read(readBuf);
			if (read == -1) break;
			append(readBuf, 0, read);
		}
	}

	public ByteBuffer append(byte[] srcBuf, int srcStartIndex, int srcLength){
		if (byteRay == null) {
			byteRay = new byte[srcLength];
			arrayCopy(srcBuf, srcStartIndex, byteRay, 0, srcLength);
		}
		else {
		    int currentSize = byteRay.length;
		    byte[] newByteRay = new byte[currentSize + srcLength];
		    arrayCopy(byteRay, 0, newByteRay, 0, currentSize);
		    int newByteRayStartIndex = currentSize;
		    arrayCopy(srcBuf, srcStartIndex, newByteRay, newByteRayStartIndex,srcLength);
		    byteRay = newByteRay;
		}
		return this;
	}

	public byte[] toByteArray() {
		return getBytes();
	}
	
	public String toString() {
		if (byteRay != null && byteRay.length > 0) {
			float sizeInKB = byteRay.length / 1000f;
		    return sizeInKB + " KB";
		}
		else {
			return "0 KB";
		}
	}
	
	public void setEncoding(String enc) {
		if (enc == null) {
			return;
		}
		else {
		    try {
		    	byte[] bytes = {(byte) '0', (byte) '1'};
			    new String(bytes, enc);
			    this.enc = enc;
		    }catch (UnsupportedEncodingException e) {
		    	System.out.println("unsupported encoding");
		    }
		}
	}

	protected final void arrayCopy(byte[] srcBuf, int srcStartIndex,
	                               byte[] destBuf, int destStartIndex,
	                               int numberOfBytesToCopy){
		System.arraycopy(srcBuf, srcStartIndex, destBuf, destStartIndex,numberOfBytesToCopy);
	
	}
	
	public byte[] getBytes() {
		if (byteRay == null) {
		    return new byte[0];
		}
		return byteRay;
	}
	
	public ByteArrayInputStream getInputStream() {
		return new ByteArrayInputStream(getBytes());
	}
	
	public int getSize() {
		if (byteRay != null) {
			return byteRay.length;
		}
		else {
		    return 0;
		}
	}

	public void append(byte[] srcBuf) {
		append(srcBuf, 0, srcBuf.length);
	}
	
	public void append(ByteBuffer buf) {
		append(buf.getBytes(), 0, buf.getSize());
	}
	
	public void clear() {
		if (byteRay != null) {
			byteRay = null;
		}
	}

}
