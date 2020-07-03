package dhm.com.dhmshop.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.entity.MyShops;
import dhm.com.dhmshop.entity.Myorder;
import dhm.com.dhmshop.utils.CustomJiaJian;

/**
 * Created by admin
 * 2019/4/29
 * 所有订单适配器
 */
public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private List<Myorder.DataBean> shopList;
    private Context context;
    //        type 0:全部 1:代付款 2.待发货 3.待收货   4.待评价
    private String type;


    public MyOrderAdapter(Context context,String type) {
        this.shopList = new ArrayList<>();
        this.context = context;
        this.type=type;
    }


    public void setShopList(List<Myorder.DataBean> shopList) {
        this.shopList = shopList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view= LayoutInflater.from(context).inflate(R.layout.item_myorder, viewGroup,false);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        switch (type){
            case "0":
                //全部
                break;
            case "1":
                //代付款
                viewHolder.tofu.setVisibility(View.VISIBLE);
                viewHolder.toshou.setVisibility(View.GONE);
                viewHolder.tofa.setVisibility(View.GONE);
                viewHolder.toping.setVisibility(View.GONE);
                viewHolder.xiu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.item(i);
                    }
                });


                break;
            case "2":
                //待发货
                viewHolder.tofu.setVisibility(View.GONE);
                viewHolder.toshou.setVisibility(View.GONE);
                viewHolder.tofa.setVisibility(View.VISIBLE);
                viewHolder.toping.setVisibility(View.GONE);
                viewHolder.cui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.item(i);
                    }
                });
                break;
            case "3":
                //待收货
                viewHolder.tofu.setVisibility(View.GONE);
                viewHolder.toshou.setVisibility(View.VISIBLE);
                viewHolder.tofa.setVisibility(View.GONE);
                viewHolder.toping.setVisibility(View.GONE);
                viewHolder.cha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.item(i);
                    }
                });
                viewHolder.sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onshopClick.item(i);
                    }
                });


                break;
            case "4":
                //待评价
                viewHolder.tofu.setVisibility(View.GONE);
                viewHolder.toshou.setVisibility(View.GONE);
                viewHolder.tofa.setVisibility(View.GONE);
                viewHolder.toping.setVisibility(View.VISIBLE);
                viewHolder.liu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.item(i);
                    }
                });
                viewHolder.ping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onshopClick.item(i);
                    }
                });

                break;
                default:
        }


        viewHolder.image.setImageURI(Constant.PATH+shopList.get(i).getGoods_img());
        viewHolder.name.setText(shopList.get(i).getGoods_name());
        viewHolder.param.setText(shopList.get(i).getShop_name());
        viewHolder.price.setText("￥"+shopList.get(i).getPrice());
        viewHolder.num.setText("x"+shopList.get(i).getCount());
        viewHolder.nameshop.setText(shopList.get(i).getShop_name());

    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView image;
        public TextView name;
        public TextView price;
        public TextView num;
        public TextView param;
        public LinearLayout shops;
        public CheckBox check;
        public CheckBox nameshop;

        RelativeLayout toshou;
        RelativeLayout tofa;
        RelativeLayout tofu;
        RelativeLayout toping;
        TextView cha;
        TextView sure;
        TextView more_shou;
        TextView cui;
        TextView buys;
        TextView xiu;
        TextView topay;
        TextView more_fu;
        TextView liu;
        TextView ping;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            param=itemView.findViewById(R.id.param);
            shops=itemView.findViewById(R.id.shops);
            num=itemView.findViewById(R.id.num);
            check=itemView.findViewById(R.id.check);
            toshou=itemView.findViewById(R.id.toshou);
            cha=itemView.findViewById(R.id.cha);
            sure=itemView.findViewById(R.id.sure);
            more_shou=itemView.findViewById(R.id.more_shou);
            tofa=itemView.findViewById(R.id.tofa);
            cui=itemView.findViewById(R.id.cui);
            buys=itemView.findViewById(R.id.buys);
            tofu=itemView.findViewById(R.id.tofu);
            xiu=itemView.findViewById(R.id.xiu);
            topay=itemView.findViewById(R.id.topay);
            more_fu=itemView.findViewById(R.id.more_fu);
            toping=itemView.findViewById(R.id.toping);
            liu=itemView.findViewById(R.id.liu);
            ping=itemView.findViewById(R.id.ping);
            nameshop=itemView.findViewById(R.id.nameshop);
        }
    }

    private OnClick onClick;
    public void setdeleteClick(OnClick onClick) {
        this.onClick = onClick;
    }
    public interface OnClick{
        void item(int posttion);
    }

    private OnIntentClick onIntentClick;
    public void setaddClick(OnIntentClick onClick) {
        this.onIntentClick = onClick;
    }
    public interface OnIntentClick{
        void item(int posttion);
    }

    private OnShopClick onshopClick;
    public void setaddClick(OnShopClick onClick) {
        this.onshopClick= onClick;
    }
    public interface OnShopClick{
        void item(int posttion);
    }



}

