package dhm.com.dhmshop.framework.module.commodity.model;

import android.util.Log;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseModel;

import dhm.com.dhmshop.framework.module.commodity.entity.OneGoodsEntity;
import dhm.com.dhmshop.framework.module.commodity.view.CommodityDetailsView;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultListener;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultSub;

public class CommodityDetailsModel extends BaseModel<CommodityDetailsView> {
    public void getCommodityDetail(int gid, int uid) {
        requestData(observable().getOneGoods(gid, uid), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<OneGoodsEntity>() {

            @Override
            public void onSuccess(OneGoodsEntity result) {
                mView.getCommodityDetails(result);
            }

            @Override
            public void onFault(String errorMsg) {
                Log.d("TAG", "onFault: "+errorMsg);
            }
        }));
    }
}
