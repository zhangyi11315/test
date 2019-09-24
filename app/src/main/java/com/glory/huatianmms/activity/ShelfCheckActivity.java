package com.glory.huatianmms.activity;

import android.widget.LinearLayout;

import com.glory.huatianmms.R;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;

import butterknife.BindView;

/**
 * @author yi.zhang
 * @date 2019/9/5
 * @desc 物料上架
 */
public class ShelfCheckActivity extends FreeUI_BaseActivity {
    @BindView(R.id.llMain)
    LinearLayout llMain;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shelf_check;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        freeUI_titleView.setTitle("治具盘点");
        freeUI_titleView.setRightListener("提交", view -> {

        });
        return new FreeUI_AddViewEntity("header", llMain);
    }
}
