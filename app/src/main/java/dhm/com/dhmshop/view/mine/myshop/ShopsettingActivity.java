package dhm.com.dhmshop.view.mine.myshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import dhm.com.dhmshop.entity.GetMyshop;
import dhm.com.dhmshop.entity.GetOneCategory;
import dhm.com.dhmshop.utils.SpUtils;

public class ShopsettingActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.shoptype)
    TextView shoptype;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.text_sex)
    TextView textSex;
    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.text_position)
    TextView textPosition;
    @BindView(R.id.text_bao)
    TextView textBao;
    private PressenterImpl pressenter;
    private String uid;

    private GetOneCategory getOneCategory;
    private int cateposition;
    private GetMyshop getMyshop;

    @Override
    protected int getLayout() {
        return R.layout.activity_shopsetting;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(this, "uid");

    }

    @Override
    protected void initData() {


        Map<String,String> map=new HashMap<>();
        map.put("token", Constant.TOKEN);
        map.put("uid",uid);
        pressenter.sendMessage(ShopsettingActivity.this,Constant.GetMyShopinfo,map, GetMyshop.class);
    }

    @OnClick({R.id.back, R.id.edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.edit:
                Intent intent=new Intent(ShopsettingActivity.this,EditShopActivity.class);
                startActivity(intent);
                break;
                default:
        }
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof GetMyshop){
            getMyshop = (GetMyshop) data;
            if (getMyshop.getCode()==1){
                shoptype.setText("没有");
                nickname.setText(getMyshop.getData().getStore_name());
                textSex.setText(getMyshop.getData().getExcerpt());
                textPhone.setText(getMyshop.getData().getMobile());
                textPosition.setText(getMyshop.getData().getProvince()+ getMyshop.getData().getCity()+ getMyshop.getData().getArea()+ getMyshop.getData().getAddress());
                if (getMyshop.getData().getIs_free()==1){
                    textBao.setText("包邮");
                }else {
                    textBao.setText("不包邮");
                }

                Map<String,String> map=new HashMap<>();
                map.put("token",Constant.TOKEN);
                pressenter.sendMessage(ShopsettingActivity.this,Constant.GetOneCategory,map, GetOneCategory.class);
            }else {
                Toast.makeText(this, "请编辑店铺信息", Toast.LENGTH_SHORT).show();
            }
        }else if (data instanceof GetOneCategory){
            getOneCategory = (GetOneCategory) data;
            cateposition = 0;
            Map<String,String> map=new HashMap<>();
            map.put("token",Constant.TOKEN);
            map.put("pid", getOneCategory.getData().get(cateposition).getId()+"");
            pressenter.sendMessage(ShopsettingActivity.this,Constant.GetChildCategory,map, GetOneCategory.ChildCategory.class);

        }else if (data instanceof GetOneCategory.ChildCategory){
            GetOneCategory.ChildCategory childCategory= (GetOneCategory.ChildCategory) data;
            cateposition=cateposition+1;
            if (cateposition==getOneCategory.getData().size()){
                for (int i=0;i<getOneCategory.getData().size();i++){
                    if (getMyshop.getData().getShop_type().equals(getOneCategory.getData().get(i).getId())){
                        shoptype.setText(getOneCategory.getData().get(i).getName());
                        i=getOneCategory.getData().size();
                    }
                    for (int j=0;j<getOneCategory.getData().get(i).getChildCategory().getData().size();j++){
                        if (getMyshop.getData().getShop_type().equals(getOneCategory.getData().get(i).getChildCategory().getData().get(j).getId())){
                            shoptype.setText(getOneCategory.getData().get(i).getChildCategory().getData().get(j).getName());
                            i=getOneCategory.getData().size();
                        }
                    }


                }
                return;
            }
            getOneCategory.getData().get(cateposition-1).setChildCategory(childCategory);
            Map<String,String> map=new HashMap<>();
            map.put("token",Constant.TOKEN);
            map.put("pid", getOneCategory.getData().get(cateposition).getId()+"");
            pressenter.sendMessage(ShopsettingActivity.this,Constant.GetChildCategory,map, GetOneCategory.ChildCategory.class);
        }
    }
    @Override
    public void fail(String error) {

    }
}
