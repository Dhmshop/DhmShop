package dhm.com.dhmshop.fragment.wode.shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.mine.ShopAdapter;
import dhm.com.dhmshop.base.BaseFragment;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.GetMyShopinfo;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.view.mine.myshop.ShowshopActivity;

@SuppressLint("ValidFragment")
public class ShopFregment extends BaseFragment implements LoginContract.IView {
    Unbinder unbinder;
    @BindView(R.id.recy)
    XRecyclerView recy;
    private View rootView;

    String type;
    private PressenterImpl pressenter;
    private String uid;
    private ShopAdapter shopAdapter;
    private GetMyShopinfo getMyShopinfo;


    public ShopFregment(String type) {
//        1、推荐  2、全部宝贝   3、新品
        this.type = type;
    }


    @Override
    protected int getLayout() {
        return R.layout.shop_fragment;
    }

    @Override
    protected void initView() {
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(getActivity(), "uid");

    }
    @Override
    protected void initData() {

        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        shopAdapter = new ShopAdapter(getActivity());
        shopAdapter.setdeleteClick(new ShopAdapter.OnClick() {
            @Override
            public void item(int posttion) {
                getMyShopinfo.getData().remove(posttion);
                shopAdapter.notifyDataSetChanged();
            }
        });
        shopAdapter.seteditClick(new ShopAdapter.OneditClick() {
            @Override
            public void item(int posttion) {
                Intent intent=new Intent(getActivity(), ShowshopActivity.class);
                intent.putExtra("type","edit");
                intent.putExtra("shop_id",getMyShopinfo.getData().get(posttion).getShop_id()+"");

            }
        });


        recy.setLayoutManager(manager);
        recy.setAdapter(shopAdapter);


        Map<String,String> map=new HashMap<>();
        map.put("token", Constant.TOKEN);
        map.put("uid",uid);
        map.put("page","1");
        pressenter.sendMessage(getActivity(),Constant.GetMyGoods,map, GetMyShopinfo.class);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.shop_fragment, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof GetMyShopinfo){
            getMyShopinfo = (GetMyShopinfo) data;
            if (getMyShopinfo.getCode()==1){
                shopAdapter.setShopList(getMyShopinfo.getData());
            }


        }

    }

    @Override
    public void fail(String error) {

    }

    private boolean isdel;
    private boolean isedit;
    public void setdelcheck(boolean isdel) {
        this.isdel=isdel;
        shopAdapter.setIsdel(this.isdel);
    }
    public void seteditcheck(boolean isdel) {
        isedit=isdel;
        shopAdapter.setIsedit(isedit);
    }

    public boolean isIsdel() {
        return isdel;
    }

    public boolean isIsedit() {
        return isedit;
    }
}
