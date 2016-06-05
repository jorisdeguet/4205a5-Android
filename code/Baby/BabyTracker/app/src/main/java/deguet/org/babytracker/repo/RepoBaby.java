package deguet.org.babytracker.repo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import deguet.org.babytracker.model.Baby;
import deguet.org.babytracker.model.BabyEvent;

/**
 * Created by joris on 15-09-15.
 */
public class RepoBaby extends RepoGSON<Baby>{

    public RepoBaby(Context context){
        super(context, Baby.class);
    }

    public List<Baby> getByIDs(List<UUID> babiesIDs) {
        List<Baby> result = new ArrayList<>();
        for (UUID id : babiesIDs){
            result.add(this.getById(id));
        }
        return result;
    }
}
