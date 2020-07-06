package dhm.com.dhmshop.framework.module.home.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
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
        SpannableString s = new SpannableString("请输入搜索关键词");
        AbsoluteSizeSpan textSize = new AbsoluteSizeSpan(14,true);
        s.setSpan(textSize,0,s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mEtSeach.setHint(s);
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
