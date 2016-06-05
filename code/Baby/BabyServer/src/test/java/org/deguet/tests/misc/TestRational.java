package org.deguet.tests.misc;

import org.jscience.mathematics.number.Rational;
import org.junit.Test;

import java.util.Random;

/**
 * Created by joris on 16-03-29.
 */
public class TestRational {

    @Test
    public void testRational(){
        Random rand = new Random(1234);
        Rational acc = Rational.ZERO;
        for (int i = 0 ; i < 10000000 ; i++){
            Rational r = Rational.valueOf(1,1+rand.nextInt(4));
            acc = acc.plus(r);

        }
        System.out.println(acc);
    }
}
