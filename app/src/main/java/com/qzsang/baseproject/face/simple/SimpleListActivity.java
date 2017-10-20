package com.qzsang.baseproject.face.simple;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qzsang.baselibrary.base.BPViewHolder;
import com.qzsang.baselibrary.util.view.ViewHolderUtil;
import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.databinding.ActivitySimpleListBinding;
import com.qzsang.baseproject.databinding.ItemMainListBinding;

public class SimpleListActivity extends BaseActivity<ActivitySimpleListBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);
    }


    @Override
    protected void init() {
        super.init();

        binding.lvList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                BPViewHolder<ItemMainListBinding> viewHolder = null;
                if (convertView == null) {
                    viewHolder = ViewHolderUtil.inflateAndSetTag(R.layout.item_main_list);
                } else {
                    viewHolder = (BPViewHolder<ItemMainListBinding>) convertView.getTag();
                }

                viewHolder.binding.tvContent.setText("我是listview的第" + position + "个item");


                return viewHolder.binding.getRoot();
            }




        });

    }
}
