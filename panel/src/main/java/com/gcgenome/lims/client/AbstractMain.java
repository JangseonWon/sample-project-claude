package com.gcgenome.lims.client;

import com.gcgenome.lims.dto.Message;
import com.google.gwt.core.client.EntryPoint;
import elemental2.core.Global;
import elemental2.dom.DomGlobal;
import jsinterop.base.JsPropertyMap;
import org.jboss.elemento.Elements;

public abstract class AbstractMain implements EntryPoint {
	protected abstract CollapseElement<?> collapse(String id, long sample, JsPropertyMap<?> service);
	protected abstract ExpandElement<?> expand(String id, long sample, JsPropertyMap<?> service);
	@Override
	public void onModuleLoad() {
		JsPropertyMap<String> params = params(DomGlobal.window.location.search);
		String id = params.get("id");
		long sample = Long.parseLong(params.get("sample").replace("-", ""));
		String service = params.get("service");
		ServiceApi.service(service).then(svc->{
			CollapseElement<?> elemCollapsed = collapse(id, sample, svc);
			ExpandElement<?> elemExpand = expand(id, sample, svc);
			Elements.body().add(elemCollapsed).add(elemExpand);
			elemCollapsed.onStateChange(evt->{
				elemExpand.update();
				elemCollapsed.element().style.display = "none";
				elemExpand.element().style.display = null;
			});
			elemExpand.onStateChange(evt->{
				elemCollapsed.update();
				elemCollapsed.element().style.display = "flex";
				elemExpand.element().style.display = "none";
			});
			elemExpand.element().style.display = "none";
			elemCollapsed.update();
			return null;
		});
		Message msg = Message.builder().id(id).type(Message.MessageType.COLLAPSE).param("64px").build();
		DomGlobal.window.parent.postMessage(Global.JSON.stringify(msg), "*");
	}
	private static native JsPropertyMap<String> params(String queryString) /*-{
		var params = {};
		queryString.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
		return params;
	}-*/;
}
