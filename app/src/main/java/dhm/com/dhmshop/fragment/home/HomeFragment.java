package dhm.com.dhmshop.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseFragment;
import dhm.com.dhmshop.view.main.MainActivity;
import dhm.com.dhmshop.view.main.SearchActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {


    private View view;
    private LinearLayout mMainHomefg;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        mMainHomefg = (LinearLayout) view.findViewById(R.id.main_homefg);
        mMainHomefg.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.main_homefg:

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

                break;
        }
    }
}
