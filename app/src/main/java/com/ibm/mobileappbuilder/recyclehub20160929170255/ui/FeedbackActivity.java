

package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * FeedbackActivity list activity
 */
public class FeedbackActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.feedbackActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return FeedbackFragment.class;
    }

}

