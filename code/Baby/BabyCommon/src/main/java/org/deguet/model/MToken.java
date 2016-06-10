package org.deguet.model;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

public class MToken extends Identifiable{

	@Override
	public String toString() {
		return "MToken [userID=" + userID + ", expirationDate=" + expirationDate
				+ ", getId()=" + getId() + "]";
	}

	public String userID;
	
	public Date expirationDate;

	public static MToken forUser(MUser p, int validityInDays) {
		MToken t = new MToken();
		t.expirationDate = DateTime.now().plusDays(validityInDays).toDate();
		t.userID = p.getId();
		t.setId(UUID.randomUUID().toString());
		return t;
	}
	
}
