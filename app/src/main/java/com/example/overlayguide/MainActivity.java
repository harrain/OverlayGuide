package com.example.overlayguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yxr.guidelibrary.Guide;
import com.yxr.guidelibrary.OverlayGuideUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Guide.ViewParams> views;
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        density = getResources().getDisplayMetrics().density;
        views = new ArrayList<>();

        List<OverlayGuideUtil.ViewParamsInfo> infos = new ArrayList<>();
        infos.add(new OverlayGuideUtil.ViewParamsInfo(R.id.tv0,"矩形抠图"));
        infos.add(new OverlayGuideUtil.ViewParamsInfo(R.id.tv1,"圆形抠图"));
        infos.add(new OverlayGuideUtil.ViewParamsInfo(R.id.tv2,"椭圆抠图"));
        OverlayGuideUtil.buildTextOverlayGuide(this,infos);
    }

    public void startOverlayGuide(View view){
        Guide.ViewParams rParams = new Guide.ViewParams(findViewById(R.id.tv0));
        rParams.guideRes = R.drawable.app_icon;
        rParams.offX = (int) (-15 * density);
        views.add(rParams);

//需要引导的控件参数
        Guide.ViewParams cParams = new Guide.ViewParams(findViewById(R.id.tv1));
//挖洞的类型
        cParams.state = Guide.State.CIRCLE;
//引导图的X偏移
        cParams.offX = (int) (15 * density);
//引导图的Y偏移
        cParams.offY = (int) (10 * density);
//引导图资源文件
        cParams.guideRes = R.drawable.map;
        views.add(cParams);

        Guide.ViewParams oParams = new Guide.ViewParams(findViewById(R.id.tv2));
        oParams.state = Guide.State.OVAL;
        oParams.offX = (int) (15 * density);
        oParams.offY = (int) (10 * density);
        oParams.guideRes = R.drawable.camera;
        views.add(oParams);

        new Guide.Builder(this)
                .backgroundColor(0xAA000000)     // 设置引导层背景色
                .oneByOne(true)                  // 设置是否一个接一个显示引导
                .outsideTouchable(true)         // 设置除targetView（需要挖洞的控件）外是否可以点击
                .guideViews(views)               // 设置多个引导
                .build()
                .show();
    }
}
