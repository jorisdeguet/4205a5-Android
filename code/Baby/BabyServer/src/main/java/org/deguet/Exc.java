package org.deguet;

/**
 * Created by joris on 16-03-09.
 */
public class Exc {

    public static class VotvotException extends Exception{
        public VotvotException(){super();}
        public VotvotException(String s){super(s);}
    }

    public static class BadEmail extends VotvotException{ public BadEmail(String s){super(s);}}
    public static class BadPassword extends VotvotException{}
    public static class BadCredentials extends VotvotException{}
    public static class BadBirth extends VotvotException{}
    public static class BadSex extends VotvotException{}
    public static class BadAddress extends VotvotException{}
    public static class BadHashTag extends VotvotException{}
    public static class NotClosedYet extends VotvotException{}
    public static class AlreadyConfirmed extends VotvotException{}
    public static class AlreadyFollowed extends VotvotException{}
    public static class MaxReached extends VotvotException{}

    public static class NoOneLogged extends VotvotException{}
    public static class NoToken extends VotvotException{}
    public static class NoSuch extends VotvotException{}
    public static class NotValidConfirmer extends VotvotException{}

    public static class NoSuchPoll extends VotvotException{}
    public static class AlreadyVoted extends VotvotException{}

    public static class ClosedOrRejectedQuestion extends VotvotException{}
}
