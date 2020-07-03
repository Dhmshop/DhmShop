package dhm.com.dhmshop.view.mine.myshop;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.mine.ShowITypeAdapter;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.Bean;
import dhm.com.dhmshop.entity.GetMyshop;
import dhm.com.dhmshop.entity.GetOneCategory;
import dhm.com.dhmshop.entity.Province;
import dhm.com.dhmshop.utils.GetJsonDataUtil;
import dhm.com.dhmshop.utils.SpUtils;

public class EditShopActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.text_type)
    TextView textType;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.num_name)
    TextView numName;
    @BindView(R.id.edit_jianjie)
    EditText editJianjie;
    @BindView(R.id.num_jianjie)
    TextView numJianjie;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_postage)
    EditText editPostage;
    @BindView(R.id.num_phone)
    TextView numPhone;
    @BindView(R.id.edit_position)
    EditText editPosition;
    @BindView(R.id.num_position)
    TextView numPosition;
    @BindView(R.id.entity)
    RadioButton entity;
    @BindView(R.id.fictitious)
    RadioButton fictitious;
    @BindView(R.id.shoptype)
    RadioGroup shoptype;
    @BindView(R.id.edit_man)
    EditText editMan;
    @BindView(R.id.edit_e)
    EditText editE;
    @BindView(R.id.text_postion)
    TextView textPosition;
    @BindView(R.id.rela_bao)
    RelativeLayout relaBao;
    private String uid;
    private String shop_id;
    private PressenterImpl pressenter;
    private PopupWindow popWindow;
    private OptionsPickerView pvOptions;


    private ArrayList<Province> options1Items = new ArrayList<>(); //省
    private ArrayList<String> optionshItems = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区

    private String province;
    private String city;
    private String area;
    private RecyclerView recy;
    private int cateposition;
    private GetOneCategory getOneCategory;
    private ShowITypeAdapter showITypeAdapter;
    private String isfirst;
    private String ischild;
    private int childpositions;
    private int oneposition;
    private String store_type;

    @Override
    protected int getLayout() {
        return R.layout.activity_edit_shop;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        uid = SpUtils.getString(this, "uid");
        shop_id = SpUtils.getString(this, "shop_id");
        pressenter = new PressenterImpl();
        pressenter.attachView(this);

    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", Constant.TOKEN);
        map.put("uid", uid);
        pressenter.sendMessage(this, Constant.GetMyShopinfo, map, GetMyshop.class);
        initJsonData();
        shoptype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.entity:
                        relaBao.setVisibility(View.VISIBLE);
                        break;
                    case R.id.fictitious:
                        relaBao.setVisibility(View.GONE);
                        break;
                    default:
                }
            }
        });

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editName.getText().toString();
                numName.setText(s.length()+"");
                if (s.length()>30){
                    numName.setTextColor(getResources().getColor(R.color.main));
                }else {
                    numName.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
        editJianjie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editJianjie.getText().toString();
                numJianjie.setText(s.length()+"");
                if (s.length()>200){
                    numJianjie.setTextColor(getResources().getColor(R.color.main));
                }else {
                    numJianjie.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editPhone.getText().toString();
                numPhone.setText(s.length()+"");
                if (s.length()>11){
                    numPhone.setTextColor(getResources().getColor(R.color.main));
                }else {
                    numPhone.setTextColor(Color.parseColor("#999999"));
                }
            }
        });

        editPosition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editPosition.getText().toString();
                numPosition.setText(s.length()+"");
                if (s.length()>50){
                    numPosition.setTextColor(getResources().getColor(R.color.main));
                }else {
                    numPosition.setTextColor(Color.parseColor("#999999"));
                }
            }
        });


    }

    @Override
    public void requesta(Object data) {
        if (data instanceof GetMyshop) {
            GetMyshop getMyshop = (GetMyshop) data;
            if (getMyshop.getCode() == 1) {
                editName.setText(getMyshop.getData().getStore_name());
                editJianjie.setText(getMyshop.getData().getExcerpt());
                editPhone.setText(getMyshop.getData().getMobile());
                textPosition.setText(getMyshop.getData().getProvince()+getMyshop.getData().getCity()+getMyshop.getData().getArea());

                editPosition.setText(getMyshop.getData().getAddress());
                if (getMyshop.getData().getIs_free()==1){
                    shoptype.check(R.id.fictitious);
                }else {
                    shoptype.check(R.id.entity);
                    editMan.setText(getMyshop.getData().getMoney()+"");
                    editMan.setText(getMyshop.getData().getPiece()+"");
                }
                province=getMyshop.getData().getProvince();
                city=getMyshop.getData().getCity();
                area=getMyshop.getData().getArea();

                if (getMyshop.getData().getIs_free()==1){
                    shoptype.check(R.id.fictitious);
                    relaBao.setVisibility(View.GONE);
                }else {
                    shoptype.check(R.id.entity);
                    relaBao.setVisibility(View.VISIBLE);
                }

            }
        }else if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getCode()==1){
                finish();
            }
        }else if (data instanceof GetOneCategory){
            getOneCategory = (GetOneCategory) data;
            cateposition = 0;
            Map<String,String> map=new HashMap<>();
            map.put("token",Constant.TOKEN);
            map.put("pid", getOneCategory.getData().get(cateposition).getId()+"");
            pressenter.sendMessage(EditShopActivity.this,Constant.GetChildCategory,map, GetOneCategory.ChildCategory.class);

        }else if (data instanceof GetOneCategory.ChildCategory){
            GetOneCategory.ChildCategory childCategory= (GetOneCategory.ChildCategory) data;
            cateposition=cateposition+1;
            if (cateposition==getOneCategory.getData().size()){
                showITypeAdapter.setImageList(getOneCategory.getData());
                return;
            }
            getOneCategory.getData().get(cateposition-1).setChildCategory(childCategory);
            Map<String,String> map=new HashMap<>();
            map.put("token",Constant.TOKEN);
            map.put("pid", getOneCategory.getData().get(cateposition).getId()+"");
            pressenter.sendMessage(EditShopActivity.this,Constant.GetChildCategory,map, GetOneCategory.ChildCategory.class);
        }
    }

    @Override
    public void fail(String error) {

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
    @OnClick({R.id.back, R.id.line_type, R.id.sure, R.id.line_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.line_position:

                //选择地址
                pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        textPosition.setText(options1Items.get(options1).getPickerViewText()
                                + options2Items.get(options1).get(options2)
                                + options3Items.get(options1).get(options2).get(options3));
                        province = options1Items.get(options1).getPickerViewText().toString().replaceAll(" ", "");
                        city = options2Items.get(options1).get(options2).toString().replaceAll(" ", "");
                        area = options3Items.get(options1).get(options2).get(options3).trim().toString().replaceAll(" ", "");
                    }
                }).build();

                pvOptions.setPicker(optionshItems, options2Items, options3Items);//三级选择器
                pvOptions.show();

                break;
            case R.id.line_type:
                setBackgroundAlpha(0.5f);
                showPopwindow();
                popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.sure:
                String shop_name = editName.getText().toString();
                String excerpt = editJianjie.getText().toString();
                String mobile = editPhone.getText().toString();
                String address = editPosition.getText().toString();
                if (store_type==null||store_type.equals("0")||shop_name==null||shop_name.equals("")||excerpt==null||excerpt.equals("")||mobile==null||mobile.equals("")||address==null||address.equals("")){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("token",Constant.TOKEN);
                map.put("uid",uid);
                map.put("shop_id",shop_id);
                map.put("store_name",shop_name);
                map.put("shop_type",store_type);
                map.put("excerpt",excerpt);
                map.put("address",address);
                map.put("mobile",mobile);
                map.put("province",province);
                map.put("city",city);
                map.put("area",area);
                int checkedRadioButtonId = shoptype.getCheckedRadioButtonId();
                switch (checkedRadioButtonId){
                    case R.id.entity:
                        map.put("is_free","0");
                        String piece = editMan.getText().toString();
                        String money = editE.getText().toString();
                        String postage = editPostage.getText().toString();
                        if (piece==null||piece.equals("")||money==null||money.equals("")||postage==null||postage.equals("")){
                            Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        map.put("money",money);
                        map.put("piece",piece);
                        map.put("postage",postage);
                        break;
                    case R.id.fictitious:
                        map.put("is_free","1");
                        map.put("money","0.00");
                        map.put("piece","0");
                        map.put("postage","postage");
                        break;
                    default:
                        Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                        return;
                }

                pressenter.sendMessage(EditShopActivity.this,Constant.FixShopinfo,map, Bean.class);

                break;
            default:
        }
    }

    private void showPopwindow() {
        View popView = View.inflate(this, R.layout.popup_shoptype, null);
        Button sure =  popView.findViewById(R.id.sure);
        recy = popView.findViewById(R.id.recy);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        isfirst = "0";
        ischild = "0";
        oneposition = 0;
        childpositions = 0;

        store_type = "0";

        showITypeAdapter = new ShowITypeAdapter(this);
        showITypeAdapter.setaddClick(new ShowITypeAdapter.OnIntentClick() {
            @Override
            public void item(int posttion, int childposttion) {
                if (isfirst.equals("0")){
                    isfirst="1";
                }else {
                    if (ischild.equals("1")){
                        getOneCategory.getData().get(oneposition).getChildCategory().getData().get(childpositions).setChecked("0");
                    }else {
                        getOneCategory.getData().get(oneposition).setChecked("0");
                    }
                }
                getOneCategory.getData().get(posttion).getChildCategory().getData().get(childposttion).setChecked("1");
                ischild="1";
                childpositions=childposttion;
                oneposition=posttion;
                showITypeAdapter.notifyDataSetChanged();
                store_type=getOneCategory.getData().get(posttion).getChildCategory().getData().get(childposttion).getId()+"";
            }
        });
        showITypeAdapter.setdeleteClick(new ShowITypeAdapter.OnClick() {
            @Override
            public void item(int posttion) {
                if (isfirst.equals("0")){
                    isfirst="1";
                }else {
                    if (ischild.equals("1")){
                        getOneCategory.getData().get(oneposition).getChildCategory().getData().get(childpositions).setChecked("0");
                    }else {
                        getOneCategory.getData().get(oneposition).setChecked("0");
                    }
                }
                getOneCategory.getData().get(posttion).setChecked("1");
                ischild="0";
                oneposition=posttion;
                showITypeAdapter.notifyDataSetChanged();
                store_type=getOneCategory.getData().get(posttion).getId()+"";
            }
        });



        recy.setLayoutManager(manager);
        recy.setAdapter(showITypeAdapter);

        popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);// 设置允许在外点击消失

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sure:
                        if (ischild.equals("1")){
                            textType.setText(getOneCategory.getData().get(oneposition).getChildCategory().getData().get(childpositions).getName());
                        }else {
                            textType.setText(getOneCategory.getData().get(oneposition).getName());
                        }
                        popWindow.dismiss();
                        break;
                    default:
                }
            }
        };

        sure.setOnClickListener(listener);

        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });


        Map<String,String> map=new HashMap<>();
        map.put("token",Constant.TOKEN);
        pressenter.sendMessage(EditShopActivity.this,Constant.GetOneCategory,map, GetOneCategory.class);
    }

    private void initJsonData() {
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<Province> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            optionshItems.add(jsonBean.get(i).getName());

            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三级）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }
    public ArrayList<Province> parseData(String result) {//Gson 解析
        ArrayList<Province> detail = new ArrayList<>();
        detail.clear();
        Gson gson = new GsonBuilder().setLenient().create();
        detail = gson.fromJson(result, new TypeToken<List<Province>>() {
        }.getType());

        return detail;
    }

}
