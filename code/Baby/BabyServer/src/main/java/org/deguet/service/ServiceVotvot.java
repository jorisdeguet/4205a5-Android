package org.deguet.service;

import org.deguet.AppConfig;
import org.deguet.Exc.*;
import org.deguet.model.NQToken;
import org.deguet.model.NQPerson;
import org.deguet.model.C2SSignUpRequest;
import org.deguet.repo.RepoNQPerson;
import org.deguet.repo.RepoNQToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * Change of philosophy, by default, everything is valid. Then if suspicious, question it.
 *
 * Basic ID is far more important that groups for validity, focus on that.
 */
public class ServiceVotvot {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private static final RepoNQPerson 				repoperson = new RepoNQPerson();
    private static final RepoNQToken				repoToken = new RepoNQToken();

    public NQPerson signUp(C2SSignUpRequest r) throws
            BadEmail, BadBirth, BadSex, NoSuchAlgorithmException, UnsupportedEncodingException,MaxReached {
        logger.debug("SERVICE : SIGNUP for " + r);
        //validateSignUp(r);
        NQPerson p = ServiceConversion.convert(r);
        boolean autovalid = false;
        if (repoperson.count() == 0 ){
            autovalid = true;
        }

        // we rely on the database unique constraint to detect failed creation
        logger.debug("SERVICE : SIGNUP before save " + allPeople());
        repoperson.save(p);
        logger.debug("SERVICE : SIGNUP after  save " + allPeople());
        return p;

    }

    public List<NQPerson> allPeople() {
        return repoperson.getAll();
    }

    public NQToken signin(String login, String password) throws BadCredentials {
        // if credentials are good
        logger.debug("SERVICE : SIGNIN request " + login);
        byte[] hashed = ServiceConversion.hash(password);
        NQPerson p;
        logger.debug("SERVICE : SIGNIN try find by email " + login);
        logger.debug("SERVICE : SIGNIN all " + allPeople());
        try{p = this.findByEmail(login);}catch(BadEmail e) {throw new BadCredentials();}
        logger.debug("SERVICE : SIGNIN password " + hashed );
        if (p != null && Arrays.equals(p.password, hashed) ){
            // get token associated with this login and delete it
            NQToken t;
            try {
                t = getTokenFor(p.getId());
                repoToken.delete(t);
            } catch (NoToken e) {}
            // produce a new one
            NQToken newT = NQToken.forUser(p, AppConfig.getTokenValidity());
            repoToken.save(newT);
            // return it serialized
            return newT;
        }
        // otherwise
        else{
            throw new BadCredentials();
        }
    }

    public NQPerson returnWithToken(String id) throws NoToken{
        //UUID uuid = UUID.fromString(id);
        NQToken t = repoToken.get(id);
        if (t == null){
            throw new NoToken();
        }
        NQPerson p = repoperson.get(t.userID);
        if (p == null)
            throw new NoToken();
        return p;
    }

    public NQPerson findByEmail(String login) throws BadEmail {
        NQPerson p = repoperson.findByEmail(login.toLowerCase().trim());
        if (p == null) throw new BadEmail(login);
        return p;
    }

    public NQToken getTokenFor(String userid) throws NoToken {
        NQToken token = repoToken.findByUserId(userid);
        if (token == null ) throw new NoToken();
        return token;

    }

    public void deletePeople() {
        repoperson.deleteAll();
    }

    public void signout(String tokenID) {
        NQToken t = repoToken.get(tokenID);
        if (t != null)
            repoToken.delete(t);
    }
}
