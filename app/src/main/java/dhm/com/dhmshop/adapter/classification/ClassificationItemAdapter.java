package dhm.com.dhmshop.adapter.classification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Ref;
import java.util.ArrayList;

import dhm.com.dhmshop.R;

public class ClassificationItemAdapter extends RecyclerView.Adapter {
    private Context context;
    ArrayList<String> titles;


    public ClassificationItemAdapter(Context context, ArrayList<String> titles) {
        this.context = context;
        this.titles = titles;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.classificationitem, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.tv.setText(titles.get(i));

    }


    @Override
    public int getItemCount() {

        return titles.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
