package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.recyclehub20160929170255.presenters.RecycleMenuItem1Presenter;
import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.SelectionBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.util.ViewHolder;
import java.net.URL;
import java.util.List;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.GoodsDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;
import ibmmobileappbuilder.mvp.view.CrudListView;
import ibmmobileappbuilder.ds.CrudDatasource;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "RecycleMenuItem1Fragment" listing
 */
public class RecycleMenuItem1Fragment extends ListGridFragment<GoodsDSSchemaItem> implements CrudListView<GoodsDSSchemaItem> {

    private CrudDatasource<GoodsDSSchemaItem> datasource;

    // "Add" button
    private FabBehaviour fabBehavior;

    public static RecycleMenuItem1Fragment newInstance(Bundle args) {
        RecycleMenuItem1Fragment fr = new RecycleMenuItem1Fragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new RecycleMenuItem1Presenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        // Multiple selection
        SelectionBehavior<GoodsDSSchemaItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<GoodsDSSchemaItem>() {
            @Override
            public void onSelected(List<GoodsDSSchemaItem> selectedItems) {
                getPresenter().deleteItems(selectedItems);
            }
        });
        addBehavior(selectionBehavior);
        // FAB button
        fabBehavior = new FabBehaviour(this, R.drawable.ic_add_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().addForm();
            }
        });
        addBehavior(fabBehavior);
    }

    protected SearchOptions getSearchOptions() {
      SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
      return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.recyclemenuitem1_item;
    }

    @Override
    protected Datasource<GoodsDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("products"),
              URI.create("https://ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix:361365b455c2c1ae9127cb954e367e339c9dbfbc71d14869f4840bc9f42d32c6@ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix.cloudant.com/products/"),
              GoodsDSSchemaItem.class,
              getSearchOptions(),
              null
      );
      return datasource;
    }

    @Override
    protected void bindView(GoodsDSSchemaItem item, View view, int position) {
        
        ImageLoader imageLoader = new PicassoImageLoader(view.getContext());
        ImageView image = ViewHolder.get(view, R.id.image);
        URL imageMedia = StringUtils.parseUrl(item.productImage);
        if(imageMedia != null){
          imageLoader.load(imageLoaderRequest()
                          .withPath(imageMedia.toExternalForm())
                          .withTargetView(image)
                          .fit()
                          .build()
          );
        	
        }
        else {
          imageLoader.load(imageLoaderRequest()
                          .withResourceToLoad(R.drawable.ic_ibm_placeholder)
                          .withTargetView(image)
                          .build()
          );
        }
        
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.productName != null){
            title.setText(item.productName);
            
        }
    }

    @Override
    protected void itemClicked(final GoodsDSSchemaItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(GoodsDSSchemaItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), RecycleMenuItem1DetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showAdd() {
        startActivityForResult(generateIntentToAddOrUpdateItem(null,
                        0,
                        getActivity(),
                        GoodsDSSchemaItemFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(GoodsDSSchemaItem item, int position) {
    startActivityForResult(
                generateIntentToAddOrUpdateItem(item,
                        position,
                        getActivity(),
                        GoodsDSSchemaItemFormActivity.class
                ), Constants.MODE_EDIT
        );
    }
}

