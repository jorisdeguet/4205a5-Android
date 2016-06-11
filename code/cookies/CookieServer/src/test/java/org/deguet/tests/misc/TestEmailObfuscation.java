package org.deguet.tests.misc;

import org.deguet.service.ServiceVotvot;
import org.junit.Assert;
import org.junit.Test;

import static org.deguet.service.ServiceConversion.anonymiseEmail;

/**
 * Created by joris on 16-03-15.
 */
public class TestEmailObfuscation {

    @Test
    public void testObfus(){
        String obf = anonymiseEmail("joris.deguet@gmail.com");
        System.out.println(obf);
        Assert.assertTrue(obf.startsWith("j___s"));
    }

}
