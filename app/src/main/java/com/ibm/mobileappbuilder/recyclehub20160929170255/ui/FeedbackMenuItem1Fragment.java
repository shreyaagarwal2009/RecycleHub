package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.recyclehub20160929170255.presenters.FeedbackMenuItem1Presenter;
import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.SelectionBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.ViewHolder;
import java.util.List;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.FeedbackDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;
import ibmmobileappbuilder.mvp.view.CrudListView;
import ibmmobileappbuilder.ds.CrudDatasource;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "FeedbackMenuItem1Fragment" listing
 */
public class FeedbackMenuItem1Fragment extends ListGridFragment<FeedbackDSSchemaItem> implements CrudListView<FeedbackDSSchemaItem> {

    private CrudDatasource<FeedbackDSSchemaItem> datasource;

    // "Add" button
    private FabBehaviour fabBehavior;

    public static FeedbackMenuItem1Fragment newInstance(Bundle args) {
        FeedbackMenuItem1Fragment fr = new FeedbackMenuItem1Fragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new FeedbackMenuItem1Presenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        // Multiple selection
        SelectionBehavior<FeedbackDSSchemaItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<FeedbackDSSchemaItem>() {
            @Override
            public void onSelected(List<FeedbackDSSchemaItem> selectedItems) {
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
        return R.layout.feedbackmenuitem1_item;
    }

    @Override
    protected Datasource<FeedbackDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("feedback"),
              URI.create("https://ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix:361365b455c2c1ae9127cb954e367e339c9dbfbc71d14869f4840bc9f42d32c6@ef7e517f-734a-4ecd-88bd-72ea16700b09-bluemix.cloudant.com/feedback/"),
              FeedbackDSSchemaItem.class,
              getSearchOptions(),
              null
      );
      return datasource;
    }

    @Override
    protected void bindView(FeedbackDSSchemaItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.name != null){
            title.setText(item.name);
            
        }
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        if (item.comment != null){
            subtitle.setText(item.comment);
            
        }
    }

    @Override
    protected void itemClicked(final FeedbackDSSchemaItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(FeedbackDSSchemaItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), FeedbackMenuItem1DetailActivity.class);
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
                        FeedbackDSSchemaItemFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(FeedbackDSSchemaItem item, int position) {
    startActivityForResult(
                generateIntentToAddOrUpdateItem(item,
                        position,
                        getActivity(),
                        FeedbackDSSchemaItemFormActivity.class
                ), Constants.MODE_EDIT
        );
    }
}

