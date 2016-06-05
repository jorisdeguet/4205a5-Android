package org.deguet.service;

import org.deguet.model.NQPerson;
import org.deguet.model.C2SSignUpRequest;
import org.deguet.model.S2CPersonLight;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by joris on 16-03-19.
 */
public class ServiceConversion {

    public static S2CPersonLight convert(NQPerson res) {
        S2CPersonLight result = new S2CPersonLight();
        result.id = res.getId();
        result.email = anonymiseEmail(res.email);
        return result;
    }

    public static String anonymiseEmail(String email){
        StringBuilder result = new StringBuilder();
        String[] parts = email.split("@");
        if (parts.length != 2) throw new IllegalArgumentException();
        for (String piece : parts[0].split("\\.")){
            result.append(piece.substring(0,1) + piece.substring(2).replaceAll(".","_")+piece.substring(piece.length()-1));
            result.append(".");
        }
        result.setLength(result.length() - 1);
        result.append("@");
        for (String piece : parts[1].split("\\.")){
            result.append(piece.substring(0,1) + piece.substring(2).replaceAll(".","_")+piece.substring(piece.length()-1));
            result.append(".");
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }

    public static NQPerson convert(C2SSignUpRequest r)  {
        NQPerson p = new NQPerson();
        p.email = 			r.email.toLowerCase().trim();
        p.password = 		hash(r.password);
        //p.birthDate = 		r.birthDate.toDate();
        return p;
    }

    public static byte[] hash(String s) {
        try{
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            md.update(s.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return digest;
        }
        catch(UnsupportedEncodingException e){throw new Error();}
        catch (NoSuchAlgorithmException e) {throw new Error();}
    }

}
