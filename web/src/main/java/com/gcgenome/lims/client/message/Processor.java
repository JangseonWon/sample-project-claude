package com.gcgenome.lims.client.message;

import elemental2.dom.HTMLIFrameElement;

public interface Processor {
    boolean chk(String json);
    void exec(String prefix, HTMLIFrameElement child, String json);
}
