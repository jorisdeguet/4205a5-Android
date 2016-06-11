package org.deguet.tests.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import org.deguet.Exc.*;
import org.deguet.Services;
import org.deguet.model.NQPerson;
import org.deguet.model.S2CPersonLight;
import org.deguet.model.vote.NQQuestion;
import org.deguet.service.ServiceForDev;

import org.deguet.service.ServiceVotvot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestServiceInitial {

	ServiceForDev si = new ServiceForDev(new ServiceVotvot());
	
	@Before
	public void deleteAll(){
		si.clearAll();
	}
	
	@Test
	public void testInitialLoad(){
		si.createSampleAll();
		System.out.println("All people : " + si.getSvote().allPeople() );
	}

	@Test
	public void testLoginJohnLennon() throws BadEmail, BadPassword, NoSuchAlgorithmException, UnsupportedEncodingException, BadCredentials {

		ServiceForDev init = si;
		try{init.createSampleAll();} catch(Exception be){}
		for (NQPerson p : si.getSvote().allPeople())
			System.out.println("All people : " +  p);

        si.getSvote().signin("joris@deguet.org", "password");
	}
	
	@Test
	public void testFindJohnLennon() throws BadEmail, BadPassword{

		ServiceForDev init = si;
		init.createSampleAll();
		for (NQPerson p : si.getSvote().allPeople())
			System.out.println("ONE : " +  p);
		
		NQPerson p = si.getSvote().findByEmail("joris@deguet.org");
		System.out.println("p " + p);
	}

    @Test
    public void testMostVoted() throws Exception{
        Random r = new Random(987);
        // delete everything
        Services.initial.clearAll();
        // create users
        Services.initial.createSamplesUsers(r,1);
        // create questions and include them
        Services.initial.addGroups(r);
        Services.initial.createSampleQuestions(r,1);
        // create voted
        Services.initial.createSamplesVotes(r);
        // see most voted
        Services.vote.mostVoted();
    }

    @Test
    public void testMostVotedByFriends() throws Exception{
        Random r = new Random(987);


        // delete everything
        Services.initial.clearAll();
        // create users
        Services.initial.createSampleAll(1);
        NQPerson first = Services.vote.allPeople().get(0);
        List<NQPerson> followed = Services.vote.followedPeopleBy(first.getId());

        List<String> qs= Services.vote.topVotedByFriends(first.getId());
        System.out.println(qs);

    }


    @Test
    public void testRecentlyAccepted() throws Exception{
        Random r = new Random(987);

        // delete everything
        Services.initial.clearAll();
        // create users
        Services.initial.createSamplesUsers(r,1);
        // create questions and include them
        Services.initial.addGroups(r);
        Services.initial.createSampleQuestions(r,1);
        // create voted
        Services.initial.createSamplesVotes(r);
        // see most voted
        System.out.println(Services.vote.recentlyAccepted().size());
    }

    @Test
    public void testCountFriendsWhoVoted() throws Exception{
        Random r = new Random(987);
        Services.initial.clearAll();                                 // delete everything
        Services.initial.createSamplesUsers(r,1);
        Services.initial.createAllTracking(r);
        Services.initial.addGroups(r);
        Services.initial.createSampleQuestions(r,1);
        Services.initial.createAllVotes();
        NQQuestion question = Services.vote.allPolls().get(0);
        //System.out.println(question.question);
        NQPerson person = Services.vote.allPeople().get(0);
        //System.out.println(person.email);
        long count = Services.vote.countHowManyTrackedVotedIt(person.getId(),question.getId());
        List<S2CPersonLight> result = Services.vote.trackedVotedIt(person.getId(),question.getId());
        Assert.assertEquals(count, result.size());
        Assert.assertEquals(24, result.size());
        System.out.println(result);
    }

    @Test
    public void testMontreal() throws Exception{
        Random r = new Random(987);
        // delete everything
        Services.initial.createMontrealLight(1000);

    }

}
