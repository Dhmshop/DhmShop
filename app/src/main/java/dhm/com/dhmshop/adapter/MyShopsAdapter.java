package dhm.com.dhmshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.entity.GetMyShopinfo;
import dhm.com.dhmshop.entity.MyShops;
import dhm.com.dhmshop.utils.CustomJiaJian;

/**
 * Created by admin
 * 2019/4/29
 * 所有订单适配器
 */
public class MyShopsAdapter extends RecyclerView.Adapter<MyShopsAdapter.ViewHolder> {

    private List<MyShops.DataBean> shopList;
    private Context context;


    public MyShopsAdapter(Context context) {
        this.shopList = new ArrayList<>();
        this.context = context;
    }


    public void setShopList(List<MyShops.DataBean> shopList) {
        this.shopList = shopList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view= LayoutInflater.from(context).inflate(R.layout.recy_shop, viewGroup,false);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.image.setImageURI(Constant.PATH+shopList.get(i).getPicture());
        viewHolder.name.setText(shopList.get(i).getGoods_name());
        viewHolder.price.setText(shopList.get(i).getPrice()+"");
        viewHolder.num.setEditText(shopList.get(i).getNumber());
        viewHolder.param.setText(shopList.get(i).getGoods_formats());
        viewHolder.check.setText(shopList.get(i).getShop_name());
        viewHolder.check.setChecked(shopList.get(i).isChecked());
        viewHolder.shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onshopClick.item(i);
            }
        });
        viewHolder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isPressed()) {
                    onClick.item(i,viewHolder.check.isChecked());
                }
            }
        });

        viewHolder.num.setCustomListener(new CustomJiaJian.CustomListener() {
            @Override
            public void jiajian(int count) {
                onIntentClick.item(i,count);
            }

            @Override
            public void shuRuZhi(int count) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView image;
        public TextView name;
        public TextView price;
        public CustomJiaJian num;
        public TextView param;
        public LinearLayout shops;
        public CheckBox check;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            param=itemView.findViewById(R.id.param);
            shops=itemView.findViewById(R.id.shops);
            num=itemView.findViewById(R.id.num);
            check=itemView.findViewById(R.id.check);
        }
    }

    private OnClick onClick;
    public void setdeleteClick(OnClick onClick) {
        this.onClick = onClick;
    }
    public interface OnClick{
        void item(int posttion,boolean checked);
    }

    private OnIntentClick onIntentClick;
    public void setaddClick(OnIntentClick onClick) {
        this.onIntentClick = onClick;
    }
    public interface OnIntentClick{
        void item(int posttion,int num);
    }

    private OnShopClick onshopClick;
    public void setaddClick(OnShopClick onClick) {
        this.onshopClick= onClick;
    }
    public interface OnShopClick{
        void item(int posttion);
    }



}

