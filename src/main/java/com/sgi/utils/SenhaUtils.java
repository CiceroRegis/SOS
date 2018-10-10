package com.sgi.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 
 * @author Cícero Régis in 28/09/2018
 *
 */
public class SenhaUtils {

	
	public static String produzirChaveMD5(String str) throws NoSuchAlgorithmException {
		if (str == null)
			return null;
		MessageDigest md = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, md.digest(str.getBytes()));
		String s = hash.toString(16);
		if (s.length() % 2 != 0)
			s = "0" + s;
		return s;
	}

	public static String produzirSenha(){
		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ123456789abcdefghijklmopqrstuvywx@#!";  
		  
		Random random = new Random();  
		  
		String armazenaChaves = "";  
		int index = -1;  
		for( int i = 0; i < 20; i++ ) {  
		   index = random.nextInt( letras.length() );  
		   armazenaChaves += letras.substring( index, index + 1 );  
		}  
		return armazenaChaves;
	}
}
