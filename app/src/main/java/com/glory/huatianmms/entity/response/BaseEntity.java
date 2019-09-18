package com.glory.huatianmms.entity.response;

import android.content.Context;
import android.widget.Toast;

import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_ToastUtils;

import java.io.Serializable;

/**
 * @author anyang
 * @date 2017/9/12
 * @desc
 */

public class BaseEntity<T> implements Serializable {

    private ResponseEntity<T> Response;

    public ResponseEntity<T> getResponse() {
        return Response == null ? new ResponseEntity<>() : Response;
    }

    public void setResponse(ResponseEntity<T> response) {
        Response = response;
    }

    //获取到对象后，必须要判断是否获取成功
    public boolean isSuccess(Context context) {
        if (Response != null) {
            if (Response.getHeader().getRESULT().equals("SUCCESS")) { //新增测试用
                  //去除boby为空的判断
//                if (Message.getBody() != null) {
//                        return true;
//                    } else {
//                        FreeApi_ToastUtils.showFreeToast("返回Body数据为空，请检查后台数据", context, false, Toast.LENGTH_SHORT);
//                        return false;
//                    }
                return true;
            } else {
                    FreeApi_ToastUtils.showFreeToast(Response.getHeader().getRESULTMESSAGE(), context, false, Toast.LENGTH_SHORT);
                    return false;
            }
        } else {
                FreeApi_ToastUtils.showFreeToast("返回data数据为空，请检查后台数据", context, false, Toast.LENGTH_SHORT);
                return false;
        }
    }
}
