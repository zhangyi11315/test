package com.glory.huatianmms.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.huatianmms.R;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;
import com.pda.platform.ui.ui_pdaplatform.view.FreeUI_ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author hx
 * @date 2019/9/24
 * @desc 物料盘点
 */
public class BatchWorkCheckActivity extends FreeUI_BaseActivity {
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

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_batch_work_check;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        freeUI_titleView.setTitle("物料盘点");
        freeUI_titleView.setRightListener("提交", view -> {

        });
        return new FreeUI_AddViewEntity("header", llMain);
    }

    @OnClick({R.id.tvStartTime, R.id.llSearch, R.id.tvEndTime, R.id.llDanJu, R.id.tvSearch, R.id.tvConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvStartTime:
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
            case R.id.tvEndTime:
                break;
            case R.id.llDanJu:
                break;
            case R.id.tvSearch:
                break;
            case R.id.tvConfirm:
                break;
        }
    }
}
