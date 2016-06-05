package deguet.org.babytracker.repo;

import android.content.Context;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import deguet.org.babytracker.model.Baby;
import deguet.org.babytracker.model.BabyEvent;

/**
 * Created by joris on 15-09-15.
 */
public class RepoBabyEvent extends RepoGSON<BabyEvent>{

    public RepoBabyEvent(Context context){
        super(context,BabyEvent.class);
    }

    public List<BabyEvent> last20Events() {
        List<BabyEvent> result = new ArrayList<>();
        result.addAll(this.getAll());
        // sort by inverse date
        Comparator<BabyEvent> comp = new Comparator<BabyEvent>() {
            @Override
            public int compare(BabyEvent lhs, BabyEvent rhs) {
                return rhs.timestamp.compareTo(lhs.timestamp);
            }
        };
        Collections.sort(result,comp);
        if (result.size() > 20) return result.subList(0,20);
        return result;
    }

    public int numberOfEventsTodayFor(Baby baby) {
        int result = 0;
        for (UUID id : baby.eventsIDs){
            BabyEvent event = this.getById(id);
            // see if same date
            if (new DateTime(event.timestamp).withTimeAtStartOfDay().equals(DateTime.now().withTimeAtStartOfDay())){
                result++;
            }
        }
        return result;
    }

    public BabyEvent lastEventFor(Baby baby) {
        BabyEvent result = null;
        for (UUID id : baby.eventsIDs){
            BabyEvent current = this.getById(id);
            if (result == null) {
                result = current;
                continue;
            }
            // see if same date
            if (current.timestamp.after(result.timestamp)){
                result = current;
            }
        }
        return result;
    }
}
