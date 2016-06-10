package org.deguet.repo;

import org.deguet.model.MToken;
import org.deguet.utils.ListRepo;

public class RepoToken extends ListRepo<MToken> {

	public RepoToken() 		{}

	public MToken findByUserId(String userid) {
		for(MToken token : getAll()){
            if (token.userID.equals(userid)) return token;
        }
        return null;
	}
}
