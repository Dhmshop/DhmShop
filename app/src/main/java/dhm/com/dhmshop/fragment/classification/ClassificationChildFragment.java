package dhm.com.dhmshop.fragment.classification;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.classification.ClassificationItemAdapter;
import dhm.com.dhmshop.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificationChildFragment extends BaseFragment implements View.OnClickListener {


    private View view;
    /**
     * 热门分类
     */
    private TextView mTitleClassificationchild;
    private RecyclerView mRecyclerviewClassificationchild;

    public ClassificationChildFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_classification_child;
    }

    @Override
    protected void initView(View view) {

        mTitleClassificationchild = (TextView) view.findViewById(R.id.title_classificationchild);
        mRecyclerviewClassificationchild = (RecyclerView) view.findViewById(R.id.recyclerview_classificationchild);
        mRecyclerviewClassificationchild.setOnClickListener(this);


        ArrayList<String> titles = new ArrayList<>();
        titles.add("时尚套装");
        titles.add("半身裙");
        titles.add("套装裙");
        titles.add("辣条");
        titles.add("女装");
        titles.add("牛仔裤女");

        ClassificationItemAdapter classificationItemAdapter = new ClassificationItemAdapter(getActivity(), titles);

        mRecyclerviewClassificationchild.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        mRecyclerviewClassificationchild.setAdapter(classificationItemAdapter);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.recyclerview_classificationchild:
                break;
        }
    }
}
