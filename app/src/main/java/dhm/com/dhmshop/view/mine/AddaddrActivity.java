package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

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
import dhm.com.dhmshop.entity.Bean;
import dhm.com.dhmshop.entity.GetAddr;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.utils.StringUtils;

public class AddaddrActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.myaddress)
    TextView myaddress;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.del)
    TextView del;
    @BindView(R.id.addrs)
    EditText addrs;
    @BindView(R.id.quan)
    ImageView quan;
    @BindView(R.id.def_no)
    ImageView defNo;
    @BindView(R.id.def_yes)
    ImageView defYes;

    private boolean isDef=false;

    private PressenterImpl pressenter;
    private String uid;
    private String province="北京市";
    private String city="北京市";
    private String district="昌平区";
    private String aid;


    @Override
    protected int getLayout() {
        return R.layout.activity_addaddr;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        Intent intent = getIntent();
        getWindow().setStatusBarColor(Color.LTGRAY);
        aid = intent.getStringExtra("aid");
        uid = SpUtils.getString(this, "uid");

        if (aid.equals("no")) {
            del.setVisibility(View.GONE);
            title.setText("添加收货地址");
        }else if (aid.equals("xiu")){

        }  else {
            title.setText("编辑收货地址");
            del.setVisibility(View.VISIBLE);
            Map<String,String> map=new HashMap<>();
            map.put("token",Constant.TOKEN);
            map.put("uid",uid);
            map.put("aid", aid);
            pressenter.sendMessage(this,Constant.GetAddress,map, GetAddr.class);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    public void requesta(Object data) {
        if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (((Bean) data).getCode()==1){
                finish();
            }
        }else if (data instanceof GetAddr){
            GetAddr getAddr= (GetAddr) data;
            name.setText(getAddr.getData().getUser_name());
            phone.setText(getAddr.getData().getMobile());
            myaddress.setText(getAddr.getData().getProvince()+getAddr.getData().getCity()+getAddr.getData().getArea());
            addrs.setText(getAddr.getData().getAddress());
            province=getAddr.getData().getProvince();
            city=getAddr.getData().getCity();
            district=getAddr.getData().getArea();
            if (getAddr.getData().getTag().equals("默认")){
                isDef=true;
                quan.setBackgroundResource(R.drawable.back_quans);
                defYes.setVisibility(View.VISIBLE);
                defNo.setVisibility(View.GONE);
            }else {
                isDef=false;
                quan.setBackgroundResource(R.drawable.back_quan);
                defNo.setVisibility(View.VISIBLE);
                defYes.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void fail(String error) {

    }


    @OnClick({R.id.back, R.id.about, R.id.quan,  R.id.del, R.id.sure})
    public void onViewClicked(View view) {
        Map<String,String> map;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.about:
                selectAddress();
                break;
            case R.id.del:
                map=new HashMap<>();
                map.put("token",Constant.TOKEN);
                map.put("aid",aid);
                map.put("uid",uid);
                pressenter.sendMessage(AddaddrActivity.this,Constant.DelAddress,map,Bean.class);
                break;
            case R.id.quan:
                if (isDef){
                    quan.setBackgroundResource(R.drawable.back_quan);
                    defNo.setVisibility(View.VISIBLE);
                    defYes.setVisibility(View.GONE);
                }else {
                    quan.setBackgroundResource(R.drawable.back_quans);
                    defYes.setVisibility(View.VISIBLE);
                    defNo.setVisibility(View.GONE);
                }
                isDef=!isDef;
                break;
            case R.id.sure:
                String names = name.getText().toString();
                String phones = phone.getText().toString();
                String addrss = addrs.getText().toString();
                String myaddresss = myaddress.getText().toString();
                if (names.isEmpty()||names.equals("")||phones.isEmpty()||phones.equals("")||addrss.isEmpty()||addrss.equals("")||myaddresss.isEmpty()||myaddresss.equals("")){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isRegistMobileNO(phones)){
                    Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }

                map=new HashMap<>();
                map.put("token", Constant.TOKEN);
                map.put("uid",uid);
                map.put("user_name",names);
                map.put("add_mobile",phones);
                map.put("province",province.trim());
                map.put("city",city.trim());
                map.put("area",district.trim());
                map.put("address",addrss);
                if (isDef){
                    map.put("tag","默认");
                }else {
                    map.put("tag","");
                }

                if (!aid.equals("no")) {
                    map.put("aid",aid);
                    pressenter.sendMessage(AddaddrActivity.this,Constant.EditAddress,map, Bean.class);

                }else {
                    pressenter.sendMessage(AddaddrActivity.this,Constant.AddAddress,map, Bean.class);

                }





                break;
            default:
        }
    }

    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(AddaddrActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
//                .titleTextColor("#696969")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province(province)
                .city(city)
                .district(district)
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                province = citySelected[0];
                //城市
                city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                myaddress.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }


}
