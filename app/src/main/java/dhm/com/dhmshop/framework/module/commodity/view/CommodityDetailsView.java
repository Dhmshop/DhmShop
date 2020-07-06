package dhm.com.dhmshop.framework.module.commodity.view;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseView;
import dhm.com.dhmshop.framework.module.commodity.entity.OneGoodsEntity;

public interface CommodityDetailsView extends BaseView {
    void getCommodityDetails(OneGoodsEntity result);
}
