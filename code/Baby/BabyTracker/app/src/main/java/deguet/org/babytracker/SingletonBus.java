package deguet.org.babytracker;

import com.google.common.eventbus.EventBus;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by joris on 15-09-15.
 */
public class SingletonBus {

    public static EventBus guavaBus = new EventBus();

    public static Bus ottoBus = new Bus(ThreadEnforcer.ANY);
}
