package com.gcgenome.lims.client.message;

import com.gcgenome.lims.dto.Progress;
import elemental2.dom.HTMLIFrameElement;
import net.sayaya.ui.ProgressBarElement;

import static elemental2.core.Global.JSON;

public class ProgressMessageOld implements Processor {
    private final ProgressBarElement progressBar;
    public ProgressMessageOld(ProgressBarElement progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public boolean chk(String json) {
        return json.contains("determinate");
    }

    @Override
    public void exec(String prefix, HTMLIFrameElement child, String json) {
        Progress progress = (Progress) JSON.parse(json);
        if (!progress.closed) progressBar.open().determinate(progress.determinate).progress(progress.progress);
        else progressBar.close();
    }
}
