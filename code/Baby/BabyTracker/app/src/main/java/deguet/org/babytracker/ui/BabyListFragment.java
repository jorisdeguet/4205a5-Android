package deguet.org.babytracker.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import deguet.org.babytracker.SingletonBus;
import deguet.org.babytracker.R;
import deguet.org.babytracker.transfer.BabyPlusLast;
import deguet.org.babytracker.ui.events.BabySelected;

/**
 * Created by joris on 15-09-15.
 */
public class BabyListFragment extends ListFragment {

    private List<BabyPlusLast> list = new ArrayList<>();

    private BabyListAdapter adapter;

    @Override public void onResume() { SingletonBus.guavaBus.register(this);     super.onResume();}
    @Override public void onPause() {   SingletonBus.guavaBus.unregister(this);    super.onPause();}

    public static class BabyListAdapter extends ArrayAdapter<BabyPlusLast>{
        public BabyListAdapter(Context c, List<BabyPlusLast> list){
            super(c, android.R.layout.simple_list_item_1, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //View v = super.getView(position, convertView, parent);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.baby_list_item, null);
            BabyPlusLast bpl = getItem(position);
            TextView name = (TextView) v.findViewById(R.id.name); name.setText(bpl.name);
            TextView last = (TextView) v.findViewById(R.id.last);
            if (bpl.last == null){
                last.setText("---");
            }
            else{
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                last.setText(bpl.last.type+" @ " + sdf.format(bpl.last.timestamp));
            }
            TextView number = (TextView) v.findViewById(R.id.numberInDay);
            number.setText("Events for today ::: "+bpl.eventsInDay);
            return v;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        adapter = new BabyListAdapter(getActivity(), list);
        this.setListAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        UUID uuid = list.get(position).id;
        SingletonBus.guavaBus.post(new BabySelected(uuid));
        super.onListItemClick(l, v, position, id);
    }

    public void show(List<BabyPlusLast> babyPlusLasts) {
        list.clear();
        list.addAll(babyPlusLasts);
        adapter.notifyDataSetChanged();
    }
}
