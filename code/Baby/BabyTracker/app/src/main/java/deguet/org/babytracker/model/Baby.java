package deguet.org.babytracker.model;

import java.util.List;
import java.util.UUID;

/**
 * Created by joris on 15-09-15.
 */
public class Baby extends Identifiable {

    public String name;

    public List<UUID> eventsIDs;

}
