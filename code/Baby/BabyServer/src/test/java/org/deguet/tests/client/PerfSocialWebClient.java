package org.deguet.tests.client;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.deguet.Exc.*;
import org.deguet.client.WebClientVotVot;
import org.deguet.client.WebClientVotVot.NotOnLocalhost;
import org.deguet.model.NQPosition;
import org.deguet.model.NQToken;
import org.deguet.model.NQPerson;
import org.deguet.model.NQPerson.Sex;
import org.deguet.model.C2SSignUpRequest;

import org.joda.time.DateTime;
import org.junit.Assert;

public class PerfSocialWebClient {

	WebClientVotVot wcs = new WebClientVotVot(BaseURL.base);
	
	//@Test ( timeout = 20000)
	public void testSignUpMultiple() throws IOException, BadEmail, BadAddress, BadBirth, BadSex, NoSuchAlgorithmException, BadPassword, BadCredentials, NotOnLocalhost{
		int size = 1000;
		
		wcs.deleteAllFromLocalhost();
		try{
			C2SSignUpRequest admin = new C2SSignUpRequest();
			admin.email = "admin@votvot.com";
			admin.birthDate = DateTime.now().minusYears(34);
			admin.password = "admin";
			admin.birthPlace = new NQPosition("","",10,10);
			admin.sex = Sex.Female;
			NQPerson adminSaved = wcs.signUp(admin);
			System.out.println("Admin log attempt " +adminSaved);
			NQToken token = wcs.signin(admin.email, admin.password);
			System.out.println("Admin logged as   " +adminSaved);
			System.out.println("Admin token is    " +token);
		}catch(Exception e){}
		
		for (int i = 0  ; i < size ; i++)
		{
			C2SSignUpRequest p = new C2SSignUpRequest();
			p.email = "jojo"+i+"@blo.com";
			p.birthDate = DateTime.now();
			p.password = "pipo";
			p.birthPlace = new NQPosition("","",10,10);
			p.sex = Sex.Female;
			NQPerson saved = wcs.signUp(p);
			//System.out.println("testSignIn " + saved.id);
			Assert.assertTrue(saved.getId() != null);
		}
		List<NQPerson> list = wcs.allPeople();
		Assert.assertEquals(size+1, list.size());
	}
	
}
