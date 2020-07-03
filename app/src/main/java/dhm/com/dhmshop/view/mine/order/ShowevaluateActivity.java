package dhm.com.dhmshop.view.mine.order;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.mine.ShowImageAdapter;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.Bean;
import dhm.com.dhmshop.entity.GetGoodsDetail;
import dhm.com.dhmshop.entity.Result;
import dhm.com.dhmshop.entity.ResultDetailCallback;
import dhm.com.dhmshop.utils.BitmapUtil;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.view.main.MainActivity;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.Call;

public class ShowevaluateActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.star_one)
    ImageView starOne;
    @BindView(R.id.star_two)
    ImageView starTwo;
    @BindView(R.id.star_three)
    ImageView starThree;
    @BindView(R.id.star_four)
    ImageView starFour;
    @BindView(R.id.star_five)
    ImageView starFive;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.tishi)
    EditText tishi;
    private String order_sn;
    private String goods_id;
    private ShowImageAdapter showImageAdapter;
    private String uid;
    private PressenterImpl pressenter;
    private View popView;


    @Override
    protected int getLayout() {
        return R.layout.activity_showevaluate;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        order_sn = intent.getStringExtra("order_sn");
        goods_id = intent.getStringExtra("goods_id");
        uid = SpUtils.getString(this, "uid");
        pressenter=new PressenterImpl();
        pressenter.attachView(this);

    }

    @Override
    protected void initData() {
        showPopwindow();
        showImageAdapter=new ShowImageAdapter(images,this);
        showImageAdapter.setaddClick(new ShowImageAdapter.OnIntentClick() {
            @Override
            public void item() {
                pickImage();
            }
        });

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(gridLayoutManager);
        recy.setAdapter(showImageAdapter);

        Map<String,String> map=new HashMap<>();
        map.put("token",Constant.TOKEN);
        map.put("uid",uid);
        map.put("gid",goods_id);
        pressenter.sendMessage(this,Constant.OneGoods,map, GetGoodsDetail.class);

    }

    private int star = 0;

    @OnClick({R.id.back, R.id.star_one, R.id.star_two, R.id.star_three, R.id.star_four, R.id.star_five, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.star_one:
                star = 1;
                starOne.setImageResource(R.mipmap.ping_star);
                starTwo.setImageResource(R.mipmap.ping_starn);
                starThree.setImageResource(R.mipmap.ping_starn);
                starFour.setImageResource(R.mipmap.ping_starn);
                starFive.setImageResource(R.mipmap.ping_starn);
                break;
            case R.id.star_two:
                star = 2;
                starOne.setImageResource(R.mipmap.ping_star);
                starTwo.setImageResource(R.mipmap.ping_star);
                starThree.setImageResource(R.mipmap.ping_starn);
                starFour.setImageResource(R.mipmap.ping_starn);
                starFive.setImageResource(R.mipmap.ping_starn);
                break;
            case R.id.star_three:
                star = 3;
                starOne.setImageResource(R.mipmap.ping_star);
                starTwo.setImageResource(R.mipmap.ping_star);
                starThree.setImageResource(R.mipmap.ping_star);
                starFour.setImageResource(R.mipmap.ping_starn);
                starFive.setImageResource(R.mipmap.ping_starn);
                break;
            case R.id.star_four:
                star = 4;
                starOne.setImageResource(R.mipmap.ping_star);
                starTwo.setImageResource(R.mipmap.ping_star);
                starThree.setImageResource(R.mipmap.ping_star);
                starFour.setImageResource(R.mipmap.ping_star);
                starFive.setImageResource(R.mipmap.ping_starn);
                break;
            case R.id.star_five:
                star = 5;
                starOne.setImageResource(R.mipmap.ping_star);
                starTwo.setImageResource(R.mipmap.ping_star);
                starThree.setImageResource(R.mipmap.ping_star);
                starFour.setImageResource(R.mipmap.ping_star);
                starFive.setImageResource(R.mipmap.ping_star);
                break;
            case R.id.commit:
                String content = tishi.getText().toString();


                Map<String, File> files = new HashMap<>();
                if (images.size()==0){
                    Map<String,String> map=new HashMap<>();
                    map.put("token",Constant.TOKEN);
                    map.put("order_sn",order_sn);
                    map.put("goods_id",goods_id);
                    map.put("star",star+"");
                    map.put("content",content);



                }else {
                    try {
                        for (int i=0;i<images.size();i++){
                            files.put(bitmapToString(images.get(i)), new File(bitmapToString(images.get(i))));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    OkHttpUtils.post().url(Constant.PATH + Constant.AddGoodsComments)
                            .addParams("token", Constant.TOKEN)
                            .addParams("uid", uid)
                            .addParams("order_sn", order_sn)
                            .addParams("goods_id", goods_id)
                            .addParams("star", star+"")
                            .addParams("content", content)
                            .files("image[]",files)
                            .build().execute(new ResultDetailCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ShowevaluateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Result response, int id) {
                            Toast.makeText(ShowevaluateActivity.this, response.getMessage(), Toast.LENGTH_LONG).show();
                            if (response != null && response.getCode()==1) {
                                finish();
                            }
                        }
                    });

                }


                break;
            default:
        }
    }
    private ArrayList<Bitmap> images = new ArrayList<>();
    private ArrayList<String> mSelectPath;
    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else {

                int maxNum = 6;
                MultiImageSelector selector = MultiImageSelector.create(ShowevaluateActivity.this);
                selector.showCamera(true);
                selector.count(maxNum);
                selector.multi();
                selector.origin(mSelectPath);
                selector.start(ShowevaluateActivity.this, REQUEST_IMAGE);
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
                            ActivityCompat.requestPermissions(ShowevaluateActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                images.clear();
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for(String p: mSelectPath){
                    sb.append(p);
                    sb.append("\n");
                    Bitmap bitmap = BitmapUtil.getScaleBitmap(p, 0, 0);
                    images.add(bitmap);
                }
                showImageAdapter.showImage(images);
            }
        }
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

    @Override
    public void requesta(Object data) {
        if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getCode()==1){
                finish();
            }
        }else if (data instanceof GetGoodsDetail){
            GetGoodsDetail getGoodsDetail= (GetGoodsDetail) data;
            image.setImageURI(Constant.PATH+getGoodsDetail.getData().getGoods_images_thumb());
            name.setText(getGoodsDetail.getData().getGoods_name());
        }
    }

    @Override
    public void fail(String error) {

    }
    private PopupWindow popWindow;

    /**
     * 防止误触退出
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setBackgroundAlpha(0.5f);
            popWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void showPopwindow() {
        popView = View.inflate(this, R.layout.dialog_finish, null);
        TextView cancle = popView.findViewById(R.id.cancle);
        TextView sure = popView.findViewById(R.id.sure);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpUtils.clear(ShowevaluateActivity.this);
                Intent intent = new Intent(ShowevaluateActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                popWindow.dismiss();
                finish();
            }
        });

        popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
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

}
