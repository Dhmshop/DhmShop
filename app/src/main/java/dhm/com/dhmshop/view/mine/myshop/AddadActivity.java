package dhm.com.dhmshop.view.mine.myshop;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import dhm.com.dhmshop.entity.GetAd;
import dhm.com.dhmshop.entity.HeaderIMage;
import dhm.com.dhmshop.utils.BitmapUtil;
import dhm.com.dhmshop.utils.HeaderImageCallback;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.utils.TakePhotoUtil;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.Call;

public class AddadActivity extends BaseActiity implements LoginContract.IView {


    @BindView(R.id.image_one)
    SimpleDraweeView imageOne;
    @BindView(R.id.image_two)
    SimpleDraweeView imageTwo;
    @BindView(R.id.image_three)
    SimpleDraweeView imageThree;

    private ArrayList<String> mSelectPath;
    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;
    private PressenterImpl pressenter;
    private String uid;
    private String shop_id;
    private List<SimpleDraweeView> list;
    private GetAd getAd;

    private String urlone="";
    private String urltwo="";
    private String urlthree="";
    private int position=0;

    @Override
    protected int getLayout() {
        return R.layout.activity_addad;
    }

    @Override
    protected void initView() {
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(this, "uid");
        shop_id = SpUtils.getString(this, "shop_id");

        getAd();

        list = new ArrayList<>();
        list.add(imageOne);
        list.add(imageTwo);
        list.add(imageThree);

    }

    private void getAd() {
        Map<String,String> map=new HashMap<>();
        map.put("token", Constant.TOKEN);
        map.put("uid",uid);
        map.put("shop_id",shop_id);
        pressenter.sendMessage(this,Constant.ShopAds,map, GetAd.class);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void requesta(Object data) {
        ButterKnife.bind(this);
        if (data instanceof GetAd){
            getAd = (GetAd) data;
            if (getAd.getCode()==1){
                for (int i = 0; i< getAd.getData().size()&&i<3; i++){
                    list.get(i).setImageURI(Constant.PATH+ getAd.getData().get(i).getAd_images());
                }
            }


        }else if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getCode()==1){
                if (bean.getMessage().equals("更新成功")){
                    finish();
                }
                if (bean.getMessage().equals("删除成功")){
                    getAd();
                }
            }
        }


    }

    @Override
    public void fail(String error) {

    }

    @OnClick({R.id.back, R.id.del_one,  R.id.image_one,  R.id.image_three,  R.id.image_two, R.id.del_two, R.id.del_three, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.image_one:
                if (urlone.equals("")&&getAd.getData().isEmpty()&&getAd.getData().size()==0){
                    position=1;
                    pickImage();
                    return;
                }else {
                    Toast.makeText(this, "请先删除", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.image_two:
                if (urltwo.equals("")&&getAd.getData().isEmpty()&&getAd.getData().size()<2){
                    position=1;
                    pickImage();
                    return;
                }else {
                    Toast.makeText(this, "请先删除", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.image_three:
                if (urlthree.equals("")&&getAd.getData().isEmpty()&&getAd.getData().size()<3){
                    position=1;
                    pickImage();
                    return;
                }else {
                    Toast.makeText(this, "请先删除", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.del_one:
                if (!getAd.getData().isEmpty()){
                    if (getAd.getData().get(0)!=null){
                        if (getAd.getData().get(0).getAd_images()!=null){
                            delAd(getAd.getData().get(0).getId()+"");
                        }
                    }
                }else if (!urlone.equals("")){
                    urlone="";
                    imageOne.setImageResource(R.mipmap.shopadd);
                } else{
                    Toast.makeText(this, "您还没有上传", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.del_two:
                if (!getAd.getData().isEmpty()){
                    if (getAd.getData().get(1)!=null){
                        if (getAd.getData().get(1).getAd_images()!=null){
                            delAd(getAd.getData().get(1).getId()+"");
                        }
                    }
                }else if (!urltwo.equals("")){
                    urltwo="";
                    imageTwo.setImageResource(R.mipmap.shopadd);
                } else{
                    Toast.makeText(this, "您还没有上传", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.del_three:
                if (!getAd.getData().isEmpty()){
                    if (getAd.getData().get(2)!=null){
                        if (getAd.getData().get(2).getAd_images()!=null){
                            delAd(getAd.getData().get(2).getId()+"");
                        }
                    }
                }else if (!urlthree.equals("")){
                    urlthree="";
                    imageThree.setImageResource(R.mipmap.shopadd);
                } else{
                    Toast.makeText(this, "您还没有上传", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sure:
                if (!urlone.equals("")){
                    uploadImage(urlone);
                }
                if (!urltwo.equals("")){
                    uploadImage(urltwo);
                }
                if (!urlthree.equals("")){
                    uploadImage(urlthree);
                }
                break;
            default:
        }
    }


    private void uploadImage(final String url) {
        OkHttpUtils.post().url(Constant.PATH + Constant.AddShopAds)
                .addFile("image", url, new File(url))
                .addParams("uid", uid)
                .addParams("shop_id", shop_id)
                .addParams("token", Constant.TOKEN)
                .build().execute(new HeaderImageCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(HeaderIMage response, int id) {
                if (response != null && response.getCode() == 1) {
                    Toast.makeText(AddadActivity.this,  response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void delAd(String aid) {
        Map<String,String> map=new HashMap<>();
        map.put("token",Constant.TOKEN);
        map.put("uid",uid);
        map.put("shop_id",shop_id);
        map.put("ad_id",aid);
        pressenter.sendMessage(AddadActivity.this,Constant.DelShopAds,map, Bean.class);
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else {
            MultiImageSelector selector = MultiImageSelector.create(AddadActivity.this);
            selector.showCamera(true);
            selector.count(1);
            selector.single();
            selector.origin(mSelectPath);
            selector.start(AddadActivity.this, REQUEST_IMAGE);
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
                            ActivityCompat.requestPermissions(AddadActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                Cursor cursor = getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                String picturePath = cursor.getString(columnIndex);
//                cursor.close();
//                //尺寸压缩 根据控件


                Bitmap bitmap = null;

                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for(String p: mSelectPath){
                    sb.append(p);
                    sb.append("\n");
                    bitmap = BitmapUtil.getScaleBitmap(p, 0, 0);
                }

                if (!TextUtils.isEmpty(TakePhotoUtil.saveTodisk(bitmap, Constant.HeadPath))) {
                    //图片的URl
                    switch (position){
                        case 1:
                            urlone = TakePhotoUtil.saveTodisk(bitmap, Constant.HeadPath);
                            imageOne.setImageBitmap(bitmap);
                            break;
                        case 2:
                            urltwo = TakePhotoUtil.saveTodisk(bitmap, Constant.HeadPath);
                            imageTwo.setImageBitmap(bitmap);
                            break;
                        case 3:
                            urlthree = TakePhotoUtil.saveTodisk(bitmap, Constant.HeadPath);
                            imageThree.setImageBitmap(bitmap);
                            break;
                            default:
                    }
                }else {
                    Toast.makeText(this, "选择出错", Toast.LENGTH_SHORT).show();
                }



            }
        }
    }


}
