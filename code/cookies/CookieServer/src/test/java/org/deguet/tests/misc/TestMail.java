package org.deguet.tests.misc;

import java.io.IOException;

import javax.mail.MessagingException;

import org.deguet.service.ServiceEmail;
import org.junit.Test;

public class TestMail {

	@Test
	public void testSend() throws MessagingException, IOException{
		ServiceEmail se = new ServiceEmail();
		se.sendEmail("joris.deguet@gmail.com", "Test Votvot");
	}
	
}
