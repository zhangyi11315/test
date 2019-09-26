package com.glory.huatianmms.util;

import com.glory.huatianmms.application.App;
import com.glory.huatianmms.entity.request.MessageFormat;
import com.glory.huatianmms.entity.request.MessageHeader;
import com.glory.huatianmms.entity.request.RequestMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pda.platform.ui.ui_pdaplatform.callback.FreeUI_EntityCallBack;
import com.pda.platform.ui.ui_pdaplatform.utils_public.FreeApi_NetUtils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yi.zhang
 * @date 2019/9/2
 * @desc 网络请求处理类
 */
public class NetUtils {

    public static <T> void requestGetNet(String url, Object tag, FreeUI_EntityCallBack<T> callBack, String method, JsonObject jsonString) {
        String uuID = UUID.randomUUID().toString();
        MessageHeader header = new MessageHeader();
        header.setMESSAGENAME(method);
        header.setTRANSACTIONID(uuID);
        header.setORGRRN(App.userEntity.getDEFAULTORGRRN());
        header.setORGNAME("FAB1");
        header.setUSERNAME(App.userEntity.getUSERNAME());
        MessageFormat format = new MessageFormat();
        format.setBody(jsonString);
        format.setHeader(header);
        RequestMessage message = new RequestMessage();
        message.setRequest(format);
        Map<String, String> map = new HashMap<>();
        map.put("senderId", "HuaTianDirectSender");
        map.put("requestMessage",StringEscapeUtils.unescapeJson(new Gson().toJson(message)));
        map.put("timeOut", "15000");
        FreeApi_NetUtils.requestGetNet(url, tag, map, callBack);
    }

    public static <T> void requestGetNet(String url, String senderId,Object tag, FreeUI_EntityCallBack<T> callBack, String method, JsonObject jsonString) {
        String uuID = UUID.randomUUID().toString();
        MessageHeader header = new MessageHeader();
        header.setMESSAGENAME(method);
        header.setTRANSACTIONID(uuID);
        header.setORGRRN(App.userEntity.getDEFAULTORGRRN());
        header.setORGNAME("FAB1");
        header.setUSERNAME(App.userEntity.getUSERNAME());
        MessageFormat format = new MessageFormat();
        format.setBody(jsonString);
        format.setHeader(header);
        RequestMessage message = new RequestMessage();
        message.setRequest(format);
        Map<String, String> map = new HashMap<>();
        map.put("senderId", senderId);
        map.put("requestMessage",StringEscapeUtils.unescapeJson(new Gson().toJson(message)));
        map.put("timeOut", "15000");
        FreeApi_NetUtils.requestGetNet(url, tag, map, callBack);
    }
}
