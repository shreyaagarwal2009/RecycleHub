
package com.ibm.mobileappbuilder.recyclehub20160929170255.ds;

import ibmmobileappbuilder.mvp.model.MutableIdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class FeedbackDSSchemaItem implements Parcelable, MutableIdentifiableBean {

    private transient String cloudantIdentifiableId;

    @SerializedName("Name") public String name;
    @SerializedName("Email") public String email;
    @SerializedName("Comment") public String comment;

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
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(comment);
    }

    public static final Creator<FeedbackDSSchemaItem> CREATOR = new Creator<FeedbackDSSchemaItem>() {
        @Override
        public FeedbackDSSchemaItem createFromParcel(Parcel in) {
            FeedbackDSSchemaItem item = new FeedbackDSSchemaItem();
            item.cloudantIdentifiableId = in.readString();

            item.name = in.readString();
            item.email = in.readString();
            item.comment = in.readString();
            return item;
        }

        @Override
        public FeedbackDSSchemaItem[] newArray(int size) {
            return new FeedbackDSSchemaItem[size];
        }
    };
}


