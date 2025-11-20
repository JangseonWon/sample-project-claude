package com.gcgenome.lims.client.message;

import com.gcgenome.lims.dto.Message;
import elemental2.core.Global;
import elemental2.dom.HTMLIFrameElement;
import jsinterop.base.Js;

public class RequestMessage implements MessageProcessor {
    @Override
    public Message.MessageType type() {
        return Message.MessageType.REQUEST;
    }

    @Override
    public void exec(String prefix, HTMLIFrameElement child, Message msg) {
        String url = Js.uncheckedCast(msg.param);
        Message response = Message.builder().id(msg.id).type(Message.MessageType.RESPONSE).param(prefix + url).build();
        child.contentWindow.postMessage(Global.JSON.stringify(response), "*");
    }
}
