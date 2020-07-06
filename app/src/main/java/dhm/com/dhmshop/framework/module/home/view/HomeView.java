package dhm.com.dhmshop.module.home.view;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseView;
import dhm.com.dhmshop.module.home.entity.TabCategoryEntity;

public interface HomeView extends BaseView {
    void getCategorySuccess(List<TabCategoryEntity> result);
}
