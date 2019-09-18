package com.glory.huatianmms.entity.request;

/**
 * @author yi.zhang
 * @date 2019/6/10
 * @desc 消息结构
 */
public class MessageFormat {

    public MessageHeader getHeader() {
        return Header == null ? new MessageHeader() : Header;
    }

    public void setHeader(MessageHeader header) {
        Header = header;
    }

    public Object getBody() {
        return Body == null ? new Object() : Body;
    }

    public void setBody(Object body) {
        Body = body;
    }

    private MessageHeader Header;

    private Object Body;

}
