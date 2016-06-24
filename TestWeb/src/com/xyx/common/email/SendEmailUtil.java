package com.xyx.common.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailUtil {
	
	private static final String EMAIL_SERVER="smtp.163.com";
	private static final String USERNAME="xieyaxiong@163.com";
	private static final String PASSWORD="13995883490";
	
	public static void sendEmail(String toName,String subject,String message){
		try{
		Properties props = new Properties();  
        props.setProperty("mail.debug", "true");  
        props.setProperty("mail.smtp.auth", "true");  
        props.setProperty("mail.host", EMAIL_SERVER);  
        props.setProperty("mail.transport.protocol", "smtp");  
          
        Session session = Session.getInstance(props);  
          
        Message msg = new MimeMessage(session);  
        msg.setSubject(subject);  
        msg.setText(message);  
        msg.setFrom(new InternetAddress(USERNAME));  
          
        Transport transport = session.getTransport();  
        transport.connect(USERNAME, PASSWORD);  
        transport.sendMessage(msg, new InternetAddress[] {new InternetAddress(toName)});  
        transport.close();  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		sendEmail("xieyaxiong@163.com", "��������","��������");
	}

}
