package dhm.com.dhmshop.fragment.wode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseFragment;
import dhm.com.dhmshop.base.LazyLoadFragment;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.UserLogin;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.view.mine.AddressActivity;
import dhm.com.dhmshop.view.mine.MyinfoActivity;
import dhm.com.dhmshop.view.mine.SettingActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class WodeFragment extends LazyLoadFragment implements LoginContract.IView {


    @BindView(R.id.mine_head)
    SimpleDraweeView mineHead;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.myshop)
    LinearLayout myshop;
    Unbinder unbinder;
    private View rootView;

    private PressenterImpl pressenter;
    private String uid;

    public WodeFragment() {
        // Required empty public constructor
    }

    protected void initView() {
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(getActivity(), "uid");

    }

    protected void initData() {
        Map<String,String> map=new HashMap<>();
        map.put("token",Constant.TOKEN);
        map.put("id",uid);
        pressenter.sendMessage(getActivity(), Constant.GetUserInfo,map, UserLogin.class);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_wode, container, false);
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
    protected int setContentView() {
        return R.layout.fragment_wode;
    }

    @SuppressLint("NewApi")
    @Override
    protected void lazyLoad() {
        Toast.makeText(getActivity(), "显示", Toast.LENGTH_SHORT).show();
        getActivity().getWindow().setStatusBarColor(Color.parseColor("#aaddff"));

    }

    @OnClick({R.id.settings, R.id.allorder, R.id.topay, R.id.myInfo, R.id.togo, R.id.geted, R.id.toping, R.id.myshop, R.id.valuation, R.id.favorite, R.id.footer, R.id.address, R.id.setLogin})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.settings:
                intent=new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.allorder:
                break;
            case R.id.topay:
                break;
            case R.id.togo:
                break;
            case R.id.geted:
                break;
            case R.id.toping:
                break;
            case R.id.myshop:
                break;
            case R.id.valuation:
                break;
            case R.id.myInfo:
                intent=new Intent(getActivity(), MyinfoActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.favorite:
                break;
            case R.id.footer:
                break;
            case R.id.address:
                intent =new Intent(getActivity(), AddressActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.setLogin:
                break;
            default:
        }
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof UserLogin){
            UserLogin userLogin= (UserLogin) data;
            if (userLogin.getCode()==1){
                name.setText(userLogin.getData().get(0).getUser_nickname());
                mineHead.setImageURI("http://app.jzcto.com"+userLogin.getData().get(0).getHeadsmall());
                if (userLogin.getData().get(0).getUser_type()==2){
                    myshop.setVisibility(View.VISIBLE);
                }else {
                    myshop.setVisibility(View.GONE);
                }

            }
        }

    }

    @Override
    public void fail(String error) {

    }
}
