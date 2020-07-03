package dhm.com.dhmshop.fragment.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.MyShopsAdapter;
import dhm.com.dhmshop.base.BaseFragment;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.MyShops;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.view.mine.myshop.MyshopActivity;
import dhm.com.dhmshop.view.mine.myshop.ShopdetailActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingcartFragment extends BaseFragment implements LoginContract.IView {


    @BindView(R.id.chenge)
    TextView chenge;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.allcheck)
    CheckBox allcheck;
    @BindView(R.id.heji)
    TextView heji;
    Unbinder unbinder;
    private View rootView;
    private MyShopsAdapter myShopsAdapter;
    private PressenterImpl pressenter;
    private String uid;
    private MyShops myShops;

    @Override
    protected int getLayout() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    protected void initView() {
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(getActivity(), "uid");
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        myShopsAdapter = new MyShopsAdapter(getActivity());
        myShopsAdapter.setdeleteClick(new MyShopsAdapter.OnClick() {
            @Override
            public void item(int posttion,boolean checked) {
                myShops.getData().get(posttion).setChecked(checked);
                myShopsAdapter.notifyDataSetChanged();

                allcheck.setChecked(checkchecked());
                allprice();
            }
        });
        myShopsAdapter.setaddClick(new MyShopsAdapter.OnIntentClick() {
            @Override
            public void item(int posttion, int num) {
                myShops.getData().get(posttion).setNumber(num);
                myShopsAdapter.notifyDataSetChanged();
            }
        });

        myShopsAdapter.setaddClick(new MyShopsAdapter.OnShopClick() {
            @Override
            public void item(int posttion) {
                Intent intent=new Intent(getActivity(), ShopdetailActivity.class);
                intent.putExtra("pid",myShops.getData().get(posttion).getShop_id()+"");
                intent.putExtra("type","3");
                getActivity().startActivity(intent);
            }
        });

        allcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isPressed()) {
                    for (int i=0;i<myShops.getData().size();i++){
                        myShops.getData().get(i).setChecked(allcheck.isChecked());
                    }
                    myShopsAdapter.notifyDataSetChanged();
                    allprice();
                }
            }
        });



        recy.setLayoutManager(manager);
        recy.setAdapter(myShopsAdapter);
        Map<String,String> map=new HashMap<>();
        map.put("token", Constant.TOKEN);
        map.put("uid",uid);
        pressenter.sendMessage(getActivity(),Constant.ShoppingList,map, MyShops.class);

    }

    private boolean checkchecked() {
        for (int i=0;i<myShops.getData().size();i++){
            if (!myShops.getData().get(i).isChecked()){
                return false;
            }
        }
        return true;
    }

    private void allprice() {
        float price=0;
        for (int i=0;i<myShops.getData().size();i++){
            if (myShops.getData().get(i).isChecked()){
                price=price+myShops.getData().get(i).getPrice();
            }
        }
        heji.setText("合计：￥"+price);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.chenge, R.id.allcheck, R.id.btn_jie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chenge:
                //选择时间
                TimePickerView pickerView = new TimePickerBuilder(getActivity(),
                        new OnTimeSelectListener() {
                            @Override
                            public void onTimeSelect(Date date, View v) {
                                Toast.makeText(getActivity(), getTime(date), Toast.LENGTH_SHORT).show();
                            }
                        }).setType(new boolean[]{true, true, true, false, false, false})
                        .build();
                pickerView.show();
                break;
            case R.id.allcheck:
                break;
            case R.id.btn_jie:
                break;
                default:
        }
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(date);
    }


    @Override
    public void requesta(Object data) {
        if (data instanceof MyShops){
            myShops = (MyShops) data;
            if (myShops.getCode()==1){
                myShopsAdapter.setShopList(myShops.getData());
            }
        }
    }

    @Override
    public void fail(String error) {

    }
}
