package dhm.com.dhmshop.framework.module.home.view;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseView;
import dhm.com.dhmshop.framework.module.home.entity.ListEliteGoodsEntity;

public interface ListEliteGoodsView extends BaseView {
    void getListEliteGoods(List<ListEliteGoodsEntity> result);
}
