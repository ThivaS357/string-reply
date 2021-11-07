package com.beta.replyservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestServiceApplicationTest {

	@Autowired
	private ReplyController replyController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void reverseStringTest(){

		String input = "kbzw9ru";

		String output = "ur9wzbk";

		assertEquals(output, replyController.reverseString(input));

	}

	@Test
	public void md5StringTest() throws NoSuchAlgorithmException, UnsupportedEncodingException{

		String input = "kbzw9ru";

		String output = "0fafeaae780954464c1b29f765861fad";

		assertEquals(output, replyController.convertToMD5(input));
	}

	@Test
	public void replyMessageCaseOne() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String input = "11-kbzw9ru";

		String output = "kbzw9ru";

		assertEquals(output, replyController.replying(input).getBody().getMessage());

	}

	@Test
	public void replyMessageCaseTwo() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String input = "12-kbzw9ru";

		String output = "5a8973b3b1fafaeaadf10e195c6e1dd4";

		assertEquals(output, replyController.replying(input).getBody().getMessage());

	}

	@Test
	public void replyMessageCaseThree() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String input = "22-kbzw9ru";

		String output = "e8501e64cf0a9fa45e3c25aa9e77ffd5";

		assertEquals(output, replyController.replying(input).getBody().getMessage());

	}

}
