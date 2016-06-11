package org.deguet.tests.client;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.deguet.Exc.*;
import org.deguet.client.WebClientVotVot;
import org.deguet.client.WebClientVotVot.NotOnLocalhost;
import org.deguet.model.NQPosition;
import org.deguet.model.NQToken;
import org.deguet.model.NQPerson;
import org.deguet.model.NQPerson.Sex;
import org.deguet.model.C2SSignUpRequest;
import org.deguet.model.transfer.C2SVoteRequest;
import org.deguet.model.vote.NQQuestion;
import org.deguet.model.vote.NQResult;

import org.joda.time.DateTime;
import org.junit.Assert;

public class TestWebClient{

	WebClientVotVot wcs = new WebClientVotVot(BaseURL.base);

	//@Test
	public void testInitial() throws BadEmail, BadAddress, BadBirth, BadSex, IOException, BadCredentials, NotOnLocalhost{
		wcs.deleteAllFromLocalhost();
		// create account and SignIn
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

		// create poll propositions
		wcs.initialFromLocalHost();
		System.out.println("People are " + wcs.allPeople().size());

		// vote for it  done in the initial load

		// check result
		for (NQQuestion p : wcs.allPolls()){
			System.out.println("------------------------------------");
			System.out.println("Poll " + p.question);
			NQResult res = wcs.resultForPoll(p);
			System.out.println("Res   " + res.result);
			System.out.println("------------------------------------");
		}
	}

	//@Test
	public void testCreateVoteAndGetResults() throws BadEmail, BadAddress, BadBirth, BadSex, IOException, BadCredentials, NotOnLocalhost{
		wcs.deleteAllFromLocalhost();
		// create account and SignIn
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

		// create poll propositions

		NQQuestion p = new NQQuestion();
		p.question = "Quel est le plus beau?";		
		p.type = NQQuestion.Type.Preferential;
		p.choices = Arrays.asList("Joris","Malcolm","Evariste","Alexandre");
		NQQuestion saved = wcs.propose(p);
		System.out.println("Saved Poll " + saved);

		System.out.println("People are " + wcs.allPeople().size());

		// vote for it 
		C2SVoteRequest v = 	new C2SVoteRequest();
		v.voterId  = 		token.userID;
		v.questionId = 		saved.getId();
		v.choice = 			p.choices.get(2);
		wcs.vote(v);
		// check result
		NQQuestion recov = wcs.getPoll(saved.getId());
		System.out.println("Recovered " + recov);
		System.out.println("------------------------------------");
		System.out.println("Poll " + recov.question);
		NQResult res = wcs.resultForPoll(recov);
		System.out.println("Res   " + res.result);
		System.out.println("------------------------------------");
		Assert.assertEquals(1, res.result.get(recov.choices.get(2)).intValue());
		Assert.assertEquals(0, res.result.get(recov.choices.get(1)).intValue());
	}

	//@Test
	public void testSignUpFail() throws IOException, BadEmail, BadAddress,  BadSex, NotOnLocalhost{
		wcs.deleteAllFromLocalhost();
		C2SSignUpRequest p = new C2SSignUpRequest();
		p.email = "jo@blo.com";
		try {
			wcs.signUp(p);
			Assert.fail();
		} catch (BadBirth e) { /* OK */}
	}

	//@Test
	public void testSignUpOk() throws IOException, BadEmail, BadAddress, BadBirth, BadSex, NoSuchAlgorithmException, NotOnLocalhost{
		wcs.deleteAllFromLocalhost();
		C2SSignUpRequest p = new C2SSignUpRequest();
		p.email = "jo@blo.com";
		p.birthDate = DateTime.now();
		p.password = "pipo";
		p.birthPlace = new NQPosition("","",10,10);
		p.sex = Sex.Female;
		NQPerson saved = wcs.signUp(p);
		System.out.println("testSignIn " + saved.getId());
		Assert.assertTrue(saved.getId() != null);
	}

	//@Test (expected = BadEmail.class)
	public void testSignUpFailDouble() throws IOException, BadEmail, BadAddress, BadBirth, BadSex, NoSuchAlgorithmException, NotOnLocalhost{
		wcs.deleteAllFromLocalhost();
		C2SSignUpRequest p = new C2SSignUpRequest();
		p.email = "jo@blo.com";
		p.birthDate = DateTime.now();
		p.password = "pipo";
		p.birthPlace = new NQPosition("","",10,10);
		p.sex = Sex.Female;
		NQPerson saved = wcs.signUp(p);
		NQPerson saved2 = wcs.signUp(p);
		System.out.println("testSignIn " + saved.getId());
		Assert.assertTrue(saved.getId() != null);
	}

	//@Test
	public void testAllPeople() throws IOException, NotOnLocalhost {
		// signup and signin as admin to have the right
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

		for (NQPerson p : wcs.allPeople()){
			System.out.println("person  " + p);
		}
		wcs.deleteAllFromLocalhost();
		Assert.assertEquals(0, wcs.allPeople().size());
	}


	//@Test
	public void testFlush() throws IOException, NotOnLocalhost{
		wcs.deleteAllFromLocalhost();
		Assert.assertEquals(wcs.allPeople().size(), 0);
	}

}
