package com.xyx.common.socket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketModel {
	
	public static Socket socket=null;
	public static InputStream inputStream=null;
	public static OutputStream outputStream=null;
	public static MessageListener messageListener=null;
	
	public static void getInstance(MessageListener messageListener){
		SocketModel.messageListener=messageListener;
		if(socket!=null && socket.isConnected()){
			
		}else{
			try{
				socket=new Socket("60.169.79.179",8888); 
				inputStream=socket.getInputStream();
				outputStream=socket.getOutputStream();
			}catch (Exception e) {
			}
		}
		new Thread(){
			public void run() {
				while(true){
					ByteArrayOutputStream outSteam=null;
					try{
						outSteam = new ByteArrayOutputStream();
						byte[] buffer = new byte[1024];  
					    int len = -1;  
					    while ((len = inputStream.read(buffer)) != -1) {
					    	outSteam.write(buffer, 0, len);  
					    } 
					}catch (Exception e) {
					}
					if(outSteam.toByteArray().length<8){
						continue;
					}
					SocketModel.messageListener.receiveMessage(outSteam.toByteArray());
					try{
						Thread.sleep(1000);
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	
	public static void sendMessage(byte[] bytes){
		try{
			socket.setSoTimeout(3000);
			socket.getOutputStream().write(bytes);
			socket.getOutputStream().flush();
	
		}catch (Exception e) {
		}
	}
	
	public static void sendMessage(String message){
		List<Integer> list=string2Unicode(message);
		byte[] bytes=new byte[list.size()];
		for(int i=0;i<list.size();i++){
			int k=list.get(i);
			bytes[i]=(byte)k;
		}
		bytes=RC4BaseUtil.RC4Base(bytes, "1234567890");
		bytes=concat(addHead(bytes.length), bytes);
		System.out.println(bytes);
		sendMessage(bytes);
	}
	
	static byte[] concat(byte[] a, byte[] b) {  
		byte[] c= new byte[a.length+b.length];  
		   System.arraycopy(a, 0, c, 0, a.length);  
		   System.arraycopy(b, 0, c, a.length, b.length);  
		   return c;  
		}
	
	private static byte[] addHead(int num){
		byte[] bytes=new byte[8];
		bytes[0]=(byte)232;
		bytes[1]=(byte)3;
		bytes[2]=(byte)0;
		bytes[3]=(byte)0;
		bytes[4]=(byte)num;
		bytes[5]=(byte)0;
		bytes[6]=(byte)0;
		bytes[7]=(byte)0;
		return bytes;
	}
	
	private static List<Integer> string2Unicode(String string) {
		 
	    List<Integer> list=new ArrayList<Integer>();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // ȡ��ÿһ���ַ�
	        char c = string.charAt(i);
	        String hexs=Integer.toHexString(c);
	        if(hexs.length()>2){
	        	String s1=hexs.substring(0,2);
	        	String s2=hexs.substring(2);
	        	list.add(Integer.parseInt(s2, 16));
	        	list.add(Integer.parseInt(s1,16));
	        }else{
	        	list.add(Integer.parseInt(hexs,16));
	        	list.add(0);
	        }
	        
	    }
	    return list;
	}

}
