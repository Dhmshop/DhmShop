package dhm.com.dhmshop.view.main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;

public class SearchActivity extends BaseActiity implements View.OnClickListener {
    private ImageView mReturnSeach;
    /**
     * 请输入搜索关键词
     */
    private EditText mEtSeach;
    private LinearLayout mMainSearch;
    /**
     * 搜索
     */
    private TextView mSeachSousuo;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

        mReturnSeach = (ImageView) findViewById(R.id.return_seach);
        mReturnSeach.setOnClickListener(this);
        mEtSeach = (EditText) findViewById(R.id.et_seach);
        mEtSeach.setOnClickListener(this);
        mMainSearch = (LinearLayout) findViewById(R.id.main_search);
        mMainSearch.setOnClickListener(this);
        mSeachSousuo = (TextView) findViewById(R.id.seach_sousuo);
        mSeachSousuo.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.return_seach:
                break;
            case R.id.et_seach:
                break;
            case R.id.main_search:
                break;
            case R.id.seach_sousuo:
                break;
        }
    }
}
