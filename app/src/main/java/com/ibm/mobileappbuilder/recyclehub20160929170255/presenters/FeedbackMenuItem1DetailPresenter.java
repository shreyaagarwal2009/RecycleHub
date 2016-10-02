
package com.ibm.mobileappbuilder.recyclehub20160929170255.presenters;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.FeedbackDSSchemaItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class FeedbackMenuItem1DetailPresenter extends BasePresenter implements DetailCrudPresenter<FeedbackDSSchemaItem>,
      Datasource.Listener<FeedbackDSSchemaItem> {

    private final CrudDatasource<FeedbackDSSchemaItem> datasource;
    private final DetailView view;

    public FeedbackMenuItem1DetailPresenter(CrudDatasource<FeedbackDSSchemaItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(FeedbackDSSchemaItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(FeedbackDSSchemaItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(FeedbackDSSchemaItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

