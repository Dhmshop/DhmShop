package dhm.com.dhmshop.framework.module.type.model;

import android.annotation.SuppressLint;
import android.view.View;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseFragment;
import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.module.home.entity.TabCategoryEntity;
import dhm.com.dhmshop.framework.module.type.entity.TypeCategoryChildEntity;
import dhm.com.dhmshop.framework.module.type.view.TypeCategoryChildView;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultListener;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultSub;
import dhm.com.dhmshop.framework.utils.ToastUtil;


public class TypeCategoryChildModel extends BaseModel<TypeCategoryChildView> {
    /**
     * 取父类下面的所有子类
     */
    public void getTypeCategoryChild(int pid){
        requestData(observable().getTypeCategoryChild(pid),new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<TypeCategoryChildEntity>>() {
            @Override
            public void onSuccess(List<TypeCategoryChildEntity> result) {
                mView.getTypeChild(result);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }


}
