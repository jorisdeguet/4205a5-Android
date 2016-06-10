package deguet.org.babytracker.service;

import org.deguet.model.MUser;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.mock.BehaviorDelegate;

/**
 * Created by joris on 16-06-10.
 */
public class ServiceBabyAuthMock implements ServiceBabyAuth{

    private final BehaviorDelegate<ServiceBabyAuth> delegate;

    public ServiceBabyAuthMock(BehaviorDelegate<ServiceBabyAuth> service) {
        this.delegate = service;
    }

    @Override
    public Call<String> signin(@Body EmailPassword s) {
        return delegate.returningResponse("hello").signin(s);
    }

    @Override
    public Call<String> signout() {
        return delegate.returningResponse("Bye").signout();
    }

    @Override
    public Call<MUser> signup(@Body EmailPassword signup) {
        MUser u = new MUser();
        u.email = "joris.deguet@deguet.org";
        u.password = "password".getBytes();
        return delegate.returningResponse(u).signup(signup);
    }

    @Override
    public Call<String> sendInvites(@Body String s) {
        return null;
    }

    @Override
    public Call<List<MUser>> all() {
        return null;
    }
}
