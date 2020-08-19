package com.example.recyclescroller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    VerticalSeekBar verticalSeekBar;

    RecyclerView recyclerView,recyclerViewtwo;

    ScrollBar scrollBar;

    MyHandler handler;

    int lastpostion = 0;

    int scrollFlag;

    CustomLinearLayoutManager linearLayoutManager;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verticalSeekBar = findViewById(R.id.myseekbar);
        recyclerView = findViewById(R.id.myrecycler);
        recyclerViewtwo = findViewById(R.id.myrecyclertwo);
        scrollBar = findViewById(R.id.myscrollbar);

        handler = new MyHandler();


        List<BluetoothCallLog> datas = new ArrayList();
        datas.add(new BluetoothCallLog("张三1","2378234792"));
        datas.add(new BluetoothCallLog("风格2","23782345392"));
        datas.add(new BluetoothCallLog("更好3","23782646792"));
        datas.add(new BluetoothCallLog("wiu4","2378234792"));
        datas.add(new BluetoothCallLog("张三5","2378234792"));
        datas.add(new BluetoothCallLog("符合6","2378234792"));
        datas.add(new BluetoothCallLog("合法7","54646"));
        datas.add(new BluetoothCallLog("规划8","2378234792"));
        datas.add(new BluetoothCallLog("认同9","2378234792"));
        datas.add(new BluetoothCallLog("干货10","647546"));
        datas.add(new BluetoothCallLog("但是11","2378234792"));
        datas.add(new BluetoothCallLog("品牌12","86886969"));
        datas.add(new BluetoothCallLog("张三13","2378234792"));
        datas.add(new BluetoothCallLog("风格14","23782345392"));
        datas.add(new BluetoothCallLog("更好15","23782646792"));
        datas.add(new BluetoothCallLog("wiu16","2378234792"));
        datas.add(new BluetoothCallLog("张三17","2378234792"));
        datas.add(new BluetoothCallLog("符合18","2378234792"));
        datas.add(new BluetoothCallLog("合法19","54646"));
        datas.add(new BluetoothCallLog("规划20","2378234792"));
        datas.add(new BluetoothCallLog("认同21","2378234792"));
        datas.add(new BluetoothCallLog("干货22","647546"));
        datas.add(new BluetoothCallLog("但是23","2378234792"));
        datas.add(new BluetoothCallLog("品牌24","86886969"));
        datas.add(new BluetoothCallLog("张三1","2378234792"));
        datas.add(new BluetoothCallLog("风格2","23782345392"));
        datas.add(new BluetoothCallLog("更好3","23782646792"));
        datas.add(new BluetoothCallLog("wiu4","2378234792"));
        datas.add(new BluetoothCallLog("张三5","2378234792"));
        datas.add(new BluetoothCallLog("符合6","2378234792"));
        datas.add(new BluetoothCallLog("合法7","54646"));
        datas.add(new BluetoothCallLog("规划8","2378234792"));
        datas.add(new BluetoothCallLog("认同9","2378234792"));
        datas.add(new BluetoothCallLog("干货10","647546"));
        datas.add(new BluetoothCallLog("但是11","2378234792"));
        datas.add(new BluetoothCallLog("品牌12","86886969"));
        datas.add(new BluetoothCallLog("张三13","2378234792"));
        datas.add(new BluetoothCallLog("风格14","23782345392"));
        datas.add(new BluetoothCallLog("更好15","23782646792"));
        datas.add(new BluetoothCallLog("wiu16","2378234792"));
        datas.add(new BluetoothCallLog("张三17","2378234792"));
        datas.add(new BluetoothCallLog("符合18","2378234792"));
        datas.add(new BluetoothCallLog("合法19","54646"));
        datas.add(new BluetoothCallLog("规划20","2378234792"));
        datas.add(new BluetoothCallLog("认同21","2378234792"));
        datas.add(new BluetoothCallLog("干货22","647546"));
        datas.add(new BluetoothCallLog("但是23","2378234792"));
        datas.add(new BluetoothCallLog("品牌24","86886969"));


        final CallLogAdapter adapter = new CallLogAdapter();
        adapter.setData(datas);

        final CallLogAdapter adaptertwo = new CallLogAdapter();
        adaptertwo.setData(datas);

        linearLayoutManager = new CustomLinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManagertwo = new LinearLayoutManager(this);
        recyclerViewtwo.setLayoutManager(linearLayoutManagertwo);
        recyclerViewtwo.setAdapter(adaptertwo);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.i(TAG,"newState : " + newState);

                //这里是为了处理数据很多的时候，列表滚动需要时间，不加控制，会让滑动条也会再次跟着一起跳动
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL
                        || newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){

                    scrollFlag = newState;

                }

                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(scrollFlag != AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    return;
                }

                int range = 0;
                int temp = recyclerView.computeVerticalScrollRange();//整体的高度，注意是整体，包括在显示区域之外的 会动态变化
                if (temp > range) {
                    range = temp;
                }
                int offset = recyclerView.computeVerticalScrollOffset();//已经向下滚动的距离，为0时表示已处于顶部。
                int extent = recyclerView.computeVerticalScrollExtent();//RecycleView显示区域的高度。
                float proportion = 1 - (float) (offset * 1.0 / (range - extent));//滑动比例
                Log.i(TAG,"proportion "+proportion);
                verticalSeekBar.setProgress((int) (proportion*100));
            }
        });


        verticalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("VerticalSeekBar","progress:" + i + "; b:" +b);

                if(verticalSeekBar.ismIsDragging()){
                    int posiotn = (int) (adapter.getItemCount() * (100 -i)/100.0f);
                    int scrollSize = Math.abs(posiotn - lastpostion);
                    Log.i(TAG,"scroll size:" + Math.abs(posiotn - lastpostion));
                    lastpostion = posiotn;
                    //同样是为了处理滑动项比较多的情况
                    if(scrollSize > 50){
                        linearLayoutManager.setTimeElement(500);
                    }else {
                        linearLayoutManager.setTimeElement(2000);
                    }
                    recyclerView.smoothScrollToPosition(posiotn);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("VerticalSeekBar","onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("VerticalSeekBar","onStopTrackingTouch");
            }
        });



        scrollBar.setRecycleView(recyclerViewtwo);

        recyclerViewtwo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                Log.i(TAG,"onScrollStateChanged newState:"+newState);

                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://滑动结束
                        Log.e("monkey","mScrollBarLayout INVISIBLE");
                        handler.sendEmptyMessageDelayed(1,800);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://开始滑动
                        handler.removeMessages(2);
                        scrollBar.setAlpha(1f);
                        scrollBar.setVisibility(View.VISIBLE);
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                Log.i(TAG,"onScrolled dx:"+dx + " ; dy:"+dy);

                super.onScrolled(recyclerView, dx, dy);
                if(scrollBar!=null){
                    scrollBar.sroll();
                }
            }
        });


    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {

            switch (msg.what){
                case 1:

                    if(recyclerViewtwo.getScrollState() != AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                        return;
                    }

                    scrollBar.animate()
                            .alpha(0f)
                            .setDuration(200)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    scrollBar.setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });

                    break;

            }

        }
    }
}
