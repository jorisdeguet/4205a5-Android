package org.deguet.tests.service;

import org.deguet.Exc;
import org.deguet.model.NQPosition;
import org.deguet.model.civil.NQAffiliation;
import org.deguet.model.civil.NQConfirmation;
import org.deguet.model.civil.NQGroup;
import org.deguet.model.NQPerson;
import org.deguet.model.C2SSignUpRequest;
import org.deguet.service.ServiceVotvot;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by joris on 16-03-19.
 */
public class TestServiceVotvot {


    @Test
    public void testSimple(){
        try{
            ServiceVotvot service = new ServiceVotvot();
            service.deletePeople();
            service.allPeople();
        }
        catch(Throwable t){
            t.printStackTrace();
            throw t;
        }
    }

    @Test
    public void testAjoutUnique() throws NoSuchAlgorithmException, Exc.MaxReached, Exc.BadBirth, UnsupportedEncodingException, Exc.BadEmail, Exc.BadSex {
        try{
            ServiceVotvot service = new ServiceVotvot();
            service.deletePeople();
            {
                C2SSignUpRequest req = new C2SSignUpRequest();
                req.birthDate = DateTime.now().minusYears(34);
                req.email = "joris@deguet.org";
                req.firstName = "Jo";
                req.lastName = "Plop";
                req.password = "Password";
                req.sex = NQPerson.Sex.Male;
                req.birthPlace = new NQPosition();
                service.signUp(req);
            }
            {
                C2SSignUpRequest req = new C2SSignUpRequest();
                req.birthDate = DateTime.now().minusYears(34);
                req.email = "joris@deguet.org";
                req.firstName = "Jo";
                req.lastName = "Plop";
                req.password = "Password";
                req.sex = NQPerson.Sex.Male;
                req.birthPlace = new NQPosition();
                service.signUp(req);
            }

        }
        catch(Throwable t){
            t.printStackTrace();
            throw t;
        }
    }

}
