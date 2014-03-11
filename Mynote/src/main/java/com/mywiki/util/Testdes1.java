package com.mywiki.util;


public class Testdes1 {
	
	public static void main(String args[]) throws Exception{

		String key = "267ac2ed67f292ff77c4a0b8";  
	    String text = "{\"fightId\":\"54771\",\"rs\":\"1\"}";  
	    String result1 = DESede.encryptDES(text,key);
	    String result2 = DESede.decryptDES(result1, key);  
	    System.out.println(result1);
	    System.out.println();
	    System.out.println(result2);
	    
	    System.out.println(DESede.decryptDES("nyNd2zn+5ibbloQ3OvteFX1arylAujf9JUdvi3kh8FgiCVDPmJIQMvtZqSuDOykAfzS7iQ/tJHvWEspn67CWfC+7UxalrUE9gvBaPnEgI2yhlIMvaKGTFw==", "267ac2ed67f292ff77c4a0b8"));
	}
}
