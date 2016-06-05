package org.deguet.service;


/**
 * Created by joris on 15-09-15.
 */
public class ServiceBaby {
//
//   final RepoBaby repoBaby;
//    final RepoBabyEvent repoBabyEvent;
//    final RepoUser repoUser;
//
//    public ServiceBaby(Context context){
//        this.repoBaby =         new RepoBaby(context);
//        this.repoBabyEvent =    new RepoBabyEvent(context);
//        this.repoUser =         new RepoUser(context);
//    }
//
//    @Override
//    public User signin(String login, String password){
//        User u = repoUser.getByLogin(login);
//        if (u == null) throw new IllegalArgumentException();
//        if (!u.password.equals(password)) throw new IllegalArgumentException();
//        return u;
//    }
//
//    @Override
//    public void signup(String login, String password){
//        User u = new User();
//        u.email = login;
//        u.password = password;
//        u.babiesIDs = new ArrayList<>();
//        repoUser.save(u);
//    }
//
//    @Override
//    public void signout(){
//
//    }
//
//    @Override
//    public Baby addNewBaby(User u, String name){
//        if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name");
//        Baby newOne = new Baby();
//        newOne.name = name;
//        newOne.eventsIDs = new ArrayList<>();
//        UUID id = repoBaby.save(newOne);
//        if (!u.babiesIDs.contains(id)) u.babiesIDs.add(id);
//        repoUser.save(u);
//        return newOne;
//    }
//
//    @Override
//    public void addBabyEvent(User u, Baby b, BabyEvent.Type type){
//        // TODO check if this baby is one os user's
//        BabyEvent event = new BabyEvent();
//        event.timestamp = DateTime.now().toDate();
//        event.type = type;
//        UUID id = repoBabyEvent.save(event);
//        b.eventsIDs.add(id);
//        repoBaby.save(b);
//    }
//
//    /**
//     * Returns all babies for this user sorted by last event for baby
//     * and adds the info about how many events for today
//     * @param u
//     * @return
//     */
//    @Override
//    public List<BabyPlusLast> listForHomeScreen(User u){
//        List<BabyPlusLast> result = new ArrayList<>();
//        for (Baby baby : repoBaby.getByIDs(u.babiesIDs)){
//            BabyPlusLast item = new BabyPlusLast();
//            item.name = baby.name;
//            item.id = baby.id;
//            item.eventsInDay = repoBabyEvent.numberOfEventsTodayFor(baby);
//            item.last = repoBabyEvent.lastEventFor(baby);
//            result.add(item);
//        }
//        return result;
//    }
//
//    @Override
//    public List<BabyEvent> last20EventsByTimeInverse(Baby baby){
//        return repoBabyEvent.last20Events();
//    }

}
