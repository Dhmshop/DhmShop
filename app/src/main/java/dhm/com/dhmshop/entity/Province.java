package dhm.com.dhmshop.entity;

//import com.contrarywind.interfaces.IPickerViewData;IPickerViewData

import java.util.List;

/**
 * Created by admin
 * 2019/4/23
 *
 * 省市级
 */
public class Province {
    public String getPickerViewText() {
        return this.name;
    }


    private String name;
    private List<CityBean> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCityList() {
        return city;
    }

    public void setCityList(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {

        private String name;
        private List<String> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }
    }
}
