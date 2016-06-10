package deguet.org.babytracker.repo;

import android.content.Context;

import org.deguet.model.MUser;

/**
 * Created by joris on 15-09-15.
 */
public class RepoUser extends RepoGSON<MUser> {

    public RepoUser(Context context){
        super(context, MUser.class);
    }

    public MUser getByLogin(String login){
        for (MUser u : this.getAll()){
            if (u.email.equals(login)) return u;
        }
        return null;
    }

}
