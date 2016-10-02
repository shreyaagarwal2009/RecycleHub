
package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.recyclehub20160929170255.presenters.FeedbackMenuItem1DetailPresenter;
import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.FeedbackDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class FeedbackMenuItem1DetailFragment extends ibmmobileappbuilder.ui.DetailFragment<FeedbackDSSchemaItem>  {

    private CrudDatasource<FeedbackDSSchemaItem> datasource;
    public static FeedbackMenuItem1DetailFragment newInstance(Bundle args){
        FeedbackMenuItem1DetailFragment fr = new FeedbackMenuItem1DetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public FeedbackMenuItem1DetailFragment(){
        super();
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

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new FeedbackMenuItem1DetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<FeedbackDSSchemaItem>) getPresenter()).editForm(getItem());
            }
        }));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.feedbackmenuitem1detail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final FeedbackDSSchemaItem item, View view) {
    }

    @Override
    protected void onShow(FeedbackDSSchemaItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), FeedbackDSSchemaItemFormActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, Constants.MODE_EDIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu, menu);

        MenuItem item = menu.findItem(R.id.action_delete);
        ColorUtils.tintIcon(item, R.color.textBarColor, getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            ((DetailCrudPresenter<FeedbackDSSchemaItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

