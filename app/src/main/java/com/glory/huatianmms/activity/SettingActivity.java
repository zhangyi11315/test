package com.glory.huatianmms.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.huatianmms.R;
import com.glory.huatianmms.application.App;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.callback.FreeUI_TextCallback;
import com.pda.platform.ui.ui_pdaplatform.dialog.FreeUI_GeneralFragmentDialog;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_DialogUtils;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_SharePreferencesUtils;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_StaticMembers;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yi.zhang
 * @date 2019/9/2
 * @desc 设置界面
 */
public class SettingActivity extends FreeUI_BaseActivity {
    @BindView(R.id.webAddress_Txt)
    TextView webAddressTxt;
    @BindView(R.id.updataAddress_Txt)
    TextView updataAddressTxt;
    @BindView(R.id.version_Txt)
    TextView versionTxt;
    @BindView(R.id.llMain)
    LinearLayout llMain;

    private FreeUI_GeneralFragmentDialog dialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        webAddressTxt.setText(App.WEB_URL);
        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;
        versionTxt.setText(version);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        freeUI_titleView.setTitle("设置");
        return new FreeUI_AddViewEntity(getIntent().getStringExtra(FreeApi_StaticMembers.SHOW_PLUGIN_KEY), llMain);
    }

    @OnClick({R.id.webAddress_Lay, R.id.updataAddress_Lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.webAddress_Lay:
                dialog = FreeApi_DialogUtils.getScanDialog(this, "请输入服务器地址",App.WEB_URL, new FreeUI_TextCallback() {
                    @Override
                    public void onSuccess(String s) {
                        webAddressTxt.setText(s);
                        App.WEB_URL = s;
                        showToast("保存成功", true);
                        FreeApi_SharePreferencesUtils.setSharePre("HTMMS_FILE", "WEB_URL", s, App.app);
                        dialog.dismiss();
                    }
                });
                dialog.show(getSupportFragmentManager(), "WEB_URL_DIALOG");
                break;
            case R.id.updataAddress_Lay:
                break;
        }
    }
}
