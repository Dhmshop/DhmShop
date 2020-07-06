package dhm.com.dhmshop.framework.module.home.view;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseView;
import dhm.com.dhmshop.framework.module.home.entity.RecommendShopListEntity;

public interface RecommendShopListView extends BaseView {
    void getReCommendShopList(List<RecommendShopListEntity> list);
}
