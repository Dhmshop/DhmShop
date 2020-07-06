package dhm.com.dhmshop.framework.module.home.model;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.module.home.entity.RecommendShopListEntity;
import dhm.com.dhmshop.framework.module.home.view.RecommendShopListView;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultListener;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultSub;

public class RecommendShopListModel extends BaseModel<RecommendShopListView> {

    //获取好店列表
    public void getRecommendShopList() {
        requestData(observable().getRecommendShopList(), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<RecommendShopListEntity>>() {
            @Override
            public void onSuccess(List<RecommendShopListEntity> result) {
                mView.getReCommendShopList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
