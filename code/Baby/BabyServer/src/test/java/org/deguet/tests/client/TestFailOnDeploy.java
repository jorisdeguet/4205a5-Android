package org.deguet.tests.client;

import java.io.IOException;

import org.deguet.Exc;
import org.deguet.client.WebClientVotVot;
import org.deguet.client.WebClientVotVot.NotOnLocalhost;
import org.deguet.model.NQPosition;
import org.deguet.model.NQToken;
import org.deguet.model.NQPerson;
import org.deguet.model.NQPerson.Sex;
import org.deguet.model.C2SSignUpRequest;
import org.joda.time.DateTime;

/**
 * Tests if flush and initial only works on localhost
 * @author joris
 *
 */

public class TestFailOnDeploy {

	WebClientVotVot wcs = new WebClientVotVot(org.deguet.tests.client.BaseURL.di5a5);

	//@Test
	public void testAllPeople() throws IOException, Exc.BadCredentials {
		// signup and signin as admin to have the right
		C2SSignUpRequest admin = new C2SSignUpRequest();
		NQPerson adminSaved = null;
		try{
			admin.email = "admin@votvot.com";
			admin.birthDate = DateTime.now().minusYears(34);
			admin.password = "admin";
			admin.birthPlace = new NQPosition("","",10,10);
			admin.sex = Sex.Female;
			adminSaved = wcs.signUp(admin);
		}catch(Exception e){e.printStackTrace();}
		System.out.println("Admin log attempt " +adminSaved);
		NQToken token = wcs.signin(admin.email, admin.password);
		System.out.println("Admin logged as   " +adminSaved);
		System.out.println("Admin token is    " +token);
	}

}
