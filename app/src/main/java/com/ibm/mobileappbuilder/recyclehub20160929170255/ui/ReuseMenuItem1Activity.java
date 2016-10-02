

package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * ReuseMenuItem1Activity list activity
 */
public class ReuseMenuItem1Activity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.reuseMenuItem1Activity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return ReuseMenuItem1Fragment.class;
    }

}

