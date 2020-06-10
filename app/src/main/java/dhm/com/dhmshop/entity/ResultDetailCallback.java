package dhm.com.dhmshop.entity;

import com.zhy.http.okhttp.callback.Callback;

import dhm.com.dhmshop.utils.JsonUtil;
import okhttp3.Response;

/**
 * Created by admin
 * 2019/4/24
 */
public abstract class ResultDetailCallback extends Callback<Result> {

    @Override
    public Result parseNetworkResponse(Response response, int id) throws Exception {
        return JsonUtil.jsonToBean(response.body().string(),Result.class);
    }
}
