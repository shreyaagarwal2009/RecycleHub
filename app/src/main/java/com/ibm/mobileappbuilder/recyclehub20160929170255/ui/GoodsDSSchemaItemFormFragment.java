
package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.recyclehub20160929170255.presenters.RecycleMenuItem1FormPresenter;
import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.GeoPicker;
import ibmmobileappbuilder.views.ImagePicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.io.IOException;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.GoodsDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class GoodsDSSchemaItemFormFragment extends FormFragment<GoodsDSSchemaItem> {

    private CrudDatasource<GoodsDSSchemaItem> datasource;

    public static GoodsDSSchemaItemFormFragment newInstance(Bundle args){
        GoodsDSSchemaItemFormFragment fr = new GoodsDSSchemaItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public GoodsDSSchemaItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new RecycleMenuItem1FormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected GoodsDSSchemaItem newItem() {
        return new GoodsDSSchemaItem();
    }

    @Override
    protected int getLayout() {
        return R.layout.recyclemenuitem1_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final GoodsDSSchemaItem item, View view) {
        
        bindString(R.id.productimage, item.productImage, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.productImage = s.toString();
            }
        });
        
        
        bindString(R.id.productname, item.productName, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.productName = s.toString();
            }
        });
        
        
        bindLong(R.id.saleprice, item.salePrice, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.salePrice = StringUtils.parseLong(s.toString());
            }
        });
        
        
        bindLocation(R.id.productlocation, item.productLocation,
            new GeoPicker.PointChangedListener() {
                @Override
                public void onPointChanged(GeoPoint point) {
                    item.productLocation = point;
                }
            }
        );
        
        
        bindLong(R.id.contact, item.contact, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.contact = StringUtils.parseLong(s.toString());
            }
        });
        
    }

    @Override
    public Datasource<GoodsDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("products"),
              URI.create("https://ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix:361365b455c2c1ae9127cb954e367e339c9dbfbc71d14869f4840bc9f42d32c6@ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix.cloudant.com/products/"),
              GoodsDSSchemaItem.class,
              new SearchOptions(),
              null
      );
        return datasource;
    }
}

