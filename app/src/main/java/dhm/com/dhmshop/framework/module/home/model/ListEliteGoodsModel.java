package dhm.com.dhmshop.framework.module.home.model;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.module.home.entity.CategoryTypeGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.ListEliteGoodsEntity;
import dhm.com.dhmshop.framework.module.home.view.ListEliteGoodsView;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultListener;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultSub;
import dhm.com.dhmshop.framework.utils.ToastUtil;

public class ListEliteGoodsModel extends BaseModel<ListEliteGoodsView> {
    /**
     * 有好货商品列表
     */
    public void getListEliteGoods(int page) {
        requestData(
                observable().getListEliteGoods(page), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<ListEliteGoodsEntity>>() {

                    @Override
                    public void onSuccess(List<ListEliteGoodsEntity> result) {
                        mView.getListEliteGoods(result);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
    }
}
