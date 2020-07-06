package dhm.com.dhmshop.framework.module.home.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.framework.base.BaseActivity;
import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.base.MyBaseViewHolder;
import dhm.com.dhmshop.framework.module.home.entity.ListEliteGoodsEntity;
import dhm.com.dhmshop.framework.module.home.model.ListEliteGoodsModel;
import dhm.com.dhmshop.framework.module.home.view.ListEliteGoodsView;
import dhm.com.dhmshop.framework.utils.GlideUtil;
import dhm.com.dhmshop.framework.utils.StringUtil;

/*
 *
 * 有好货商品列表
 *
 * */
public class ListEliteGoodsActivity extends BaseActivity<ListEliteGoodsModel> implements ListEliteGoodsView {

    private int page = 0;
    private ListEliteGoodsAdapter listEliteGoodsAdapter;


    @Override
    protected void initView() {
        ImageView eiteGoodsFan = findViewById(R.id.eitegoods_fan);
        RecyclerView listEliteGoodsRecy = findViewById(R.id.list_elite_goods);

        listEliteGoodsAdapter = new ListEliteGoodsAdapter();
        listEliteGoodsRecy.setAdapter(listEliteGoodsAdapter);

        //添加Android自带的分割线
        listEliteGoodsRecy.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        eiteGoodsFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void initData() {
        page++;
        model.getListEliteGoods(page);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int intiLayoutId() {
        return R.layout.activity_list_elite_goods;
    }

    @Override
    protected ListEliteGoodsModel initModel() {
        return new ListEliteGoodsModel();
    }

    @Override
    public void getListEliteGoods(List<ListEliteGoodsEntity> result) {
        if (result == null || result.size() == 0) {
            return;
        }
        listEliteGoodsAdapter.addData(result);
    }

    class ListEliteGoodsAdapter extends BaseQuickAdapter<ListEliteGoodsEntity, MyBaseViewHolder> {

        public ListEliteGoodsAdapter() {
            super(R.layout.listelitegoodsitem);
        }

        @Override
        protected void convert(MyBaseViewHolder helper, ListEliteGoodsEntity item) {
            GlideUtil.loadGrayscaleImage(ListEliteGoodsActivity.this, Constant.PATH+item.getGoods_images(), helper.getView(R.id.listelitegoods_goodsimages), 2);
            helper.setText(R.id.listelitegoods_goodsname, StringUtil.preventNull(item.getGoods_name()));
            helper.setText(R.id.listelitegoods_goodskeywordone, StringUtil.preventNull(item.getExpress_price()));
            helper.setText(R.id.listelitegoods_goodskeywordtwo, StringUtil.preventNull(item.getExpress_price()));
            helper.setText(R.id.listelitegoods_goodskeywordthree, StringUtil.preventNull(item.getExpress_price()));
            helper.setText(R.id.listelitegoods_price, StringUtil.preventNull(item.getPrice()));
            helper.setText(R.id.listelitegoods_salenum, StringUtil.buildString(item.getSale_num(), "人付款"));

        }
    }
}
