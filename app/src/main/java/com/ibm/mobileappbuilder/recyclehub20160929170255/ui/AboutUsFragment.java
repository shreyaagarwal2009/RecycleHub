
package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import ibmmobileappbuilder.ds.Datasource;
import android.widget.TextView;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.Item;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.EmptyDatasource;

public class AboutUsFragment extends ibmmobileappbuilder.ui.DetailFragment<Item>  {

    private Datasource<Item> datasource;
    private SearchOptions searchOptions;

    public static AboutUsFragment newInstance(Bundle args){
        AboutUsFragment card = new AboutUsFragment();
        card.setArguments(args);

        return card;
    }

    public AboutUsFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            searchOptions = SearchOptions.Builder.searchOptions().build();
    }

    @Override
    public Datasource getDatasource() {
      if (datasource != null) {
          return datasource;
      }
          datasource = EmptyDatasource.getInstance(searchOptions);
          return datasource;
    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.aboutus_custom;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final Item item, View view) {
        
        TextView view0 = (TextView) view.findViewById(R.id.view0);
        view0.setText("This app is a way for us to GO GREEN by reusing and recycling books we have since ever tried to loose ties of. ");
        
        
        TextView view1 = (TextView) view.findViewById(R.id.view1);
        view1.setText("So now is the time to help others and help yourself by giving up your stuff at a minimal amount and even receiving them at the cheapest.");
        
        
        TextView view2 = (TextView) view.findViewById(R.id.view2);
        view2.setText("RecycleHub");
        
    }

    @Override
    protected void onShow(Item item) {
        // set the title for this fragment
        getActivity().setTitle("About Us");
    }

}

