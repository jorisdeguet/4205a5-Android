package org.deguet.model;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

public class NQToken extends Identifiable{

	@Override
	public String toString() {
		return "Token [userID=" + userID + ", expirationDate=" + expirationDate
				+ ", getId()=" + getId() + "]";
	}

	public String userID;
	
	public Date expirationDate;

	public static NQToken forUser(NQPerson p, int validityInDays) {
		NQToken t = new NQToken();
		t.expirationDate = DateTime.now().plusDays(validityInDays).toDate();
		t.userID = p.getId();
		t.setId(UUID.randomUUID().toString());
		return t;
	}
	
}
