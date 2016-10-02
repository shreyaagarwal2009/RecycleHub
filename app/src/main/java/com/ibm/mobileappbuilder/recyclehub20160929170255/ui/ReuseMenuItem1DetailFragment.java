
package com.ibm.mobileappbuilder.recyclehub20160929170255.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MailAction;
import ibmmobileappbuilder.actions.MapsAction;
import ibmmobileappbuilder.actions.PhoneAction;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.recyclehub20160929170255.ds.GoodsDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class ReuseMenuItem1DetailFragment extends ibmmobileappbuilder.ui.DetailFragment<GoodsDSSchemaItem> implements ShareBehavior.ShareListener  {

    private Datasource<GoodsDSSchemaItem> datasource;
    public static ReuseMenuItem1DetailFragment newInstance(Bundle args){
        ReuseMenuItem1DetailFragment fr = new ReuseMenuItem1DetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public ReuseMenuItem1DetailFragment(){
        super();
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

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.reusemenuitem1detail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final GoodsDSSchemaItem item, View view) {
        
        ImageView view0 = (ImageView) view.findViewById(R.id.view0);
        URL view0Media = StringUtils.parseUrl(item.productImage);
        if(view0Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view0.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view0Media.toExternalForm())
                                   .withTargetView(view0)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view0.setImageDrawable(null);
        }
        if (item.productName != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.productName);
            
        }
        if (item.salePrice != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText("Rs." + item.salePrice.toString());
            
        }
        if (item.productLocation != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.productLocation.toString());
            bindAction(view3, new MapsAction(
            new ActivityIntentLauncher()
            , "http://maps.google.com/maps?q=" + item.productLocation.toString()));
        }
        if (item.contact != null){
            
            TextView view4 = (TextView) view.findViewById(R.id.view4);
            view4.setText(item.contact.toString());
            bindAction(view4, new PhoneAction(
            new ActivityIntentLauncher()
            , item.contact.toString()));
        }
        
        TextView view5 = (TextView) view.findViewById(R.id.view5);
        view5.setText("PLACE ORDER");
        bindAction(view5, new MailAction(
        new ActivityIntentLauncher()
        , "shreyaagarwal2009@gmail.com"));
    }

    @Override
    protected void onShow(GoodsDSSchemaItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }
    @Override
    public void onShare() {
        GoodsDSSchemaItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.productName != null ? item.productName : "" ) + "\n" +
                    (item.salePrice != null ? "Rs." + item.salePrice.toString() : "" ) + "\n" +
                    (item.productLocation != null ? item.productLocation.toString() : "" ) + "\n" +
                    (item.contact != null ? item.contact.toString() : "" ) + "\n" +
                    "PLACE ORDER");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

