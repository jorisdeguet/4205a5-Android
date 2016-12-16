package org.deguet.tests.gson;

import org.deguet.MonGsonAMoiPersoAvecLesTrucsQueJAime;
import org.deguet.model.ChoseTruc;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by joris on 16-06-11.
 */
public class TestGson {

    @Test
    public void testAllerRetour() throws UnsupportedEncodingException {
        ChoseTruc truc = new ChoseTruc();
        truc.dateTime = DateTime.now().minusDays(123);
        truc.date = new Date();
        truc.pleinDOctets = "de quoi que c'est ça des octets pouet pouet".getBytes("UTF-8");

        String encode = MonGsonAMoiPersoAvecLesTrucsQueJAime.getIt().toJson(truc);
        System.out.println(encode);

        ChoseTruc decode = MonGsonAMoiPersoAvecLesTrucsQueJAime.getIt().fromJson(encode,ChoseTruc.class);

        Assert.assertEquals(truc.date , decode.date);
        Assert.assertEquals(truc.dateTime , decode.dateTime);
        Assert.assertArrayEquals(truc.pleinDOctets , decode.pleinDOctets);
    }
}
