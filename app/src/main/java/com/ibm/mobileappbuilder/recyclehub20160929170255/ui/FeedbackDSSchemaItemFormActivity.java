

package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ibmmobileappbuilder.ui.BaseDetailActivity;

/**
 * FeedbackDSSchemaItemFormActivity form activity
 */
public class FeedbackDSSchemaItemFormActivity extends BaseDetailActivity {
  	
  	@Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return FeedbackDSSchemaItemFormFragment.class;
    }
}


