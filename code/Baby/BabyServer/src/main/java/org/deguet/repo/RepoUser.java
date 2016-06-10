package org.deguet.repo;

import jersey.repackaged.com.google.common.collect.Lists;
import org.deguet.model.MUser;
import org.deguet.utils.ListRepo;

import java.util.List;

public class RepoUser extends ListRepo<MUser> {


	public MUser findByEmail(String email) {
		for (MUser p : getAll()) if(p.email.equals(email)) return p;
        return null;
	}

	public List<MUser> search(String searchtext) {
		List<MUser> res = Lists.newArrayList();
        for (MUser p : getAll()) {
            if (p.email.contains(searchtext)) {res.add(p); continue;}
        }
        return res;
    }
}
