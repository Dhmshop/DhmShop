package dhm.com.dhmshop.framework.module.commodity.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.framework.base.BaseActivity;
import dhm.com.dhmshop.framework.module.commodity.base.GradationScrollView;
import dhm.com.dhmshop.framework.module.commodity.base.NoScrollListView;
import dhm.com.dhmshop.framework.module.commodity.base.ScrollViewContainer;
import dhm.com.dhmshop.framework.module.commodity.base.StatusBarUtil;
import dhm.com.dhmshop.framework.module.commodity.entity.OneGoodsEntity;
import dhm.com.dhmshop.framework.module.commodity.model.CommodityDetailsModel;
import dhm.com.dhmshop.framework.module.commodity.view.CommodityDetailsView;
import dhm.com.dhmshop.framework.utils.GlideUtil;
import dhm.com.dhmshop.framework.utils.StringUtil;


public class CommodityDetailsActivity extends BaseActivity<CommodityDetailsModel> implements CommodityDetailsView, View.OnClickListener, GradationScrollView.ScrollViewListener {

    private int uid = 4;
    private int gid;
    private List<OneGoodsEntity> commodityDetailstitles;
    private ImageView commdetaiImg;
    /**
     * 188
     */
    private TextView commdePrice;
    /**
     * WOODWICK无烟精油香氛大豆蜡烛 亚麻240G国内发货（预计1-3工作日内送达）
     */
    private TextView commdeTitle;
    private ImageView commedYoufeiima;
    /**
     * 包邮
     */
    private TextView commedYoufeitv;
    private LinearLayout commedYoufei;
    /**
     * 广东广州
     */
    private TextView commedLocationtv;
    /**
     * (1111)
     */
    private TextView commdeCommentshu;
    private RelativeLayout commdeComment;
    private RelativeLayout commdetailJindian;
    /**
     * 继续拖动,查看图文详情
     */
    private TextView commdetailTuodong;
    private GradationScrollView scrollview;
    private NoScrollListView commdetialNoscllv;
    /**
     * 已经最到底啦!
     */
    private TextView commdetailDaodi;
    private ScrollViewContainer svContainer;
    private ImageView commdeFanhui;
    private ImageView commdeShare;
    private LinearLayout commdeService;
    private LinearLayout commdeCollected;
    private LinearLayout commdeShop;
    /**
     * 加入购物车
     */
    private TextView commdetailJiaru;
    /**
     * 立即购买
     */
    private TextView commdetailBuy;
    private LinearLayout commdetailBottom;
    private LinearLayout commdeTitleLl;
    private ScrollViewContainer container;

    private int height;
    private int width;
    private TextView commoDityDetailsShopTv;
    private ImageView commoDityDetailsShop;

