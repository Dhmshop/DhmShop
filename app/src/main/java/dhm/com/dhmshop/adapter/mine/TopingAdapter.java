package dhm.com.dhmshop.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.entity.Myorder;
import dhm.com.dhmshop.utils.CustomJiaJian;

/**
 * Created by admin
 * 2019/4/29
 * 所有订单适配器
 */
public class TopingAdapter extends RecyclerView.Adapter<TopingAdapter.ViewHolder> {

    private List<Myorder.DataBean> shopList;
    private Context context;


    public TopingAdapter(Context context) {
        this.shopList = new ArrayList<>();
        this.context = context;
    }


    public void setShopList(List<Myorder.DataBean> shopList) {
        this.shopList = shopList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view= LayoutInflater.from(context).inflate(R.layout.item_toping, viewGroup,false);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.image.setImageURI(Constant.PATH+shopList.get(i).getGoods_img());
        viewHolder.name.setText(shopList.get(i).getGoods_name());

    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView image;
        public TextView name;
        public Button toping;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            toping=itemView.findViewById(R.id.toping);
        }
    }

    private OnClick onClick;
    public void setdeleteClick(OnClick onClick) {
        this.onClick = onClick;
    }
    public interface OnClick{
        void item(int posttion, boolean checked);
    }

    private OnIntentClick onIntentClick;
    public void setaddClick(OnIntentClick onClick) {
        this.onIntentClick = onClick;
    }
    public interface OnIntentClick{
        void item(int posttion, int num);
    }

    private OnShopClick onshopClick;
    public void setaddClick(OnShopClick onClick) {
        this.onshopClick= onClick;
    }
    public interface OnShopClick{
        void item(int posttion);
    }



}

