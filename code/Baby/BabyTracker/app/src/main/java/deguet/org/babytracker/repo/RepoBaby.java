package deguet.org.babytracker.repo;

import android.content.Context;

import org.deguet.model.MBaby;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by joris on 15-09-15.
 */
public class RepoBaby extends RepoGSON<MBaby>{

    public RepoBaby(Context context){
        super(context, MBaby.class);
    }

    public List<MBaby> getByIDs(List<UUID> babiesIDs) {
        List<MBaby> result = new ArrayList<>();
        for (UUID id : babiesIDs){
            result.add(this.getById(id));
        }
        return result;
    }
}
