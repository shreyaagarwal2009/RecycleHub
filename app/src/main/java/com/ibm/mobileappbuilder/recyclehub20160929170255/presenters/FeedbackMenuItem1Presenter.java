
package com.ibm.mobileappbuilder.recyclehub20160929170255.presenters;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.FeedbackDSSchemaItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class FeedbackMenuItem1Presenter extends BasePresenter implements ListCrudPresenter<FeedbackDSSchemaItem>,
      Datasource.Listener<FeedbackDSSchemaItem>{

    private final CrudDatasource<FeedbackDSSchemaItem> crudDatasource;
    private final CrudListView<FeedbackDSSchemaItem> view;

    public FeedbackMenuItem1Presenter(CrudDatasource<FeedbackDSSchemaItem> crudDatasource,
                                         CrudListView<FeedbackDSSchemaItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(FeedbackDSSchemaItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<FeedbackDSSchemaItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(FeedbackDSSchemaItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(FeedbackDSSchemaItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(FeedbackDSSchemaItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

