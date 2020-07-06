package dhm.com.dhmshop.framework.module.home.model;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.module.home.entity.ListHotGoodsEntity;
import dhm.com.dhmshop.framework.module.home.view.ListHotGoodsView;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultListener;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultSub;

public class ListHotGoodsModel extends BaseModel<ListHotGoodsView> {
    //热门商品列表
    public void getListHotGoods(int page) {
        requestData(observable().getListHotGoods(page), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<ListHotGoodsEntity>>() {
            @Override
            public void onSuccess(List<ListHotGoodsEntity> result) {
                mView.getListHotGoods(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
