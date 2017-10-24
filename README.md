# BaseProject4Android
baseProject4Android   为了快速开发而新建的一个基础项目  （立志用最少的代码  完成业务需求）

1、分享一波 databinding 简单封装后的  activity


```java
public class SimpleActivity extends BaseActivity<ActivitySimpleBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
    }

    public void clickBtn (View view) {
        binding.tvContent.setText("文字已改变：" + (int)(Math.random()*1000));
    }

}


```
简不简洁？刺不刺激？   

2、网络请求   经过简单封装后  返回的Observable  是在io线程进行请求，在ui线程进行响应 并且任务和activity或fragment的生命周期绑定 以下为简单示例 
```
     simpleService.getuser(rqUserBean.username, rqUserBean.pwd)
        .compose(new SubscribeTransformer<RpUserBean>(mContext, new NetSubscriber<RpUserBean>() {
            @Override
            public void onNext(RpUserBean response) {
                super.onNext(response);
                binding.tvPostField.setText("post field response:" + response);
            }
        }));

```

3、recycleView  的adapter.viewHolder  的简化
```java

class RecyclerViewAdapter extends RecyclerView.Adapter<BPRViewHolder<ItemMainListBinding>>{


        @Override
        public BPRViewHolder<ItemMainListBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

            return ViewHolderUtil.inflate4RV(R.layout.item_main_list, parent);
        }


        @Override
        public void onBindViewHolder(BPRViewHolder<ItemMainListBinding> holder, final int position) {
            
            holder.binding.tvContent.setText(items[position].first + "");
            holder.binding.tvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   //...

                }
            });
        }



        @Override
        public int getItemCount() {
            return items.length;
        }


    }
```
再也不用写viewHolder了是不是？

4、图片加载   
```
     MyImageUtil.with(mContext)
        .load("http://i.imgur.com/DvpvklR.png")
        .into(binding.ivNetImage);
```
只是简单封装了一层  为了后期的维护

5、其他的直接看源码吧。。MainActivity  里面都有一一对应的 例子  上列表  如下
```
 public Pair[] items = new Pair[] {
            new Pair("activity", SimpleActivity.class),
            new Pair("fragment", SimpleFragmentActivity.class),
            new Pair("webActivity", SimpleWebActivity.class),
            new Pair("viewholder", SimpleListActivity.class),
            new Pair("viewholder for recyclerView", "该页面就是"),
            new Pair("ImageView", SimpleImageViewActivity.class),
            new Pair("net", SimpleNetActivity.class),
            new Pair("alertDialog", SimpleDialogActivity.class),
            new Pair("图片选择", SimpleImageSelActivity.class)
    };

```