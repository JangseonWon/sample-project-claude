package com.gcgenome.lims.client;

import com.gcgenome.lims.api.SampleApi;
import com.gcgenome.lims.client.message.MessageProcessor;
import com.gcgenome.lims.dto.Message;
import com.gcgenome.lims.dto.Service;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import elemental2.dom.CSSProperties;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLIFrameElement;
import elemental2.promise.Promise;
import net.sayaya.ui.HTMLElementBuilder;
import net.sayaya.ui.TabBarElement;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HTMLContainerBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.jboss.elemento.Elements.div;

public class ServiceTabElement extends HTMLElementBuilder<HTMLDivElement, ServiceTabElement> {
	public static ServiceTabElement build(long sample, List<Service> services) {
		return new ServiceTabElement(sample, services, div());
	}
	private final long sample;
	private final List<Service> services;
	private final TabBarElement tab;
	private final HTMLContainerBuilder<HTMLDivElement> stack = div().style("min-height: calc(100% - 182px);" +
			"background: #FFFFFF;" +
			"overflow: auto;" +
			"display: flex;" +
			"flex-wrap: nowrap;" +
			"flex-direction: column;" +
			"align-content: stretch;" +
			"align-items: stretch;");
	private final List<SubjectPanel> children;
	private final Refresh refreshMessage = new Refresh();
	private ServiceTabElement(long sample, List<Service> services, HTMLContainerBuilder<HTMLDivElement> e) {
		super(e.css("request"));
		this.sample = sample;
		this.services = services;
		children = new LinkedList<>();
		var tabs = services.stream().map(dto -> TabBarElement.tab().text(Objects.isNull(dto.displayName) ? dto.name : dto.displayName)).toArray(TabBarElement.Tab[]::new);		tab = TabBarElement.tabBar(tabs);
		tab.onValueChange(evt->layout(evt.value()));
		e.add(tab).add(stack);
		tab.activate(0);
	}
	private void layout(int tab) {
		Service service = services.get(tab);
		stack.element().innerHTML = "";
		children.clear();
		SampleApi.subjects(sample, service.code)
				.then(urls->{
					for(int i = 0; i < urls.size(); ++i) {
						if(i > 0) stack.add(divider());
						SubjectPanel child = SubjectPanel.build(Document.get().createUniqueId(), sample, service.code, urls.get(i));
						stack.add(child);
						children.add(child);
						child.addEventHandler(refreshMessage);
						child.addEventHandler(new Collapse(child));
						child.addEventHandler(new Stretch(child));
						child.addEventHandler(new FullScreen(child));
						child.update();
					}
					return Promise.resolve(urls);
				});
	}
	private HTMLDivElement divider() {
		return div().css("mdc-list-divider").attr("role", "separator").element();
	}
	private void hideExcept(SubjectPanel one) {
		for(int i = 0; i < stack.element().childElementCount; ++i) {
			HTMLElement e = (HTMLElement) stack.element().childNodes.getAt(i);
			e.style.maxHeight = CSSProperties.MaxHeightUnionType.of("0px");
		}
		one.element().style.maxHeight = null;
		Scheduler.get().scheduleFixedDelay(()->{
			for(int i = 0; i < stack.element().childElementCount; ++i) {
				HTMLElement e = (HTMLElement) stack.element().childNodes.getAt(i);
				e.style.display = "none";
			}
			one.element().style.display = null;
			return false;
		}, 100);
	}
	private void showAll() {
		for(int i = 0; i < stack.element().childElementCount; ++i) {
			HTMLElement e = (HTMLElement) stack.element().childNodes.getAt(i);
			e.style.maxHeight = null;
			e.style.display = null;
		}
	}
	private final class Collapse implements MessageProcessor {
		private final SubjectPanel one;
		public Collapse(SubjectPanel one) {
			this.one = one;
		}
		@Override public Message.MessageType type() {
			return Message.MessageType.COLLAPSE;
		}
		@Override public void exec(String prefix, HTMLIFrameElement child, Message msg) {
			String height = msg.param.toString();
			one.iframe.element().style.height = CSSProperties.HeightUnionType.of(height);
			one.iframe.element().style.width = CSSProperties.WidthUnionType.of("0px");
			one.ripple.element().style.display = null;
			showAll();
		}
	}
	private final class Stretch implements MessageProcessor {
		private final SubjectPanel one;
		public Stretch(SubjectPanel one) {
			this.one = one;
		}
		@Override
		public Message.MessageType type() {
			return Message.MessageType.STRETCH;
		}
		@Override
		public void exec(String prefix, HTMLIFrameElement child, Message msg) {
			one.iframe.element().style.height = CSSProperties.HeightUnionType.of("100vh");
			one.ripple.element().style.display = "none";
			hideExcept(one);
		}
	}
	private final class FullScreen implements MessageProcessor {
		private final SubjectPanel one;
		public FullScreen(SubjectPanel one) {
			this.one = one;
		}
		@Override
		public Message.MessageType type() {
			return Message.MessageType.FULLSCREEN;
		}
		@Override
		public void exec(String prefix, HTMLIFrameElement child, Message msg) {
			one.iframe.element().style.height = CSSProperties.HeightUnionType.of("100vh");
			one.iframe.element().style.width = CSSProperties.WidthUnionType.of(Elements.body().element().clientWidth + "px");
			one.ripple.element().style.display = "none";
			hideExcept(one);
		}
	}
	private final class Refresh implements MessageProcessor {
		@Override
		public Message.MessageType type() {
			return Message.MessageType.REFRESH;
		}

		@Override
		public void exec(String prefix, HTMLIFrameElement child, Message msg) {
			for(var c: children) c.update();
		}
	}
	@Override
	public ServiceTabElement that() {
		return this;
	}
}