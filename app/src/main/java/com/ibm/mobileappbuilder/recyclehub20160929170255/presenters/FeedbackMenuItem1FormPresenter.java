
package com.ibm.mobileappbuilder.recyclehub20160929170255.presenters;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.FeedbackDSSchemaItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BaseFormPresenter;
import ibmmobileappbuilder.mvp.view.FormView;

public class FeedbackMenuItem1FormPresenter extends BaseFormPresenter<FeedbackDSSchemaItem> {

    private final CrudDatasource<FeedbackDSSchemaItem> datasource;

    public FeedbackMenuItem1FormPresenter(CrudDatasource<FeedbackDSSchemaItem> datasource, FormView<FeedbackDSSchemaItem> view){
        super(view);
        this.datasource = datasource;
    }

    @Override
    public void deleteItem(FeedbackDSSchemaItem item) {
        datasource.deleteItem(item, new OnItemDeletedListener());
    }

    @Override
    public void save(FeedbackDSSchemaItem item) {
        // validate
        if (validate(item)){
            datasource.updateItem(item, new OnItemUpdatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    @Override
    public void create(FeedbackDSSchemaItem item) {
        // validate
        if (validate(item)){
            datasource.create(item, new OnItemCreatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    private class OnItemDeletedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(FeedbackDSSchemaItem  item) {
                        view.showMessage(R.string.item_deleted, true);
            view.close(true);
        }
    }

    private class OnItemUpdatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(FeedbackDSSchemaItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_updated, true);
            view.close(true);
        }
    }

    private class OnItemCreatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(FeedbackDSSchemaItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_created, true);
            view.close(true);
        }
    }

    private abstract class ShowingErrorOnFailureListener implements Datasource.Listener<FeedbackDSSchemaItem > {
        @Override
        public void onFailure(Exception e) {
            view.showMessage(R.string.error_data_generic, true);
        }
    }

}

