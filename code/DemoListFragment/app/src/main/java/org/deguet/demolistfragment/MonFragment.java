package org.deguet.demolistfragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joris on 16-08-25.
 */
public class MonFragment extends ListFragment {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> liste = Arrays.asList("Jo","Blo");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, liste);
        setListAdapter(adapter);
    }


}
