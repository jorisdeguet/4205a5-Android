package org.deguet.model;

import java.util.UUID;

/**
 * Created by joris on 15-09-15.
 */
public class TBabyPlusLast {

    public String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TBabyPlusLast{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append(", last=").append(last);
        sb.append(", eventsInDay=").append(eventsInDay);
        sb.append('}');
        return sb.toString();
    }

    public UUID id; // used to go the page afterwards.

    public MBabyEvent last;

    public int eventsInDay;

}
