package dhm.com.dhmshop.view.mine.myshop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.mine.ParamsAdapter;
import dhm.com.dhmshop.adapter.mine.ShowDataAdapter;
import dhm.com.dhmshop.adapter.mine.ShowImageAdapter;
import dhm.com.dhmshop.adapter.home.AllImageAdapter;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.GetGoodsDetail;
import dhm.com.dhmshop.entity.Result;
import dhm.com.dhmshop.entity.ResultDetailCallback;
import dhm.com.dhmshop.utils.BitMap;
import dhm.com.dhmshop.utils.BitmapUtil;
import dhm.com.dhmshop.utils.Calendar.DateBean;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.utils.TakePhotoUtil;
import dhm.com.dhmshop.view.main.MainActivity;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.Call;

public class ShowshopActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_tit)
    EditText editTit;
    @BindView(R.id.edit_keyword)
    EditText editKeyword;
    @BindView(R.id.entity)
    RadioButton entity;
    @BindView(R.id.fictitious)
    RadioButton fictitious;
    @BindView(R.id.shoptype)
    RadioGroup shoptype;
    @BindView(R.id.one)
    CheckBox one;
    @BindView(R.id.two)
    CheckBox two;
    @BindView(R.id.three)
    CheckBox three;
    @BindView(R.id.five)
    CheckBox five;
    @BindView(R.id.now_price)
    EditText nowPrice;
    @BindView(R.id.edit_num)
    EditText editnum;
    @BindView(R.id.old_price)
    EditText oldPrice;
    @BindView(R.id.recy_image)
    RecyclerView recyImage;
    @BindView(R.id.agree)
    CheckBox agree;
    @BindView(R.id.recy_param)
    RecyclerView recyParam;
    @BindView(R.id.shelfn)
    RadioButton shelfn;
    @BindView(R.id.shelfs)
    RadioButton shelfs;
    @BindView(R.id.timen)
    RadioButton timen;
    @BindView(R.id.times)
    RadioButton times;
    @BindView(R.id.times_shelf)
    RadioGroup timesShelf;
    @BindView(R.id.shop_shelf)
    RadioGroup shopShelf;
    @BindView(R.id.shelftime)
    TextView shelftime;
    @BindView(R.id.edit_detail)
    EditText editDetail;
    @BindView(R.id.recy_detail)
    RecyclerView recyDetail;
    private PressenterImpl pressenter;

    private static final int REQUEST_IMAGE = 2;
    private static final int DETAILREQUEST_IMAGE = 200;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private ArrayList<String> mSelectPath;
    private ArrayList<Bitmap> image = new ArrayList<>();
    private ArrayList<String> detailmSelectPath;
    private ArrayList<Bitmap> detailimage = new ArrayList<>();
    private AllImageAdapter allImageAdapter;
    private ShowImageAdapter showImageAdapter;
    private String choose="all";
    private ParamsAdapter paramsAdapter;
    private PopupWindow popWindow;
    List<String> values=new ArrayList<>();
    List<String> name=new ArrayList<>();



    private RecyclerView recyData;


    private static final String TAG = MainActivity.class.getSimpleName() + "_LOG";
    private ShowDataAdapter adapter;
    private ArrayList<ArrayList<DateBean>> days;
    private String uid;
    private String shop_id;
    private String type;


    @Override
    protected int getLayout() {
        return R.layout.activity_showshop;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(this, "uid");
//        shop_id = SpUtils.getString(this, "shop_id");

        Intent intent = getIntent();
        type=intent.getStringExtra("type");

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager agridLayoutManager=new GridLayoutManager(this,4);
        agridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        showImageAdapter=new ShowImageAdapter(image,this);
        allImageAdapter=new AllImageAdapter(detailimage,this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        paramsAdapter();
        recyParam.setLayoutManager(linearLayoutManager);


        allImageAdapter.setaddClick(new AllImageAdapter.OnIntentClick() {
            @Override
            public void item() {
                choose = "all";
                pickImage();
            }
        });
        showImageAdapter.setaddClick(new ShowImageAdapter.OnIntentClick() {
            @Override
            public void item() {
                choose="nine";
                pickImage();
            }
        });
        recyImage.setLayoutManager(gridLayoutManager);
        recyImage.setAdapter(showImageAdapter);
        recyDetail.setLayoutManager(agridLayoutManager);
        recyDetail.setAdapter(allImageAdapter);
        helper.attachToRecyclerView(recyImage);
        detailhelper.attachToRecyclerView(recyDetail);


        if(type.equals("show")){
            shop_id=intent.getStringExtra("shop_id");
        }else{
            shop_id=intent.getStringExtra("shop_id");
            Map<String,String> map=new HashMap<>();
            map.put("token",Constant.TOKEN);
            map.put("gid",shop_id);
            map.put("uid",uid);
            pressenter.sendMessage(this,Constant.OneGoods,map, GetGoodsDetail.class);


        }


    }

    @Override
    protected void initData() {
        days = days("", "");
        showPopwindow();
    }

    private void paramsAdapter() {
        paramsAdapter = new ParamsAdapter(this);

        //参数值改变的时候
        paramsAdapter.setaddClick(new ParamsAdapter.OnIntentClick() {
            @Override
            public void item(int posttion, String value) {
                name.set(posttion,value);
            }
        });
        ////参数名改变的时候
        paramsAdapter.setdeleteClick(new ParamsAdapter.OnClick() {
            @Override
            public void item(int posttion, String value) {
                values.set(posttion,value);
            }
        });
        //添加参数名
        paramsAdapter.setOnAddNameClick(new ParamsAdapter.OnAddNameClick() {
            @Override
            public void item(int posttion, String value) {
                if (name.size()==posttion){
                    name.add(value);

                }else {
                    name.set(posttion,value);
                }
            }
        });
        //添加参数值
        paramsAdapter.setOnAddValueClick(new ParamsAdapter.OnAddValueClick() {
            @Override
            public void item(int posttion, String value) {
                if (values.size()==posttion){
                    values.add(value);

                }else {
                    values.set(posttion,value);
                }
            }
        });
        recyParam.setAdapter(paramsAdapter);

    }

    @OnClick({R.id.back, R.id.entity, R.id.fictitious, R.id.add_param, R.id.shelftime, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_param:
                if (name.size()!=values.size()){
                    Toast.makeText(this, "请填写完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                paramsAdapter();
                paramsAdapter.showImage(values,name);
                break;
            case R.id.shelftime:
                setBackgroundAlpha(0.5f);
                popWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.sure:
                String goods_name = editName.getText().toString();
                String keywords = editKeyword.getText().toString();
                String goods_remark = editTit.getText().toString();
                String goods_content = editDetail.getText().toString();
                String store_count = editnum.getText().toString();
                String shop_type="";
                String is_elite="";
                String is_new="";
                String is_hot="";
                String is_discount="";
                String is_on_sale="";
                switch (shoptype.getCheckedRadioButtonId()){
                    case R.id.entity:
//                        实体商品
                        shop_type="1";
                        break;
                    case R.id.fictitious:
//                        虚拟商品
                        shop_type="2";
                        break;
                    default:
                }
                switch (shopShelf.getCheckedRadioButtonId()){
                    case R.id.shelfn:
//                        不选择上架时间
                        is_on_sale="0";
                        break;
                    case R.id.shelfs:
//                        选择上架时间
                        is_on_sale="1";
                        break;
                    default:
                }
                if (one.isChecked()){
                    is_elite="1";
                }else {
                    is_elite="0";
                }
                if (two.isChecked()){
                    is_new="1";
                }else {
                    is_new="0";
                }
                if (three.isChecked()){
                    is_hot="1";
                }else {
                    is_hot="0";
                }
                if (five.isChecked()){
                    is_discount="1";
                }else {
                    is_discount="0";
                }
                String express_price = nowPrice.getText().toString();
                String price = oldPrice.getText().toString();
                if (store_count==null||store_count.equals("")||goods_name==null||goods_name.equals("")||goods_content==null||goods_content.equals("")||keywords==null||keywords.equals("")||goods_remark==null
                        ||goods_remark.equals("")||shop_type==null||shop_type.equals("")||is_elite==null||is_elite.equals("")||
                        is_new==null||is_new.equals("")||is_hot==null||is_hot.equals("")||is_discount==null||is_discount.equals("")||
                        is_on_sale==null||is_on_sale.equals("")||express_price==null||express_price.equals("")||price==null||price.equals("")||name.size()==0||values.size()==0){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (image.size()==0||detailimage.size()==0){
                    Toast.makeText(this, "请选择图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.size()!=values.size()){
                    Toast.makeText(this, "请检查参数名和参数值", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap bitmap1 = image.get(0);
                String url = TakePhotoUtil.saveTodisk(bitmap1, Constant.STOREIMGPATH);
                PostFormBuilder postFormBuilder = OkHttpUtils.post().url(Constant.PATH + Constant.AddGoods)
                        .addParams("token", Constant.TOKEN)
                        .addParams("uid", uid)
                        .addParams("goods_name", goods_name)
                        .addParams("keywords", keywords)
                        .addParams("shop_type", shop_type)
                        .addParams("store_count", store_count)
                        .addParams("price",price )
                        .addParams("express_price", express_price)
                        .addParams("is_on_sale", is_on_sale)
                        .addParams("is_elite", is_elite)
                        .addParams("is_new", is_new)
                        .addParams("is_hot",is_hot )
                        .addParams("is_discount", is_discount)
                        .addParams("goods_remark", goods_remark)
                        .addParams("goods_content",goods_content )
                        .addParams("shop_id", shop_id);

                Map<String,String> map=new IdentityHashMap<>();
                        for (int i=0;i<name.size();i++){
                            map.put("param_name[]",name.get(i));
                            map.put("param_value[]", values.get(i));
                        }
                postFormBuilder.params(map);
                Map<String, File> files = new HashMap<>();
                Map<String, File> detailfiles = new HashMap<>();

                try {
                    for (int i=0;i<image.size();i++){
                        files.put(bitmapToString(image.get(i)), new File(bitmapToString(image.get(i))));
                    }
                    for (int i=0;i<detailimage.size();i++){
                        detailfiles.put(bitmapToString(detailimage.get(i)), new File(bitmapToString(detailimage.get(i))));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



                postFormBuilder.files("goods_content_images[]",detailfiles)
                        .files("goods_image[]",files)
                        .build().execute(new ResultDetailCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ShowshopActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Result response, int id) {
                        Toast.makeText(ShowshopActivity.this, response.getMessage(), Toast.LENGTH_LONG).show();
                        if (response != null && response.getCode()==1) {
                            finish();
                        }
                    }
                });



                break;
            default:
        }
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof GetGoodsDetail){
            GetGoodsDetail getGoodsDetail = (GetGoodsDetail) data;
            if (getGoodsDetail.getCode()==1){
                editName.setText(getGoodsDetail.getData().getGoods_name());
                editTit.setText(getGoodsDetail.getData().getGoods_remark());
                editKeyword.setText(getGoodsDetail.getData().getGoods_name());
                if (getGoodsDetail.getData().getTerm_id()==1){
                    shoptype.check(R.id.entity);
                }else {
                    shoptype.check(R.id.fictitious);
                }
                if (getGoodsDetail.getData().getIs_elite()==1){
                    one.setChecked(true);
                }else {
                    one.setChecked(false);
                }
                if (getGoodsDetail.getData().getIs_new()==1){
                    two.setChecked(true);
                }else {
                    two.setChecked(false);
                }
                if (getGoodsDetail.getData().getIs_hot()==1){
                    three.setChecked(true);
                }else {
                    three.setChecked(false);
                }
                if (getGoodsDetail.getData().getIs_discount()==1){
                    five.setChecked(true);
                }else {
                    five.setChecked(false);
                }
                nowPrice.setText(getGoodsDetail.getData().getExpress_price());
                oldPrice.setText(getGoodsDetail.getData().getPrice());
                editnum.setText(getGoodsDetail.getData().getStore_count()+"");
                if (getGoodsDetail.getData().getIs_on_sale()==1){
                    shopShelf.check(R.id.shelfs);
                }else {
                    shopShelf.check(R.id.shelfn);
                }
                editDetail.setText(getGoodsDetail.getData().getGoods_content());

                if (getGoodsDetail.getData().getParam().size()!=0){
                    for (int i=0;i<getGoodsDetail.getData().getParam().size();i++){
                        name.add(getGoodsDetail.getData().getParam().get(i).getParam_name());
                        String value="";
                        for (int j=0;j<getGoodsDetail.getData().getParam().get(i).getParam_value().size();j++){
                            value+=getGoodsDetail.getData().getParam().get(i).getParam_value().get(j);
                        }
                        values.add(value);
                    }
                }

                for (int i=0;i<getGoodsDetail.getData().getGoods_images().size();i++){
                    image.add(BitMap.getInstance().returnBitMap(Constant.PATH+getGoodsDetail.getData().getGoods_images().get(i)));
                    detailimage.add(BitMap.getInstance().returnBitMap(Constant.PATH+getGoodsDetail.getData().getGoods_images().get(i)));

                }

//                adapter.notifyDataSetChanged();
                paramsAdapter.notifyDataSetChanged();
                allImageAdapter.notifyDataSetChanged();
                showImageAdapter.notifyDataSetChanged();


            }

        }

    }

    @Override
    public void fail(String error) {

    }


    private void showPopwindow() {
        View popView = View.inflate(this, R.layout.activity_calender, null);

        ImageView before =  popView.findViewById(R.id.before);
        ImageView next =  popView.findViewById(R.id.next);
        TextView month =  popView.findViewById(R.id.month);
        RecyclerView recyData =  popView.findViewById(R.id.recy_data);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyData.setLayoutManager(manager);

        adapter = new ShowDataAdapter(this);
        adapter.setDatebean(days);
        adapter.setaddClick(new ShowDataAdapter.OnIntentClick() {
            @Override
            public void item(DateBean startDate, DateBean endDate) {
                Toast.makeText(ShowshopActivity.this, startDate.getMonthStr()+startDate.getDay()+"-------------"+endDate.getDate()+endDate.getDay(), Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });


        recyData.setAdapter(adapter);
        PagerSnapHelper helper=new PagerSnapHelper();
        helper.attachToRecyclerView(recyData);

        recyData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstCompletelyVisibleItemPosition = manager.findFirstVisibleItemPosition();
                month.setText(days.get(firstCompletelyVisibleItemPosition).get(0).getMonthStr());
            }
        });
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int firstCompletelyVisibleItemPosition = manager.findFirstVisibleItemPosition();
                if (firstCompletelyVisibleItemPosition!=0){
                    recyData.smoothScrollToPosition(--firstCompletelyVisibleItemPosition);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int firstCompletelyVisibleItemPosition = manager.findFirstVisibleItemPosition();
                if ((firstCompletelyVisibleItemPosition+1)!=days.size()){
                    recyData.smoothScrollToPosition(++firstCompletelyVisibleItemPosition);
                }
            }
        });





        popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);// 设置允许在外点击消失


        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });




    }


    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else {

            if (choose.equals("nine")){
                int maxNum = 6;
                MultiImageSelector selector = MultiImageSelector.create(ShowshopActivity.this);
                selector.showCamera(true);
                selector.count(maxNum);
                selector.multi();
                selector.origin(mSelectPath);
                selector.start(ShowshopActivity.this, REQUEST_IMAGE);
            }else {
                int maxNum = 200;
                MultiImageSelector selector = MultiImageSelector.create(ShowshopActivity.this);
                selector.showCamera(true);
                selector.count(maxNum);
                selector.multi();
                selector.origin(detailmSelectPath);
                selector.start(ShowshopActivity.this, DETAILREQUEST_IMAGE);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                image.clear();
                for(String p: mSelectPath){
                    sb.append(p);
                    sb.append("\n");
                    Bitmap bitmap = BitmapUtil.getScaleBitmap(p, 0, 0);
                    image.add(bitmap);
                }
                showImageAdapter.showImage(image);
            }
        }else {
            if(resultCode == RESULT_OK){
                detailmSelectPath= data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                detailimage.clear();
                for(String p: detailmSelectPath){
                    sb.append(p);
                    sb.append("\n");
                    Bitmap bitmap = BitmapUtil.getScaleBitmap(p, 0, 0);
                    detailimage.add(bitmap);
                }
                allImageAdapter.showImage(detailimage);
            }

        }
    }


    private void requestPermission(final String permission, String rationale, final int requestCode){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ShowshopActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }


    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFrlg = 0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
                dragFrlg = ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            }else if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                dragFrlg = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(dragFrlg,0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //滑动事件  下面注释的代码，滑动后数据和条目错乱，被舍弃
//            Collections.swap(datas,viewHolder.getAdapterPosition(),target.getAdapterPosition());
//            ap.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());

            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition();
            //拿到当前拖拽到的item的viewHolder
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(image, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(image, i, i - 1);
                }
            }
            showImageAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //侧滑删除可以使用；
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
        /**
         * 长按选中Item的时候开始调用
         * 长按高亮
         * @param viewHolder
         * @param actionState
         */
        @SuppressLint("MissingPermission")
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#00ff00ff"));
                //获取系统震动服务//震动70毫秒
                Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
