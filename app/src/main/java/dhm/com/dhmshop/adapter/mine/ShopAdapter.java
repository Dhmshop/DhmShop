package dhm.com.dhmshop.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.entity.GetMyShopinfo;
import dhm.com.dhmshop.entity.GetOneCategory;

/**
 * Created by admin
 * 2019/4/29
 * 所有订单适配器
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private List<GetMyShopinfo.DataBean> shopList;
    private Context context;


    public ShopAdapter(Context context) {
        this.shopList = new ArrayList<>();
        this.context = context;
    }


    public void setShopList(List<GetMyShopinfo.DataBean> shopList) {
        this.shopList = shopList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view= LayoutInflater.from(context).inflate(R.layout.item_shop, viewGroup,false);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.image.setImageURI(Constant.PATH+shopList.get(i).getGoods_images_thumb());
        viewHolder.name.setText(shopList.get(i).getGoods_name());
        viewHolder.money.setText(shopList.get(i).getExpress_price()+"");
        viewHolder.pay.setText(shopList.get(i).getSale_num()+"人付款");
        if (isdel){
            viewHolder.del.setVisibility(View.VISIBLE);
        }else {
            viewHolder.del.setVisibility(View.GONE);
        }
        if (isedit){
            viewHolder.edit.setVisibility(View.VISIBLE);
        }else {
            viewHolder.edit.setVisibility(View.GONE);
        }
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneditClick.item(i);
            }
        });
        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ondelClick.item(i);
            }
        });


    }

    private boolean isdel=false;
    private boolean isedit=false;

    public boolean isIsdel() {
        return isdel;
    }

    public void setIsdel(boolean isdel) {
        this.isdel = isdel;
        notifyDataSetChanged();
    }

    public boolean isIsedit() {
        return isedit;
    }

    public void setIsedit(boolean isedit) {
        this.isedit = isedit;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView image;
        public TextView name;
        public TextView money;
        public TextView pay;
        public ImageView del;
        public ImageView edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            money=itemView.findViewById(R.id.money);
            pay=itemView.findViewById(R.id.pay);
            edit=itemView.findViewById(R.id.edit);
            del=itemView.findViewById(R.id.del);
        }
    }

    private OnClick ondelClick;
    public void setdeleteClick(OnClick onClick) {
        this.ondelClick = onClick;
    }
    public interface OnClick{
        void item(int posttion);
    }


    private OneditClick oneditClick;
    public void seteditClick(OneditClick onClick) {
        this.oneditClick = onClick;
    }
    public interface OneditClick{
        void item(int posttion);
    }

    private OnIntentClick onIntentClick;
    public void setaddClick(OnIntentClick onClick) {
        this.onIntentClick = onClick;
    }
    public interface OnIntentClick{
        void item(int posttion);
    }



}

