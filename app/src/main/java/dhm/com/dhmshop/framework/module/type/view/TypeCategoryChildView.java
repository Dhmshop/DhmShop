package dhm.com.dhmshop.framework.module.type.view;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.base.BaseView;
import dhm.com.dhmshop.framework.module.type.entity.TypeCategoryChildEntity;


public interface TypeCategoryChildView extends BaseView {


    void getTypeChild(List<TypeCategoryChildEntity> result);
}
