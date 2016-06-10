package deguet.org.babytracker.repo;

import android.content.Context;

import org.deguet.model.MBaby;
import org.deguet.model.MBabyEvent;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


/**
 * Created by joris on 15-09-15.
 */
public class RepoBabyEvent extends RepoGSON<MBabyEvent>{

    public RepoBabyEvent(Context context){
        super(context,MBabyEvent.class);
    }

    public List<MBabyEvent> last20Events() {
        List<MBabyEvent> result = new ArrayList<>();
        result.addAll(this.getAll());
        // sort by inverse date
        Comparator<MBabyEvent> comp = new Comparator<MBabyEvent>() {
            @Override
            public int compare(MBabyEvent lhs, MBabyEvent rhs) {
                return rhs.timestamp.compareTo(lhs.timestamp);
            }
        };
        Collections.sort(result,comp);
        if (result.size() > 20) return result.subList(0,20);
        return result;
    }

    public int numberOfEventsTodayFor(MBaby baby) {
        int result = 0;
        for (UUID id : baby.eventsIDs){
            MBabyEvent event = this.getById(id);
            // see if same date
            if (new DateTime(event.timestamp).withTimeAtStartOfDay().equals(DateTime.now().withTimeAtStartOfDay())){
                result++;
            }
        }
        return result;
    }

    public MBabyEvent lastEventFor(MBaby baby) {
        MBabyEvent result = null;
        for (UUID id : baby.eventsIDs){
            MBabyEvent current = this.getById(id);
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
