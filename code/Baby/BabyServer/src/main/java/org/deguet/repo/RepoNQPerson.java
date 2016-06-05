package org.deguet.repo;

import jersey.repackaged.com.google.common.collect.Lists;
import org.deguet.model.NQPerson;
import org.deguet.utils.ListRepo;

import java.util.List;

public class RepoNQPerson extends ListRepo<NQPerson> {


	public NQPerson findByEmail(String email) {
		for (NQPerson p : getAll()) if(p.email.equals(email)) return p;
        return null;
	}

	public List<NQPerson> search(String searchtext) {
		List<NQPerson> res = Lists.newArrayList();
        for (NQPerson p : getAll()) {
            if (p.email.contains(searchtext)) {res.add(p); continue;}
        }
        return res;
    }
}
