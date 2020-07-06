package dhm.com.dhmshop.framework.module.home.fragment;


import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;


import dhm.com.dhmshop.R;
import dhm.com.dhmshop.framework.base.BaseFragment;
import dhm.com.dhmshop.framework.base.MyBaseViewHolder;
import dhm.com.dhmshop.framework.utils.GlideUtil;
import dhm.com.dhmshop.framework.utils.StringUtil;
import dhm.com.dhmshop.framework.module.home.entity.CategoryTypeGoodsEntity;
import dhm.com.dhmshop.framework.module.home.model.HomeCategoryTypeModel;
import dhm.com.dhmshop.framework.module.home.view.HomeCategoryTypeView;

public class HomeTabTypeFragment extends BaseFragment<HomeCategoryTypeModel> implements HomeCategoryTypeView {

    private int pid;
    private TopTypeAdapter topTypeAdapter;
    private GoodsListAdapter goodsListAdapter;

    public HomeTabTypeFragment() {
    }

    @SuppressLint("ValidFragment")
    public HomeTabTypeFragment(int id) {
        pid = id;
    }


    @Override
    protected void initView(View inflate) {
        RecyclerView topTypeRecycle = findViewById(R.id.topTypeRecycle);
        RecyclerView goodsListRecycle = findViewById(R.id.goodsListRecycle);
        topTypeAdapter = new TopTypeAdapter();
        topTypeRecycle.setAdapter(topTypeAdapter);
        goodsListAdapter = new GoodsListAdapter();
        goodsListRecycle.setAdapter(goodsListAdapter);
    }

    @Override
    protected void initData() {
        model.getCategoryTypeGoods(pid);
    }

    @Override
    protected void initListener() {
        //设置头部分类条目点击监听
        topTypeAdapter.setOnItemClickListener((adapter, view, position) -> {

        });
        //设置商品列表点击监听
        goodsListAdapter.setOnItemClickListener((adapter, view, position) -> {

        });
    }

    @Override
    protected HomeCategoryTypeModel initModel() {
        return new HomeCategoryTypeModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_child;
    }

    @Override
    public void onCategoryGoodsListSuccess(CategoryTypeGoodsEntity categoryTypeGoodsEntity) {
        if (categoryTypeGoodsEntity.getCatelist() != null && categoryTypeGoodsEntity.getCatelist().size() > 0){
            topTypeAdapter.addData(categoryTypeGoodsEntity.getCatelist());
        }
        if (categoryTypeGoodsEntity.getGoods() != null && categoryTypeGoodsEntity.getGoods().size() > 0){
            goodsListAdapter.addData(categoryTypeGoodsEntity.getGoods());
        }
    }

    /**
     * 顶部分类列表 适配器
     */
    class TopTypeAdapter extends BaseQuickAdapter<CategoryTypeGoodsEntity.CatelistBean, MyBaseViewHolder> {

        public TopTypeAdapter() {
            super(R.layout.getchildgorygoodsoneitem);
        }

        @Override
        protected void convert(MyBaseViewHolder helper, CategoryTypeGoodsEntity.CatelistBean item) {
            helper.setText(R.id.name, item.getName());
            GlideUtil.loadImage(context, StringUtil.preventNull(item.getThumb()), helper.getView(R.id.thumb));
        }
    }

    /**
     * 商品列表 适配器
     */

    class GoodsListAdapter extends BaseQuickAdapter<CategoryTypeGoodsEntity.GoodsBean,MyBaseViewHolder>{

        public GoodsListAdapter() {
            super(R.layout.hotgoodslistitem);
        }

        @Override
        protected void convert(MyBaseViewHolder helper, CategoryTypeGoodsEntity.GoodsBean item) {
            helper.setText(R.id.goods_name,StringUtil.preventNull(item.getGoods_name()))
                    .setText(R.id.price,StringUtil.preventNull(item.getPrice()))
                    .setText(R.id.sale_num,StringUtil.buildString(item.getSale_num(),"人付款"));
            GlideUtil.loadImage(context,item.getGoods_images(),helper.getView(R.id.goods_images));
        }
    }
}