    @Override
    protected void initView() {
        initViews();
        //透明状态栏
        StatusBarUtil.setTranslucentForImageView(this, commdeTitleLl);
      /*  LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) commdeTitleLl.getLayoutParams();
        params1.setMargins(15, -StatusBarUtil.getStatusBarHeight(this) / 4, 15, 0);
        commdeTitleLl.setLayoutParams(params1);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) commdetaiImg.getLayoutParams();
        params.height = getScreenHeight(this) * 2 / 3;
        commdetaiImg.setLayoutParams(params);*/

        container = new ScrollViewContainer(getApplicationContext());
        Intent intent = getIntent();


        gid = intent.getIntExtra("pid", 1);
        commodityDetailstitles = new ArrayList<>();

    }

    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    private void initViews() {
        commdetaiImg = (ImageView) findViewById(R.id.commdetai_img);
        commdePrice = (TextView) findViewById(R.id.commde_price);
        commdeTitle = (TextView) findViewById(R.id.commde_title);
        commedYoufeiima = (ImageView) findViewById(R.id.commed_youfeiima);
        commedYoufeitv = (TextView) findViewById(R.id.commed_youfeitv);
        commedYoufei = (LinearLayout) findViewById(R.id.commed_youfei);
        commedLocationtv = (TextView) findViewById(R.id.commed_locationtv);
        commdeCommentshu = (TextView) findViewById(R.id.commde_commentshu);
        commdeComment = findViewById(R.id.commde_comment);
        commdeComment.setOnClickListener(this);
        commdetailJindian = findViewById(R.id.commdetail_jindian);
        commdetailJindian.setOnClickListener(this);
        commdetailTuodong = (TextView) findViewById(R.id.commdetail_tuodong);
        scrollview = (GradationScrollView) findViewById(R.id.scrollview);
        commdetialNoscllv = (NoScrollListView) findViewById(R.id.commdetial_noscllv);
        commdetailDaodi = (TextView) findViewById(R.id.commdetail_daodi);
        svContainer = (ScrollViewContainer) findViewById(R.id.sv_container);
        commdeFanhui = (ImageView) findViewById(R.id.commde_fanhui);
        commdeShare = (ImageView) findViewById(R.id.commde_share);
        commdeService = (LinearLayout) findViewById(R.id.commde_service);
        commdeService.setOnClickListener(this);
        commdeCollected = (LinearLayout) findViewById(R.id.commde_collected);
        commdeCollected.setOnClickListener(this);
        commdeShop = (LinearLayout) findViewById(R.id.commde_shop);
        commdeShop.setOnClickListener(this);
        commdetailJiaru = (TextView) findViewById(R.id.commdetail_jiaru);
        commdetailJiaru.setOnClickListener(this);
        commdetailBuy = (TextView) findViewById(R.id.commdetail_buy);
        commdetailBuy.setOnClickListener(this);
        commdetailBottom = (LinearLayout) findViewById(R.id.commdetail_bottom);
        commdeTitleLl = findViewById(R.id.commde_titlell);
        commoDityDetailsShopTv = findViewById(R.id.commoditydetails_shoptv);
        commoDityDetailsShop = findViewById(R.id.commoditydetails_shop);
    }

    @Override
    protected void initData() {
        model.getCommodityDetail(gid, uid);
    }

    @Override
    protected void initListener() {
        ViewTreeObserver vto = commdetaiImg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                commdeTitleLl.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = commdetaiImg.getHeight();

                scrollview.setScrollViewListener(CommodityDetailsActivity.this);
            }
        });
    }

    @Override
    protected int intiLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    protected CommodityDetailsModel initModel() {
        return new CommodityDetailsModel();
    }

    @Override
    public void getCommodityDetails(OneGoodsEntity oneGoodsEntity) {
        if (oneGoodsEntity != null || oneGoodsEntity.getComment().size() > 0) {

            Log.d(TAG, "getCommodityDetails: " + oneGoodsEntity.getGoods_content() + oneGoodsEntity.getGoods_images_thumb());
            String image = oneGoodsEntity.getGoods_images().get(0);
            OneGoodsEntity.ShopInfoBean shop_info = oneGoodsEntity.getShop_info();

            GlideUtil.loadImage(CommodityDetailsActivity.this, StringUtil.preventNull(image), commdetaiImg);
            commdePrice.setText(StringUtil.preventNull(oneGoodsEntity.getPrice()));
            commdeTitle.setText(StringUtil.preventNull(oneGoodsEntity.getGoods_name()));
            commedLocationtv.setText(StringUtil.preventNull(shop_info.getProvince() + shop_info.getCity()));
            commdeCommentshu.setText(StringUtil.preventNull("(" + oneGoodsEntity.getComment_count() + ")"));
            GlideUtil.loadCircleImage(this,StringUtil.preventNull(Constant.PATH+shop_info.getImg()),commoDityDetailsShop);
            commoDityDetailsShopTv.setText(StringUtil.preventNull(shop_info.getStore_name()));
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.commde_comment:
                break;
            case R.id.commdetail_jindian:
                break;
            case R.id.commde_service:
                break;
            case R.id.commde_collected:
                break;
            case R.id.commde_shop:
                break;
            case R.id.commdetail_jiaru:
                break;
            case R.id.commdetail_buy:
                break;
        }
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            commdeTitleLl.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            commdeTitleLl.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {    //滑动到banner下面设置普通颜色
            commdeTitleLl.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }
}
