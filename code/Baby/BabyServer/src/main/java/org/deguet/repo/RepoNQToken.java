package org.deguet.repo;

import org.deguet.model.NQToken;
import org.deguet.utils.ListRepo;

public class RepoNQToken extends ListRepo<NQToken> {

	public RepoNQToken() 		{}

	public NQToken findByUserId(String userid) {
		for(NQToken token : getAll()){
            if (token.userID.equals(userid)) return token;
        }
        return null;
	}
}
