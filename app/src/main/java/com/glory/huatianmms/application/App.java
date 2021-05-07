package com.glory.huatianmms.application;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.glory.huatianmms.entity.UserEntity;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_InitUtils;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_StaticMembers;

/**
 * @author yi.zhang
 * @date 2019/8/30
 * @desc
 */
public class App extends Application {

    public static App instance;
    public static Context app;
    public static String WEB_URL = "http://172.24.197.84:8080/esbrest/RestService/sendrequest";
    public static String EQPID = "";
    public static UserEntity userEntity;
    public static String RegionName = "";
    public static String RegionValue = "";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        app = getApplicationContext();
        userEntity = new UserEntity();
        FreeUI_InitUtils.init(getApplicationContext(), this, true);
        FreeUI_InitUtils.setTheme(FreeApi_StaticMembers.THEME_SKY_BLUE);
        //Toast显示时长
        FreeApi_StaticMembers.TOASTSHOWTIME = Toast.LENGTH_LONG;
    }
}
