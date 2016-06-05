package org.deguet.model;

import org.deguet.model.Identifiable;
import org.deguet.model.NQPerson;

import java.util.Date;

/**
 * Created by joris on 15-10-14.
 */
public class S2CPersonWithoutPassword extends Identifiable{


    public String email;

    public S2CPersonWithoutPassword(NQPerson p){
        this.setId(p.getId());
        this.email =    p.email;
    }

}
