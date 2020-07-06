package dhm.com.dhmshop.view.mine.myshop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import dhm.com.dhmshop.entity.Bean;
import dhm.com.dhmshop.utils.SpUtils;

public class WithdrawalActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.text_price)
    TextView textPrice;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.btn_count)
    TextView btnCount;
    @BindView(R.id.excess)
    TextView excess;
    @BindView(R.id.sure)
    Button sure;

    private PressenterImpl pressenter;

    private boolean isuser=false;
    private boolean ispwd=false;
    private String uid;
    private String shop_id;

    @Override
    protected int getLayout() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        uid = SpUtils.getString(this, "uid");
        shop_id = SpUtils.getString(this, "shop_id");
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = userName.getText().toString();

                if (!ispwd){
                    sure.setClickable(false);
                    if (s==null||s.equals("")){
                        sure.setBackgroundResource(R.drawable.back_button);
                        isuser=false;
                    }else {
                        sure.setBackgroundResource(R.drawable.back_registn);
                        isuser=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        sure.setClickable(false);
                        sure.setBackgroundResource(R.drawable.back_registn);
                        isuser=false;
                    }else {
                        sure.setClickable(true);
                        sure.setBackgroundResource(R.drawable.back_btn);
                        isuser=true;
                    }
                }


            }
        });
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = price.getText().toString();

                if (s==null||s.equals("")){
                    excess.setVisibility(View.GONE);

                }else {
                    excess.setVisibility(View.VISIBLE);
                    price.setText("    "+s);
                }

                if (!isuser){
                    if (s==null||s.equals("")){
                        sure.setBackgroundResource(R.drawable.back_button);
                        sure.setBackgroundResource(R.drawable.back_button);
                        ispwd=false;
                    }else {
                        sure.setBackgroundResource(R.drawable.back_registn);
                        sure.setBackgroundResource(R.drawable.back_button);
                        ispwd=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        sure.setBackgroundResource(R.drawable.back_registn);
                        sure.setBackgroundResource(R.drawable.back_button);
                        ispwd=false;
                    }else {
                        sure.setBackgroundResource(R.drawable.back_btn);
                        sure.setBackgroundResource(R.drawable.back_button);
                        ispwd=true;
                    }
                }


            }
        });

    }

    @Override
    protected void initData() {

    }
    
    @OnClick({R.id.back, R.id.btn_count, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_count:
                break;
            case R.id.sure:
                String account = userName.getText().toString();
                String account_remark = count.getText().toString();
                String money = price.getText().toString();

                if (account==null||account.equals("")||money==null||money.equals("")){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String,String> map=new HashMap<>();
                map.put("token",Constant.TOKEN);
                map.put("uid",uid);
                map.put("shop_id",shop_id);
                map.put("account",account);
                map.put("account_type","1");
                map.put("money",money);
                if (account_remark==null||account_remark.equals("")){

                }else {
                    map.put("account_remark",account_remark);
                }
                pressenter.sendMessage(this, Constant.CashOutShop,map, Bean.class);

                break;
            default:
        }
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getCode()==1){
                finish();
            }
            
            
        }

    }

    @Override
    public void fail(String error) {

    }
}
