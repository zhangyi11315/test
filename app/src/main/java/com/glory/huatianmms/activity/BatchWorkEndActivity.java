package com.glory.huatianmms.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.glory.huatianmms.R;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_StaticMembers;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yi.zhang
 * @date 2019/9/5
 * @desc 物料下架
 */
public class BatchWorkEndActivity extends FreeUI_BaseActivity {
    @BindView(R.id.llMain)
    LinearLayout llMain;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_batchworkend;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        freeUI_titleView.setTitle("物料下架");
        return new FreeUI_AddViewEntity(getIntent().getStringExtra(FreeApi_StaticMembers.SHOW_PLUGIN_KEY), llMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
