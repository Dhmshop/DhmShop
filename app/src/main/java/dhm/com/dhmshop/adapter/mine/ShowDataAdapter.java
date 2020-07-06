package dhm.com.dhmshop.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.utils.Calendar.CalendarList;
import dhm.com.dhmshop.utils.Calendar.DateBean;

/**
 * Created by admin
 * 2019/4/29
 * 所有订单适配器
 */
public class ShowDataAdapter extends RecyclerView.Adapter<ShowDataAdapter.ViewHolder> {


    private Context context;
    CalendarList.CalendarAdapter adapter;


    //开始时间
    private DateBean startDate;
    private int sposition_recy=0;

    private int sposition_calend=0;


    //结束时间
    private DateBean endDate;
    private int nposition_recy=0;

    private int nposition_calend=0;
    //选中监听
    CalendarList.OnDateSelected onDateSelected;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");


    private ArrayList<ArrayList<DateBean>> datebean;

    public ShowDataAdapter(Context context) {
        datebean=new ArrayList<>();
        this.context = context;
    }


    public void setDatebean(ArrayList<ArrayList<DateBean>> datebean) {
        this.datebean = datebean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view= LayoutInflater.from(context).inflate(R.layout.item_calendarlist, viewGroup,false);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.calendarList.setData(datebean.get(i));
        viewHolder.calendarList.setOnRecyclerviewItemClick(new CalendarList.OnRecyclerviewItemClick() {
            @Override
            public void onItemClick(DateBean dateBean,int position) {
                onClick(dateBean,i,position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return datebean.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public CalendarList calendarList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendarList=itemView.findViewById(R.id.calendarlist);
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
        void item(DateBean startDate,DateBean endDate);
    }




    private void onClick(DateBean dateBean,int i,int position) {
        //如果没有选中开始日期则此次操作选中开始日期
        if (startDate == null) {
            startDate = dateBean;
            dateBean.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);
            sposition_recy=i;
            sposition_calend=position;
        } else if (endDate == null) {
            //如果选中了开始日期但没有选中结束日期，本次操作选中结束日期

            //如果当前点击的结束日期跟开始日期一致 则不做操作
            if (startDate == dateBean) {

            } else if (dateBean.getDate().getTime() < startDate.getDate().getTime()) {
                //当前点选的日期小于当前选中的开始日期 则本次操作重新选中开始日期
                startDate.setItemState(DateBean.ITEM_STATE_NORMAL);
                startDate = dateBean;
                startDate.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);
            } else {
                //选中结束日期
                endDate = dateBean;
                endDate.setItemState(DateBean.ITEM_STATE_END_DATE);
                if(onDateSelected!=null){
                    onDateSelected.selected(simpleDateFormat.format(startDate.getDate()),simpleDateFormat.format(endDate.getDate()));
                }
                nposition_recy=i;
                nposition_calend=position;
                onIntentClick.item(startDate,endDate);

            }
        } else if (startDate != null && endDate != null) {
            //重新选择开始日期,结束日期清除
            datebean.get(sposition_recy).get(sposition_calend).setItemState(DateBean.ITEM_STATE_NORMAL);
            datebean.get(nposition_recy).get(nposition_calend).setItemState(DateBean.ITEM_STATE_NORMAL);
            startDate.setItemState(DateBean.ITEM_STATE_NORMAL);
            datebean.get(i).get(position).setItemState(DateBean.ITEM_STATE_BEGIN_DATE);
            startDate = datebean.get(i).get(position);
            startDate.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);
            endDate.setItemState(DateBean.ITEM_STATE_NORMAL);
            endDate = null;
        }
        datebean.get(i).set(position,dateBean);
        notifyDataSetChanged();
    }
}

