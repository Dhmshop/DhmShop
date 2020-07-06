package dhm.com.dhmshop.module.home.model;

import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultListener;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultSub;
import dhm.com.dhmshop.framework.utils.ToastUtil;
import dhm.com.dhmshop.module.home.entity.CategoryTypeGoodsEntity;
import dhm.com.dhmshop.module.home.view.HomeCategoryTypeView;

public class HomeCategoryTypeModel extends BaseModel<HomeCategoryTypeView> {
    /**
     * 获取父类下面的所有子类和销量前六的产品
     */
    public void getCategoryTypeGoods(int pid) {
        requestData(observable().getCategoryTypeGoods(pid), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<CategoryTypeGoodsEntity>() {
            @Override
            public void onSuccess(CategoryTypeGoodsEntity categoryTypeGoodsEntity) {
                mView.onCategoryGoodsListSuccess(categoryTypeGoodsEntity);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }
}
