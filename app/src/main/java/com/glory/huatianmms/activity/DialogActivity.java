package com.glory.huatianmms.activity;

import android.os.Bundle;

import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_ActivityManager;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_StaticMembers;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_Utils;

/**
 * @author yi.zhang
 * @date 2019/9/4
 * @desc 跳转处理类
 */
public class DialogActivity extends FreeUI_BaseActivity {

    private String extra;

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initView() {
        extra = getIntent().getStringExtra(FreeApi_StaticMembers.SHOW_PLUGIN_KEY);
        Jump(extra);
        DialogActivity.this.finish();
        FreeApi_ActivityManager.getActivityManager().finishActivity();
    }

    private void Jump(String str)
    {
        Bundle bundle = new Bundle();
        bundle.putString("EQPID",str);
        switch (extra){
            //物料上架
            case "BatchWorkBeginActivity":
                FreeApi_Utils.skipWithExtraIntent("header",bundle, DialogActivity.this, BatchWorkBeginActivity.class);
                break;
            //物料下架
            case "BatchWorkEndActivity":
                FreeApi_Utils.skipWithExtraIntent("header",bundle, DialogActivity.this, BatchWorkEndActivity.class);
                break;
            //治具上架
            case "uppershelf":
                FreeApi_Utils.skipWithExtraIntent("header",bundle, DialogActivity.this, UpperShelfActivity.class);
                break;
            //治具下架
            case "lowershelf":
                FreeApi_Utils.skipWithExtraIntent("header",bundle, DialogActivity.this, LowerShelfActivity.class);
                break;
            //物料盘点
            case "DailyInspectionActivity":
                FreeApi_Utils.skipWithExtraIntent("header",bundle, DialogActivity.this, DailyInspectionActivity.class);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        return null;
    }
}
