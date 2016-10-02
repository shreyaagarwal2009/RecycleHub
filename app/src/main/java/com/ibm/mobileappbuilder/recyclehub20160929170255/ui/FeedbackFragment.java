

package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;

/**
 * FeedbackFragment menu fragment.
 */
public class FeedbackFragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public FeedbackFragment(){
        super();
    }

    // Factory method
    public static FeedbackFragment newInstance(Bundle args) {
        FeedbackFragment fragment = new FeedbackFragment();

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
            .setLabel("FEEDBACKS")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(FeedbackMenuItem1Activity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.feedback_item;
    }
}

