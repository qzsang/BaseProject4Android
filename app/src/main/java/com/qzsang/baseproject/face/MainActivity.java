package com.qzsang.baseproject.face;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qzsang.baselibrary.base.BRViewHolder;
import com.qzsang.baselibrary.util.view.ViewHolderUtil;
import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.databinding.ActivityMainBinding;
import com.qzsang.baseproject.databinding.ItemMainListBinding;
import com.qzsang.baseproject.face.simple.BannerActivity;
import com.qzsang.baseproject.face.simple.SimpleActivity;
import com.qzsang.baseproject.face.simple.SimpleDialogActivity;
import com.qzsang.baseproject.face.simple.SimpleFragmentActivity;
import com.qzsang.baseproject.face.simple.SimpleImageSelActivity;
import com.qzsang.baseproject.face.simple.SimpleImageViewActivity;
import com.qzsang.baseproject.face.simple.SimpleListActivity;
import com.qzsang.baseproject.face.simple.SimpleNetActivity;
import com.qzsang.baseproject.face.simple.SimpleRxjavaActivity;
import com.qzsang.baseproject.face.simple.SimpleWebActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    public Pair[] items = new Pair[] {
            new Pair("activity", SimpleActivity.class),
            new Pair("fragment", SimpleFragmentActivity.class),
            new Pair("webActivity", SimpleWebActivity.class),
            new Pair("viewholder", SimpleListActivity.class),
            new Pair("viewholder for recyclerView", "该页面就是"),
            new Pair("ImageView", SimpleImageViewActivity.class),
            new Pair("net", SimpleNetActivity.class),
            new Pair("alertDialog", SimpleDialogActivity.class),
            new Pair("图片选择", SimpleImageSelActivity.class),
            new Pair("Banner", BannerActivity.class),
            new Pair("rxjava", SimpleRxjavaActivity.class),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void init() {
        super.init();
        binding.rvList.setLayoutManager(new LinearLayoutManager(mContext));


        binding.rvList.setAdapter(new RecyclerViewAdapter());

    }


    /**
     * 类中类的写法并不规范  这里只是演示用法
     */
    class RecyclerViewAdapter extends RecyclerView.Adapter<BRViewHolder<ItemMainListBinding>>{


        @Override
        public BRViewHolder<ItemMainListBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

            return ViewHolderUtil.inflate4RV(R.layout.item_main_list, parent);
        }


        @Override
        public void onBindViewHolder(BRViewHolder<ItemMainListBinding> holder, final int position) {
            holder.binding.tvContent.setText(items[position].first + "");

            holder.binding.tvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (items[position].second instanceof Class) {
                        Class clas = (Class) items[position].second;
                        startActivity(clas);
                    } else if (items[position].second instanceof String) {
                        toast(items[position].second + "");
                    } else {
                        toast("正在开发");
                    }

                }
            });
        }



        @Override
        public int getItemCount() {
            return items.length;
        }


    }

}
