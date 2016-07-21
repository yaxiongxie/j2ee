package com.xyx.common.image;

import java.io.File;

public class Test  
{  
    @SuppressWarnings("unused")
	public static void main(String[] args)  
    {  
        try  
        {  
              
            File testDataDir = new File("testdata");  
            int i = 0 ;   
//            for(File file :testDataDir.listFiles())  
//            {  
//                i++ ;  
//                String recognizeText = new OCR().recognizeText(file,"png");  
//                System.out.print(recognizeText+"\t");  
//  
//                if( i % 5  == 0 )  
//                {  
//                    System.out.println();  
//                }  
//            }  
            File file=new File("E:\\Downloads\\123.png");
            String recognizeText = new OCR().recognizeText(file,"png",2);  
          System.out.print(recognizeText+"\t");  
              
        } catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
  
    }  
} 