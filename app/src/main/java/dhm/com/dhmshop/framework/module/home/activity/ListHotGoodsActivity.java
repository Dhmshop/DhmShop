package dhm.com.dhmshop.framework.module.home.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.framework.base.BaseActivity;
import dhm.com.dhmshop.framework.base.MyBaseViewHolder;
import dhm.com.dhmshop.framework.module.commodity.activity.CommodityDetailsActivity;
import dhm.com.dhmshop.framework.module.home.entity.ListHotGoodsEntity;
import dhm.com.dhmshop.framework.module.home.model.ListHotGoodsModel;
import dhm.com.dhmshop.framework.module.home.view.ListHotGoodsView;
import dhm.com.dhmshop.framework.utils.GlideUtil;
import dhm.com.dhmshop.framework.utils.StringUtil;

public class ListHotGoodsActivity extends BaseActivity<ListHotGoodsModel> implements ListHotGoodsView {

    private int page = 0;
    private ListHotGoodsAdapter listHotGoodsAdapter;
    private List<ListHotGoodsEntity> listHotGoodstitles;

    @Override
    protected void initView() {
        ImageView hotGoodsFan = findViewById(R.id.hotgoods_fan);
        RecyclerView listHotGoodsRecycler = findViewById(R.id.list_hot_goods);
        listHotGoodsAdapter = new ListHotGoodsAdapter();
        listHotGoodstitles = new ArrayList<>();
        listHotGoodsRecycler.setAdapter(listHotGoodsAdapter);

        hotGoodsFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        page++;
        model.getListHotGoods(page);
    }

    @Override
    protected void initListener() {
        listHotGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ListHotGoodsActivity.this, CommodityDetailsActivity.class);

                intent.putExtra("pid", listHotGoodstitles.get(position).getGoods_id());
                startActivity(intent);
            }
        });
    }

    @Override
    protected int intiLayoutId() {
        return R.layout.activity_list_hot_goods;
    }

    @Override
    protected ListHotGoodsModel initModel() {
        return new ListHotGoodsModel();
    }

    @Override
    public void getListHotGoods(List<ListHotGoodsEntity> result) {
        if (result == null || result.size() == 0) {
            return;
        }
        listHotGoodstitles.addAll(result);
        listHotGoodsAdapter.addData(result);
    }

    class ListHotGoodsAdapter extends BaseQuickAdapter<ListHotGoodsEntity, MyBaseViewHolder> {

        public ListHotGoodsAdapter() {
            super(R.layout.listhotgoodsitem);
        }

        @Override
        protected void convert(MyBaseViewHolder helper, ListHotGoodsEntity item) {
            helper.setText(R.id.goods_name, StringUtil.preventNull(item.getGoods_name()))
                    .setText(R.id.price, StringUtil.preventNull(item.getPrice()))
                    .setText(R.id.sale_num, StringUtil.buildString(item.getClick_count(), "人付款"));
            GlideUtil.loadImage(ListHotGoodsActivity.this, item.getGoods_images(), helper.getView(R.id.goods_images));
        }
    }
}
