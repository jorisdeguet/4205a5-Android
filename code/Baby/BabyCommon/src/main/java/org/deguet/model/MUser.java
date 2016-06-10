package org.deguet.model;

import java.util.List;
import java.util.UUID;

/**
 * Created by joris on 15-09-15.
 */
public class MUser extends Identifiable{

    public String email;

    public byte[] password;

    public List<UUID> babiesIDs;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MUser{");
        sb.append("email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", babiesIDs=").append(babiesIDs);
        sb.append('}');
        return sb.toString();
    }
}
