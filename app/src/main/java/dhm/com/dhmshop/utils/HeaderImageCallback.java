package dhm.com.dhmshop.utils;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import dhm.com.dhmshop.entity.HeaderIMage;
import okhttp3.Response;

/**
 * Created by admin
 * 2019/4/24
 */
public  abstract class HeaderImageCallback extends Callback<HeaderIMage> {

    @Override
    public HeaderIMage parseNetworkResponse(Response response, int id) throws IOException {

        return JsonUtil.jsonToBean(response.body().string(),HeaderIMage.class);
    }
}
