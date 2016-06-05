package org.deguet.utils;

import jersey.repackaged.com.google.common.collect.Lists;
import org.deguet.model.Identifiable;

import java.util.List;
import java.util.UUID;

/**
 * Created by joris on 16-05-30.
 */
public class ListRepo<T extends Identifiable> implements CRUD<T> {

    private List<T> internalList = Lists.newArrayList();

    @Override
    public int count() {
        return internalList.size();
    }

    @Override
    public T get(String id) {
        for (T t  : getAll()){
            if (t.getId().equals(id)) return t;
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return Lists.newArrayList(internalList);
    }

    @Override
    public void save(T a) throws BadId {
        if (a.getId() == null) {
            a.setId(UUID.randomUUID().toString());
        }
        delete(a);
        internalList.add(a);
    }

    @Override
    public void delete(T a) {
        T rec = this.get(a.getId());
        if (rec != null) internalList.remove(rec);
    }

    @Override
    public void deleteAll() {
        internalList.clear();
    }
}
