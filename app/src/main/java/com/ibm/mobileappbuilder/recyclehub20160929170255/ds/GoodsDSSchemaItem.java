
package com.ibm.mobileappbuilder.recyclehub20160929170255.ds;
import ibmmobileappbuilder.ds.restds.GeoPoint;

import ibmmobileappbuilder.mvp.model.MutableIdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class GoodsDSSchemaItem implements Parcelable, MutableIdentifiableBean {

    private transient String cloudantIdentifiableId;

    @SerializedName("productImage") public String productImage;
    @SerializedName("productName") public String productName;
    @SerializedName("salePrice") public Long salePrice;
    @SerializedName("productLocation") public GeoPoint productLocation;
    @SerializedName("contact") public Long contact;

    @Override
    public void setIdentifiableId(String id) {
        this.cloudantIdentifiableId = id;
    }

    @Override
    public String getIdentifiableId() {
        return cloudantIdentifiableId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cloudantIdentifiableId);
        dest.writeString(productImage);
        dest.writeString(productName);
        dest.writeValue(salePrice);
        dest.writeDoubleArray(productLocation != null  && productLocation.coordinates.length != 0 ? productLocation.coordinates : null);
        dest.writeValue(contact);
    }

    public static final Creator<GoodsDSSchemaItem> CREATOR = new Creator<GoodsDSSchemaItem>() {
        @Override
        public GoodsDSSchemaItem createFromParcel(Parcel in) {
            GoodsDSSchemaItem item = new GoodsDSSchemaItem();
            item.cloudantIdentifiableId = in.readString();

            item.productImage = in.readString();
            item.productName = in.readString();
            item.salePrice = (Long) in.readValue(null);
            double[] productLocation_coords = in.createDoubleArray();
            if (productLocation_coords != null)
                item.productLocation = new GeoPoint(productLocation_coords);
            item.contact = (Long) in.readValue(null);
            return item;
        }

        @Override
        public GoodsDSSchemaItem[] newArray(int size) {
            return new GoodsDSSchemaItem[size];
        }
    };
}


