package com.yxr.guidelibrary;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by data on 2017/8/31.
 */

public class OverlayGuideUtil {

    public static void buildTextOverlayGuide(Activity activity, List<ViewParamsInfo> infos){

        List<Guide.ViewParams> views = new ArrayList<>();
        float density = activity.getResources().getDisplayMetrics().density;

        for (ViewParamsInfo info : infos) {
            //需要引导的控件参数
            Guide.ViewParams cParams = new Guide.ViewParams(activity.findViewById(info.viewId));
//挖洞的类型
            cParams.state = Guide.State.OVAL;
//引导图的X偏移
            cParams.offX = (int) (-20 * density);
//引导图的Y偏移
            cParams.offY = (int) (30 * density);
//引导图资源文件
            cParams.des = info.textDes;
            views.add(cParams);
        }

        new Guide.Builder(activity)
                .backgroundColor(0xAA000000)     // 设置引导层背景色
                .oneByOne(true)                  // 设置是否一个接一个显示引导
                .outsideTouchable(true)         // 设置除targetView（需要挖洞的控件）外是否可以点击
                .guideViews(views)               // 设置多个引导
                .build()
                .show();


    }

    public static class ViewParamsInfo{

        public ViewParamsInfo(int viewId, String textDes) {
            this.viewId = viewId;
            this.textDes = textDes;
        }

        int viewId;
        String textDes;
    }
}
