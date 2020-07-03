package dhm.com.dhmshop.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.entity.UserAddr;

/**
 * Created by admin
 * 2019/4/29
 * 所有订单适配器
 */
public class ShowAddrAdapter extends RecyclerView.Adapter<ShowAddrAdapter.ViewHolder> {

    private List<UserAddr.DataBean> getReply;

    private Context context;


    public ShowAddrAdapter(Context context) {
         getReply = new ArrayList<>();
        this.context = context;
    }

    public void showImage(List<UserAddr.DataBean> getReply) {
        this.getReply = getReply;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view= LayoutInflater.from(context).inflate(R.layout.recy_address, null);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(getReply.get(i).getUser_name());
        viewHolder.phone.setText(getReply.get(i).getMobile());
        viewHolder.addr.setText(getReply.get(i).getProvince()+getReply.get(i).getCity()+getReply.get(i).getArea()+getReply.get(i).getAddress());

        if (getReply.get(i).getTag().equals("默认")){
            viewHolder.def.setVisibility(View.VISIBLE);
        }else {
            viewHolder.def.setVisibility(View.GONE);
        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onIntentClick.item(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getReply.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView phone;
        private TextView addr;
        private TextView def;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);
            addr=itemView.findViewById(R.id.addr);
            def=itemView.findViewById(R.id.def);
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
        void item(int postion);
    }



}

