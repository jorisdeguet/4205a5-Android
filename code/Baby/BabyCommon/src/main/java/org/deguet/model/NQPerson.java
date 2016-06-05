package org.deguet.model;

import java.util.Base64;


public class NQPerson extends Identifiable implements Comparable<NQPerson>{

	// authentication
    public String email;

    public byte[] password;

	@Override
	public String toString() {
		return "Person [email=" + email + ", password="
				+ Base64.getEncoder().encodeToString(password)
				+ "]";
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NQPerson nqPerson = (NQPerson) o;
        return getId().equals(nqPerson.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
	public int compareTo(NQPerson o) {
		return this.email.compareTo(o.email);
	}
}
