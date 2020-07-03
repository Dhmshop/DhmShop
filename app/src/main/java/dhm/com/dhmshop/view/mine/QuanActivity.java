package dhm.com.dhmshop.view.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import dhm.com.dhmshop.entity.GetAbout;

public class QuanActivity extends BaseActiity implements LoginContract.IView {


    @BindView(R.id.tv_me_title)
    TextView tvMeTitle;
    @BindView(R.id.ticker_progress)
    ProgressBar tickerProgress;
    @BindView(R.id.ticket_wb)
    WebView ticketWb;

    private PressenterImpl pressenter;
    private String name;

    @Override
    protected int getLayout() {
        return R.layout.activity_quan;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        tvMeTitle.setText(name);




    }

    @Override
    protected void initData() {

        Map<String,String> map=new HashMap<>();
        map.put("token", Constant.TOKEN);
        if (name.equals("版本信息")) {
            map.put("article_id","2");
        } else {
            map.put("article_id","3");
        }
        pressenter.sendMessage(this,Constant.GetAboutCon,map, GetAbout.class);

    }

    @Override
    public void requesta(Object data) {
        if (data instanceof GetAbout){
            GetAbout getAbout= (GetAbout) data;
            if (getAbout.getCode()==1){
                ticketWb.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        //设置加载进度条
                        view.setWebChromeClient(new WebChromeClient() {
                            @Override
                            public void onProgressChanged(WebView view, int progress) {
                                if (tickerProgress != null) {
                                    tickerProgress.setProgress(progress);
                                    if (progress == 100) {
                                        tickerProgress.setVisibility(View.GONE);
                                    } else {
                                        tickerProgress.setVisibility(View.VISIBLE);
                                    }
                                }
                                super.onProgressChanged(view, progress);
                            }
                        });
                        return true;

                    }
                });

                WebSettings settings = ticketWb.getSettings();
                settings.setUseWideViewPort(true);//是否支持双击缩放网页操作(wap网页不支持)
                settings.setJavaScriptEnabled(true);//是否支持JavaScript
                String js = "<script type=\"text/javascript\">"+
                        "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                        "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                        "imgs[i].style.width = '100%';" +  // 宽度改为100%
                        "imgs[i].style.height = 'auto';" +
                        "}" +
                        "</script>";
//                ticketWb.loadData(Html.fromHtml(getAbout.getData()).toString()+js, "text/html", "UTF-8");
                ticketWb.loadDataWithBaseURL(null, getAbout.getData()+js, "text/html", "utf-8", null);

            }
        }

    }

    @Override
    public void fail(String error) {

    }

    @OnClick(R.id.iv_me_back)
    public void onViewClicked() {
        finish();
    }
}
