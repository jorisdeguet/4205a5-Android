package deguet.org.babytracker.repo;

import android.content.Context;

import deguet.org.babytracker.model.User;

/**
 * Created by joris on 15-09-15.
 */
public class RepoUser extends RepoGSON<User> {

    public RepoUser(Context context){
        super(context, User.class);
    }

    public User getByLogin(String login){
        for (User u : this.getAll()){
            if (u.email.equals(login)) return u;
        }
        return null;
    }

}
