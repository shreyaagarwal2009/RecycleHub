

package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;

/**
 * MainScreenFragment menu fragment.
 */
public class MainScreenFragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public MainScreenFragment(){
        super();
    }

    // Factory method
    public static MainScreenFragment newInstance(Bundle args) {
        MainScreenFragment fragment = new MainScreenFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
                }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("Reuse")
            .setIcon(R.drawable.png_download136)
            .setAction(new StartActivityAction(ReuseMenuItem1Activity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Recycle")
            .setIcon(R.drawable.png_images930)
            .setAction(new StartActivityAction(RecycleMenuItem1Activity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.mainscreen_item;
    }
}

