package com.glory.huatianmms.entity.response;

import java.io.Serializable;

/**
 * @author lx
 * @date 2019/3/16
 * @desc
 */
public class ResponseEntity<T> implements Serializable {

    private ResponseHeader Header;

    private T Body;

    public T getBody() {
        return Body;
    }

    public void setBody(T Body) {
        this.Body = Body;
    }

    public ResponseHeader getHeader() {
        return Header == null ? new ResponseHeader() : Header;
    }

    public void setHeader(ResponseHeader header) {
        Header = header;
    }


}
