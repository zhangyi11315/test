package com.glory.huatianmms.activity;

import com.glory.huatianmms.application.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pda.platform.ui.ui_pdaplatform.base.FreeUI_BaseActivity;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_AddViewEntity;
import com.pda.platform.ui.ui_pdaplatform.entity.FreeUI_TabCountEntity;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_ActivityManager;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_StaticMembers;

import java.util.List;

/**
 * @author yi.zhang
 * @date 2019/9/2
 * @desc
 */
public class MainActivity  extends FreeUI_BaseActivity {
    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        String json = "[{\n" +
                "\t\t\"title\": \"功能管理\",\n" +
                "\t\t\"normalPicName\": \"function\",\n" +
                "\t\t\"selectPicName\": \"function_select\",\n" +
                "\t\t\"whatFragment\": 1,\n" +
                "\t\t\"tabIndexGridEntityList\": [{\n" +
                "\t\t\t\"picName\": \"start_function\",\n" +
                "\t\t\t\"showPlugin\": \"BatchWorkBeginActivity\",\n" +
                "\t\t\t\"text\": \"物料上架\",\n" +
                "\t\t\t\"skipClass\": \"com.glory.huatianmms.activity.DialogActivity\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"picName\": \"end_function\",\n" +
                "\t\t\t\"showPlugin\": \"BatchWorkEndActivity\",\n" +
                "\t\t\t\"text\": \"物料下架\",\n" +
                "\t\t\t\"skipClass\": \"com.glory.huatianmms.activity.DialogActivity\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"picName\": \"uppershelf\",\n" +
                "\t\t\t\"showPlugin\": \"uppershelf\",\n" +
                "\t\t\t\"text\": \"治具上架\",\n" +
                "\t\t\t\"skipClass\": \"com.glory.huatianmms.activity.DialogActivity\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"picName\": \"lowershelf\",\n" +
                "\t\t\t\"showPlugin\": \"lowershelf\",\n" +
                "\t\t\t\"text\": \"治具下架\",\n" +
                "\t\t\t\"skipClass\": \"com.glory.huatianmms.activity.DialogActivity\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"picName\": \"check\",\n" +
                "\t\t\t\"showPlugin\": \"DailyInspectionActivity\",\n" +
                "\t\t\t\"text\": \"物料盘点\",\n" +
                "\t\t\t\"skipClass\": \"com.glory.huatianmms.activity.DialogActivity\"\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"title\": \"我的信息\",\n" +
                "\t\t\"normalPicName\": \"mine\",\n" +
                "\t\t\"selectPicName\": \"mine_select\",\n" +
                "\t\t\"whatFragment\": 3,\n" +
                "\t\t\"tabIndexGridEntityList\": [{\n" +
                "\t\t\t\"picName\": \"\",\n" +
                "\t\t\t\"showPlugin\": \"用户名\",\n" +
                "\t\t\t\"text\": \"用户名\",\n" +
                "\t\t\t\"content\": \""+ App.userEntity.getUSERNAME()+"\",\n" +
                "\t\t\t\"skipClass\": \"\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"picName\": \"\",\n" +
                "\t\t\t\"showPlugin\": \"中文名\",\n" +
                "\t\t\t\"text\": \"中文名\",\n" +
                "\t\t\t\"content\": \""+ App.userEntity.getDESCRIPTION()+"\",\n" +
                "\t\t\t\"skipClass\": \"\"\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "]";
        List<FreeUI_TabCountEntity> entity = new Gson().fromJson(json, new TypeToken<List<FreeUI_TabCountEntity>>() {
        }.getType());
        goHomeActivity(0,FreeApi_StaticMembers.THEME_SKY_BLUE, getBytesList(entity));
        FreeApi_ActivityManager.getActivityManager().finishActivity();
    }

    @Override
    protected FreeUI_AddViewEntity getBaseEntity() {
        return null;
    }
}
