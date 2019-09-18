package com.glory.huatianmms.entity.request;

/**
 * @author yi.zhang
 * @date 2019/6/10
 * @desc 请求消息
 */
public class RequestMessage {

    public MessageFormat getRequest() {
        return Request == null ? new MessageFormat() : Request;
    }

    public void setRequest(MessageFormat request) {
        Request = request;
    }

    private MessageFormat Request;

}
