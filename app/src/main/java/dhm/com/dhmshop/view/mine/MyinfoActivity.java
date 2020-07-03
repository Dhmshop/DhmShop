package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import dhm.com.dhmshop.entity.GetUserInfo;
import dhm.com.dhmshop.entity.HeaderIMage;
import dhm.com.dhmshop.entity.Province;
import dhm.com.dhmshop.entity.UserLogin;
import dhm.com.dhmshop.utils.BitmapUtil;
import dhm.com.dhmshop.utils.FileUtils;
import dhm.com.dhmshop.utils.GetJsonDataUtil;
import dhm.com.dhmshop.utils.HeaderImageCallback;
import dhm.com.dhmshop.utils.PermissionUtils;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.utils.TakePhotoUtil;
import okhttp3.Call;

import static java.security.AccessController.getContext;

public class MyinfoActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.head_image)
    SimpleDraweeView headImage;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.text_sex)
    TextView textSex;
    @BindView(R.id.text_area)
    TextView textArea;
    private PressenterImpl pressenter;
    private ArrayList<Province> options1Items = new ArrayList<>(); //省
    private ArrayList<String> optionshItems = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private OptionsPickerView pvOptions;
    private List<String> options = new ArrayList<>();
    private String uid;

    private List<String> sexlist=new ArrayList<>();
    private static final int IMAGE_REQUEST_CODE = 12;
    private File file;
    private String fileResult;
    private Uri fileUri;

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_CAMERA:
                    OptionsPickerView pickerView = new OptionsPickerBuilder(MyinfoActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            switch (options1) {
                                case 0:
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if (Build.VERSION.SDK_INT >= 24) {
                                        file = new File(getApplicationContext().getExternalCacheDir(), "mystoreimg" + ".jpg");
                                        FileUtils.createFile(file);
                                        fileUri = FileProvider.getUriForFile(getApplicationContext(), Constant.Tag, file);
                                        fileResult = file.getAbsolutePath();
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    } else {
                                        file = new File(Constant.HeadPath, "mystoreimg" + ".jpg");
                                        FileUtils.createFile(file);
                                        Uri fileUri = Uri.fromFile(file);
                                        fileResult = fileUri.getPath();
                                    }

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                                    startActivityForResult(intent, Constant.REQUESTCODE1);
                                    break;
                                case 1:
                                    //相册
                                    //相册
                                    Intent albumIntent = new Intent(Intent.ACTION_PICK);
                                    albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                    startActivityForResult(albumIntent, Constant.IMAGE_REQUEST_CODE);
                                    break;
                                    default:
                            }
                        }
                    }).build();
                    pickerView.setPicker(options);
                    pickerView.show();
                    break;
                    default:
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_myinfo;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        getWindow().setStatusBarColor(Color.LTGRAY);
        ButterKnife.bind(this);
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        uid = SpUtils.getString(this, "uid");
        options.clear();
        options.add("拍照");
        options.add("相册");
        initJsonData();
    }

    @Override
    protected void initData() {
        sexlist.clear();
        sexlist.add("男");
        sexlist.add("女");
        sexlist.add("保密");
        Map<String,String> map=new HashMap<>();
        map.put("token", Constant.TOKEN);
        map.put("uid",uid);
        pressenter.sendMessage(this, Constant.GetUserInfo,map, GetUserInfo.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @OnClick({R.id.back, R.id.headimage, R.id.nack_name, R.id.sex, R.id.area, R.id.edit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.headimage:
                PermissionUtils.requestPermission(MyinfoActivity.this, PermissionUtils.CODE_CAMERA,
                        mPermissionGrant);

                break;
            case R.id.nack_name:
                break;
            case R.id.sex:

                //选择地址
                pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        textSex.setText(sexlist.get(options1));
                    }
                }).build();

                pvOptions.setPicker(sexlist);//三级选择器
                pvOptions.show();
                break;
            case R.id.area:

                //选择地址
                pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        textArea.setText(options1Items.get(options1).getPickerViewText()
                                + options2Items.get(options1).get(options2)
                                + options3Items.get(options1).get(options2).get(options3));
                    }
                }).build();

                pvOptions.setPicker(optionshItems, options2Items, options3Items);//三级选择器
                pvOptions.show();
                break;
            case R.id.edit_login:
                Map<String,String> map=new HashMap<>();
                String sex = textSex.getText().toString();
                map.put("token",Constant.TOKEN);
                map.put("uid",uid);
                if (sex!=null&&!sex.equals("")){
                    switch (sex){
                        case "男":
                            map.put("sex","1");
                            break;
                        case "女":
                            map.put("sex","2");
                            break;
                        case "保密":
                            map.put("sex","0");
                            break;
                        default:
                    }

                }
                String address = textArea.getText().toString();
                if (address!=null&&!address.equals("")){
                    map.put("address", address);
                }
                if (map.size()>1){
                    pressenter.sendMessage(MyinfoActivity.this,Constant.ChangeUserInfo,map, Bean.class);
                }


                break;
                default:
        }
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof GetUserInfo){
            GetUserInfo userLogin= (GetUserInfo) data;
            if (userLogin.getCode()==1){
                headImage.setImageURI(Constant.PATH+userLogin.getData().getHeadsmall());
                nickname.setText(userLogin.getData().getUser_nickname());
                textArea.setText(userLogin.getData().getAddress());
                switch (userLogin.getData().getSex()){
                    case 0:
                        textSex.setText("保密");
                        break;
                    case 1:
                        textSex.setText("男");
                        break;
                    case 2:
                        textSex.setText("女");
                        break;
                    default:
                }

            }


        }else if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getCode()==1){
                finish();
            }
        }
    }

    @Override
    public void fail(String error) {

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == Constant.REQUESTCODE && resultCode == RESULT_OK) {

            Bitmap bitmap = BitmapUtil.getScaleBitmap(fileResult,0,0);
            String url = TakePhotoUtil.saveTodisk(bitmap, Constant.HeadPath);
            if (!TextUtils.isEmpty(url)) {
                //图片的URl
                headImage.setImageBitmap(bitmap);
                uploadImage(url);
            } else {
                Toast.makeText(this, "拍照错误", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            //尺寸压缩 根据控件
            Bitmap bitmap = BitmapUtil.getScaleBitmap(picturePath,0,0);
            String url = TakePhotoUtil.saveTodisk(bitmap, Constant.HeadPath);
            if (!TextUtils.isEmpty(url)) {
                //图片的URl
                headImage.setImageBitmap(bitmap);
                uploadImage(url);
            }else {
                Toast.makeText(this, "选择出错", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }


    private void uploadImage(final String url) {
        OkHttpUtils.post().url(Constant.PATH + Constant.ChangeHeadImage)
                .addFile("image", url, new File(url))
                .addParams("uid", uid)
                .addParams("token", Constant.TOKEN)
                .build().execute(new HeaderImageCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(HeaderIMage response, int id) {
                if (response != null && response.getCode() == 1) {
                    Toast.makeText(MyinfoActivity.this,  response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void initJsonData() {
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<Province> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            optionshItems.add(jsonBean.get(i).getName());

            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三级）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public ArrayList<Province> parseData(String result) {//Gson 解析
        ArrayList<Province> detail = new ArrayList<>();
        detail.clear();
        Gson gson = new GsonBuilder().setLenient().create();
        detail = gson.fromJson(result, new TypeToken<List<Province>>() {
        }.getType());

        return detail;
    }



}
