package dhm.com.dhmshop.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;

/**
 * Created by admin
 * 2019/4/29
 * 所有订单适配器
 */
public class ParamsAdapter extends RecyclerView.Adapter<ParamsAdapter.ViewHolder> {





    List<String> values;
    List<String> name;
    private Context context;




    public ParamsAdapter( Context context) {
        values=new ArrayList<>();
        name=new ArrayList<>();
        this.context = context;
    }

    public void showImage(List<String> values,List<String> name) {
        this.values=values;
        this.name=name;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view= LayoutInflater.from(context).inflate(R.layout.shop_param, null);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (name.size()==i){
            viewHolder.name.setText("");
            viewHolder.value.setText("");
            viewHolder.name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    onAddNameClick.item(i,viewHolder.name.getText().toString());
                }
            });
            viewHolder.value.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    onAddValueClick.item(i,viewHolder.value.getText().toString());
                }
            });
        }else {
            viewHolder.name.setText(name.get(i));
            viewHolder.value.setText(values.get(i));
            viewHolder.name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    //参数名改变的时候
                    onClick.item(i,viewHolder.name.getText().toString());
                }
            });
            viewHolder.value.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    //参数值改变的时候
                    onIntentClick.item(i,viewHolder.name.getText().toString());
                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return name.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            value=itemView.findViewById(R.id.value);
        }
    }

    private OnClick onClick;
    public void setdeleteClick(OnClick onClick) {
        this.onClick = onClick;
    }
    public interface OnClick{
        void item(int posttion,String value);
    }

    private OnIntentClick onIntentClick;
    public void setaddClick(OnIntentClick onClick) {
        this.onIntentClick = onClick;
    }
    public interface OnIntentClick{
        void item(int posttion,String value);
    }

    private OnAddValueClick onAddValueClick;
    public void setOnAddValueClick(OnAddValueClick onClick) {
        this.onAddValueClick = onClick;
    }
    public interface OnAddValueClick{
        void item(int posttion,String value);
    }

    private OnAddNameClick onAddNameClick;
    public void setOnAddNameClick(OnAddNameClick onClick) {
        this.onAddNameClick = onClick;
    }
    public interface OnAddNameClick{
        void item(int posttion,String value);
    }



}

