package deguet.org.babytracker;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by joris on 15-09-15.
 */
public class SingletonBus {

    public static Bus ottoBus = new Bus(ThreadEnforcer.ANY);
}
