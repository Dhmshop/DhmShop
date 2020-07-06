package dhm.com.dhmshop.framework.module.home.model;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultListener;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultSub;
import dhm.com.dhmshop.framework.utils.ToastUtil;
import dhm.com.dhmshop.framework.module.home.entity.TabCategoryEntity;
import dhm.com.dhmshop.framework.module.home.view.HomeView;

public class HomeModel extends BaseModel<HomeView> {
    /**
     * 获取首页tab栏
     */
    public void getTabCategory(){
        requestData(observable().getTabCategory(),new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<TabCategoryEntity>>() {
            @Override
            public void onSuccess(List<TabCategoryEntity> result) {
                mView.getCategorySuccess(result);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }
}
