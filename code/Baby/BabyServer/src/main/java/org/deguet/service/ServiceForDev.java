package org.deguet.service;

import org.deguet.model.MUser;
import org.deguet.model.TLoginPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


public class ServiceForDev {

	final Logger logger = LoggerFactory.getLogger(getClass());

	private final ServiceVotvot svote;

	public ServiceVotvot getSvote() {
		return svote;
	}

	public ServiceForDev(ServiceVotvot sv){
		this.svote 		= sv;
	}

	public void clearAll(){
		svote.deletePeople();
	}


    public void createSampleAll(int size){
        Random r = new Random(1234);
        try {
            // base objects
            logger.debug("SERVICE DEV : Creating all ");
            logger.debug("SERVICE DEV : Creating people ");
            this.createSamplesUsers(r,size);

            logger.debug("SERVICE DEV : Creating groups and adding members ");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


	public void createSamplesUsers(Random r, int sizeBy100) {
		String[] first = {"arnaud", "joris","malcolm","évariste","alexandre",
				"paul","ringo","george","leandre","andrée",
				//"madeleine","camille","isaac","clément","norbert",
				//"mick","raoul","roger","robert","réjean",
				//"marie-claude","jeanine","emilia","Marianne","Lucette"
		};
		String[] last = {
				//"lennon","mccartney","star","harrison",
				//"leduc","delury","richards","jeiger",
				"deguet","morin","dupont","valtrid","levasseur"
		};
		for (String f : first){
			logger.debug("SERVICE DEV : "+f+" ");
			for (String l : last){
				for (int i = 0 ; i < sizeBy100 ; i++){
					try {
						TLoginPassword p = new TLoginPassword();
						p.email = f + (i != 0 ? i + "" : "") + "@" + l + ".org";
						p.password = "password";
						//System.out.println(p);
						MUser person = svote.signUp(p);
					}
					catch (Exception e){

                        e.printStackTrace();
                    }
				}
			}
		}
	}

}
