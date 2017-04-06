package xi.lsl.code.lib.utils.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 反射解析linktreemap
 * Created by lishoulin on 2017/4/6.
 */

public class Convert2Bean {
    private Gson mGson;

    public Convert2Bean() {
        mGson = new Gson();
    }

    public ArrayList convert(Class cls, ArrayList treeMap) {
        ArrayList result;
        JsonArray jsonArray = mGson.toJsonTree(treeMap).getAsJsonArray();
        result = mGson.fromJson(jsonArray.toString(), new TypeToken<List<Object>>() {
        }.getType());
        return result;
    }
}
