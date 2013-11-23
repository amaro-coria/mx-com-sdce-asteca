package mx.com.asteca.util;

import org.apache.commons.lang.RandomStringUtils;

public class RandomString {

	public static String getRandomString(){
		String id =  RandomStringUtils.random(8,
        "0123456789abcdefghijklmnopqrstuvwxyz");
		return id;
	}
	
}
