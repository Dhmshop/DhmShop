package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.mine.ShowAddrAdapter;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.UserAddr;
import dhm.com.dhmshop.utils.SpUtils;

public class AddressActivity extends BaseActiity implements LoginContract.IView {
    @BindView(R.id.recy)
    RecyclerView recy;

    private PressenterImpl pressenter;
    private String uid;
    private ShowAddrAdapter showAddrAdapter;
    private UserAddr userAddr;


    @Override
    protected int getLayout() {
        return R.layout.activity_address;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(AddressActivity.this, "uid");
        getWindow().setStatusBarColor(Color.LTGRAY);
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        showAddrAdapter = new ShowAddrAdapter(AddressActivity.this);
        showAddrAdapter.setaddClick(new ShowAddrAdapter.OnIntentClick() {
            @Override
            public void item(int postion) {
                Intent intent=new Intent(AddressActivity.this,AddaddrActivity.class);
                intent.putExtra("aid",userAddr.getData().get(postion).getId()+"");
                startActivity(intent);
            }
        });
        recy.setLayoutManager(linearLayoutManager);
        recy.setAdapter(showAddrAdapter);
        getaddr();
    }

    private void getaddr() {
        Map<String,String> map=new HashMap<>();
        map.put("token",Constant.TOKEN);
        map.put("uid",uid);
        pressenter.sendMessage(AddressActivity.this, Constant.GetUserAddr,map, UserAddr.class);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.back, R.id.chenge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.chenge:
                Intent intent=new Intent(AddressActivity.this,AddaddrActivity.class);
                intent.putExtra("aid","no");
                startActivity(intent);
                break;
            default:
        }
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof UserAddr){
            userAddr = (UserAddr) data;
            if (userAddr.getData().size()==0){
                Toast.makeText(this, "您还没有收货地址呦", Toast.LENGTH_SHORT).show();
            }else {
                showAddrAdapter.showImage(userAddr.getData());
            }
        }
    }

    @Override
    public void fail(String error) {

    }
}
