package com.glory.huatianmms.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.huatianmms.R;
import com.glory.huatianmms.application.App;
import com.glory.huatianmms.entity.DataListEntity;
import com.glory.huatianmms.entity.MaterielEntity;
import com.glory.huatianmms.entity.response.BaseEntity;
import com.glory.huatianmms.util.NetUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.callback.FreeUI_DialogEntityCallBack;
import com.pda.platform.ui.ui_pdaplatform.callback.FreeUI_EntityCallBack;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_StaticMembers;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_StringUtils;
import com.pda.platform.ui.ui_pdaplatform.view.FreeUI_ClearEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yi.zhang
 * @date 2019/9/5
 * @desc 物料上架
 */
public class BatchWorkBeginActivity extends FreeUI_BaseActivity {
    @BindView(R.id.llMain)
    LinearLayout llMain;
    @BindView(R.id.lot_Eit)
    FreeUI_ClearEditText lotEit;
    @BindView(R.id.lotNumber_Txt)
    TextView lotNumberTxt;
    @BindView(R.id.lotState_Txt)
    TextView lotStateTxt;
    @BindView(R.id.materielName_Txt)
    TextView materielNameTxt;
    @BindView(R.id.materielNumber_Txt)
    TextView materielNumberTxt;
    @BindView(R.id.pecifications_Txt)
    TextView pecificationsTxt;
    @BindView(R.id.model_Txt)
    TextView modelTxt;
    @BindView(R.id.supplier_Txt)
    TextView supplierTxt;
    @BindView(R.id.validity_Txt)
    TextView validityTxt;
    @BindView(R.id.shelves_Ext)
    FreeUI_ClearEditText shelvesExt;
    @BindView(R.id.storage_Ext)
    FreeUI_ClearEditText storageExt;

    private MaterielEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_batchworkbegin;
    }

    @Override
    protected void initView() {

        lotEit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == 0 && before == 0 && count > 1) {
                    if (s.length() > 0) {
                        queryData(s.toString());
                    } else {
                        showToast("输入信息有误", false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lotEit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (lotEit.getText().toString().equals("")) {
                    return true;
                }
                queryData(lotEit.getText().toString());
                return true;
            }
            return false;
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        freeUI_titleView.setTitle("物料上架");
        freeUI_titleView.setRightListener("提交", view -> {
            String strLot = lotEit.getText().toString().trim();
            if (FreeApi_StringUtils.isEmpty(strLot)) {
                showToast("请扫描批次号", false);
                return;
            }
            String strShelves = shelvesExt.getText().toString().trim();
            if (FreeApi_StringUtils.isEmpty(strShelves)) {
                showToast("请扫描货架号", false);
                return;
            }
            String strStorage = storageExt.getText().toString().trim();
            if (FreeApi_StringUtils.isEmpty(strStorage)) {
                showToast("请扫描储位号", false);
                return;
            }
            FreeUI_EntityCallBack<BaseEntity<?>> callBack = new FreeUI_DialogEntityCallBack<BaseEntity<?>>
                    (new TypeToken<BaseEntity<?>>() {
                    }.getType(), getSupportFragmentManager(), this) {

                @Override
                public void onSuccess
                        (final Response<BaseEntity<?>> response) {
                    if (response.body().isSuccess(getApplicationContext())) {
                        showToast("上架成功", true);
                    } else {
                        showToast(response.body().getResponse().getHeader().getRESULTMESSAGE(), false);
                    }
                }

                @Override
                public void onError
                        (Response<BaseEntity<?>> response) {
                    super.onError(response);
                    loadError(response.getException(), "HT.ONSHELF");
                }
            };

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("OBJECTTYPE", "MLOT");
            jsonObject.addProperty("WAREHOUSEID", "");
            jsonObject.addProperty("SHELFID",strShelves);
            jsonObject.addProperty("SHELFPOSITIONID", strStorage);
            JsonObject lotJsonObject =new JsonObject();
            lotJsonObject.addProperty("MLOTID",strLot);
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(lotJsonObject);
            jsonObject.add("MLOTLIST", jsonArray);

            NetUtils.requestGetNet(App.WEB_URL, this, callBack, "HT.ONSHELF", jsonObject);

        });
        return new FreeUI_AddViewEntity(getIntent().getStringExtra(FreeApi_StaticMembers.SHOW_PLUGIN_KEY), llMain);
    }

    private void queryData(String lotID) {
        loadError(new Exception(), "查询lot"+lotID);
        FreeUI_EntityCallBack<BaseEntity<DataListEntity<MaterielEntity>>> callBack = new FreeUI_DialogEntityCallBack<BaseEntity<DataListEntity<MaterielEntity>>>
                (new TypeToken<BaseEntity<DataListEntity<MaterielEntity>>>() {
                }.getType(), getSupportFragmentManager(), this) {

            @Override
            public void onSuccess
                    (final Response<BaseEntity<DataListEntity<MaterielEntity>>> response) {
                if (response.body().isSuccess(getApplicationContext())) {
                    List<MaterielEntity> list = response.body().getResponse().getBody().getDATALIST();
                    if (list.size() == 0)
                        return;
                    entity = list.get(0);
                    fillData();
                }else {
                    showToast(response.body().getResponse().getHeader().getRESULTMESSAGE(), false);
                }
            }

            @Override
            public void onError
                    (Response<BaseEntity<DataListEntity<MaterielEntity>>> response) {
                super.onError(response);
                loadError(response.getException(), "GETENTITYLIST");
            }
        };

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("MAX", "1");
        jsonObject.addProperty("ENTITYMODEL", "com.glory.mes.mm.lot.model.MLot");
        jsonObject.addProperty("WHERECLAUSE", "mLotId= '" + lotID + "'");
        NetUtils.requestGetNet(App.WEB_URL, this, callBack, "GETENTITYLIST", jsonObject);
    }

    private void fillData() {
        lotNumberTxt.setText(entity.getMAINQTY());
        lotStateTxt.setText(entity.getSTATE());
        materielNameTxt.setText(entity.getMATERIALDESC());
        materielNumberTxt.setText(entity.getMATERIALNAME());
        pecificationsTxt.setText(entity.getGRADE1());
        modelTxt.setText(entity.getGRADE2());
        supplierTxt.setText(entity.getPARTNERCODE());
        validityTxt.setText(entity.getFLOORLIFEEXPIRE());
    }
}
