package org.deguet;

import org.deguet.service.ServiceForDev;
import org.deguet.service.ServiceVotvot;

public class Services {

	public static final ServiceVotvot vote = 			new ServiceVotvot();
	public static final ServiceForDev initial = 	new ServiceForDev(vote);

}
