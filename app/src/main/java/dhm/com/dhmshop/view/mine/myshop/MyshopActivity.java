package dhm.com.dhmshop.view.mine.myshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.GetShopProfit;
import dhm.com.dhmshop.utils.SpUtils;

public class MyshopActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.commandes)
    TextView commandes;
    @BindView(R.id.gain)
    TextView gain;

    private PressenterImpl pressenter;
    private String uid;
    private String shop_id;

    @Override
    protected int getLayout() {
        return R.layout.activity_myshop;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(MyshopActivity.this, "uid");
        shop_id = SpUtils.getString(MyshopActivity.this, "shop_id");
    }

    @Override
    protected void initData() {

        Map<String,String> map=new HashMap<>();
        map.put("token",Constant.TOKEN);
        map.put("uid",uid);
        map.put("shop_id",shop_id);
        pressenter.sendMessage(MyshopActivity.this, Constant.ShopProfit, map,GetShopProfit.class);
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof GetShopProfit){
            GetShopProfit getShopProfit= (GetShopProfit) data;
            if (getShopProfit.getCode()==1){

            }
        }

    }

    @Override
    public void fail(String error) {

    }

    @OnClick({R.id.back, R.id.topay,  R.id.ad,  R.id.cui, R.id.togo,R.id.toping, R.id.setting, R.id.smail_shop, R.id.newWork, R.id.getmoney})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.topay:
//                小店订单
                break;
            case R.id.togo:
//                店铺账单
                break;
            case R.id.ad:
//                广告位
                intent=new Intent(MyshopActivity.this,AddadActivity.class);
                startActivity(intent);
                break;
            case R.id.cui:
//                查看催发货
                break;
            case R.id.toping:
//                小店上货
                intent=new Intent(MyshopActivity.this,ShowshopActivity.class);
                intent.putExtra("type","show");
                intent.putExtra("shop_id",shop_id);
                startActivity(intent);
                break;
            case R.id.setting:
//                店铺设置
                intent=new Intent(MyshopActivity.this,ShopsettingActivity.class);
                startActivity(intent);
                break;
            case R.id.smail_shop:
//                查看微店
                intent=new Intent(MyshopActivity.this,ShopdetailActivity.class);
                intent.putExtra("pid",shop_id);
                intent.putExtra("type","2");
                startActivity(intent);
                break;
            case R.id.newWork:
//                提现
                break;
            case R.id.getmoney:
//                新手必看
                break;
            default:
        }
    }
}
