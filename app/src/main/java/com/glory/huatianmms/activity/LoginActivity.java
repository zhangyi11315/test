package com.glory.huatianmms.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.glory.huatianmms.R;
import com.glory.huatianmms.application.App;
import com.glory.huatianmms.entity.LoginEntity;
import com.glory.huatianmms.entity.response.BaseEntity;
import com.glory.huatianmms.util.NetUtils;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.callback.FreeUI_DialogEntityCallBack;
import com.pda.platform.ui.ui_pdaplatform.callback.FreeUI_EntityCallBack;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_ActivityManager;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_SharePreferencesUtils;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_Utils;
import com.pda.platform.ui.ui_pdaplatform.view.FreeUI_ClearEditText;
import com.pda.platform.ui.ui_pdaplatform.view.FreeUI_PasswordEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author yi.zhang
 * @date 2019/8/30
 * @desc
 */
public class LoginActivity extends FreeUI_BaseActivity implements EasyPermissions.PermissionCallbacks {


    @BindView(R.id.etMobile)
    FreeUI_ClearEditText etMobile;
    @BindView(R.id.etPassword)
    FreeUI_PasswordEditText etPassword;
    @BindView(R.id.region_Spn)
    Spinner regionSpn;

    private List<String> RegionNameList;
    private List<String> RegionValueList;
    private int selectIndex = 0;

    private static final int PERMISSIONS_REQUEST_CODE = 10;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        String userName = FreeApi_SharePreferencesUtils.getSharePre("HTMMS_FILE", "USERNAME", "", App.app);
        String passWord = FreeApi_SharePreferencesUtils.getSharePre("HTMMS_FILE", "PASSWORD", "", App.app);
        etMobile.setText(userName);
        etPassword.setText(passWord);

        RegionNameList = new ArrayList<>();
        RegionNameList.add("请选择区域");
        RegionNameList.add("FAB1");
        RegionNameList.add("封装一部");
        RegionNameList.add("封装二部");
        RegionNameList.add("封装三部");
        RegionNameList.add("封装六部");
        RegionNameList.add("测试一部");
        RegionNameList.add("测试二部");
        RegionNameList.add("测试三部");
        RegionNameList.add("封装一部8#厂房减划工序");
        RegionNameList.add("封装一部6#厂房减划工序");
        RegionNameList.add("封装六部压焊");
        RegionNameList.add("汽车线上芯");
        RegionNameList.add("1#封装二部上芯");
        RegionNameList.add("封一8#厂房压焊工序");
        RegionNameList.add("封装五部");

        RegionValueList = new ArrayList<>();
        RegionValueList.add("000000");
        RegionValueList.add("378341");
        RegionValueList.add("133951");
        RegionValueList.add("133952");
        RegionValueList.add("143702");
        RegionValueList.add("142801");
        RegionValueList.add("142802");
        RegionValueList.add("142803");
        RegionValueList.add("142804");
        RegionValueList.add("143701");
        RegionValueList.add("143902");
        RegionValueList.add("144201");
        RegionValueList.add("147951");
        RegionValueList.add("144252");
        RegionValueList.add("144251");
        RegionValueList.add("167951");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this,
                android.R.layout.simple_dropdown_item_1line, RegionNameList);
        regionSpn.setAdapter(adapter);
        regionSpn.setSelection(0);
        regionSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectIndex = position;
                if(selectIndex == 0)
                    return;
                App.RegionName = RegionNameList.get(position);
                App.RegionValue = RegionValueList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void initData() {
        App.EQPID = FreeApi_SharePreferencesUtils.getSharePre("HTMMS_FILE", "EQP_ID", App.EQPID, App.app);
        App.WEB_URL = FreeApi_SharePreferencesUtils.getSharePre("HTMMS_FILE", "WEB_URL", App.WEB_URL, App.app);
    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        return null;
    }

    @OnClick({R.id.ivSetting, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivSetting:
                FreeApi_Utils.skipWithExtraIntent("header", LoginActivity.this, SettingActivity.class);
                break;
            case R.id.btnLogin:
                if (etMobile.getText().toString().trim().length() == 0) {
                    showToast("请输入用户名", false);
                    return;
                }
                if (etPassword.getText().toString().trim().length() == 0) {
                    showToast("请输入密码", false);
                    return;
                }
                if(selectIndex == 0){
                    showToast("请选择区域", false);
                    return;
                }
                requestPermission();
                break;
        }
    }

    private void Login() {
        FreeUI_EntityCallBack<BaseEntity<LoginEntity>> callBack = new FreeUI_DialogEntityCallBack<BaseEntity<LoginEntity>>
                (new TypeToken<BaseEntity<LoginEntity>>() {
                }.getType(), getSupportFragmentManager(), this) {

            @Override
            public void onSuccess
                    (final Response<BaseEntity<LoginEntity>> response) {
                if (response.body().isSuccess(getApplicationContext())) {
                    LoginEntity loginEntity = response.body().getResponse().getBody();
                    App.userEntity = loginEntity.getUSER();
                    FreeApi_ActivityManager.getActivityManager().finishAllActivity();
                    FreeApi_Utils.skipWithExtraIntent("", LoginActivity.this, MainActivity.class);
                    FreeApi_SharePreferencesUtils.setSharePre("HTMMS_FILE", "USERNAME", etMobile.getText().toString().trim(), App.app);
                    FreeApi_SharePreferencesUtils.setSharePre("HTMMS_FILE", "PASSWORD", etPassword.getText().toString().trim(), App.app);
                } else {
                    showToast(response.body().getResponse().getHeader().getRESULTMESSAGE(), false);
                }
            }

            @Override
            public void onError
                    (Response<BaseEntity<LoginEntity>> response) {
                super.onError(response);
                loadError(response.getException(), "PDALoginRequest");
            }
        };

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("USERNAME", etMobile.getText().toString().trim());
        jsonObject.addProperty("PASSWORD", etPassword.getText().toString().trim());
        NetUtils.requestGetNet(App.WEB_URL, this, callBack, "USER_LOGIN", jsonObject);
//        FreeApi_ActivityManager.getActivityManager().finishAllActivity();
//        FreeApi_Utils.skipWithExtraIntent("", LoginActivity.this, MainActivity.class);
    }

    @AfterPermissionGranted(PERMISSIONS_REQUEST_CODE)
    private void requestPermission() {
        String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        //权限判断，第一次弹出系统的授权提示框
        if (EasyPermissions.hasPermissions(this, perms)) {
            //@AfterPermissionGranted 有权限直接执行 todo...
            Login();
        } else {
            //没有权限的话，先提示，点确定后弹出系统的授权提示框
            EasyPermissions.requestPermissions(this, "程序运行需要权限",
                    PERMISSIONS_REQUEST_CODE, perms);
        }
    }

    /**
     * 允许权限成功后触发
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    /**
     * 禁止权限后触发
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        showToast("用户授权失败", false);
        /**
         * 若是在权限弹窗中，用户勾选了'NEVER ASK AGAIN.'或者'不在提示'，且拒绝权限。
         * 这时候，需要跳转到设置界面去，让用户手动开启。
         */
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
