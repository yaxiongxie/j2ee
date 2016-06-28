package com.xyx.common.socket;

import java.io.UnsupportedEncodingException;

public abstract class MessageListener {
	
	private String getString(byte[] bytes){
		for(byte b:bytes){
			System.out.print(b+",");
		}
		byte[] newbytes=new byte[bytes.length-8];
		for(int i=8;i<bytes.length;i++){
			newbytes[i-8]=bytes[i];
		}
		for(byte b:newbytes){
			System.out.print(b+",");
		}
		System.out.println();
		newbytes=RC4BaseUtil.RC4Base(newbytes, "1234567890");
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
		return byteToString(newbytes);
	}
	
	public String byteToString(byte[] bytes){
		String temp="";
		String result="";
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
				result=result+deUnicode(hexS);
			}else{
				temp=hexString;
			}
		}
		return result;
	}
	
	public String deUnicode(String content){//��16������ת��Ϊ����
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
	
	public void receiveMessage(byte[] bytes){
		String message=getString(bytes);
		dealMessage(message);
	}
	
	@SuppressWarnings("unused")
	private String getGBKString(byte[] bytes){
		for(byte b:bytes){
			System.out.print(b+",");
		}
		byte[] newbytes=new byte[bytes.length-8];
		for(int i=8;i<bytes.length;i++){
			newbytes[i-8]=bytes[i];
		}
		for(byte b:newbytes){
			System.out.print(b+",");
		}
		System.out.println();
		newbytes=RC4BaseUtil.RC4Base(newbytes, "1234567890");
		for(byte b:newbytes){
			int i=(int)b;
			if(i<0){
				System.out.print((256+i)+",");
			}else{
				System.out.print(b+",");
			}
		}
		System.out.println();
		try {
			return new String(newbytes,"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	abstract public void dealMessage(String message);

}
