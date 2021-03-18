package cn.catgod.netty.rpc.model;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-11
 * @Version 1.0
 */

import java.util.Map;

public class MessageKeyVal {

    private Map<String, Object> messageKeyVal;

    public Map<String, Object> getMessageKeyVal() {
        return messageKeyVal;
    }

    public void setMessageKeyVal(Map<String, Object> messageKeyVal) {
        this.messageKeyVal = messageKeyVal;
    }
}
