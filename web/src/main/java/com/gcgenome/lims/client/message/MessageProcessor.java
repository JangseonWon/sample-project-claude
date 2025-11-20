package com.gcgenome.lims.client.message;

import com.gcgenome.lims.dto.Message;
import elemental2.core.Global;
import elemental2.dom.HTMLIFrameElement;
import jsinterop.base.Js;

public interface MessageProcessor extends Processor {
    @Override default boolean chk(String json) {
        if(!json.contains("___id")) return false;
        Message msg = Js.uncheckedCast(Global.JSON.parse(json));
        return msg.type.equals(type().name());
    }
    @Override default void exec(String prefix, HTMLIFrameElement child, String json) {
        Message msg = Js.uncheckedCast(Global.JSON.parse(json));
        exec(prefix, child, msg);
    }
    Message.MessageType type();
    void exec(String prefix, HTMLIFrameElement child, Message msg);
}
