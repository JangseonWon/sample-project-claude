package com.gcgenome.lims.client;

import com.gcgenome.lims.client.message.*;
import elemental2.dom.*;
import net.sayaya.ui.HTMLElementBuilder;
import net.sayaya.ui.ProgressBarElement;
import net.sayaya.ui.event.HasClickHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HTMLContainerBuilder;

import java.util.LinkedList;
import java.util.List;

import static org.jboss.elemento.Elements.div;

final class SubjectPanel extends HTMLElementBuilder<HTMLDivElement, SubjectPanel> implements HasClickHandlers {
    static SubjectPanel build(String id, long sample, String service, String url) {
        return new SubjectPanel(div(), id, sample, service, url);
    }
    private final ProgressBarElement progressBar = ProgressBarElement.progressBar().style("z-index:99999; position: absolute;top: 0;left: 0;right: 0; height: 1px;");
    final HTMLContainerBuilder<HTMLElement> ripple = Elements.span().css("mdc-list-item__ripple");
    HTMLContainerBuilder<HTMLIFrameElement> iframe;
    private final String id;
    private final long sample;
    private final String service;
    private final String url;
    private final String prefix;
    private final List<Processor> eventHandlers = new LinkedList<>();
    private SubjectPanel(HTMLContainerBuilder<HTMLDivElement> div, String id, long sample, String service, String url) {
        super(div.css("mdc-list-item").style("height: 100%; padding:0").attr("role", "option"));
        this.id = id;
        this.sample = sample;
        this.service = service;
        this.url = url;
        if(url.indexOf("/", 1) > 0) prefix = url.substring(0, url.indexOf("/", 1));
        else prefix = "";

        iframe = Elements.iframe()
                .id(id)
                .attr("frameborder", "0")
                .attr("scrolling", "auto")
                .attr("allow", "autoplay")
                .style("width:0px; min-width: 1200px; height:0px; max-height: calc(100vh - 183px); transition: all 150ms ease 0s;");
        div.add(progressBar.close()).add(ripple).add(iframe);
        eventHandlers.add(new RequestMessage());
        eventHandlers.add(new ProgressMessage(progressBar));
        eventHandlers.add(new ProgressMessageOld(progressBar));
        eventHandlers.add(new WindowMessage());
        listen();
    }
    public SubjectPanel addEventHandler(Processor handler) {
        this.eventHandlers.add(handler);
        return this;
    }
    public SubjectPanel enabled(boolean enabled) {
        if (!enabled) this.css("mdc-list-item--disabled");
        else this.ncss("mdc-list-item--disabled");
        return this.that();
    }
    void update() {
        iframe.element().src = url + "?id=" + id + "&service=" + service + "&sample=" + sample;
    }
    @Override
    public SubjectPanel that() {
        return this;
    }
    public HandlerRegistration onClick(EventListener listener) {
        return this.onClick(this.that().element(), listener);
    }

    private void listen() {
        DomGlobal.window.addEventListener("message", _evt->{ try {
            elemental2.dom.MessageEvent<String> evt = (MessageEvent<String>) _evt;
            if(!evt.source.parent.frameElement.asHTMLIFrameElement().id.equals(this.id)) return;
            String json = evt.data;
            if(json == null || json.isEmpty()) return;
            for(var processor: eventHandlers) if(processor.chk(json)) {
                processor.exec(prefix, iframe.element(), json);
                break;
            }
        } catch(Exception ex) {
            DomGlobal.console.error(ex.getMessage());
        }});
    }
}
