
package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.recyclehub20160929170255.presenters.FeedbackMenuItem1FormPresenter;
import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.FeedbackDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class FeedbackDSSchemaItemFormFragment extends FormFragment<FeedbackDSSchemaItem> {

    private CrudDatasource<FeedbackDSSchemaItem> datasource;

    public static FeedbackDSSchemaItemFormFragment newInstance(Bundle args){
        FeedbackDSSchemaItemFormFragment fr = new FeedbackDSSchemaItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public FeedbackDSSchemaItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new FeedbackMenuItem1FormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected FeedbackDSSchemaItem newItem() {
        return new FeedbackDSSchemaItem();
    }

    @Override
    protected int getLayout() {
        return R.layout.feedbackmenuitem1_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final FeedbackDSSchemaItem item, View view) {
        
        bindString(R.id.name, item.name, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.name = s.toString();
            }
        });
        
        
        bindString(R.id.email, item.email, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.email = s.toString();
            }
        });
        
        
        bindString(R.id.comment, item.comment, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.comment = s.toString();
            }
        });
        
    }

    @Override
    public Datasource<FeedbackDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("feedback"),
              URI.create("https://ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix:361365b455c2c1ae9127cb954e367e339c9dbfbc71d14869f4840bc9f42d32c6@ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix.cloudant.com/feedback/"),
              FeedbackDSSchemaItem.class,
              new SearchOptions(),
              null
      );
        return datasource;
    }
}

