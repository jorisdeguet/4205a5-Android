package org.deguet.service;

import org.deguet.AppConfig;
import org.deguet.Exc.*;
import org.deguet.model.MToken;
import org.deguet.model.MUser;
import org.deguet.model.TLoginPassword;
import org.deguet.repo.RepoUser;
import org.deguet.repo.RepoToken;
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

    private static final RepoUser repoperson = new RepoUser();
    private static final RepoToken repoToken = new RepoToken();

    public MUser signUp(TLoginPassword r)  {
        logger.debug("SERVICE : SIGNUP for " + r);
        //validateSignUp(r);
        MUser p = ServiceConversion.convert(r);
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

    public List<MUser> allPeople() {
        return repoperson.getAll();
    }

    public MToken signin(String login, String password) throws BadCredentials {
        // if credentials are good
        logger.debug("SERVICE : SIGNIN request " + login);
        byte[] hashed = ServiceConversion.hash(password);
        MUser p;
        logger.debug("SERVICE : SIGNIN try find by email " + login);
        logger.debug("SERVICE : SIGNIN all " + allPeople());
        try{p = this.findByEmail(login);}catch(BadEmail e) {throw new BadCredentials();}
        logger.debug("SERVICE : SIGNIN password " + hashed );
        if (p != null && Arrays.equals(p.password, hashed) ){
            // get token associated with this login and delete it
            MToken t;
            try {
                t = getTokenFor(p.getId());
                repoToken.delete(t);
            } catch (NoToken e) {}
            // produce a new one
            MToken newT = MToken.forUser(p, AppConfig.getTokenValidity());
            repoToken.save(newT);
            // return it serialized
            return newT;
        }
        // otherwise
        else{
            throw new BadCredentials();
        }
    }

    public MUser returnWithToken(String id) throws NoToken{
        //UUID uuid = UUID.fromString(id);
        MToken t = repoToken.get(id);
        if (t == null){
            throw new NoToken();
        }
        MUser p = repoperson.get(t.userID);
        if (p == null)
            throw new NoToken();
        return p;
    }

    public MUser findByEmail(String login) throws BadEmail {
        MUser p = repoperson.findByEmail(login.toLowerCase().trim());
        if (p == null) throw new BadEmail(login);
        return p;
    }

    public MToken getTokenFor(String userid) throws NoToken {
        MToken token = repoToken.findByUserId(userid);
        if (token == null ) throw new NoToken();
        return token;

    }

    public void deletePeople() {
        repoperson.deleteAll();
    }

    public void signout(String tokenID) {
        MToken t = repoToken.get(tokenID);
        if (t != null)
            repoToken.delete(t);
    }
}
