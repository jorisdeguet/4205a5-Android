package deguet.org.babytracker.transfer;

import java.util.UUID;

import deguet.org.babytracker.model.BabyEvent;

/**
 * Created by joris on 15-09-15.
 */
public class BabyPlusLast {

    public String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BabyPlusLast{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append(", last=").append(last);
        sb.append(", eventsInDay=").append(eventsInDay);
        sb.append('}');
        return sb.toString();
    }

    public UUID id; // used to go the page afterwards.

    public BabyEvent last;

    public int eventsInDay;

}
