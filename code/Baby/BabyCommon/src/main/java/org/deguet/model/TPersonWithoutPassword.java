package org.deguet.model;

import org.deguet.model.Identifiable;

import java.util.Date;

/**
 * Created by joris on 15-10-14.
 */
public class TPersonWithoutPassword extends Identifiable{


    public String email;

    public TPersonWithoutPassword(MUser p){
        this.setId(p.getId());
        this.email =    p.email;
    }

}
