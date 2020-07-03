package dhm.com.dhmshop.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.entity.GetOneCategory;

/**
 * Created by admin
 * 2019/4/29
 * 所有订单适配器
 */
public class ShowITypeAdapter extends RecyclerView.Adapter<ShowITypeAdapter.ViewHolder> {

    private List<GetOneCategory.DataBean> imageList;
    private Context context;


    public ShowITypeAdapter(Context context) {
        this.imageList = new ArrayList<>();
        this.context = context;
    }


    public void setImageList(List<GetOneCategory.DataBean> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view= LayoutInflater.from(context).inflate(R.layout.iten_shoptype, viewGroup,false);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.recy.setVisibility(View.VISIBLE);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);


        ShowIChildAdapter showIChildAdapter=new ShowIChildAdapter(context);


        if (i==0){
            viewHolder.line.setVisibility(View.GONE);
        }else {
            viewHolder.line.setVisibility(View.VISIBLE);
        }

        showIChildAdapter.setImageList(imageList.get(i).getChildCategory().getData());
        viewHolder.radiobutton.setText(imageList.get(i).getName());

        if (imageList.get(i).getChecked().equals("0")){
            viewHolder.radiobutton.setChecked(false);
        }else if (imageList.get(i).getChecked().equals("2")){
            viewHolder.radiobutton.setChecked(true);
        }

        viewHolder.radiobutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isPressed()) {
                    onClick.item(i);
                }
            }
        });


        showIChildAdapter.setdeleteClick(new ShowIChildAdapter.OnClick() {
            @Override
            public void item(int posttion) {
                onIntentClick.item(i,posttion);
            }
        });



        viewHolder.recy.setLayoutManager(manager);
        viewHolder.recy.setAdapter(showIChildAdapter);


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public RadioButton radiobutton;
        public RecyclerView recy;
        public TextView line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radiobutton=itemView.findViewById(R.id.btn_camera_pop_cancel);
            line=itemView.findViewById(R.id.line);
            recy=itemView.findViewById(R.id.recy);
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
        void item(int posttion,int childposttion);
    }



}

