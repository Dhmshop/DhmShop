package dhm.com.dhmshop.view.main;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;

public class CalenderActivity extends BaseActiity {


    @Override
    protected int getLayout() {
        return R.layout.activity_calender;
    }

    @Override
    protected void initView() {







    }

    @Override
    protected void initData() {

    }





//    private ArrayList<ArrayList<DateBean>> days() {
//        ArrayList<ArrayList<DateBean>> databean=new ArrayList<>();
//        try {
//            Calendar calendar = Calendar.getInstance();
//            //日期格式化
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat formatYYYYMM = new SimpleDateFormat("yyyy-MM");
//
//            //起始日期
//            Date startDate = new Date();
//            calendar.setTime(startDate);
//
//            //结束日期
//            calendar.add(Calendar.MONTH, 100);
//            Date endDate = new Date(calendar.getTimeInMillis());
//
//            Log.d(TAG, "startDate:" + format.format(startDate) + "----------endDate:" + format.format(endDate));
//
//            //格式化开始日期和结束日期为 yyyy-mm-dd格式
//            String endDateStr = format.format(endDate);
//            endDate = format.parse(endDateStr);
//
//            String startDateStr = format.format(startDate);
//            startDate = format.parse(startDateStr);
//
//            calendar.setTime(startDate);
//
//            Log.d(TAG, "startDateStr:" + startDateStr + "---------endDate:" + format.format(endDate));
//            Log.d(TAG, "endDateStr:" + endDateStr + "---------endDate:" + format.format(endDate));
//
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            Calendar monthCalendar = Calendar.getInstance();
//
//
//            //按月生成日历 每行7个 最多6行 42个
//            //每一行有七个日期  日 一 二 三 四 五 六 的顺序
//            for (; calendar.getTimeInMillis() <= endDate.getTime(); ) {
//
//                ArrayList<DateBean> dateBeans = new ArrayList<>();
//                //月份item
//                DateBean monthDateBean = new DateBean();
//                monthDateBean.setDate(calendar.getTime());
//                monthDateBean.setMonthStr(formatYYYYMM.format(monthDateBean.getDate()));
//                monthDateBean.setItemType(DateBean.item_type_month);
//                dateBeans.add(monthDateBean);
//
//                //获取一个月结束的日期和开始日期
//                monthCalendar.setTime(calendar.getTime());
//                monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
//                Date startMonthDay = calendar.getTime();
//
//                monthCalendar.add(Calendar.MONTH, 1);
//                monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
//                Date endMonthDay = monthCalendar.getTime();
//
//                //重置为本月开始
//                monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
//
//                Log.d(TAG, "月份的开始日期:" + format.format(startMonthDay) + "---------结束日期:" + format.format(endMonthDay));
//                for (; monthCalendar.getTimeInMillis() <= endMonthDay.getTime(); ) {
//                    //生成单个月的日历
//
//                    //处理一个月开始的第一天
//                    if (monthCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
//                        //看某个月第一天是周几
//                        int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
//                        switch (weekDay) {
//                            case 1:
//                                //周日
//                                break;
//                            case 2:
//                                //周一
//                                addDatePlaceholder(dateBeans, 1, monthDateBean.getMonthStr());
//                                break;
//                            case 3:
//                                //周二
//                                addDatePlaceholder(dateBeans, 2, monthDateBean.getMonthStr());
//                                break;
//                            case 4:
//                                //周三
//                                addDatePlaceholder(dateBeans, 3, monthDateBean.getMonthStr());
//                                break;
//                            case 5:
//                                //周四
//                                addDatePlaceholder(dateBeans, 4, monthDateBean.getMonthStr());
//                                break;
//                            case 6:
//                                addDatePlaceholder(dateBeans, 5, monthDateBean.getMonthStr());
//                                //周五
//                                break;
//                            case 7:
//                                addDatePlaceholder(dateBeans, 6, monthDateBean.getMonthStr());
//                                //周六
//                                break;
//                        }
//                    }
//
//                    //生成某一天日期实体 日item
//                    DateBean dateBean = new DateBean();
//                    dateBean.setDate(monthCalendar.getTime());
//                    dateBean.setDay(monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
//                    dateBean.setMonthStr(monthDateBean.getMonthStr());
//                    dateBeans.add(dateBean);
//
//                    //处理一个月的最后一天
//                    if (monthCalendar.getTimeInMillis() == endMonthDay.getTime()) {
//                        //看某个月第一天是周几
//                        int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
//                        switch (weekDay) {
//                            case 1:
//                                //周日
//                                addDatePlaceholder(dateBeans, 6, monthDateBean.getMonthStr());
//                                break;
//                            case 2:
//                                //周一
//                                addDatePlaceholder(dateBeans, 5, monthDateBean.getMonthStr());
//                                break;
//                            case 3:
//                                //周二
//                                addDatePlaceholder(dateBeans, 4, monthDateBean.getMonthStr());
//                                break;
//                            case 4:
//                                //周三
//                                addDatePlaceholder(dateBeans, 3, monthDateBean.getMonthStr());
//                                break;
//                            case 5:
//                                //周四
//                                addDatePlaceholder(dateBeans, 2, monthDateBean.getMonthStr());
//                                break;
//                            case 6:
//                                addDatePlaceholder(dateBeans, 1, monthDateBean.getMonthStr());
//                                //周5
//                                break;
//                        }
//                    }
//
//                    //天数加1
//                    monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
//
//                }
//
//                databean.add(dateBeans);
//                Log.d(TAG, "日期" + format.format(calendar.getTime()) + "----周几" + getWeekStr(calendar.get(Calendar.DAY_OF_WEEK) + ""));
//                //月份加1
//                calendar.add(Calendar.MONTH, -1);
//            }
//
//        } catch (Exception ex) {
//
//        }
//
//        return databean;
//    }


}
