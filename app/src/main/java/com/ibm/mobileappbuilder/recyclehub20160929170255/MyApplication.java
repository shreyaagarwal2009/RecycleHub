

package com.ibm.mobileappbuilder.recyclehub20160929170255;

import android.app.Application;
import ibmmobileappbuilder.injectors.ApplicationInjector;
import android.app.Activity;
import android.os.Bundle;
import ibmmobileappbuilder.util.SecurePreferences;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ui.LoginActivity;
import ibmmobileappbuilder.util.LoginUtils;
import ibmmobileappbuilder.cloudant.factory.CloudantDatabaseSyncerFactory;
import java.net.URI;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private SecurePreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
        mSharedPreferences = new SecurePreferences(this);
        registerActivityLifecycleCallbacks(this);

        mSharedPreferences.edit().putLong(LoginUtils.EXPIRATION_TIME, LoginUtils.SESSION_EXPIRED).apply();
        //Syncing cloudant ds
        CloudantDatabaseSyncerFactory.instanceFor(
            "products",
            URI.create("https://ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix:361365b455c2c1ae9127cb954e367e339c9dbfbc71d14869f4840bc9f42d32c6@ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix.cloudant.com/products/")
        ).sync(null);
          CloudantDatabaseSyncerFactory.instanceFor(
            "feedback",
            URI.create("https://ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix:361365b455c2c1ae9127cb954e367e339c9dbfbc71d14869f4840bc9f42d32c6@ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix.cloudant.com/feedback/")
        ).sync(null);
      }

    public SecurePreferences getSecureSharedPreferences() {
        return mSharedPreferences;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        boolean splashShown = false;
        if(!splashShown && !(activity instanceof LoginActivity) ){
            LoginUtils.checkLoggedStatus(mSharedPreferences, LoginActivity.class, activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if(!(activity instanceof LoginActivity) ) {
            LoginUtils.storeLastActiveStatus(mSharedPreferences);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


}