//                vib.vibrate(70);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        /**
         * 手指松开的时候还原高亮
         * @param recyclerView
         * @param viewHolder
         */
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setBackgroundColor(0);
            showImageAdapter.notifyDataSetChanged();  //完成拖动后刷新适配器，这样拖动后删除就不会错乱
        }
    });



    ItemTouchHelper detailhelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFrlg = 0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
                dragFrlg = ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            }else if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                dragFrlg = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(dragFrlg,0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //滑动事件  下面注释的代码，滑动后数据和条目错乱，被舍弃
//            Collections.swap(datas,viewHolder.getAdapterPosition(),target.getAdapterPosition());
//            ap.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());

            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition();
            //拿到当前拖拽到的item的viewHolder
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(image, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(image, i, i - 1);
                }
            }
            allImageAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //侧滑删除可以使用；
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
        /**
         * 长按选中Item的时候开始调用
         * 长按高亮
         * @param viewHolder
         * @param actionState
         */
        @SuppressLint("MissingPermission")
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#00ff00ff"));
                //获取系统震动服务//震动70毫秒
                Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
//                vib.vibrate(70);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        /**
         * 手指松开的时候还原高亮
         * @param recyclerView
         * @param viewHolder
         */
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setBackgroundColor(0);
            allImageAdapter.notifyDataSetChanged();  //完成拖动后刷新适配器，这样拖动后删除就不会错乱
        }
    });

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



    /**
     * 生成日历数据
     */

    private ArrayList<ArrayList<DateBean>> days(String sDate, String eDate) {
        ArrayList<ArrayList<DateBean>> databean=new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            //日期格式化
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatYYYYMM = new SimpleDateFormat("yyyy-MM");

            //起始日期
            Date startDate = new Date();
            calendar.setTime(startDate);

            //结束日期
            calendar.add(Calendar.MONTH, 100);
            Date endDate = new Date(calendar.getTimeInMillis());

            Log.d(TAG, "startDate:" + format.format(startDate) + "----------endDate:" + format.format(endDate));

            //格式化开始日期和结束日期为 yyyy-mm-dd格式
            String endDateStr = format.format(endDate);
            endDate = format.parse(endDateStr);

            String startDateStr = format.format(startDate);
            startDate = format.parse(startDateStr);

            calendar.setTime(startDate);

            Log.d(TAG, "startDateStr:" + startDateStr + "---------endDate:" + format.format(endDate));
            Log.d(TAG, "endDateStr:" + endDateStr + "---------endDate:" + format.format(endDate));

            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Calendar monthCalendar = Calendar.getInstance();


            //按月生成日历 每行7个 最多6行 42个
            //每一行有七个日期  日 一 二 三 四 五 六 的顺序
            for (; calendar.getTimeInMillis() <= endDate.getTime(); ) {
                ArrayList<DateBean> dateBeans = new ArrayList<>();
                //月份item
                DateBean monthDateBean = new DateBean();
                monthDateBean.setDate(calendar.getTime());
                monthDateBean.setMonthStr(formatYYYYMM.format(monthDateBean.getDate()));
                monthDateBean.setItemType(DateBean.item_type_month);
                dateBeans.add(monthDateBean);

                //获取一个月结束的日期和开始日期
                monthCalendar.setTime(calendar.getTime());
                monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
                Date startMonthDay = calendar.getTime();

                monthCalendar.add(Calendar.MONTH, 1);
                monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
                Date endMonthDay = monthCalendar.getTime();

                //重置为本月开始
                monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                Log.d(TAG, "月份的开始日期:" + format.format(startMonthDay) + "---------结束日期:" + format.format(endMonthDay));
                for (; monthCalendar.getTimeInMillis() <= endMonthDay.getTime(); ) {
                    //生成单个月的日历

                    //处理一个月开始的第一天
                    if (monthCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
                        //看某个月第一天是周几
                        int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                        switch (weekDay) {
                            case 1:
                                //周日
                                break;
                            case 2:
                                //周一
                                addDatePlaceholder(dateBeans, 1, monthDateBean.getMonthStr());
                                break;
                            case 3:
                                //周二
                                addDatePlaceholder(dateBeans, 2, monthDateBean.getMonthStr());
                                break;
                            case 4:
                                //周三
                                addDatePlaceholder(dateBeans, 3, monthDateBean.getMonthStr());
                                break;
                            case 5:
                                //周四
                                addDatePlaceholder(dateBeans, 4, monthDateBean.getMonthStr());
                                break;
                            case 6:
                                addDatePlaceholder(dateBeans, 5, monthDateBean.getMonthStr());
                                //周五
                                break;
                            case 7:
                                addDatePlaceholder(dateBeans, 6, monthDateBean.getMonthStr());
                                //周六
                                break;
                        }
                    }

                    //生成某一天日期实体 日item
                    DateBean dateBean = new DateBean();
                    dateBean.setDate(monthCalendar.getTime());
                    dateBean.setDay(monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
                    dateBean.setMonthStr(monthDateBean.getMonthStr());
                    dateBeans.add(dateBean);

                    //处理一个月的最后一天
                    if (monthCalendar.getTimeInMillis() == endMonthDay.getTime()) {
                        //看某个月第一天是周几
                        int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                        switch (weekDay) {
                            case 1:
                                //周日
                                addDatePlaceholder(dateBeans, 6, monthDateBean.getMonthStr());
                                break;
                            case 2:
                                //周一
                                addDatePlaceholder(dateBeans, 5, monthDateBean.getMonthStr());
                                break;
                            case 3:
                                //周二
                                addDatePlaceholder(dateBeans, 4, monthDateBean.getMonthStr());
                                break;
                            case 4:
                                //周三
                                addDatePlaceholder(dateBeans, 3, monthDateBean.getMonthStr());
                                break;
                            case 5:
                                //周四
                                addDatePlaceholder(dateBeans, 2, monthDateBean.getMonthStr());
                                break;
                            case 6:
                                addDatePlaceholder(dateBeans, 1, monthDateBean.getMonthStr());
                                //周5
                                break;
                        }
                    }

                    //天数加1
                    monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                databean.add(dateBeans);
                Log.d(TAG, "日期" + format.format(calendar.getTime()) + "----周几" + getWeekStr(calendar.get(Calendar.DAY_OF_WEEK) + ""));
                //月份加1
                calendar.add(Calendar.MONTH, 1);
            }

        } catch (Exception ex) {

        }

        return databean;
    }


    private String getWeekStr(String mWay) {
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mWay;
    }


    //添加空的日期占位
    private void addDatePlaceholder(List<DateBean> dateBeans, int count, String monthStr) {
        for (int i = 0; i < count; i++) {
            DateBean dateBean = new DateBean();
            dateBean.setMonthStr(monthStr);
            dateBeans.add(dateBean);
        }
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

    public String bitmapToString(Bitmap bitmap) {
        //将bitmap转换为uri
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        return img_path;
    }


}
