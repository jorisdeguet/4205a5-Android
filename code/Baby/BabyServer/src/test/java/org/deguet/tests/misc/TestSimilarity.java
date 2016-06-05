package org.deguet.tests.misc;

import java.util.ArrayList;
import java.util.List;

import org.deguet.service.calc.ServiceStringSimilarity;
import org.junit.Assert;
import org.junit.Test;

public class TestSimilarity {

	@Test
	public void testSimple(){
		ServiceStringSimilarity sss = new ServiceStringSimilarity();
		int result = sss.distance("joris deguet", "Joris Deguet");
		System.out.println("Result " + result);
		Assert.assertEquals(2, result);
	}
	
	@Test
	public void testLong(){
		ServiceStringSimilarity sss = new ServiceStringSimilarity();
		int result = sss.distance("Arnaud Morin", "Arno Maurin");
		System.out.println("Result " + result);
		Assert.assertEquals(5, result);
	}
	
	@Test
	public void testCloseCandidate(){
		ServiceStringSimilarity sss = new ServiceStringSimilarity();
		List<String> candidates = new ArrayList<String>();
		for (String f : new String[]{"John", "Joris", "Johnny"}){
			for (String l : new String[]{"Deguet", "Delury", "Dupont"}){
				candidates.add(f+" "+l);
			}
		}
		String mostSimilar = sss.best("Jorris Duguet", candidates);
		System.out.println("Result " + mostSimilar);
	}
	
}
