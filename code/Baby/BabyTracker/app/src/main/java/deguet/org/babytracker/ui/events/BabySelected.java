package deguet.org.babytracker.ui.events;

import java.util.UUID;

/**
 * Created by joris on 15-09-16.
 */
public class BabySelected {

    public UUID uuid;

    public BabySelected(UUID uuid) {
        this.uuid = uuid;
    }
}
