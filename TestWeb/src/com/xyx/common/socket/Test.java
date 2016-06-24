package com.xyx.common.socket;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {
	public String getString(byte[] newbytes){
		
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
	public static void main(String[] args) throws UnsupportedEncodingException {
		//183,162,203,205,182,204,208,197,124,49,56,54,54,56,52,51,48,50,54,49,124,91,0,36,177,36,198,12,213,45,0,248,188,120,199,32,0,120,198,192,208,120,199,120,177,156,205,8,174,192,201,93,0,32,0,248,188,120,199,120,199,157,201,136,188,56,214,148,178,32,0,50,0,57,0,51,0,56,0,52,0,53,0,32,0,133,199,200,178,228,178,46,0,21,200,85,214,136,215,32,0,133,199,37,184,116,213,24,201,148,198,46,0,0,0,124,49,55,48,57,48,51,49,57,50,54,53,
		//183,162,203,205,208,197,207,162,124,49,56,54,54,56,52,51,48,50,54,49,124,91,63,63,63,45,63,63,32,63,63,63,63,63,63,63,93,32,63,63,63,63,63,63,63,32,50,57,51,56,52,53,32,63,63,63,46,63,63,63,32,63,63,63,63,63,46,124,49,55,48,57,48,51,49,57,50,54,53,
//		if(true){
//			String mm="27,127,244,145,181,45,243,14,184,137,40,105,61,177,238,33,33,126,148,54,125,154,244,183,219,19,55,146,157,218,87,16,189,200,56,109,187,17,7,79,212,72,123,110,81,61,158,244,44,247,192,61,8,183,128,104,116,185,175,167,151,69,29,81,170,253,156,230,247,34,55,82,184,122,125,228,138,187,175,78,119,41,235,62,113,204,127,168,79,153,105,155,118,118,0,248,226,194,16,161,164,100,172,119,114,85,113,16,44,136,4,124,170,124,177,249,169,230,243,93,124,93,80,206,154,27,38,12,231,13,52,86,96,59,96,47,220,142,85,19,39,42,89,190,158,199,207,10,24,126,211,51,176,203";
//			String[] arr=mm.split(",");
//			byte[] bytes=new byte[arr.length];
//			for(int i=0;i<arr.length;i++){
//				bytes[i]=(byte)Integer.parseInt(arr[i]);
//			}
//			System.out.println(new Test().getString(bytes));
//			return ;
//		}
		
		SocketModel.getInstance(new MessageListener() {
			
			@Override
			public void dealMessage(String message) {
				// TODO Auto-generated method stub
				System.out.println(message);
				System.out.println();
				System.out.println();
				if(message.contains("读取匹配关键字")){
					Map<String, String> map=getMatchMap(message);
					String smscontent="[네오플-본인 외타인노출금지]11111111111111";
					String telephoneNum="18668430261";
					String proString=getProject(map, smscontent);
					String mm="接收记录"+"|"+telephoneNum+"|"+smscontent+"|"+proString;
					System.out.println(mm);
					SocketModel.sendMessage(mm);
					System.out.println("发送成功");
				}
			}
		});
		SocketModel.sendMessage("机房号码|18668430261");
		SocketModel.sendMessage("读取匹配关键字");
	}
	
	public static String getProject(Map<String, String> map,String content){
		Set<String> keySet=map.keySet();
		for(String s:keySet){
			if(content.contains(map.get(s))){
				return s;
			}
		}
		return "";
	}
	
	public static Map<String, String> getMatchMap(String message){
		HashMap<String, String> map=new HashMap<String, String>();
		message=message.split("\\|")[1];
		String[] arrString=message.split("\r\n");
		for(String string:arrString){
			String[] arrS=string.split("\t");
			map.put(arrS[0], arrS[1]);
		}
		return map;
	}
	
	

}
