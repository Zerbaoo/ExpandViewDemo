package com.ljx.expand;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private ImageView iv , iv1;
    private PopupWindow popupWindow;
    private View contentView;
    private RelativeLayout ll_search;
    private TransitionSet mSet;
    private TextView tv_search;
    private boolean isExpand = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPop();
                if(popupWindow!=null){
                    popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.TOP|Gravity.RIGHT,DpOrPxUtils.dip2px(getApplicationContext(),20),DpOrPxUtils.dip2px(getApplicationContext(),100));
                }

            }
        });

        ll_search = findViewById(R.id.ll_search);

        findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExpand){
                    expand();
                    isExpand = false;
                }else{
                    reduce();
                    isExpand = true;
                }
            }
        });

    }

    private void initPop(){
        contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_menu, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.PopupAnimation);


    }


    private void expand() {
        //设置伸展状态时的布局
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) ll_search.getLayoutParams();
        LayoutParams.width = LayoutParams.MATCH_PARENT;
        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
        ll_search.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(ll_search);
    }

    private void reduce() {
        //设置收缩状态时的布局
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) ll_search.getLayoutParams();
        LayoutParams.width = dip2px(40);
        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
        ll_search.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(ll_search);
    }

    void beginDelayedTransition(ViewGroup view) {
        mSet = new AutoTransition();
        mSet.setDuration(300);
        TransitionManager.beginDelayedTransition(view, mSet);
    }

    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

}
