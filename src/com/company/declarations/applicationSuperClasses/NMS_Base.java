package com.company.declarations.applicationSuperClasses;

import com.company.utilities.REST_Call;
import com.company.utilities.REST_METHOD;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMarchenko on 2017-06-14.
 */
public class NMS_Base {
    public final REST_Call rest_call = new REST_Call();
    public <T>List<T> getAll(Class<T> resultClass, String uriIn){
        int skip=0;
        int take=100;
        int size;
        List<T>respond = new ArrayList<>();
        do {
            List<T>current;
            String uri = uriIn + "skip=" + skip + "&take=" + take;
            current = rest_call.getDeserializedListOfClass(resultClass, uri, REST_METHOD.GET, null);
            respond.addAll(current);
            size = current.size();
            skip +=take;
        }
        while (size==0 || size==take);
        return respond;
    }
    public <T> String getUID_Base (Class<T> resultClass, String uriIn, String itemName, String keyFieldName, String valueFieldName) throws IllegalAccessException {
        String sOut = null;
        List<T> respond = getAll(resultClass, uriIn);
        Object name=null, uid=null;
        boolean returnFirst = itemName ==null;

        for (T item:respond) {
            Field[] fields = item.getClass().getFields();
            for (Field field1:fields){
                if (field1.getName().equals(keyFieldName)){
                    name = field1.get(item);
                    continue;
                }
                if (field1.getName().equals(valueFieldName)){
                    uid = field1.get(item);
                    continue;
                }
            }
            if (name.toString().equals(itemName) || returnFirst) {
                sOut = uid.toString();
                break;
            }
        }
        return sOut;
    }

}
