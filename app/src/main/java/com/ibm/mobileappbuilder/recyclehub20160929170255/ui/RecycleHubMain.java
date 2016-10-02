package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import ibmmobileappbuilder.ui.DrawerActivity;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;

public class RecycleHubMain extends DrawerActivity {

    private final SparseArray<Class<? extends Fragment>> sectionFragments = new SparseArray<>();
    {
                sectionFragments.append(R.id.entry0, MainScreenFragment.class);
            sectionFragments.append(R.id.entry1, AboutUsFragment.class);
            sectionFragments.append(R.id.entry2, FeedbackFragment.class);
            sectionFragments.append(R.id.entry3, LogoutFragment.class);
    }

    @Override
    public SparseArray<Class<? extends Fragment>> getSectionFragmentClasses() {
      return sectionFragments;
    }

}

