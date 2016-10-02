

package com.ibm.mobileappbuilder.recyclehub20160929170255.ds;
import com.ibm.mobileappbuilder.recyclehub20160929170255.R;
import java.util.ArrayList;
import java.util.List;
import ibmmobileappbuilder.util.StringUtils;

// Item static data
public class EmptyDatasourceItems{

    public static List<Item> ITEMS = new ArrayList<Item>();
    public static void addItem(Item item) {
        ITEMS.add(item);
    }
}


