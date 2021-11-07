package com.example.oschina.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.oschina.R;
import com.example.oschina.bean.BannerBean;
import com.example.oschina.bean.NewsBean;
import com.example.oschina.utils.SpUtils;
import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.loopviewpager.listener.OnItemClickListener;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import org.itheima.recycler.adapter.BaseLoadMoreRecyclerAdapter;
import org.itheima.recycler.header.RecyclerViewHeader;
import org.itheima.recycler.viewholder.BaseRecyclerViewHolder;
import org.itheima.recycler.widget.ItheimaRecyclerView;
import org.itheima.recycler.widget.PullToLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    PullToLoadMoreRecyclerView pullToLoadMoreRecyclerView;
    //    @BindView(R.id.recycler_view)
//    ItheimaRecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ItheimaRecyclerView mRecyclerView;
    private RecyclerViewHeader header;
    private LoopViewPager loopViewPager;

    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    private int state = STATE_REFRESH;
    private NewsBean mNewsBean;
    private String mNextPageToken;

    ArrayList<NewsBean.ResultBean.ItemsBean> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_view, new LinearLayout(this), false);
            supportActionBar.setCustomView(actionbar);
            View actionbarRoot = actionbar.findViewById(R.id.action_bar_container);
            actionbarRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerView.scrollToPosition(0);
                }

            });
        }

        header = findViewById(R.id.recycler_header);
        mRecyclerView = findViewById(R.id.recycler_view);
        header.attachTo(mRecyclerView);

        loopViewPager = (LoopViewPager) findViewById(R.id.lvp_pager);
        initBanner();

        pullToLoadMoreRecyclerView = new PullToLoadMoreRecyclerView<NewsBean>(mSwipeRefreshLayout, mRecyclerView, MyRecyclerViewHolder.class) {
            @Override
            public int getItemResId() {
                //recylerview item资源id
                return R.layout.item_list_news;
            }

            @Override
            public String getApi() {
                //接口
                String url = "action/apiv2/news?pageToken=";
                switch (state) {
                    case STATE_REFRESH:
                        break;
                    case STATE_LOADMORE:
                        mNextPageToken = mNewsBean.result.nextPageToken;
                        url += mNextPageToken;
                        break;
                    default:
                        break;
                }

                return url;
            }

            //是否加载更多的数据，根据业务逻辑自行判断，true表示有更多的数据，false表示没有更多的数据，如果不需要监听可以不重写该方法
            @Override
            public boolean isMoreData(BaseLoadMoreRecyclerAdapter.LoadMoreViewHolder holder) {

                System.out.println("isMoreData" + holder);
                holder.loading("加载中");

                state = STATE_LOADMORE;
                return true;
            }

        };

        pullToLoadMoreRecyclerView.setLoadingDataListener(new PullToLoadMoreRecyclerView.LoadingDataListener<NewsBean>() {

            @Override
            public void onRefresh() {
                //监听下啦刷新，如果不需要监听可以不重新该方法z
                Log.i(TAG, "setLoadingDataListener onRefresh");
                state = STATE_REFRESH;
            }

            @Override
            public void onStart() {
                //监听http请求开始，如果不需要监听可以不重新该方法
                Log.i(TAG, "setLoadingDataListener onStart");
            }

            @Override
            public void onSuccess(NewsBean newsBean, Headers headers) {
//                super.onSuccess(newsBean, headers);
                //监听http请求成功，如果不需要监听可以不重新该方法
                Log.i(TAG, "setLoadingDataListener onSuccess: " + newsBean);
                mNewsBean = newsBean;
//                List<NewsBean.ResultBean.ItemsBean> itemDatas = mNewsBean.getItemDatas();
//                for (NewsBean.ResultBean.ItemsBean itemsBean : itemDatas) {
//                    itemList.add(itemsBean);
//                }
            }

            @Override
            public void onFailure() {
                //监听http请求失败，如果不需要监听可以不重新该方法
                Log.i(TAG, "setLoadingDataListener onFailure");
            }
        });

        pullToLoadMoreRecyclerView.requestData();
    }

    List<String> bannerHrefs = new ArrayList<>();

    private void initBanner() {
        Request request = ItheimaHttp.newGetRequest("action/apiv2/banner?catalog=1");//apiUrl格式："xxx/xxxxx"
        Call call = ItheimaHttp.send(request, new HttpResponseListener<BannerBean>() {
            @Override
            public void onResponse(BannerBean bannerBean, Headers headers) {
                System.out.println("--------");
                List<BannerBean.ResultBean.ItemsBean> itemDatas = bannerBean.getItemDatas();
                List<String> textList = new ArrayList<>();
                List<String> imgList = new ArrayList<>();
                bannerHrefs.clear();
                for (int i = 0; i < itemDatas.size(); i++) {
                    textList.add(itemDatas.get(i).name);
                    imgList.add(itemDatas.get(i).img);
                    bannerHrefs.add(itemDatas.get(i).href);
                }
                loopViewPager.setImgData(imgList);
                loopViewPager.setTitleData(textList);
                loopViewPager.start();
            }
        });

        //处理点击事件
        loopViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("href", bannerHrefs.get(position % 4));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public static class MyRecyclerViewHolder extends BaseRecyclerViewHolder<NewsBean.ResultBean.ItemsBean> {
        //换成你布局文件中的id
        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.body)
        TextView body;
        @BindView(R.id.date_time)
        TextView dateTime;
        @BindView(R.id.comment_count)
        TextView commentCount;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.recommend)
        TextView recommend;

        public MyRecyclerViewHolder(ViewGroup parentView, int itemResId) {
            super(parentView, itemResId);
        }

        /**
         * 绑定数据的方法，在mData获取数据（mData声明在基类中）
         */
        @Override
        public void onBindRealData() {
            tvTitle.setText(mData.title);
            boolean readed = SpUtils.checkReaded(mData.id);
            author.setText("作者：" + mData.author);
            recommend.setVisibility(mData.recommend ? View.VISIBLE : View.GONE);
            body.setText(mData.body);
            dateTime.setText(mData.pubDate);
            commentCount.setText(mData.viewCount + "阅读" + ",\t" + mData.commentCount + "评论");

//            Log.d(TAG, "id = " + mData.id + ",author=" + mData.author + ",href=" + mData.href + ",recommend=" + mData.recommend + ",type=" + mData.type + ",viewCount=" + mData.viewCount);
            tvTitle.setTextColor(mContext.getColor(readed ? R.color.color_text_readed : R.color.colorPrimary));
            author.setTextColor(mContext.getColor(readed ? R.color.color_text_readed : R.color.colorAccent));
            int color = mContext.getColor(readed ? R.color.color_text_readed : R.color.color_text_unreaded);
            dateTime.setTextColor(color);
            body.setTextColor(color);
            commentCount.setTextColor(color);
        }

        /**
         * 给按钮添加点击事件（button改成你要添加点击事件的id）
         *
         * @param v
         */
        @OnClick({R.id.body, R.id.title})
        public void click(View v) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("id", mData.id);
            tvTitle.setTextColor(mContext.getColor(R.color.color_text_readed));
            body.setTextColor(mContext.getColor(R.color.color_text_readed));
            dateTime.setTextColor(mContext.getColor(R.color.color_text_readed));
            commentCount.setTextColor(mContext.getColor(R.color.color_text_readed));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            SpUtils.markReaded(mData.id, true);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        SpUtils.saveReadState(this);
    }
}
