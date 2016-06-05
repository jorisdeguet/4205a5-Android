package deguet.org.babytracker;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;
import java.util.UUID;

import deguet.org.babytracker.model.User;
import deguet.org.babytracker.repo.RepoUser;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testRepo(){
        RepoUser repo = new RepoUser(getContext());
        repo.deleteAll();
        for (int i = 0 ; i < 10 ; i++){
            User u = new User();
            u.email = "jo"+i;
            u.password = "pass";
            u.babiesIDs = new ArrayList<UUID>();
            repo.save(u);
        }
        System.out.println("repo getall " + repo.getAll());
    }

}