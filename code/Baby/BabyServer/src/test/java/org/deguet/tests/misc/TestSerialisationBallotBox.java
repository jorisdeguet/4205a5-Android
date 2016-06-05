package org.deguet.tests.misc;

import java.util.ArrayList;
import java.util.List;

import org.deguet.CustomGson;
import org.junit.Test;

import com.deguet.gutils.random.CopiableRandom;
import com.deguet.gutils.vote.BallotBox;
import com.deguet.gutils.vote.RankedVote;
import com.google.gson.Gson;

public class TestSerialisationBallotBox {

	@Test(timeout = 10000)
	public void testGSONforBallotBox(){
		BallotBox<String> bb = new BallotBox<String>();
		List<RankedVote<String>> list = new ArrayList<RankedVote<String>>();

		// add the same votes to the Condorcet List and BallotBox
		CopiableRandom r = new CopiableRandom(9875);
		String[] candidates = {"A","B","C"};
		for (int i = 0 ; i < 100000 ; i++){
			RankedVote<String> vote = RankedVote.atRandom(r.asRandom(),candidates);
			list.add(vote);
			bb.add(vote);
		}
		Gson gson = CustomGson.getIt();
		String json = gson.toJson(bb);
		System.out.println(json);

		BallotBox<String> recov = gson.fromJson(json, BallotBox.class);
		System.out.println(recov.stringMatrix(10));

	}

}
