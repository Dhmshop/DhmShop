package dhm.com.dhmshop.framework.module.home.view;

import dhm.com.dhmshop.framework.base.BaseView;
import dhm.com.dhmshop.framework.module.home.entity.CategoryTypeGoodsEntity;

public interface HomeCategoryTypeView extends BaseView {
    void onCategoryGoodsListSuccess(CategoryTypeGoodsEntity categoryTypeGoodsEntity);
}
