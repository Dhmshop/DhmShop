package dhm.com.dhmshop.framework.module.type.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.framework.base.BaseFragment;
import dhm.com.dhmshop.framework.base.MyBaseViewHolder;
import dhm.com.dhmshop.framework.module.home.entity.HomeHotGoodsList;
import dhm.com.dhmshop.framework.module.type.entity.TypeCategoryChildEntity;
import dhm.com.dhmshop.framework.module.type.model.TypeCategoryChildModel;
import dhm.com.dhmshop.framework.module.type.view.TypeCategoryChildView;
import dhm.com.dhmshop.framework.utils.GlideUtil;
import dhm.com.dhmshop.framework.utils.StringUtil;


@SuppressLint("ValidFragment")
public class TypeCategoryChildFragment extends BaseFragment<TypeCategoryChildModel> implements TypeCategoryChildView {


    private int pid;

    private TypeCategoryChildEntity typeCategoryChild;
    private View view;
    /**
     * 热门分类
     */
    private TextView titleTypechild;
    private RecyclerView recyclerviewTypechild;
    private TypeCategoryAdapter typeCategoryAdapter;

    public TypeCategoryChildFragment(int id) {
        pid = id;
    }

    @Override
    protected void initView(View inflate) {
        typeCategoryChild = new TypeCategoryChildEntity();
        typeCategoryAdapter = new TypeCategoryAdapter();

        titleTypechild = findViewById(R.id.title_typechild);
        recyclerviewTypechild = findViewById(R.id.recyclerview_typechild);
        recyclerviewTypechild.setAdapter(typeCategoryAdapter);
    }

    @Override
    protected void initData() {
        model.getTypeCategoryChild(pid);
    }

    @Override
    protected void initListener() {
        typeCategoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected TypeCategoryChildModel initModel() {
        return new TypeCategoryChildModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classification_child;
    }

    @Override
    public void getTypeChild(List<TypeCategoryChildEntity> result) {
        if (result == null || result.size() == 0) {
            return;
        }
        typeCategoryAdapter.addData(result);
    }


    /**
     * 热门商品内部适配器
     */
    class TypeCategoryAdapter extends BaseQuickAdapter<TypeCategoryChildEntity, MyBaseViewHolder> {

        public TypeCategoryAdapter() {
            super(R.layout.classificationitem);
        }


        @Override
        protected void convert(MyBaseViewHolder helper, TypeCategoryChildEntity item) {
            helper.setText(R.id.tv_typechild, StringUtil.preventNull(item.getName()));
            ImageView iv_typechild = helper.getView(R.id.iv_typechild);
            Glide.with(context).load(item.getThumb()).into(iv_typechild);
        }

    }

}
