package com.glory.huatianmms.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.huatianmms.R;
import com.glory.huatianmms.entity.LotEntity;

import java.util.List;


public class CheckListAdapter extends BaseQuickAdapter<LotEntity, BaseViewHolder> {

    public CheckListAdapter(@Nullable List<LotEntity> data) {
        super(R.layout.adapter_check_list, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, final LotEntity item) {
        helper.setText(R.id.tvMainQty, String.valueOf(item.getMAINQTY()));
        if (item.getTRANSMAINQTY() > 0) {
            helper.setText(R.id.tvTrueQty,  String.valueOf(item.getTRANSMAINQTY()));
        }else {
            helper.setText(R.id.tvTrueQty,  "");
        }
        helper.setText(R.id.tvId, item.getLOTID());
    }
}
