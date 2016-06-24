package com.xyx;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class Test {
	
	public static byte[] getbytes(String content){
		List<Integer> list=string2Unicode(content);
		byte[] bytes=new byte[list.size()];
		for(int i=0;i<list.size();i++){
			int k=list.get(i);
			bytes[i]=(byte)k;
		}
		bytes=RC4Base(bytes, "1234567890");
		bytes=concat(addHead(bytes.length), bytes);
		return bytes;
	}
	
	public static void main(String[] args) {
//		String string="111,79,195,100,232,209,71,30,184,137,40,105,61,177,238,33,41,126,149,54,125,154,242,183,211,19,54,146,151,218,85,16,189,200,56,109,187,17,7,79,212,72,123,110,81,61,158,244,44,247,192,61,8,183,128,104,116,185,175,167,151,69,29,81,170,253,156,230,247,34,55,82,184,122,125,228,138,187,175,78,119,41,235,62,113,204,127,168,79,153,105,155,118,118,0,248,226,194,16,161,164,100,172,119,114,85,113,16,44,136,4,124,170,124,177,249,169,230,243,93,124,93,80,206,154,27,90,12,223,13,75,86,20,59";
//		byte[] bytes=new byte[134];
//		String[] strings=string.split(",");
//		for(int i=0;i<strings.length;i++){
//			bytes[i]=(byte)Integer.parseInt(strings[i]);
//		}
		if(true){
			JSONObject jObject=new JSONObject();
			try {
				System.out.println(jObject.isNull("id"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ;
		}
		
		String string="读取匹配关键字";
		byte[] bytes=getbytes(string);
		bytes=sendMessage(bytes);
//		for(byte b:bytes){
//			System.out.println(b);
//		}
		byte[] newbytes=new byte[bytes.length-8];
		for(int i=8;i<bytes.length;i++){
			newbytes[i-8]=bytes[i];
		}
		for(byte b:newbytes){
			System.out.print(b+",");
		}
		System.out.println();
		newbytes=RC4Base(newbytes, "1234567890");
		for(byte b:newbytes){
			int i=(int)b;
			if(i<0){
				System.out.print((256+i)+",");
			}else{
				System.out.print(b+",");
			}
		}
		System.out.println();
		for(byte b:newbytes){
			int i=(int)b;
			if(i<0){
				System.out.print(Integer.toHexString((256+i))+",");
			}else{
				System.out.print(Integer.toHexString(i)+",");
			}
		}
		System.out.println();
		byteToString(newbytes);
		
		
	}
	
	public static String byteToString(byte[] bytes){
		String temp="";
		int flag=0;
		for(byte b:bytes){
			flag++;
			int i=(int)b;
			String hexString="";
			if(i<0){
				hexString=Integer.toHexString((256+i));
			}else{
				hexString=Integer.toHexString(i);
			}
			if(hexString.length()==1){
				hexString="0"+hexString;
			}
			if(flag%2==0){
				String hexS=hexString+temp;
				System.out.print(deUnicode(hexS));
			}else{
				temp=hexString;
			}
		}
		return temp;
	}
	
	public static String deUnicode(String content){//将16进制数转换为汉字
		 String enUnicode=null;
		 String deUnicode=null;
		 for(int i=0;i<content.length();i++){
		     if(enUnicode==null){
		      enUnicode=String.valueOf(content.charAt(i));
		     }else{
		      enUnicode=enUnicode+content.charAt(i);
		     }
		     if(i%4==3){
		      if(enUnicode!=null){
		       if(deUnicode==null){
		        deUnicode=String.valueOf((char)Integer.valueOf(enUnicode, 16).intValue());
		       }else{
		        deUnicode=deUnicode+String.valueOf((char)Integer.valueOf(enUnicode, 16).intValue());
		       }
		      }
		      enUnicode=null;
		     }
		     
		    }
		 return deUnicode;
		}
	
	static byte[] concat(byte[] a, byte[] b) {  
		byte[] c= new byte[a.length+b.length];  
		   System.arraycopy(a, 0, c, 0, a.length);  
		   System.arraycopy(b, 0, c, a.length, b.length);  
		   return c;  
		}  
	
	public static byte[] addHead(int num){
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
	
	public static List<Integer> string2Unicode(String string) {
		 
	    List<Integer> list=new ArrayList<Integer>();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
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
	
	
	private static byte[] RC4Base(byte[] input, String mKkey) {  
        int x = 0;  
        int y = 0;  
        byte key[] = initKey(mKkey);  
        int xorIndex;  
        byte[] result = new byte[input.length];  
  
        for (int i = 0; i < input.length; i++) {  
            x = (x + 1) & 0xff;  
            y = ((key[x] & 0xff) + y) & 0xff;  
            byte tmp = key[x];  
            key[x] = key[y];  
            key[y] = tmp;  
            xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;  
            result[i] = (byte) (input[i] ^ key[xorIndex]);  
        }  
        return result;  
    } 
	
	public static byte[] sendMessage(byte[] bytes){
		Socket socket=null;
		ByteArrayOutputStream outSteam=null;
		try{
			socket=new Socket("60.169.79.179",8888); 
			socket.setSoTimeout(3000);
			socket.getOutputStream().write(bytes);
			socket.getOutputStream().flush();
	
			outSteam = new ByteArrayOutputStream(); 
			InputStream inputStream=socket.getInputStream();
		    byte[] buffer = new byte[1024];  
		    int len = -1;  
		    while ((len = inputStream.read(buffer)) != -1) {
		        outSteam.write(buffer, 0, len);  
		    }  
		    outSteam.close();  
		    inputStream.close();  
		    return outSteam.toByteArray();  
		}catch (Exception e) {
			return outSteam.toByteArray();
		}finally{
			try{
				socket.close();
			}catch (Exception e) {
			}
		}
		
	}
	
	private static byte[] initKey(String aKey) {  
        byte[] b_key = aKey.getBytes();  
        byte state[] = new byte[256];  
  
        for (int i = 0; i < 256; i++) {  
            state[i] = (byte) i;  
        }  
        int index1 = 0;  
        int index2 = 0;  
        if (b_key == null || b_key.length == 0) {  
            return null;  
        }  
        for (int i = 0; i < 256; i++) {  
            index2 = ((b_key[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;  
            byte tmp = state[i];  
            state[i] = state[index2];  
            state[index2] = tmp;  
            index1 = (index1 + 1) % b_key.length;  
        }  
        return state;  
    }

}
