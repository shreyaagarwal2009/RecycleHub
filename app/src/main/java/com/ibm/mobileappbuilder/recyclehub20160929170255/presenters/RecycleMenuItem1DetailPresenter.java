
package com.ibm.mobileappbuilder.recyclehub20160929170255.presenters;

import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.GoodsDSSchemaItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class RecycleMenuItem1DetailPresenter extends BasePresenter implements DetailCrudPresenter<GoodsDSSchemaItem>,
      Datasource.Listener<GoodsDSSchemaItem> {

    private final CrudDatasource<GoodsDSSchemaItem> datasource;
    private final DetailView view;

    public RecycleMenuItem1DetailPresenter(CrudDatasource<GoodsDSSchemaItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(GoodsDSSchemaItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(GoodsDSSchemaItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(GoodsDSSchemaItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

