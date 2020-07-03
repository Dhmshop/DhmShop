package dhm.com.dhmshop.fragment.wode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import dhm.com.dhmshop.adapter.mine.MyOrderAdapter;
import dhm.com.dhmshop.view.mine.order.ShowevaluateActivity;
import dhm.com.dhmshop.base.BaseFragment;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.Bean;
import dhm.com.dhmshop.entity.Myorder;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.view.mine.AddaddrActivity;

@SuppressLint("ValidFragment")
public class MyorderFragment extends BaseFragment implements LoginContract.IView {

    @BindView(R.id.recy)
    XRecyclerView recy;
    Unbinder unbinder;
    private String type;
    private String uid;
    private PressenterImpl pressenter;
    private MyOrderAdapter myOrderAdapter;
    private Myorder myorder;


    public MyorderFragment(String type) {
//        type 0:全部 1:代付款 2.待发货 3.待收货   4.待评价
        this.type = type;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_myorder;
    }

    @Override
    protected void initView() {
        uid = SpUtils.getString(getActivity(), "uid");
        pressenter = new PressenterImpl();
        pressenter.attachView(this);
        myOrderAdapter = new MyOrderAdapter(getActivity(),type);
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", "1");
        switch (type) {
            case "0":
//                全部
                break;
            case "1":
//                代付款
                myOrderAdapter.setdeleteClick(new MyOrderAdapter.OnClick() {
                    @Override
                    public void item(int posttion) {
                        Intent intent=new Intent(getActivity(), AddaddrActivity.class);
                        intent.putExtra("aid","xiu");
                    }
                });
                map.put("status", "0");
                break;
            case "2":
//                待发货
                myOrderAdapter.setdeleteClick(new MyOrderAdapter.OnClick() {
                    @Override
                    public void item(int posttion) {
                        Map<String,String> map1=new HashMap<>();
                        map1.put("token",Constant.TOKEN);
                        map1.put("token",Constant.TOKEN);
                        pressenter.sendMessage(getActivity(),Constant.AddSendOrder,map, Bean.class);
                    }
                });

                map.put("status", "1");
                break;
            case "3":
//                待收货
                myOrderAdapter.setdeleteClick(new MyOrderAdapter.OnClick() {
                    @Override
                    public void item(int posttion) {
//                        查看物流

                    }
                });
                myOrderAdapter.setaddClick(new MyOrderAdapter.OnIntentClick() {
                    @Override
                    public void item(int posttion) {
//                        确认收货
                        Map<String,String> map1=new HashMap<>();
                        map1.put("token",Constant.TOKEN);
                        map1.put("shop_id",myorder.getData().get(posttion).getShop_id()+"");
                        map1.put("order_id",myorder.getData().get(posttion).getOrder_id()+"");
                        pressenter.sendMessage(getActivity(),Constant.OrderOver,map,Bean.class);
                    }
                });

                map.put("status", "2");
                break;
            case "4":
//                待评价
                myOrderAdapter.setdeleteClick(new MyOrderAdapter.OnClick() {
                    @Override
                    public void item(int posttion) {
//                        查看物流
                    }
                });
                myOrderAdapter.setaddClick(new MyOrderAdapter.OnShopClick() {
                    @Override
                    public void item(int posttion) {
//                        评价
                        Intent intent=new Intent(getActivity(), ShowevaluateActivity.class);
                        intent.putExtra("order_sn",myorder.getData().get(posttion).getOrder_sn());
                        intent.putExtra("goods_id",myorder.getData().get(posttion).getGoods_id()+"");
                        getActivity().startActivity(intent);
                    }
                });
                map.put("is_evaluate", "0");
                break;
            default:
        }
        recy.setAdapter(myOrderAdapter);
        recy.setLayoutManager(manager);
        pressenter.sendMessage(getActivity(), Constant.Allorders, map, Myorder.class);
    }



    @Override
    public void requesta(Object data) {
        if (data instanceof Myorder) {
            myorder = (Myorder) data;
            if (myorder.getCode() == 1) {
                myOrderAdapter.setShopList(myorder.getData());
            }
        }else if (data instanceof Bean){
            Bean bean= (Bean) data;
            if (bean.getCode()==1){
                Toast.makeText(getActivity(), bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void fail(String error) {

    }
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_myorder, container, false);
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
}
