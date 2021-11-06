package com.beta.replyservice;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReplyController {

	@GetMapping("/reply")
	public ResponseEntity<ReplyMessage> replying() {
		return ResponseEntity.status(HttpStatus.OK).body(new ReplyMessage("Message is empty"));
	}

	@GetMapping("/reply/{message}")
	public ResponseEntity<ReplyMessage> replying(@PathVariable String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		if(!message.matches("[1-2]{2}-[a-z0-9]*")){

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReplyMessage("Invalid Input"));
		}

		String [] inputs = message.split("-");

		if(inputs[1].length()==0){
			return ResponseEntity.status(HttpStatus.OK).body(new ReplyMessage(inputs[1]));
		}
		else{
			int rules = Integer.parseInt(inputs[0]);

			int firstRule = rules/10;

			int secoudRule = rules%10;

			if( firstRule == 1 ){
				message = reverseString(inputs[1]);
			}
			else{
				message = convertToMD5(inputs[1]);
			}



			if( secoudRule == 1 ){
				message = reverseString(message);
			}
			else{
				message = convertToMD5(message);
			}


		}

		return ResponseEntity.status(HttpStatus.OK).body(new ReplyMessage(message));
	}

	private String reverseString( final String message ){

		StringBuilder stringBuilder = new StringBuilder(message);

    	stringBuilder.reverse();

    	return stringBuilder.toString();

	}

	private String convertToMD5( final String message ) throws NoSuchAlgorithmException, UnsupportedEncodingException{

		MessageDigest messageDigest = MessageDigest.getInstance("MD5"); 
		
		byte[] hash = messageDigest.digest(message.getBytes("UTF-8"));
		
		StringBuilder stringBuilder = new StringBuilder(2*hash.length); 
		
		for(byte b : hash){ 
			stringBuilder.append(String.format("%02x", b&0xff));
		} 
		
		return stringBuilder.toString();

	}
}