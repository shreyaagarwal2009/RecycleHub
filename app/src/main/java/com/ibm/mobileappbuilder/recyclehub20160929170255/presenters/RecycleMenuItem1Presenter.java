
package com.ibm.mobileappbuilder.recyclehub20160929170255.presenters;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.GoodsDSSchemaItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class RecycleMenuItem1Presenter extends BasePresenter implements ListCrudPresenter<GoodsDSSchemaItem>,
      Datasource.Listener<GoodsDSSchemaItem>{

    private final CrudDatasource<GoodsDSSchemaItem> crudDatasource;
    private final CrudListView<GoodsDSSchemaItem> view;

    public RecycleMenuItem1Presenter(CrudDatasource<GoodsDSSchemaItem> crudDatasource,
                                         CrudListView<GoodsDSSchemaItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(GoodsDSSchemaItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<GoodsDSSchemaItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(GoodsDSSchemaItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(GoodsDSSchemaItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(GoodsDSSchemaItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

