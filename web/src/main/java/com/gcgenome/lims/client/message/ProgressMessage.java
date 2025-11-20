package com.gcgenome.lims.client.message;

import com.gcgenome.lims.dto.Message;
import com.gcgenome.lims.dto.Progress;
import elemental2.dom.HTMLIFrameElement;
import jsinterop.base.Js;
import net.sayaya.ui.ProgressBarElement;

public class ProgressMessage implements MessageProcessor {
    private final ProgressBarElement progressBar;
    public ProgressMessage(ProgressBarElement progressBar) {
        this.progressBar = progressBar;
    }
    @Override
    public Message.MessageType type() {
        return Message.MessageType.PROGRESS;
    }

    @Override
    public void exec(String prefix, HTMLIFrameElement child, Message msg) {
        Progress progress = Js.uncheckedCast(msg.param);
        if (!progress.closed) progressBar.open().determinate(progress.determinate).progress(progress.progress);
        else progressBar.close();
    }
}
