package dhm.com.dhmshop.framework.module.home.view;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseView;
import dhm.com.dhmshop.framework.module.home.entity.ListHotGoodsEntity;

public interface ListHotGoodsView extends BaseView {
    void getListHotGoods(List<ListHotGoodsEntity> result);
}
