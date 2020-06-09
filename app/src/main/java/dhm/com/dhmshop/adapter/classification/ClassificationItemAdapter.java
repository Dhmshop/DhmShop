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

public class ClassificationItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<String> titles;
    ArrayList<Integer> images;

    public ClassificationItemAdapter(Context context, ArrayList<String> titles, ArrayList<Integer> images) {
        this.context = context;
        this.titles = titles;
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.classificationitem, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }


    @Override
    public int getItemCount() {

        return titles.size()+images.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;
        public ImageView iv;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
