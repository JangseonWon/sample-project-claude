package com.gcgenome.lims.client.message;

import com.gcgenome.lims.dto.Window;
import elemental2.core.Global;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLIFrameElement;

import static elemental2.core.Global.JSON;

public class WindowMessage implements Processor {
    @Override
    public boolean chk(String json) {
        return json.contains("url");
    }
    @Override
    public void exec(String prefix, HTMLIFrameElement child, String json) {
        Window window = (Window) JSON.parse(json);
        String url = "info.html#" + Global.encodeURI(prefix + "/" + window.url);
        DomGlobal.window.open(url, window.name, window.feature, window.shouldReplace);
    }
}
