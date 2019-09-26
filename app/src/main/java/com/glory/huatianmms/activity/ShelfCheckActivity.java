package com.glory.huatianmms.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.glory.huatianmms.R;
import com.glory.huatianmms.adapter.CheckListAdapter;
import com.glory.huatianmms.application.App;
import com.glory.huatianmms.entity.BillEntity;
import com.glory.huatianmms.entity.DataListEntity;
import com.glory.huatianmms.entity.LotEntity;
import com.glory.huatianmms.entity.response.BaseEntity;
import com.glory.huatianmms.util.NetUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.callback.FreeUI_DialogEntityCallBack;
import com.pda.platform.ui.ui_pdaplatform.callback.FreeUI_EntityCallBack;
import com.pda.platform.ui.ui_pdaplatform.datepicker.CustomDatePicker;
import com.pda.platform.ui.ui_pdaplatform.dialog.FreeUI_GeneralPop;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_DateUtils;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_PopUtils;
import com.pda.platform.ui.ui_pdaplatform.view.FreeUI_ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author hx
 * @date 2019/9/25
 * @desc 治具盘点
 */
public class ShelfCheckActivity extends FreeUI_BaseActivity {
    @BindView(R.id.llMain)
    LinearLayout llMain;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.ivHideArrow)
    ImageView ivHideArrow;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.etUser)
    FreeUI_ClearEditText etUser;
    @BindView(R.id.etDepot)
    FreeUI_ClearEditText etDepot;
    @BindView(R.id.tvDanJu)
    TextView tvDanJu;
    @BindView(R.id.ivArrow)
    ImageView ivArrow;
    @BindView(R.id.llDanJu)
    LinearLayout llDanJu;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.llMainEqpParent)
    LinearLayout llMainEqpParent;
    @BindView(R.id.llTop)
    LinearLayout llTop;
    @BindView(R.id.etLot)
    FreeUI_ClearEditText etLot;
    @BindView(R.id.etNum)
    FreeUI_ClearEditText etNum;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    @BindView(R.id.rvThingList)
    RecyclerView rvThingList;

    private Animation animation, animationDismiss;
    private CustomDatePicker mDatePicker;
    private boolean isStart = true;
    private FreeUI_GeneralPop billPop;
    private CheckListAdapter checkListAdapter;
    private List<LotEntity> lotList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shelf_check;
    }

    @Override
    protected void initView() {
        initDatePicker();
        rvThingList.setLayoutManager(new LinearLayoutManager(ShelfCheckActivity.this));
        rvThingList.setItemAnimator(new DefaultItemAnimator());
        rvThingList.addItemDecoration(new DividerItemDecoration(ShelfCheckActivity.this, 1));
        checkListAdapter = new CheckListAdapter(lotList);
        checkListAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        checkListAdapter.isFirstOnly(true);
        rvThingList.setAdapter(checkListAdapter);
    }

    @Override
    protected void initData() {
        etLot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == 0 && before == 0 && count > 1) {
                    if (s.length() > 0) {
                        boolean isHave = false;
                        for (LotEntity lotEntity : lotList) {
                            if (lotEntity.getLOTID().equals(s.toString())) {
                                isHave = true;
                                break;
                            }
                        }
                        if (!isHave) {
                            etLot.setText("");
                            showToast("治具号不存在于列表中,请重新扫描", false);
                        }
                    } else {
                        showToast("输入信息有误", false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        freeUI_titleView.setTitle("治具盘点");
        freeUI_titleView.setRightListener("提交", view -> {
            if (lotList.size() == 0) {
                showToast("暂无盘点数据，无法提交", false);
                return;
            }
            boolean isOk = true;
            for (LotEntity lotEntity : lotList) {
                if (lotEntity.getTRANSMAINQTY() < 0) {
                    isOk = false;
                    break;
                }
            }
            if (!isOk) {
                showToast("请填写列表中的全部数量后再提交", false);
                return;
            }
            FreeUI_EntityCallBack<BaseEntity<?>> callBack = new FreeUI_DialogEntityCallBack<BaseEntity<?>>
                    (new TypeToken<BaseEntity<?>>() {
                    }.getType(), getSupportFragmentManager(), this) {

                @Override
                public void onSuccess
                        (final Response<BaseEntity<?>> response) {
                    if (response.body().isSuccess(getApplicationContext())) {
                        showToast("提交成功", true);
                    } else {
                        showToast(response.body().getResponse().getHeader().getRESULTMESSAGE(), false);
                    }
                }

                @Override
                public void onError
                        (Response<BaseEntity<?>> response) {
                    super.onError(response);
                    loadError(response.getException(), "WMS.MLOTSTOCKTAKING");
                }
            };

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("TACKINGDOCID", tvDanJu.getText().toString());
            jsonObject.add("INOUTLOTLIST", new JsonParser().parse(new Gson().toJson(lotList)).getAsJsonArray());
            NetUtils.requestGetNet(App.WEB_URL,"WMSDirectSender", this, callBack, "WMS.MLOTSTOCKTAKING", jsonObject);
        });
        return new FreeUI_AddViewEntity("header", llMain);
    }

    private void initDatePicker() {
        long beginTimestamp = FreeApi_DateUtils.str2Long("1992-03-20", false);
        long endTimestamp = FreeApi_DateUtils.str2Long("2222-02-22", false);
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(this, timestamp -> {
            String date = FreeApi_DateUtils.long2Str(timestamp, false);
            if (isStart) {
                tvStartTime.setText(date);
                tvEndTime.setText("");
            } else {
                String startDate = tvStartTime.getText().toString();
                if (startDate.length() > 0) {
                    long longStartDate = FreeApi_DateUtils.str2Long(startDate, false);
                    long longEndDate = FreeApi_DateUtils.str2Long(date, false);
                    if (longStartDate >= longEndDate) {
                        showToast("结束日期必须大于开始日期", false);
                    } else {
                        tvEndTime.setText(date);
                    }
                } else {
                    tvEndTime.setText(date);
                }
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(true);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }

    @OnClick({R.id.tvStartTime, R.id.llSearch, R.id.tvEndTime, R.id.llDanJu, R.id.tvSearch, R.id.tvConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvStartTime:
                isStart = true;
                mDatePicker.show(System.currentTimeMillis());
                break;
            case R.id.tvEndTime:
                isStart = false;
                mDatePicker.show(System.currentTimeMillis());
                break;
            case R.id.llSearch:
                if (llTop.getVisibility() == View.VISIBLE) {
                    llTop.setVisibility(View.GONE);
                    if (animation == null) {
                        animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        animation.setDuration(500);
                        animation.setFillAfter(true);
                    }
                    ivHideArrow.startAnimation(animation);
                } else {
                    llTop.setVisibility(View.VISIBLE);
                    if (animationDismiss == null) {
                        animationDismiss = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        animationDismiss.setDuration(500);
                        animationDismiss.setFillAfter(true);
                    }
                    ivHideArrow.startAnimation(animationDismiss);
                }
                break;
            case R.id.llDanJu:
                if (billPop == null) {
                    showToast("暂未获取到单据信息", false);
                    return;
                }
                if (billPop.isShowing()) {
                    billPop.dismiss();
                } else {
                    FreeApi_PopUtils.setArrowShow(ivArrow);
                    billPop.showAsDropDown(llDanJu, 0, 0);
                }
                break;
            case R.id.tvSearch:
                tvDanJu.setText("");
                String startDate = tvStartTime.getText().toString();
                String endDate = tvEndTime.getText().toString();
                String user = etUser.getText().toString().trim();
                String depot = etDepot.getText().toString().trim();
                if (startDate.length() == 0 && endDate.length() == 0 && user.length() == 0 && depot.length() == 0) {
                    showToast("请填写筛选条件", false);
                    return;
                }
                if (startDate.length() > 0 && endDate.length() == 0) {
                    showToast("请选择结束日期", false);
                    return;
                }
                if (startDate.length() == 0 && endDate.length() > 0) {
                    showToast("请选择开始日期", false);
                    return;
                }
                FreeUI_EntityCallBack<BaseEntity<DataListEntity<BillEntity>>> callBack = new FreeUI_DialogEntityCallBack<BaseEntity<DataListEntity<BillEntity>>>
                        (new TypeToken<BaseEntity<DataListEntity<BillEntity>>>() {
                        }.getType(), getSupportFragmentManager(), this) {

                    @Override
                    public void onSuccess
                            (final Response<BaseEntity<DataListEntity<BillEntity>>> response) {
                        if (response.body().isSuccess(getApplicationContext())) {
                            List<BillEntity> list = response.body().getResponse().getBody().getDATALIST();
                            if (list.size() == 0) {
                                showToast("未查询到单据信息", false);
                            } else {
                                showToast("查询单据信息成功", true);
                                List<String> stringList = new ArrayList<>();
                                for (BillEntity billEntity : list) {
                                    stringList.add(billEntity.getDOCID());
                                }
                                billPop = FreeApi_PopUtils.getNormalPop(ShelfCheckActivity.this,
                                        llDanJu.getWidth(), stringList, ivArrow, s -> {
                                            tvDanJu.setText(s);
                                            requestLotListData(s);
                                        });
                            }
                        } else {
                            showToast(response.body().getResponse().getHeader().getRESULTMESSAGE(), false);
                        }
                    }

                    @Override
                    public void onError
                            (Response<BaseEntity<DataListEntity<BillEntity>>> response) {
                        super.onError(response);
                        loadError(response.getException(), "com.glory.wms.model.InOut");
                    }
                };

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("MAX", "999");
                jsonObject.addProperty("ENTITYMODEL", "com.glory.wms.model.InOut");
                StringBuffer sb = new StringBuffer();
                sb.append("docType = 'TOOLSTOCKTAKING' AND docStatus = 'APPROVED'");
                if (depot.length() > 0) {
                    sb.append(" AND warehouseId = '").append(depot).append("'");
                }
                if (user.length() > 0) {
                    sb.append(" AND owner  = '").append(user).append("'");
                }
                if (startDate.length() > 0) {
                    sb.append(" AND approvedDate >= to_date('").append(startDate)
                            .append("','yyyy-MM-dd') AND approvedDate < to_date('").append(endDate)
                            .append("','yyyy-MM-dd')");
                }
                jsonObject.addProperty("WHERECLAUSE", sb.toString());
                jsonObject.addProperty("ORDERBYCLAUSE", "objectRrn");
                NetUtils.requestGetNet(App.WEB_URL, this, callBack, "GETENTITYLIST", jsonObject);
                break;
            case R.id.tvConfirm:
                String lotId = etLot.getText().toString();
                if (lotId.length() == 0) {
                    showToast("请扫描治具号", false);
                    return;
                }
                String num = etNum.getText().toString();
                if (num.length() == 0) {
                    showToast("请填写数量", false);
                    return;
                }
                for (LotEntity lotEntity : lotList) {
                    if (lotEntity.getLOTID().equals(lotId)) {
                        lotEntity.setTRANSMAINQTY(Double.parseDouble(num));
                    }
                }
                checkListAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void requestLotListData(String s) {
        FreeUI_EntityCallBack<BaseEntity<DataListEntity<LotEntity>>> callBack = new FreeUI_DialogEntityCallBack<BaseEntity<DataListEntity<LotEntity>>>
                (new TypeToken<BaseEntity<DataListEntity<LotEntity>>>() {
                }.getType(), getSupportFragmentManager(), this) {

            @Override
            public void onSuccess
                    (final Response<BaseEntity<DataListEntity<LotEntity>>> response) {
                if (response.body().isSuccess(getApplicationContext())) {
                    lotList = response.body().getResponse().getBody().getDATALIST();
                    if (lotList.size() == 0) {
                        showToast("未查询到治具信息", false);
                    } else {
                        for (LotEntity lotEntity : lotList) {
                            lotEntity.setTRANSMAINQTY(-1);
                            lotEntity.setMLOTID(lotEntity.getLOTID());
                        }
                        checkListAdapter.setNewData(lotList);
                        showToast("查询治具信息成功", true);
                    }
                } else {
                    showToast(response.body().getResponse().getHeader().getRESULTMESSAGE(), false);
                }
            }

            @Override
            public void onError
                    (Response<BaseEntity<DataListEntity<LotEntity>>> response) {
                super.onError(response);
                loadError(response.getException(), "com.glory.wms.model.InOutLine");
            }
        };

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("MAX", "999");
        jsonObject.addProperty("ENTITYMODEL", "com.glory.wms.model.InOutLine");
        jsonObject.addProperty("WHERECLAUSE", "parentId= '" + s + "'");
        jsonObject.addProperty("ORDERBYCLAUSE", "objectRrn");
        NetUtils.requestGetNet(App.WEB_URL, this, callBack, "GETENTITYLIST", jsonObject);
    }
}
